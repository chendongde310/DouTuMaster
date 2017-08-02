package soft.cdgame.com.cn.doutu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends Activity {

    private com.facebook.drawee.view.SimpleDraweeView myimageview;
    private android.widget.EditText search;
    private android.widget.TextView go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.go = (TextView) findViewById(R.id.go);
        this.search = (EditText) findViewById(R.id.search);
        this.myimageview = (SimpleDraweeView) findViewById(R.id.my_image_view);

    }
}
