package atguigu.blibli.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import atguigu.blibli.R;
import atguigu.blibli.adapter.LiveAdapter;
import atguigu.blibli.bean.LiveBean;
import atguigu.blibli.utils.Contants;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 陈江峰 on 2017/3/21.
 */

public class LiveFragment extends BaseFragment {
    @InjectView(R.id.fragment_live)
    RecyclerView fragmentLive;

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
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get()
                .tag(this)
                .url(Contants.LIVE_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(mContext, "" + e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        //解析数据
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        if (!TextUtils.isEmpty(json)) {

            LiveBean bean = JSON.parseObject(json, LiveBean.class);
            LiveBean.DataBean data = bean.getData();
            if (data != null) {
                //设置适配器
                LiveAdapter adapter = new LiveAdapter(mContext, data);

                fragmentLive.setAdapter(adapter);
            }


            //布局管理器
//            GridLayoutManager manager = new GridLayoutManager(mContext, 1);
//            fragmentLive.setLayoutManager(manager);


            fragmentLive.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        OkHttpUtils.delete().tag(this);
    }
}
