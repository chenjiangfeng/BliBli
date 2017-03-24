package atguigu.blibli.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.blibli.R;
import atguigu.blibli.adapter.BranchAdapter;
import atguigu.blibli.bean.BranchBean;
import atguigu.blibli.bean.PartitionTwoBean;
import atguigu.blibli.utils.Contants;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 陈江峰 on 2017/3/21.
 */

public class PartitionFragment extends BaseFragment {
    @InjectView(R.id.rv_view)
    RecyclerView rvView;
    @InjectView(R.id.sw_view)
    SwipeRefreshLayout swView;
    private List<BranchBean.DataBean> data;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_partition, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //下拉刷新
        refresh();

        //联网请求数据

        getDataFromeNet();
    }

    private void refresh() {
        swView.setDistanceToTriggerSync(100);
        // 设置颜色
        swView.setColorSchemeColors(Color.BLACK, Color.RED);
        //设置背景颜色
        swView.setProgressBackgroundColorSchemeResource(android.R.color.holo_orange_dark);
        // 下拉刷新
        swView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataFromeNet();
            }
        });

    }

    private void getDataFromeNet() {
        OkHttpUtils.get().url(Contants.BRANCH_URL)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(mContext,""+e,Toast.LENGTH_SHORT).show();
                swView.setRefreshing(false);
            }

            @Override
            public void onResponse(String response, int id) {
                //解析
                analysisData(response);

                getDataRefresh();

            }
        });
    }

    private void getDataRefresh() {
        OkHttpUtils.get().url(Contants.PARTITION_URL).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(mContext, ""+e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //解析数据
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        PartitionTwoBean partitionTwoBean = JSON.parseObject(json,PartitionTwoBean.class);
        List<PartitionTwoBean.DataBean> dataTwo = partitionTwoBean.getData();
        //设置适配器
        if(data!=null&&data.size()>0) {
            swView.setRefreshing(false);
            //设置适配器

            BranchAdapter adapter = new BranchAdapter(mContext,data,dataTwo);

            adapter.notifyDataSetChanged();
            rvView.setAdapter(adapter);
        }
        //布局管理器
        rvView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));

    }


    //头的数据的解析
    private void analysisData(String response) {
        BranchBean branchBean = JSON.parseObject(response,BranchBean.class);
         data = branchBean.getData();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
