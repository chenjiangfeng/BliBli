package atguigu.blibli.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.blibli.R;
import atguigu.blibli.bean.LiveBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 陈江峰 on 2017/3/22.
 */

public class DrawaerAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<LiveBean.DataBean.PartitionsBean.LivesBean> datas;
//    private final LiveBean.DataBean.PartitionsBean datas;

//    public DrawaerAdapter(Context context, LiveBean.DataBean.PartitionsBean partitionsBean) {
//        this.mContext = context;
//        this.datas = partitionsBean;
//    }

    public DrawaerAdapter(Context context, List<LiveBean.DataBean.PartitionsBean.LivesBean> lives) {
        this.mContext = context;
this.datas = lives;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.drawer_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        LiveBean.DataBean.PartitionsBean.LivesBean owner = datas.get(position);

        Glide.with(mContext).load(owner.getCover().getSrc()).into(viewHolder.ivCart);

        viewHolder.tvlDescribe.setText(owner.getTitle());
        viewHolder.tvName.setText(owner.getOwner().getName());
        viewHolder.tvNor.setText(owner.getOnline()+"万");

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_cart)
        ImageView ivCart;
        @InjectView(R.id.tvl_describe)
        TextView tvlDescribe;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_nor)
        TextView tvNor;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
