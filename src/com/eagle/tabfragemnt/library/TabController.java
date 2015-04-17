
package com.eagle.tabfragemnt.library;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.eagle.tabfragemnt.library.TabPageIndicator.OnTabClickListener;

public class TabController implements OnTabClickListener {

    private static final String TAG = TabController.class.getSimpleName();
    private static final String KEY_TAB = "tab";
    private static final boolean DEBUG = true;

    private FragmentManager mFragmentManager;
    private int mContainerId;
    private OnTabChangeListener mOnTabChangeListener;
    private TabPageIndicator mTabPageIndicator;
    private Context mContext;

    public TabController(Context context) {
        mContext = context;
    }

    public void setUp(FragmentManager fManager, int containerId, TabPageIndicator tabIndicator) {
        mFragmentManager = fManager;
        mContainerId = containerId;
        mTabPageIndicator = tabIndicator;
        mTabPageIndicator.setOnTabClickListener(this);
    }

    public void setCurrentTab(int index) {
        mTabPageIndicator.setCurrentTab(index);
    }

    private void addItem(TabItem item) {
        mTabPageIndicator.addItem(item);
    }

    public void showFragement(Fragment fragment) {
        mFragmentManager.beginTransaction()
                .replace(mContainerId, fragment)
                .commit();
    }

    public int getCurrentTab() {
        return mTabPageIndicator.getCurrentTab();
    }

    public Fragment getCurrentFragment() {
        return mTabPageIndicator.getCurrentFragment();
    }

    public void setOnTabChangeListener(OnTabChangeListener listener) {
        mOnTabChangeListener = listener;
    }

    public void addStringTabItem(String title, Fragment fragment) {
        addTabItem(title, -1, fragment);
    }

    public void addStringTabItem(int titileId, Fragment fragment) {
        addTabItem(titileId, -1, fragment);
    }

    public void addIconTabItem(int iconId, Fragment fragment) {
        addTabItem(-1, iconId, fragment);
    }

    public void addTabItem(String title, int iconId, Fragment fragment) {
        addTabItem(title, -1, iconId, fragment);
    }

    public void addTabItem(int titileId, int iconId, Fragment fragment) {
        addTabItem(null, titileId, iconId, fragment);
    }

    public void addTabItem(String title, int titileId, int iconId, Fragment fragment) {
        TabItem item = new TabItem.ItemBuilder(mContext)
                .setFragment(fragment)
                .setIcon(iconId)
                .setTitle(titileId)
                .setTitle(title)
                .build();
        addItem(item);
    }

    @Override
    public void onTabClick(int index, Fragment fragment) {
        if (fragment != null) {
            showFragement(fragment);
        }
        if (mOnTabChangeListener != null) {
            mOnTabChangeListener.onTabChanged(index);
        }
    }

    public boolean onRestoreInstanceState(Bundle bundle) {
        if (bundle.containsKey(KEY_TAB)) {
            int tab = bundle.getInt(KEY_TAB);
            setCurrentTab(tab);
            return true;
        }
        return false;
    }

    public Bundle onSaveInstanceState(Bundle bundle) {
        bundle.putInt(KEY_TAB, getCurrentTab());
        return bundle;
    }

    public interface OnTabChangeListener {
        public void onTabChanged(int index);
    }

    public static void logd(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }
}
