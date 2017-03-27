package atguigu.blibli.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;

import java.util.ArrayList;
import java.util.List;

import atguigu.blibli.R;
import atguigu.blibli.activity.WebViewActivity;
import atguigu.blibli.bean.LiveBean;
import atguigu.blibli.bean.WebViewBean;
import atguigu.blibli.utils.MyGridView;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 陈江峰 on 2017/3/21.
 */

public class LiveAdapter extends RecyclerView.Adapter {
    private final LiveBean.DataBean datas;
    private final Context mContext;



    private LayoutInflater inflater;
    public static final String LINK = "LINK";
    public static final String VIEW = "VIEW";

    public static final int BANNER = 0;

    public static final int DRAWAER = 1;

    public static final int LIVE = 2;
    /**
     * 当前的缓存
     */
    public int currentType = BANNER;


    public LiveAdapter(Context mContext, LiveBean.DataBean data) {
        this.mContext = mContext;
        this.datas = data;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == DRAWAER) {
            currentType = DRAWAER;
        }
//        else if (position == LIVE) {
//            currentType = LIVE;
//        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, inflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == DRAWAER) {
            return new PartitionsViewHolder(mContext, inflater.inflate(R.layout.partitions_viewpager, null));
        }
//        else if (viewType == LIVE) {
//            return new LiveViewHolder(mContext, inflater.inflate(R.layout.live_viewpager, null));
//        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder viewHolder = (BannerViewHolder) holder;
            viewHolder.setData(datas.getBanner());
        } else if (getItemViewType(position) == DRAWAER) {
            PartitionsViewHolder viewHolder = (PartitionsViewHolder) holder;
            viewHolder.setData(datas.getPartitions().get(position-1));
        }
//        else if(getItemViewType(position)==LIVE) {
//            LiveViewHolder viewHolder = (LiveViewHolder) holder;
//            viewHolder.setData(datas.getPartitions());
//        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }


    private class BannerViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private Banner banner;
        private String link;
        private String title;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.context = mContext;
            banner = (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(final List<LiveBean.DataBean.BannerBean> beanbanner) {

            List<String> images = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                images.add(beanbanner.get(0).getImg());
            }
            banner.setImages(images)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
                            Glide.with(context)
                                    .load(path)
                                    .crossFade()
                                    .into(imageView);
                        }
                    })
                    .start();
            banner.setBannerAnimation(BackgroundToForegroundTransformer.class);
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {

                    for (int i = 0; i < beanbanner.size(); i++) {
                        link = beanbanner.get(i).getLink();

                        title = beanbanner.get(i).getTitle();

                    }

                    WebViewBean bean = new WebViewBean();
                    bean.setLink(link);
                    bean.setTitle(title);
                    Intent intent = new Intent(mContext, WebViewActivity.class);
                    intent.putExtra(LINK, bean);
                    mContext.startActivity(intent);
                }
            });
        }
    }


    class PartitionsViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        @InjectView(R.id.iv_imageview)
        ImageView ivImageview;
        @InjectView(R.id.painting)
        TextView painting;
        @InjectView(R.id.tv_title)
        TextView tvTitle;
        @InjectView(R.id.iv_right)
        ImageView ivRight;
        @InjectView(R.id.gv_view)
        MyGridView gvView;
        @InjectView(R.id.bt_more)
        Button btMore;
        @InjectView(R.id.tv_refresh)
        TextView tvRefresh;

        public PartitionsViewHolder(Context mContext, View inflate) {
            super(inflate);
            this.context = mContext;
            ButterKnife.inject(this, inflate);
        }

        public void setData(final LiveBean.DataBean.PartitionsBean partitionsBean) {
            String src = partitionsBean.getPartition().getSub_icon().getSrc();
            String name = partitionsBean.getPartition().getName();
            Glide.with(context).load(src)
                    .into(ivImageview);
            painting.setText(name);

            int online = partitionsBean.getPartition().getCount();

            tvTitle.setText(online+"");
            DrawaerAdapter adapter = new DrawaerAdapter(context, partitionsBean.getLives());
            gvView.setAdapter(adapter);
            //设置监听


//            gvView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//
////                    String playurl = partitions.get(getLayoutPosition()-1).getLives().get(position).getPlayurl();
//
//                    Intent intent = new Intent(mContext, DanmkuVideoActivity.class);
//
//
//                    String playurl = partitionsBean.getLives().get(position).getPlayurl();
//                     intent.putExtra(VIEW,playurl);
//
//                    mContext.startActivity(intent);
//
//                }
//            });
        }
    }

//    class LiveViewHolder extends RecyclerView.ViewHolder {
//        private final Context context;
//        @InjectView(R.id.iv_live)
//        ImageView ivLive;
//        @InjectView(R.id.tv_live)
//        TextView tvLive;
//        @InjectView(R.id.tv_right)
//        TextView tvRight;
//        @InjectView(R.id.gv_view)
//        MyGridView gvView;
//        @InjectView(R.id.tv_live_right)
//        TextView tvLiveRight;
//        @InjectView(R.id.iv_live_refresh)
//        ImageView ivLiveRefresh;
//        public LiveViewHolder(Context mContext, View inflate) {
//            super(inflate);
//            this.context =mContext;
//            ButterKnife.inject(this,inflate);
//        }
//
//        public void setData(List<LiveBean.DataBean.PartitionsBean> partitions) {
//            String src = partitions.get(1).getPartition().getSub_icon().getSrc();
//            Glide.with(context).load(src).into(ivLive);
//            LiveBean.DataBean.PartitionsBean bean = partitions.get(1);
//            int count = bean.getPartition().getCount();
//
//            Spanned text = Html.fromHtml("当前" + "<font color=#Fb7299><b>count</b></font>" + "个直播");
//            tvRight.setText(text);
//            int online = partitions.get(1).getLives().get(0).getOnline();
//            Spanned refresh = Html.fromHtml("<font color=#Fb7299><b>online</b></font>" + "条新动态,点击刷新");
//
//            tvLiveRight.setText(refresh);
//            List<LiveBean.DataBean.PartitionsBean.LivesBean> lives = partitions.get(1).getLives();
//            LifeAdapter adapter = new LifeAdapter(context,lives);
//            gvView.setAdapter(adapter);
//        }
//    }
}
