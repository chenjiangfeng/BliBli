package atguigu.blibli.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.anye.greendao.gen.UserDao;
import com.bumptech.glide.Glide;

import java.util.List;

import atguigu.blibli.R;
import atguigu.blibli.user.User;
import atguigu.blibli.view.AddSubView;
import atguigu.blibli.view.MyApplication;
import butterknife.ButterKnife;
import butterknife.InjectView;

import static atguigu.blibli.R.id.cb_gov;

/**
 * Created by 陈江峰 on 2017/3/28.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyShoppingViewHorlder> {
    private final Context mContext;
    private final List<User> datas;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;
    private final CheckBox checkboxDeleteAll;
    private UserDao userDao;


    public ShoppingCartAdapter(Context mContext, List<User> users, CheckBox checkboxAll, CheckBox checkboxDeleteAll, TextView tvShopcartTotal) {
        this.mContext = mContext;
        this.datas = users;
        this.checkboxAll = checkboxAll;
        this.checkboxDeleteAll = checkboxDeleteAll;
        this.tvShopcartTotal = tvShopcartTotal;//价钱
        userDao = MyApplication.getInstance().getDaoSession().getUserDao();
        showTotalPrice();
    }

    @Override
    public MyShoppingViewHorlder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyShoppingViewHorlder(View.inflate(mContext, R.layout.item_shop_cart, null));
    }

    @Override
    public void onBindViewHolder(final MyShoppingViewHorlder holder, final int position) {
        final User user = datas.get(position);
        String name = user.getName();
        String url = user.getUrl();

        Boolean check = user.getCheck();

        if (check) {

         //   holder.cbGov.setChecked(true);

        }
        List<User> users = userDao.loadAll();
        for (int i = 0;i < users.size();i++) {
            if (user.getId() == users.get(i).getId()) {
                holder.cbGov.setChecked(users.get(i).getCheck());
            }
        }

        String shangpingming = user.getShangpingming();
        holder.tvPriceGov.setText(name + "");//价钱

        holder.addSubView.setValue(Integer.parseInt(user.getPassword()));//个数
        //图片
        Glide.with(mContext).load(url).into(holder.ivGov);

        holder.tvDescGov.setText(shangpingming);

        holder.addSubView.setMinValue(1);


        holder.addSubView.setOnNumberChangerListener(new AddSubView.OnNumberChangerListener() {
            @Override
            public void onNumberChanger(int value) {

                // User one = new User(null, value+"", user.getName()+"");
                //userDao.insert(one);
                showTotalPrice();
            }
        });


        holder.cbGov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClickListener(v, position);
                }
            }
        });
        holder.addSubView.setOnNumberChangerListener(new AddSubView.OnNumberChangerListener() {
            @Override
            public void onNumberChanger(int value) {
                //得到数据
                User na = datas.get(position);
                na.setPassword(value+"");

                //跟新数据库
                userDao.update(na);

              //刷新
                notifyItemChanged(position);

                //价格改变
                showTotalPrice();

            }
        });
    }

    private void showTotalPrice() {

        //显示总价格
        tvShopcartTotal.setText("合计:" + getTotalPrice());

    }

    private double getTotalPrice() {
        double zongjia = 0;
        if (datas != null && datas.size() > 0) {

            for (int i = 0; i < datas.size(); i++) {
                User user = datas.get(i);
                String name = user.getName();
                int yy = Integer.parseInt(name);
                String password = user.getPassword();//个数
                int xx = Integer.parseInt(password);
                Boolean check = user.getCheck();

                if(check) {
                    zongjia += xx* yy;
                }
            }

        }
        return zongjia;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 检验是否全选
     */
    public void checkAll() {

        int number = 0;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                User user = datas.get(i);
                if (!user.getCheck()) {
                    checkboxAll.setChecked(false);
                } else {
                    number++;
                    showTotalPrice();

                }
            }
            if (datas.size() == number) {
                checkboxAll.setChecked(true);
            }
//            else {
//                checkboxAll.setChecked(false);
//            }
        } else {//没有数据

            checkboxAll.setChecked(false);
        }

    }

    //全选跟反全选
    public void check_All(Boolean b) {

        List<User> users = userDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            users.get(i).setCheck(b);
            userDao.update(users.get(i));
        }
//        if(datas!=null&&datas.size()>0) {
//            for (int i = 0; i < datas.size(); i++) {
//                User user = datas.get(i);
//                user.setCheck(b);
//                userDao.update(user);
//               // notifyDataSetChanged();
//            }
//        }

    }

    class MyShoppingViewHorlder extends RecyclerView.ViewHolder {
        @InjectView(cb_gov)
        CheckBox cbGov;
        @InjectView(R.id.iv_gov)
        ImageView ivGov;
        @InjectView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @InjectView(R.id.tv_price_gov)
        TextView tvPriceGov;
        @InjectView(R.id.addSubView)
        AddSubView addSubView;

        public MyShoppingViewHorlder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

        }
    }

    //回调点击事件的监听
    private OnItemClickListener itemClickListener;

    /**
     * 点击item的监听
     */
    public interface OnItemClickListener {
        public void onItemClickListener(View view, int position);
    }

    /**
     * 设置item的监听
     *
     * @param l
     */
    public void setOnItemClickListener(OnItemClickListener l) {
        this.itemClickListener = l;
    }
}
