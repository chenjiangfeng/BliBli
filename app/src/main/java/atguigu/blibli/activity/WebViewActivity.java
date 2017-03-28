package atguigu.blibli.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import atguigu.blibli.R;
import atguigu.blibli.adapter.LiveAdapter;
import atguigu.blibli.bean.WebViewBean;
import atguigu.blibli.utils.ClipboardUtil;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class WebViewActivity extends AppCompatActivity {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @InjectView(R.id.webview)
    WebView webview;
    @InjectView(R.id.activity_web_view)
    LinearLayout activityWebView;
    /* @InjectView(R.id.toolbar)
        Toolbar toolbar;
        @InjectView(R.id.app_bar_layout)
        AppBarLayout appBarLayout;
        @InjectView(R.id.webview)
        WebView webview;
        @InjectView(R.id.progressbar)
        ProgressBar progressbar;
        @InjectView(R.id.fl_dixia)
        FrameLayout flDixia;
        @InjectView(R.id.activity_web_view)
        LinearLayout activityWebView;*/
    private WebViewBean webViewBean;
    private String link;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.inject(this);
        getData();
    }

    private void getData() {

        webViewBean = (WebViewBean) getIntent().getSerializableExtra(LiveAdapter.LINK);
        title = webViewBean.getTitle();
        link = webViewBean.getLink();


        setSupportActionBar(toolbar);

        //设置totel的返回箭头
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            // ActionBar左上角添加返回图标
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        //设置标题
        toolbar.setTitle(TextUtils.isEmpty(title) ? "详情" : title);


//        tvTitle.setText(title);
        WebSettings setting = webview.getSettings();
        //设置java调用js
        setting.setJavaScriptEnabled(true);
        //设置缩放按钮
        setting.setBuiltInZoomControls(true);
        //设置单击双击
        setting.setUseWideViewPort(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                progressbar.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }
        });
//        webview.addJavascriptInterface(new MyJavascriptInterface(), "cyc");
        webview.loadUrl(link);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web_menu, menu);
        return true;
        //
    }

    //toobar点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed(); //返回
                break;

            case R.id.share: //分享
                share();
                break;

            case R.id.web: //浏览器打开
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(link));
                startActivity(intent);
                break;

            case R.id.copy: //复制链接
                ClipboardUtil.setText(WebViewActivity.this, link);
                Toast.makeText(WebViewActivity.this, "已复制", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "来自「哔哩哔哩」的分享:" + link);
        startActivity(Intent.createChooser(intent, title));

    }

    @Override
    public void onBackPressed() {

        if (webview.canGoBack() && webview.copyBackForwardList().getSize() > 0
                && !webview.getUrl().equals(webview.copyBackForwardList()
                .getItemAtIndex(0).getOriginalUrl())) {
            webview.goBack();
        } else {
            finish();
        }
    }
}
