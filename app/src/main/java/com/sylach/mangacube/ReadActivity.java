package com.sylach.mangacube;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

    private int chapterPosition, pageDisplayNumber, pageNumber, pageTotal, chapterTotal;

    private MangaData mangaData;
    private ArrayList<ChapterInfo> chaptersData;
    private Page[] pages;

    private ViewPager viewPager;
    private ReaderAdapter readerAdapter;
    private TextView tvCMenu;
    private TextView textViewPage;
    private Toolbar toolbar;
    private TextView textViewRManga;
    private TextView textViewRChapter;
    private RelativeLayout rlBottom;
    //private RelativeLayout rlTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        onLoadLayout();

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        sourceId = b.getString("s_id");
        mangaId = b.getString("m_id");
        mangaData = b.getParcelable("m_data");
        chapterPosition = b.getInt("position");
        chaptersData = b.getParcelableArrayList("c_data");
        chapterId = chaptersData.get(chapterPosition).getId();
        chapterTotal = chaptersData.size();

        textViewRManga.setText(
            mangaData.getTitle()
        );

        textViewRChapter.setText(
                chaptersData.get(chapterPosition).getNumber()
        );

        requestChapterPages(
            String.format("%s%s",
                Endpoint.MANGA_EDEN_CHAPTER.getValue(),
                chapterId
            )
        );
    }
    public void onLoadLayout(){

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rlBottom = (RelativeLayout) findViewById(R.id.rlBottom);
        viewPager = (ViewPager) findViewById(R.id.viewPagerChapter);
        tvCMenu = (TextView) findViewById(R.id.tvCMenu);
        textViewPage = (TextView) findViewById(R.id.textViewPage);
        textViewRChapter = (TextView) findViewById(R.id.textViewRChapter);
        textViewRManga = (TextView) findViewById(R.id.textViewRManga);
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
                        pageTotal = pageImgs.length;
                        pageDisplayNumber = 1;
                        pageNumber = 0;

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

        updatePageNumber();

        readerAdapter = new ReaderAdapter(
                getApplicationContext(),
                pageImgs,
                toolbar,
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
                pageDisplayNumber = position +1;
                pageNumber = position;

                updatePageNumber();

                if(position == pageImgs.length -1){
                    if(chapterPosition > 0){
                        chapterPosition--;
                        loadChapterPages();
                    }
                    else
                        Toast.makeText(getApplicationContext(), R.string.lastPage, Toast.LENGTH_SHORT).show();
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
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void loadChapterPages(){
/*
        Handler handlerTimer = new Handler();
        handlerTimer.postDelayed(new Runnable(){
            public void run() {*/

        textViewRChapter.setText(
                chaptersData.get(chapterPosition).getNumber()
        );

                requestChapterPages(
                        String.format("%s%s",
                                Endpoint.MANGA_EDEN_CHAPTER.getValue(),
                                chaptersData.get(chapterPosition).getId()
                        )
                );
          /*  }}, 1500
        );*/
    }
    public void updatePageNumber(){
        textViewPage.setText(
                String.format("%s - %s",
                        Integer.toString(pageDisplayNumber),
                        Integer.toString(pageTotal)
                )
        );
    }
//

    public void tvNextPage_Clicked(View view) {
       // Toast.makeText(getApplicationContext(), "tvNextPage_Clicked", Toast.LENGTH_SHORT).show();



        if(pageNumber < pageTotal -1)
        {
            pageNumber++;
            viewPager.setCurrentItem(pageNumber);
        }

    }

    public void tvPreviousPage_Clicked(View view) {
       // Toast.makeText(getApplicationContext(), "tvPreviousPage_Clicked", Toast.LENGTH_SHORT).show();


        if(pageNumber > 0)
        {
            pageNumber--;
            viewPager.setCurrentItem(pageNumber);
        }


    }
    public void tvNextChap_Clicked(View view) {

        if(chapterPosition > 0){
            chapterPosition--;


            loadChapterPages();
        }
    }

    public void tvNextPrevious_Clicked(View view) {
        if(chapterPosition < chapterTotal -1){
            chapterPosition++;


            loadChapterPages();
        }
    }
    public String formatChapterTextViewString(){

        String t = chaptersData.get(chapterPosition).getTitle();
        String n = chaptersData.get(chapterPosition).getNumber();
        String res = "";

        try {
            if(t == null)
                return n;

            if(t.contains(n))
                return t.replace(n, "");

        }catch (Exception e){}

        return res;
    }


}
