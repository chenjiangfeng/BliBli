package atguigu.blibli.model;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by 陈江峰 on 2017/4/6.
 */

public class NetUtils {

    public static void okHttpUtils(String path , final Class beanClass, final ResponseBean listener){
        OkHttpUtils.get().url(path).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

                if(listener!=null) {
                    listener.onError(e.getMessage());
                }

            }
            @Override
            public void onResponse(String response, int id) {
                if(!TextUtils.isEmpty(response)) {
                    listener.onResponse(JSON.parseObject(response,beanClass));
                }
            }
        });
    }

    public  interface ResponseBean<T>{
        void onResponse(T bean);
        void onError(String error);
    }
}
