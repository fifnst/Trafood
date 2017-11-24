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

import butterknife.BindView;
import butterknife.ButterKnife;
import id.trafood.trafood.DetailMenu;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.Connect;

/**
 * Created by kulinerin 1 on 30/10/2017.
 */

public class HomeMenuAdapter extends RecyclerView.Adapter<HomeMenuAdapter.MyViewHolder> {
    List<ModelMenu> modelMenus;

    public HomeMenuAdapter(List<ModelMenu> modelMenus) {
        this.modelMenus = modelMenus;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_home_menu, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Picasso.with(holder.ivHomeMenu.getContext()).load(Connect.IMAGE_MENU_URL+modelMenus.get(position).getFoto())
                .error(R.mipmap.ic_launcher).into(holder.ivHomeMenu);
        holder.tvLike.setText(modelMenus.get(position).getLikes());
        holder.tvHarga.setText("Rp. " + modelMenus.get(position).getHarga());
        holder.tvNamaMenu.setText(modelMenus.get(position).getNamarm());
        holder.tvnamaRm.setText(modelMenus.get(position).getNamarm());
        holder.tvKecamatan.setText(modelMenus.get(position).getKecamatan()+", ");
        holder.tvKota.setText(modelMenus.get(position).getKota());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailMenu.class);
                intent.putExtra("USERID", modelMenus.get(position).getUserid());
                intent.putExtra("MENUID", modelMenus.get(position).getMenuid());
                intent.putExtra("FOTOMENU", modelMenus.get(position).getFoto());
                intent.putExtra("HARGA", modelMenus.get(position).getHarga());
                intent.putExtra("NAMAMENU", modelMenus.get(position).getNamamenu());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelMenus.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivHomeMenu;
        public TextView tvLike,tvHarga ,tvNamaMenu ,tvnamaRm,tvKota,tvKecamatan;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivHomeMenu = (ImageView) itemView.findViewById(R.id.ivHomeMenu);
            tvLike = (TextView) itemView.findViewById(R.id.tvLikeHomeMenu);
            tvHarga = (TextView) itemView.findViewById(R.id.tvHargaHomeMenu);
            tvNamaMenu = (TextView) itemView.findViewById(R.id.tvNamamenuHomeMenu);
            tvnamaRm = (TextView) itemView.findViewById(R.id.tvNamaRmMenuHome);
            tvKota = (TextView) itemView.findViewById(R.id.tvKotaHomeMenu);
            tvKecamatan = (TextView) itemView.findViewById(R.id.tvKecamatanHomeMenu);

        }
    }
}
