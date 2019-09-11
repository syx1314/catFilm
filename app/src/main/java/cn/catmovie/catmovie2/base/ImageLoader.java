package cn.catmovie.catmovie2.base;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cn.catmovie.catmovie2.MyApp;


/**
 * Created by yyj on 2018/1/4.
 */

public class ImageLoader {

    public static void display(Context mContext,String url, ImageView imageView) {

        Glide.with(mContext).load(url).into(imageView);
    }


//    public static void displayCircleImage(Activity activity, String url, ImageView imageView) {
//        Glide.with(activity).load(url).transform(new GlideCircleTransform(activity)).into(imageView);
//    }
}
