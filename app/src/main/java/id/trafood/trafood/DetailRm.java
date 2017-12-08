package id.trafood.trafood;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import id.trafood.trafood.Adapter.DetailRumahmakanAdapater;
import id.trafood.trafood.Models.GetRumahmakan;
import id.trafood.trafood.Models.Rumahmakan;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rest.Connect;
import id.trafood.trafood.Rest.UtilsApi;
import id.trafood.trafood.Rumahmakan.Adapter.RmViewPagerAdapter;
import id.trafood.trafood.Rumahmakan.Fragment_Gallery;
import id.trafood.trafood.Rumahmakan.Fragment_Info;
import id.trafood.trafood.Rumahmakan.Fragment_ListMenu;
import id.trafood.trafood.Rumahmakan.Fragment_Review;
import id.trafood.trafood.Rumahmakan.Fragment_map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRm extends AppCompatActivity {
    Context mContext;
    ApiInterface apiInterface;

    TextView textViewnamaRM,textViewkategori, textViewAlamat,tvRating;
    ImageView iVfotoRm;
    ViewPager myViewPager;
    TabLayout mytabLayout;
    TabLayout tableLayout;
    private int[] tabIcons = {
            R.drawable.ic_menu,
            R.drawable.location,
            R.drawable.ic_galery,
            R.drawable.ic_review,
            R.drawable.info
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
       // this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail_rm);

        textViewnamaRM = (TextView) findViewById(R.id.tvnamarms);
        textViewAlamat = (TextView) findViewById(R.id.tvAlamatrms);
        iVfotoRm = (ImageView) findViewById(R.id.ivFotoRmDetails);
        tableLayout = (TabLayout) findViewById(R.id.tlListMenuRM);
        tvRating = (TextView) findViewById(R.id.tvRatingrm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().hide();
        mContext = this;
        apiInterface = UtilsApi.getApiServive();

        final Intent mIntent = getIntent();
        //getSupportActionBar().setTitle(getNamaRM);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String rmid = mIntent.getStringExtra("RMID");
        String foto = mIntent.getStringExtra("FOTO");

        Picasso.with(this).load(Connect.IMAGE_RM_URL+foto).error(R.mipmap.ic_launcher).into(iVfotoRm);

        myViewPager = (ViewPager) findViewById(R.id.vpMenuRM);
        mytabLayout = (TabLayout) findViewById(R.id.tlListMenuRM);

        setRating(rmid); //set rating rumah makan

        tvRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,RatingActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    private void setRating(final String rmid) {
        apiInterface.ringkasanRm(rmid).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String review = jsonObject.getJSONObject("result").getString("review");
                    String visit = jsonObject.getJSONObject("result").getString("visit");
                    String rating1 = jsonObject.getJSONObject("result").getString("rating1");
                    String rating2 =  jsonObject.getJSONObject("result").getString("rating2");
                    String rating3 = jsonObject.getJSONObject("result").getString("rating3");
                    String rating4 = jsonObject.getJSONObject("result").getString("rating4");
                    final String namarm = jsonObject.getJSONObject("result").getString("namarm");
                    final String kategori = jsonObject.getJSONObject("result").getString("kategorirm");
                    final String alamat = jsonObject.getJSONObject("result").getString("alamat");
                    final String lat = jsonObject.getJSONObject("result").getString("latitude");
                    final String longi = jsonObject.getJSONObject("result").getString("longitude");

                    textViewnamaRM.setText(namarm);
                    textViewAlamat.setText(alamat);

                    if (rating1.equals("null") && rating2 .equals("null") && rating3 .equals("null") && rating4 .equals("null") ){
                        tvRating.setText("");
                    }
                    else if (rating1 != null && rating2 != null && rating3 != null && rating4 != null ) {
                        Double rating1d;
                        Double rating2d;
                        Double rating3d;
                        Double rating4d;

                        rating1d = Double.parseDouble(rating1);
                        rating2d = Double.parseDouble(rating2);
                        rating3d = Double.parseDouble(rating3);
                        rating4d = Double.parseDouble(rating4);
                        Double total = rating1d+rating2d+rating3d+rating4d;
                        DecimalFormat df = new DecimalFormat("#.#");
                        String dx = df.format(total);
                        tvRating.setText(dx);
                        //  tvRating.setText("ss");
                    }

                    //send data from activity to fragment
                    Bundle bundle = new Bundle();
                    bundle.putString("rmid", rmid);
                    bundle.putString("lat", lat);
                    bundle.putString("longs", longi);
                    Fragment_ListMenu fragtry = new Fragment_ListMenu();
                    Fragment_map fragmap = new Fragment_map();
                    Fragment_Gallery fraglery = new Fragment_Gallery();
                    Fragment_Info fragfo = new Fragment_Info();
                    Fragment_Review fragview = new Fragment_Review();
                    fragtry.setArguments(bundle);
                    fragmap.setArguments(bundle);
                    fraglery.setArguments(bundle);
                    fragfo.setArguments(bundle);
                    fragview.setArguments(bundle);

                    ArrayList<Fragment> fragments = new ArrayList<>();
                    fragments.add(fragtry);
                    fragments.add(fragmap);
                    fragments.add(fraglery);
                    fragments.add(fragview);
                    fragments.add(fragfo);



                    RmViewPagerAdapter adapter = new RmViewPagerAdapter(getSupportFragmentManager(), fragments);

                    myViewPager.setAdapter(adapter);
                    //setupTabIcons();
                    mytabLayout.setupWithViewPager(myViewPager);
                    setupTabIcons();

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void setupTabIcons() {
        tableLayout.getTabAt(0).setIcon(tabIcons[0]);
        tableLayout.getTabAt(1).setIcon(tabIcons[1]);
        tableLayout.getTabAt(2).setIcon(tabIcons[2]);
        tableLayout.getTabAt(3).setIcon(tabIcons[3]);
        tableLayout.getTabAt(4).setIcon(tabIcons[4]);


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
}
