package atguigu.blibli.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import atguigu.blibli.R;
import atguigu.blibli.adapter.LiveAdapter;
import atguigu.blibli.adapter.TalkMianActivity;
import atguigu.blibli.bean.CenterBean;
import atguigu.blibli.bean.WebViewBean;
import atguigu.blibli.utils.Contants;
import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class TalkActivity extends AppCompatActivity {


    @InjectView(R.id.list_view)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_main);
        ButterKnife.inject(this);

        //联网请求

           getDataFromeNet();
    }

    private void getDataFromeNet() {
        OkHttpUtils.get().url(Contants.FING_TOALK_URL)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(TalkActivity.this, "" + e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                //解析数据
                Analytical(response);
            }
        });

    }

    private void Analytical(String json) {
        CenterBean bean = JSON.parseObject(json, CenterBean.class);
        final List<CenterBean.ListBean> list = bean.getList();
        if(list!=null&&list.size()>0) {
            //设置适配器
            TalkMianActivity adapter = new TalkMianActivity(TalkActivity.this, list);
            listView.setAdapter(adapter);
        }

        //listView的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WebViewBean webBean = new WebViewBean();
                webBean.setTitle(list.get(position).getTitle());
                webBean.setLink(list.get(position).getLink());
                Intent intent = new Intent(TalkActivity.this,WebViewActivity.class);
                intent.putExtra(LiveAdapter.LINK,webBean);
                startActivity(intent);
            }
        });
    }


}
