package com.example.deezactivity.ServiceAPI;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.deezactivity.Interface.IApiListener;
import com.example.deezactivity.Model.Music;
import com.example.deezactivity.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ServiceApi {

    public static void loadImage(Context context, String url, final ImageView imageView){
        RequestQueue queue = Volley.newRequestQueue(context);
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        imageView.setImageResource(android.R.drawable.ic_menu_gallery);
                    }
                });
        queue.add(request);
    }

    public static void searchQuery(Context context, String query, IApiListener listener) {
        String url = context.getResources().getString(R.string.getListUrl) + "?q=";
        RequestQueue queue = Volley.newRequestQueue(context);
        if (query.equals("")) {
            listener.onGetMusics(new ArrayList<Music>());
        } else {
            StringRequest request = new StringRequest(Request.Method.GET, url + query,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            ArrayList<Music> musics = new ArrayList<Music>();
                            Gson gson = new Gson();
                            JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
                            try {

                                JsonArray jsonArray = jsonObject.getAsJsonArray("data");
                                for (JsonElement element : jsonArray) {
                                    JsonObject obj = element.getAsJsonObject();
                                    Music music = new Music(
                                            obj.get("title").getAsString(),
                                            obj.getAsJsonObject("album").get("title").getAsString(),
                                            obj.getAsJsonObject("artist").get("name").getAsString(),
                                            obj.getAsJsonObject("album").get("cover_medium").getAsString(),
                                            obj.get("preview").getAsString(),
                                            obj.get("link").getAsString()
                                    );
                                    musics.add(music);
                                }
                            } catch (JsonSyntaxException e) {
                                throw new RuntimeException(e);
                            }
                            listener.onGetMusics(musics);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(request);
        }
    }

}