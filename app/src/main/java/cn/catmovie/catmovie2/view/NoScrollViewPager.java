package cn.catmovie.catmovie2.view;

import android.content.Context;
import android.util.AttributeSet;

import com.qmuiteam.qmui.widget.QMUIViewPager;

/**
 * Created by 呆呆 on 2019/8/24.
 */

public class NoScrollViewPager extends QMUIViewPager {
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void scrollTo(int x, int y) {
//        super.scrollTo(x, y);

    }
}
