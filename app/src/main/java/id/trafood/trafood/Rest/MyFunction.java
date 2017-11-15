package id.trafood.trafood.Rest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by kulinerin 1 on 16/10/2017.
 */

public class MyFunction extends AppCompatActivity{
    public Context con;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        con=MyFunction.this;
    }

    //funsgi untuk pindah halaman
    public void aksesclass(Class kelastujuan){
        startActivity(new Intent(con, kelastujuan));
    }

    //memunculkan pesan
    public void pesan(String isipesan){
        Toast.makeText(con, isipesan, Toast.LENGTH_SHORT).show();
    }

    //memunculkan alert
    public void alertdialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(con);
        builder.setTitle("informasi");
    }
}
