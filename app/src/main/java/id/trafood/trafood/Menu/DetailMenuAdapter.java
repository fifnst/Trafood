package id.trafood.trafood.Menu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.trafood.trafood.DetailMenu;
import id.trafood.trafood.Models.MenuDetail;
import id.trafood.trafood.R;

/**
 * Created by kulinerin 1 on 27/10/2017.
 */

public class DetailMenuAdapter extends RecyclerView.Adapter<DetailMenuAdapter.MyHolder> {
    List<MenuDetail> mMenu;

    public DetailMenuAdapter(List<MenuDetail> mMenu) {
        this.mMenu = mMenu;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_menu_detail, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.tvNamamenu.setText(mMenu.get(position).getNamamenu());
        holder.tvDeskrispisMenu.setText(mMenu.get(position).getDeskripsi());
        holder.tvLike.setText(mMenu.get(position).getLikes());
        holder.tvNamaRm.setText(mMenu.get(position).getNamarm());
        holder.tvAlamat.setText(mMenu.get(position).getAlamat());
        holder.tvKategoriRm.setText(mMenu.get(position).getKategorirm());
        holder.tvTagMenu.setText(mMenu.get(position).getTag());
        holder.tvDilihat.setText(mMenu.get(position).getDilihat());

        /*String tag = mMenu.get(position).getTag();
        ArrayList<String> StringArray = new ArrayList<String>();
        String[] kata = tag.split(" ");

        for(int i=0; i< kata.length; i++){
            holder.textViewArray[i].setText(kata[i]);
        }*/

    }

    @Override
    public int getItemCount() {
        return mMenu.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView tvNamamenu, tvDeskrispisMenu, tvLike, tvNamaRm, tvAlamat,tvTagMenu,tvKategoriRm, tvDilihat;
        //public TextView[] textViewArray = new TextView[10];

        public MyHolder(View itemView) {
            super(itemView);
            tvNamamenu = (TextView) itemView.findViewById(R.id.tvNamaMenuDetail);
            tvDeskrispisMenu = (TextView) itemView.findViewById(R.id.tvDeskripsiMenu);
            tvLike = (TextView) itemView.findViewById(R.id.tvLikesDetail);
            tvNamaRm = (TextView) itemView.findViewById(R.id.tvNamaRmMenuDetail);
            tvAlamat = (TextView) itemView.findViewById(R.id.tvAlamatMenuDetail);
            tvKategoriRm = (TextView) itemView.findViewById(R.id.tvKategoriRmMenuDetail);
            tvTagMenu = (TextView) itemView.findViewById(R.id.tvTagMenu);
            tvDilihat = (TextView) itemView.findViewById(R.id.tvdilihatDetail);


            /*int textViewCount = 10;
            //TextView[] textViewArray = new TextView[10];

            for(int i = 0; i < textViewCount; i++) {
                textViewArray[i] = itemView.findViewById(R.id.tvTagMenu);
            }*/

        }
    }
}
