package soft.cdgame.com.cn.doutu;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * 作者：陈东  —  www.renwey.com
 * 日期：2017/8/3 0003 - 2:17
 * 注释：
 */
public class ListAdapter extends BaseQuickAdapter<BQBean, BaseViewHolder> {

    public ListAdapter() {
        super(R.layout.item_bq);
    }

    @Override
    protected void convert(BaseViewHolder helper, final BQBean item) {
        final ImageView view = helper.getView(R.id.pic);
        Glide.with(mContext).load(item.pic).into(view);

    }
}
