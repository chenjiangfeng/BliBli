package atguigu.blibli.liveFragmentmvp.model;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import atguigu.blibli.liveFragmentmvp.bean.LiveBeanOne;
import okhttp3.Call;

/**
 * Created by 陈江峰 on 2017/4/6.
 */

public class LiveModelImpl  implements ILiveMode{


    @Override
    public void getDataFromeNet(String url, final OnRequestListener onRequestListener) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                onRequestListener.onFail(e);
            }

            @Override
            public void onResponse(String response, int id) {
                if(onRequestListener!=null&&response!=null) {
                    LiveBeanOne liveBean = JSON.parseObject(response,LiveBeanOne.class);

                    onRequestListener.onSuccess(liveBean);
                }


            }
        });
    }
}
