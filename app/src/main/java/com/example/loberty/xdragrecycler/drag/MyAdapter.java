package com.example.loberty.xdragrecycler.drag;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.loberty.xdragrecycler.drag.item.DefaultItemDelegate;
import com.example.loberty.xdragrecycler.drag.item.DividerItemDelegate;
import com.example.loberty.xdragrecycler.utils.adapter.MultiItemTypeAdapter;

import java.util.List;

/**
 * Create by WangChen on 2020-04-21
 */
public class MyAdapter extends MultiItemTypeAdapter<ItemBean> {

    public MyAdapter(Context context, List<ItemBean> datas) {
        super(context, datas);
        addItemViewDelegate(new DefaultItemDelegate());
        addItemViewDelegate(new DividerItemDelegate());
    }

    public int getPlaceHolderIndex(){
        for (int i = 0; i < getDatas().size(); i++) {
            ItemBean bean = getDatas().get(i);
            if (bean.getType() == ItemBean.TYPE_HOLDER){
                return i;
            }
        }
        return -1;
    }

    public void updateData(){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                notifyItemChanged(getPlaceHolderIndex());
            }
        });
    }

}
