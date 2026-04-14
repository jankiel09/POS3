package com.example.pos3;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pos3.datamodels.Inventory;
import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> {
    private List<Inventory> inventoryList;

    public InventoryAdapter(List<Inventory> list) {
        this.inventoryList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inventory_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Inventory item = inventoryList.get(position);
        holder.tvName.setText(item.getName());
        holder.tvPrice.setText(String.format("P%.2f", item.getPrice()));
        holder.tvQty.setText(String.valueOf(item.getQty()));

        // Easy to understand: Turn RED if low stock (less than 10)
        if (item.getQty() < 10) {
            holder.tvQty.setTextColor(Color.RED);
        } else {
            holder.tvQty.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() { return inventoryList.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice, tvQty;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvInvName);
            tvPrice = itemView.findViewById(R.id.tvInvPrice);
            tvQty = itemView.findViewById(R.id.tvInvQty);
        }
    }
}