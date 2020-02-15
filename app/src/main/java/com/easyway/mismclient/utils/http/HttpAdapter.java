package com.easyway.mismclient.utils.http;


import com.easyway.mismclient.utils.UToast;
import com.easyway.mismclient.utils.Ulog;
import com.easyway.mismclient.utils.http.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

import static com.easyway.mismclient.base.BaseConstants.FORMAL_URL;


/**
 * @date 2018/6/4 
 * @author 侯建军 47466436@qq.com
 * @description 请填写描述
 */
public class HttpAdapter {


    private static HttpApis service;

    private static OkHttpClient client = new OkHttpClient();

    public static void init() {
        try {
            client = client.newBuilder()
                    .addNetworkInterceptor(new HttpInterceptor())
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    //处理多BaseUrl,添加应用拦截器
                    .addInterceptor(new MoreBaseUrlInterceptor())
                    .readTimeout(5, TimeUnit.SECONDS)
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(FORMAL_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();
            service = retrofit.create(HttpApis.class);
        } catch (Exception e) {
            UToast.showText("您输入的ip端口不符合规范");
        }
    }


    public static HttpApis getService() {
        if (service == null) {
            init();
        }
        return service;
    }


    public static class HttpInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {


            Request request = chain.request();
            HttpUrl httpUrl = request.url()
                    .newBuilder()
//                    .addQueryParameter("IMEI", "864686037902690")
//                    .addQueryParameter("user_id", "1")
                    .build();

            Request build = request.newBuilder()
                    // add common header
                    .url(httpUrl)
                    .build();
            Response response = chain.proceed(build);

            Ulog.i("url", request.url() + "?" + bodyToString(request.body()));
            return response;
        }
    }

    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

}
