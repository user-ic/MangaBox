package com.sylach.mangacube;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import model.Endpoint;
import model.MangaData;

public class ReadActivity extends AppCompatActivity {

    private MangaData mangaData;
    private String mangaId;
    private String sourceId;
    private String chapterId;
    private int chapterPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);


        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        sourceId = b.getString("s_id");
        mangaId = b.getString("m_id");
        mangaData = b.getParcelable("m_data");
        chapterPosition = b.getInt("position");

        chapterId = mangaData.getChapters()[chapterPosition].getId();

        requestChapterPages(
            String.format("%s%s",
                Endpoint.MANGA_EDEN_CHAPTER.getValue(),
                chapterId
            )
        );
    }

    public void requestChapterPages(String url){

    }
}
