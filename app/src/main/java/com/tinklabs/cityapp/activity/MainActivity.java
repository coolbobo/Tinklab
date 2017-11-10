package com.tinklabs.cityapp.activity;

import android.content.Context;
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

import com.tinklabs.cityapp.R;
import com.tinklabs.cityapp.comm.CommonConsts;
import com.tinklabs.cityapp.model.ContentModel;
import com.tinklabs.cityapp.service.ContentServ;
import com.tinklabs.cityapp.thirdLib.CircleRefreshLayout;

import java.util.ArrayList;

/**
 * 程序页面Activity类，用于显示页面
 */
public class MainActivity extends AppCompatActivity {

    public ContentServ conterServ = null;

    private CircleRefreshLayout mRefreshLayout;
    private ListView mList;
    private ContentCityGuideListAdapter contentListAdapter;
    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        conterServ = ContentServ.getInstance();


        setContentView(R.layout.activity_main);
        mContext = this;

        //提前加载数据，显示时能快速显示出来。
        conterServ.getCityGuideData();
        conterServ.getEatData();
        conterServ.getShopData();
        showData();


    }

    private void showData()
    {
        mRefreshLayout = (CircleRefreshLayout) findViewById(R.id.refresh_layout);
        mList = (ListView) findViewById(R.id.list);
        contentListAdapter = new ContentCityGuideListAdapter();

        contentListAdapter.setData(conterServ.cityGuideList);
        mList.setAdapter(contentListAdapter);
        mRefreshLayout.setOnRefreshListener(
                new CircleRefreshLayout.OnCircleRefreshListener() {
                    @Override
                    public void refreshing() {
                        conterServ.getCityGuideData();

                    }
                    @Override
                    public void completeRefresh() {
                        mRefreshLayout.finishRefreshing();
                        //通知数据更新
                        contentListAdapter.notifyDataSetChanged();
                    }
                });
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
