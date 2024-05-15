package com.khair.contole_ad;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    private AdView adView;

    private AdView adContainerView;

    LinearLayout bannerAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bannerAds = findViewById(R.id.bannerAds);
        BannerAds.loadAds(bannerAds, MainActivity.this);

        MobileAds.initialize(MainActivity.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView adView = new AdView(MainActivity.this);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        bannerAds.addView(adView);
        adView.setAdSize(AdSize.BANNER);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://abulk77912.000webhostapp.com/apps/hello.php";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.contains("AdON")){

                            AdRequest adRequest = new AdRequest.Builder().build();
                            adView.loadAd(adRequest);

                        }else {


                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);






    }

    public static class BannerAds {
        public static void loadAds(LinearLayout container, Context context) {
            MobileAds.initialize(context, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });
            AdView adView = new AdView(context);
            adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
            container.addView(adView);
            adView.setAdSize(AdSize.BANNER);



        }
    }
}

