package com.tinklabs.cityapp.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.tinklabs.cityapp.R;
import com.tinklabs.cityapp.comm.CommonConsts;
import com.tinklabs.cityapp.model.ContentModel;
import com.tinklabs.cityapp.service.ContentServ;

import java.util.ArrayList;

/**
 * 程序页面Activity类，用于显示页面，切换页面
 */
public class MainActivity extends AppCompatActivity {

    public ContentServ conterServ = null;  //界面UI服务类

    private PullToRefreshLayout pullToRefreshLayout; //下拉刷新组件


    private ListView mContentList; //显示具体内容的列表,三个列表共用一个List
    private ContentCityGuideListAdapter contentListAdapter; //显示内容的适配器
    private RelativeLayout cityGuideLayout,shopLayout,eatLayout; //布局对象
    private TextView cityGuideTxt,shopTxt,eatTxt;//布局对象
    private RelativeLayout cityGuideLine,shopLine,eatLine;//布局对象
    public static Context mContext; //程序上下文
    private static int currContent; //当前显示的内容，城市向导、购物、美食

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        conterServ = ContentServ.getInstance();
        setContentView(R.layout.activity_main);
        mContext = this;
        init();
    }

    /**
     * 初始化页面和数据
     */
    private void init()
    {
        cityGuideLayout =  (RelativeLayout) findViewById(R.id.cityguide_layout);
        shopLayout =  (RelativeLayout) findViewById(R.id.shop_layout);
        eatLayout =  (RelativeLayout) findViewById(R.id.eat_layout);

        cityGuideTxt = (TextView) findViewById(R.id.cityGuideTxt);
        shopTxt = (TextView) findViewById(R.id.shopTxt);
        eatTxt = (TextView) findViewById(R.id.eatTxt);

        cityGuideLine = (RelativeLayout) findViewById(R.id.cityGuideLine);
        shopLine = (RelativeLayout) findViewById(R.id.shopLine);
        eatLine = (RelativeLayout) findViewById(R.id.eatLine);

        pullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.refresh_layout);
        mContentList = (ListView) findViewById(R.id.list);
        contentListAdapter = new ContentCityGuideListAdapter();

        pullToRefreshLayout.setCanLoadMore(false);
        pullToRefreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 根据不同的显示类型，加载不同的数据
                        if(currContent == CommonConsts.SHOP)
                        {
                            conterServ.getShopData();
                        }
                        else if (currContent == CommonConsts.EAT)
                        {
                            conterServ.getEatData();
                        }else
                        {
                            conterServ.getCityGuideData();
                        }

                        //通知刷新数据
                        contentListAdapter.notifyDataSetChanged();
                        pullToRefreshLayout.finishRefresh();
                    }
                }, 500);
            }

            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 结束加载更多
                        pullToRefreshLayout.finishLoadMore();
                    }
                }, 500);
            }
        });


        //提前加载数据，显示时能快速显示出来。
        conterServ.getCityGuideData();
        conterServ.getEatData();
        conterServ.getShopData();

        initListener();

        //程序初始化时，需要聚集焦点在第一个按钮上，模拟点击事件。
        cityGuideLayout.performClick();
    }

    /**
     * 控件点击事件注册
     */
    private void initListener()
    {
        //城市向导的点击事件
        cityGuideLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //切換數據
                cityGuideTxt.setTextColor(Color.BLACK);
                shopTxt.setTextColor(Color.GRAY);
                eatTxt.setTextColor(Color.GRAY);

                cityGuideLine.setVisibility(View.VISIBLE);
                shopLine.setVisibility(View.INVISIBLE);
                eatLine.setVisibility(View.INVISIBLE);

                showData(CommonConsts.CITY_GUIDE);
                currContent = CommonConsts.CITY_GUIDE;
                Log.i(TAG, "cityGuideLayout click");

            }
        });
        //购物的点击事件
        shopLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //切換數據
                cityGuideTxt.setTextColor(Color.GRAY);
                shopTxt.setTextColor(Color.BLACK);
                eatTxt.setTextColor(Color.GRAY);

                cityGuideLine.setVisibility(View.INVISIBLE);
                shopLine.setVisibility(View.VISIBLE);
                eatLine.setVisibility(View.INVISIBLE);

                showData(CommonConsts.SHOP);
                currContent = CommonConsts.SHOP;
                Log.i(TAG, "shopLayout click");
            }
        });
        //美食向导的点击事件
        eatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //切換數據
                cityGuideTxt.setTextColor(Color.GRAY);
                shopTxt.setTextColor(Color.GRAY);
                eatTxt.setTextColor(Color.BLACK);

                cityGuideLine.setVisibility(View.INVISIBLE);
                shopLine.setVisibility(View.INVISIBLE);
                eatLine.setVisibility(View.VISIBLE);

                showData(CommonConsts.EAT);
                currContent = CommonConsts.EAT;
                Log.i(TAG, "eatLayout click");
            }
        });
    }

    /**
     * 修改适配器的显示数据
     * @param contentType
     */
    private void showData(int contentType)
    {

        if (contentType == CommonConsts.CITY_GUIDE)
        {
            contentListAdapter.setData(conterServ.cityGuideList);
        }
        else if (contentType == CommonConsts.EAT)
        {
            contentListAdapter.setData(conterServ.eatList);
        }
        else
        {
            contentListAdapter.setData(conterServ.shopList);
        }
        mContentList.setAdapter(contentListAdapter);
        contentListAdapter.notifyDataSetChanged();

    }


    /**
     * 数据适配器类，用于显示具体的某一条数据
     */
    private class ContentCityGuideListAdapter extends BaseAdapter
    {
        private ArrayList<ContentModel> cityGuideLst = new ArrayList<ContentModel>();
        private LayoutInflater mInflater = LayoutInflater.from(MainActivity.mContext);
        private ContentModel cityGuideItem = null;

        @Override
        public int getCount()
        {
            return cityGuideLst.size();
        }

        @Override
        public Object getItem(int arg0)
        {
            return cityGuideLst.get(arg0);
        }

        @Override
        public long getItemId(int arg0)
        {
            return arg0;
        }

        public void setData(ArrayList<ContentModel> cityGuideLst)
        {
            this.cityGuideLst = cityGuideLst;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2)
        {
            CityGuideViewHolder cityGuideViewHolder = new CityGuideViewHolder();
            cityGuideItem = cityGuideLst.get(arg0);

            arg1 = this.mInflater.inflate(R.layout.cityguide_item, null);
            cityGuideViewHolder.itemPic = (ImageView) arg1.findViewById(R.id.item_pic_image);
            cityGuideViewHolder.itemPicTextPic = (ImageView) arg1.findViewById(R.id.pic_text_image);
            cityGuideViewHolder.title = (TextView) arg1.findViewById(R.id.pic_text_title);
            cityGuideViewHolder.titleContent = (TextView) arg1.findViewById(R.id.pic_text_content);
            cityGuideViewHolder.picLayout = (RelativeLayout) arg1.findViewById(R.id.item_pic_layout);
            cityGuideViewHolder.picTextLayout = (RelativeLayout) arg1.findViewById(R.id.item_pic_text_layout);


            //圖片和文字混排模式
            if(cityGuideItem.contentType == CommonConsts.CONTENT_TYPE_PIC_TEXT)
            {
                cityGuideViewHolder.picLayout.setVisibility(View.GONE);
                cityGuideViewHolder.title.setText(cityGuideItem.title);
                cityGuideViewHolder.titleContent.setText(cityGuideItem.content);
                cityGuideViewHolder.itemPicTextPic.setImageResource(cityGuideItem.pictureUri);

            }
            else
            {
                cityGuideViewHolder.picTextLayout.setVisibility(View.GONE);
                cityGuideViewHolder.itemPic.setImageResource(cityGuideItem.pictureUri);
            }
            return arg1;
        }
    }

    /**
     * Item数据的Holder
     */
    class CityGuideViewHolder
    {
        RelativeLayout picLayout;
        ImageView itemPic;
        RelativeLayout picTextLayout;
        ImageView itemPicTextPic;
        TextView title;
        TextView titleContent;
    }
}
