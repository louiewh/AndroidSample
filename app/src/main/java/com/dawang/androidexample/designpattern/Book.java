package com.dawang.androidexample.designpattern;

/**
 * Created by DaWang on 2017/4/11.
 */

public class Book{
    private String mName;
    private String mAuthor;
    private int    mPrivce;
    private String ISBN;

    String getName(){
        return mName;
    }

    void setName(String name){
        mName = name;
    }

    String getAuthor(){
        return mAuthor;
    }

    void setAuthor(String author){
        mAuthor = author;
    }

    int getPrice(){
        return mPrivce;
    }

    void setPrivce(int price){
        mPrivce = price;
    }
    String getISBN(){
        return ISBN;
    }

    void setISBN(String BN ){
        ISBN = BN;
    }

    static String formatXML(Book book){

        XMLBookFormatter xmlBookFormatter = new XMLBookFormatter();
        return xmlBookFormatter.formatter(book);
    }

    static String formatJson(Book book){

        JsonBookFormatter jsonBookFormatter = new JsonBookFormatter();
        return jsonBookFormatter.formatter(book);
    }
}