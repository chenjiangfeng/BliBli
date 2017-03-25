package atguigu.blibli.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import atguigu.blibli.fragment.BaseFragment;

/**
 * Created by 陈江峰 on 2017/3/24.
 */

public class RanKingAdapter extends FragmentPagerAdapter {
    private final String[] st;
    private final List<BaseFragment> datas;

    public RanKingAdapter(FragmentManager fm, List<BaseFragment> lists, String[] st) {
        super(fm);
        this.datas = lists;
        this.st = st;
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
