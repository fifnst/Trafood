package id.trafood.trafood;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class SettingProfilActivity extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;
    LinearLayout linearProfil, linearKedai, linearLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_profil);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pengaturan Akun");

        sharedPrefManager = new SharedPrefManager(this);

        linearProfil = (LinearLayout) findViewById(R.id.linearProfil);
        linearKedai = (LinearLayout) findViewById(R.id.linearKedai);
        linearLogout = (LinearLayout) findViewById(R.id.linearLogout);

        if (!sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(SettingProfilActivity.this,LoginActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
        String rumahmakan = sharedPrefManager.getSpRumahmakan();
        linearKedai.setVisibility(View.GONE);

        if (rumahmakan.equals("1")){
            linearKedai.setVisibility(View.VISIBLE);
        }

        linearKedai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingProfilActivity.this, EditKedaiActivity.class);
                SettingProfilActivity.this.startActivity(intent);
            }
        });

        linearProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingProfilActivity.this, EditProfilActivity.class);
                SettingProfilActivity.this.startActivity(intent);
            }
        });

        linearLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new AlertDialog.Builder(SettingProfilActivity.this)
                        .setMessage("Arey You sure you want to logout?")
                        .setCancelable(false)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                                startActivity(new Intent(view.getContext(), MainActivity.class));
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
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
