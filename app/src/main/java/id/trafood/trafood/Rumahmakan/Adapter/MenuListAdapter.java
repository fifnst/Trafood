package id.trafood.trafood.Rumahmakan.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.trafood.trafood.DetailMenu;
import id.trafood.trafood.Models.Menu;
import id.trafood.trafood.Models.MenuDetail;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.Connect;

/**
 * Created by kulinerin 1 on 18/10/2017.
 */

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.MyHolder> {

    List<Menu> mMenu;

    public MenuListAdapter(List<Menu> mMenu) {
        this.mMenu = mMenu;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.litsview_rm_listmenu,parent,false);
        MyHolder myHolder = new MyHolder(v);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.tvLike.setText(mMenu.get(position).getLikes());
        holder.tvHargamenu.setText("Rp. " + mMenu.get(position).getHarga());
        Picasso.with(holder.ivFotomenu.getContext()).load(Connect.IMAGE_MENU_URL+mMenu.get(position).getFoto())
                .error(R.mipmap.ic_launcher).into(holder.ivFotomenu);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailMenu.class);
                intent.putExtra("MENUID", mMenu.get(position).getMenuid());
                intent.putExtra("FOTOMENU", mMenu.get(position).getFoto());
                intent.putExtra("HARGA", mMenu.get(position).getHarga());
                intent.putExtra("NAMAMENU", mMenu.get(position).getNamamenu());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMenu.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView tvHargamenu, tvNamamemu,tvLike;
        public ImageView ivFotomenu;
        public MyHolder(View itemView) {
            super(itemView);

            tvLike = (TextView) itemView.findViewById(R.id.tvLikeMenus);
            tvHargamenu = (TextView) itemView.findViewById(R.id.tvHargaMenuRm);
            ivFotomenu =(ImageView) itemView.findViewById(R.id.ivMenuRM);
        }




    }
}
