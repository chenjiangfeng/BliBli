package atguigu.blibli.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.util.List;

import atguigu.blibli.R;
import atguigu.blibli.adapter.FindAdapter;
import atguigu.blibli.bean.FindBean;
import atguigu.blibli.utils.Contants;
import atguigu.blibli.utils.GetDataFromNet;
import atguigu.blibli.utils.LoadNetHttp;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 陈江峰 on 2017/3/24.
 */

public class RankingsTwoFragment extends BaseFragment {
    @InjectView(R.id.rec_view)
    RecyclerView recView;
    @InjectView(R.id.re_rank)
    SwipeRefreshLayout swView;

    @Override
    public View initView() {
//
        View view = View.inflate(mContext, R.layout.fragment_rank, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
//        textView.setText("TWO");

        refresh();



            //请求数据
        getDataFromNet();


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
                getDataFromNet();
            }
        });

    }

    public void getDataFromNet() {
        GetDataFromNet.getDataFromNet(Contants.REGION_URLONE, new LoadNetHttp() {
            @Override
            public void success(String context) {
                //解析数据
                swView.setRefreshing(false);
                FindBean bean =  JSON.parseObject(context,FindBean.class);
                List<FindBean.DataBean> data = bean.getData();
                if(data!=null&&data.size()>0) {

                    FindAdapter adapter = new FindAdapter(mContext,data);
                    recView.setAdapter(adapter);
                    //布局管理器
                    GridLayoutManager manager =new GridLayoutManager(mContext,1);
                    recView.setLayoutManager(manager);
                }
            }
            @Override
            public void failure(String error) {
                Toast.makeText(mContext, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
