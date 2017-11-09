package com.tinklabs.cityapp.service;

import com.tinklabs.cityapp.model.ContentModel;

import java.util.ArrayList;

/**
 * 内容服务类
 * Created by Administrator on 2017/11/9 0009.
 */

public class ContentServ {

    public ArrayList<ContentModel> cityGuideList = new ArrayList<ContentModel>();
    public ArrayList<ContentModel> shopList = new ArrayList<ContentModel>();
    public ArrayList<ContentModel> eatList = new ArrayList<ContentModel>();
    private ContentServ contentServ;


    private static ContentServ instance = null;

    private ContentServ() {

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

    }

    /**
     * 获取购物的数据
     */
    public void getShopData()
    {

    }

    /**
     * 获取美食的数据
     */
    public void getEatData()
    {

    }
}
