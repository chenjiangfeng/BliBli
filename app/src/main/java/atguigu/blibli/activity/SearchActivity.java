package atguigu.blibli.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import atguigu.blibli.R;
import atguigu.blibli.adapter.SeacherAdpter;
import atguigu.blibli.bean.SeacherBean;
import atguigu.blibli.fragment.BaseFragment;
import atguigu.blibli.fragment.ComprehensiveAdapter;
import atguigu.blibli.fragment.DramaFragment;
import atguigu.blibli.fragment.MasterFragment;
import atguigu.blibli.fragment.MoviesFragment;
import atguigu.blibli.utils.GetDataFromNet;
import atguigu.blibli.utils.LoadNetHttp;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {


    @InjectView(R.id.iv_search_top)
    ImageView ivSearchTop;
    @InjectView(R.id.et_search_top)
    EditText etSearchTop;
    @InjectView(R.id.iv_serch_top)
    ImageView ivSerchTop;
    @InjectView(R.id.tl_top)
    TabLayout tlTop;
    @InjectView(R.id.tv_sort)
    TextView tvSort;
    @InjectView(R.id.tv_long)
    TextView tvLong;
    @InjectView(R.id.tv_partition)
    TextView tvPartition;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    @InjectView(R.id.activity_search)
    CoordinatorLayout activitySearch;
    private String url;
    private String title;
    private List<BaseFragment> lists;
    private SeacherAdpter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.inject(this);

        url = getIntent().getStringExtra(MainActivity.URL);

        title = getIntent().getStringExtra("KEYWORD");
        Toast.makeText(SearchActivity.this, ""+title, Toast.LENGTH_SHORT).show();

        etSearchTop.setText(title);

        getDataFromeNet();

    }

    private void initData(SeacherBean.DataBean.ItemsBean items) {
        lists = new ArrayList<>();
        lists.add(new ComprehensiveAdapter(items));
        lists.add(new DramaFragment(items));
        lists.add(new MasterFragment(items));
        lists.add(new MoviesFragment(items));
    }

    private void getDataFromeNet() {
        GetDataFromNet.getDataFromNet(url, new LoadNetHttp() {
            @Override
            public void success(String context) {
                //解析数据
                ProcessData(context);
            }

            @Override
            public void failure(String error) {
                Toast.makeText(SearchActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ProcessData(String context) {


        SeacherBean bean = JSON.parseObject(context, SeacherBean.class);
        List<SeacherBean.DataBean.NavBean> nav = bean.getData().getNav();
        SeacherBean.DataBean.ItemsBean items = bean.getData().getItems();
        initData(items);
        List<String>  bing = new ArrayList<>();
        bing.add("综合");
        for (int i=0;i<nav.size();i++){
            if(nav.get(i).getTotal()>0&&nav.get(i).getTotal()<100) {
                bing.add(nav.get(i).getName()+"("+nav.get(i).getTotal()+")");

            }else if(nav.get(i).getTotal()>90) {
                bing.add(nav.get(i).getName()+"(99+"+")");
            }else {
                bing.add(nav.get(i).getName());
            }
//            String name = nav.get(i).getName();
//            bing.add(name);
        }
        adpter = new SeacherAdpter(getSupportFragmentManager(), lists,bing);
        viewPager.setAdapter(adpter);
        tlTop.setupWithViewPager(viewPager);
        tlTop.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

    @OnClick({R.id.iv_search_top, R.id.et_search_top, R.id.iv_serch_top, R.id.tl_top, R.id.tv_sort, R.id.tv_long, R.id.tv_partition, R.id.view_pager, R.id.activity_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search_top:
                finish();
                break;
            case R.id.et_search_top:
                break;
            case R.id.iv_serch_top:
                Toast.makeText(SearchActivity.this, "二维码还没有做哦", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tl_top:
                break;
            case R.id.tv_sort:
                break;
            case R.id.tv_long:
                break;
            case R.id.tv_partition:
                break;
            case R.id.view_pager:
                break;
            case R.id.activity_search:
                break;
        }
    }
}
