package cn.catmovie.catmovie2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.catmovie.catmovie2.adapter.MainPagerAdapter;
import cn.catmovie.catmovie2.base.BaseActivity;
import cn.catmovie.catmovie2.base.BaseFragment;
import cn.catmovie.catmovie2.fragment.FindFragment;
import cn.catmovie.catmovie2.fragment.HomeFragment;
import cn.catmovie.catmovie2.fragment.LiveFragment;
import cn.catmovie.catmovie2.fragment.MeFragment;


public class MainActivity extends BaseActivity {

    @BindView(R.id.tab)
    FragmentTabHost tab;
    @BindView(R.id.fl)
    FrameLayout fl;

    String[] titles={"首页","直播","发现","我的"};
    int[] resId={R.drawable.bt_home_selector,R.drawable.bt_live_selector,R.drawable.bt_tab3_selector,R.drawable.bt_tab4_selector};
    List<Fragment> fragmentList;

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        fragmentList=new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new LiveFragment());
        fragmentList.add(new FindFragment());
        fragmentList.add(new MeFragment());
        tab.setup(this,getSupportFragmentManager(),R.id.fl);

        for (int i = 0; i < titles.length; i++) {
            View inflate = getLayoutInflater().from(this).inflate(R.layout.layout_tab, null);
            ImageView ivImg = inflate.findViewById(R.id.iv_img);
            TextView tvTab = inflate.findViewById(R.id.tv_tab);
            ivImg.setImageResource(resId[i]);
            tvTab.setText(titles[i]);
            tab.addTab(tab.newTabSpec(""+i).setIndicator(inflate),fragmentList.get(i).getClass(),null);
        }

    }


}
