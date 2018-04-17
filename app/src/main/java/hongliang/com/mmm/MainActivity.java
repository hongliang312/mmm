package hongliang.com.mmm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IView {

    private PresenterImpl presenter;
    private static final String TAG = "MainActivity";
    private TextView mmm;
    private ImageView image;
    private  List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mmm = findViewById(R.id.mm);
        image = findViewById(R.id.tu);
        HashMap<String, String> map = new HashMap<>();
        map.put("","");
        presenter = new PresenterImpl(this);
        presenter.getDataToView("https://www.zhaoapi.cn/",map);
    }

    @Override
    public void getData(Object o) {
        Product product = (Product) o;
        List<Product.DataBean> data = product.getData();
        for (int i = 0; i <data.size() ; i++) {
            String icon = data.get(i).getIcon();
            Glide.with(this).load(icon).into(image);
        }
        mmm.setText(product.toString());
        Log.e(TAG, "getData: ----"+product.getData().get(0).getTitle());
    }

    @Override
    public void getFailed(Exception e) {
        Log.e(TAG, "getData: ----展示失败");
    }
}
