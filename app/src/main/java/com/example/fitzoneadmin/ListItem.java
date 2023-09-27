package com.example.fitzoneadmin;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ListItem {
    private String title;
    private String subTitle;
    private Integer imagId;

    // Default no-argument constructor required by Firebase
    public ListItem() {
    }

    public ListItem(String title, String subTitle, Integer imagId) {
        this.title = title;
        this.subTitle = subTitle;
        this.imagId = imagId;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public Integer getImagId() {
        return imagId;
    }
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
    public void setTitle(String title) {
        this.title = title;
    }

}
