package id.trafood.trafood.Profil;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.trafood.trafood.Models.UserVote;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.Connect;

/**
 * Created by kulinerin 1 on 28/10/2017.
 */

public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.MyHolder>{

    List<UserVote> uVotes;

    public VoteAdapter(List<UserVote> uVotes) {
        this.uVotes = uVotes;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_profil_vote, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Picasso.with(holder.ivFoto.getContext()).load(Connect.IMAGE_RM_URL+uVotes.get(position).getFotosampul())
                .error(R.mipmap.ic_launcher).into(holder.ivFoto);
        holder.namarm.setText(uVotes.get(position).getNamarm());
        holder.tvkecamatan.setText(uVotes.get(position).getKecamatan()+", ");
        holder.tvkota.setText(uVotes.get(position).getKota());
        holder.tvbody.setText(uVotes.get(position).getBody());
        holder.tvDateCreate.setText(uVotes.get(position).getDatecreate());
        String r1 = uVotes.get(position).getRating1();
        String r2 = uVotes.get(position).getRating2();
        String r3 = uVotes.get(position).getRating3();
        String r4 = uVotes.get(position).getRating4();
        Double doubler1 = Double.parseDouble(r1);
        Double doubler2 = Double.parseDouble(r2);
        Double doubler3 = Double.parseDouble(r3);
        Double doubler4 = Double.parseDouble(r4);
        double vote = (doubler1+doubler2+doubler3+doubler4);
        String review = String.valueOf(vote);
        holder.tvVote.setText(review);
    }

    @Override
    public int getItemCount() {
        return uVotes.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public ImageView ivEmot, ivFoto;
        public TextView namarm, tvkecamatan, tvkota, tvbody, tvDateCreate, tvVote;
        public MyHolder(View itemView) {
            super(itemView);
            ivEmot = (ImageView) itemView.findViewById(R.id.ivEmot);
            ivFoto = (ImageView) itemView.findViewById(R.id.ivFotoRmVote);
            namarm = (TextView) itemView.findViewById(R.id.tvNamaRmVote);
            tvkecamatan = (TextView) itemView.findViewById(R.id.tvKecamatanVote);
            tvkota = (TextView) itemView.findViewById(R.id.tvKotaVote);
            tvbody = (TextView) itemView.findViewById(R.id.tvReviewVote);
            tvDateCreate = (TextView) itemView.findViewById(R.id.tvdateVote);
            tvVote = (TextView) itemView.findViewById(R.id.tvVote);

        }
    }
}
