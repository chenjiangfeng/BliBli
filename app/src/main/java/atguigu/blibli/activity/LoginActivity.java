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

import java.util.Timer;
import java.util.TimerTask;

import atguigu.blibli.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.inject(this);
        etPhone.setSelection(etPhone.length());
        initListener();
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
                Toast.makeText(LoginActivity.this, "注册页面还没有写", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_login:
                String phone = etPhone.getText().toString().trim();
                String passerworld = etPassworld.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(LoginActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passerworld)) {
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                break;
            case R.id.activity_login:
                break;
        }
    }
}
