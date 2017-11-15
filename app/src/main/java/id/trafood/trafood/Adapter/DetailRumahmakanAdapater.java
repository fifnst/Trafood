package id.trafood.trafood.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.trafood.trafood.Models.Rumahmakan;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.Connect;

/**
 * Created by kulinerin 1 on 18/10/2017.
 */

public class DetailRumahmakanAdapater extends RecyclerView.Adapter<DetailRumahmakanAdapater.MyViewHolder> {

    List<Rumahmakan> mRumahmakan;

    public DetailRumahmakanAdapater(List<Rumahmakan> mRumahmakan) {
        this.mRumahmakan = mRumahmakan;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_rm, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvNamaRm.setText(mRumahmakan.get(position).getNamarm());
        holder.tvKategori.setText(mRumahmakan.get(position).getKategorirm());
        holder.tvAlamat.setText(mRumahmakan.get(position).getAlamat());
       // holder.tvDeskripsi.setText(mRumahmakan.get(position).getDeskripsirm());
        Glide.with(holder.ivFotoRm.getContext()).load(Connect.IMAGE_RM_URL+mRumahmakan.get(position).getFotorm())
                .error(R.mipmap.ic_launcher).into(holder.ivFotoRm);
    }

    @Override
    public int getItemCount() {
        return mRumahmakan.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNamaRm, tvUrl, tvKategori, tvHari, tvBuka, tvTutup, tvNoTelp;
        public TextView tvAlamat, tvDeskripsi;
        public ImageView ivFotoRm, ivSampul;
     //   public ImageView ivFasilitas, ivFSatu, ivFtiga, ivFEmpat, ivFLima;
        public MyViewHolder(View itemView) {
            super(itemView);

            tvNamaRm = (TextView) itemView.findViewById(R.id.tvNamarmDetail);
            //tvUrl = (TextView) itemView.findViewById(R.id.tvUrlDetail);
            tvKategori = (TextView) itemView.findViewById(R.id.tvKategoriDetail);
           /* tvHari = (TextView) itemView.findViewById(R.id.tvHariDetail);
            tvBuka = (TextView) itemView.findViewById(R.id.tvBukaDetail);
            tvTutup = (TextView) itemView.findViewById(R.id.tvNoTelpDetail);
            tvNoTelp = (TextView) itemView.findViewById(R.id.tvNoTelpDetail);*/
            tvAlamat = (TextView) itemView.findViewById(R.id.tvAlamatDetail);
          //  tvDeskripsi = (TextView) itemView.findViewById(R.id.tvNamarmDetail);

            ivFotoRm = (ImageView) itemView.findViewById(R.id.ivFotoRmDetail);
             /* ivSampul = (ImageView) itemView.findViewById(R.id.ivFotoSampulDetail);
          ivFSatu =(ImageView) itemView.findViewById(R.id.ivFSatu);
            ivFasilitas =(ImageView) itemView.findViewById(R.id.ivFasilitasDetail);
            ivFtiga =(ImageView) itemView.findViewById(R.id.ivFTiga);
            ivFEmpat =(ImageView) itemView.findViewById(R.id.ivFEmpat);
            ivFLima =(ImageView) itemView.findViewById(R.id.ivFLima); */



        }
    }
}
