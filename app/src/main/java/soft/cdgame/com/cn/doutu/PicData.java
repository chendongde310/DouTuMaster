package soft.cdgame.com.cn.doutu;

import com.orhanobut.logger.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 作者：陈东  —  www.renwey.com
 * 日期：2017/8/3 0003 - 1:39
 * 注释：图片数据
 */
public class PicData {

    private static int TimeOut = 1000 * 15;
    private static String HOST_URL = "https://www.doutula.com/search?type=photo&more=1&keyword=";

    public static Observable<List<BQBean>> getBQB(final String key, final int page) {
        return Observable.create(new Observable.OnSubscribe<List<BQBean>>() {
            @Override
            public void call(Subscriber<? super List<BQBean>> subscriber) {
                List<BQBean> bqBeen = new ArrayList<BQBean>();
                try {
                    Document index = Jsoup.connect(HOST_URL + key + "&page=" + page).timeout(TimeOut).get();
                    Logger.d(HOST_URL + key + "&page=" + page);
                    Elements pics = index.getElementsByClass("random_picture").first().getElementsByTag("a");
                    for (Element e : pics) {
                        BQBean bean = new BQBean();
                        bean.url = e.attr("href");
                        bean.pic = "http:"+e.getElementsByTag("img").first().attr("data-original");
                        bqBeen.add(bean);
                        Logger.d(bean.pic);
                    }
                    if (bqBeen.size() > 0) {
                        subscriber.onNext(bqBeen);
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(new NullPointerException("没有数据"));
                    }
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }


}
