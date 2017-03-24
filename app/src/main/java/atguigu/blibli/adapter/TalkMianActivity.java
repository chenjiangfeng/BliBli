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
import atguigu.blibli.bean.CenterBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 陈江峰 on 2017/3/22.
 */

public class TalkMianActivity extends BaseAdapter {
    private final Context mContext;
    private final List<CenterBean.ListBean> datas;

    public TalkMianActivity(Context mContext, List<CenterBean.ListBean> list) {
        this.mContext = mContext;
        this.datas = list;
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
        ViewHolder viewHolder =null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.activity_talk, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //设置数据
        CenterBean.ListBean Bean = datas.get(position);
        Glide.with(mContext).load(Bean.getCover())
                .placeholder(R.drawable.bili_default_image_tv).into(viewHolder.ivIcon);
        viewHolder.tvTitle.setText(Bean.getTitle());

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_icon)
        ImageView ivIcon;
        @InjectView(R.id.tv_title)
        TextView tvTitle;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
