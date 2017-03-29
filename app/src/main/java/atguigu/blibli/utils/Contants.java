package atguigu.blibli.utils;

/**
 * Created by 陈江峰 on 2017/3/21.
 */

public class Contants {
    //直播的地址
    public static String LIVE_URL = "http://live.bilibili.com/AppNewIndex/common?_device=android&appkey=1d8b6e7d45233436&build=501000&mobi_app=android&platform=android&scale=hdpi&ts=1490013188000&sign=92541a11ed62841120e786e637b9db3b";

    //发现流逝布局
    public static String FIND_URL = "http://app.bilibili.com/x/v2/search/hot?appkey=1d8b6e7d45233436&build=501000&limit=50&mobi_app=android&platform=android&ts=1490014710000&sign=e5ddf94fa9a0d6876cb85756c37c4adc";

    //话题中心的跳转

    public static String FING_TOALK_URL = "http://api.bilibili.com/topic/getlist?appkey=1d8b6e7d45233436&build=501000&mobi_app=android&page=1&pageSize=20&platform=android&ts=1490015740000&sign=be68382cdc99c168ef87f2fa423dd280";

    public static String ORIGINAL_URL = "http://app.bilibili.com/x/v2/rank?appkey=1d8b6e7d45233436&build=501000&mobi_app=android&order=all&platform=android&pn=1&ps=20&ts=1490015891000&sign=8e7dfaa1c2fb779943430b46e734b422";


    //推荐的
    public static String RECOMMEND_URL = "http://app.bilibili.com/x/feed/index?appkey=1d8b6e7d45233436&build=501000&idx=1490013261&mobi_app=android&network=wifi&platform=android&pull=true&style=2&ts=1490015599000&sign=af4edc66aef7e443c98c28de2b660aa4";
    //追番

    public static String RUN_URL = "http://bangumi.bilibili.com/api/app_index_page_v4?build=3940&device=phone&mobi_app=iphone&platform=ios";
    //追番的Banner
    public static String BANNER_URL = "http://bangumi.bilibili.com/api/bangumi_recommend?access_key=f5bd4e793b82fba5aaf5b91fb549910a&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3470&cursor=0&device=phone&mobi_app=iphone&pagesize=10&platform=ios&sign=56329a5709c401d4d7c0237f64f7943f&ts=1469613558";
    //分区的头布局
//    public static String BRANCH_URL = "http://live.bilibili.com/AppIndex/areas?_device=android&appkey=1d8b6e7d45233436&build=501000&mobi_app=android&platform=android&scale=xxhdpi&ts=1490016232000&sign=678fafda8c1c1e2db3d8224c2b31044f";
    //分区的头布局正确
    public static String BRANCH_URL = "http://app.bilibili.com/x/v2/region?appkey=1d8b6e7d45233436&build=501000&mobi_app=";
    //分区的下边的数据
    public static String PARTITION_URL = "http://app.bilibili.com/x/v2/show/region?appkey=1d8b6e7d45233436&build=501000&mobi_app=android&platform=android&ts=1490014674000&sign=93edb7634f38498a60e5c3ad0b8b0974";

    //全区排行榜
    public static String REGION_URL = "http://app.bilibili.com/x/v2/rank/region?appkey=1d8b6e7d45233436&build=501000&mobi_app=android&platform=android&pn=1&ps=20&rid=13&ts=1490017056000&sign=6825377c5a626fa";
    //全区 排行榜的番据
    public static String REGION_URLONE = "http://app.bilibili.com/x/v2/rank/region?appkey=1d8b6e7d45233436&build=501000&mobi_app=android&platform=android&pn=1&ps=20&rid=13&ts=1490017056000&sign=6825377c5a626fa80913d27e41ab8049";

    public static String SHOPPING_URL = "http://bmall.bilibili.com/apiSecond/public/getModelData.do?mark_code=7f7d1ac0796211e6ab5352223301d29a";

    public static String SHOPPINGALL_URL = "http://bmall.bilibili.com/api/product/list.do?pn=1&ps=6";

    //搜素的头
    public static String SEACHER_TOP_URL = "http://app.bilibili.com/x/v2/search?appkey=1d8b6e7d45233436&build=501000&duration=0&keyword=";

    //搜索的尾
    public static String SEACHER_BUTTON_URL = "&mobi_app=android&platform=android&pn=1&ps=20";

        //购物车
    public static String LIST_URL = "http://bmall.bilibili.com/api/product/list.do?pn=1&ps=6";

}
