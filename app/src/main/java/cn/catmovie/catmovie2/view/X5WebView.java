package cn.catmovie.catmovie2.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.utils.OkLogger;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import cn.catmovie.catmovie2.LoadHtmlActivity;
import cn.catmovie.catmovie2.LoginActivity;

public class X5WebView extends WebView {
	TextView title;
	private WebViewClient client = new WebViewClient() {
		@Override
		public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
			String url= webResourceRequest.getUrl().toString();
//			setCookie(webView,url);
			return super.shouldInterceptRequest(webView, webResourceRequest);
		}

		/**
		 * 防止加载网页时调起系统浏览器
		 */
		public boolean shouldOverrideUrlLoading(WebView view, String url) {

			OkLogger.e("当前加载页面----"+url);
			if(!TextUtils.isEmpty(url)){
				if (url.contains("/user/login.html")) {
					Intent intent = new Intent(getContext(), LoginActivity.class);
					intent.putExtra("url", url);
					getContext().startActivity(intent);
				}
			}
			if (!TextUtils.isEmpty(url)) {


				if (url.contains("/user/login.html")) {
					Intent intent = new Intent(getContext(), LoginActivity.class);
					intent.putExtra("url", url);
					getContext().startActivity(intent);
				}else if(url.contains("vod/type/")||url.endsWith("catmovie.cn/")){
					view.loadUrl(url);
				} else {
					Intent intent = new Intent(getContext(), LoadHtmlActivity.class);
					intent.putExtra("url", url);
					getContext().startActivity(intent);
				}
				return true;

			}

			return true;

		}

		@Override
		public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
			super.onPageStarted(webView, s, bitmap);
			webSetting.setBlockNetworkImage(true);
		}

		@Override
		public void onPageFinished(WebView webView, String s) {
			super.onPageFinished(webView, s);
			webSetting.setBlockNetworkImage(false);
			String js = "javascript:(function() {document.getElementById(\'header_sort\').style.display=\'none\';" + "})()";



			//定义javaScript方法
			String javascript = "javascript:function hideBottom() { "
					+ "document.getElementById('header_sort').style.display='none'"
					+ "}";

			//加载方法
			webView.loadUrl(javascript);
			//执行方法
			webView.loadUrl("javascript:hideBottom();");

//			webView.loadUrl(js);

		}
	};

	private WebChromeClient webChromeClient=new WebChromeClient(){
		@Override
		public void onProgressChanged(WebView webView, int i) {
			super.onProgressChanged(webView, i);
		}

	};
	private WebSettings webSetting;

	@SuppressLint("SetJavaScriptEnabled")
	public X5WebView(Context arg0, AttributeSet arg1) {
		super(arg0, arg1);
		this.setWebViewClient(client);
		this.setWebChromeClient(webChromeClient);
		// this.setWebChromeClient(chromeClient);
		// WebStorage webStorage = WebStorage.getInstance();
		initWebViewSettings();
		this.getView().setClickable(true);
	}

	private void initWebViewSettings() {
		 webSetting = this.getSettings();
		webSetting.setJavaScriptEnabled(true);
		webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
		webSetting.setAllowFileAccess(true);
		webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
		webSetting.setSupportZoom(true);
		webSetting.setBuiltInZoomControls(true);
		webSetting.setUseWideViewPort(true);
		webSetting.setSupportMultipleWindows(true);
		// webSetting.setLoadWithOverviewMode(true);
		webSetting.setAppCacheEnabled(true);
		// webSetting.setDatabaseEnabled(true);
		webSetting.setDomStorageEnabled(true);
		webSetting.setGeolocationEnabled(true);
		webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
		// webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
		webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
		// webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
//		ConnectivityManager cm = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo info = cm.getActiveNetworkInfo();
//			webSetting.setCacheMode(WebSettings.LOAD_NORMAL);//不使用网络，只加载缓存

//		webSetting.setBlockNetworkImage(true);
//		webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);//设置渲染的优先级
		// this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
		// settings 的设计
	}



}
