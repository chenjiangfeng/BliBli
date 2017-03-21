package atguigu.blibli;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import atguigu.blibli.adapter.MainAdapter;
import atguigu.blibli.fragment.BaseFragment;
import atguigu.blibli.fragment.FindFragment;
import atguigu.blibli.fragment.LiveFragment;
import atguigu.blibli.fragment.PartitionFragment;
import atguigu.blibli.fragment.RecommendFragment;
import atguigu.blibli.fragment.RunFragment;

import static atguigu.blibli.R.id.tablayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private List<BaseFragment> fragments;
    private MainAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drablelayout);
        tabLayout = (TabLayout) findViewById(tablayout);
         viewPager = (ViewPager) findViewById(R.id.view_pager);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        initData();//初始化fragment

        //设置适配器

        adapter = new MainAdapter(getSupportFragmentManager(),fragments);
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
}
