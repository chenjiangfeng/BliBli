package atguigu.blibli.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.blibli.R;
import atguigu.blibli.bean.FindBean;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 陈江峰 on 2017/3/25.
 */

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.MyViewHolder> {
    private final List<FindBean.DataBean> datas;
    private final Context mContext;

    public FindAdapter(Context mContext, List<FindBean.DataBean> data) {
        this.mContext = mContext;
        this.datas = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(mContext, View.inflate(mContext, R.layout.find_item, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (position < 3) {
            holder.tvItem.setText((position + 1) + "");
            holder.tvItem.setTextColor(Color.parseColor("#fb7299"));
            holder.tvItem.setTextSize(25);
        } else {
            holder.tvItem.setText((position + 1) + "");
            holder.tvItem.setTextColor(Color.parseColor("#000000"));
            holder.tvItem.setTextSize(20);
        }
//        holder.tvItem.setText(position+"");

        Glide.with(mContext).load(datas.get(position).getCover())
                .into(holder.ivOriginalItem);
        holder.tvDescribe.setText(datas.get(position).getTitle());
        holder.tvLike.setText("up :" + datas.get(position).getName() + "");
        holder.evaluate.setText("综合评分 :" + datas.get(position).getPlay() + "");
      holder.lvRank.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Toast.makeText(mContext, ""+position, Toast.LENGTH_SHORT).show();
          }
      });
        holder.itemLiveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "你好"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_item)
        TextView tvItem;
        @InjectView(R.id.iv_original_item)
        ImageView ivOriginalItem;
        @InjectView(R.id.item_live_layout)
        CardView itemLiveLayout;
        @InjectView(R.id.tv_describe)
        TextView tvDescribe;
        @InjectView(R.id.tv_like)
        TextView tvLike;
        @InjectView(R.id.evaluate)
        TextView evaluate;
        @InjectView(R.id.lv_rank)
        LinearLayout lvRank;


        public MyViewHolder(final Context mContext, View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

        }


    }
}
