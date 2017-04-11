package com.dawang.androidexample.designpattern;

/**
 * Created by DaWang on 2017/4/11.
 */

public class XMLBookFormatter extends BookFormatterTemplate {
    @Override
    protected String formatting(Book book) {
        String result = "";
        result += "<book_name>" + book.getName() + "</book_name>\n";
        result += "<price>" + book.getPrice() + "</price>\n";
        result += "<author>" + book.getAuthor() + "</author>\n";
        result += "<isbn>" + book.getISBN() + "</ISBN>\n";

        return result;
    }
}
