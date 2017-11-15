package id.trafood.trafood.Rumahmakan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import id.trafood.trafood.Models.Comment;
import id.trafood.trafood.Models.GetComment;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rumahmakan.Adapter.RmCommentAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Review extends Fragment {
    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static Fragment_Review fr;
    ProgressBar progressBar;
    String rmid;
    TextView textView;

    public Fragment_Review() {
        // Required empty public constructor
    }

    public static Fragment_Review newInstance (Bundle f){
        Fragment_Review tabf = new Fragment_Review();
        tabf.setArguments(f);
        return tabf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rm_review, container, false);
        Bundle bundle = this.getArguments();
        rmid= bundle.getString("rmid");
        recyclerView = (RecyclerView) view.findViewById(R.id.rvComment);
        progressBar = (ProgressBar) view.findViewById(R.id.pbComment);
        textView = (TextView) view.findViewById(R.id.pengumuman);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        fr = this;

        Call<GetComment> commentCall = apiInterface.getComment(rmid);
        commentCall.enqueue(new Callback<GetComment>() {
            @Override
            public void onResponse(Call<GetComment> call, Response<GetComment> response) {
                progressBar.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                List<Comment> CommentList = response.body().getListDataComment();
                adapter = new RmCommentAdapter(CommentList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetComment> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }

}
