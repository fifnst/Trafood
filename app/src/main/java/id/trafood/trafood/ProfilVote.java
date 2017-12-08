package id.trafood.trafood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.List;

import id.trafood.trafood.Models.GetUserVote;
import id.trafood.trafood.Models.UserVote;
import id.trafood.trafood.Profil.VoteAdapter;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilVote extends AppCompatActivity {

    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static ProfilVote pv;
    ProgressBar progressBar;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_vote);
        Intent intent = getIntent();
        String userid = intent.getStringExtra("USERID");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Review");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_green_24dp);
        getSupportActionBar().setElevation(0);

        recyclerView = (RecyclerView) findViewById(R.id.rvUserVote);
        progressBar = (ProgressBar) findViewById(R.id.pbVote);
        linearLayout = (LinearLayout) findViewById(R.id.LinearVote) ;
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        pv = this;
        linearLayout.setVisibility(View.GONE);

        Call<GetUserVote> votingCall = apiInterface.getVote(userid);
        votingCall.enqueue(new Callback<GetUserVote>() {
            @Override
            public void onResponse(Call<GetUserVote> call, Response<GetUserVote> response) {
                if(response.body().getStatus().equals("200")){
                    progressBar.setVisibility(View.GONE);
                    List<UserVote> uservote = response.body().getListUserVote();
                    adapter = new VoteAdapter(uservote);
                    recyclerView.setAdapter(adapter);
                }else {
                    linearLayout.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<GetUserVote> call, Throwable t) {

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
}
