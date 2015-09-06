package com.rharj.merrymayflower.model;

/**
 * Created by Konga Tech on 2/4/2015.
 */
public class XmlValueModels {

    private String id;
    private String title;
    private String details;
    private String author;

    /****** Define Setter Methods **********/

    public void setId(String id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setDetails(String details){
        this.details = details;
    }
    public void setAuthor(String author){this.author = author;}


    /******** Define Getter Methods *********/

    public String getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public String getDetails(){
        return this.details;
    }
    public String getAuthor(){return this.author;}

    @Override
    public String toString() {
        return  id + " " + title + " "
                + details + " ";
    }
}
