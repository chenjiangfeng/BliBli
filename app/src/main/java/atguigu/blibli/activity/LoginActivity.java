package atguigu.blibli.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.anye.greendao.gen.UserLoginDao;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import atguigu.blibli.R;
import atguigu.blibli.user.UserLogin;
import atguigu.blibli.view.MyApplication;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @InjectView(R.id.iv_left_login)
    ImageView ivLeftLogin;
    @InjectView(R.id.iv_center_login)
    ImageView ivCenterLogin;
    @InjectView(R.id.iv_left_user)
    ImageView ivLeftUser;
    @InjectView(R.id.et_phone)
    EditText etPhone;
    @InjectView(R.id.iv_lock)
    ImageView ivLock;
    @InjectView(R.id.et_passworld)
    EditText etPassworld;
    @InjectView(R.id.bt_register)
    Button btRegister;
    @InjectView(R.id.bt_login)
    Button btLogin;
    @InjectView(R.id.activity_login)
    LinearLayout activityLogin;
    @InjectView(R.id.iv_right_login)
    ImageView ivRightLogin;
    @InjectView(R.id.iv_jiantou)
    ImageView ivJiantou;
    @InjectView(R.id.forget)
    TextView forget;

    private UserLoginDao userDao;
    private EditText zhang;
    private EditText mi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.inject(this);
        etPhone.setSelection(etPhone.length());

        initListener();
        userDao = MyApplication.getInstance().getDaoSession().getUserLoginDao();
    }

    private void initListener() {
        etPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ivLeftLogin.setImageResource(R.drawable.ic_22);
                    ivRightLogin.setImageResource(R.drawable.ic_33);
                    new Timer().schedule(new TimerTask()

                                         {

                                             public void run()

                                             {

                                                 InputMethodManager inputManager =

                                                         (InputMethodManager) etPhone.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                                                 inputManager.showSoftInput(etPhone, 0);

                                             }

                                         },

                            200);
                } else {
                    ivLeftLogin.setImageResource(R.drawable.ic_22_hide);
                    ivRightLogin.setImageResource(R.drawable.ic_100);

                }
            }
        });
    }


    @OnClick({R.id.iv_jiantou, R.id.forget, R.id.iv_left_login, R.id.iv_center_login, R.id.iv_right_login, R.id.iv_left_user, R.id.et_phone, R.id.iv_lock, R.id.et_passworld, R.id.bt_register, R.id.bt_login, R.id.activity_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_jiantou:
                finish();
                break;
            case R.id.forget:
                LayoutInflater inflater = LayoutInflater.from(LoginActivity.this);//提示
                View viewdialog = inflater.inflate(R.layout.dialog_item, null);
               zhang = (EditText) viewdialog.findViewById(R.id.et_zhanghao);
                 mi = (EditText) viewdialog.findViewById(R.id.et_mima);
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("修改密码")
                        .setMessage("新密码").setIcon(
                        android.R.drawable.ic_dialog_info)

                        .setView(viewdialog)

                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String trim = zhang.getText().toString().trim();
                                String mima = mi.getText().toString().trim();
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;
            case R.id.iv_left_login:
                break;
            case R.id.iv_center_login:
                break;
            case R.id.iv_right_login:
                break;
            case R.id.iv_left_user:
                break;
            case R.id.et_phone:
                break;
            case R.id.iv_lock:
                break;
            case R.id.et_passworld:
                break;
            case R.id.bt_register:

                Boolean b = true;
                String zhanghaoOne = etPhone.getText().toString().trim();
                String mimaOne = etPassworld.getText().toString().trim();
                if (TextUtils.isEmpty(zhanghaoOne)) {

                    Toast.makeText(LoginActivity.this, "账号不能为空,请输入账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mimaOne)) {
                    Toast.makeText(LoginActivity.this, "密码不能为空,请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<UserLogin> userLogins = userDao.loadAll();
                for (int i = 0; i < userLogins.size(); i++) {
                    String phone = userLogins.get(i).getPhone();
                    double v = Double.parseDouble(phone);
                    double v1 = Double.parseDouble(zhanghaoOne);
                    if (v == v1) {
                        Toast.makeText(LoginActivity.this, "此账号已注册,请重新注册", Toast.LENGTH_SHORT).show();
                        b = false;
                    } else {
                        b = true;
                    }
                }
                //注册
                if (b) {

                    UserLogin account = new UserLogin(zhanghaoOne, mimaOne, false);
                    userDao.insert(account);
                    Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_login:

                String zhanghao = etPhone.getText().toString().trim();
                String mima = etPassworld.getText().toString().trim();

                if (TextUtils.isEmpty(zhanghao)) {

                    Toast.makeText(LoginActivity.this, "账号不能为空,请输入账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mima)) {
                    Toast.makeText(LoginActivity.this, "密码不能为空,请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean a = true;

                List<UserLogin> userLogins1 = userDao.loadAll();
                if (userLogins1 != null && userLogins1.size() > 0) {//数据不为空

                    for (int i = 0; i < userLogins1.size(); i++) {

                        UserLogin userLogin = userLogins1.get(i);
                        String phone = userLogin.getPhone();
                        String passworld = userLogin.getPassworld();

                        if (phone.equals(zhanghao)) {//数据库有这个账号
                            a = false;
                            if (passworld.equals(mima)) {
                                //跳转
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();

                            } else {
                                //密码错误
                                Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    if (a) {
                        Toast.makeText(LoginActivity.this, "账号不存在", Toast.LENGTH_SHORT).show();
                    }
                } else {//数据库为空
                    Toast.makeText(LoginActivity.this, "账号不存在", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.activity_login:
                break;
        }
    }

}
