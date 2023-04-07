package com.example.deezactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.deezactivity.Adapters.MusicItemAdapter;
import com.example.deezactivity.Interface.IApiListener;
import com.example.deezactivity.Interface.IOnSelectMusic;
import com.example.deezactivity.Model.Music;
import com.example.deezactivity.ServiceAPI.ServiceApi;

import java.util.ArrayList;

public class ListFragment extends Fragment implements AdapterView.OnItemClickListener, IApiListener, TextWatcher {

    private ArrayList<Music> musics = new ArrayList<Music>();
    private ListView listMusics;
    private EditText search;
    private IOnSelectMusic listener;

    public void setListener(IOnSelectMusic listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, null);

        // getElement
        search = view.findViewById(R.id.editTextTextPersonName);
        listMusics = view.findViewById(R.id.listViewMusics);

        // Event
        listMusics.setOnItemClickListener(this);
        search.addTextChangedListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        listener.onSelectedMusic(musics.get(i));
    }

    @Override
    public void onGetMusics(ArrayList<Music> musics) {
        this.musics = musics;
        MusicItemAdapter musicAdapter = new MusicItemAdapter(musics, getContext());
        listMusics.setAdapter(musicAdapter);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String query = charSequence.toString();
        ServiceApi.searchQuery(getContext(), query, this);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}