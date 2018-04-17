package hongliang.com.retrofitrxjava;

import java.util.List;

/**
 * Created by 知足 on 2018/4/16.
 */

public interface NewsView {

    void success(List<JavaBean.NewslistBean> data);
    void failed(String e);
}
