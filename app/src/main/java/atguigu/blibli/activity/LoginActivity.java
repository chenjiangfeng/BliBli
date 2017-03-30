package atguigu.blibli.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anye.greendao.gen.UserDao;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import atguigu.blibli.R;
import atguigu.blibli.user.User;
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
    private String phone;
    private String passerworld;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.inject(this);
        etPhone.setSelection(etPhone.length());
        initListener();
        phone = etPhone.getText().toString().trim();
        passerworld = etPassworld.getText().toString().trim();
        userDao = MyApplication.getInstance().getDaoSession().getUserDao();

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
                Toast.makeText(LoginActivity.this, "忘记密码", Toast.LENGTH_SHORT).show();
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

                if (TextUtils.isEmpty(phone)) {

                    Toast.makeText(LoginActivity.this, "账号不能为空,请输入账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passerworld)) {
                    Toast.makeText(LoginActivity.this, "密码不能为空,请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<User> users = userDao.loadAll();
                for (int i = 0; i < users.size(); i++) {
                    String name = users.get(i).getName();
                    if (phone == name) {
                        Toast.makeText(LoginActivity.this, "此账号已注册,请重新注册", Toast.LENGTH_SHORT).show();
                        return;
                    }
               }
                //注册

                User u = new User(null,null,"",passerworld,phone,true);
                userDao.insert(u);




                break;
            case R.id.bt_login:

                if (TextUtils.isEmpty(phone)) {

                    Toast.makeText(LoginActivity.this, "账号不能为空,请输入账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passerworld)) {
                    Toast.makeText(LoginActivity.this, "密码不能为空,请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                break;
            case R.id.activity_login:
                break;
        }
    }
}
