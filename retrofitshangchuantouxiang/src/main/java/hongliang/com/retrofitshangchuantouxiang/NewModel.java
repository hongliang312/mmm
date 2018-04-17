package hongliang.com.retrofitshangchuantouxiang;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;

/**
 * Created by 知足 on 2018/4/15.
 */

public class NewModel {
    private NewsPresenter presenter;

    public NewModel(NewsPresenter presenter) {
        this.presenter = presenter;
    }
    public void getData(String uid , MultipartBody.Part file){
        Flowable<MessageBean> flowable = RetrofitUtils.getInstance().getService().getMusicList(uid, file);
        presenter.getNews(flowable);
    }
}
