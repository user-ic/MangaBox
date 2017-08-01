package adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sylach.mangabox.MangaActivity;
import com.sylach.mangabox.R;


import model.MangaStack;

/**
 * Created by Panda on 11-07-2017.
 */

public class MangaListAdapter extends RecyclerView.Adapter<MangaListAdapter.MangaViewHolder> {
    MangaStack[] arrayMangaStack = null;
    Context context = null;
    int position = 0;

    public MangaListAdapter(Context context, MangaStack[] arrayMangaStack) {
        this.context = context;
        this.arrayMangaStack = arrayMangaStack;
    }

    @Override
    public MangaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_manga, parent, false);
        return new MangaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MangaViewHolder holder, final int position) {

        final MangaStack alObj = arrayMangaStack[position];
        this.position = position;

        Picasso.with(holder.itemView.getContext())
                .load(arrayMangaStack[(position)].getCover())
                .placeholder(R.drawable.ic_menu_slideshow)
                .fit()
                .error(R.drawable.ic_menu_manage)
                .into(holder.ivVhCover);

        holder.tvVhTitle.setText(alObj.getTitle());


        holder.tvVhTitle.setText(alObj.getTitle());
        holder.ivVhCover.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MangaActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("MANGA_ID", arrayMangaStack[position].getId());
                intent.putExtras(bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        });

    }



       /*
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                */


    @Override
    public int getItemCount() {
        return arrayMangaStack.length;
    }

    public int getPosition() {
        return position;
    }

    public void setFilter(MangaStack[] list) {
        arrayMangaStack = list;
        //notifyDataSetChanged();
    }

    public static class MangaViewHolder extends RecyclerView.ViewHolder {

        ImageView ivVhCover;
        TextView tvVhTitle;

        public MangaViewHolder(View itemView) {
            super(itemView);
            ivVhCover = (ImageView) itemView.findViewById(R.id.ivLiMangaCover);
            tvVhTitle = (TextView) itemView.findViewById(R.id.tvLiMangaTitle);
        }
    }
}
