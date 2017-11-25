package id.trafood.trafood.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import id.trafood.trafood.DetailRm;
import id.trafood.trafood.Models.GetMenu;
import id.trafood.trafood.Models.Rumahmakan;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.Connect;

/**
 * Created by kulinerin 1 on 13/10/2017.
 */

public class RumahmakanAdapter extends RecyclerView.Adapter<RumahmakanAdapter.MyViewHolder> {
    List<Rumahmakan> mRumahmakanList;

    public RumahmakanAdapter(List<Rumahmakan> mRumahmakanList) {
        this.mRumahmakanList = mRumahmakanList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_rumahmakan,parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.textViewNamarm.setText(mRumahmakanList.get(position).getNamarm());
        holder.textViewKategori.setText(mRumahmakanList.get(position).getKategorirm());
        holder.textViewAlamat.setText(mRumahmakanList.get(position).getKota());
        holder.textViewKecamatan.setText(mRumahmakanList.get(position).getKecamatan() + ",");
        Glide.with(holder.imageViewfotoRm.getContext())
                .load(Connect.IMAGE_RM_URL+mRumahmakanList.get(position).getFotosampul())
               .error(R.mipmap.ic_launcher).into(holder.imageViewfotoRm);
        int fasilitas = mRumahmakanList.get(position).getFasilitas() ;
        int fSatu = mRumahmakanList.get(position).getFsatu() ;
        int fDua = mRumahmakanList.get(position).getFdua() ;
        int fTiga = mRumahmakanList.get(position).getFtiga() ;
        int fEmpat = mRumahmakanList.get(position).getFempat() ;
        int fLima = mRumahmakanList.get(position).getFlima() ;
        holder.imageViewFasilitas.setVisibility(View.GONE);
        holder.imageViewFDua.setVisibility(View.GONE);
        holder.imageViewFSatu.setVisibility(View.GONE);
        holder.imageViewFTiga.setVisibility(View.GONE);
        holder.imageViewFEmpat.setVisibility(View.GONE);
        holder.imageViewFlima.setVisibility(View.GONE);
        if (fasilitas == 1){
            holder.imageViewFasilitas.setVisibility(View.VISIBLE);
        }
        if (fSatu ==1 ){
            holder.imageViewFSatu.setVisibility(View.VISIBLE);
        }
        if (fDua ==1 ){
            holder.imageViewFDua.setVisibility(View.VISIBLE);
        }
        if (fTiga ==1){
            holder.imageViewFTiga.setVisibility(View.VISIBLE);
        }
        if (fEmpat ==1){
            holder.imageViewFEmpat.setVisibility(View.VISIBLE);
        }
        if (fLima ==1){
            holder.imageViewFlima.setVisibility(View.VISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), DetailRm.class);
                mIntent.putExtra("RMID", mRumahmakanList.get(position).getRmid());
                mIntent.putExtra("URL", mRumahmakanList.get(position).getUrl());
                mIntent.putExtra("NAMARM", mRumahmakanList.get(position).getNamarm());
                mIntent.putExtra("KATEGORI", mRumahmakanList.get(position).getKategorirm());
                mIntent.putExtra("ALAMAT", mRumahmakanList.get(position).getAlamat());
                mIntent.putExtra("FOTO", mRumahmakanList.get(position).getFotosampul());
                mIntent.putExtra("LAT", mRumahmakanList.get(position).getLatitude());
                mIntent.putExtra("LONG", mRumahmakanList.get(position).getLongitude());

                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRumahmakanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  textViewNamarm, textViewAlamat,textViewKecamatan,textViewKategori;
        public ImageView imageViewfotoRm, imageViewFasilitas, imageViewFSatu, imageViewFTiga;
        public  ImageView imageViewFEmpat, imageViewFlima, imageViewFDua;
        public MyViewHolder(View itemView) {
            super(itemView);

            textViewKategori = (TextView) itemView.findViewById(R.id.tvKategoriRm);
            textViewKecamatan = (TextView) itemView.findViewById(R.id.tvKecamatan);
            textViewNamarm = (TextView) itemView.findViewById(R.id.tvNamarm);
            textViewAlamat = (TextView) itemView.findViewById(R.id.tvAlamat);
            imageViewfotoRm = (ImageView) itemView.findViewById(R.id.ivFotorm);
            imageViewFasilitas = (ImageView) itemView.findViewById(R.id.ivFasilitas);
            imageViewFSatu = (ImageView) itemView.findViewById(R.id.ivFSatu);
            imageViewFTiga = (ImageView) itemView.findViewById(R.id.ivFTiga);
            imageViewFEmpat = (ImageView) itemView.findViewById(R.id.ivFEmpat);
            imageViewFlima = (ImageView) itemView.findViewById(R.id.ivFLima);
            imageViewFDua = (ImageView) itemView.findViewById(R.id.ivFDua);
         }
    }
}
