package cn.catmovie.catmovie2.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.List;

/**
 * Created by yyj on 2017/12/29.
 */

public class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    public List<T> mdatas;
    private int resLayoutId;
    private OnItemClickListener onItemClickListener;
    public Context context;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public BaseRecycleViewAdapter(List<T> mdatas, @LayoutRes int resLayoutId) {
        this.mdatas = mdatas;
        this.resLayoutId = resLayoutId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (parent.getContext() != null) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(resLayoutId, parent, false);
             context=parent.getContext();
            return new ViewHolder(inflate);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if (holder == null) {
            return;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdatas != null ? mdatas.size() : 0;
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void refreshData() {
        notifyDataSetChanged();
    }
}
