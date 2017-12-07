package id.trafood.trafood.Rumahmakan.Adapter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.trafood.trafood.Models.Comment;
import id.trafood.trafood.ProfilGuest;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.Connect;

/**
 * Created by kulinerin 1 on 24/10/2017.
 */

public class RmCommentAdapter extends RecyclerView.Adapter<RmCommentAdapter.MyHolder> {

    List<Comment> mComment;

    public RmCommentAdapter(List<Comment> mComment) {
        this.mComment = mComment;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_rm_comment, parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        Picasso.with(holder.ivFotouser.getContext()).load(Connect.IMAGE_USER+mComment.get(position).getFotouser())
                .error(R.mipmap.ic_launcher).into(holder.ivFotouser);
        holder.tvNama.setText(mComment.get(position).getNama());
        holder.tvBody.setText(mComment.get(position).getBody());
        holder.tvDate.setText(mComment.get(position).getDatecretae());
        holder.tvKota.setText(mComment.get(position).getKota());

        String rating1 = mComment.get(position).getRating1();
        String rating2 = mComment.get(position).getRating2();
        String rating3 = mComment.get(position).getRating3();
        String rating4 = mComment.get(position).getRating4();

        if (rating1.equals("null") && rating2 .equals("null") && rating3 .equals("null") && rating4 .equals("null") ){
            holder.tvNilai.setText("");
        }
        else if (rating1 != null && rating2 != null && rating3 != null && rating4 != null ) {
            Double rating1d;
            Double rating2d;
            Double rating3d;
            Double rating4d;
            rating1d = Double.parseDouble(rating1);
            rating2d = Double.parseDouble(rating2);
            rating3d = Double.parseDouble(rating3);
            rating4d = Double.parseDouble(rating4);
            Double total = rating1d+rating2d+rating3d+rating4d;
            DecimalFormat dfs = new DecimalFormat("#.#");
            String dx = dfs.format(total);
            holder.tvNilai.setText(dx);
            if (total >= 0 && total <=2 ){
                holder.ivNilai.setImageResource(R.drawable.nol_duapuluh);
                holder.tvNilai.setTextColor(ContextCompat.getColor(holder.tvNilai.getContext(), R.color.blue));
            }if (total > 2 && total <=4 ){
                holder.ivNilai.setImageResource(R.drawable.duapuluhpatpuluh);
                holder.tvNilai.setTextColor(ContextCompat.getColor(holder.tvNilai.getContext(), R.color.jad));
            }if (total > 4 && total <=6 ){
                holder.ivNilai.setImageResource(R.drawable.patpuluh);
                holder.tvNilai.setTextColor(ContextCompat.getColor(holder.tvNilai.getContext(), R.color.yellow));
            }if (total > 6 && total <=8 ){
                holder.ivNilai.setImageResource(R.drawable.nepuluh);
                holder.tvNilai.setTextColor(ContextCompat.getColor(holder.tvNilai.getContext(), R.color.ectasy));
            }if (total > 8 && total <=10 ){
                holder.ivNilai.setImageResource(R.drawable.lapanpuluh);
                holder.tvNilai.setTextColor(ContextCompat.getColor(holder.tvNilai.getContext(), R.color.alizarin));
            }

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), ProfilGuest.class);
                mIntent.putExtra("USERID",mComment.get(position).getUserid());
                mIntent.putExtra("NAMAUSER", mComment.get(position).getNama());
                view.getContext().startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mComment.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public CircleImageView ivFotouser;
        ImageView  ivNilai;
        public TextView tvNama, tvBody, tvDate,tvKota, tvNilai;

        public MyHolder(View itemView) {
            super(itemView);

            ivFotouser = (CircleImageView) itemView.findViewById(R.id.ivFotoUserComment);
            ivNilai = (ImageView) itemView.findViewById(R.id.ivNilaiComment);

            tvNama = (TextView) itemView.findViewById(R.id.tvNamaUserComment);
            tvBody = (TextView) itemView.findViewById(R.id.tvBodyComment);
            tvDate = (TextView) itemView.findViewById(R.id.tvDateComment);
            tvKota = (TextView) itemView.findViewById(R.id.tvKotaComment);
            tvNilai = (TextView) itemView.findViewById(R.id.tvNilai);
        }
    }
}
