package atguigu.blibli.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import java.util.ArrayList;
import java.util.List;

import atguigu.blibli.R;
import atguigu.blibli.adapter.MainAdapter;
import atguigu.blibli.download.DownLoadMainActivity;
import atguigu.blibli.fragment.BaseFragment;
import atguigu.blibli.fragment.FindFragment;
import atguigu.blibli.fragment.PartitionFragment;
import atguigu.blibli.fragment.RecommendFragment;
import atguigu.blibli.fragment.RunFragment;
import atguigu.blibli.liveFragmentmvp.view.LiveFragment;
import atguigu.blibli.utils.Contants;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static atguigu.blibli.R.id.tablayout;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout dlMain;
    private TabLayout tabLayout;
    private List<BaseFragment> fragments;
    private MainAdapter adapter;
    private ViewPager viewPager;
    private NavigationView navigationView;
    private ImageView iv;
    private ImageView iv_search;
    private ImageView iv_download;
    //GdL3nGFMpEtVslr5vqB3Vt2qnLcgr2MZ

    public static final String URL = "URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.inject(this);
//        //透明状态栏  沉浸式状态栏加入的代码
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        iv_search = (ImageView) findViewById(R.id.iv_search);
        iv = (ImageView) findViewById(R.id.iv_select);
        dlMain = (DrawerLayout) findViewById(R.id.dl_main);
        tabLayout = (TabLayout) findViewById(tablayout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        navigationView = (NavigationView) findViewById(R.id.navigation_heade);
        ImageView viewById = (ImageView) findViewById(R.id.iv_download);

        View headerView = navigationView.getHeaderView(0);

        ImageView imageView = (ImageView) headerView.findViewById(R.id.iv_tatle);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //头部点击事件
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlMain.closeDrawer(GravityCompat.START);

            }
        });
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlMain.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                dlMain.closeDrawer(GravityCompat.START);
                return true;
            }
        });

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      /*  fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        initData();//初始化fragment

        //设置适配器

        adapter = new MainAdapter(getSupportFragmentManager(), fragments);
        //让fragment有缓存
        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new LiveFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new RunFragment());
        fragments.add(new PartitionFragment());
        fragments.add(new FindFragment());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.iv_download, R.id.iv_search,R.id.iv_game})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_download:

                Intent intent = new Intent(this, DownLoadMainActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_search:
                SearchFragment searchFragment = SearchFragment.newInstance();
                searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
                    @Override
                    public void OnSearchClick(String keyword) {

                        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                        intent.putExtra("URL", Contants.SEACHER_TOP_URL + keyword + Contants.SEACHER_BUTTON_URL);
                        intent.putExtra("KEYWORD", keyword);


                        startActivity(intent);

                    }
                });
                searchFragment.show(getSupportFragmentManager(), SearchFragment.TAG);
                break;
            case R.id.iv_game:

                Intent intentone = new Intent(MainActivity.this,BluetoothActivity.class);
                startActivity(intentone);
                Toast.makeText(MainActivity.this, "youxia", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
