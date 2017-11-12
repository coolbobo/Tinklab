package com.tinklabs.cityapp.service;

import com.tinklabs.cityapp.comm.CommonConsts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Service中的测试类，用于测试ContentService函数的正确性
 * Created by Administrator on 2017/11/12 0012.
 */
public class ContentServTest {
    private ContentServ contentServ;
    @Before
    public void setUp() throws Exception {
        contentServ = ContentServ.getInstance();
    }

    @After
    public void tearDown() throws Exception {

    }
    /**
     * 单例模式是否生效
     * @throws Exception
     */
    @Test
    public void getInstance() throws Exception {
        //比较是否是同一个对象，判断单例模式是否生效
        assertEquals(contentServ,  ContentServ.getInstance());
    }

    /**
     * 测试是否每次获取三条数据
     * 超过三次后，获取的数据是否是增加
     * @throws Exception
     */
    @Test
    public void getCityGuideData() throws Exception {
        contentServ.getCityGuideData();
        assertEquals(3,  contentServ.cityGuideList.size());
        contentServ.getCityGuideData();
        assertEquals(6,  contentServ.cityGuideList.size());
        contentServ.getCityGuideData();
        assertEquals(9,  contentServ.cityGuideList.size());

        contentServ.getCityGuideData();
        assertEquals(9,  contentServ.cityGuideList.size());
    }

    /**
     * 测试是否每次获取三条数据
     * 超过三次后，获取的数据是否是增加
     * @throws Exception
     */
    @Test
    public void getShopData() throws Exception {
        contentServ.getShopData();
        assertEquals(3,  contentServ.shopList.size());
        contentServ.getShopData();
        assertEquals(6,  contentServ.shopList.size());
        contentServ.getShopData();
        assertEquals(9,  contentServ.shopList.size());

        contentServ.getShopData();
        assertEquals(9,  contentServ.shopList.size());
    }

    /**
     * 测试是否每次获取三条数据
     * 超过三次后，获取的数据是否是增加
     * @throws Exception
     */
    @Test
    public void getEatData() throws Exception {
        contentServ.getEatData();
        assertEquals(3,  contentServ.eatList.size());
        contentServ.getEatData();
        assertEquals(6,  contentServ.eatList.size());
        contentServ.getEatData();
        assertEquals(9,  contentServ.eatList.size());

        contentServ.getEatData();
        assertEquals(9,  contentServ.eatList.size());
    }

}