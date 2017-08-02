package soft.cdgame.com.cn.doutu;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

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
    protected void convert(BaseViewHolder helper, BQBean item) {


        SimpleDraweeView view = helper.getView(R.id.pic);
        view.setDrawingCacheEnabled(true);
        view.setImageURI(item.pic);
    }
}
