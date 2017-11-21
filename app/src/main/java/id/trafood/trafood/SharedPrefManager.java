package id.trafood.trafood;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kulinerin 1 on 20/11/2017.
 */

public class SharedPrefManager {
    public static final String SP_MAHASISWA_APP = "spMahasiswaApp";
    public static final String SP_RUMAHMAKAN = "spRumahmakan";
    public static final String SP_FOTO = "spFoto";
    public static final String SP_USERID = "spUserID";
    public static final String SP_NAMA = "spNama";
    public static final String SP_EMAIL = "spEmail";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context) {
        sp = context.getSharedPreferences(SP_MAHASISWA_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value) {
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value) {
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPNama() {
        return sp.getString(SP_NAMA, "");
    }


    public String getSPEmail() {
        return sp.getString(SP_EMAIL, "");
    }

    public String getSpRumahmakan() {
        return sp.getString(SP_RUMAHMAKAN, "");
    }

    public String getSpFoto() {
        return sp.getString(SP_FOTO, "");
    }

    public String getSpUserid() {
        return sp.getString(SP_USERID, "");
    }

    public Boolean getSPSudahLogin() {
        return sp.getBoolean(SP_SUDAH_LOGIN, false);

    }
}