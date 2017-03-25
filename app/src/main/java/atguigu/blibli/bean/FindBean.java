package atguigu.blibli.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by 陈江峰 on 2017/3/25.
 */

public class FindBean {
    /**
     * code : 0
     * data : [{"title":"【1月】小林家的龙女仆 11【独家正版】","cover":"http://i2.hdslb.com/bfs/archive/a1080d35c404c099ee066646113fca17a5f131e5.jpg_320x200.jpg","uri":"bilibili://video/9326290","param":"9326290","goto":"av","name":"哔哩哔哩番剧","play":1229156,"danmaku":30726,"reply":23,"favourite":541,"pts":1229156},{"title":"【1月/完结】火影忍者 疾风传 720","cover":"http://i0.hdslb.com/bfs/archive/2deda6733b8266ec67ae536aee93aa068ecdc5d7.jpg_320x200.jpg","uri":"bilibili://video/9341465","param":"9341465","goto":"av","name":"TV-TOKYO","play":698080,"danmaku":58733,"reply":21,"favourite":3259,"pts":698080},{"title":"【1月】人渣的本愿 11【幻樱】","cover":"http://i0.hdslb.com/bfs/archive/1e2ce0f65ab7d6be7464cb64dcf71158117d7a8a.jpg_320x200.jpg","uri":"bilibili://video/9352370","param":"9352370","goto":"av","name":"真鱼","play":422872,"danmaku":18191,"reply":22,"favourite":1285,"pts":422872},{"title":"【4月】双星之阴阳师 49","cover":"http://i1.hdslb.com/bfs/archive/4a69258c93dba82814810066f57b0f981a3d3ac5.jpg_320x200.jpg","uri":"bilibili://video/9323043","param":"9323043","goto":"av","name":"TV-TOKYO","play":356528,"danmaku":12314,"reply":7,"favourite":143,"pts":356528},{"title":"【1月/完结】SEIREN 清恋 12","cover":"http://i0.hdslb.com/bfs/archive/92992943edf75317a8f61bb9dae8f1a8f8d06e17.jpg_320x200.jpg","uri":"bilibili://video/9354347","param":"9354347","goto":"av","name":"哔哩哔哩番剧","play":154779,"danmaku":10170,"favourite":112,"pts":154779},{"title":"【1月/完结】URARA迷路帖 12【独家正版】","cover":"http://i0.hdslb.com/bfs/archive/c07088fed4a6314824b6dc1af72cb120e8acef1c.jpg_320x200.jpg","uri":"bilibili://video/9354354","param":"9354354","goto":"av","name":"哔哩哔哩番剧","play":134973,"danmaku":12442,"reply":4,"favourite":164,"pts":134973},{"title":"【1月】One Room 11【独家正版】","cover":"http://i0.hdslb.com/bfs/archive/f1658f3ead2dccd45186cfb9efcc34608ed26f11.jpg_320x200.jpg","uri":"bilibili://video/9324701","param":"9324701","goto":"av","name":"哔哩哔哩番剧","play":119597,"danmaku":1276,"reply":2,"favourite":85,"pts":119597},{"title":"【1月】秋叶原之旅 12【独家正版】","cover":"http://i0.hdslb.com/bfs/archive/2251ac1a81b23075d089ee75827c53d203b8b6dc.jpg_320x200.jpg","uri":"bilibili://video/9325758","param":"9325758","goto":"av","name":"哔哩哔哩番剧","play":93008,"danmaku":3813,"reply":2,"favourite":79,"pts":93008},{"title":"【1月/完结】风夏 12","cover":"http://i0.hdslb.com/bfs/archive/35568a527a83e51b8dfa391d9aa946e15a19c4cc.jpg_320x200.jpg","uri":"bilibili://video/9361867","param":"9361867","goto":"av","name":"哔哩哔哩番剧","play":91410,"danmaku":6755,"reply":6,"favourite":71,"pts":91410},{"title":"【合集】巴哈姆特之怒 GENESIS","cover":"http://i1.hdslb.com/bfs/archive/a01aa9235ebdcd5fa0ff51119168a8d58d1e21cf.jpg_320x200.jpg","uri":"bilibili://video/9307081","param":"9307081","goto":"av","name":"哔哩哔哩番剧","play":71972,"danmaku":2470,"reply":1,"favourite":3108,"pts":71972},{"title":"【OVA】机动战士敢达 雷霆宙域战线 05【独家正版】","cover":"http://i1.hdslb.com/bfs/archive/4782a41b9bafd53aba9373126d572624792d851c.jpg_320x200.jpg","uri":"bilibili://video/9343907","param":"9343907","goto":"av","name":"哔哩哔哩番剧","play":71780,"danmaku":2185,"reply":2,"favourite":178,"pts":71780},{"title":"【1月】CHAOS;CHILD 11【独家正版】","cover":"http://i2.hdslb.com/bfs/archive/2ceed9bbd3d7fc38a4f46b01b44a52c282312c68.jpg_320x200.jpg","uri":"bilibili://video/9331337","param":"9331337","goto":"av","name":"哔哩哔哩番剧","play":61861,"danmaku":1608,"reply":4,"favourite":65,"pts":61861},{"title":"【浪川大辅】如果是女性，会选择伊佐那社吗？《梦100》联动《K》","cover":"http://i0.hdslb.com/bfs/archive/5def9a9fda64fa6b49a7fef26b79b44ac981b61d.jpg_320x200.jpg","uri":"bilibili://video/9319306","param":"9319306","goto":"av","name":"哔哩哔哩游戏中心","play":52118,"danmaku":626,"favourite":2106,"pts":52118},{"title":"【OVA】珈百璃的堕落OVA 01【F宅】","cover":"http://i0.hdslb.com/bfs/archive/86a4796e21333822b85c370ceb04df8e9d35a93f.jpg_320x200.jpg","uri":"bilibili://video/9343225","param":"9343225","goto":"av","name":"空灵雨迹","play":43778,"danmaku":1965,"reply":10,"favourite":1370,"pts":43778},{"title":"【OVA】机动战士敢达 雷霆宙域战线 01【独家正版】","cover":"http://i2.hdslb.com/bfs/archive/c4833f76b82d30803ac1eddc67a2a06aea787782.jpg_320x200.jpg","uri":"bilibili://video/9343934","param":"9343934","goto":"av","name":"哔哩哔哩番剧","play":39916,"danmaku":675,"reply":2,"favourite":148,"pts":39916},{"title":"【4月】雏子的笔记 01【桜都】","cover":"http://i0.hdslb.com/bfs/archive/08e92cf02034a757a6665fd490d64b0bf5ceb603.jpg_320x200.jpg","uri":"bilibili://video/9317339","param":"9317339","goto":"av","name":"真鱼","play":39394,"danmaku":1332,"reply":4,"favourite":1463,"pts":39394},{"title":"【8月/韩漫】心灵的声音 61-62","cover":"http://i0.hdslb.com/bfs/archive/5630d3d798d4f5267002b1869e86d9ac872cf61b.jpg_320x200.jpg","uri":"bilibili://video/9322133","param":"9322133","goto":"av","name":"番剧烤肉","play":34081,"danmaku":561,"favourite":26,"pts":34081},{"title":"【长篇】精灵宝可梦 日月 19【枫叶】","cover":"http://i1.hdslb.com/bfs/archive/caa710a762235b3db127f7347e85d48cefbf2e8e.jpg_320x200.jpg","uri":"bilibili://video/9361648","param":"9361648","goto":"av","name":"真鱼","play":31663,"danmaku":1827,"reply":2,"favourite":123,"pts":31663},{"title":"【7月】狂赌之渊 PV1","cover":"http://i0.hdslb.com/bfs/archive/0e07a33a913f3c58cd5c010d3be10e992f17c03a.jpg_320x200.jpg","uri":"bilibili://video/9317083","param":"9317083","goto":"av","name":"ACG速报","play":16615,"danmaku":95,"reply":1,"favourite":273,"pts":16615},{"title":"【4月】Re:CREATORS PV3【F宅】","cover":"http://i1.hdslb.com/bfs/archive/cc2b0716046c7244f69660de9b3e0704c0c05028.jpg_320x200.jpg","uri":"bilibili://video/9318157","param":"9318157","goto":"av","name":"空灵雨迹","play":13363,"danmaku":92,"favourite":159,"pts":13363}]
     * message :
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 【1月】小林家的龙女仆 11【独家正版】
         * cover : http://i2.hdslb.com/bfs/archive/a1080d35c404c099ee066646113fca17a5f131e5.jpg_320x200.jpg
         * uri : bilibili://video/9326290
         * param : 9326290
         * goto : av
         * name : 哔哩哔哩番剧
         * play : 1229156
         * danmaku : 30726
         * reply : 23
         * favourite : 541
         * pts : 1229156
         */

        private String title;
        private String cover;
        private String uri;
        private String param;
        @JSONField(name = "goto")
        private String gotoX;
        private String name;
        private int play;
        private int danmaku;
        private int reply;
        private int favourite;
        private int pts;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getGotoX() {
            return gotoX;
        }

        public void setGotoX(String gotoX) {
            this.gotoX = gotoX;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPlay() {
            return play;
        }

        public void setPlay(int play) {
            this.play = play;
        }

        public int getDanmaku() {
            return danmaku;
        }

        public void setDanmaku(int danmaku) {
            this.danmaku = danmaku;
        }

        public int getReply() {
            return reply;
        }

        public void setReply(int reply) {
            this.reply = reply;
        }

        public int getFavourite() {
            return favourite;
        }

        public void setFavourite(int favourite) {
            this.favourite = favourite;
        }

        public int getPts() {
            return pts;
        }

        public void setPts(int pts) {
            this.pts = pts;
        }
    }
}
