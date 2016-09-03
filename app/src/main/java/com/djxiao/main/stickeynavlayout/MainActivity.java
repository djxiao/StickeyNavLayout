package com.djxiao.main.stickeynavlayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.djxiao.main.stickeynavlayout.views.IndicatorView;
import com.djxiao.main.stickeynavlayout.views.StickeyNavLayout;

public class MainActivity extends AppCompatActivity {

    private String[] mTitles = new String[] { "简介", "评价", "相关" };
    private ViewPager viewPager;
    private IndicatorView indicatorView;
    private FragmentPagerAdapter adapter;

    private TabFragment[] fragments = new TabFragment[mTitles.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerComponent();

    }

    private void registerComponent(){

        indicatorView = (IndicatorView) findViewById(R.id.id_nav_view);
        viewPager = (ViewPager) findViewById(R.id.id_view_pager);

        indicatorView.setTitles(mTitles);
        for(int i = 0;i<mTitles.length;i++){
            fragments[i] = TabFragment.newInstance(mTitles[i]);
        }
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }
        };
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicatorView.scoll(position,positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
}
