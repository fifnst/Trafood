package id.trafood.trafood;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.trafood.trafood.Models.PostPutDelRm;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rest.RestApi;
import id.trafood.trafood.Rest.UtilsApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditKedaiActivity extends AppCompatActivity {

    EditText etNamaKedai, eturl, etDeskrpsi, etNoTelp;
    Spinner spWaktuBuka, spWaktututup, spCategory;
    CheckBox cbSunday, cbMonday, cbTuesday, cbWednesday, cbThursday, cbFriday, cbSaturday;
    CheckBox cbFasilitas, cbFsatu, cbFDua, cbFtiga, cbFEmpat, cbFLima;
    Button buttonSave;
    RestApi restApi;
    Context mContext;
    ApiInterface apiInterface;
    TextView tvSunday, tvMondday, tvTuesday, tvWednesday, tvThrusday, tvFriday, tvSaturday,warningurl;
    TextView tvFasilitas, tvF1, tvF2, tvF3, tvF4, tvF5;
    RelativeLayout relativeLayout;
    SharedPrefManager sharedPrefManager;
    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kedai);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Kedai");
        sharedPrefManager = new SharedPrefManager(this);
        mContext = this;
        restApi = ApiClient.getClient().create(RestApi.class);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        setView();

        fill();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(mContext, null, "Please wait.... Uploading...", true, false);
                save();
            }
        });


    }


    private void fill() {
        Intent intent = getIntent();

        String rmid = intent.getStringExtra("RMID");

        String namakedai = intent.getStringExtra("NAMAKEDAI");
        final String uurl = intent.getStringExtra("URL");
        String deskripsi = intent.getStringExtra("DESKRIPSI ");
        String notelp = intent.getStringExtra("NOTELP");

        String buka = intent.getStringExtra("BUKA");
        String tutup = intent.getStringExtra("TUTUP");
        String category = intent.getStringExtra("CATEGORY");

        String sunday = intent.getStringExtra("SUNDAY");
        String monday = intent.getStringExtra("MONDAY");
        String tuesday = intent.getStringExtra("TUESDAY");
        String wednesday = intent.getStringExtra("WEDNESDAY");
        String thrusday = intent.getStringExtra("THRUSDAY");
        String friday = intent.getStringExtra("FRIDAY");
        String saturday = intent.getStringExtra("SATURDAY");

        String fasilitas = intent.getStringExtra("FASILITAS");
        String fsatu = intent.getStringExtra("FSATU");
        String fdua = intent.getStringExtra("FDUA");
        String ftiga = intent.getStringExtra("FTIIGA");
        String fempat = intent.getStringExtra("FEMPAT");
        String flima = intent.getStringExtra("FLIMA");

        etNamaKedai.setText(namakedai);
        eturl.setText(uurl);
        etDeskrpsi.setText(deskripsi);
        etNoTelp.setText(notelp);

        if (sunday.equals("1")){
            cbSunday.setChecked(true);
        }
        if (monday.equals("1")){
            cbMonday.setChecked(true);
        }
        if (tuesday.equals("1")){
            cbTuesday.setChecked(true);
        }
        if (wednesday.equals("1")){
            cbWednesday.setChecked(true);
        }
        if (thrusday.equals("1")){
            cbThursday.setChecked(true);
        }
        if (friday.equals("1")){
            cbFriday.setChecked(true);
        }
        if (saturday.equals("1")){
            cbSaturday.setChecked(true);
        }

        if (fasilitas.equals("1")){
            cbFasilitas.setChecked(true);
        }

        if (fsatu.equals("1")){
            cbFsatu.setChecked(true);
        }
        if (fdua.equals("1")){
            cbFDua.setChecked(true);
        }
        if (ftiga.equals("1")){
            cbFtiga.setChecked(true);
        }
        if (fempat.equals("1")){
            cbFEmpat.setChecked(true);
        }
        if (flima.equals("1")) {
            cbFLima.setChecked(true);
        }

        //utnuk katgori
        if (category.equals("Kafe")){
            spCategory.setSelection(0,false);
        }if (category.equals("Resto")){
            spCategory.setSelection(1,false);
        }if (category.equals("Food Court")){
            spCategory.setSelection(2,false);
        }if (category.equals("Kedai")){
            spCategory.setSelection(3,false);
        }if (category.equals("Warung")){
            spCategory.setSelection(4,false);
        }if (category.equals("Kaki Lima")){
            spCategory.setSelection(5,false);
        }

        //untuk jam buka
        if (buka.equals("06.00")){
            spWaktuBuka.setSelection(0, false);
        }if (buka.equals("06.30")){
            spWaktuBuka.setSelection(1,false);
        }if (buka.equals("07.00")){
            spWaktuBuka.setSelection(2, false);
        }if (buka.equals("07.30")){
            spWaktuBuka.setSelection(3,false);
        }if (buka.equals("08.00")){
            spWaktuBuka.setSelection(4, false);
        }if (buka.equals("08.30")){
            spWaktuBuka.setSelection(5,false);
        }if (buka.equals("09.00")){
            spWaktuBuka.setSelection(6, false);
        }if (buka.equals("09.30")){
            spWaktuBuka.setSelection(7,false);
        }if (buka.equals("10.00")){
            spWaktuBuka.setSelection(8, false);
        }if (buka.equals("10.30")){
            spWaktuBuka.setSelection(9,false);
        }if (buka.equals("11.00")){
            spWaktuBuka.setSelection(10, false);
        }if (buka.equals("11.30")){
            spWaktuBuka.setSelection(11,false);
        }if (buka.equals("12.00")){
            spWaktuBuka.setSelection(12, false);
        }if (buka.equals("12.30")){
            spWaktuBuka.setSelection(13,false);
        }if (buka.equals("13.00")){
            spWaktuBuka.setSelection(14, false);
        }if (buka.equals("13.30")){
            spWaktuBuka.setSelection(15,false);
        }if (buka.equals("14.00")){
            spWaktuBuka.setSelection(16, false);
        }if (buka.equals("14.30")){
            spWaktuBuka.setSelection(17,false);
        }if (buka.equals("15.00")){
            spWaktuBuka.setSelection(18, false);
        }if (buka.equals("15.30")){
            spWaktuBuka.setSelection(19,false);
        }if (buka.equals("17.00")){
            spWaktuBuka.setSelection(20, false);
        }if (buka.equals("17.30")){
            spWaktuBuka.setSelection(21,false);
        }if (buka.equals("18.00")){
            spWaktuBuka.setSelection(22, false);
        }if (buka.equals("18.30")){
            spWaktuBuka.setSelection(23,false);
        }if (buka.equals("19.00")){
            spWaktuBuka.setSelection(24, false);
        }if (buka.equals("19.30")){
            spWaktuBuka.setSelection(25,false);
        }if (buka.equals("20.00")){
            spWaktuBuka.setSelection(26, false);
        }if (buka.equals("20.30")){
            spWaktuBuka.setSelection(27,false);
        }if (buka.equals("21.00")){
            spWaktuBuka.setSelection(28,false);
        }if (buka.equals("22.00")){
            spWaktuBuka.setSelection(39, false);
        }if (buka.equals("23.00")){
            spWaktuBuka.setSelection(30,false);
        }if (buka.equals("24.00")){
            spWaktuBuka.setSelection(31,false);
        }
        //untuk jam tutup
        if (tutup.equals("18.00")){
            spWaktututup.setSelection(0, false);
        }if (tutup.equals("18.30")){
            spWaktututup.setSelection(1,false);
        }if (tutup.equals("19.00")){
            spWaktututup.setSelection(2, false);
        }if (tutup.equals("19.30")){
            spWaktututup.setSelection(3,false);
        }if (tutup.equals("20.00")){
            spWaktututup.setSelection(4, false);
        }if (tutup.equals("20.30")){
            spWaktututup.setSelection(5,false);
        }if (tutup.equals("21.00")){
            spWaktututup.setSelection(6, false);
        }if (tutup.equals("22.00")){
            spWaktututup.setSelection(7,false);
        }if (tutup.equals("23.00")){
            spWaktututup.setSelection(8, false);
        }if (tutup.equals("24.00")){
            spWaktututup.setSelection(9,false);
        }if (tutup.equals("01.00")){
            spWaktututup.setSelection(10, false);
        }if (tutup.equals("02.00")){
            spWaktututup.setSelection(11,false);
        }if (tutup.equals("03.00")){
            spWaktututup.setSelection(12, false);
        }if (tutup.equals("04.00")){
            spWaktututup.setSelection(13,false);
        }if (tutup.equals("05.00")){
            spWaktututup.setSelection(14, false);
        }

        eturl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                warningurl.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                apiInterface.cekUrl(eturl.getText().toString()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            String uuurl = jsonObject.getString("status");
                            if (uuurl.equals("200")){
                                if (eturl.getText().toString().equals("")){
                                    warningurl.setVisibility(View.GONE);
                                    buttonSave.setEnabled(false);
                                }else{
                                    warningurl.setVisibility(View.VISIBLE);
                                    warningurl.setText("Url "+eturl.getText().toString()+" tersedia");
                                    warningurl.setTextColor(getResources().getColor(R.color.green));
                                    buttonSave.setEnabled(true);
                                }
                            }else{
                                if (eturl.getText().toString().equals(uurl)){
                                    warningurl.setVisibility(View.GONE);
                                    buttonSave.setEnabled(true);
                                }else {
                                    warningurl.setVisibility(View.VISIBLE);
                                    warningurl.setText("Url "+eturl.getText().toString()+" sudah ada yang pake");
                                    warningurl.setTextColor(getResources().getColor(R.color.red));
                                    buttonSave.setEnabled(false);
                                }
                            }
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

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void setView() {
        etNamaKedai = (EditText) findViewById(R.id.etNamaKedaiEdit);
        eturl = (EditText) findViewById(R.id.eturlKedaiEdit);
        etDeskrpsi = (EditText) findViewById(R.id.etDeskripsiKedaiEdit);
        etNoTelp = (EditText) findViewById(R.id.etNoTelpKedaiEdit);

        spWaktuBuka = (Spinner) findViewById(R.id.spJambukaEdit);
        spWaktututup = (Spinner) findViewById(R.id.spJamTutupEdit);
        spCategory = (Spinner) findViewById(R.id.spCategoryEdit);
        cbSunday = (CheckBox) findViewById(R.id.cbSundayEdit);
        cbMonday = (CheckBox) findViewById(R.id.cbSeninEdit);
        cbTuesday = (CheckBox) findViewById(R.id.cbSelasaEdit);
        cbWednesday = (CheckBox) findViewById(R.id.cbRabuEdit);
        cbThursday = (CheckBox) findViewById(R.id.cbKamisEdit);
        cbFriday = (CheckBox) findViewById(R.id.cbJumatEdit);
        cbSaturday = (CheckBox) findViewById(R.id.cbSabtuEdit);

        cbFasilitas = (CheckBox) findViewById(R.id.cbFasilitasEdit);
        cbFsatu = (CheckBox) findViewById(R.id.cbFSatuEdit);
        cbFDua = (CheckBox) findViewById(R.id.cbcbFDuaEdit);
        cbFtiga = (CheckBox) findViewById(R.id.cbFTigaEdit);
        cbFEmpat = (CheckBox) findViewById(R.id.cbFEmpatEdit);
        cbFLima = (CheckBox) findViewById(R.id.cbFLimauEdit);

        buttonSave = (Button) findViewById(R.id.btnEditKedai);
        warningurl = (TextView) findViewById(R.id.WarningUrlEdit);
        warningurl.setVisibility(View.GONE);

        tvFasilitas = (TextView) findViewById(R.id.etFasilitas);
        tvF1 = (TextView) findViewById(R.id.etF1);
        tvF2 = (TextView) findViewById(R.id.etF2);
        tvF3 = (TextView) findViewById(R.id.etF3);
        tvF4 = (TextView) findViewById(R.id.etF4);
        tvF5 = (TextView) findViewById(R.id.etF5);

        tvSunday = (TextView) findViewById(R.id.etsunday);
        tvMondday = (TextView) findViewById(R.id.etMonday);
        tvTuesday = (TextView) findViewById(R.id.etTuesday);
        tvWednesday = (TextView) findViewById(R.id.etWednesday);
        tvThrusday = (TextView) findViewById(R.id.ettrusday);
        tvSaturday = (TextView) findViewById(R.id.etsaturday);
        tvFriday = (TextView) findViewById(R.id.etFriday);

        relativeLayout = (RelativeLayout) findViewById(R.id.relatifFasilitas);

        relativeLayout.setVisibility(View.GONE);

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

        final String rmid = getIntent().getStringExtra("RMID");
        final String userid = sharedPrefManager.getSpUserid();

        String namakedai = etNamaKedai.getText().toString();
        String uurl = eturl.getText().toString();
        String deskripsi = etDeskrpsi.getText().toString();
        String notelp = etNoTelp.getText().toString();

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

        Call<PostPutDelRm> updaterm = restApi.putRm(namakedai,uurl,deskripsi,category,buka,tutup,sunday,
                monday,tuesday,wednesday,thrusday,friday,saturday,fasilitas,fsatu,fdua,ftiga,fempat,flima,notelp,userid,rmid);
        updaterm.enqueue(new Callback<PostPutDelRm>() {
            @Override
            public void onResponse(Call<PostPutDelRm> call, Response<PostPutDelRm> response) {
                loading.dismiss();
                Toast.makeText(mContext, "Sukses update", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, RingkasanKedaiActivity.class);
                intent.putExtra("TITLE","Ringkasan Kedai");
                intent.putExtra("RMID", rmid);
                intent.putExtra("USERID", userid);
                mContext.startActivity(intent);
            }

            @Override
            public void onFailure(Call<PostPutDelRm> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Sukses update", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, RingkasanKedaiActivity.class);
                intent.putExtra("TITLE","Ringkasan Kedai");
                intent.putExtra("RMID", rmid);
                intent.putExtra("USERID", userid);
                mContext.startActivity(intent);

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
