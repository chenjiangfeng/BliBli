package atguigu.blibli.liveFragmentmvp.presenter;

import android.os.Handler;

import atguigu.blibli.liveFragmentmvp.bean.LiveBeanOne;
import atguigu.blibli.liveFragmentmvp.model.LiveModelImpl;
import atguigu.blibli.liveFragmentmvp.model.OnRequestListener;
import atguigu.blibli.liveFragmentmvp.view.LiveFragment;

/**
 * Created by 陈江峰 on 2017/4/6.
 */

public class LivePresenter {
    private LiveModelImpl liveModel;
    private LiveFragment liveFragment;

    public LivePresenter(LiveFragment liveFragment){

        this.liveFragment = liveFragment;

        this.liveModel = new LiveModelImpl();

    }
    public void getDataFromNet(){
        liveFragment.showProgressBar();
        liveModel.getDataFromeNet(liveFragment.getUrl(), new OnRequestListener() {
            @Override
            public void onSuccess(final LiveBeanOne bean) {
                if(bean!=null) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            liveFragment.onSuccess(bean);
                            liveFragment.hideProgressBar();
                        }
                    },500);

                }
            }
            @Override
            public void onFail(Exception e) {
                liveFragment.hideProgressBar();
                liveFragment.onFailed(e);
            }
        });
    }


}
