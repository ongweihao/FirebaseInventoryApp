package sg.edu.rp.webservices.firebaseinventoryapp;

import java.io.Serializable;

/**
 * Created by 15039840 on 1/8/2017.
 */

public class Items implements Serializable {
    private String id;
    private String name;
    private int cost;

    public Items(){

    }
    public Items(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return name;
    }



}
