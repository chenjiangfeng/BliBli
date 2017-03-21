package atguigu.blibli.fragment;

import android.view.View;
import android.widget.TextView;

import atguigu.blibli.R;

/**
 * Created by 陈江峰 on 2017/3/21.
 */

public class FindFragment extends  BaseFragment {
    private TextView textView;

    @Override
    public View initView() {
       View view = View.inflate(mContext, R.layout.fragment_find,null);

        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("FindFragment");
    }
}
