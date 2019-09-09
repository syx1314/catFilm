package cn.catmovie.catmovie2;


import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;


import com.lzy.okgo.OkGo;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import cn.catmovie.catmovie2.base.BaseActivity;
import cn.catmovie.catmovie2.view.X5WebView;

public class LoadHtmlActivity extends BaseActivity {

    @BindView(R.id.webView)
    X5WebView wb;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    public int layoutId() {
        return R.layout.activity_load_html;
    }

    @Override
    public void initData() {


        String url = getIntent().getStringExtra("url");
        setCookie(wb,url);


//        wb.loadUrl(url);
       wb.loadUrl(url);

        wb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView webView, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }
                super.onProgressChanged(webView, newProgress);

            }
        });

    }

    @Override
    protected void doOnBackPressed() {
        if (wb.canGoBack()) {
            wb.goBack();
        } else {
            super.doOnBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (wb != null) {
            wb.destroy();
        }
    }
}
