package adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sylach.mangacube.MangaActivity;
import com.sylach.mangacube.R;
import com.sylach.mangacube.ReadActivity;

import java.util.ArrayList;

import model.ChapterInfo;

/**
 * Created by Panda on 01-08-2017.
 */


public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {
    ArrayList<ChapterInfo> arlData = null;
    MangaActivity mangaActivity = null;
    public ChapterAdapter(ArrayList<ChapterInfo> arlData, MangaActivity mangaActivity) {
        this.arlData = arlData;
        this.mangaActivity = mangaActivity;
    }

    public ArrayList<ChapterInfo> getArlData() {
        return arlData;
    }

    @Override
    public ChapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_chapter, parent, false);
        TextView tvliNum = v.findViewById(R.id.tvliNum);
        TextView tvliTitle = v.findViewById(R.id.tvliTitle);
        TextView tvliDate = v.findViewById(R.id.tvliDate);
        LinearLayout liliChap = v.findViewById(R.id.liChapter);

        return new ChapterViewHolder(v, tvliNum, tvliTitle, tvliDate, liliChap);
    }

    @Override
    public void onBindViewHolder(ChapterViewHolder holder, final int position) {

        final ChapterInfo alObj = arlData.get(position);
        holder.getTvVhNum().setText(alObj.getNumber());
        holder.getTvVhTitle().setText(alObj.getTitle());
        holder.getTvVhDate().setText(alObj.getDate());
        holder.getLiliChap().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //...
/*
                localDB.insertHistoryEntry(MangaActivity.manga_sid,
                        mangaDataMC.getId(),
                        mangaDataMC.getChapters().get(position).getId(),
                        mangaDataMC.getTitle(),
                        mangaDataMC.getImg_path(),
                        mangaDataMC.getChapters().get(position).getNum(),
                        Integer.toString(position),
                        Integer.toString(mangaDataMC.getChapters().size()));
*/
                Intent intent = new Intent(mangaActivity, ReadActivity.class);
                Bundle b = new Bundle();

                b.putString("m_id", mangaActivity.getMangaId());
                b.putString("s_id", mangaActivity.getSourceId());
                b.putInt("position", position);
                b.putParcelable("m_data", mangaActivity.getMangaData());
                b.putParcelableArrayList("c_data", arlData);

                intent.putExtras(b);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                mangaActivity.getApplicationContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arlData.size();
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder {

        TextView tvVhNum;
        TextView tvVhTitle;
        TextView tvVhDate;
        LinearLayout liliChap;

        public ChapterViewHolder(View itemView, TextView tvVhNum, TextView tvVhTitle, TextView tvVhDate, LinearLayout liliChap) {
            super(itemView);
            this.tvVhNum = tvVhNum;
            this.tvVhTitle = tvVhTitle;
            this.tvVhDate = tvVhDate;
            this.liliChap = liliChap;
        }

        public TextView getTvVhNum() {
            return tvVhNum;
        }

        public void setTvVhNum(TextView tvVhNum) {
            this.tvVhNum = tvVhNum;
        }

        public TextView getTvVhTitle() {
            return tvVhTitle;
        }

        public void setTvVhTitle(TextView tvVhTitle) {
            this.tvVhTitle = tvVhTitle;
        }

        public TextView getTvVhDate() {
            return tvVhDate;
        }

        public void setTvVhDate(TextView tvVhDate) {
            this.tvVhDate = tvVhDate;
        }

        public LinearLayout getLiliChap() {
            return liliChap;
        }
    }
}
