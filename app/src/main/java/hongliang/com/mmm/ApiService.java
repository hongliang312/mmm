package hongliang.com.mmm;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by 知足 on 2018/4/14.
 */

public interface ApiService {
    @GET("ad/getAd")
    Observable<Product> getProduct(@QueryMap Map<String,String> map);
}
