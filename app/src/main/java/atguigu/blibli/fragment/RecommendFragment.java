package atguigu.blibli.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 陈江峰 on 2017/3/21.
 */

public class RecommendFragment extends  BaseFragment {
    private TextView textView;

    @Override
    public View initView() {
        textView = new TextView( mContext);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("RecommendFragment");
    }
}
