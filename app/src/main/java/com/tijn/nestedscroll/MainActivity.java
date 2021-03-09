package com.tijn.nestedscroll;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.tijn.nestedscroll.listener.AppBarStateChangeListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    List<String> titles;
    ViewPager mViewPager;
    private AppBarLayout appBarLayout;
    NativeViewPagerFragment nativeViewPagerFragment;
    private int position;
    private boolean isEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.tab_layout_main);
        appBarLayout = findViewById(R.id.app_bar);


        titles = new ArrayList<>();
        titles.add("推荐1");
        titles.add("推荐2");
        titles.add("推荐推荐3");
        titles.add("推推荐4");
        titles.add("荐推荐5");
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(3)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(3)));

        mViewPager = findViewById(R.id.view_pager_view_pager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                position = i;
                nativeViewPagerFragment.setPtrFrameEnabled(isEnabled);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        appBarLayout.addOnOffsetChangedListener(mAppBarStateChangeListener);
    }

    AppBarStateChangeListener mAppBarStateChangeListener = new AppBarStateChangeListener() {
        @Override
        public void onStateChanged(AppBarLayout appBarLayout, State state) {

        }

        @Override
        public void onOffsetChange(AppBarLayout appBarLayout, int verticalOffset) {
            //滚动下拉问题
            if (verticalOffset >= 0) {
                isEnabled = true;
            } else {
                isEnabled = false;
            }
            if (position == 0) {
                nativeViewPagerFragment.setPtrFrameEnabled(isEnabled);
            }
        }
    };

    public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

        public HomeFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                nativeViewPagerFragment = NativeViewPagerFragment.create();
                return nativeViewPagerFragment;
            }
            return new WebViewViewPagerFragment();
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
