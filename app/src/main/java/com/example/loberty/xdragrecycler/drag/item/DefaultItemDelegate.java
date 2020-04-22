package com.example.loberty.xdragrecycler.drag.item;

import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.example.loberty.xdragrecycler.R;
import com.example.loberty.xdragrecycler.drag.ItemBean;
import com.example.loberty.xdragrecycler.drag.MyApp;
import com.example.loberty.xdragrecycler.utils.DragViewHolder;
import com.example.loberty.xdragrecycler.utils.base.ItemViewDelegate;

/**
 * Create by WangChen on 2020-04-21
 */
public class DefaultItemDelegate implements ItemViewDelegate<ItemBean> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.layout_item_default;
    }

    @Override
    public boolean isForViewType(ItemBean item, int position) {
        return item.getType() == ItemBean.TYPE_TEXT || item.getType() == ItemBean.TYPE_HOLDER;
    }

    @Override
    public void convert(DragViewHolder holder, ItemBean itemBean, int position) {
        holder.setText(R.id.text, itemBean.getType() == ItemBean.TYPE_HOLDER ? "占" : itemBean.getText());
//        Drawable bgDrawable = ContextCompat.getDrawable(MyApp.getInstance(), itemBean.isAdd() ? R.drawable.item_bg : R.drawable.item_bg2);
        holder.setBackgroundRes(R.id.text,itemBean.isAdd() ? R.drawable.item_bg : R.drawable.item_bg2);
    }

}
