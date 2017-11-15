package id.trafood.trafood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import id.trafood.trafood.Adapter.LocationAdapaterSearch;
import id.trafood.trafood.Adapter.LocationAdapter;
import id.trafood.trafood.Models.GetLocation;
import id.trafood.trafood.Models.Location;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationSearch extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static LocationSearch ls;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Select Location");

        recyclerView = (RecyclerView) findViewById(R.id.rvLocationSearch);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        ls = this;

        Call<GetLocation> getLocationCall = apiInterface.getLocation();
        getLocationCall.enqueue(new Callback<GetLocation>() {
            @Override
            public void onResponse(Call<GetLocation> call, Response<GetLocation> response) {
                List<Location> locations = response.body().getLisLocation();
                adapter = new LocationAdapaterSearch(locations);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetLocation> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void carialamat(View view) {
        String message = getIntent().getStringExtra("SEARCH");
        Intent mIntent = new Intent(this, HomeActivity.class);
        mIntent.putExtra("NEAR","1");
        mIntent.putExtra("PESAN", message);
        mIntent.putExtra("NAMES", "Nearest");
        this.startActivity(mIntent);
        Toast.makeText(this, "Location detected", Toast.LENGTH_SHORT).show();
    }
}
