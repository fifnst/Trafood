package id.trafood.trafood.OrderP;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.trafood.trafood.ConfirmationActivity;
import id.trafood.trafood.Models.Courier;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.Connect;

/**
 * Created by kulinerin 1 on 15/12/2017.
 */

public class CourierAdapter extends RecyclerView.Adapter<CourierAdapter.MuHolder> {
    List<Courier> mCourier;

    public CourierAdapter(List<Courier> mCourier) {
        this.mCourier = mCourier;
    }

    @Override
    public MuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_courier,parent,false);
        MuHolder holder = new MuHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MuHolder holder, final int position) {
        Picasso.with(holder.logo.getContext()).load(Connect.IMAGE_COURIER+mCourier.get(position).getC_logo())
                .error(R.mipmap.ic_launcher).into(holder.logo);
        holder.namakurir.setText(mCourier.get(position).getC_name());
        holder.logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ConfirmationActivity.class);
                intent.putExtra("COURIERID",mCourier.get(position).getCourier_id());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCourier.size();
    }

    public class MuHolder extends RecyclerView.ViewHolder {
        public CircleImageView logo;
        public TextView namakurir;
        public MuHolder(View itemView) {
            super(itemView);
            logo = (CircleImageView) itemView.findViewById(R.id.CiLogoKurir);
            namakurir = (TextView) itemView.findViewById(R.id.tvNamaKurir);
        }
    }
}
