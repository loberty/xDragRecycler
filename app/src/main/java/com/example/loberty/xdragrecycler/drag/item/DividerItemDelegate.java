package com.example.loberty.xdragrecycler.drag.item;

import com.example.loberty.xdragrecycler.R;
import com.example.loberty.xdragrecycler.drag.ItemBean;
import com.example.loberty.xdragrecycler.utils.DragViewHolder;
import com.example.loberty.xdragrecycler.utils.base.ItemViewDelegate;

/**
 * Create by WangChen on 2020-04-21
 */
public class DividerItemDelegate implements ItemViewDelegate<ItemBean> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.layout_item_divider;
    }

    @Override
    public boolean isForViewType(ItemBean item, int position) {
        return item.getType() == ItemBean.TYPE_DIVIDER || item.getType() == ItemBean.TYPE_DIVIDER_1;
    }

    @Override
    public void convert(DragViewHolder holder, ItemBean itemBean, int position) {
        holder.setText(R.id.text, itemBean.getType() == ItemBean.TYPE_DIVIDER ? "以上应用在首页显示" : "");
    }

}
