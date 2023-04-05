package com.example.deezactivity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deezactivity.Model.Music;
import com.example.deezactivity.R;
import com.example.deezactivity.ServiceAPI.ServiceApi;

import java.util.ArrayList;

public class MusicItemAdapter extends BaseAdapter {

    private ArrayList<Music> musics = new ArrayList<Music>();
    private Context context;

    public MusicItemAdapter(ArrayList<Music> musics, Context context) {
        this.musics = musics;
        this.context = context;
    }

    @Override
    public int getCount() {
        return musics.size();
    }

    @Override
    public Object getItem(int i) {
        return musics.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_music, viewGroup, false);
        }

        ImageView cover = view.findViewById(R.id.imageViewItem);
        ServiceApi.loadImage(context, musics.get(i).getCover(), cover);
        //cover.setImageResource(musics.get(i).getCover());

        TextView artist = view.findViewById(R.id.textViewItemArtist);
        artist.setText(musics.get(i).getArtist());

        TextView album = view.findViewById(R.id.textViewItemAlbum);
        album.setText(musics.get(i).getAlbum());

        TextView title = view.findViewById(R.id.textViewItemTitle);
        title.setText(musics.get(i).getTitle());
        return view;
    }
}
