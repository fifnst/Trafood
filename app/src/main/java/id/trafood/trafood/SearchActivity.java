package id.trafood.trafood;

import android.content.Context;
import android.content.Intent;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;


import java.util.List;

import id.trafood.trafood.Home.GetModelMenu;
import id.trafood.trafood.Home.HomeMenuAdapter;
import id.trafood.trafood.Home.HomePagerAdapater;
import id.trafood.trafood.Home.ModelMenu;
import id.trafood.trafood.Home.SearchMenuAdapater;
import id.trafood.trafood.Home.SearchRmAdapter;
import id.trafood.trafood.Models.GetMenuDetail;
import id.trafood.trafood.Models.GetRumahmakan;
import id.trafood.trafood.Models.Rumahmakan;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    EditText editText;
    Toolbar toolbar;
    TextView lat,lng,textas,kedai;
    private RecyclerView recyclerViewMenu,recyclerViewRm;
    private RecyclerView.Adapter adapterMenu,adapterRm;
    private RecyclerView.LayoutManager layoutManager, layoutManagerRm;
    public static SearchActivity sa;
    ApiInterface apiInterface;
    String likes,distance,price,cheapest,expensive,wifi,parkir,music,mushola,wc,smoking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editText = (EditText) findViewById(R.id.etSearch);
        lat = (TextView) findViewById(R.id.latSearch);
        lng = (TextView) findViewById(R.id.lngSearch);
        toolbar = (Toolbar) findViewById(R.id.toolbarSearch);
        recyclerViewMenu = (RecyclerView) findViewById(R.id.rvSearchMenu);
        recyclerViewRm = (RecyclerView) findViewById(R.id.rvSearchKedai);
        textas = (TextView) findViewById(R.id.textas);
        kedai = (TextView) findViewById(R.id.textKedai);
        textas.setVisibility(View.GONE);
        kedai.setVisibility(View.GONE);
        final String lats = getIntent().getStringExtra("LAT");
        final String lngs = getIntent().getStringExtra("LNG");
        final String name = getIntent().getStringExtra("NAME");
        lat.setText(lats);
        lng.setText(lngs);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ini");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText.setFocusable(true);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);

        //intent data
        String message = getIntent().getStringExtra("SEARCH");
        editText.setText(message);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    String search = editText.getText().toString();
                    Intent intent = new Intent(SearchActivity.this, HomeActivity.class);
                    intent.putExtra("MESSAGE", search);
                    intent.putExtra("LATS", lats);
                    intent.putExtra("LNGS", lngs);
                    intent.putExtra("NAMES", name);
                    SearchActivity.this.startActivity(intent);
                }
                return false;
            }
        });

        layoutManager = new LinearLayoutManager(this);
        layoutManagerRm = new LinearLayoutManager(this);
        recyclerViewMenu.setLayoutManager(layoutManager);
        recyclerViewRm.setLayoutManager(layoutManagerRm);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        sa = this;

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                String search = editText.getText().toString();
                Call<GetModelMenu> modelMenuCall = apiInterface.getMenuLike(lats,lngs,search,likes,distance,price,cheapest,expensive,wifi,parkir,music,mushola,wc,smoking);
                modelMenuCall.enqueue(new Callback<GetModelMenu>() {
                    @Override
                    public void onResponse(Call<GetModelMenu> call, Response<GetModelMenu> response) {
                        if (response.body().getStatus().equals("200")){
                            textas.setVisibility(View.VISIBLE);
                            recyclerViewMenu.setVisibility(View.VISIBLE);
                            List<ModelMenu> modelMenuList = response.body().getLisModelmenu();
                            adapterMenu = new SearchMenuAdapater(modelMenuList);
                            recyclerViewMenu.setAdapter(adapterMenu);
                        }else{
                            textas.setVisibility(View.GONE);
                            recyclerViewMenu.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onFailure(Call<GetModelMenu> call, Throwable t) {

                    }
                });

                Call<GetRumahmakan> getRumahmakanCall = apiInterface.getRumahmakan(lats,lngs,search,wifi,parkir,music,mushola,wc,smoking);
                getRumahmakanCall.enqueue(new Callback<GetRumahmakan>() {
                    @Override
                    public void onResponse(Call<GetRumahmakan> call, Response<GetRumahmakan> response) {
                        if (response.body().getStatus().equals("200")) {
                            kedai.setVisibility(View.VISIBLE);
                            recyclerViewRm.setVisibility(View.VISIBLE);
                            List<Rumahmakan> rumahmakanList = response.body().getListDataRumahmakan();
                            adapterRm = new SearchRmAdapter(rumahmakanList);
                            recyclerViewRm.setAdapter(adapterRm);
                        }else {
                            kedai.setVisibility(View.GONE);
                            recyclerViewRm.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetRumahmakan> call, Throwable t) {

                    }
                });

                if (editText == null){
                    recyclerViewMenu.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        final  MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("Search... ");
        searchView.setIconified(false);
        //searchView.setOnQueryTextListener(this);
        return true;
    } */


}
