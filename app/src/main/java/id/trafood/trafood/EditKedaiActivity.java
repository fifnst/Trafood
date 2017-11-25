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
