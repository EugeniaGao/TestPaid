package com.jing.www.testpaid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.img_zhangdan)
    ImageView imgZhangdan;
    @BindView(R.id.img_zhangdan_txt)
    TextView imgZhangdanTxt;
    @BindView(R.id.jiahao)
    ImageView jiahao;
    @BindView(R.id.tongxunlu)
    ImageView tongxunlu;
    @BindView(R.id.img_shaomiao)
    ImageView imgShaomiao;
    @BindView(R.id.img_fukuang)
    ImageView imgFukuang;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.img_zhaoxiang)
    ImageView imgZhaoxiang;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.toolbar1)
    View toolbar1;
    @BindView(R.id.toolbar2)
    View toolbar2;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.activity_main)
    CoordinatorLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //recyclerView必须要添加管理器,来决定是瀑布流,线性,...
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapter());
        //给Appbar设置偏移改变的监听
        appBarLayout.addOnOffsetChangedListener(new MyOnOffsetChangedListener());

    }


    private class MyAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //造价数据
           View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 60;
        }

        private class MyViewHolder extends RecyclerView.ViewHolder {
            public MyViewHolder(View view) {
                super(view);
            }
        }
    }

    private class MyOnOffsetChangedListener implements AppBarLayout.OnOffsetChangedListener {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            //展开的程度改变来调用,完全展开状态是0,展开一部分是负的
            if(verticalOffset==0){
                //完全展开
                toolbar1.setVisibility(View.VISIBLE);
                toolbar2.setVisibility(View.GONE);
                setAlpha(255);

            }else if(Math.abs(verticalOffset)==appBarLayout.getTotalScrollRange()){
                //完全折叠
                toolbar1.setVisibility(View.GONE);
                toolbar2.setVisibility(View.VISIBLE);
                setAlpha2(255);
            }else{
                //过程,透明的变化
                //根据可见来设置
                if (toolbar1.getVisibility()==View.VISIBLE){
                    int alpha=100- Math.abs(verticalOffset);
                    setAlpha(alpha);
                } else if(toolbar2.getVisibility()==View.VISIBLE){
                    int alpha=(int) (255* Math.abs(verticalOffset)/100f);
                    setAlpha2(alpha);
                }

            }

        }
    }
        //toolBar1.因为要让里面的控件也要变透明
    public void setAlpha(int alpha){
    imgZhangdan.getDrawable().setAlpha(alpha);
        imgZhangdanTxt.setTextColor(Color.alpha(alpha));
        tongxunlu.getDrawable().setAlpha(alpha);
        jiahao.getDrawable().setAlpha(alpha);
    }
    //toolbar2
    public void setAlpha2(int alpha){
        imgShaomiao.getDrawable().setAlpha(alpha);
        imgFukuang.getDrawable().setAlpha(alpha);
        imgSearch.getDrawable().setAlpha(alpha);
        imgZhaoxiang.getDrawable().setAlpha(alpha);
    }
    public void show(View view){
        if(toolbar1.getVisibility() == View.VISIBLE){
            //折叠
            appBarLayout.setExpanded(false);
        }else{
            //展开
            appBarLayout.setExpanded(true);
        }
    }

}
