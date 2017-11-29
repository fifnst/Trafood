package id.trafood.trafood;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import id.trafood.trafood.Models.PostPutDelRm;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.RestApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarKedaiInfo extends AppCompatActivity {

    Spinner spWaktuBuka, spWaktututup, spCategory;
    CheckBox cbSunday, cbMonday, cbTuesday, cbWednesday, cbThursday, cbFriday, cbSaturday;
    CheckBox cbFasilitas, cbFsatu, cbFDua, cbFtiga, cbFEmpat, cbFLima;
    Button buttonSave;
    RestApi restApi;
    Context mContext;
    TextView tvSunday, tvMondday, tvTuesday, tvWednesday, tvThrusday, tvFriday, tvSaturday;
    TextView tvFasilitas, tvF1, tvF2, tvF3, tvF4, tvF5;
    RelativeLayout relativeLayout;
    SharedPrefManager sharedPrefManager;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_kedai_info);


        sharedPrefManager = new SharedPrefManager(this);
        mContext = this;
        restApi = ApiClient.getClient().create(RestApi.class);

        setView();
        spWaktuBuka = (Spinner) findViewById(R.id.spJambukaTambah);
        spWaktututup = (Spinner) findViewById(R.id.spJamTutupTambah);
        spCategory = (Spinner) findViewById(R.id.spCategoryTambah);
        cbSunday = (CheckBox) findViewById(R.id.cbSundayTambah);
        cbMonday = (CheckBox) findViewById(R.id.cbSeninTambah);
        cbTuesday = (CheckBox) findViewById(R.id.cbSelasaTambah);
        cbWednesday = (CheckBox) findViewById(R.id.cbRabuTambah);
        cbThursday = (CheckBox) findViewById(R.id.cbKamisTambah);
        cbFriday = (CheckBox) findViewById(R.id.cbJumatTambah);
        cbSaturday = (CheckBox) findViewById(R.id.cbSabtuTambah);

        cbFasilitas = (CheckBox) findViewById(R.id.cbFasilitasTambah);
        cbFsatu = (CheckBox) findViewById(R.id.cbFSatuTambah);
        cbFDua = (CheckBox) findViewById(R.id.cbcbFDuaTambah);
        cbFtiga = (CheckBox) findViewById(R.id.cbFTigaTambah);
        cbFEmpat = (CheckBox) findViewById(R.id.cbFEmpatTambah);
        cbFLima = (CheckBox) findViewById(R.id.cbFLimauTambah);

        buttonSave = (Button) findViewById(R.id.btnTambahKedai);

        tvFasilitas = (TextView) findViewById(R.id.etFasilitasTambah);
        tvF1 = (TextView) findViewById(R.id.etF1Tambah);
        tvF2 = (TextView) findViewById(R.id.etF2Tambah);
        tvF3 = (TextView) findViewById(R.id.etF3Tambah);
        tvF4 = (TextView) findViewById(R.id.etF4Tambah);
        tvF5 = (TextView) findViewById(R.id.etF5Tambah);

        tvSunday = (TextView) findViewById(R.id.etsundayTambah);
        tvMondday = (TextView) findViewById(R.id.etMondayTambah);
        tvTuesday = (TextView) findViewById(R.id.etTuesdayTambah);
        tvWednesday = (TextView) findViewById(R.id.etWednesdayTambah);
        tvThrusday = (TextView) findViewById(R.id.ettrusdayTambah);
        tvSaturday = (TextView) findViewById(R.id.etsaturdayTambah);
        tvFriday = (TextView) findViewById(R.id.etFridayTambah);

        relativeLayout = (RelativeLayout) findViewById(R.id.relatifFasilitasTambah);

        relativeLayout.setVisibility(View.GONE);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(mContext, null, "Please wait.... Uploading...", true, false);
                save();
            }
        });
    }

    private void save() {
        if (cbFasilitas.isChecked()){
            tvFasilitas.setText("1");
        }if (!cbFasilitas.isChecked()){
            tvFasilitas.setText("0");
        }if (cbFsatu.isChecked()){
            tvF1.setText("1");
        }if (!cbFsatu.isChecked()){
            tvF1.setText("0");
        }if (cbFDua.isChecked()){
            tvF2.setText("1");
        }if (!cbFDua.isChecked()){
            tvF2.setText("0");
        }if (cbFtiga.isChecked()){
            tvF3.setText("1");
        }if (!cbFtiga.isChecked()){
            tvF3.setText("0");
        }if (cbFEmpat.isChecked()){
            tvF4.setText("1");
        }if (!cbFEmpat.isChecked()){
            tvF4.setText("0");
        }if (cbFLima.isChecked()){
            tvF5.setText("1");
        }if (!cbFLima.isChecked()){
            tvF5.setText("0");
        }

        if (cbSunday.isChecked()){
            tvSunday.setText("1");
        }if (!cbSunday.isChecked()){
            tvSunday.setText("0");
        }if (cbMonday.isChecked()){
            tvMondday.setText("1");
        }if (!cbMonday.isChecked()){
            tvMondday.setText("0");
        }if (cbTuesday.isChecked()){
            tvTuesday.setText("1");
        }if (!cbTuesday.isChecked()){
            tvTuesday.setText("0");
        }if (cbWednesday.isChecked()){
            tvWednesday.setText("1");
        }if (!cbWednesday.isChecked()){
            tvWednesday.setText("0");
        }if (cbThursday.isChecked()){
            tvThrusday.setText("1");
        }if (!cbThursday.isChecked()){
            tvThrusday.setText("0");
        }if (cbFriday.isChecked()){
            tvFriday.setText("1");
        }if (!cbFriday.isChecked()){
            tvFriday.setText("0");
        }if (cbSaturday.isChecked()){
            tvSaturday.setText("1");
        }if (!cbSaturday.isChecked()){
            tvSaturday.setText("0");
        }


        String buka = spWaktuBuka.getSelectedItem().toString();
        String tutup = spWaktututup.getSelectedItem().toString();
        String category = spCategory.getSelectedItem().toString();

        String sunday = tvSunday.getText().toString();
        String monday = tvMondday.getText().toString();
        String tuesday = tvTuesday.getText().toString();
        String wednesday = tvWednesday.getText().toString();
        String thrusday = tvThrusday.getText().toString();
        String friday = tvFriday.getText().toString();
        String saturday = tvSaturday.getText().toString();

        String fasilitas = tvFasilitas.getText().toString();
        String fsatu = tvF1.getText().toString();
        String fdua = tvF2.getText().toString();
        String ftiga = tvF3.getText().toString();
        String fempat = tvF4.getText().toString();
        String flima = tvF5.getText().toString() ;
        String userid = sharedPrefManager.getSpUserid();

        Call<PostPutDelRm> putDelRmCall = restApi.postInfoRm(category,buka,tutup,sunday,
                monday,tuesday,wednesday,thrusday,friday,saturday,fasilitas,fsatu,fdua,ftiga,fempat,flima,userid);
        putDelRmCall.enqueue(new Callback<PostPutDelRm>() {
            @Override
            public void onResponse(Call<PostPutDelRm> call, Response<PostPutDelRm> response) {
                loading.dismiss();
                Toast.makeText(mContext, "Sukses mendaftarkan rumah makan", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);
            }

            @Override
            public void onFailure(Call<PostPutDelRm> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Sukses mendaftarkan rumah makan", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);

            }
        });

    }

    private void setView() {

    }
}
