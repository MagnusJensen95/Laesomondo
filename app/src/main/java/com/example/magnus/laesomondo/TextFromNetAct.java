package com.example.magnus.laesomondo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TextFromNetAct extends AppCompatActivity {

    WebView web;
    Button load;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_from_net);


        web = (WebView) findViewById(R.id.webviewContent);

        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        web.loadUrl("http://www.google.com");

        load = (Button)findViewById(R.id.loadPageButton);



    }

    public void onLoadContent(View v){

        new fetchHTMLText().execute();

    }

    private class fetchHTMLText extends AsyncTask<Void, Void, Void>{

        String HTMLTExt = "";
        String HTMLTitel;
        String currentURL = web.getUrl().toString();
        @Override
        protected Void doInBackground(Void... params) {


            try {

                Document doc = Jsoup.connect(currentURL).get();
                //  HTMLTExt = doc.text();
                Elements paragraphs = doc.select("p");
                HTMLTitel = doc.title();
                for (Element p : paragraphs) {
                    if (p.text().length() > 100) {

                        HTMLTExt += p.text();
                        HTMLTExt += "\n\n";
                    }

                }
            } catch (IOException ex) {

                Log.i("DEBUG", "IN THE EXCEPTION");

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // textLoader.setUrl(this.web.getUrl());

            Intent goToReading = new Intent(getApplicationContext(), ReadingTest.class);

            goToReading.putExtra("TextToLoad", HTMLTExt);
            goToReading.putExtra("Titel", HTMLTitel);

            startActivity(goToReading);
        }
    }

}