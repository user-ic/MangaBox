package com.sylach.mangacube;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

import adapter.ReaderAdapter;
import model.ChapterInfo;
import model.Endpoint;
import model.MangaData;
import model.Page;
import volley.RequestManager;

public class ReadActivity extends AppCompatActivity {

    private String mangaId;
    private String sourceId;
    private String chapterId;
    private String[] pageImgs;

    private int chapterPosition, pagePosition;

    private MangaData mangaData;
    private ArrayList<ChapterInfo> chaptersData;
    private Page[] pages;

    private ViewPager viewPager;
    private ReaderAdapter readerAdapter;
    private TextView tvCMenu;
    private RelativeLayout rlBottom;
    private RelativeLayout rlTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        rlBottom = (RelativeLayout) findViewById(R.id.rlBottom);
        rlTop = (RelativeLayout) findViewById(R.id.rlToop);
        viewPager = (ViewPager) findViewById(R.id.viewPagerChapter);
        tvCMenu = (TextView) findViewById(R.id.tvCMenu);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        sourceId = b.getString("s_id");
        mangaId = b.getString("m_id");
        mangaData = b.getParcelable("m_data");
        chapterPosition = b.getInt("position");
        chaptersData = b.getParcelableArrayList("c_data");
        chapterId = chaptersData.get(chapterPosition).getId();


       // chapterId = mangaData.getChapters()chapterPosition].getId();

        requestChapterPages(
            String.format("%s%s",
                Endpoint.MANGA_EDEN_CHAPTER.getValue(),
                chapterId
            )
        );
    }

    public void requestChapterPages(String url){
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();
                        pages = gson.fromJson(response, Page[].class);
                        String[] tempPages = new String[pages.length];
                        pageImgs = new String[pages.length];

                        for (int i = 0; i < pages.length; i++)
                            tempPages[i] = (Endpoint.MANGA_EDEN_IMG.getValue() + pages[i].getImg_path());
                       // pageImgs = tempPages;

                        for(int i = tempPages.length -1,j = 0; i >= 0;j++, i--)
                            pageImgs[j] = tempPages[i];

                        buildReader();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //add error page
                        Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_LONG).show();
                    }
                });
        RequestManager.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    public void buildReader() {
        Toast.makeText(getApplicationContext(), "pos: " + Integer.toString(chapterPosition),Toast.LENGTH_SHORT).show();
        readerAdapter = new ReaderAdapter(
                getApplicationContext(),
                pageImgs,
                rlTop,
                rlBottom,
                tvCMenu
        );
        viewPager.setAdapter(readerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position)
            {
                pagePosition = position;
                if(position == pageImgs.length -1)
                {
                    if(chapterPosition > 0)
                    {
                       // Handler handlerTimer = new Handler();
                       // handlerTimer.postDelayed(new Runnable(){
                        //    public void run() {
                                /*
                                positionGen--;
                                GetChapterPages(ReadableOnly.MEDEN_CDATA +  mc.getChapters().get(positionGen).getId());
                                */
                        //    }}, 1500
                        //);
                    }
                    else
                        Toast.makeText(getApplicationContext(), "last page reached",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onPageScrollStateChanged(int state){

            }
        });
        /*
        textViewPrevPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        textViewNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */
    }

    public void tvNextPage_Clicked(View view) {
        Toast.makeText(getApplicationContext(), "tvNextPage_Clicked", Toast.LENGTH_SHORT).show();
        /*
        Log.d("b posPage",Integer.toString(posPage));
        if(posPage < IMAGES.size()-1)
        {
            posPage++;
            viewPager.setCurrentItem(posPage);
        }
        Log.d("a posPage",Integer.toString(posPage));
        */
    }

    public void tvPreviousPage_Clicked(View view) {
        Toast.makeText(getApplicationContext(), "tvPreviousPage_Clicked", Toast.LENGTH_SHORT).show();
      /*
      Log.d("b posPage",Integer.toString(posPage));
        if(posPage > 1)
        {
            posPage--;
            viewPager.setCurrentItem(posPage);
        }
        Log.d("a posPage",Integer.toString(posPage));
        */
    }
}
