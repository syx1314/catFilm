package cn.catmovie.catmovie2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.widget.webview.QMUIWebView;
import com.qmuiteam.qmui.widget.webview.QMUIWebViewClient;

import cn.catmovie.catmovie2.R;

/**
 * Created by 呆呆 on 2019/8/22.
 */

public class HomeFragment extends QMUIFragment {
    QMUIWebView webView;
    @Override
    protected View onCreateView() {

        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_home,null);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        webView=getActivity().findViewById(R.id.wv);
        webView.loadUrl("http://catmovie.cn/");

        webView.setWebViewClient(new QMUIWebViewClient(true,true){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
