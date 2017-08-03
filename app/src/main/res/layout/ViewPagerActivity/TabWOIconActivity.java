package com.example.jayantachowdhury.myapplication4.ViewPagerActivity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.jayantachowdhury.myapplication4.R;


public class TabWOIconActivity extends AppCompatActivity {

    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    //Fragments

    com.example.jayantachowdhury.myapplication4.Fragment.DosFragment dosFragment;
    com.example.jayantachowdhury.myapplication4.Fragment.DontsFragment dontsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_without_icon);
        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position,false);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);


    }





    private void setupViewPager(ViewPager viewPager)
    {
        com.example.jayantachowdhury.myapplication4.ViewPagerAdapter adapter = new com.example.jayantachowdhury.myapplication4.ViewPagerAdapter(getSupportFragmentManager());
        dosFragment=new com.example.jayantachowdhury.myapplication4.Fragment.DosFragment();
        dontsFragment=new com.example.jayantachowdhury.myapplication4.Fragment.DontsFragment();

        adapter.addFragment(dosFragment,"DO's");
        adapter.addFragment(dontsFragment,"DONT'S");
         viewPager.setAdapter(adapter);
    }

}
