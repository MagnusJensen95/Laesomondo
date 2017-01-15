package com.example.magnus.laesomondo.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.magnus.laesomondo.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TextFromNetAct extends Fragment {

    WebView web;
    Button load;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_text_from_net, container, false);
        firstRunPreferences();

        web = (WebView) v.findViewById(R.id.webviewContent);


        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        web.loadUrl("http://www.google.com");

        load = (Button) v.findViewById(R.id.loadPageButton);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new fetchHTMLText().execute();
            }
        });


        if (getFirstRun()) {
            setRunned();
            new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme)
                    .setTitle("Valg af tekst")
                    .setMessage("Gå ind på en hjemmeside,og find en tekst eller artikel og klik på kanppen Hent")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })

                    .setIcon(android.R.drawable.ic_dialog_info)

                    .show();
        }

        return v;
    }


    private class fetchHTMLText extends AsyncTask<Void, Void, Void> {

        String HTMLTExt = "";
        String HTMLTitel = "";
        String currentURL = web.getUrl().toString();

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Document doc = Jsoup.connect(currentURL).get();
                //  HTMLTExt = doc.text();
                Elements paragraphs = doc.select("p");
                HTMLTitel += doc.title();
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


            Bundle b = new Bundle();
            ReadingTest frag = new ReadingTest();

            b.putString("readingTestType", "readingTestNetTest");
            b.putString("TextToLoad", HTMLTExt);
            b.putString("Title", HTMLTitel);
            frag.setArguments(b);

            getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left,
                    R.animator.exit_to_right, R.animator.enter_from_right).replace(R.id.container_main,
                    frag).addToBackStack(null).commit();

        }
    }

    /**
     * get if this is the first run
     *
     * @return returns true, if this is the first run
     */
    public boolean getFirstRun() {
        return mPrefs.getBoolean("firstRun", true);
    }

    /**
     * store the first run
     */
    public void setRunned() {
        SharedPreferences.Editor edit = mPrefs.edit();
        edit.putBoolean("firstRun", false);
        edit.commit();
    }

    SharedPreferences mPrefs;

    /**
     * setting up preferences storage
     */
    public void firstRunPreferences() {
        Context mContext = getActivity().getApplicationContext();
        mPrefs = mContext.getSharedPreferences("myAppPrefs", 0);
    }

}