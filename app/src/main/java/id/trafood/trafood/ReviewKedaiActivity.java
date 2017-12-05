package id.trafood.trafood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import id.trafood.trafood.Models.Comment;
import id.trafood.trafood.Models.GetComment;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rumahmakan.Adapter.RmCommentAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewKedaiActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    TextView textView;
    ApiInterface apiInterface;
    ReviewKedaiActivity rka;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_kedai);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Review");

        textView = (TextView) findViewById(R.id.tvPengumuman);
        recyclerView = (RecyclerView) findViewById(R.id.rvReview);
        progressBar = (ProgressBar) findViewById(R.id.pbReview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        rka=this;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        String rmid = getIntent().getStringExtra("RMID");

        loadReview(rmid);

        recyclerView.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);
    }

    private void loadReview(String rmid) {
        Call<GetComment> commentCall = apiInterface.getComment(rmid);
        commentCall.enqueue(new Callback<GetComment>() {
            @Override
            public void onResponse(Call<GetComment> call, Response<GetComment> response) {
                if (response.body().getStatus().equals("200")){
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    List<Comment> commentList = response.body().getListDataComment();
                    adapter = new RmCommentAdapter(commentList);
                    recyclerView.setAdapter(adapter);
                }else {
                    textView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<GetComment> call, Throwable t) {

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
