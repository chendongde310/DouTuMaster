package soft.cdgame.com.cn.doutu;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：陈东  —  www.renwey.com
 * 日期：2017/8/3 0003 - 1:48
 * 注释：
 */
class BQBean implements Parcelable {

    public String url;
    public String pic;
    public String path;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.pic);
        dest.writeString(this.path);

    }

    public BQBean() {
    }

    protected BQBean(Parcel in) {
        this.url = in.readString();
        this.pic = in.readString();
        this.path = in.readString();
    }

    public static final Parcelable.Creator<BQBean> CREATOR = new Parcelable.Creator<BQBean>() {
        public BQBean createFromParcel(Parcel source) {
            return new BQBean(source);
        }

        public BQBean[] newArray(int size) {
            return new BQBean[size];
        }
    };
}
