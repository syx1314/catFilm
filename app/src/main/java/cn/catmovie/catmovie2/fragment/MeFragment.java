package cn.catmovie.catmovie2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.catmovie.catmovie2.LoginActivity;
import cn.catmovie.catmovie2.R;
import cn.catmovie.catmovie2.base.BaseFragment;
import cn.catmovie.catmovie2.base.BaseUrl;
import cn.catmovie.catmovie2.bean.ApiResult;
import cn.catmovie.catmovie2.bean.UserGroup;
import cn.catmovie.catmovie2.bean.UserInfo;
import cn.catmovie.catmovie2.callback.JsonCallback;

/**
 * Created by 呆呆 on 2019/8/22.
 */

public class MeFragment extends BaseFragment {


    @BindView(R.id.iv_head)
    QMUIRadiusImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_vip_end)
    TextView tvVipEnd;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_vip)
    TextView tvVip;
    @BindView(R.id.tv_login_num)
    TextView tvLoginNum;

    @Override
    public int layoutResId() {
        return R.layout.fragment_me;
    }


    @OnClick({R.id.tv_main_page, R.id.tv_play_record, R.id.tv_open_vip, R.id.tv_invite_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_main_page:
                break;
            case R.id.tv_play_record:
                break;
            case R.id.tv_open_vip:
                break;
            case R.id.tv_invite_record:
                break;
            case R.id.btn_exit:

                DBCookieStore dbCookieStore = new DBCookieStore(getActivity());
                dbCookieStore.removeAllCookie();
                startActivity(new Intent(getActivity(),LoginActivity.class));
                break;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            OkGo.<ApiResult<UserInfo>>post(BaseUrl.URL+"/index.php/app/userinfo")
                    .execute(new JsonCallback<ApiResult<UserInfo>>() {
                        @Override
                        public void onSuccess(Response<ApiResult<UserInfo>> response) {
                            ApiResult<UserInfo> body = response.body();
                            UserInfo userInfo = body.getData();
                            if (userInfo!=null){
                                if (tvName!=null){
                                    tvName.setText(userInfo.getUser_name());
                                    tvScore.setText(userInfo.getUser_points());
                                    tvLoginNum.setText(userInfo.getUser_login_num());
                                    if (!TextUtils.isEmpty(userInfo.getUser_end_time())){
                                        Date date=new Date(Long.valueOf(userInfo.getUser_end_time()));
                                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                                        String format = sdf.format(date);
                                        tvVipEnd.setText("VIP到期时间:"+format);
                                    }

                                }
                                UserGroup userGroup = userInfo.getGroup();
                                if (userGroup!=null){
                                    tvVip.setText(userGroup.getGroup_name());
                                }
                            }else {

                                startActivity(new Intent(getActivity(), LoginActivity.class));
                            }
                        }
                    });
        }
    }
}
