package id.trafood.trafood.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.trafood.trafood.HomeActivity;
import id.trafood.trafood.Models.Location;
import id.trafood.trafood.R;

/**
 * Created by kulinerin 1 on 07/11/2017.
 */

public class LocationAdapaterSearch extends RecyclerView.Adapter<LocationAdapaterSearch.MyHolder> {
    List<Location> mLocation;

    public LocationAdapaterSearch(List<Location> mLocation) {
        this.mLocation = mLocation;
    }

    @Override
    public LocationAdapaterSearch.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_location, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(LocationAdapaterSearch.MyHolder holder, final int position) {
        holder.district.setText(mLocation.get(position).getDistrict());
        holder.city.setText(mLocation.get(position).getCity());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), HomeActivity.class);
                intent.putExtra("LATS",mLocation.get(position).getLat());
                intent.putExtra("LNGS", mLocation.get(position).getLng());
                intent.putExtra("NAMES" , mLocation.get(position).getDistrict());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLocation.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView district, city;
        public MyHolder(View itemView) {
            super(itemView);
            district = (TextView) itemView.findViewById(R.id.tvDistrictLocation);
            city = (TextView) itemView.findViewById(R.id.tvCityLocation);
        }
    }
}
