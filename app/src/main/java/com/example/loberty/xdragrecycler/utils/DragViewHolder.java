package com.example.loberty.xdragrecycler.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.loberty.xdragrecycler.utils.wrapper.ItemDrag;


public class DragViewHolder extends RecyclerView.ViewHolder implements ItemDrag
{
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;
    private boolean needChangeBg = false;
    private int bgColor1 = 0xfff0f0f0;//条目选中时颜色
    private int bgColor2 = 0xffffffff;//拖动后条目重置的颜色
    private boolean isCanMove; //拖动时是否能交换位置
    private Object itemData;

    public DragViewHolder(Context context, View itemView)
    {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }


    public static DragViewHolder createViewHolder(Context context, View itemView)
    {
        DragViewHolder holder = new DragViewHolder(context, itemView);
        return holder;
    }

    public static DragViewHolder createViewHolder(Context context, ViewGroup parent, int layoutId)
    {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        DragViewHolder holder = new DragViewHolder(context, itemView);
        return holder;
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView()
    {
        return mConvertView;
    }

    public Context getmContext() {
        return mContext;
    }

    public boolean isNeedChangeBg(){
        return needChangeBg;
    }

    public void setNeedChangeBg(boolean b){
        this.needChangeBg = b;
    }

    public void setBgChangeColor(int color){
        this.bgColor1 = color;
    }
    public void setBgResetColor(int color){
        this.bgColor2 = color;
    }

    /****以下为辅助方法*****/

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public DragViewHolder setText(int viewId, String text)
    {
        TextView tv = getView(viewId);
        tv.setText(TextUtils.isEmpty(text)?"":text);
        return this;
    }

    public DragViewHolder setText(int viewId, CharSequence text)
    {
        TextView tv = getView(viewId);
        tv.setText(TextUtils.isEmpty(text)?"":text);
        return this;
    }

    public DragViewHolder setTextSize(int viewId, int size){
        TextView tv = getView(viewId);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size);
        return this;
    }

    public DragViewHolder setImageResource(int viewId, int resId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public DragViewHolder changeContentViewBackground()
    {
        mConvertView.setBackgroundColor(bgColor1);
        return this;
    }
    public DragViewHolder resetContentViewBackground()
    {
        mConvertView.setBackgroundColor(bgColor2);
        return this;
    }

    public DragViewHolder setImageBitmap(int viewId, Bitmap bitmap)
    {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public DragViewHolder setImageDrawable(int viewId, Drawable drawable)
    {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public DragViewHolder setImageUrl(int viewId, String url)
    {
        ImageView view = getView(viewId);
        return this;
    }

    public DragViewHolder setBackgroundColor(int viewId, int color)
    {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public DragViewHolder setBackgroundRes(int viewId, int backgroundRes)
    {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public DragViewHolder setTextColor(int viewId, int textColor)
    {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public DragViewHolder setTextColorRes(int viewId, int textColorRes)
    {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    public DragViewHolder setHeadWithGlide(int viewId, Object resourceUrl){
        ImageView view = getView(viewId);
//        GlideUtils.loadAppIcon(view.getContext(),resourceUrl,view);
        return this;
    }

    @SuppressLint("NewApi")
    public DragViewHolder setAlpha(int viewId, float value)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            getView(viewId).setAlpha(value);
        } else
        {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public DragViewHolder setVisible(int viewId, boolean visible)
    {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public DragViewHolder linkify(int viewId)
    {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public DragViewHolder setTypeface(Typeface typeface, int... viewIds)
    {
        for (int viewId : viewIds)
        {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public DragViewHolder setProgress(int viewId, int progress)
    {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public DragViewHolder setProgress(int viewId, int progress, int max)
    {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public DragViewHolder setMax(int viewId, int max)
    {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    public DragViewHolder setRating(int viewId, float rating)
    {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public DragViewHolder setRating(int viewId, float rating, int max)
    {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public DragViewHolder setTag(int viewId, Object tag)
    {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public DragViewHolder setTag(int viewId, int key, Object tag)
    {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public DragViewHolder setChecked(int viewId, boolean checked)
    {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 关于事件的
     */
    public DragViewHolder setOnClickListener(int viewId, View.OnClickListener listener)
    {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public DragViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener)
    {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public DragViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener)
    {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public DragViewHolder setSelect(int viewId, boolean select)
    {
        View view = getView(viewId);
        view.setSelected(select);
        return this;
    }

    public void setCanMove(boolean canMove) {
        isCanMove = canMove;
    }

    @Override
    public boolean isCanMove() {
        return isCanMove;
    }

    public Object getItemData() {
        return itemData;
    }

    public void setItemData(Object itemData) {
        this.itemData = itemData;
    }
}
