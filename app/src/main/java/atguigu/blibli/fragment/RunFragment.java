package atguigu.blibli.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.blibli.R;
import atguigu.blibli.adapter.RunAdapter;
import atguigu.blibli.bean.RunBannerBean;
import atguigu.blibli.bean.RunBean;
import atguigu.blibli.utils.Contants;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 陈江峰 on 2017/3/21.
 */

public class RunFragment extends BaseFragment {

    @InjectView(R.id.rv)
    RecyclerView rv;
    private RunAdapter adapter;
    private List<RunBannerBean.ResultBean> resulttwo;
    private RunBean.ResultBean result;

    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.runfrgment, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //联网请求数据
        getDataFromeNet();



    }
    private void getDataFromeNet() {
        OkHttpUtils.get().url(Contants.RUN_URL).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(mContext, "" + e, Toast.LENGTH_SHORT).show();
                        bannerGetDataFromeNet();
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        //解析数据
                        analysisData(response);

                        //Banner 联网请求
                        bannerGetDataFromeNet();
                    }
                });
    }
    private void analysisData(String json) {
        RunBean runBean = JSON.parseObject(json, RunBean.class);
         result = runBean.getResult();

    }
    private void bannerGetDataFromeNet() {

        OkHttpUtils.get().url(Contants.BANNER_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(mContext, ""+e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
//                Toast.makeText(mContext,""+response,Toast.LENGTH_SHORT).show();
                //解析数据
                resolutionData(response);
                if (result != null) {
                    if(resulttwo!= null) {
                        //设置适配器
                        adapter = new RunAdapter(mContext, result,resulttwo);

                        rv.setAdapter(adapter);


                    }

                    //布局管理器
                    GridLayoutManager manager = new GridLayoutManager(mContext, 1);
                    rv.setLayoutManager(manager);
                }

            }
        });
    }

    private void resolutionData(String response) {
        RunBannerBean bannerBean = JSON.parseObject(response,RunBannerBean.class);
         resulttwo = bannerBean.getResult();

    }






    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
