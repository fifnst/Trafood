package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kulinerin 1 on 23/10/2017.
 */

public class Galery {
    @SerializedName("rmid")
    private String rmid;
    @SerializedName("file_name")
    private String file_name;
    @SerializedName("crated")
    private String created;

    public Galery(String rmid, String file_name, String created) {
        this.rmid = rmid;
        this.file_name = file_name;
        this.created = created;
    }

    public String getRmid() {
        return rmid;
    }

    public void setRmid(String rmid) {
        this.rmid = rmid;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
