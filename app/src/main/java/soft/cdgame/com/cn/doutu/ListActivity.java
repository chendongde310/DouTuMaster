package soft.cdgame.com.cn.doutu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.io.File;
import java.util.List;

import rx.Subscriber;

public class ListActivity extends Activity {
    RecyclerView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mList = (RecyclerView) findViewById(R.id.list);
        mList.setLayoutManager(new
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
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
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                BQBean bean = (BQBean) adapter.getData().get(position);
                FileBinaryResource resource = (FileBinaryResource) Fresco.getImagePipelineFactory().getMainFileCache().getResource(new
                        SimpleCacheKey(bean.pic));

                File file = resource.getFile();
                Intent imageIntent = new Intent(Intent.ACTION_SEND);
                imageIntent.setType("image/jpeg");
                imageIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                startActivity(Intent.createChooser(imageIntent, "分享"));
            }
        });
    }


    public void shareMsg(String activityTitle, String msgTitle, String msgText,
                         String imgPath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        File f = new File(imgPath);
        if (f != null && f.exists() && f.isFile()) {
            intent.setType("image/jpg");
            Uri u = Uri.fromFile(f);
            intent.putExtra(Intent.EXTRA_STREAM, u);
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, activityTitle));
    }
}
