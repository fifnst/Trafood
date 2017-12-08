package id.trafood.trafood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.List;

import id.trafood.trafood.Home.ArticleUserAdapater;
import id.trafood.trafood.Models.Article;
import id.trafood.trafood.Models.GetArticle;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserArticle extends AppCompatActivity {
    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static UserArticle ua;
    ProgressBar progressBar;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_article);
        Intent intent = getIntent();
        String userid = intent.getStringExtra("USERID");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Article");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_green_24dp);
        getSupportActionBar().setElevation(0);

        recyclerView = (RecyclerView) findViewById(R.id.rvArticleUser);
        progressBar = (ProgressBar) findViewById(R.id.pbArticleUser);
        linearLayout = (LinearLayout) findViewById(R.id.LinearArticleUser) ;
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        linearLayout.setVisibility(View.GONE);
        ua = this;

        setArticle(userid);
    }

    private void setArticle(String userid) {
        Call<GetArticle> articleCall = apiInterface.getArticleUser(userid);
        articleCall.enqueue(new Callback<GetArticle>() {
            @Override
            public void onResponse(Call<GetArticle> call, Response<GetArticle> response) {
                if(response.body().getStatus().equals("200")){
                    progressBar.setVisibility(View.GONE);
                    List<Article> userarticle = response.body().getGetListArticle();
                    adapter = new ArticleUserAdapater(userarticle);
                    recyclerView.setAdapter(adapter);
                }else {
                    progressBar.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<GetArticle> call, Throwable t) {

            }
        });
    }

    //
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
