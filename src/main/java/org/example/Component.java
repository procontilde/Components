package org.example;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Component extends SparePart{

    private Map<Integer, SparePart> mySpareParts;

    public Component(int code, String text, double price) {

        super(code, text, price);
        this.mySpareParts = new HashMap<>();

    }

    public boolean addSparePart(SparePart sP){

        if(sP != null && !mySpareParts.containsKey(sP.getCode())){

            mySpareParts.put(sP.getCode(), sP);
            return true;

        }

        return false;

    }

    public boolean removeSparePart(int code){

        if(mySpareParts.containsKey(code)){

            mySpareParts.remove(code);
            return true;

        }

        return false;

    }

    public SparePart findSparePart(int code){

        return mySpareParts.get(code);

    }

    public Map<Integer, SparePart> getMySpareParts() { return mySpareParts; }

    @Override
    public String toString() {

        return "COMPONENTS:" +
                "myComponents=" + mySpareParts;

    }

    public String toJson(){

        Gson gson = new Gson();

        return gson.toJson(this, Component.class);

    }

    public static Component fromJson(String json){

        Gson gson = new Gson();
        return gson.fromJson(json, Component.class);

    }

}
