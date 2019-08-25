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
    protected View onCreateView() {

        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, null);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        webView = getActivity().findViewById(R.id.wv);
        webView.loadUrl("http://www.catmovie.cn/");

        webView.setWebViewClient(new com.tencent.smtt.sdk.WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView webView, String s) {

                if (!TextUtils.isEmpty(s)) {


                    if (s.contains("/user/login.html")) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.putExtra("url", s);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getActivity(), LoadHtmlActivity.class);
                        intent.putExtra("url", s);
                        startActivity(intent);
                    }
                    return true;

                }
                return super.shouldOverrideUrlLoading(webView, s);
            }
        });

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
    public void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.destroy();
        }
    }
}
