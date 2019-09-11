package cn.catmovie.catmovie2.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.tencent.smtt.sdk.TbsVideo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.catmovie.catmovie2.R;
import cn.catmovie.catmovie2.base.BaseFragment;
import cn.catmovie.catmovie2.base.BaseRecycleViewAdapter;
import cn.catmovie.catmovie2.base.BaseUrl;
import cn.catmovie.catmovie2.base.ViewHolder;
import cn.catmovie.catmovie2.bean.ApiResult;
import cn.catmovie.catmovie2.bean.TvBean;
import cn.catmovie.catmovie2.bean.TvChanel;
import cn.catmovie.catmovie2.callback.JsonCallback;

/**
 * Created by 呆呆 on 2019/8/22.
 */

public class LiveFragment extends BaseFragment {


    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.rv)
    RecyclerView rv;

    List<TvChanel> tvChanelList;
    private TvBean tvBean;

    @Override
    public int layoutResId() {
        return R.layout.fragment_live;
    }

    @Override
    public void init() {
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        tvChanelList = new ArrayList<>();
        rv.setAdapter(new BaseRecycleViewAdapter<TvChanel>(tvChanelList, R.layout.layout_chanel) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                final TvChanel tvChanel = mdatas.get(position);
                holder.setImage(context,R.id.iv_img, BaseUrl.URL+"/"+tvChanel.getPic());
                holder.setText(R.id.tv_tab, tvChanel.getName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle=new Bundle();
                        bundle.putInt("screenMode", 102);
                        TbsVideo.openVideo(context,tvChanel.getUrl(),bundle);
                    }
                });
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                tvChanelList.clear();
                if (position==0){
                    if (tvBean!=null&&tvBean.getYs()!=null) {
                        tvChanelList.addAll(tvBean.getYs());
                    }
                }else {
                    if (tvBean!=null&&tvBean.getWs()!=null) {
                        tvChanelList.addAll(tvBean.getWs());
                    }
                }
                rv.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void loadData() {
        tabLayout.removeAllTabs();
        super.loadData();
        OkGo.<ApiResult<TvBean>>post(BaseUrl.URL + "/index.php/api/zhibo")
                .execute(new JsonCallback<ApiResult<TvBean>>() {
                    @Override
                    public void onSuccess(Response<ApiResult<TvBean>> response) {
                        if (response != null) {
                            ApiResult<TvBean> body = response.body();
                            if (body != null) {
                                 tvBean = body.getData();
                                if (tvBean.getYs() != null && tvBean.getYs().size() > 0) {
                                    tabLayout.addTab(tabLayout.newTab().setText("央视"));
                                    if (tvBean.getWs() != null) {
                                        tvChanelList.addAll(tvBean.getYs());
                                        rv.getAdapter().notifyDataSetChanged();
                                    }
                                }
                                if (tvBean.getYs() != null && tvBean.getWs().size() > 0) {
                                    tabLayout.addTab(tabLayout.newTab().setText("卫视"));
                                }
                            }
                        }
                    }
                });
    }


}
