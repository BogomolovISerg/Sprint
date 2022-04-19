package org.web.model;

public class Product {
    private int id;
    private String title;
    private float cost;

    public Product(int id,String title,float cost ){
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public String printProduct(){
        return "<tr><td align=\"center\">" + id + "</td><td>" + title + "</td><td align=\"right\">" + cost + "</td></tr>";
    }
}
