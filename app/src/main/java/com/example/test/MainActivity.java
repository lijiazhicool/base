package com.example.test;

import com.example.test.base.BaseActivity;
import com.example.test.fragment.AlbumFragment;
import com.example.test.fragment.ArtistFragment;
import com.example.test.fragment.SongsFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiJiaZhi on 16/8/3.
 *
 * 主界面
 */
public class MainActivity extends BaseActivity {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private Adapter mAdapter;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_settings:
                    break;
                case R.id.action_shuffle:
                    break;
                case R.id.action_search:
                    break;
                case R.id.action_equalizer:
                    break;
            }
            Toast.makeText(MainActivity.this,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
            return true;
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initBundleExtra() {

    }

    @Override
    protected void findViewById() {
        mToolbar = findView(R.id.toolbar);
        // App Logo
        mToolbar.setLogo(R.mipmap.ic_launcher);
        // Title
        mToolbar.setTitle("Title");
        // Sub Title
        mToolbar.setSubtitle("Subtitle");
        setSupportActionBar(mToolbar);

        // Navigation Icon 要設定在 setSupoortActionBar 才有作用-----否則會出現 back button
        mToolbar.setNavigationIcon(R.drawable.ic_menu);
        mToolbar.setOnMenuItemClickListener(onMenuItemClick);
        // ActionBar ab = getSupportActionBar();
        // ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        // ab.setDisplayHomeAsUpEnabled(true);

        mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        if (mViewPager != null) {
            mAdapter = new Adapter(this, getSupportFragmentManager());
            mAdapter.addFragment(new SongsFragment(), this.getString(R.string.songs));
            mAdapter.addFragment(new AlbumFragment(), this.getString(R.string.albums));
            mAdapter.addFragment(new ArtistFragment(), this.getString(R.string.artists));
            mViewPager.setAdapter(mAdapter);
            mViewPager.setOffscreenPageLimit(2);
        }

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        // for (int i = 0; i < tabLayout.getTabCount(); i++) {
        // TabLayout.Tab tab = tabLayout.getTabAt(i);
        // tab.setCustomView(mAdapter.getTabView(i));
        // }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        private Context context;

        public Adapter(Context context, FragmentManager fm) {
            super(fm);
            this.context = context;
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }

        // public View getTabView(int position) {
        // View view = LayoutInflater.from(context).inflate(R.layout.item_custom_tableview, null);
        // TextView tv = (TextView) view.findViewById(R.id.item_smartlayout_tableview_tv);
        // tv.setText(mFragmentTitles.get(position));
        // return view;
        // }

    }
}
