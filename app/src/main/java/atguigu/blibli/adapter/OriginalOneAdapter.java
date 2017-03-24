package atguigu.blibli.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.blibli.R;
import atguigu.blibli.bean.OriginalBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 陈江峰 on 2017/3/22.
 */

public class OriginalOneAdapter extends BaseAdapter {
    private final List<OriginalBean.DataBean> datas;
    private final Context mContext;

    public OriginalOneAdapter(Context mContext, List<OriginalBean.DataBean> data) {
        this.mContext = mContext;
        this.datas = data;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.original_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置数据
        OriginalBean.DataBean dataBean = datas.get(position);
        if (position < 3) {
            viewHolder.tvItem.setTextColor(Color.parseColor("#Fb7299"));
            viewHolder.tvItem.setText("" + (position + 1));


        } else {
            viewHolder.tvItem.setText("" + (position + 1));
            viewHolder.tvItem.setTextColor(Color.parseColor("#99000000"));
        }

        Glide.with(mContext)
                .load(dataBean.getCover())
                .into(viewHolder.ivOriginalItem);

        viewHolder.tvDescribe.setText(dataBean.getTitle());
        viewHolder.tvLike.setText("up: " + dataBean.getName());
        viewHolder.evaluate.setText("综合评分:  " + dataBean.getPts());
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_item)
        TextView tvItem;
        @InjectView(R.id.iv_original_item)
        ImageView ivOriginalItem;
        @InjectView(R.id.tv_describe)
        TextView tvDescribe;
        @InjectView(R.id.tv_like)
        TextView tvLike;
        @InjectView(R.id.evaluate)
        TextView evaluate;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
