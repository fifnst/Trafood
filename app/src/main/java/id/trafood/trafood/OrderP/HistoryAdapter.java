package id.trafood.trafood.OrderP;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import id.trafood.trafood.DetailHistoryTransaksi;
import id.trafood.trafood.Models.OrderHistory;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.Connect;

/**
 * Created by kulinerin 1 on 22/12/2017.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Holdw> {
    List<OrderHistory> histories;

    public HistoryAdapter(List<OrderHistory> histories) {
        this.histories = histories;
    }

    @Override
    public Holdw onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_history, parent, false);
        Holdw holdw = new Holdw(view);
        return holdw;
    }

    @Override
    public void onBindViewHolder(Holdw holder, int position) {
        holder.tvTgl.setText(histories.get(position).getDatecreate());
        holder.tvNamarm.setText(histories.get(position).getNamarm());
        holder.tvAlamatrm.setText(histories.get(position).getAlamatrm());
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        String harga = histories.get(position).getTotalprice();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        Double tital = Double.valueOf(harga);
        holder.tvHarga.setText(kursIndonesia.format(tital+0));
        Glide.with(holder.ivFotoRm.getContext()).load(Connect.IMAGE_RM_URL+histories.get(position).getFotosampul()).error(R.drawable.add_pict_kedai)
                .into(holder.ivFotoRm);
        final String orderid = histories.get(position).getTrans_id();

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailHistoryTransaksi.class);
                intent.putExtra("TRANSDID", orderid);
                view.getContext().startActivity(intent);
            }
        });
        holder.btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailHistoryTransaksi.class);
                intent.putExtra("TRANSDID", orderid);
                view.getContext().startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public class Holdw extends RecyclerView.ViewHolder {
        TextView tvTgl, tvNamarm, tvAlamatrm, tvHarga;
        ImageView ivFotoRm;
        Button btndetail;
        //Button btnBeliLagi;
        LinearLayout linearLayout;
        public Holdw(View itemView) {
            super(itemView);
            tvTgl = (TextView) itemView.findViewById(R.id.tvTglListHistory);
            tvNamarm = (TextView) itemView.findViewById(R.id.tvnamaRmListHistory);
            tvAlamatrm = (TextView) itemView.findViewById(R.id.tvAlamatRmListHistory);
            tvHarga = (TextView) itemView.findViewById(R.id.tvHargaListHistory);

            ivFotoRm = (ImageView) itemView.findViewById(R.id.ivRmListHistory);

            btndetail = (Button) itemView.findViewById(R.id.btnDetailListHistory);
           // btnBeliLagi = (Button) itemView.findViewById(R.id.btnBeliListHistory);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearAtas);


        }
    }
}
