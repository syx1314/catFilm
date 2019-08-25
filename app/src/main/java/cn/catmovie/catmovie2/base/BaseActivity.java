package cn.catmovie.catmovie2.base;

import android.os.Build;
import android.os.Bundle;

import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.utils.OkLogger;
import com.qmuiteam.qmui.arch.QMUIActivity;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import butterknife.ButterKnife;
import cn.catmovie.catmovie2.LoginActivity;
import cn.catmovie.catmovie2.view.X5WebView;
import okhttp3.Cookie;

/**
 * Created by 呆呆 on 2019/8/24.
 */

public abstract class BaseActivity extends QMUIActivity {
    // Cookie: PHPSESSID=ja3899oejle5iffmv93v1p8f76; admin_id=1; admin_name=syx1314; admin_check=92005b59d22c50d680aa4320515e8205; UM_distinctid=16cc3d16172226-0f152274cb80b2-3c604504-1fa400-16cc3d16173138; appdw2=the_value; CNZZDATA1275136140=351332024-1566652594-http%253A%252F%252Fcatmovie.cn%252F%7C1566658731; user_id=1; user_name=syx1314; group_id=3; group_name=VIP%E4%BC%9A%E5%91%98; user_check=cd66e3dea1280cd097db4dc68c64bf96; user_portrait=%2Fstatic%2Fimages%2Ftouxiang.png


    private String ck;
    List<Cookie> allCookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        ButterKnife.bind(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        initData();
    }

    public abstract int layoutId();

    public abstract void initData();

    public void getCookie() {
        StringBuffer sbf = new StringBuffer();
        DBCookieStore dbCookieStore = new DBCookieStore(this);
        allCookie  = dbCookieStore.getAllCookie();

    }



    public void clearCookie(){
        new DBCookieStore(this).removeAllCookie();
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            cookieManager.removeSessionCookies(null);
            cookieManager.flush();
        } else {
            cookieManager.removeSessionCookie();
            CookieSyncManager.getInstance().sync();
        }
    }
    public void setCookie(WebView webView, String url) {

        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(webView, true);
            cookieManager.removeSessionCookies(null);
        } else {
            cookieManager.removeSessionCookie();
        }
        if (allCookie==null){
          getCookie();
        }
        if(allCookie!=null){
            for (Cookie cookie:allCookie){
                cookieManager.setCookie(url, cookie.name()+"="+cookie.value());
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.flush();
        } else {
            CookieSyncManager.getInstance().sync();
        }
    }
}
