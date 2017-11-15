package id.trafood.trafood.Rumahmakan.Adapter;

import android.support.annotation.StringDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.xml.transform.Result;

import id.trafood.trafood.Models.Rumahmakan;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.Connect;

/**
 * Created by kulinerin 1 on 24/10/2017.
 */

public class RmInfoAdapter extends RecyclerView.Adapter<RmInfoAdapter.MyHolder> {

    List <Rumahmakan> mRumahmakan;

    public RmInfoAdapter(List<Rumahmakan> mRumahmakan) {
        this.mRumahmakan = mRumahmakan;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_rm_info,parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.tvNoTelp.setText(mRumahmakan.get(position).getNotelp());
        holder.tvDeskripsi.setText(mRumahmakan.get(position).getDeskripsirm());
        int sunday = mRumahmakan.get(position).getSunday();
        int monday = mRumahmakan.get(position).getMonday();
        int tuesday = mRumahmakan.get(position).getTuesday();
        int wednesday = mRumahmakan.get(position).getWednesday();
        int thursday = mRumahmakan.get(position).getThursday();
        int friday = mRumahmakan.get(position).getFriday();
        int saturday = mRumahmakan.get(position).getSaturday();
        String buka = mRumahmakan.get(position).getBuka();
        String tutup = mRumahmakan.get(position).getTutup();
        int fasilitas = mRumahmakan.get(position).getFasilitas() ;
        int fSatu = mRumahmakan.get(position).getFsatu() ;
        int fDua = mRumahmakan.get(position).getFdua() ;
        int fTiga = mRumahmakan.get(position).getFtiga() ;
        int fEmpat = mRumahmakan.get(position).getFempat() ;
        int fLima = mRumahmakan.get(position).getFlima() ;
        //this is for day and hour open
       if (sunday ==1){
            holder.tvSunday.setText(R.string.sunday);
            holder.tvBukaSunday.setText(" : " + buka);
            holder.tvTutupSunday.setText(" - " + tutup);
        }
        if (monday ==1){
            holder.tvMonday.setText(R.string.monday);
            holder.tvBukaMonday.setText(" : " + buka);
            holder.tvTutupMonday.setText(" - " + tutup);
        }
        if (tuesday ==1){
            holder.tvTuesday.setText(R.string.teusday);
            holder.tvBukaTuesday.setText(" : " + buka);
            holder.tvTutupTuesday.setText(" - " + tutup);
        }
        if (wednesday ==1){
            holder.tvWednesday.setText(R.string.wednesday);
            holder.tvBukaWednesday.setText(" : " + buka);
            holder.tvTutupWednesday.setText(" - " + tutup);
        }
        if (thursday ==1){
            holder.tvThrusday.setText(R.string.thursday);
            holder.tvBukaThrusday.setText(" : " + buka);
            holder.tvTutupThrusday.setText(" - " + tutup);
        }
        if (friday ==1){
            holder.tvFriday.setText(R.string.friday);
            holder.tvBukaFriday.setText(" : " + buka);
            holder.tvTutupFriday.setText(" - " + tutup);
        }
        if (saturday ==1){
            holder.tvSaturday.setText(R.string.saturday);
            holder.tvBukaSaturday.setText(" : " + buka);
            holder.tvTutupSaturday.setText(" - " + tutup);
        }

        if (fasilitas == 1){
            Picasso.with(holder.ivFasilitas.getContext())
                    .load(Connect.IMAGE_ASSET+"signal.png").resize(70,70)
                    .into(holder.ivFasilitas);
            holder.tvFasilitas.setText(R.string.wifi);
        }
        if (fSatu ==1 ){
            Picasso.with(holder.ivFSatu.getContext()).load(Connect.IMAGE_ASSET+"parkir.png").resize(70,70)
                    .into(holder.ivFSatu);
            holder.tvFSatu.setText(R.string.parking);

        }
        if (fDua ==1 ){
            Picasso.with(holder.ivFDua.getContext()).load(Connect.IMAGE_ASSET+"parkir.png").resize(70,70)
                    .into(holder.ivFDua);
            holder.tvFDua.setText(R.string.music);
        }
        if (fTiga ==1){
            Picasso.with(holder.ivFtiga.getContext()).load(Connect.IMAGE_ASSET+"mushola.png").resize(70,70)
                    .into(holder.ivFtiga);
            holder.tvFTiga.setText(R.string.mosque);
        }
        if (fEmpat ==1){
            Picasso.with(holder.ivFEmpat.getContext()).load(Connect.IMAGE_ASSET+"wc.png").resize(70,70)
                    .into(holder.ivFEmpat);
            holder.tvFEmpat.setText(R.string.toilet);
        }
        if (fLima ==1){
            Picasso.with(holder.ivFLima.getContext()).load(Connect.IMAGE_ASSET+"smoking.png").resize(70,70)
                    .into(holder.ivFLima);
            holder.tvFlima.setText(R.string.smoking);
        }



    }

    @Override
    public int getItemCount() {
        return mRumahmakan.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView tvNoTelp, tvDeskripsi;
        public TextView tvSunday, tvBukaSunday, tvTutupSunday;
        public TextView tvMonday, tvBukaMonday, tvTutupMonday;
        public TextView tvTuesday, tvBukaTuesday, tvTutupTuesday;
        public TextView tvWednesday, tvBukaWednesday, tvTutupWednesday;
        public TextView tvThrusday, tvBukaThrusday, tvTutupThrusday;
        public TextView tvFriday, tvBukaFriday, tvTutupFriday;
        public TextView tvSaturday, tvBukaSaturday, tvTutupSaturday;
        public ImageView ivFasilitas, ivFSatu, ivFDua, ivFtiga, ivFEmpat, ivFLima;
        public TextView tvFasilitas, tvFSatu, tvFDua, tvFTiga, tvFEmpat, tvFlima;
        public MyHolder(View itemView) {
            super(itemView);
            tvNoTelp = (TextView) itemView.findViewById(R.id.tvNoTelpDetail);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.tvDeskripsiInfo);

            tvSunday = (TextView) itemView.findViewById(R.id.tvSunday);
            tvBukaSunday = (TextView) itemView.findViewById(R.id.tvBukaSunday);
            tvTutupSunday = (TextView) itemView.findViewById(R.id.tvTutupSunday);

            tvMonday = (TextView) itemView.findViewById(R.id.tvMonday);
            tvBukaMonday = (TextView) itemView.findViewById(R.id.tvBukaMonday);
            tvTutupMonday = (TextView) itemView.findViewById(R.id.tvTutupMonday);

            tvTuesday = (TextView) itemView.findViewById(R.id.tvSelasa);
            tvBukaTuesday = (TextView) itemView.findViewById(R.id.tvBukaTuesday);
            tvTutupTuesday = (TextView) itemView.findViewById(R.id.tvTutupTuesday);

            tvWednesday = (TextView) itemView.findViewById(R.id.tvWednesday);
            tvBukaWednesday = (TextView) itemView.findViewById(R.id.tvBukaWednesday);
            tvTutupWednesday = (TextView) itemView.findViewById(R.id.tvTutupWednesday);

            tvThrusday = (TextView) itemView.findViewById(R.id.tvThursday);
            tvBukaThrusday = (TextView) itemView.findViewById(R.id.tvBukaThursday);
            tvTutupThrusday = (TextView) itemView.findViewById(R.id.tvTutupThursday);

            tvFriday = (TextView) itemView.findViewById(R.id.tvFriday);
            tvBukaFriday = (TextView) itemView.findViewById(R.id.tvBukaFriday);
            tvTutupFriday = (TextView) itemView.findViewById(R.id.tvTutupFriday);

            tvSaturday = (TextView) itemView.findViewById(R.id.tvSaturday);
            tvBukaSaturday = (TextView) itemView.findViewById(R.id.tvBukaSaturday);
            tvTutupSaturday = (TextView) itemView.findViewById(R.id.tvTutupSaturday);

            tvFasilitas = (TextView) itemView.findViewById(R.id.tvFasilitas);
            tvFSatu = (TextView) itemView.findViewById(R.id.tvFSatu);
            tvFDua = (TextView) itemView.findViewById(R.id.tvFDua);
            tvFTiga = (TextView) itemView.findViewById(R.id.tvFTiga);
            tvFEmpat = (TextView) itemView.findViewById(R.id.tvFEmpat);
            tvFlima = (TextView) itemView.findViewById(R.id.tvFLima);

            ivFSatu =(ImageView) itemView.findViewById(R.id.ivFSatuInfo);
            ivFasilitas =(ImageView) itemView.findViewById(R.id.ivFasilitasInfo);
            ivFDua = (ImageView) itemView.findViewById(R.id.ivFDuaInfo);
            ivFtiga =(ImageView) itemView.findViewById(R.id.ivFTigaInfo);
            ivFEmpat =(ImageView) itemView.findViewById(R.id.ivFEmpatInfo);
            ivFLima =(ImageView) itemView.findViewById(R.id.ivFLimaInfo);
        }
    }
}
