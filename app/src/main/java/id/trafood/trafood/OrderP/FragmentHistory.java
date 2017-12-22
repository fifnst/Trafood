package id.trafood.trafood.OrderP;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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
public class FragmentHistory extends Fragment {
    ApiInterface apiInterface;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    public  static  FragmentHistory fh;
    SharedPrefManager sharedPrefManager;

    public FragmentHistory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_history, container, false);
        sharedPrefManager = new SharedPrefManager(view.getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.rvHistory);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        fh = this;

        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        setHistory();

        return view;
    }

    public void setHistory() {
        String userid = sharedPrefManager.getSpUserid();

        Call<GetOrderHistory> orderHistoryCall = apiInterface.getOrderHistory(userid);
        orderHistoryCall.enqueue(new Callback<GetOrderHistory>() {
            @Override
            public void onResponse(Call<GetOrderHistory> call, Response<GetOrderHistory> response) {
                if (response.body().getStatus().equals("200")){
                    List<OrderHistory> orderHistories = response.body().getListDataOrderHistory();
                    adapter = new HistoryAdapter(orderHistories);
                    recyclerView.setAdapter(adapter);
                }else {
                    Toast.makeText(recyclerView.getContext(), "asdasd", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetOrderHistory> call, Throwable t) {

            }
        });
    }

}
