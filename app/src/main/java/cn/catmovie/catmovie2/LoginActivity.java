package cn.catmovie.catmovie2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.lzy.okgo.utils.OkLogger;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.OnClick;
import cn.catmovie.catmovie2.base.BaseActivity;
import cn.catmovie.catmovie2.base.BaseUrl;
import cn.catmovie.catmovie2.base.CommonCallBack;
import cn.catmovie.catmovie2.bean.ApiResult;
import cn.catmovie.catmovie2.callback.JsonCallback;
import okhttp3.Cookie;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    @Override
    public int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {

        //清空ck
        clearCookie();

    }

    @OnClick(R.id.btn_login)
    public void login() {
        OkGo.<ApiResult>post(BaseUrl.URL + "/index.php/user/login.html")
                .params("user_name", etAccount.getText().toString())
                .params("user_pwd", etPwd.getText().toString())
                .execute(new JsonCallback<ApiResult>() {
                    @Override
                    public void onSuccess(Response<ApiResult> response) {
                        ApiResult body = response.body();
                        if (body != null) {
                            if ("1".equals(body.getCode())) {
                                //登录成功
                                finish();
                            } else {
                                new QMUIDialog.MessageDialogBuilder(LoginActivity.this).setMessage(body.getMsg()).show();
                            }

                        }
                    }
                });
    }

    @OnClick(R.id.btn_reg)
    public void reg() {

    }
}
