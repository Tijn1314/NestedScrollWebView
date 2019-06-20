package com.tijn.nestedscroll;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private List<Fragment> mFragment;
    List<String> titles;
    ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.tab_layout_main);


        titles = new ArrayList<>();
        titles.add("推荐1");
        titles.add("推荐推荐推荐2");
        titles.add("推荐推荐3");
        titles.add("推荐推荐推荐推荐4");
        titles.add("推荐推荐推荐推荐5");
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(3)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(3)));

        mFragment = new ArrayList<>();
        mFragment.add(new WebViewViewPagerFragment());
        mFragment.add(new WebViewViewPagerFragment());
        mFragment.add(new WebViewViewPagerFragment());
        mFragment.add(new WebViewViewPagerFragment());
        mFragment.add(new WebViewViewPagerFragment());
        mViewPager = findViewById(R.id.view_pager_view_pager);
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(), mFragment);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
        public List<Fragment> mList;

        public HomeFragmentPagerAdapter(FragmentManager fm, List<Fragment> mList) {
            super(fm);
            this.mList = mList;
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
