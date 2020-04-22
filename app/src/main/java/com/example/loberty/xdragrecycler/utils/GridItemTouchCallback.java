package com.example.loberty.xdragrecycler.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * WangChen on 2017/3/30.
 * 服务排序 grid排序 ItemTouchCallback
 */

public class GridItemTouchCallback extends DefaultItemTouchCallback {

    public GridItemTouchCallback(OnItemTouchCallbackListener onItemTouchCallbackListener) {
        super(onItemTouchCallbackListener);
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if(actionState == ItemTouchHelper.ACTION_STATE_DRAG){
            ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(viewHolder.itemView, "scaleX", 1.0f, 1.5f);
            ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(viewHolder.itemView, "scaleY", 1.0f, 1.5f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(300);
            animatorSet.playTogether(objectAnimatorX, objectAnimatorY);
            animatorSet.start();
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(viewHolder.itemView, "scaleX", 1.5f, 1.0f);
        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(viewHolder.itemView, "scaleY", 1.5f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300);
        animatorSet.playTogether(objectAnimatorX, objectAnimatorY);
        animatorSet.start();
        super.clearView(recyclerView, viewHolder);
    }
}
