package cn.catmovie.catmovie2;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUIViewPager;

import java.util.ArrayList;
import java.util.List;

import cn.catmovie.catmovie2.adapter.MainPagerAdapter;
import cn.catmovie.catmovie2.fragment.FindFragment;
import cn.catmovie.catmovie2.fragment.HomeFragment;
import cn.catmovie.catmovie2.fragment.LiveFragment;
import cn.catmovie.catmovie2.fragment.MeFragment;

public class MainActivity extends AppCompatActivity {
    QMUITabSegment tab;
    QMUIViewPager viewpager;
    String[] titles={"首页","直播","发现","我的"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tab=findViewById(R.id.tab);
        viewpager=findViewById(R.id.viewpager);
        tab.setupWithViewPager(viewpager,true);
        List<QMUIFragment> fragmentList=new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new LiveFragment());
        fragmentList.add(new FindFragment());
        fragmentList.add(new MeFragment());
        viewpager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(),titles,fragmentList));
    }
}
