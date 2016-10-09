package com.example.magnus.laesomondo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class TextFromNetAct extends AppCompatActivity {

    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_from_net);


        web = (WebView) findViewById(R.id.webviewContent);

        web.loadUrl("http//:google.com");

        import java.io.File;
        import java.io.IOException;

        import org.jsoup.Jsoup;
        import org.jsoup.nodes.Document;
        import org.jsoup.nodes.Element;
        import org.jsoup.select.Elements;



        public class HTMLLoader {

            private String url;


            public HTMLLoader(String URL) {



            }

            public static void main(String[] args)  {
                try {
                    String url = "http://ekstrabladet.dk/flash/taxichauffoer-kaldte-louise-luder-luder-luder/6338782";
                    Document doc = Jsoup.connect(url).get();
                    Elements paragraphs = doc.select("p");
                    for(Element p : paragraphs)
                        if(p.text().length() > 200){
                            System.out.println(p.text());
                            System.out.println("\n");
                        }
                }
                catch (IOException ex) {

                }
            }
        }


    }
}
