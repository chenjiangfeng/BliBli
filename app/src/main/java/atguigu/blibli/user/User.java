package atguigu.blibli.user;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 陈江峰 on 2017/3/28.
 */
@Entity
public class User {
    @Id
    private Long id;
    private String password;
    private String name;
    @Transient
    private int tempUsageCount; // not persisted
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Generated(hash = 861448798)
    public User(Long id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }
    @Generated(hash = 586692638)
    public User() {
    }
}
