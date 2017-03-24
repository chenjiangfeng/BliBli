package atguigu.blibli.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 陈江峰 on 2017/3/22.
 */

public class StationFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
//        View view = View.inflate(mContext, R.layout.fragment_original,null);
        textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("全站");
    }
}
