package com.example.loberty.xdragrecycler.drag;


import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

/**
 * Create by WangChen on 2020-03-30
 * app manage touch item callback
 */
public class ManageItemTouchCallback extends ItemTouchHelper.Callback{

    private static final int MAIN_APP_PLACE_HOLDER_POSITION = 7;

    private MyAdapter adapter;

    @Override
    public boolean onMove(@NonNull  RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

        List<ItemBean> datas = adapter.getDatas();

        //当拖拽的viewHolder的position
        int fromPosition = viewHolder.getAdapterPosition();
        //当前拖拽到的viewHolder的position
        int toPosition = target.getAdapterPosition();

        ItemBean from = datas.get(fromPosition);
        ItemBean to = datas.get(toPosition);


        if (to.getType() == ItemBean.TYPE_DIVIDER ||
                to.getType() == ItemBean.TYPE_DIVIDER_1) {
            return false;
        }

        if (adapter.getPlaceHolderIndex() == MAIN_APP_PLACE_HOLDER_POSITION){
            //占位的位置特殊处理
            if (fromPosition>MAIN_APP_PLACE_HOLDER_POSITION && toPosition <= MAIN_APP_PLACE_HOLDER_POSITION){
                for (int i=fromPosition; i>toPosition; i--){
                    //占位的条目位置不交换
                    if (i==MAIN_APP_PLACE_HOLDER_POSITION+2){
                        Collections.swap(datas, i, MAIN_APP_PLACE_HOLDER_POSITION-1);
                    }else if (i!=MAIN_APP_PLACE_HOLDER_POSITION+1 && i!=MAIN_APP_PLACE_HOLDER_POSITION){
                        Collections.swap(datas, i, i - 1);
                    }
                }

                adapter.notifyItemMoved(fromPosition,toPosition==MAIN_APP_PLACE_HOLDER_POSITION?toPosition-1:toPosition);
                adapter.notifyItemChanged(MAIN_APP_PLACE_HOLDER_POSITION);
                adapter.notifyItemChanged(MAIN_APP_PLACE_HOLDER_POSITION+1);
                return true;
            }else if (toPosition==MAIN_APP_PLACE_HOLDER_POSITION){
                datas.remove(fromPosition);
                datas.add(MAIN_APP_PLACE_HOLDER_POSITION-1,from);
                adapter.notifyItemMoved(fromPosition,MAIN_APP_PLACE_HOLDER_POSITION-1);
                return true;
            }
        }

        if (to.getType() == ItemBean.TYPE_HOLDER){
            if (toPosition - fromPosition == 1){
                return false;
            }

            if (fromPosition > toPosition){
                datas.remove(fromPosition);
                datas.add(toPosition,from);
                adapter.notifyItemMoved(fromPosition,toPosition);
            }else{
                //只允许往placeholder前面添加
                datas.remove(fromPosition);
                datas.add(toPosition-1,from);
                adapter.notifyItemMoved(fromPosition,toPosition-1);
            }
            return true;
        }

        if (!to.isAdd()){
            return false;
        }

        datas.remove(fromPosition);
        datas.add(toPosition,from);
        adapter.notifyItemMoved(fromPosition,toPosition);
        return true;

    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG){
            ViewCompat.animate(viewHolder.itemView).scaleX(1.1f).scaleY(1.1f).setDuration(100).start();
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        ViewCompat.animate(viewHolder.itemView).scaleX(1.0f).scaleY(1.0f).setDuration(100).start();
        super.clearView(recyclerView, viewHolder);
        adapter.updateData();
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        adapter = ((MyAdapter)recyclerView.getAdapter());

        int dragFlag = 0;
        int position = viewHolder.getAdapterPosition();
        System.out.println("ItemTouchHelper getMovementFlags position " + position );
        List<ItemBean> datas = adapter.getDatas();
        ItemBean bean = datas.get(position);
        if (!bean.isAdd()){
            return makeMovementFlags(0,0);
        }

        //只有已添加的允许拖拽

        if (recyclerView.getLayoutManager() instanceof GridLayoutManager){
            dragFlag = ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
        }else if(recyclerView.getLayoutManager() instanceof LinearLayoutManager){
            dragFlag = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
        }
        return makeMovementFlags(dragFlag,0);
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {}
}
