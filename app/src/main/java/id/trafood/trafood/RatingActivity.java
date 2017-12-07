package id.trafood.trafood;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class RatingActivity extends AppCompatActivity {

    RatingBar rbRating1, rbRating2, rbRating3, rbRating4;
    TextView tvRating1,tvRating2,tvRating3,tvRating4, tvSetRating;
    ImageView ekspresi;
    EditText eidtText;


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
        eidtText = (EditText) findViewById(R.id.ETInputComment);
        tvSetRating.setText("0.0");
        //harus dinolkan dulu textviewnya supaya begitu diconvert ke double tidak error
        tvRating1.setText("0");
        tvRating2.setText("0");
        tvRating3.setText("0");
        tvRating4.setText("0");

        rating();

    }

    private void rating() {
        rbRating1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //Tes Fahri
                tvRating1.setText(rating+"");
                rubahwarna();
            }
        });

        rbRating2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                tvRating2.setText(v+"");
                rubahwarna();
            }
        });

        rbRating3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                tvRating3.setText(v+"");
                rubahwarna();
            }
        });

        rbRating4.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                tvRating4.setText(v+"");
                rubahwarna();
            }
        });

    }

    private void rubahwarna() {

        Double rating1 = Double.parseDouble(tvRating1.getText().toString());
        Double rating2 = Double.parseDouble(tvRating2.getText().toString());
        Double rating3 = Double.parseDouble(tvRating3.getText().toString());
        Double rating4 = Double.parseDouble(tvRating4.getText().toString());
        Double rating = (rating1+rating2+rating3+rating4)/4;
        DecimalFormat dfs = new DecimalFormat("#.#");
        String dx = dfs.format(rating);
        tvSetRating.setText(dx);
        if (rating> 0 && rating <=1 ){
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
            tvSetRating.setTextColor(ContextCompat.getColor(tvSetRating.getContext(), R.color.alizarin));
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

    public void btnSaveRating(View view) {
        //diconvert dulu dari string ke double lalu dibagi 4
        Double rating1 = Double.parseDouble(tvRating1.getText().toString())/4;
        Double rating2 = Double.parseDouble(tvRating2.getText().toString())/4;
        Double rating3 = Double.parseDouble(tvRating3.getText().toString())/4;
        Double rating4 = Double.parseDouble(tvRating4.getText().toString())/4;
        String comment = eidtText.getText().toString();
        Toast.makeText(this, rating1+", "+rating2+", "+rating3+", "+rating4+comment, Toast.LENGTH_SHORT).show();
    }
}
