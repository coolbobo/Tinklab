package com.tinklabs.cityapp.service;

import com.tinklabs.cityapp.comm.CommonConsts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Service中的测试类，用于测试Jsonservcie函数的正确性
 * Created by Administrator on 2017/11/12 0012.
 */
public class JsonServiceTest {
    private JsonService jsonServ;
    @Before
    public void setUp() throws Exception {
        jsonServ =  JsonService.getInstance();
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
        assertEquals(jsonServ,  JsonService.getInstance());
    }

    /**
     * 三次获取城市向导数据后，是否还有数据返回
     * @throws Exception
     */
    @Test
    public void getCityGuideDataByContentType() throws Exception {

        jsonServ.getDataByContentType(CommonConsts.CITY_GUIDE);
        jsonServ.getDataByContentType(CommonConsts.CITY_GUIDE);
        jsonServ.getDataByContentType(CommonConsts.CITY_GUIDE);

        //检测多次取数据后，是不是返回值为NULL
        assertEquals(null, jsonServ.getDataByContentType(CommonConsts.CITY_GUIDE));
    }

    /**
     * 三次获取购物数据后，是否还有数据返回
     * @throws Exception
     */
    @Test
    public void getShopDataByContentType() throws Exception {

        jsonServ.getDataByContentType(CommonConsts.SHOP);
        jsonServ.getDataByContentType(CommonConsts.SHOP);
        jsonServ.getDataByContentType(CommonConsts.SHOP);
        //检测多次取数据后，是不是返回值为NULL
        assertEquals(null, jsonServ.getDataByContentType(CommonConsts.SHOP));
    }

    /**
     * 三次获取美食数据后，是否还有数据返回
     * @throws Exception
     */
    @Test
    public void getEatDataByContentType() throws Exception {

        jsonServ.getDataByContentType(CommonConsts.EAT);
        jsonServ.getDataByContentType(CommonConsts.EAT);
        jsonServ.getDataByContentType(CommonConsts.EAT);
        //检测多次取数据后，是不是返回值为NULL
        assertEquals(null, jsonServ.getDataByContentType(CommonConsts.EAT));
    }


}