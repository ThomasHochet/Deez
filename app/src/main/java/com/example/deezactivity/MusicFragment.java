package com.example.deezactivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deezactivity.Model.Music;
import com.example.deezactivity.ServiceAPI.ServiceApi;

import java.io.IOException;

public class MusicFragment extends Fragment implements View.OnClickListener {

    private TextView artist, album, title;
    private ImageView cover;
    private Button play, link;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private Music music;

    public void setMusic(Music music) {
        this.music = music;
        refresh();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, null);

        // Bouton retour
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        artist = view.findViewById(R.id.textViewArtist);
        album = view.findViewById(R.id.textViewAlbum);
        title = view.findViewById(R.id.textViewMusic);
        cover = view.findViewById(R.id.imageViewCover);
        play = view.findViewById(R.id.buttonListen);
        link = view.findViewById(R.id.buttonDeezer);

        play.setOnClickListener(this);
        link.setOnClickListener(this);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Yes");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        return view;
    }



    public void refresh() {
        artist.setText(music.getArtist());
        album.setText(music.getAlbum());
        title.setText(music.getTitle());
        ServiceApi.loadImage(getContext(), music.getCover(), cover);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(link)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(music.getLink()));
            startActivity(intent);
        }
        if(!mediaPlayer.isPlaying()) {
            play.setText(this.getResources().getString(R.string.pause));
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(getContext(), Uri.parse(music.getUrl()));
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            play.setText(this.getResources().getString(R.string.listen));
            mediaPlayer.stop();
        }

    }



}