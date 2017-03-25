package atguigu.blibli.utils;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by 陈江峰 on 2017/3/25.
 */

public class GetDataFromNet {


    public static void getDataFromNet(String url , final LoadNetHttp loadNetHttp) {
        OkHttpUtils.get().url(url)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if(loadNetHttp!= null) {
                    loadNetHttp.failure(e+"");
                }

            }

            @Override
            public void onResponse(String response, int id) {
                if(loadNetHttp!= null) {
                    loadNetHttp.success(response);
                }
            }
        });
    }
}
