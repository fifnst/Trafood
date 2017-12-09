package id.trafood.trafood.Profil;

import android.content.Intent;
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
import id.trafood.trafood.DaftarKedaiActivity;
import id.trafood.trafood.DetailRm;
import id.trafood.trafood.MenuLainActivity;
import id.trafood.trafood.Models.UserView;
import id.trafood.trafood.ProfilVote;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.Connect;
import id.trafood.trafood.RingkasanKedaiActivity;
import id.trafood.trafood.SettingProfilActivity;
import id.trafood.trafood.TambahMenuActivity;
import id.trafood.trafood.UserArticle;

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
    public void onBindViewHolder(MyHolders holder, final int position) {
        String rumahmakan = user.get(position).getRumahmakan();
        holder.linearPengaturanKedai.setVisibility(View.GONE);
        holder.lineardaftarkedai.setVisibility(View.GONE);
        if (rumahmakan.equals("1") ){
            holder.linearPengaturanKedai.setVisibility(View.VISIBLE);
        }
        if (!rumahmakan.equals("1")){
            holder.lineardaftarkedai.setVisibility(View.VISIBLE);
        }
        holder.nama.setText(user.get(position).getNama());
        holder.tvkota.setText(user.get(position).getKota());
        holder.tvabout.setText(user.get(position).getTentang());
        holder.tvartikel.setText("("+user.get(position).getArticle()+")");
        holder.tvulasan.setText("("+user.get(position).getReview()+")");
        Picasso.with(holder.fotoprofil.getContext()).load(Connect.IMAGE_USER+user.get(position).getFotouser())
                .error(R.drawable.user_circle).into(holder.fotoprofil);

        holder.btnTambahMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TambahMenuActivity.class);
                intent.putExtra("USERID",user.get(position).getUserid());
                intent.putExtra("RMID", user.get(position).getRmid());
                view.getContext().startActivity(intent);
            }
        });

        holder.btneditkedai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), DetailRm.class);
                mIntent.putExtra("RMID", user.get(position).getRmid());
                mIntent.putExtra("NAMARM", user.get(position).getNamarm());
                mIntent.putExtra("KATEGORI", user.get(position).getKategorirm());
                mIntent.putExtra("ALAMAT", user.get(position).getAlamatrm());
                mIntent.putExtra("FOTO", user.get(position).getFotorm());
                mIntent.putExtra("LAT", user.get(position).getLatitude());
                mIntent.putExtra("LONG", user.get(position).getLongitude());
                view.getContext().startActivity(mIntent);
            }
        });

        holder.btnRingkasankedai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RingkasanKedaiActivity.class);
                intent.putExtra("RMID", user.get(position).getRmid());
                intent.putExtra("USERID",user.get(position).getUserid());
                intent.putExtra("TITLE", "Ringkasan Kedai");
                view.getContext().startActivity(intent);

            }
        });

        holder.btnReviewProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ProfilVote.class);
                intent.putExtra("USERID",user.get(position).getUserid());
                view.getContext().startActivity(intent);
            }
        });

        holder.btnArticleProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UserArticle.class);
                intent.putExtra("USERID", user.get(position).getUserid());
                view.getContext().startActivity(intent);
            }
        });

        holder.btnPengaturanProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SettingProfilActivity.class);
                intent.putExtra("RMID", user.get(position).getRmid());
                intent.putExtra("USERID",user.get(position).getUserid());
                view.getContext().startActivity(intent);
            }
        });

        holder.btnTambahKedai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DaftarKedaiActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        holder.tvMenuLain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MenuLainActivity.class);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public class MyHolders extends RecyclerView.ViewHolder {
        LinearLayout linearsudahlogin,linearPengaturanKedai,lineardaftarkedai;
        TextView nama,tvkota, tvabout, tvpoint,tvartikel, tvulasan, tvMenuLain;
        CircleImageView fotoprofil;

        ImageView btnTambahMenu,btneditkedai,btnRingkasankedai,btnReviewProfil, btnArticleProfil, btnPengaturanProfil,btnTambahKedai;

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
            tvMenuLain = (TextView) itemView.findViewById(R.id.tvMenuLain);

            fotoprofil = (CircleImageView) itemView.findViewById(R.id.ciFotoProfil);
            btnTambahMenu = (ImageView) itemView.findViewById(R.id.btnTambahMenuProfil);
            btneditkedai = (ImageView) itemView.findViewById(R.id.btnLihatProfil);
            btnRingkasankedai = (ImageView) itemView.findViewById(R.id.btnRingkasankedai);
            btnReviewProfil = (ImageView) itemView.findViewById(R.id.btnReviewProfil);
            btnArticleProfil = (ImageView) itemView.findViewById(R.id.btnArticleProfil);
            btnPengaturanProfil = (ImageView) itemView.findViewById(R.id.btnPengaturanProfil);

            btnTambahKedai = (ImageView) itemView.findViewById(R.id.btnBuatKedai);
            lineardaftarkedai = (LinearLayout) itemView.findViewById(R.id.linearTambahKedai);

        }
    }
}
