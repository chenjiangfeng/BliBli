package atguigu.blibli.liveFragmentmvp.view;

import atguigu.blibli.liveFragmentmvp.bean.LiveBeanOne;

/**
 * Created by 陈江峰 on 2017/4/6.
 */

public interface ILiveView {
    /**
     * 显示progressBar
     *
     */
    void showProgressBar();

    /**
     *隐藏progressBar
     */
    void hideProgressBar();

    /**
     * 成功
     * @param liveBean
     */
    void onSuccess(LiveBeanOne liveBean);

    /**
     * 失败
     * @param e
     */

    void onFailed(Exception e);

    /**
     * URL
     */
    String getUrl();
}
