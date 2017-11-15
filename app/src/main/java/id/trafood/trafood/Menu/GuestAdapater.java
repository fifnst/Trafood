package id.trafood.trafood.Menu;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.trafood.trafood.Models.UserView;
import id.trafood.trafood.ProfilVote;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.Connect;
import id.trafood.trafood.UserArticle;

/**
 * Created by kulinerin 1 on 27/10/2017.
 */

public class GuestAdapater extends RecyclerView.Adapter<GuestAdapater.MyHolder> {

    List<UserView> mUser;

    public GuestAdapater(List<UserView> mUser) {
        this.mUser = mUser;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_profil_guest, parent, false),listener);
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_profil_guest, parent, false);
         MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        Picasso.with(holder.ivFoto.getContext()).load(Connect.IMAGE_USER+mUser.get(position).getFotouser())
                .error(R.mipmap.ic_launcher).into(holder.ivFoto);
        holder.tvKota.setText(mUser.get(position).getKota());
        holder.tvAbout.setText(mUser.get(position).getTentang());
        holder.tvPoint.setText(R.string.point);
        holder.tvUlasan.setText(mUser.get(position).getVisit());
        holder.tvArtikel.setText(mUser.get(position).getArticle());
        holder.tvPengaturan.setText(R.string.pengaturan );

        holder.ivReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "masuk ke review", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), ProfilVote.class);
                intent.putExtra("USERID",mUser.get(position).getUserid());
                view.getContext().startActivity(intent);
            }
        });

        holder.ivArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UserArticle.class);
                intent.putExtra("USERID", mUser.get(position).getUserid());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView tvUlasan;
        public TextView tvArtikel;
        public TextView tvPengaturan;
        public CircleImageView ivFoto,ivReview, ivArticle;
        public TextView tvPoint,tvKota,tvAbout;
        public MyHolder(View itemView) {
            super(itemView);
            ivFoto = (CircleImageView) itemView.findViewById(R.id.ivFotoUserGuest);
            tvKota = (TextView) itemView.findViewById(R.id.tvKotaUserGuest);
            tvAbout  = (TextView) itemView.findViewById(R.id.tvAboutUserguest);
            tvUlasan = (TextView) itemView.findViewById(R.id.tvulasan);
            tvArtikel  = (TextView) itemView.findViewById(R.id.tvArtikel);
            tvPengaturan  = (TextView) itemView.findViewById(R.id.tvPengaturan);
            tvPoint = (TextView) itemView.findViewById(R.id.kumhassia);
            ivReview = (CircleImageView) itemView.findViewById(R.id.btnReview);
            ivArticle = (CircleImageView) itemView.findViewById(R.id.btnArticle);

        }
    }
}
