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

    private static final float DEFAULT_ICON_WIDTH = 24f;
    private static final float DEFAULT_ICON_HEIGHT = 24f;
    private static final float DEFAULT_TEXT_SIZE = 12f;
    private static final int DEFAULT_TEXT_COLOR = 0xff666666;
    private static final float DEFAULT_TEXT_TOPMARGIN = 3f;

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

    public void setResource(int iconResId, int textResId) {
        setResource(iconResId, mRes.getString(textResId));
    }

    public void setResource(int iconResId, String text) {
        mImageView.setImageResource(iconResId);
        mTextView.setText(text);
    }

    public void setImgResource(int resId) {
        mImageView.setImageResource(resId);
    }

    public void setText(int textResId) {
        setText(mRes.getString(textResId));
    }

    public void setText(String text) {
        mTextView.setText(text);
    }

    public void setTextSize(float size) {
        mTextView.setTextSize(size);
    }

    public void setTextColor(int color) {
        mTextView.setTextColor(color);
    }

    public void setTextMarginTop(int topMargin) {
        LayoutParams params = (LayoutParams) mTextView.getLayoutParams();
        params.topMargin = topMargin;
        mTextView.setLayoutParams(params);
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public TextView getTextView() {
        return mTextView;
    }

    private void init(Context context, AttributeSet attrs) {
        mRes = context.getResources();

        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);

        findView(context);
        initAttrs(context, attrs);
    }

    private void findView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_tab, this, true);
        mImageView = (ImageView) findViewById(R.id.iv_tab);
        mTextView = (TextView) findViewById(R.id.tv_tab);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TabView);

        int iconResId = a.getResourceId(R.styleable.TabView_tv_icon_src, -1);
        if (iconResId != -1)
            mImageView.setImageResource(iconResId);
        float iconWidth = a.getDimension(R.styleable.TabView_tv_icon_width, dp2px(DEFAULT_ICON_WIDTH));
        float iconHeight = a.getDimension(R.styleable.TabView_tv_icon_height, dp2px(DEFAULT_ICON_HEIGHT));
        LayoutParams imgParams = (LayoutParams) mImageView.getLayoutParams();
        if (iconWidth > 0)
            imgParams.width = (int) iconWidth;
        if (iconHeight > 0)
            imgParams.height = (int) iconHeight;
        mImageView.setLayoutParams(imgParams);

        String text = a.getString(R.styleable.TabView_tv_text);
        mTextView.setText(TextUtils.isEmpty(text) ? "" : text);

        mTextView.setTextSize(a.getDimension(R.styleable.TabView_tv_text_size, DEFAULT_TEXT_SIZE));
        mTextView.setTextColor(a.getColor(R.styleable.TabView_tv_text_color, DEFAULT_TEXT_COLOR));
        LayoutParams textParams = (LayoutParams) mTextView.getLayoutParams();
        textParams.topMargin = (int) a.getDimension(R.styleable.TabView_tv_text_marginTop, dp2px(DEFAULT_TEXT_TOPMARGIN));
        mTextView.setLayoutParams(textParams);

        a.recycle();
    }

    private int dp2px(float dp) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
