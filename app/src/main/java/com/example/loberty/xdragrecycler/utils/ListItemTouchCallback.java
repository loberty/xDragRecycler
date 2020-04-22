package com.example.loberty.xdragrecycler.utils;


import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * WangChen on 2017/3/30.
 * 卡片排序 list排序 ItemTouchCallback
 */

public class ListItemTouchCallback extends DefaultItemTouchCallback {

    public ListItemTouchCallback(OnItemTouchCallbackListener onItemTouchCallbackListener) {
        super(onItemTouchCallbackListener);
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        DragViewHolder dvh = (DragViewHolder) viewHolder;
        if (dvh != null && dvh.isNeedChangeBg() && actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            dvh.changeContentViewBackground();
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        DragViewHolder dvh = (DragViewHolder) viewHolder;
        if (dvh != null && dvh.isNeedChangeBg()){
            dvh.resetContentViewBackground();
        }
        super.clearView(recyclerView, viewHolder);
    }
}
