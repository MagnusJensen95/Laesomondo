package com.example.magnus.laesomondo;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;



public class HTMLExtractor {

    private String url;


    public HTMLExtractor(String URL) {
        this.url = URL;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHTML() {
        String s = "";
        try {
            Log.i("DEBUG", this.url);
            Document doc = Jsoup.connect(this.url).get();
            Elements paragraphs = doc.select("p");
            for (Element p : paragraphs) {
                if (p.text().length() > 200) {

                    s += p.text();
                }

            }
        } catch (IOException ex) {

            Log.i("DEBUG", "IN THE EXCEPTION");

        }

        return s;
    }
}