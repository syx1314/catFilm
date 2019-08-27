package cn.catmovie.catmovie2;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qmuiteam.qmui.arch.QMUIActivity;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUIViewPager;

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
import cn.catmovie.catmovie2.view.NoScrollViewPager;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tab)
    QMUITabSegment tab;
    @BindView(R.id.viewpager)
    QMUIViewPager viewpager;
    String[] titles={"首页","直播","发现","我的"};


    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        tab.setupWithViewPager(viewpager,true);


        List<BaseFragment> fragmentList=new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new LiveFragment());
        fragmentList.add(new FindFragment());
        fragmentList.add(new MeFragment());
        viewpager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(),titles,fragmentList));
        tab.setupWithViewPager(viewpager);

    }


}
