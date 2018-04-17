package hongliang.com.mmm;

import android.util.Log;

import java.util.Map;

/**
 * Created by 知足 on 2018/4/14.
 */

public class PresenterImpl implements IPresenter {
    private IView iView;
    private static final String TAG = "PresenterImpl";

    public PresenterImpl(IView iView){
        this.iView = iView;
    }
    @Override
    public void getDataToView(String baseUrl, Map<String, String> map) {
        ModelImpl model = new ModelImpl();
        model.getProduct(baseUrl, map, new LoadNetListener() {
            @Override
            public void getData(Object o) {
                iView.getData(o);
            }

            @Override
            public void getFailed(Throwable t) {

            }
            });
    }
}


