package com.example.fitzoneadmin;

public class ListItem {
    private String title;
    private String subTitle ;
    private Integer imagId ;

    public ListItem(String title , String subTitle, Integer imagId) {
        this.title = title;
        this.subTitle = subTitle;
        this.imagId = imagId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setImagId(Integer imagId) {
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
}

