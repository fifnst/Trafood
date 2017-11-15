package id.trafood.trafood.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.trafood.trafood.HomeActivity;
import id.trafood.trafood.MainActivity;
import id.trafood.trafood.Models.Location;
import id.trafood.trafood.R;

/**
 * Created by kulinerin 1 on 06/11/2017.
 */

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyHolders> {

    List<Location> mLocation;

    public LocationAdapter(List<Location> mLocation) {
        this.mLocation = mLocation;
    }

    @Override
    public MyHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_location,parent, false);
        MyHolders holders = new MyHolders(view);
        return holders;
    }

    @Override
    public void onBindViewHolder(final MyHolders holder, final int position) {
        holder.district.setText(mLocation.get(position).getDistrict());
        holder.city.setText(mLocation.get(position).getCity());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent mIntent = new Intent(view.getContext(), MainActivity.class);
                        mIntent.putExtra("LATS",mLocation.get(position).getLat());
                        mIntent.putExtra("LNGS", mLocation.get(position).getLng());
                        mIntent.putExtra("NAME" , mLocation.get(position).getDistrict());
                        view.getContext().startActivity(mIntent);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLocation.size();
    }

    public class MyHolders extends RecyclerView.ViewHolder {
        public TextView district, city;
        public MyHolders(View itemView) {
            super(itemView);
            district = (TextView) itemView.findViewById(R.id.tvDistrictLocation);
            city = (TextView) itemView.findViewById(R.id.tvCityLocation);
        }
    }
}
