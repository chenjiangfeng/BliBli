package atguigu.blibli.download;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import atguigu.blibli.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class DownLoadMainActivity extends AppCompatActivity {

    @InjectView(R.id.bt_download)
    Button btDownload;
    @InjectView(R.id.bt_download_list)
    Button btDownloadList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load_main);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.bt_download, R.id.bt_download_list})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_download:
                startActivity(new Intent(this,DownloadActivity.class));
                break;
            case R.id.bt_download_list:
                startActivity(new Intent(this,DownloadListActivity.class));

                break;
        }
    }
}
