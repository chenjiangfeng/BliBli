package atguigu.blibli.model;

import atguigu.blibli.user.User;

/**
 * Created by 陈江峰 on 2017/4/1.
 */

public interface LoginHuiDiao {
    /**
     * 成功
     */
    void onSuccess(User user);

    /**
     * 失败
     */
    void onFailes(String e);
}
