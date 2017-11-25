package id.trafood.trafood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class EditKedaiActivity extends AppCompatActivity {

    EditText etNamaKedai, eturl, etDeskrpsi, etNoTelp;
    Spinner spWaktuBuka, spWaktututup, spCategory;
    CheckBox cbSunday, cbMonday, cbTuesday, cbWednesday, cbThursday, cbFriday, cbSaturday;
    CheckBox cbFasilitas, cbFsatu, cbFDua, cbFtiga, cbFEmpat, cbFLima;
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kedai);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Kedai");
        

        setView();

        fill();
    }

    private void fill() {
        Intent intent = getIntent();

        String rmid = intent.getStringExtra("RMID");

        String namakedai = intent.getStringExtra("NAMAKEDAI");
        String uurl = intent.getStringExtra("URL");
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
