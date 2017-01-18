package com.example.magnus.laesomondo.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.magnus.laesomondo.R;
import com.nhaarman.supertooltips.ToolTip;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.nhaarman.supertooltips.ToolTipView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class TextFromNetAct extends Fragment {

    WebView web;
    FloatingActionButton load;
    ToolTipView myToolTipView;
    private Thread t;
    ToolTipRelativeLayout toolTipRelativeLayout;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_text_from_net, container, false);
        firstRunPreferences();

     toolTipRelativeLayout = (ToolTipRelativeLayout) v.findViewById(R.id.activity_main_tooltipRelativeLayout);

        final ToolTip toolTip = new ToolTip()
                .withText("Hent tekst")
                .withTextColor(Color.WHITE)
                .withColor(getResources().getColor(R.color.colorPrimaryDark))
                .withShadow()
                .withAnimationType(ToolTip.AnimationType.FROM_TOP);
        myToolTipView = toolTipRelativeLayout.showToolTipForView(toolTip,v.findViewById(R.id.floatingActionButton));


     t = new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    synchronized(this){
                        wait(3000);
                        if(!(getActivity() == null)) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    myToolTipView.performClick();
                                }
                            });
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();



        web = (WebView) v.findViewById(R.id.webviewContent);
        web.getSettings().setJavaScriptEnabled(true);
        web.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    WebView webView = (WebView) v;

                    switch(keyCode)
                    {
                        case KeyEvent.KEYCODE_BACK:
                            if(webView.canGoBack())
                            {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }

                return false;
            }
        });


        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        web.loadUrl("http://www.google.com");

        load = (FloatingActionButton) v.findViewById(R.id.floatingActionButton);

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
                    .setMessage("Gå ind på en hjemmeside,og find en tekst eller artikel og klik på ikonet hent")
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

            if (HTMLTExt.length() > 0) {
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left,
                        R.animator.exit_to_right, R.animator.enter_from_right).replace(R.id.container_main,
                        frag).addToBackStack(null).commit();
            }

            else {
                Toast.makeText(getActivity(), "Der er ingen tekst i dit nuværende web-vindue!", Toast.LENGTH_SHORT).show();

            }

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