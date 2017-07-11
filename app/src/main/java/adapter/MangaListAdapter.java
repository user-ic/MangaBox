package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sylach.mangabox.R;


import model.MangaData;

/**
 * Created by Panda on 11-07-2017.
 */

public class MangaListAdapter extends RecyclerView.Adapter<MangaListAdapter.MyViewHolder> {
    MangaData[] arrayList = null;
    Context context = null;
    int position = 0;

    public MangaListAdapter(Context context, MangaData[] arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_manga, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MangaData alObj = arrayList[position];
        this.position = position;
        Picasso.with(holder.itemView.getContext())
                .load(arrayList[(position)].getCover())
                .placeholder(R.drawable.ic_menu_slideshow)
                .fit()
                .error(R.drawable.ic_menu_manage)
                .into(holder.ivVhCover);

        holder.tvVhTitle.setText(alObj.getTitle());

                 /*
        holder.tvVhTitle.setText(alObj.getTitle());
        holder.ivLiMangaCover.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(holder.itemView.getContext().getApplicationContext(), MangaActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("manga_sid", Integer.toString(0));
                intent.putExtra("manga_id", arrayList.get(position).getId());
                holder.itemView.getContext().getApplicationContext().startActivity(intent);


            }
        });
        */
    }



       /*
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                */


    @Override
    public int getItemCount() {
        return arrayList.length;
    }

    public int getPosition() {
        return position;
    }

    public void setFilter(MangaData[] list) {
        arrayList = list;
        //notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivVhCover;
        TextView tvVhTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivVhCover = (ImageView) itemView.findViewById(R.id.ivLiMangaCover);
            tvVhTitle = (TextView) itemView.findViewById(R.id.tvLiMangaTitle);
        }
    }
}