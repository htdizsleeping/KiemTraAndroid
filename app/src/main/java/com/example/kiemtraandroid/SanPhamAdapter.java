package com.example.kiemtraandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder> {
    private Context context;
    private List<SanPham> sanPhamList;
    private SanPhamDAO sanPhamDAO;

    public SanPhamAdapter(Context context, List<SanPham> sanPhamList, SanPhamDAO sanPhamDAO) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.sanPhamDAO = sanPhamDAO;
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        holder.edtTenSP.setText(sanPham.getTenSP());
        holder.edtPrice.setText(sanPham.getGiaTien());
        Picasso.get().load(sanPham.getImage()).into(holder.productImage);

        holder.imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    int productId = sanPhamList.get(adapterPosition).getMaSP();
                    boolean isDeleted = sanPhamDAO.deleteSanPham(productId);

                    if (isDeleted) {
                        sanPhamList.remove(adapterPosition);
                        notifyItemRemoved(adapterPosition);
                        notifyItemRangeChanged(adapterPosition, sanPhamList.size());
                        Toast.makeText(context, "Sản phẩm đã bị xóa", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Xóa sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public static class SanPhamViewHolder extends RecyclerView.ViewHolder {
        TextView edtTenSP, edtPrice;
        ImageView productImage, imageDelete;

        public SanPhamViewHolder(View itemView) {
            super(itemView);
            edtTenSP = itemView.findViewById(R.id.edtTenSP);
            edtPrice = itemView.findViewById(R.id.edtPrice);
            productImage = itemView.findViewById(R.id.productImage);
            imageDelete = itemView.findViewById(R.id.imageDelete);
        }
    }
}


