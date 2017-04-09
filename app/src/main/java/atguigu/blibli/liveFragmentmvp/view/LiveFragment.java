package atguigu.blibli.liveFragmentmvp.view;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;

import atguigu.blibli.R;
import atguigu.blibli.bean.LiveBean;
import atguigu.blibli.fragment.BaseFragment;
import atguigu.blibli.liveFragmentmvp.bean.LiveBeanOne;
import atguigu.blibli.liveFragmentmvp.presenter.LivePresenter;
import atguigu.blibli.utils.Contants;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 陈江峰 on 2017/3/21.
 */

public class LiveFragment extends BaseFragment implements ILiveView {
    @InjectView(R.id.fragment_live)
    RecyclerView fragmentLive;
    @InjectView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    @InjectView(R.id.pb_live)
    ProgressBar pbLive;
    LivePresenter livePresenter;

    public LiveFragment (){
        super();
        livePresenter  = new LivePresenter(LiveFragment.this);
    }

    @Override
    public View initView() {

        View view = null;

        view = View.inflate(mContext, R.layout.fragment_live, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //联网请求数据
        livePresenter.getDataFromNet();
        refresh();
       // getDataFromNet();

    }

    private void refresh() {
        //设置滑动距离
        swiperefresh.setDistanceToTriggerSync(100);
        // 设置颜色
        swiperefresh.setColorSchemeColors(Color.RED);
        //设置背景颜色
        swiperefresh.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 下拉刷新
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                livePresenter.getDataFromNet();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        OkHttpUtils.delete().tag(this);
    }
    private boolean  show= true;
    @Override
    public void showProgressBar() {
        if(show) {
            pbLive.setVisibility(View.VISIBLE);
            show=false;
        }else {
            swiperefresh.setRefreshing(true);
        }
    }

    @Override
    public void hideProgressBar() {
        pbLive.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(LiveBeanOne liveBean) {
        pbLive.setVisibility(View.GONE);
        swiperefresh.setRefreshing(false);
        LiveBean.DataBean data = liveBean.getData();
        if (data != null) {
            //设置适配器
            LiveAdapter adapter = new LiveAdapter(mContext, data);

            fragmentLive.setAdapter(adapter);
        }
        fragmentLive.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void onFailed(Exception e) {
        Toast.makeText(mContext, ""+e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getUrl() {
        return Contants.LIVE_URL;
    }
}
