package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sylach.mangacube.MangaActivity;
import com.sylach.mangacube.R;

import model.MangaData;

public class GeneralFragment extends Fragment {

    private ImageView ivGCover;
    private TextView tvGDesc, tvGCat, tvGTitle;
    private Button btBkm, btDown;
    private MangaActivity mangaActivity;
    private MangaData mangaData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_general, container, false);
        mangaActivity = (MangaActivity) getActivity();
        mangaData = mangaActivity.getMangaData();


        btBkm = (Button) view.findViewById(R.id.btBkm);
        btDown = (Button) view.findViewById(R.id.btDown);
        ivGCover = (ImageView) view.findViewById(R.id.ivGCover);
        tvGDesc = (TextView) view.findViewById(R.id.tvGDesc);
        tvGCat = (TextView) view.findViewById(R.id.tvGCat);
        tvGTitle = (TextView) view.findViewById(R.id.tvGTitle);

        tvGCat.setText(mangaData.getCategories());
        tvGTitle.setText(mangaData.getTitle());
        tvGDesc.setText(mangaData.getDescription());

        Picasso.with(getContext())
                .load(mangaData.getImage())
                .placeholder(R.drawable.ic_menu_camera)
                .error(R.drawable.ic_menu_share)
                .fit()
                .into(ivGCover);

        return view;
    }


}
