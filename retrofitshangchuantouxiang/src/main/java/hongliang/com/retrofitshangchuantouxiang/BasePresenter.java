package hongliang.com.retrofitshangchuantouxiang;

import okhttp3.MultipartBody;

/**
 * Created by 知足 on 2018/4/15.
 */

public interface BasePresenter {

    void getData(String uid, MultipartBody.Part file);
}
