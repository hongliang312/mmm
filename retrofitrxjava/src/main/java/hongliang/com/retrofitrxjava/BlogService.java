package hongliang.com.retrofitrxjava;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 知足 on 2018/4/16.
 */

public interface BlogService {
    @GET("nba/")
    Observable<JavaBean> getUrl(@Query("key") String key, @Query("num") int num);
}
