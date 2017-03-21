package atguigu.blibli.bean;

import java.io.Serializable;

/**
 * Created by 陈江峰 on 2017/3/21.
 */

public class WebViewBean  implements Serializable{
    /**
     * title : 对象？你尽管抢，抢到算我输
     * img : http://i0.hdslb.com/bfs/live/2807719e244e45714a3e08548b1c889815eaa1f6.png
     * remark : 情人节2.0
     * link : http://live.bilibili.com/AppBanner/index?id=467
     */

    private String title;
    private String img;
    private String remark;
    private String link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "WebViewBean{" +
                "title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", remark='" + remark + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
