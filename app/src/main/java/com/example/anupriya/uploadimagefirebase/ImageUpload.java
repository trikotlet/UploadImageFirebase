package com.example.anupriya.uploadimagefirebase;

/**
 * Created by anupriya on 2017-08-02.
 */

public class ImageUpload {

    public static String ID;
    public static String p_name;
    public static String p_price;
    public static String p_description;




    public ImageUpload(String ID, String p_name, String p_price, String p_description) {
        this.ID = ID;
        this.p_name = p_name;
        this.p_price = p_price;
        this.p_description = p_description;
    }

    public String getID() {
        return ID;
    }

    public String getP_name() {
        return p_name;
    }

    public String getP_price() {
        return p_price;
    }

    public String getP_description() {
        return p_description;
    }

    public static void setID(String ID) {
        ImageUpload.ID = ID;
    }

    public static void setP_name(String p_name) {
        ImageUpload.p_name = p_name;
    }

    public static void setP_price(String p_price) {
        ImageUpload.p_price = p_price;
    }

    public static void setP_description(String p_description) {
        ImageUpload.p_description = p_description;
    }

    public ImageUpload() {
    }
}
