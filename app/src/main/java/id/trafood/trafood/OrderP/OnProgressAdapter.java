package id.trafood.trafood.OrderP;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import id.trafood.trafood.DetailMenu;
import id.trafood.trafood.HistoryActivity;
import id.trafood.trafood.Models.OrderHistory;
import id.trafood.trafood.R;
import id.trafood.trafood.StatusPengirimanActivity;

/**
 * Created by kulinerin 1 on 22/12/2017.
 */

public class OnProgressAdapter extends RecyclerView.Adapter<OnProgressAdapter.MYholde> {
    List<OrderHistory> order;

    public OnProgressAdapter(List<OrderHistory> order) {
        this.order = order;
    }

    @Override
    public MYholde onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_onprogress, parent, false);
        MYholde holder = new MYholde(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MYholde holder, final int position) {
        holder.tvNamarm.setText(order.get(position).getNamarm());
        holder.tvAlamatUser.setText(order.get(position).getAddressuser());
        String subtotal = order.get(position).getTotalprice();
        String shippingcharge = order.get(position).getShippingcharge();
        Double i = Double.valueOf(subtotal);
        Double j = Double.valueOf(shippingcharge);

        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);

        holder.tvHarga.setText(kursIndonesia.format(i+j));
        final Drawable ivStep1before = holder.iv1.getContext().getResources().getDrawable(R.drawable.love_before);
        final Drawable ivStep1After = holder.iv1.getContext().getResources().getDrawable(R.drawable.love_after);
        final Drawable ivStep2before = holder.iv2.getContext().getResources().getDrawable(R.drawable.love_before);
        final Drawable ivStep2After = holder.iv2.getContext().getResources().getDrawable(R.drawable.love_after);
        final Drawable ivStep3before = holder.iv3.getContext().getResources().getDrawable(R.drawable.love_before);
        final Drawable ivStep3After = holder.iv3.getContext().getResources().getDrawable(R.drawable.love_after);
        final Drawable ivStep4before = holder.iv4.getContext().getResources().getDrawable(R.drawable.love_before);
        final Drawable ivStep4After = holder.iv4.getContext().getResources().getDrawable(R.drawable.love_after);

        String status  = order.get(position).getStatus();
        if (status.equals("1")){
            holder.iv1.setImageDrawable(ivStep1before);
            holder.iv2.setImageDrawable(ivStep2before);
            holder.iv3.setImageDrawable(ivStep3before);
            holder.iv4.setImageDrawable(ivStep4before);
        }if (status.equals("2")){
            holder.iv1.setImageDrawable(ivStep1After);
            holder.iv2.setImageDrawable(ivStep2before);
            holder.iv3.setImageDrawable(ivStep3before);
            holder.iv4.setImageDrawable(ivStep4before);
        }if (status.equals("3")){
            holder.iv1.setImageDrawable(ivStep1After);
            holder.iv2.setImageDrawable(ivStep2After);
            holder.iv3.setImageDrawable(ivStep3before);
            holder.iv4.setImageDrawable(ivStep4before);
        }if (status.equals("4")){
            holder.iv1.setImageDrawable(ivStep1After);
            holder.iv2.setImageDrawable(ivStep2After);
            holder.iv3.setImageDrawable(ivStep3After);
            holder.iv4.setImageDrawable(ivStep4before);
        }if (status.equals("5")){
            holder.iv1.setImageDrawable(ivStep1After);
            holder.iv2.setImageDrawable(ivStep2After);
            holder.iv3.setImageDrawable(ivStep3After);
            holder.iv4.setImageDrawable(ivStep4After);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), StatusPengirimanActivity.class);
                intent.putExtra("TRANSID",order.get(position).getTrans_id());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return order.size();
    }

    public class MYholde extends RecyclerView.ViewHolder {
        TextView tvNamarm, tvHarga, tvAlamatUser;
        ImageView iv1, iv2, iv3, iv4;
        public MYholde(View itemView) {
            super(itemView);
            tvNamarm = (TextView) itemView.findViewById(R.id.tvNamaRmListProgress);
            tvHarga = (TextView) itemView.findViewById(R.id.tvHargaListProgress);
            tvAlamatUser = (TextView) itemView.findViewById(R.id.tvAlamatUserListProgress);

            iv1 = (ImageView) itemView.findViewById(R.id.ivListProgress1);
            iv2 = (ImageView) itemView.findViewById(R.id.ivListProgress2);
            iv3 = (ImageView) itemView.findViewById(R.id.ivListProgress3);
            iv4 = (ImageView) itemView.findViewById(R.id.ivListProgress4);

        }
    }
}
