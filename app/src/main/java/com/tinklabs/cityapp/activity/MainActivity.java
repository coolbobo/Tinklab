package com.tinklabs.cityapp.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
 * 程序页面Activity类，用于显示页面
 */
public class MainActivity extends AppCompatActivity {

    public ContentServ conterServ = null;

    private PullToRefreshLayout pullToRefreshLayout;


    private ListView mList;
    private ContentCityGuideListAdapter contentListAdapter;
    private RelativeLayout cityGuideLayout,shopLayout,eatLayout;
    private TextView cityGuideTxt,shopTxt,eatTxt;
    private RelativeLayout cityGuideLine,shopLine,eatLine;
    public static Context mContext;
    public static int currContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        conterServ = ContentServ.getInstance();


        setContentView(R.layout.activity_main);
        mContext = this;
        init();


    }

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
        mList = (ListView) findViewById(R.id.list);
        contentListAdapter = new ContentCityGuideListAdapter();
        pullToRefreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 结束刷新
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


                        contentListAdapter.notifyDataSetChanged();
                        pullToRefreshLayout.finishRefresh();
                    }
                }, 2000);
            }

            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 结束加载更多
                        pullToRefreshLayout.finishLoadMore();
                    }
                }, 2000);
            }
        });


        //提前加载数据，显示时能快速显示出来。
        conterServ.getCityGuideData();
        conterServ.getEatData();
        conterServ.getShopData();

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

            }
        });

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
            }
        });

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
            }
        });
        cityGuideLayout.performClick();
        showData(CommonConsts.CITY_GUIDE);

    }

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

        mList.setAdapter(contentListAdapter);
        contentListAdapter.notifyDataSetChanged();


    }



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

    class CityGuideViewHolder
    {
        RelativeLayout picLayout;
        ImageView itemPic;

        RelativeLayout picTextLayout;
        /* 点赞 */
        ImageView itemPicTextPic;

        /* 姓名 */
        TextView title;
        /* 擅长 */
        TextView titleContent;
    }

}
