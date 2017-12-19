package id.trafood.trafood.OrderP;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.trafood.trafood.DetailMenu;
import id.trafood.trafood.Models.Order;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.Connect;
import id.trafood.trafood.Rest.RestApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void onBindViewHolder(final Holder holder, final int position) {
        Picasso.with(holder.ivCart.getContext()).load(Connect.IMAGE_MENU_URL+orders.get(position).getFoto())
                .into(holder.ivCart);
        holder.tvNamaMenuCart.setText(orders.get(position).getNamamenu());
        holder.tvHargaCart.setText(orders.get(position).getHarga());
        holder.idCart.setText(orders.get(position).getCart_id());
        holder.tvQtyCart.setText(orders.get(position).getQty());

        //Tes Fahri
        holder.tvPesanKhusus.setText(orders.get(position).getNotes());

        String harga = orders.get(position).getHarga();
        int y = Integer.parseInt(harga);
        String qty = orders.get(position).getQty();
        int x = Integer.parseInt(qty);

        final int total = y*x;
        holder.tvSubtotal.setText(String.valueOf(total));

        final String cartid = orders.get(position).getCart_id();
        final int positions = holder.getAdapterPosition();
        holder.tvDeleteCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.restApi.deleteCart(cartid).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        orders.remove(positions);
                        notifyItemRemoved(positions);
                        notifyItemRangeChanged(positions, orders.size());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                    }
                });
            }
        });

        String sumStr = orders.get(position).getQty();
        if (sumStr.equals("1")){
            holder.minCart.setEnabled(false);
        }

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

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.tvPesanKhusus.setText(orders.get(position).getNotes());
                holder.tvQtyCart.setText(orders.get(position).getQty());
                holder.tvHargaCart.setText(orders.get(position).getHarga());
                holder.tvSubtotal.setText(String.valueOf(total));
                holder.linearLayout.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        ImageView ivCart;
        TextView tvNamaMenuCart, tvHargaCart,tvSubtotal, tvQtyCart,idCart;
        EditText tvPesanKhusus;
        ImageButton tvDeleteCart;
        RestApi restApi;
        Button plusCart, minCart,saveCart,cancel;
        LinearLayout linearLayout;

        public Holder(View itemView) {
            super(itemView);
            ivCart = (ImageView) itemView.findViewById(R.id.imageCart);
            tvNamaMenuCart = (TextView) itemView.findViewById(R.id.tvNamaMenuCart);
            tvHargaCart = (TextView) itemView.findViewById(R.id.tvHargaCart);
            tvDeleteCart = (ImageButton) itemView.findViewById(R.id.tvDeleteCart);
            tvSubtotal = (TextView) itemView.findViewById(R.id.tvSubtotalCart);
            idCart = (TextView) itemView.findViewById(R.id.idCart);
            plusCart = (Button) itemView.findViewById(R.id.btnPlusCart);
            minCart = (Button) itemView.findViewById(R.id.btnMinCart);
            saveCart = (Button) itemView.findViewById(R.id.btnSaveCart);
            tvSubtotal = (TextView) itemView.findViewById(R.id.tvSubtotalCart);
            tvPesanKhusus = (EditText) itemView.findViewById(R.id.tvPesanKhusus);
            tvQtyCart = (TextView) itemView.findViewById(R.id.tvQtyCart);
            restApi = ApiClient.getClient().create(RestApi.class);
            idCart.setVisibility(View.GONE);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearSimpan);
            cancel = (Button) itemView.findViewById(R.id.btnCancel);

            linearLayout.setVisibility(View.GONE);

            plusCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String sumStr = tvQtyCart.getText().toString();
                    int sum = Integer.valueOf(sumStr);
                    sum += 1;
                    String sumStra = String.valueOf(sum);
                    tvQtyCart.setText(sumStra);

                    String harga = tvHargaCart.getText().toString();
                    int hargas = Integer.parseInt(harga);
                    int kali = hargas*sum;
                    tvSubtotal.setText(String.valueOf(kali));

                    linearLayout.setVisibility(View.VISIBLE);

                    if (sumStra.equals(sumStr)){
                        linearLayout.setVisibility(View.GONE);
                    }
                    minCart.setEnabled(true);
                    if (sumStra.equals("1")){
                        minCart.setEnabled(false);
                    }
                }
            });

            minCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String sumStr = tvQtyCart.getText().toString();
                    int sum = Integer.valueOf(sumStr);
                    sum -= 1;
                    String sumStra = String.valueOf(sum);
                    tvQtyCart.setText(sumStra);

                    String harga = tvHargaCart.getText().toString();
                    int hargas = Integer.parseInt(harga);
                    int kali = hargas*sum;
                    tvSubtotal.setText(String.valueOf(kali));

                    if (sumStra.equals(sumStr)){
                        linearLayout.setVisibility(View.GONE);
                    }
                    if (sumStra.equals("1")){
                        minCart.setEnabled(false);
                    }

                    linearLayout.setVisibility(View.VISIBLE);

                }
            });

           tvPesanKhusus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    linearLayout.setVisibility(View.VISIBLE);
                }
            });

            saveCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String cartid = idCart.getText().toString();
                    String notes = tvPesanKhusus.getText().toString();
                    String qty = tvQtyCart.getText().toString();
                    restApi.putNotes(cartid,qty,notes).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            linearLayout.setVisibility(View.GONE);
                        }
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
            });

        }

    }
}
