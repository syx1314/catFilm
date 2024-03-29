package cn.catmovie.catmovie2.base;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



/**
 * Created by yyj on 2017/12/29.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;

    public ViewHolder(View itemView) {
        super(itemView);
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }

    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        if (tv != null && Validator.isNotEmpty(text)) {
            tv.setText(text);
        }
        return this;
    }
    public ViewHolder setTextHtml(int viewId, String text) {
        TextView tv = getView(viewId);
        if (tv != null && Validator.isNotEmpty(text)) {
            tv.setText(Html.fromHtml(text));
        }
        return this;
    }

    public  ViewHolder visable(int viewId,int visable){
        View view = getView(viewId);
        view.setVisibility(visable);
        return this;
    }

    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        if (view != null) {
            view.setImageResource(resId);
        }
        return this;
    }
    public ViewHolder setImage(Context context,int viewId, String url) {
        ImageView view = getView(viewId);
        if (view != null) {
            ImageLoader.display(context,url,view);
        }
        return this;
    }


    public ViewHolder setOnClickListener(int viewId,
                                         View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }


    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }


}
