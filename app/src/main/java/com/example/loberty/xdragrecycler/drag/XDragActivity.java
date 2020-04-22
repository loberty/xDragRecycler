package com.example.loberty.xdragrecycler.drag;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loberty.xdragrecycler.R;
import com.example.loberty.xdragrecycler.utils.adapter.MultiItemTypeAdapter;

import java.util.ArrayList;

/**
 * Create by WangChen on 2020-04-21
 */
public class XDragActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_drag_layout);
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = this.findViewById(R.id.recyclerView);
        final MyAdapter adapter = new MyAdapter(this,new ArrayList<ItemBean>());
        GridLayoutManager topLayoutManager = new GridLayoutManager(this,4);
        topLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (adapter.getDatas().get(position).getType()){
                    case ItemBean.TYPE_TEXT:
                    case ItemBean.TYPE_HOLDER:
                        return 1;
                }
                return 4;
            }
        });

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(topLayoutManager);
        //防止notifyItemChange闪烁
        recyclerView.getItemAnimator().setChangeDuration(0);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ManageItemTouchCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);

        adapter.setDatas(DataModel.getDataList());
        adapter.notifyDataSetChanged();
    }

}
