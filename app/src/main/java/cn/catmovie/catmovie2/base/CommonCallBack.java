package cn.catmovie.catmovie2.base;

import com.google.gson.Gson;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.lzy.okgo.utils.OkLogger;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by 呆呆 on 2019/8/24.
 */

public class CommonCallBack<T> extends AbsCallback<T> {

    private Type type;
    private Class<T> clazz;
    public CommonCallBack(Type type) {
        this.type = type;
    }

    public CommonCallBack(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void onSuccess(Response<T> response) {

    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {




        if(response!=null&&response.body()!=null){
            Gson gson=new Gson();

            String json = response.body().string();
            if (type == null) {
                if (clazz == null) {
                    Type genType = getClass().getGenericSuperclass();
                    type = ((ParameterizedType) genType).getActualTypeArguments()[0];
                } else {
                    return gson.fromJson(json,clazz);
                }
            }else {
              return   gson.fromJson(json,type);
            }
        }
        return null;
    }

    @Override
    public void onError(Response<T> response) {
        super.onError(response);
        Throwable exception = response.getException();
        OkLogger.e("程序产生异常"+exception.toString());

    }
}
