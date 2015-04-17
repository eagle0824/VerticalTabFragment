
package com.eagle.tabfragemnt;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.eagle.tabfragemnt.library.TabController;
import com.eagle.tabfragemnt.library.TabPageIndicator;
import com.eagle.tabfragemnt.library.TabController.OnTabChangeListener;

public class TabFragment extends FragmentActivity implements OnTabChangeListener
{
    private static final String TAG = TabFragment.class.getSimpleName();

    private TabController mTabController;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mTabController = new TabController(this);
        initTabs();
        if (!(savedInstanceState != null && mTabController
                .onRestoreInstanceState(savedInstanceState))) {
            mTabController.setCurrentTab(2);
        }
    }

    private void initTabs() {
        TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
        mTabController.setUp(getSupportFragmentManager(), R.id.container, indicator);
        mTabController.setOnTabChangeListener(this);
        mTabController.addStringTabItem("tab1", TestFragment.newInstance("tab1"));
        mTabController.addStringTabItem("tab2", TestFragment.newInstance("tab2"));
        mTabController.addStringTabItem("tab3", TestFragment.newInstance("tab3"));
        mTabController.addStringTabItem("tab4", TestFragment.newInstance("tab4"));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mTabController.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(mTabController.onSaveInstanceState(outState));
    }

    @Override
    public void onTabChanged(int index) {
        TabController.logd(TAG,
                "onTabChanged index : " + index + " current : " + mTabController.getCurrentTab());
    }

}