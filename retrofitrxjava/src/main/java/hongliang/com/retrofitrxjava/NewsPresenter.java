package hongliang.com.retrofitrxjava;

import java.util.List;

import retrofit2.Retrofit;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by 知足 on 2018/4/16.
 */

public class NewsPresenter {
    private NewsView inv;
    private Subscription subscribe;

    public void attachView(NewsView inv){
        this.inv = inv;
    }
    public void getNews(String key,int num){
        Retrofit retrofit = RetrofitUtils.getInstance().getRetrofit();
        BlogService service = retrofit.create(BlogService.class);
        subscribe = service.getUrl(key, num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JavaBean>() {
                    @Override
                    public void call(JavaBean javaBean) {
                        List<JavaBean.NewslistBean> list = javaBean.getNewslist();
                        inv.success(list);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        inv.failed(throwable.getMessage());
                    }
                });

    }
    public void detachView(){
        // 当Activity销毁的时候取消订阅时间，防止内存泄漏
        if (subscribe != null) {
            if (subscribe.isUnsubscribed()) {
                subscribe.unsubscribe();
            }
        }
        if (inv!=null){
            inv = null;
        }
    }
}
