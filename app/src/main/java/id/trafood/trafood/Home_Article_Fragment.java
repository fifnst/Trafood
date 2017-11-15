package id.trafood.trafood;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import id.trafood.trafood.Home.ArticleAdapter;
import id.trafood.trafood.Models.Article;
import id.trafood.trafood.Models.GetArticle;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Article_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    public Home_Article_Fragment haf;
    ApiInterface apiInterface;
    ProgressBar progressBar;

    public Home_Article_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home__article_, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvArticle);
        progressBar = (ProgressBar) view.findViewById(R.id.pbArticle);

        layoutManager = new GridLayoutManager(this.getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        haf = this;

        loadArticles();

        return view;
    }

    public void loadArticles() {
        Call<GetArticle> articleCall = apiInterface.getArticle();
        articleCall.enqueue(new Callback<GetArticle>() {
            @Override
            public void onResponse(Call<GetArticle> call, Response<GetArticle> response) {
                progressBar.setVisibility(View.GONE);
                List<Article> articleList = response.body().getGetListArticle();
                adapter = new ArticleAdapter(articleList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetArticle> call, Throwable t) {

            }
        });
    }


}
