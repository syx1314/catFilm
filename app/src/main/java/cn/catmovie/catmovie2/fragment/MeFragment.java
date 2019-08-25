package cn.catmovie.catmovie2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.widget.webview.QMUIWebView;
import com.qmuiteam.qmui.widget.webview.QMUIWebViewClient;

import cn.catmovie.catmovie2.R;
import cn.catmovie.catmovie2.base.BaseFragment;

/**
 * Created by 呆呆 on 2019/8/22.
 */

public class MeFragment extends BaseFragment {

    @Override
    protected View onCreateView() {

        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_me,null);
    }

}
