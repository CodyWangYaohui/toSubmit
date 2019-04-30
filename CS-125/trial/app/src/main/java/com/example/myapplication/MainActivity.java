package com.example.myapplication;

import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.util.Log;
import android.widget.ImageView;
import java.util.Random;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Map;
import java.util.HashMap;


import android.view.Window;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {



    final String TAG = "Lab12:Main";
    RequestQueue requestQueue;
    JSONObject weatherInfo;

    ImageView geoffy;

    ImageView watering;
    ImageView scissoring;
    ImageView shampooing;

    ImageView chuchu;
    ImageView crazychuchu;

    ImageView xyz;
    ImageView changedXYZ;

    ImageView littlegrass;
    ImageView largegrass;

    ImageView flower1;
    ImageView crazyflower1;

    ImageView flower2;
    ImageView crazyflower2;

    ConstraintLayout theWholePicture;


    ImageView hair;

    ImageView hair1;
    ImageView hair2;
    ImageView hair3;
    ImageView hair4;
    ImageView hair5;

    private boolean gameState = false;

    private boolean SthOnHead = false;




    public void growWhat(double growSth, double growHair) {
        Random temp = new Random();
        double indx = temp.nextDouble();
        if (indx >= growSth && indx < growHair) {
            Random tem = new Random();
            double inside = tem.nextDouble();
            if (inside < 0.2) {
                chuchu.setVisibility(View.VISIBLE);
            } else if (inside > 0.2 && inside < 0.4) {
                xyz.setVisibility(View.VISIBLE);
            } else if (inside > 0.4 && inside < 0.6) {
                flower1.setVisibility(View.VISIBLE);
            } else if (inside > 0.6 && inside < 0.8) {
                flower2.setVisibility(View.VISIBLE);
            } else {
                littlegrass.setVisibility(View.VISIBLE);
            }
            SthOnHead = true;
        } else if (indx >= growHair && indx <= 1) {
            Random tempor = new Random();
            double inside = tempor.nextDouble();
            if (inside < 0.2) {
                geoffy.setVisibility(View.INVISIBLE);
                hair.setVisibility(View.VISIBLE);
            }
            SthOnHead = true;
        } else if (indx >= 0 && indx < growSth) {
            return;
        }
    }
    public void magic() {
        if (!gameState && SthOnHead) {
            if (hair.getVisibility() == View.VISIBLE) {
                Random temp = new Random();
                double indx = temp.nextDouble();
                if (indx >= 0 && indx < 0.2) {
                    new Animation(R.id.scissoring, 500).execute();
                    hair.setVisibility(View.INVISIBLE);
                    hair1.setVisibility(View.VISIBLE);
                } else if (indx >= 0.2 && indx < 0.4) {
                    new Animation(R.id.scissoring, 500).execute();
                    hair.setVisibility(View.INVISIBLE);
                    hair2.setVisibility(View.VISIBLE);
                } else if (indx >= 0.4 && indx < 0.6) {
                    new Animation(R.id.scissoring, 500).execute();
                    hair.setVisibility(View.INVISIBLE);
                    hair3.setVisibility(View.VISIBLE);
                } else if (indx >= 0.6 && indx < 0.8) {
                    new Animation(R.id.scissoring, 500).execute();
                    hair.setVisibility(View.INVISIBLE);
                    hair4.setVisibility(View.VISIBLE);
                } else if (indx >= 0.8 && indx <= 1){
                    new Animation(R.id.scissoring, 500).execute();
                    hair.setVisibility(View.INVISIBLE);
                    hair5.setVisibility(View.VISIBLE);
                }
                gameState = true;
            }  else {
                if (xyz.getVisibility() == View.VISIBLE) {
                    new Animation(R.id.scissoring, 500).execute();
                    xyz.setVisibility(View.INVISIBLE);
                    changedXYZ.setVisibility(View.VISIBLE);
                } else if (chuchu.getVisibility() == View.VISIBLE) {
                    new Animation(R.id.scissoring, 500).execute();
                    chuchu.setVisibility(View.INVISIBLE);
                    crazychuchu.setVisibility(View.VISIBLE);
                } else if (flower1.getVisibility() == View.VISIBLE) {
                    new Animation(R.id.scissoring, 500).execute();
                    flower1.setVisibility(View.INVISIBLE);
                    crazyflower1.setVisibility(View.VISIBLE);
                } else if (flower2.getVisibility() == View.VISIBLE) {
                    new Animation(R.id.scissoring, 500).execute();
                    flower2.setVisibility(View.INVISIBLE);
                    crazyflower2.setVisibility(View.VISIBLE);
                } else if (littlegrass.getVisibility() == View.VISIBLE) {
                    new Animation(R.id.scissoring, 500).execute();
                    littlegrass.setVisibility(View.INVISIBLE);
                    largegrass.setVisibility(View.VISIBLE);
                }
            }

        }
    }
    public void startAPICall() {
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                "http://api.openweathermap.org/data/2.5/weather?zip=61820,us&APPID=5a337d94e431fa24402018f3163f08f1",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        Log.d(TAG, response.toString());
                        Log.d("hello", "geoff");
                        weatherInfo = response;
                        double temperature;
                        try {
                            JSONObject main = weatherInfo.getJSONObject("main");
                            temperature = main.getDouble("temp");
                        } catch(Exception e) {
                            return;
                        }
                        if (temperature >= 273.0 && temperature < 290.0) {
                            theWholePicture.setBackground(getDrawable(R.drawable.rainning));
                        } else if (temperature >= 290.0 && temperature < 310) {
                            theWholePicture.setBackground(getDrawable(R.drawable.wolkig));
                        } else if (temperature >= 310 && temperature < 330){
                            theWholePicture.setBackground(getDrawable(R.drawable.sonny));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
                        Log.w(TAG, error.toString());
                    }
                });
        requestQueue.add(jsonObjectRequest);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ImageButton water = findViewById(R.id.water);
        ImageButton weather = findViewById(R.id.weather);
        ImageButton scissor = findViewById(R.id.scissor);
        ImageButton shampoo = findViewById(R.id.shampoo);
        ImageButton restart = findViewById(R.id.restart);

        theWholePicture = findViewById(R.id.theWholePicture);


        geoffy = findViewById(R.id.geoffy);

        watering = findViewById(R.id.wateringg);
        scissoring = findViewById(R.id.scissoring);
        shampooing = findViewById(R.id.shampooing);

        chuchu = findViewById(R.id.chuchu);
        crazychuchu = findViewById(R.id.crazychuchu);

        xyz = findViewById(R.id.xyz);
        changedXYZ = findViewById(R.id.changeXYZ);

        littlegrass = findViewById(R.id.littleGrass);
        largegrass = findViewById(R.id.largeGrass);

        flower1 = findViewById(R.id.flower1);
        crazyflower1 = findViewById(R.id.crazyflower1);

        flower2 = findViewById(R.id.flower2);
        crazyflower2 = findViewById(R.id.crazyflower2);

        hair = findViewById(R.id.normalHair);

        hair1 = findViewById(R.id.hair1);
        hair2 = findViewById(R.id.hair2);
        hair3 = findViewById(R.id.hair3);
        hair4 = findViewById(R.id.hair4);
        hair5 = findViewById(R.id.hair5);

        weather.setOnClickListener(v -> {
            if (!gameState && !SthOnHead) {
                startAPICall();
            }
        });


        scissoring.setVisibility(View.INVISIBLE);
        scissor.setOnClickListener(v -> {
            magic();
        });


        shampooing.setVisibility(View.INVISIBLE);
        shampoo.setOnClickListener(v -> {
            if (!gameState && !SthOnHead) {
                new Animation(R.id.shampooing, 500).execute();
                growWhat(0.3, 0.5);
            }
        });



        restart.setOnClickListener(v -> {
            chuchu.setVisibility(View.INVISIBLE);
            crazychuchu.setVisibility(View.INVISIBLE);
            xyz.setVisibility(View.INVISIBLE);
            changedXYZ.setVisibility(View.INVISIBLE);
            hair.setVisibility(View.INVISIBLE);
            hair1.setVisibility(View.INVISIBLE);
            hair2.setVisibility(View.INVISIBLE);
            hair3.setVisibility(View.INVISIBLE);
            hair4.setVisibility(View.INVISIBLE);
            hair5.setVisibility(View.INVISIBLE);
            flower1.setVisibility(View.INVISIBLE);
            crazyflower1.setVisibility(View.INVISIBLE);
            flower2.setVisibility(View.INVISIBLE);
            crazyflower2.setVisibility(View.INVISIBLE);
            littlegrass.setVisibility(View.INVISIBLE);
            largegrass.setVisibility(View.INVISIBLE);
            scissoring.setVisibility(View.INVISIBLE);
            watering.setVisibility(View.INVISIBLE);
            shampooing.setVisibility(View.INVISIBLE);


            gameState = false;
            SthOnHead = false;

            geoffy.setVisibility(View.VISIBLE);

        });



        water.setOnClickListener(v -> {
            if (!SthOnHead) {
                new Animation(R.id.wateringg, 500).execute();
                growWhat(0.4, 0.6);
            }
        });





    }

    private class Animation extends AsyncTask<Integer,Integer,Integer> {
        private int id, duration;
        Animation(int setId, int setDuration) {
            id = setId;
            duration = setDuration;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            findViewById(id).setVisibility(View.VISIBLE);
        }
        @Override
        protected Integer doInBackground(Integer[] para) {
            long t = System.currentTimeMillis();
            Log.d("project","loop start");
            while (System.currentTimeMillis() - t < duration) {}
            Log.d("project","loop end");
            findViewById(id).setVisibility(View.INVISIBLE);
            return null;
        }
    }
}
