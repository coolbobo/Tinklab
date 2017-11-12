package com.tinklabs.cityapp.service;

import com.tinklabs.cityapp.R;
import com.tinklabs.cityapp.comm.CommonConsts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Json服务类
 * Created by Administrator on 2017/11/10 0010.
 */


public class JsonService {

    private int cityGuideReqCount = 0;
    private int shopReqCount = 0;
    private int eatReqCount = 0;

    private JSONArray cityGuideJson1, cityGuideJson2, cityGuideJson3;
    private JSONArray shopJson1, shopJson2,shopJson3;
    private JSONArray eatJson1,eatJson2,eatJson3;


    private static int GET_DATA_COUNT_1 = 1;  //请求数据的次数，本Demo演示最多请求三次数据
    private static int GET_DATA_COUNT_2 = 2;
    private static int GET_DATA_COUNT_3 = 3;
    private static JsonService instance = null;

    private JsonService() {
        try {
            initSetupData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单例模式创建对象，保证只有一个服务类创建。
     */
    public static JsonService getInstance() {
        synchronized (ContentServ.class) {
            if (instance == null) {
                instance = new JsonService();
            }
        }
        return instance;
    }
    /**
     * 初始化组装数据,打桩模拟数据
     */
    private void initSetupData() throws JSONException {
        //json数组  
        cityGuideJson1 = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("title","深圳世界之窗");
        jsonObject.put("titleContent","位于中国广东省深圳市南山区华侨城，是一个把世界奇观、历史遗迹、古今名胜、民间歌舞表演融为一体的人造主题公园。公园中的各个景点都按不同比例自由仿建。 深圳世界之窗包括世界著名景观埃及金字塔、阿蒙神庙、柬埔寨吴哥窟、美国大峡谷、巴黎雄狮凯旋门、梵蒂冈圣彼得大教堂、印度泰姬陵、澳大利亚悉尼歌剧院、意大利比萨斜塔等等");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.shijiezhichuang);
        cityGuideJson1.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC);
        jsonObject.put("image", R.drawable.dameisha);
        cityGuideJson1.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("title","欢乐谷");
        jsonObject.put("titleContent","位于深圳南山区，深圳欢乐谷是华侨城集团新一代大型主题乐园，首批国家5A级旅游景区，是一座融参与性、观赏性、娱乐性、趣味性于一体的中国现代主题乐园。 经过5期的滚动发展，已成为国内投资规模最大、设施最先进的现代主题乐园，并连续4年荣膺亚太十大主题公园");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.huanlegu);
        cityGuideJson1.put(jsonObject);

        cityGuideJson2 = new JSONArray();
        jsonObject = new JSONObject();
        jsonObject.put("title","华侨城");
        jsonObject.put("titleContent","东部华侨城，坐落于中国广东省深圳市大梅沙是国内首个集休闲度假、观光旅游、户外运动、科普教育、生态探险等主题于一体的大型综合性国家生态旅游示范区。 主要包括大侠谷生态公园、茶溪谷休闲公园、云海谷体育公园、华兴寺、主题酒店群落、天麓大宅等六大板块。以“让都市人回归自然”为宗旨，定位于建设成为集生态旅游、娱乐休闲、郊野度假、户外运动等多个主题于一体的综合性都市型山地主题休闲度假区");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.huaqiaocheng);
        cityGuideJson2.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC);
        jsonObject.put("image", R.drawable.xichong);
        cityGuideJson2.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("title","红树林");
        jsonObject.put("titleContent","深圳红树林位于深圳湾畔，是我国面积最小的国家级自然保护区，是以红树科植物为主组成的海洋木本植物群落，因树干呈淡红色而得名。 保护区有海漆、木榄、秋茄等珍稀树种，还是国家级的鸟类保护区，是东半球候鸟迁徙的栖息地和中途歇脚点");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.hongshunlin);
        cityGuideJson2.put(jsonObject);

        cityGuideJson3 = new JSONArray();
        jsonObject = new JSONObject();
        jsonObject.put("title","仙湖植物园");
        jsonObject.put("titleContent","深圳仙湖植物园位于深圳市东北郊，东倚深圳第一高峰梧桐山，西临深圳水库。占地8800多亩，始建于1983年，1988年正式对外开放");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.xianhuzhiwuyuan);
        cityGuideJson3.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC);
        jsonObject.put("image", R.drawable.dapengsuocheng);
        cityGuideJson3.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("title","园博园");
        jsonObject.put("titleContent","深圳国际园林花卉博览园（简称“园博园”）地处深圳市福田区竹子林西片，面积约66万平方米，是一个集园林花卉博览、文化艺术、科普教育、旅游展览、太阳能并网发电于一体的市政公园。于2004年9月23日正式开放。2008年9月12日，园博园被住房和城乡建设部评为“国家重点公园”。 ");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.yuanboyuan);
        cityGuideJson3.put(jsonObject);



        //SHOP数据
        //json数组  
        shopJson1 = new JSONArray();
        jsonObject = new JSONObject();

        jsonObject.put("title","宜家家居");
        jsonObject.put("titleContent","IKEA 是瑞典来的品牌，是家具、家居用品的大卖场。卖场里面很大，家具主要是木制和铁制的居多。 家居用品和摆设的设计都偏向于简约、实用。价格方面，总体还是比较实惠的。运输采取自助形式。 所有商品基本都倾向于平板包装，可以自己打包后装到车上运回家，再自行组装。商场里还配有小卖部，有一些零食和轻食供应。 如果需要在商场里逛较长时间的话，也可以在小卖部小坐休息");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.yijia);
        shopJson1.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC);
        jsonObject.put("image", R.drawable.aotelaisi);
        shopJson1.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("title","益田假日");
        jsonObject.put("titleContent","地处深圳华侨城，紧临深南大道，是一个集体验式购物中心、生态写字体、五星级主题酒店为一体的大型建筑综合体");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.yitianjiari);
        shopJson1.put(jsonObject);

        shopJson2 = new JSONArray();
        jsonObject = new JSONObject();
        jsonObject.put("title","COCO PARK");
        jsonObject.put("titleContent","COCO Park是深圳唯一公园版情景式购物中心，CBD版块新商业代表。是深圳最具时尚气质的购物中心，集餐饮、购物、休闲、娱乐多功能于一体。拥有200余家国际国内知名品牌。负一楼为吉之岛超市、国际连锁餐饮、珠宝配饰、个人护理、家居生活、影音、礼品店。一楼为国际精品服饰店、珠宝、名表、经典配饰名");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.coco);
        shopJson2.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC);
        jsonObject.put("image", R.drawable.jingji);
        shopJson2.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("title","海岸城购物中心");
        jsonObject.put("titleContent","海岸城购物中心是目前深圳西部经营面积最大、功能最齐全、服务人群最广泛、最具代表性的集购物、休闲、娱乐、餐饮等为一体的大型购物中心，也是深圳唯一具有滨海风情特色的购物中心");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.haiancheng);
        shopJson2.put(jsonObject);

        shopJson3 = new JSONArray();
        jsonObject = new JSONObject();
        jsonObject.put("title","深圳书城");
        jsonObject.put("titleContent","深圳书城是深圳出版发行集团旗下享誉国内外的现代化大型综合购书中心和知名文化品牌。深圳书城品种齐全、功能完备、管理先进，致力于为读者提供多功能、全方位、高质量的一站式文化消费服务。在全国同业中率先引进大型综合超市(GMS)的经营业态，内设有商务中心、餐饮中心、总服务台、邮电代办所、银行等服务设施，采用城中设店布局、出版物全品种营销，经销全国800多家出版生产商的各类书刊、音像制品、电子出版物、文化艺术品、PDA产品等约23万种。");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.shucheng);
        shopJson3.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC);
        jsonObject.put("image", R.drawable.wanxiangcheng);
        shopJson3.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("title","东门");
        jsonObject.put("titleContent","东门步行街是深圳历史最为悠久的商业区。以前的东门商业街主要都是一些小摊、小贩在经营，经营的商品也是以低档为主。而今，经过改造的东门商业街已经成为集购物、休闲和旅游观光于一体的新型步行街。新“东门”作为中国18条重要商街位列其中");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.dongmen);
        shopJson3.put(jsonObject);


        //美食数据
        //json数组  
        eatJson1 = new JSONArray();
        jsonObject = new JSONObject();

        jsonObject.put("title","沙井鲜蚝");
        jsonObject.put("titleContent","蚝是深圳最著名的特产，以沙井蚝最著称于世。沙井鲜蚝富含很高的蛋白质，干淀粉等营养成分，被人们誉为“海底牛奶”。用它来佐餐、清蒸、酥炸，都十分鲜美可口");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.shenghao);
        eatJson1.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC);
        jsonObject.put("image", R.drawable.shuangpinai);
        eatJson1.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("title","师公会海鲜酒家");
        jsonObject.put("titleContent","这里是一条海鲜食街，这里晚上很多人，有的甚至特地开车来吃，几乎爆满要排队。环境一般，但很卫生，街对面就是海，楼上的玻璃窗外可以看到码头和海，楼下还有人点歌，很不错，停车也免费，吃完海能在附近逛逛海产，也是一个不错的选择。上菜速度也很快");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.shigonghui);
        eatJson1.put(jsonObject);

        eatJson2 = new JSONArray();
        jsonObject = new JSONObject();
        jsonObject.put("title","基围虾");
        jsonObject.put("titleContent","基围虾是深圳西乡镇创汇的主要水产品。其体积较小，状如中指，全身透明略带麻点，虾皮特别薄，肉质非常细嫩，鲜美可口，营养丰富，其蛋白质含量与沙井蚝相媲美。经常食用基围虾具有明目洁齿、养颜健身、益寿延年的功效。");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.jiweixia);
        eatJson2.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC);
        jsonObject.put("image", R.drawable.wutouyu);
        eatJson2.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("title","光明乳鸽");
        jsonObject.put("titleContent","光明农场四大美食之一，以润、滑、甜、嫩为特点，滋味浓鲜。烹制的“光明乳鸽”，因皮脆肉滑、鲜嫩味美受到市民喜爱，成为深圳特色食品");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.guangmingruge);
        eatJson2.put(jsonObject);

        eatJson3 = new JSONArray();
        jsonObject = new JSONObject();
        jsonObject.put("title","公明烧鹅");
        jsonObject.put("titleContent","公明烧鹅，因公明镇烧制的鹅色、香、味都最佳而得名。它选用本地草鹅，鹅肥肉细，约要养100天才好。辅以各种配料用中火烧烤，烧出的鹅金黄色，皮脆，香味浓郁，肥而不腻，昔日最出名的师傅是公明镇陈水德父子");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.shaoer);
        eatJson3.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC);
        jsonObject.put("image", R.drawable.changfen);
        eatJson3.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("title","南澳鲍鱼");
        jsonObject.put("titleContent","鲍鱼主要产于南澳海湾的海崖险要处，尤以东冲的鹿咀为最多。鲍鱼肉很发达，肉质滑爽脆嫩，营养特别丰富，自古被视为“鲍、参、翅、肚”四海味珍品之首。");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.baoyu);
        eatJson3.put(jsonObject);
    }

    /**
     *
     * @param contentType 获取数据的类型：ctiyguide/shop/eat
     * @return
     */
    public JSONArray getDataByContentType(int contentType)
    {
        if (contentType == CommonConsts.CITY_GUIDE) {
            cityGuideReqCount++;
            if (cityGuideReqCount > GET_DATA_COUNT_3) {
                return null;
            }
            if (cityGuideReqCount == GET_DATA_COUNT_1) {
                return cityGuideJson1;
            } else if (cityGuideReqCount == GET_DATA_COUNT_2) {
                return cityGuideJson2;
            } else if (cityGuideReqCount == GET_DATA_COUNT_3) {
                return cityGuideJson3;
            }


        } else if (contentType == CommonConsts.SHOP) {
            shopReqCount++;
            if (shopReqCount > GET_DATA_COUNT_3) {
                return null;
            }
            if (shopReqCount == GET_DATA_COUNT_1) {
                return shopJson1;
            } else if (shopReqCount == GET_DATA_COUNT_2) {
                return shopJson2;
            } else if (shopReqCount == GET_DATA_COUNT_3) {
                return shopJson3;
            }

        } else if (contentType == CommonConsts.EAT) {
            eatReqCount++;
            if (eatReqCount > GET_DATA_COUNT_3) {
                return null;
            }
            if (eatReqCount == GET_DATA_COUNT_1) {
                return eatJson1;
            } else if (eatReqCount == GET_DATA_COUNT_2) {
                return eatJson2;
            } else if (eatReqCount == GET_DATA_COUNT_3) {
                return eatJson3;
            }
        }
        return null;
    }
}
