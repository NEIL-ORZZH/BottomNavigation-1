package com.chrischeng.bottomnavigation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.chrischeng.gradienttabview.GradientImageView;
import com.chrischeng.gradienttabview.GradientTextView;

public class GradientTabView extends LinearLayout {

    private GradientImageView mImageView;
    private GradientTextView mTextView;

    public GradientTabView(Context context) {
        this(context, null);
    }

    public GradientTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public void setImageResources(@DrawableRes int botResId, @DrawableRes int topResId) {
        mImageView.setBotResource(botResId);
        mImageView.setTopResource(topResId);
    }

    public void setImageDrawable(@Nullable Drawable botDrawable, @Nullable Drawable topDrawable) {
        mImageView.setBotDrawable(botDrawable);
        mImageView.setTopDrawable(topDrawable);
    }

    public void setText(@StringRes int resId) {
        mTextView.setText(resId);
    }

    public void setText(String text) {
        mTextView.setText(text);
    }

    public void setTextSize(float size) {
        mTextView.setTextSize(size);
    }

    public void setTextColors(int botColor, int topColor) {
        setBotTextColor(botColor);
        setTopTextColor(topColor);
    }

    public void setBotTextColor(int color) {
        mTextView.setBotTextColor(color);
    }

    public void setTopTextColor(int color) {
        mTextView.setTopTextColor(color);
    }

    public void setTextMarginTop(int topMargin) {
        LayoutParams params = (LayoutParams) mTextView.getLayoutParams();
        params.topMargin = topMargin;
        mTextView.setLayoutParams(params);
    }

    public GradientImageView getImageView() {
        return mImageView;
    }

    public GradientTextView getTextView() {
        return mTextView;
    }

    public void highlight() {
        mImageView.setAlpha(1f);
        mTextView.setAlpha(1f);
    }

    public void reset() {
        mImageView.setAlpha(0);
        mTextView.setAlpha(0);
    }

    private void init(AttributeSet attrs) {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);

        findView();
        initAttrs(attrs);
    }

    private void findView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_tab, this, true);
        mImageView = (GradientImageView) findViewById(R.id.iv_tab);
        mTextView = (GradientTextView) findViewById(R.id.tv_tab);
    }

    private void initAttrs(AttributeSet attrs) {
        Resources res = getContext().getResources();

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.GradientTabView);

        mImageView.setBotDrawable(a.getDrawable(R.styleable.GradientTabView_gt_icon_bot_src));
        mImageView.setTopDrawable(a.getDrawable(R.styleable.GradientTabView_gt_icon_top_src));

        float iconWidth = a.getDimension(R.styleable.GradientTabView_gt_icon_width, 0);
        float iconHeight = a.getDimension(R.styleable.GradientTabView_gt_icon_height, 0);
        LayoutParams imgParams = (LayoutParams) mImageView.getLayoutParams();
        if (iconWidth > 0)
            imgParams.width = (int) iconWidth;
        if (iconHeight > 0)
            imgParams.height = (int) iconHeight;
        mImageView.setLayoutParams(imgParams);

        String text = a.getString(R.styleable.GradientTabView_gt_text);
        mTextView.setText(TextUtils.isEmpty(text) ? "" : text);

        mTextView.setTextSize(a.getDimension(R.styleable.GradientTabView_gt_text_size,
                res.getInteger(R.integer.default_tab_text_size)));
        mTextView.setBotTextColor(a.getColor(R.styleable.GradientTabView_gt_text_bot_color,
                res.getColor(R.color.default_tab_text_color)));
        mTextView.setTopTextColor(a.getColor(R.styleable.GradientTabView_gt_text_top_color,
                res.getColor(R.color.default_tab_text_color)));

        ((LayoutParams) mTextView.getLayoutParams()).topMargin = (int) a.getDimension(R.styleable.GradientTabView_gt_text_marginTop,
                res.getDimension(R.dimen.default_tab_text_top_margin));

        a.recycle();
    }
}
