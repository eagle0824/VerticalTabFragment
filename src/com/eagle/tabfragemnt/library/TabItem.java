
package com.eagle.tabfragemnt.library;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eagle.tabfragemnt.R;
import com.eagle.tabfragemnt.R.drawable;
import com.eagle.tabfragemnt.R.id;
import com.eagle.tabfragemnt.R.layout;

public class TabItem extends LinearLayout {

    private int mIndex;
    private Fragment mFragment;
    private ImageView mIcon;
    private TextView mText;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.tab_indicator, this, true);
        mIcon = (ImageView) findViewById(R.id.icon);
        mText = (TextView) findViewById(R.id.title);
        setBackgroundResource(R.drawable.tab_indicator_bg);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
    }

    public void setIcon(int resId) {
        mIcon.setImageResource(resId);
    }

    public void setTitle(String text) {
        mText.setText(text);
    }

    public void setTitle(int stringId) {
        mText.setText(stringId);
    }

    public String getTitle() {
        return mText.getText().toString();
    }

    public void setFragment(Fragment fragment) {
        mFragment = fragment;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    public int getIndex() {
        return mIndex;
    }

    public static class ItemBuilder {
        private Context mContext;
        private String mTitle;
        private int mTitleId;
        private int mIconId;
        private Fragment mFragment;

        public ItemBuilder(Context context) {
            mContext = context;
        }

        public ItemBuilder setTitle(String titile) {
            mTitle = titile;
            return this;
        }

        public ItemBuilder setTitle(int resId) {
            mTitleId = resId;
            return this;
        }

        public ItemBuilder setFragment(Fragment fragment) {
            mFragment = fragment;
            return this;
        }

        public ItemBuilder setIcon(int resId) {
            mIconId = resId;
            return this;
        }

        public TabItem build() {
            TabItem item = new TabItem(mContext);
            if (!TextUtils.isEmpty(mTitle)) {
                item.setTitle(mTitle);
            }
            if (mTitleId > 0) {
                item.setTitle(mTitleId);
            }
            if (mIconId > 0) {
                item.setIcon(mIconId);
            }
            if (mFragment != null) {
                item.setFragment(mFragment);
            }
            return item;
        }
    }
}
