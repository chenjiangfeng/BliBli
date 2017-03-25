package atguigu.blibli.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by 陈江峰 on 2017/3/15.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayoutid());
        ButterKnife.inject(this);


        initTitle();
        initListener();
        initData();
    }

    public abstract void initData();

    protected abstract void initListener();

    protected abstract void initTitle();


    public abstract int getlayoutid();

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
