package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.sylach.mangacube.MangaActivity;
import com.sylach.mangacube.R;

import database.DatabaseConn;
import model.MangaData;

public class GeneralFragment extends Fragment {

    private ImageView ivGCover;
    private TextView tvGDesc, tvGCat, tvGTitle;
    private Button btBkm, btDown;
    private MangaActivity mangaActivity;
    private String mangaId;
    private String sourceId;
    private MangaData mangaData;
    private DatabaseConn localDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_general, container, false);
        mangaActivity = (MangaActivity) getActivity();
        mangaData = mangaActivity.getMangaData();
        mangaId = mangaActivity.getMangaId();
        sourceId = mangaActivity.getSourceId();

        btBkm = (Button) view.findViewById(R.id.btBkm);
        btDown = (Button) view.findViewById(R.id.btDown);
        ivGCover = (ImageView) view.findViewById(R.id.ivGCover);
        tvGDesc = (TextView) view.findViewById(R.id.tvGDesc);
        tvGCat = (TextView) view.findViewById(R.id.tvGCat);
        tvGTitle = (TextView) view.findViewById(R.id.tvGTitle);

        tvGCat.setText(mangaData.getCategories());
        tvGTitle.setText(mangaData.getTitle());
        tvGDesc.setText(mangaData.getDescription());

        localDB = new DatabaseConn(getContext());

        //
        if(localDB.entryExists(mangaId, sourceId))
            handleFavoriteUI(0, 1);

        else
            handleFavoriteUI(0, 2);


        Picasso.with(getContext())
                .load(mangaData.getImage())
                .placeholder(R.drawable.ic_menu_camera)
                .error(R.drawable.ic_menu_share)
                .fit()
                .into(ivGCover);

        btBkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!localDB.entryExists(mangaId, sourceId)){
                    localDB. insertFavorite(
                            sourceId,
                            mangaId,
                            mangaData.getTitle(),
                            "state",
                            mangaData.getImage()
                    );
                    handleFavoriteUI(1, 1);
                }
                else
                    if(localDB.deleteFavorite(mangaId))
                        handleFavoriteUI(2, 2);


            }
        });
        return view;
    }

    private void handleFavoriteUI(int msg, int lbl){

        switch (msg){
            case 1:
                Toast.makeText(getContext(), "Added to Favorites", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getContext(), "Deleted from Favorites", Toast.LENGTH_SHORT).show();
                break;
        }
        switch (lbl){
            case 1:
                btBkm.setText(R.string.btnBkDel);
                break;
            case 2:
                btBkm.setText(R.string.btnBkAdd);
                break;
        }
    }
}
