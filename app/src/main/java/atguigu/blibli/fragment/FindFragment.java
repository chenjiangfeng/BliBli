package atguigu.blibli.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import atguigu.blibli.R;
import atguigu.blibli.activity.OriginalActivity;
import atguigu.blibli.activity.RegionActivity;
import atguigu.blibli.activity.ShoppingActivity;
import atguigu.blibli.activity.TalkActivity;
import atguigu.blibli.bean.WaterBean;
import atguigu.blibli.utils.Contants;
import atguigu.blibli.utils.DensityUtil;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by 陈江峰 on 2017/3/21.
 */

public class FindFragment extends BaseFragment {


    @InjectView(R.id.tv_search)
    TextView tvSearch;
    @InjectView(R.id.iv_scan)
    ImageView ivScan;
    @InjectView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    @InjectView(R.id.tv_load_more)
    TextView tvLoadMore;
    @InjectView(R.id.textView1)
    TextView textView1;
    @InjectView(R.id.textView2)
    TextView textView2;
    @InjectView(R.id.tv_group)
    TextView tvGroup;
    @InjectView(R.id.tv_topic)
    TextView tvTopic;
    @InjectView(R.id.tv_activity)
    TextView tvActivity;
    @InjectView(R.id.min)
    TextView min;
    @InjectView(R.id.tv_ranking)
    TextView tvRanking;
    @InjectView(R.id.whole)
    TextView whole;
    @InjectView(R.id.game)
    TextView game;
    @InjectView(R.id.around)
    TextView around;
    @InjectView(R.id.sv_view)
    NestedScrollView svView;
    private int[] colors = {
            Color.parseColor("#f0a420"), Color.parseColor("#4ba5e2"), Color.parseColor("#f0839a"),
            Color.parseColor("#4ba5e2"), Color.parseColor("#f0839a"), Color.parseColor("#f0a420"),
            Color.parseColor("#f0839a"), Color.parseColor("#f0a420"), Color.parseColor("#4ba5e2")
    };
    private Intent intent;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_find, null);
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();

    }

    private void getDataFromNet() {
        OkHttpUtils.get()
                .tag(this)
                .url(Contants.FIND_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(mContext, "" + e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                //解析数据
                analysisData(response);
            }
        });

    }

    private void analysisData(String json) {
        WaterBean bean = JSON.parseObject(json, WaterBean.class);
        final List<WaterBean.DataBean.ListBean> list = bean.getData().getList();


        if (list != null && list.size() > 0) {
            idFlowlayout.setAdapter(new TagAdapter(list) {
                @Override
                public View getView(FlowLayout parent, int position, Object o) {
                    TextView tv = (TextView) View.inflate(mContext, R.layout.tv,
                            null);
                    tv.setText(list.get(position).getKeyword());
                    tv.setTextColor(colors[position % colors.length]);
                    return tv;


                }
            });
           idFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
               @Override
               public boolean onTagClick(View view, int position, FlowLayout parent) {
                   return true;
               }
           });

        }
    }


    @Override
    public void onDestroyView() {

        super.onDestroyView();
        ButterKnife.reset(this);
        OkHttpUtils.delete().tag(this);
    }

    private boolean isOpen = false;

    @OnClick({R.id.tv_search, R.id.iv_scan, R.id.id_flowlayout, R.id.tv_load_more, R.id.textView1, R.id.textView2, R.id.tv_group, R.id.tv_topic, R.id.tv_activity, R.id.min, R.id.tv_ranking, R.id.whole, R.id.game, R.id.around})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                SearchFragment searchFragment = SearchFragment.newInstance();
                searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
                    @Override
                    public void OnSearchClick(String keyword) {

//
//                    Intent intent = new Intent(mContext,SearchActivity.class);
//                                mContext.startActivity(intent);
                        Toast.makeText(mContext, keyword, Toast.LENGTH_SHORT).show();
                    }
                });
                searchFragment.show(getFragmentManager(),SearchFragment.TAG);

                break;
            case R.id.iv_scan:
                break;
            case R.id.id_flowlayout:
                break;
            case R.id.tv_load_more:

                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) svView.getLayoutParams();
                if (!isOpen) {
                    layoutParams.height = DensityUtil.dip2px(getActivity(), 300);
                    svView.setLayoutParams(layoutParams);
                    tvLoadMore.setText("收起");
                    Drawable img = getResources().getDrawable(R.drawable.ic_arrow_up);
                        // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
                    img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
                    tvLoadMore.setCompoundDrawables(img, null, null, null); //设置左图标
                    isOpen = true;
                } else {
                    layoutParams.height = DensityUtil.dip2px(getActivity(), 180);
                    svView.setLayoutParams(layoutParams);
                    tvLoadMore.setText("查看更多");
                    Drawable img = getResources().getDrawable(R.drawable.ic_arrow_down);
                    // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
                    img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
                    tvLoadMore.setCompoundDrawables(img, null, null, null); //设置左图标
                    isOpen = false;
                }
                break;
            case R.id.textView1:
                Toast.makeText(mContext, "55555555555", Toast.LENGTH_SHORT).show();
                break;
            case R.id.textView2:
                Toast.makeText(mContext, "55555555555", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_group:
                Toast.makeText(mContext, "55555555555", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_topic:
                 intent = new Intent(mContext, TalkActivity.class);
                mContext.startActivity(intent);

                break;
            case R.id.tv_activity:
                Toast.makeText(mContext, "55555555555", Toast.LENGTH_SHORT).show();
                break;
            case R.id.min:
                break;
            case R.id.tv_ranking:
                 intent = new Intent(mContext,OriginalActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.whole:
                intent = new Intent(mContext,RegionActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.game:
                break;
            case R.id.around:
                intent = new Intent(mContext,ShoppingActivity.class);
                mContext.startActivity(intent);
                break;
        }
    }


}
