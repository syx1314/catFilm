package cn.catmovie.catmovie2.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qmuiteam.qmui.arch.QMUIFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.catmovie.catmovie2.R;

/**
 * Created by 呆呆 on 2019/8/24.
 */

public abstract class BaseFragment extends QMUIFragment {


    private Unbinder bind;

    public  abstract int layoutResId();



    @Override
    protected View onCreateView() {
        View inflate = LayoutInflater.from(getContext()).inflate(layoutResId(), null);
         bind = ButterKnife.bind(this,inflate);
        return inflate;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(bind!=null){
            bind.unbind();
        }
    }
}
