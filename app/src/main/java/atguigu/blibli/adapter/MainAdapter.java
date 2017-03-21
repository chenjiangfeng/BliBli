package atguigu.blibli.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import atguigu.blibli.fragment.BaseFragment;

/**
 * Created by 陈江峰 on 2017/3/21.
 */

public class MainAdapter extends FragmentPagerAdapter {


    private final List<BaseFragment> datas;
    private String [] st = {"直播","推荐","追番","分区","发现"};



    public MainAdapter(FragmentManager fragmentManager, List<BaseFragment> fragments) {
        super(fragmentManager);
        this.datas = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return st[position];
    }
}
