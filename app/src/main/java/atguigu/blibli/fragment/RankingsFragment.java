package atguigu.blibli.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.blibli.R;
import atguigu.blibli.adapter.FindAdapter;
import atguigu.blibli.bean.FindBean;
import atguigu.blibli.utils.Contants;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 陈江峰 on 2017/3/24.
 */

public class RankingsFragment extends BaseFragment {
    @InjectView(R.id.rec_view)
    RecyclerView recView;
    @InjectView(R.id.re_rank)
    SwipeRefreshLayout reRank;
    private FindAdapter adapter;

    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.fragment_rank, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //刷新
        refresh();
        getDataFromeNet();

    }

    private void refresh() {
        reRank.setDistanceToTriggerSync(100);
        // 设置颜色
        reRank.setColorSchemeColors(Color.BLACK, Color.RED);
        //设置背景颜色
        reRank.setProgressBackgroundColorSchemeResource(android.R.color.holo_orange_dark);
        // 下拉刷新
        reRank.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataFromeNet();
            }
        });

    }

    private void getDataFromeNet() {

        OkHttpUtils.get()
                .tag(this)
                .url(Contants.REGION_URLONE)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(mContext, "" + e, Toast.LENGTH_SHORT).show();
                reRank.setRefreshing(false);
            }

            @Override
            public void onResponse(String response, int id) {
                //解析数据
                ProcessData(response);
                reRank.setRefreshing(false);
            }
        });
    }

    private void ProcessData(String response) {
        FindBean bean = JSON.parseObject(response, FindBean.class);
        List<FindBean.DataBean> data = bean.getData();
        if (data != null && data.size() > 0) {


            //设置适配器
            adapter = new FindAdapter(mContext, data);
            recView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            
            //布局管理器
            GridLayoutManager layoutManager = new GridLayoutManager(mContext, 1);
            recView.setLayoutManager(layoutManager);
        }
    }


    @Override
    public void onDestroyView() {
        OkHttpUtils.delete().tag(this);
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
