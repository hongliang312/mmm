package hongliang.com.retrofitrxjava;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<JavaBean.NewslistBean> list = new ArrayList<>();
    private int num=10;
    private NewsPresenter presenter;
    private MyAdapter adapter;
    private XRecyclerView xRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xRecyclerView = (XRecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        Fresco.initialize(this);

        getData(num);

        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        num=10;
                        getData(num);
                        xRecyclerView.refreshComplete();
                    }

                }, 2000);

            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        num+=10;
                        getData(num);
                        xRecyclerView.refreshComplete();
                    }
                }, 2000);

            }
        });
    }
    public void getData(int count){
        presenter = new NewsPresenter();
        presenter.getNews("71e58b5b2f930eaf1f937407acde08fe", count);
        presenter.attachView(new NewsView() {
            @Override
            public void success(List<JavaBean.NewslistBean> data) {
                list.clear();
                list.addAll(data);
                Log.i("zzz", "success: " + list.toString());
                if (adapter==null){
                    adapter = new MyAdapter(list, MainActivity.this);
                    xRecyclerView.setAdapter(adapter);
                }else {
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void failed(String e) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detachView();
        }

    }
}
