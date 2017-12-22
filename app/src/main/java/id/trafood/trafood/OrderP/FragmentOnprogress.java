package id.trafood.trafood.OrderP;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import id.trafood.trafood.Models.GetOrderHistory;
import id.trafood.trafood.Models.OrderHistory;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOnprogress extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ApiInterface apiInterface;

    public static FragmentOnprogress fo;
    SharedPrefManager sharedPrefManager;
    String userid;

    public FragmentOnprogress() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_fragment_onprogress, container, false);
        sharedPrefManager = new SharedPrefManager(view.getContext());
        userid = sharedPrefManager.getSpUserid();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        fo =this;
        recyclerView = (RecyclerView) view.findViewById(R.id.rvOnProgress);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        setProgress();

        return view;
    }

    public void setProgress() {
        userid = sharedPrefManager.getSpUserid();
        Call<GetOrderHistory> orderHistories = apiInterface.getOrderProgress(userid);
        orderHistories.enqueue(new Callback<GetOrderHistory>() {
            @Override
            public void onResponse(Call<GetOrderHistory> call, Response<GetOrderHistory> response) {
                if (response.body().getStatus().equals("200")){
                    List<OrderHistory> orderHistories1 = response.body().getListDataOrderHistory();
                    adapter = new OnProgressAdapter(orderHistories1);
                    recyclerView.setAdapter(adapter);
                    Log.d("TAG",userid+"jumlah "+orderHistories1.size());
                }else{
                    Toast.makeText(recyclerView.getContext(), "d", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetOrderHistory> call, Throwable t) {

            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        setProgress();
    }
}
