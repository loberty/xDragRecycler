package com.example.loberty.xdragrecycler.drag;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by WangChen on 2020-04-21
 */
public class DataModel {

    private static List<ItemBean> addList = new ArrayList<>();
    private static List<ItemBean> nonAddList = new ArrayList<>();

    private static void addData(){
        clearAll();
        List<Character> list = new ArrayList<>();
        char a = 'A';
        for (int i = 0; i < 26; i++) {
            list.add(a);
            a++;
        }

        for (int i = 0; i < list.size(); i++) {
            ItemBean bean = new ItemBean();
            bean.setType(ItemBean.TYPE_TEXT);
            bean.setText(list.get(i)+"");
            if (i >11){
                bean.setAdd(false);
                nonAddList.add(bean);
            }else{
                bean.setAdd(true);
                addList.add(bean);
            }
        }
    }

    public static List<ItemBean> getDataList(){
        addData();
        List<ItemBean> itemBeans = new ArrayList<>(addList);
        itemBeans.add(5,addHolder());
        itemBeans.add(6,addDivider());
        itemBeans.add(addDivider1());
        itemBeans.addAll(nonAddList);
        return itemBeans;
    }

    private static ItemBean addHolder(){
        ItemBean bean = new ItemBean();
        bean.setType(ItemBean.TYPE_HOLDER);
        return bean;
    }

    private static ItemBean addDivider(){
        ItemBean bean = new ItemBean();
        bean.setType(ItemBean.TYPE_DIVIDER);
        return bean;
    }

    private static ItemBean addDivider1(){
        ItemBean bean = new ItemBean();
        bean.setType(ItemBean.TYPE_DIVIDER_1);
        return bean;
    }

    private static void clearAll(){
        addList.clear();
        nonAddList.clear();
    }

}
