package com.lsl.base.net.callback;

import com.google.gson.stream.JsonReader;
import com.lsl.base.common.BaseBean;
import com.lsl.base.net.request.BaseRequest;
import com.lsl.base.net.utils.Convert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by Forrest
 * on 2017/7/25 11:53
 */

public abstract class JsonCallback<T> extends AbsCallback<T>{

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        // 主要用于在所有请求之前添加公共的请求头或请求参数
        // 例如登录授权的 token
        // 使用的设备信息
        // 可以随意添加,也可以什么都不传
        // 还可以在这里对所有的参数进行加密，均在这里实现
//        request.headers("header1", "HeaderValue1")//
//                .params("params1", "ParamsValue1")//
//                .params("token", "3215sdf13ad1f65asd4f3ads1f");
    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     * <pre>
     * OkGo.get(Urls.URL_METHOD)//
     *     .tag(this)//
     *     .execute(new DialogCallback<BaseBean<ActivityBean>>(this) {
     *          @Override
     *          public void onSuccess(BaseBean<ActivityBean> responseData, Call call, Response response) {
     *              handleResponse(responseData.data, call, response);
     *          }
     *     });
     * </pre>
     */
    @Override
    public T convertSuccess(Response response ,BaseRequest request) throws Exception {

        //以下代码是通过泛型解析实际参数，泛型必须传
        //这里为了方便理解，假如请求的代码按照上述注释文档中的请求来写，那么下面分别得到是

        //DialogCallback<BaseBean<ActivityBean>> 得到的泛型，包括了泛型参数
        Type genType = getClass().getGenericSuperclass();
        //从上述的类中取出真实的泛型参数，有些类可能有多个泛型，所以是数值
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        //示例代码中，只有一个泛型，所以取出第一个，得到如下结果
        //BaseBean<ActivityBean>
        Type type = params[0];

        //这里这么写的原因是,我们需要保证上面我解析到的type泛型，仍然还具有一层参数化的泛型，也就是两层泛型
        if (!(type instanceof ParameterizedType)) throw new IllegalStateException("没有填写泛型参数");
        //如果确实还有泛型，那么我们需要取出真实的泛型，得到如下结果
        //BaseBean
        //此时，rawType的类型实际上是 class，但Class 实现了Type接口，所以我们用Type 接收没有问题
        Type rawType = ((ParameterizedType) type).getRawType();
        //这里获取最终内部泛型的类型 ActivityBean
        Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];

        //这里我们既然都已经拿到了泛型的真实类型，即对应的 class ，那么当然可以开始解析数据了，我们采用 Gson 解析
        //以下代码是根据泛型解析数据，返回对象，返回的对象自动以参数的形式传递到 onSuccess 中，可以直接使用
        JsonReader jsonReader = new JsonReader(response.body().charStream());
        if (typeArgument == Void.class) {
            //无数据类型,表示没有data数据的情况（以  new DialogCallback<BaseBean<Void>>(this)  以这种形式传递的泛型)
            BaseBean baseBean = Convert.fromJson(jsonReader, BaseBean.class);
            response.close();
            //noinspection unchecked
            return (T) baseBean;
        } else if (rawType == BaseBean.class) {
            BaseBean baseBean = Convert.fromJson(jsonReader, type);
            response.close();
            int code = baseBean.getCode();
            //这里的0是以下意思
            //一般来说服务器会和客户端约定一个数表示成功，其余的表示失败，这里根据实际情况修改
            if (code == 0) {
                //noinspection unchecked
                return (T) baseBean;
            }
//            else if (code == 100) {
//                //可以定义为强制登出
//                BLog.i("需要强制退出");
//            } else {
//                BLog.e("错误代码：" + code + "，错误信息：" + baseBean.getMessage());
//            }
        } else {
            response.close();
            throw new IllegalStateException("基类错误无法解析!");
        }
        return null;
    }
}
