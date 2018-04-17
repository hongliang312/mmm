package hongliang.com.mmm;

/**
 * Created by 知足 on 2018/4/14.
 */

public interface LoadNetListener {

    void getData(Object o);
    void getFailed(Throwable t);
}
