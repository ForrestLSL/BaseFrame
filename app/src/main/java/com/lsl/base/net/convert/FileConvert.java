package com.lsl.base.net.convert;

import android.os.Environment;
import android.text.TextUtils;
import com.lsl.base.common.AppConfig;
import com.lsl.base.common.BLog;
import com.lsl.base.net.OkHttp;
import com.lsl.base.net.callback.AbsCallback;
import com.lsl.base.net.utils.HttpUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import okhttp3.Response;

/**
 * Created by Forrest
 * on 2017/7/25 08:51
 */

public class FileConvert implements Converter<File> {

    private String destFileDir;     //目标文件存储的文件夹路径
    private String destFileName;    //目标文件存储的文件名
    private AbsCallback callback;    //下载回调

    public FileConvert() {
        this(null);
    }

    public FileConvert(String destFileName) {
        this(AppConfig.DM_FOLDER, destFileName);
    }

    public FileConvert(String destFileDir, String destFileName) {
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;
    }

    public void setCallback(AbsCallback callback) {
        this.callback = callback;
    }



    @Override
    public File convertSuccess(Response response) throws Exception {
        if (TextUtils.isEmpty(destFileDir)) {
            destFileDir = Environment.getExternalStorageDirectory() + "/download/";
        }
        if (TextUtils.isEmpty(destFileName)){
            destFileName = HttpUtils.getNetFileName(response,response.request().url().toString());
        }
        File dir = new File(destFileDir);
        if (!dir.exists()) dir.mkdirs();
        File file = new File(dir, destFileName);
        if (file.exists()) file.delete();

        long lastRefreshUiTime = 0;    //最后一次刷新的时间
        long lastWriteBytes = 0;       //最后一次写入字节数据

        InputStream is = null;
        byte[] buf = new byte[2048];
        FileOutputStream fos = null;

        try {
            is = response.body().byteStream();
            final long total = response.body().contentLength();
            long sum = 0;
            int len;
            fos = new FileOutputStream(file);

            while ((len = is.read(buf)) != -1){
                sum += len;
                fos.write(buf, 0, len);
                //下载进度回调
                if (callback != null){
                    final long finalSum = sum;
                    long curTime = System.currentTimeMillis();
                    //每200毫秒刷新一次数据
                    if (curTime - lastRefreshUiTime >= OkHttp.REFRESH_TIME || finalSum == total){
                        long diffTime = (curTime - lastRefreshUiTime) /1000;
                        if (diffTime == 0) diffTime += 1;
                        long diffBytes = finalSum - lastWriteBytes;
                        final long netWorkSpeed = diffBytes / diffTime;
                        OkHttp.getInstance().getDelivery().post(new Runnable() {
                            @Override
                            public void run() {
                                callback.downloadProgress(finalSum, total, finalSum*1.0f / total, netWorkSpeed);
                            }
                        });
                        lastRefreshUiTime = System.currentTimeMillis();
                        lastWriteBytes = finalSum;
                    }
                }
            }
            fos.flush();
            return file;
        }finally {
            try {
                if (is != null) is.close();
            }catch (Exception e){
                BLog.e(e);
            }
            try {
                if (fos != null) fos.close();
            }catch (Exception e){
                BLog.e(e);
            }
        }
    }
}
