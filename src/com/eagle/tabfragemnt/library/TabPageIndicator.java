
package com.eagle.tabfragemnt.library;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class TabPageIndicator extends LinearLayout implements OnClickListener {

    private static final String TAG = "TabPageIndicator";
    private OnTabClickListener mOnTabClickListener;
    private int mIndex = -1;

    public TabPageIndicator(Context context) {
        this(context, null);
    }

    public TabPageIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabPageIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addItem(TabItem view) {
        int index = getChildCount();
        addItem(view, index);
    }

    public void addItem(TabItem view, int index) {
        view.setIndex(index);
        view.setOnClickListener(this);
        addView(view, index, getItemLayoutParams());
    }

    private LayoutParams getItemLayoutParams() {
        LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1);
        params.setMargins(0, 0, 0, 0);
        return params;
    }

    public int getCurrentTab() {
        return mIndex;
    }

    public Fragment getCurrentFragment() {
        View view = getChildAt(mIndex);
        if (view instanceof TabItem) {
            return ((TabItem) view).getFragment();
        }
        return null;
    }

    public void setCurrentTab(int index) {
        if (mIndex != index) {
            final int count = getChildCount();
            if (index < count) {
                mIndex = index;
                View view = getChildAt(index);
                if (view instanceof TabItem && mOnTabClickListener != null) {
                    updateTabState(count, index);
                    mOnTabClickListener.onTabClick(index, ((TabItem) view).getFragment());
                }
            }
        }
    }

    private void updateTabState(int count, int index) {
        for (int i = 0; i < count; i++) {
            getChildAt(i).setSelected(i == index ? true : false);
        }
    }

    @Override
    public void onClick(View view) {
        if (view instanceof TabItem && mOnTabClickListener != null) {
            TabItem item = (TabItem) view;
            int index = item.getIndex();
            setCurrentTab(index);
        }
    }

    public void setOnTabClickListener(OnTabClickListener listener) {
        mOnTabClickListener = listener;
    }

    public interface OnTabClickListener {
        public void onTabClick(int index, Fragment fragment);
    }
}
