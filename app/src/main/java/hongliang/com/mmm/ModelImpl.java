package hongliang.com.mmm;

import android.util.Log;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by 知足 on 2018/4/14.
 */

public class ModelImpl implements IModel {
    private static final String TAG = "ModelImpl";
    private DisposableSubscriber<Product> subscriber;

    @Override
    public void getProduct(String baseUrl, Map<String, String> map, final LoadNetListener loadNetListener) {
        Log.e(TAG, "getProduct: ---"+map.toString());
        Observable<Product> flowable = RetrofitUtil.getInstance(baseUrl).getRetrofit().create(ApiService.class).getProduct(map);
        subscriber = (DisposableSubscriber)flowable.subscribeOn(Schedulers.io())//在io线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())//回到主线程请求结果
                .subscribe(new Consumer<Product>() {
                    @Override
                    public void accept(Product product) throws Exception {

                        Log.e(TAG, "onNext: ----next");
                        loadNetListener.getData(product);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "onError: ----"+throwable.toString());
                        loadNetListener.getFailed(throwable);

                    }
                });
               /* .subscribeWith(new DisposableSubscriber(){
                    @Override
                    public void onNext(Object o) {
                        Log.e(TAG, "onNext: ----next");
                        loadNetListener.getData(o);
                    }

                    @Override
                    public void onError(Throwable t) {
                      c
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ----complete");
                    }
                });
*/
    }
}
