package com.example.taskapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapplication.items.Items;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private ArrayList<Items> itemsList = new ArrayList<>();
    private RecyclerViewCallBack callBack;


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    public void setItemsList(ArrayList<Items> itemsList) {
        if (itemsList != null) {
            this.itemsList = itemsList;
            notifyDataSetChanged();
        }

    }

    public void clearItems() {
        this.itemsList.clear();
        notifyDataSetChanged();
    }

    public void setCallBack(RecyclerViewCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.addToCartBtn.setTag(position);
        holder.onBind(itemsList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemsList != null ? itemsList.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Button addToCartBtn;
        private TextView itemName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.item_name);
            addToCartBtn = (Button) itemView.findViewById(R.id.select_btn);
        }

        public void onBind(Items items) {
            itemName.setText(items.getItemName());
            addToCartBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            callBack.onItemClick(itemsList.get((int)v.getTag()));
        }

    }

    public interface RecyclerViewCallBack {
        public void onItemClick(Items items);
    }
}
