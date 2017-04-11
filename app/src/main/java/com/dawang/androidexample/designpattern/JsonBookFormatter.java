package com.dawang.androidexample.designpattern;

/**
 * Created by DaWang on 2017/4/11.
 */

public class JsonBookFormatter extends BookFormatterTemplate {
    @Override
    protected String formatting(Book book) {
        String result = "";
        result += "{\n";
        result += "\"book_name\" : \"" + book.getName() + "\",\n";
        result += "\"price\" : \"" + book.getPrice() + "\",\n";
        result += "\"author\" : \"" + book.getAuthor() + "\",\n";
        result += "\"isbn\" : \"" + book.getISBN() + "\",\n";
        result += "}";
        return result;
    }
}
