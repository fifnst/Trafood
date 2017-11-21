package id.trafood.trafood.Profil;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;
import id.trafood.trafood.Models.UserView;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.Connect;

/**
 * Created by kulinerin 1 on 21/11/2017.
 */

public class ProfilUserAdapter extends RecyclerView.Adapter<ProfilUserAdapter.MyHolders> {
    List<UserView> user;

    public ProfilUserAdapter(List<UserView> user) {
        this.user = user;
    }

    @Override
    public MyHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_profil_user, parent, false);
        MyHolders holders = new MyHolders(view);
        return holders;
    }

    @Override
    public void onBindViewHolder(MyHolders holder, int position) {
        String rumahmakan = user.get(position).getRumahmakan();
        holder.linearPengaturanKedai.setVisibility(View.GONE);
        if (rumahmakan.equals("1") ){
            holder.linearPengaturanKedai.setVisibility(View.VISIBLE);
        }
        holder.nama.setText(user.get(position).getNama());
        holder.tvkota.setText(user.get(position).getKota());
        holder.tvabout.setText(user.get(position).getTentang());
        holder.tvartikel.setText("("+user.get(position).getArticle()+")");
        holder.tvulasan.setText("("+user.get(position).getReview()+")");
        Picasso.with(holder.fotoprofil.getContext()).load(Connect.IMAGE_USER+user.get(position).getFotouser())
                .into(holder.fotoprofil);

        holder.btnTambahMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.btneditkedai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.btnRingkasankedai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.btnReviewProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.btnArticleProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.btnPengaturanProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public class MyHolders extends RecyclerView.ViewHolder {
        LinearLayout linearsudahlogin,linearPengaturanKedai;
        TextView nama,tvkota, tvabout, tvpoint,tvartikel, tvulasan;
        CircleImageView fotoprofil;

        ImageView btnTambahMenu,btneditkedai,btnRingkasankedai,btnReviewProfil, btnArticleProfil, btnPengaturanProfil;

        public MyHolders(View itemView) {
            super(itemView);

            linearsudahlogin = (LinearLayout) itemView.findViewById(R.id.linearSudahLogin);
            linearPengaturanKedai = (LinearLayout) itemView.findViewById(R.id.LinearPengaturanKedai);
            nama = (TextView) itemView.findViewById(R.id.tvNamaProfil);
            tvkota = (TextView) itemView.findViewById(R.id.tvKotaProfil);
            tvabout = (TextView) itemView.findViewById(R.id.tvAboutProfil);
            tvpoint = (TextView) itemView.findViewById(R.id.tvPointProfil);
            tvartikel = (TextView) itemView.findViewById(R.id.tvArtikelProfil);
            tvulasan = (TextView) itemView.findViewById(R.id.tvUlasanProfil);

            fotoprofil = (CircleImageView) itemView.findViewById(R.id.ciFotoProfil);
            btnTambahMenu = (ImageView) itemView.findViewById(R.id.btnTambahMenuProfil);
            btneditkedai = (ImageView) itemView.findViewById(R.id.btnLihatProfil);
            btnRingkasankedai = (ImageView) itemView.findViewById(R.id.btnRingkasankedai);
            btnReviewProfil = (ImageView) itemView.findViewById(R.id.btnReviewProfil);
            btnArticleProfil = (ImageView) itemView.findViewById(R.id.btnArticleProfil);
            btnPengaturanProfil = (ImageView) itemView.findViewById(R.id.btnPengaturanProfil);
        }
    }
}
