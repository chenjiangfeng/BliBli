package atguigu.blibli.liveFragmentmvp.model;

import atguigu.blibli.liveFragmentmvp.bean.LiveBeanOne;

/**
 * Created by 陈江峰 on 2017/4/6.
 */

public interface OnRequestListener {

    void onSuccess(LiveBeanOne bean);

    void onFail(Exception e);
}
