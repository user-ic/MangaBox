package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sylach.mangacube.MangaActivity;
import com.sylach.mangacube.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import adapter.ChapterAdapter;
import model.ChapterInfo;
import model.MangaData;
import util.GeneralUtils;


public class ChaptersFragment extends Fragment {


    private RecyclerView recyclerViewChapters;
    private ChapterAdapter chapterAdapter;
    private LinearLayout liChapter;
    private MangaActivity mangaActivity;
    private MangaData mangaData;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<ChapterInfo> chapters = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chapters, container, false);

        liChapter = (LinearLayout) view.findViewById(R.id.liChapter);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewChapters = (RecyclerView) view.findViewById(R.id.recyclerViewChapters);
        mangaActivity = (MangaActivity) getActivity();

        mangaData = mangaActivity.getMangaData();
        ArrayList<ChapterInfo> rawChapters = mangaActivity
                        .getMangaData()
                        .getChapters();

        chapters = new ArrayList<ChapterInfo> ();

        for(int i = 0; i < rawChapters.size(); i++)
            chapters.add(new ChapterInfo(
                rawChapters.get(i).getNumber(),
                GeneralUtils.FormatToHumanDate(rawChapters.get(i).getDate()),
                GeneralUtils.FormatTitle(rawChapters.get(i).getTitle()),
                rawChapters.get(i).getId()
            ));
        //Collections.reverse(Arrays.asList(chapters));
        chapterAdapter = new ChapterAdapter(chapters, mangaActivity);
        recyclerViewChapters.setLayoutManager(linearLayoutManager);
        recyclerViewChapters.setAdapter(chapterAdapter);

        return view;
    }

}
