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

        jsonObject.put("title","深圳景点之华侨城");
        jsonObject.put("titleContent","华侨城位于深圳盐田区，背靠梧桐山");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.demo);
        cityGuideJson1.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC);
        jsonObject.put("image", R.drawable.demo22);
        cityGuideJson1.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("title","深圳景点之莲花山");
        jsonObject.put("titleContent","莲花山城位于深圳福田区中心区");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.demo);
        cityGuideJson1.put(jsonObject);

        cityGuideJson2 = new JSONArray();
        jsonObject = new JSONObject();
        jsonObject.put("title","深圳景点之中心公园");
        jsonObject.put("titleContent","中心公园位于深圳福田区，紧邻华强北");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.demo);
        cityGuideJson2.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC);
        jsonObject.put("image", R.drawable.demo22);
        cityGuideJson2.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("title","深圳景点之笔架山");
        jsonObject.put("titleContent","笔架山城位于深圳福田区中心区，背靠笔架山水厂");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.demo);
        cityGuideJson2.put(jsonObject);

        cityGuideJson3 = new JSONArray();
        jsonObject = new JSONObject();
        jsonObject.put("title","深圳景点之梧桐山");
        jsonObject.put("titleContent","梧桐山位于深圳福田区，紧邻华强北");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.demo);
        cityGuideJson3.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC);
        jsonObject.put("image", R.drawable.demo22);
        cityGuideJson3.put(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("title","深圳景点之塘朗山");
        jsonObject.put("titleContent","塘朗山位于深圳福田区中心区，背靠笔架山水厂");
        jsonObject.put("contentType",CommonConsts.CONTENT_TYPE_PIC_TEXT);
        jsonObject.put("image", R.drawable.demo);
        cityGuideJson3.put(jsonObject);
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
            if (cityGuideReqCount > 3) {
                return null;
            }
            if (cityGuideReqCount == 1) {
                return cityGuideJson1;
            } else if (cityGuideReqCount == 2) {
                return cityGuideJson2;
            } else if (cityGuideReqCount == 3) {
                return cityGuideJson3;
            }


        } else if (contentType == CommonConsts.SHOP) {
            shopReqCount++;
            if (shopReqCount > 3) {
                return null;
            }
            if (shopReqCount == 1) {
                return shopJson1;
            } else if (shopReqCount == 2) {
                return shopJson2;
            } else if (shopReqCount == 3) {
                return shopJson3;
            }

        } else if (contentType == CommonConsts.EAT) {
            eatReqCount++;
            if (eatReqCount > 3) {
                return null;
            }
            if (eatReqCount == 1) {
                return eatJson1;
            } else if (eatReqCount == 2) {
                return eatJson2;
            } else if (eatReqCount == 3) {
                return eatJson3;
            }
        }
        return null;
    }
}
