package cn.catmovie.catmovie2.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.arch.QMUIFragmentPagerAdapter;
import com.qmuiteam.qmui.widget.QMUIPagerAdapter;

import java.util.List;

import cn.catmovie.catmovie2.base.BaseFragment;

/**
 * Created by 呆呆 on 2019/8/22.
 */

public class MainPagerAdapter extends QMUIFragmentPagerAdapter {


    private String[] titles;
    List<BaseFragment> fragmentList;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public QMUIFragment createFragment(int position) {
        return fragmentList.get(position);
    }

    public MainPagerAdapter(FragmentManager fm,String[] titles, List<BaseFragment> fragmentList) {
        super(fm);
        this.titles=titles;
        this.fragmentList=fragmentList;
    }



    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }

}
