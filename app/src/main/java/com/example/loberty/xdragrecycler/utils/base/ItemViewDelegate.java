package com.example.loberty.xdragrecycler.utils.base;


import com.example.loberty.xdragrecycler.utils.DragViewHolder;

/**
 * Created by zhy on 16/6/22.
 */
public interface ItemViewDelegate<T>
{

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(DragViewHolder holder, T t, int position);

}
