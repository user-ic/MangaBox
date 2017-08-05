package com.sylach.mangacube;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adapter.MangaFragmentAdapter;
import model.ChapterInfo;
import model.Endpoint;
import model.MangaData;
import util.GeneralUtils;
import volley.RequestManager;

public class MangaActivity extends AppCompatActivity {

    FragmentPagerAdapter mangaFragmentAdapter;
    ViewPager viewPager;
    private MangaData mangaData;

    private String mangaId = null;

    private String sourceId = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        Bundle bundle = getIntent().getExtras();
        mangaId = bundle.getString("MANGA_ID");
        //sourceId = bundle.getString("SOURCE_ID");
        sourceId = "0";
        requestMangaData(
                String.format("%s%s",
                    Endpoint.MANGA_EDEN_MANGA.getValue(),
                    mangaId
                )
        );
    }

    public void requestMangaData(String url) {
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mangaData = new MangaData();
                        ChapterInfo[] arrChapInfo = null;
                        JSONArray jsonArray;
                        JSONArray injsonChapters;
                        JSONArray jsonChapters;
                        String cats;
                        JSONObject json = null;

                        try {
                            json = new JSONObject(response);
                            jsonChapters = json.getJSONArray("chapters");

                            mangaData.setTitle(json.getString("title"));
                            mangaData.setDescription(json.getString("description"));
                            mangaData.setChapters_len(json.getInt("chapters_len"));

                            arrChapInfo = new ChapterInfo[mangaData.getChapters_len()];

                            for (int i = 0; i < mangaData.getChapters_len(); i++) {

                                injsonChapters = jsonChapters.getJSONArray(i);
                                arrChapInfo[i] = new ChapterInfo(
                                        injsonChapters.get(0).toString(),
                                        GeneralUtils.FormatToHumanDate(injsonChapters.get(1).toString()),
                                        injsonChapters.get(2).toString(),
                                        injsonChapters.get(3).toString()
                                );
                            }
                            mangaData.setChapters(arrChapInfo);
                            jsonArray = json.getJSONArray("categories");
                            cats = "";
                            for (int i=0; i< jsonArray.length();i++){
                                if(i < jsonArray.length() -1)
                                    cats += jsonArray.get(i) +", ";
                                else
                                    cats += jsonArray.get(i);
                            }
                            mangaData.setImage(
                                    String.format("%s%s",
                                            Endpoint.MANGA_EDEN_IMG.getValue(),
                                            json.getString("image")
                                    )
                            );
                            mangaData.setCategories(cats);

                            mangaFragmentAdapter = new MangaFragmentAdapter(getSupportFragmentManager());
                            viewPager.setAdapter(mangaFragmentAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_LONG).show();
                    }
                });
        RequestManager.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
    public  MangaData getMangaData(){
        return mangaData;
    }

    public  String getMangaId(){
        return mangaId;
    }

    public String getSourceId() {
        return sourceId;
    }

}
