package hongliang.com.mmm;

import java.util.Map;

/**
 * Created by 知足 on 2018/4/14.
 */

public interface IModel {

    void getProduct(String baseUrl, Map<String,String> map, LoadNetListener loadNetListener);
}
