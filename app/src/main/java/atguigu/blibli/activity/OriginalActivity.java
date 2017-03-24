package atguigu.blibli.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import atguigu.blibli.R;
import atguigu.blibli.adapter.OriginalAdapter;
import atguigu.blibli.fragment.AccordingFragment;
import atguigu.blibli.fragment.BaseFragment;
import atguigu.blibli.fragment.OriginalFragment;
import atguigu.blibli.fragment.StationFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class OriginalActivity extends AppCompatActivity {

    @InjectView(R.id.iv_select)
    ImageView ivSelect;
    @InjectView(R.id.tablayout)
    TabLayout tablayout;
    @InjectView(R.id.abl_original)
    AppBarLayout ablOriginal;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    @InjectView(R.id.activity_original)
    CoordinatorLayout activityOriginal;
    private List<BaseFragment> fragments ;
    private OriginalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_original);
        ButterKnife.inject(this);
        //准备数据
        initData();
        //设置适配器
         adapter = new  OriginalAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initData() {

        fragments = new ArrayList<>();
        fragments.add(new OriginalFragment());
        fragments.add(new StationFragment());
        fragments.add(new AccordingFragment());


    }

    @OnClick({R.id.iv_select, R.id.tablayout, R.id.abl_original, R.id.view_pager, R.id.activity_original})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_select:
                finish();
                break;
            case R.id.tablayout:
                break;
            case R.id.abl_original:
                break;
            case R.id.view_pager:
                break;
            case R.id.activity_original:
                break;
        }
    }
}
