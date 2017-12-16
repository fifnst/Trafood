package id.trafood.trafood.OrderP;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.trafood.trafood.Models.Order;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.Connect;

/**
 * Created by kulinerin 1 on 16/12/2017.
 */

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.Holdes> {
    List<Order> orders;

    public OrderDetailAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public Holdes onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_order_confirmation,parent,false);
        Holdes holdes = new Holdes(view);
        return holdes;
    }

    @Override
    public void onBindViewHolder(Holdes holder, int position) {
        Picasso.with(holder.ivCart.getContext()).load(Connect.IMAGE_MENU_URL+orders.get(position).getFoto())
                .into(holder.ivCart);
        holder.tvNamaMenuCart.setText(orders.get(position).getNamamenu());
        holder.tvHargaCart.setText("Rp. " +orders.get(position).getHarga());

        holder.tvQtyCart.setText(orders.get(position).getQty());

        //Tes Fahri
        holder.tvPesanKhusus.setText("Catatan: "+orders.get(position).getNotes());

        String harga = orders.get(position).getHarga();
        int y = Integer.parseInt(harga);
        String qty = orders.get(position).getQty();
        int x = Integer.parseInt(qty);

        int total = y*x;
        holder.tvSubtotal.setText("Sub total Rp. "+String.valueOf(total));

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class Holdes extends RecyclerView.ViewHolder {
        ImageView ivCart;
        TextView tvNamaMenuCart, tvHargaCart,tvSubtotal, tvPesanKhusus, tvQtyCart;
        public Holdes(View itemView) {
            super(itemView);
            ivCart = (ImageView) itemView.findViewById(R.id.imageConfrmation);
            tvNamaMenuCart = (TextView) itemView.findViewById(R.id.tvNamaMenuConfrmation);
            tvHargaCart = (TextView) itemView.findViewById(R.id.tvHargaConfrmation);
            tvSubtotal = (TextView) itemView.findViewById(R.id.tvSubtotalConfrmation);
            tvPesanKhusus = (TextView) itemView.findViewById(R.id.tvPesanKhususConfrmation);
            tvQtyCart = (TextView) itemView.findViewById(R.id.tvQtyConfrmation);

        }
    }
}
