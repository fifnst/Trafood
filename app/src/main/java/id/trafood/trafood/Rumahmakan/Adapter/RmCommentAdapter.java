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
        public TextView tvNama, tvBody, tvDate;

        public MyHolder(View itemView) {
            super(itemView);

            ivFotouser = (CircleImageView) itemView.findViewById(R.id.ivFotoUserComment);
            tvNama = (TextView) itemView.findViewById(R.id.tvNamaUserComment);
            tvBody = (TextView) itemView.findViewById(R.id.tvBodyComment);
            tvDate = (TextView) itemView.findViewById(R.id.tvDateComment);
        }
    }
}
