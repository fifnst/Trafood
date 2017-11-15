package id.trafood.trafood.Home;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.trafood.trafood.DetailRm;
import id.trafood.trafood.Models.Rumahmakan;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.Connect;

/**
 * Created by kulinerin 1 on 15/11/2017.
 */

public class SearchRmAdapter extends RecyclerView.Adapter<SearchRmAdapter.Holders> {

    List<Rumahmakan> mRumahmakanList;

    public SearchRmAdapter(List<Rumahmakan> mRumahmakanList) {
        this.mRumahmakanList = mRumahmakanList;
    }

    @Override
    public Holders onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_search_suggestionmenu,parent,false);
        Holders holders = new Holders(view);
        return holders;
    }

    @Override
    public void onBindViewHolder(Holders holder, final int position) {
        Picasso.with(holder.imageView.getContext()).load(Connect.IMAGE_RM_URL+mRumahmakanList.get(position).getFotosampul())
                .error(R.mipmap.ic_launcher).into(holder.imageView);
        holder.nama.setText(mRumahmakanList.get(position).getNamarm());
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

    public class Holders extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView nama;
        public Holders(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.ivFotoRmSearch);
            nama = (TextView) itemView.findViewById(R.id.tvNamaRmSearch);
        }
    }
}
