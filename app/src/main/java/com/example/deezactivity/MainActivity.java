package com.example.deezactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.deezactivity.Adapters.MusicItemAdapter;
import com.example.deezactivity.Model.Music;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listMusics = findViewById(R.id.listViewMusics);

        ArrayList<Music> musics = new ArrayList<Music>();
        musics.add(new Music("Beautiful Light", "Origins", "Uppermost", "https://e-cdns-images.dzcdn.net/images/artist/11f5fc92d5acfb2c0e3dc95aaf88c2fd/250x250-000000-80-0-0.jpg", "https://cdns-preview-e.dzcdn.net/stream/c-e8a81e4bfba85d6162b8d4b5cca03225-7.mp3", "https://www.deezer.com/artist/185743"));
        musics.add(new Music("Flashback", "One", "Uppermost", "https://e-cdns-images.dzcdn.net/images/artist/11f5fc92d5acfb2c0e3dc95aaf88c2fd/250x250-000000-80-0-0.jpg", "https://cdns-preview-3.dzcdn.net/stream/c-3626e5601d9dc8559d82f94b8f7fff37-5.mp3", "https://www.deezer.com/artist/185743"));

        MusicItemAdapter musicAdapter = new MusicItemAdapter(musics, this);
        listMusics.setAdapter(musicAdapter);
    }
}