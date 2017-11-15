package id.trafood.trafood.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.trafood.trafood.Models.Menu;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.Connect;

/**
 * Created by kulinerin 1 on 16/10/2017.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyHolder> {

    //buat variable
    Context con;
    List <Menu> data_menu;

    public MenuAdapter(Context con, List<Menu> data_menu) {
        this.con = con;
        this.data_menu = data_menu;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_menu,parent,false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.textViewmenuid.setText(data_menu.get(position).getMenuid());
        holder.textViewnamamenu.setText(data_menu.get(position).getNamamenu());
        holder.textViewHarga.setText(data_menu.get(position).getHarga());
        holder.textViewlikes.setText(data_menu.get(position).getLikes());
        holder.textViewrmid.setText(data_menu.get(position).getRmid());
        Picasso.with(con).load(Connect.IMAGE_MENU_URL+data_menu.get(position).
        getFoto()).error(R.mipmap.ic_launcher).into(holder.imgfotomenu);
    }

    @Override
    public int getItemCount() {
        return data_menu.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imgfotomenu;
        TextView textViewmenuid, textViewnamamenu, textViewHarga, textViewrmid, textViewnamarm, textViewlikes;
        public MyHolder(View itemView) {
            super(itemView);
            imgfotomenu = (ImageView) itemView.findViewById(R.id.ivFotoMenu);
            textViewmenuid = (TextView) itemView.findViewById(R.id.tvMenuID);
            textViewnamamenu = (TextView) itemView.findViewById(R.id.tvNamamenu);
            textViewHarga = (TextView) itemView.findViewById(R.id.tvHargaMenu);
            textViewrmid = (TextView) itemView.findViewById(R.id.tvRmidMenu);
            textViewnamarm= (TextView) itemView.findViewById(R.id.tvNamarmMenu);
            textViewlikes = (TextView) itemView.findViewById(R.id.tvLikesMenu);
        }
    }
}
