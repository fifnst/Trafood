package id.trafood.trafood.Rumahmakan.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.trafood.trafood.GalleryRm;
import id.trafood.trafood.Models.Galery;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.Connect;

/**
 * Created by kulinerin 1 on 23/10/2017.
 */

public class RmGaleryAdapter extends RecyclerView.Adapter<RmGaleryAdapter.MyHolder>{

    List<Galery> mGalery;

    public RmGaleryAdapter(List<Galery> mGalery) {
        this.mGalery = mGalery;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_rm_galeri, parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        Picasso.with(holder.ivGalery.getContext()).load(Connect.IMAGE_GALERY+mGalery.get(position).getFile_name())
                .error(R.mipmap.ic_launcher).into(holder.ivGalery);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), GalleryRm.class);
                mIntent.putExtra("FOTOGALERY", mGalery.get(position).getFile_name());
                view.getContext().startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mGalery.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public ImageView ivGalery;
        public MyHolder(View itemView) {
            super(itemView);

            ivGalery = (ImageView) itemView.findViewById(R.id.ivGaleri);
        }
    }
}
