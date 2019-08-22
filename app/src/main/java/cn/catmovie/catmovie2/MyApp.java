package cn.catmovie.catmovie2;

import android.app.Application;

import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;

/**
 * Created by 呆呆 on 2019/8/22.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        QMUISwipeBackActivityManager.init(this);
    }
}
