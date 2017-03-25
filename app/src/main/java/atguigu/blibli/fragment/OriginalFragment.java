package atguigu.blibli.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.blibli.R;
import atguigu.blibli.adapter.OriginalOneAdapter;
import atguigu.blibli.bean.OriginalBean;
import atguigu.blibli.utils.Contants;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 陈江峰 on 2017/3/22.
 */

public class OriginalFragment extends BaseFragment {
    @InjectView(R.id.listView)
    ListView listView;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_original, null);
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

        OkHttpUtils.get()
                .tag(this).url(Contants.ORIGINAL_URL)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(mContext, "失败"+e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                //解析数据
                analyticalData(response);
            }
        });
    }

    private void analyticalData(String json) {
        OriginalBean bean = JSON.parseObject(json, OriginalBean.class);
        List<OriginalBean.DataBean> data = bean.getData();
        if(data!=null&&data.size()>0)    {
            //设置适配器
            OriginalOneAdapter adapter = new OriginalOneAdapter(mContext,data);
            listView.setAdapter(adapter);
        }

    }

    @Override
    public void onDestroyView() {
        OkHttpUtils.delete().tag(this);
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
