package com.chrischeng.bottomnavigation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TabView extends LinearLayout {

    private static final float DEFAULT_ICON_WIDTH = 52f;
    private static final float DEFAULT_ICON_HEIGHT = 52f;

    private Resources mRes;
    private ImageView mImageView;
    private TextView mTextView;

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void setData(int iconResId, int textResId) {
        setData(iconResId, mRes.getString(textResId));
    }

    public void setData(int iconResId, String text) {
        mImageView.setImageResource(iconResId);
        mTextView.setText(text);
    }

    public void setText(int textResId) {
        setText(mRes.getString(textResId));
    }

    public void setText(String text) {
        mTextView.setText(text);
    }

    private void init(Context context, AttributeSet attrs) {
        mRes = context.getResources();

        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);

        findView(context);
        initAttrs(context, attrs);
    }

    private void findView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_tag, this, true);
        mImageView = (ImageView) findViewById(R.id.iv_tag);
        mTextView = (TextView) findViewById(R.id.tv_tag);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TagView);

        int iconResId = a.getResourceId(R.styleable.TagView_tv_icon_src, -1);
        if (iconResId != -1)
            mImageView.setImageResource(iconResId);
        float iconWidth = a.getDimension(R.styleable.TagView_tv_icon_width, dp2px(DEFAULT_ICON_WIDTH));
        float iconHeight = a.getDimension(R.styleable.TagView_tv_icon_height, dp2px(DEFAULT_ICON_HEIGHT));
        LayoutParams params = (LayoutParams) mImageView.getLayoutParams();
        if (iconWidth > 0)
            params.width = (int) iconWidth;
        if (iconHeight > 0)
            params.height = (int) iconHeight;
        mImageView.setLayoutParams(params);

        String text = a.getString(R.styleable.TagView_tv_text);
        mTextView.setText(TextUtils.isEmpty(text) ? "" : text);

        a.recycle();
    }

    private int dp2px(float dp) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
