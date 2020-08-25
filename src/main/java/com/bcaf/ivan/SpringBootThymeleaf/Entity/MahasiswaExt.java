package com.bcaf.ivan.SpringBootThymeleaf.Entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MahasiswaExt extends Mahasiswa{
    private String createdDateExt;
    private String updatedDateExt;

    public String getCreatedDateExt() {
        return createdDateExt;
    }

    public void setCreatedDateExt(Date createdDateExt) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY, HH:mm");
        this.createdDateExt = dateFormat.format(createdDateExt);
    }

    public String getUpdatedDateExt() {
        return updatedDateExt;
    }

    public void setUpdatedDateExt(Date updatedDateExt) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY, HH:mm");
        if(updatedDateExt!=null)
            this.updatedDateExt = dateFormat.format(updatedDateExt);
        else
            this.updatedDateExt="";
    }
}
