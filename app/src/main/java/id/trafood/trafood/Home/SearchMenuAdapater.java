package id.trafood.trafood.Home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.trafood.trafood.DetailMenu;
import id.trafood.trafood.MainActivity;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.Connect;

/**
 * Created by kulinerin 1 on 15/11/2017.
 */

public class SearchMenuAdapater extends RecyclerView.Adapter<SearchMenuAdapater.Holder> {
    List<ModelMenu> mMenu;

    public SearchMenuAdapater(List<ModelMenu> mMenu) {
        this.mMenu = mMenu;
    }

    @Override
    public SearchMenuAdapater.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_search_suggestionrm, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SearchMenuAdapater.Holder holder, final int position) {
        Picasso.with(holder.imageView.getContext()).load(Connect.IMAGE_MENU_URL+mMenu.get(position).getFoto())
                .error(R.mipmap.ic_launcher).into(holder.imageView);
        holder.nama.setText(mMenu.get(position).getNamamenu());
        holder.hargas.setText("Rp. " + mMenu.get(position).getHarga());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailMenu.class);
                intent.putExtra("USERID", mMenu.get(position).getUserid());
                intent.putExtra("MENUID", mMenu.get(position).getMenuid());
                intent.putExtra("FOTOMENU", mMenu.get(position).getFoto());
                intent.putExtra("HARGA", mMenu.get(position).getHarga());
                intent.putExtra("NAMAMENU", mMenu.get(position).getNamamenu());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMenu.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView nama, hargas;
        public Holder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.ivFotoMenuSearch);
            nama = (TextView) itemView.findViewById(R.id.tvNamaMenuSearch);
            hargas = (TextView) itemView.findViewById(R.id.tvHargaSearch);
        }
    }
}
