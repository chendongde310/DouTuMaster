package soft.cdgame.com.cn.doutu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;

import rx.Subscriber;

public class ListActivity extends Activity {
    RecyclerView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mList = (RecyclerView) findViewById(R.id.list);
        mList.setLayoutManager(new
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        final ListAdapter adapter = new ListAdapter();
        mList.setAdapter(adapter);
        String key = getIntent().getStringExtra("key");
        Subscriber subscriber = new Subscriber<List<BQBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(List<BQBean> bqBeen) {
                adapter.setNewData(bqBeen);
            }
        };
        PicData.getBQB(key, 1).subscribe(subscriber);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final BaseQuickAdapter adapter, View view, final int position) {

                final BQBean bean = (BQBean) adapter.getData().get(position);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            File file = new File(Glide.with(ListActivity.this).download(bean.pic).submit().get().getParent().replace(".0",".jpg"));
                            Logger.e(file.getPath());
                            Intent imageIntent = new Intent(Intent.ACTION_SEND);
                            imageIntent.setType("image/jpeg");
                            imageIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                            startActivity(Intent.createChooser(imageIntent, "分享"));
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


            }
        });
    }
}
