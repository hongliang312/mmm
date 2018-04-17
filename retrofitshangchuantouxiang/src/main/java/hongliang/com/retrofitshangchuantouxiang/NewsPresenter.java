package hongliang.com.retrofitshangchuantouxiang;

import android.util.Log;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.MultipartBody;

/**
 * Created by 知足 on 2018/4/15.
 */

public class NewsPresenter implements BasePresenter{
    private NewsView inv;
    private DisposableSubscriber<MessageBean> disposableSubscriber;

    public void attachView(NewsView inv){
        this.inv = inv;
    }

    public void detachView(){
        // 当Activity销毁的时候取消订阅时间，防止内存泄漏
        if (disposableSubscriber != null) {
            if (disposableSubscriber.isDisposed()) {
                disposableSubscriber.dispose();
            }
        }
        if (inv!=null){
            inv = null;
        }
    }
    @Override
    public void getData(String uid, MultipartBody.Part file) {
        NewModel newModel = new NewModel(this);
        newModel.getData(uid,file);
    }
    public void getNews(Flowable<MessageBean> flowable) {
        disposableSubscriber = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<MessageBean>() {
                    @Override
                    public void onNext(MessageBean messageBean) {
                        if(messageBean!=null){
                            inv.onSuccess(messageBean);

                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("zxz",t.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}