package id.trafood.trafood.OrderP;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.trafood.trafood.DetailMenu;
import id.trafood.trafood.Models.Order;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.Connect;

/**
 * Created by TRAFOOD on 12/7/2017.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Holder> {
    List<Order> orders;

    public CartAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_cart,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        Picasso.with(holder.ivCart.getContext()).load(Connect.IMAGE_MENU_URL+orders.get(position).getFoto())
                .into(holder.ivCart);
        holder.tvNamaMenuCart.setText(orders.get(position).getNamamenu());
        holder.tvHargaCart.setText(orders.get(position).getHarga());
        holder.tvDeleteCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.ivCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), DetailMenu.class);
                i.putExtra("MENUID", orders.get(position).getMenuid());
                i.putExtra("NAMAMENU", orders.get(position).getNamamenu());
                i.putExtra("FOTOMENU", orders.get(position).getFoto());
                view.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView ivCart;
        public TextView tvNamaMenuCart, tvHargaCart, tvDeleteCart;
        public Spinner spBanyakPesan;
        public Holder(View itemView) {
            super(itemView);
            ivCart = (ImageView) itemView.findViewById(R.id.imageCart);
            tvNamaMenuCart = (TextView) itemView.findViewById(R.id.tvNamaMenuCart);
            tvHargaCart = (TextView) itemView.findViewById(R.id.tvHargaCart);
            tvDeleteCart = (TextView) itemView.findViewById(R.id.tvDeleteCart);
        }
    }
}
