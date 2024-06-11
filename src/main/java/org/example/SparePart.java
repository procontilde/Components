package org.example;

import com.google.gson.Gson;

public class SparePart {

    protected int code;
    protected String text;
    protected double price;

    public SparePart(int code, String text, double price) {

        this.code = code;
        this.text = text;
        this.price = price;

    }

    public int getCode() { return code; }
    public String getText() { return text; }
    public double getPrice() { return price; }

    public void setCode(int code) { this.code = code; }
    public void setText(String text) { this.text = text; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {

        return "SPAREPARTS:"
                + "code=" + code
                + ", text='" + text
                + ", price=" + price;

    }

    public String toJson(){

        Gson gson = new Gson();

        return gson.toJson(this, SparePart.class);

    }

    public static SparePart formJson(String json){

        Gson gson = new Gson();

        return gson.fromJson(json, SparePart.class);

    }

}
