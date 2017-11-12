package com.tinklabs.cityapp.service;

import com.tinklabs.cityapp.comm.CommonConsts;
import com.tinklabs.cityapp.model.ContentModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 内容服务类
 * Created by Administrator on 2017/11/9 0009.
 */

public class ContentServ {

    public ArrayList<ContentModel> cityGuideList = new ArrayList<ContentModel>();  //城市服务数据
    public ArrayList<ContentModel> shopList = new ArrayList<ContentModel>();//购物服务数据
    public ArrayList<ContentModel> eatList = new ArrayList<ContentModel>();//美食服务数据
    private JsonService jsonServ; //JSON服务处理


    private static ContentServ instance = null;

    private ContentServ() {
        jsonServ  = JsonService.getInstance();
    }

    /**
     * 单例模式创建对象，保证只有一个服务类创建。
     */
    public static ContentServ getInstance() {
        synchronized (ContentServ.class) {
            if (instance == null) {
                instance = new ContentServ();
            }
        }
        return instance;
    }
    /**
     * 获取城市向导的数据
     */
    public void getCityGuideData()
    {
        //JSON解析数据
        JSONArray jsonArray = jsonServ.getDataByContentType(CommonConsts.CITY_GUIDE);
        if (jsonArray == null)
        {
            return;
        }
        JSONObject jsonObject = null;
        ContentModel model = null;
        try {
             for(int i = 0; i < jsonArray.length(); i++)
            {
                jsonObject = (JSONObject) jsonArray.get(i);
                model = new ContentModel();
                model.contentType = jsonObject.getInt("contentType");
                if (model.contentType == CommonConsts.CONTENT_TYPE_PIC_TEXT )
                {
                    model.title = jsonObject.getString("title");
                    model.pictureUri = jsonObject.getInt("image");
                    model.content = jsonObject.getString("titleContent");
                }
                else
                {
                    model.pictureUri = jsonObject.getInt("image");
                }
                cityGuideList.add(0,model);

            }
        } catch (JSONException e) {
                e.printStackTrace();
        }
    }

    /**
     * 获取购物的数据
     */
    public void getShopData()
    {
        //JSON解析数据
        JSONArray jsonArray = jsonServ.getDataByContentType(CommonConsts.SHOP);
        if (jsonArray == null)
        {
            return;
        }
        JSONObject jsonObject = null;
        ContentModel model = null;
        try {
            for(int i = 0; i < jsonArray.length(); i++)
            {
                jsonObject = (JSONObject) jsonArray.get(i);
                model = new ContentModel();
                model.contentType = jsonObject.getInt("contentType");
                if (model.contentType == CommonConsts.CONTENT_TYPE_PIC_TEXT )
                {
                    model.title = jsonObject.getString("title");
                    model.pictureUri = jsonObject.getInt("image");
                    model.content = jsonObject.getString("titleContent");
                }
                else
                {
                    model.pictureUri = jsonObject.getInt("image");
                }
                shopList.add(0,model);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取美食的数据
     */
    public void getEatData()
    {
        //JSON解析数据
        JSONArray jsonArray = jsonServ.getDataByContentType(CommonConsts.EAT);
        if (jsonArray == null)
        {
            return;
        }
        JSONObject jsonObject = null;
        ContentModel model = null;
        try {
            for(int i = 0; i < jsonArray.length(); i++)
            {
                jsonObject = (JSONObject) jsonArray.get(i);
                model = new ContentModel();
                model.contentType = jsonObject.getInt("contentType");
                if (model.contentType == CommonConsts.CONTENT_TYPE_PIC_TEXT )
                {
                    model.title = jsonObject.getString("title");
                    model.pictureUri = jsonObject.getInt("image");
                    model.content = jsonObject.getString("titleContent");
                }
                else
                {
                    model.pictureUri = jsonObject.getInt("image");
                }
                eatList.add(0,model);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
