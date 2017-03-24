package atguigu.blibli.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import atguigu.blibli.fragment.BaseFragment;

/**
 * Created by 陈江峰 on 2017/3/22.
 */

public class OriginalAdapter extends FragmentPagerAdapter {
    String st [] = {"原创","全站","番剧"};
    private final List<BaseFragment> datas;

    public OriginalAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
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
