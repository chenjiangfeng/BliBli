package atguigu.blibli.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

/**
 * Created by 陈江峰 on 2017/3/21.
 */

public class LiveAdapter extends RecyclerView.Adapter {
    private final LiveBean.DataBean datas;
    private final Context mContext;

    private LayoutInflater inflater;
    public static final String LINK ="LINK";

    public static final int BANNER = 0;


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
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, inflater.inflate(R.layout.banner_viewpager, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder viewHolder = (BannerViewHolder) holder;
            viewHolder.setData(datas.getBanner());
        }
    }

    @Override
    public int getItemCount() {
        return 2;
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

                    for (int i = 0;i<beanbanner.size();i++){
                        link = beanbanner.get(i).getLink();
                         title = beanbanner.get(i).getTitle();

                    }
                    WebViewBean bean = new WebViewBean();
                    bean.setLink(link);
                    bean.setTitle(title);
                    Intent intent = new Intent(mContext,WebViewActivity.class);
                    intent.putExtra(LINK,bean);
                    mContext.startActivity(intent);
                }
            });
        }
    }

}
