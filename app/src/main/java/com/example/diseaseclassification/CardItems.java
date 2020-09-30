package com.example.diseaseclassification;

public class CardItems {
    private int imgRes;
    private String textTitle;
    private String textDesc;

    public CardItems(int image, String title, String desc){
    imgRes = image;
    textTitle = title;
    textDesc = desc;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getTextTitle() {
        return textTitle;
    }

    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
    }

    public String getTextDesc() {
        return textDesc;
    }

    public void setTextDesc(String textDesc) {
        this.textDesc = textDesc;
    }
}
