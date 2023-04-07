package com.example.deezactivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.deezactivity.Interface.IOnSelectMusic;
import com.example.deezactivity.Model.Music;

public class MainActivity extends AppCompatActivity implements IOnSelectMusic {

    private ListFragment listFragment;
    private MusicFragment musicFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isMobile()) {
            listFragment = new ListFragment();
            musicFragment = new MusicFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frameLayout, listFragment)
                    .add(R.id.frameLayout, musicFragment)
                    .hide(musicFragment)
                    .commit();
            getSupportActionBar();
        } else {
            listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFragment);
            musicFragment = (MusicFragment) getSupportFragmentManager().findFragmentById(R.id.musicFragment);
            getSupportFragmentManager().beginTransaction()
                    .show(musicFragment)
                    .commit();
        }
        listFragment.setListener(this);
    }

    @Override
    public void onSelectedMusic(Music music) {
        musicFragment.setMusic(music);
        getSupportFragmentManager().beginTransaction()
            .hide(listFragment)
            .show(musicFragment)
            .commit();
    }

    @Override
    public void onBackPressed() {
        if(isMobile() && musicFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction()
                .hide(musicFragment)
                .show(listFragment)
                .commit();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (musicFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction()
                .hide(musicFragment)
                .show(listFragment)
                .commit();
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isMobile() {
        return findViewById(R.id.frameLayout) != null;
    }
}
