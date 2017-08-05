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

import java.util.Arrays;
import java.util.Collections;

import adapter.ChapterListAdapter;
import model.ChapterInfo;
import util.GeneralUtils;


public class ChaptersFragment extends Fragment {


    private RecyclerView recyclerViewChapters;
    private ChapterListAdapter chapterListAdapter;
    private LinearLayout liChapter;
    private MangaActivity mangaActivity;
    private LinearLayoutManager linearLayoutManager;
    private ChapterInfo[] chapters = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chapters, container, false);

        liChapter = (LinearLayout) view.findViewById(R.id.liChapter);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewChapters = (RecyclerView) view.findViewById(R.id.recyclerViewChapters);
        mangaActivity = (MangaActivity) getActivity();

        ChapterInfo[] rawChapters = mangaActivity
                        .getMangaData()
                        .getChapters();

        chapters = new ChapterInfo[rawChapters.length];

        for(int i = 0; i < rawChapters.length; i++)
            chapters[i] = new ChapterInfo(
                rawChapters[i].getNumber(),
                GeneralUtils.FormatToHumanDate(rawChapters[i].getDate()),
                GeneralUtils.FormatTitle(rawChapters[i].getTitle()),
                rawChapters[i].getId()
            );
        Collections.reverse(Arrays.asList(chapters));
        chapterListAdapter = new ChapterListAdapter(chapters);
        recyclerViewChapters.setLayoutManager(linearLayoutManager);
        recyclerViewChapters.setAdapter(chapterListAdapter);

        return view;
    }

}
