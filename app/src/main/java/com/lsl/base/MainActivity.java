package com.lsl.base;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.reflect.TypeToken;
import com.lsl.base.bean.ActivityBean;
import com.lsl.base.bean.ContractBean;
import com.lsl.base.common.BLog;
import com.lsl.base.common.BaseActivity;
import com.lsl.base.common.BaseBean;
import com.lsl.base.common.URLs;
import com.lsl.base.example.CacheActivity;
import com.lsl.base.loader.GlideApp;
import com.lsl.base.net.OkHttp;
import com.lsl.base.net.cache.CacheMode;
import com.lsl.base.net.callback.JsonCallback;
import com.lsl.base.net.callback.StringDialogCallback;
import com.lsl.base.parser.BaseBeanParser;
import com.lsl.base.utils.ContractUtil;

import java.text.ParseException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        GlideApp.with(this).load("").apply(RequestOptions.fitCenterTransform().placeholder(3)).into(new ImageView(this));
//        Glide.with(MainActivity.this).load("").into(new ImageView(this));

    }

    @OnClick(R.id.fab)
    public void fab(){
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this);
        builder.title("OkHttp");
        builder.content("是否导入通讯录");
        builder.positiveText("确认");
        builder.negativeText("取消");
        builder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                Toast.makeText(MainActivity.this,"onPositive",Toast.LENGTH_SHORT).show();
                getContract();
            }
        });
        builder.onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                Toast.makeText(MainActivity.this,"onNegative",Toast.LENGTH_SHORT).show();
            }
        });
        MaterialDialog dialog =builder.show();
    }

    private void getContract(){
        //"http://192.168.10.204:8080/user_contacts/getList"
//       OkHttp.get(URLs.GET_HQ_ACTIVITY_LIST)
//               .tag(this)
//               .cacheMode(CacheMode.NO_CACHE)
//               .execute(new StringDialogCallback(this) {
//                   @Override
//                   public void onSuccess(String s, Call call, Response response) {
//                       BaseActivity activity = (BaseActivity) call.request().tag();
//                       activity.startActivity(new Intent(activity, CacheActivity.class));
//                   }
//               });
        OkHttp.get(URLs.GET_HQ_ACTIVITY_LIST)
                .tag(this)
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new JsonCallback<BaseBean<List<ActivityBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<ActivityBean>> activityBeanBaseBean, Call call, Response response) {
                        BLog.i("获取成功");
                        BLog.i("地址：" + activityBeanBaseBean.getData().get(0).getImagePath());
                    }
                });
    }



}
