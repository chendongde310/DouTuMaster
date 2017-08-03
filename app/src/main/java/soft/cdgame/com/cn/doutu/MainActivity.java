package soft.cdgame.com.cn.doutu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private ImageView myimageview;
    private android.widget.EditText search;
    private android.widget.TextView go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.go = (TextView) findViewById(R.id.go);
        this.search = (EditText) findViewById(R.id.search);
        this.myimageview = (ImageView) findViewById(R.id.my_image_view);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListActivity.class);
                intent.putExtra("key",search.getText().toString());
                startActivity(intent);
            }
        });

    }
}
