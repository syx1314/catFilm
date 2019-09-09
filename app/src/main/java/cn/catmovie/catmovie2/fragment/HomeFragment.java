package cn.catmovie.catmovie2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.widget.webview.QMUIWebView;
import com.qmuiteam.qmui.widget.webview.QMUIWebViewClient;

import java.time.Instant;

import cn.catmovie.catmovie2.LoadHtmlActivity;
import cn.catmovie.catmovie2.LoginActivity;
import cn.catmovie.catmovie2.R;
import cn.catmovie.catmovie2.base.BaseFragment;
import cn.catmovie.catmovie2.view.X5WebView;

/**
 * Created by 呆呆 on 2019/8/22.
 */

public class HomeFragment extends BaseFragment {
    X5WebView webView;



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        webView = getActivity().findViewById(R.id.wv);
        webView.loadUrl("http://www.catmovie.cn/");
//        webView.loadUrl("http://debugtbs.qq.com");

    }

    @Override
    protected void onBackPressed() {

        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public int layoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.destroy();
        }
    }
}
