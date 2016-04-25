package com.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chrischeng.bottomnavigation.BottomNavigation;
import com.chrischeng.bottomnavigation.CommonFragmentPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] botImgRes = {R.drawable.tab_1_normal, R.drawable.tab_2_normal, R.drawable.tab_3_normal};
        int[] topImgRes = {R.drawable.tab_1_check, R.drawable.tab_2_check, R.drawable.tab_3_check};
        int[] textRes = {R.string.tab1, R.string.tab2, R.string.tab3};
        int[] textColors = {Color.GRAY, getResources().getColor(R.color.theme)};

        BottomNavigation nav = (BottomNavigation) findViewById(R.id.bot_nav);
        CommonFragmentPagerAdapter adapter = new CommonFragmentPagerAdapter(this, getSupportFragmentManager());
        adapter.setResources(botImgRes, topImgRes, textRes, textColors);
        adapter.addFragments(TabFragment1.class, TabFragment2.class, TabFragment3.class);
        if (nav != null)
            nav.setAdapter(adapter);
    }
}
