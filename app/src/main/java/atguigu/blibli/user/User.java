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
    private Long id;//主键
    private String shangpingming;
    private String url;
    private String password;
    private String name;
    private Boolean check;
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
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getShangpingming() {
        return this.shangpingming;
    }
    public void setShangpingming(String shangpingming) {
        this.shangpingming = shangpingming;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    //    public Boolean getCheck() {
//        return check;
//    }
//    public void setCheck(Boolean check) {
//        this.check = check;
//    }
    @Generated(hash = 1644338871)
    public User(Long id, String shangpingming, String url, String password,
            String name, Boolean check) {
        this.id = id;
        this.shangpingming = shangpingming;
        this.url = url;
        this.password = password;
        this.name = name;
        this.check = check;
    }
    @Generated(hash = 586692638)
    public User() {
    }
}
