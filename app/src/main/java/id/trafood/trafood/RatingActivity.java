package id.trafood.trafood;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class RatingActivity extends AppCompatActivity {

    RatingBar rbRating1, rbRating2, rbRating3, rbRating4;
    TextView tvRating1,tvRating2,tvRating3,tvRating4, tvSetRating;
    ImageView ekspresi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Rating");

        //Rating Umum
        ekspresi = (ImageView) findViewById(R.id.ivEmoticon);
        tvSetRating = (TextView) findViewById(R.id.tvSetRating);

        rbRating1 = (RatingBar) findViewById(R.id.rating1);
        tvRating1 = (TextView) findViewById(R.id.tvRating1);
        rbRating2 = (RatingBar) findViewById(R.id.rating2);
        tvRating2 = (TextView) findViewById(R.id.tvRating2);
        rbRating3 = (RatingBar) findViewById(R.id.rating3);
        tvRating3 = (TextView) findViewById(R.id.tvRating3);
        rbRating4 = (RatingBar) findViewById(R.id.rating4);
        tvRating4 = (TextView) findViewById(R.id.tvRating4);
        rbRating1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                //Tes Fahri

                tvRating1.setText(rating+"");
            }
        });

        tvRating1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                double a = 0.5;
                rubahwarna(a);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void rubahwarna(double a) {
        if (a> 0 && rating <=1 ){
            ekspresi.setImageResource(R.drawable.nol_duapuluh);
            tvSetRating.setTextColor(ContextCompat.getColor(tvSetRating.getContext(), R.color.blue));
        }if (rating > 1 && rating <=2 ){
            ekspresi.setImageResource(R.drawable.duapuluhpatpuluh);
            tvSetRating.setTextColor(ContextCompat.getColor(tvSetRating.getContext(), R.color.jad));
        }if (rating > 2 && rating <=3 ){
            ekspresi.setImageResource(R.drawable.patpuluh);
            tvSetRating.setTextColor(ContextCompat.getColor(tvSetRating.getContext(), R.color.yellow));
        }if (rating > 3 && rating <=4 ){
            ekspresi.setImageResource(R.drawable.nepuluh);
            tvSetRating.setTextColor(ContextCompat.getColor(tvSetRating.getContext(), R.color.ectasy));
        }if (rating > 4 && rating <=5 ){
            ekspresi.setImageResource(R.drawable.lapanpuluh);
            tvSetRating.setTextColor(ContextCompat.getColor(tvSetRating.getContext(), R.color.red));
        }
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
