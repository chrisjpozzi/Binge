package com.binge;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.binge.LocalMovie;
import com.binge.LocalMovieObject;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.binge.MovieActivity;

import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        String search;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                search = null;
            } else {
                search = extras.getString("searchElem");
            }
        } else {
            search = (String) savedInstanceState.getSerializable("searchElem");
        }
        if (search.equals("%listall%")) {
            LocalMovie db = new LocalMovie();
            db.readLocalMovie(getApplicationContext());
            String[] returnStrings = new String[1];
            returnStrings[0] = "No results found.";
            try {
                returnStrings = new String[db.listAll().size()];
                for (int j = 0; j < db.listAll().size(); j++) {
                    returnStrings[j] = db.listAll().get(j).title.toString();
                }

            } catch (Exception e) {
                e.getMessage();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, returnStrings);
            ListView content = (ListView) findViewById(R.id.listView);
            content.setAdapter(adapter);
            content.setOnItemClickListener(new ItemList());
        }
        else if(search.equals("%netflix%")){
            LocalMovie db = new LocalMovie();
            db.readLocalMovie(getApplicationContext());
            String[] returnStrings = new String[1];
            returnStrings[0] = "No results found.";
            try{
                returnStrings = new String[db.searchNetflix().size()];
                for(int h=0; h<db.searchNetflix().size(); h++){
                    returnStrings[h] = db.searchNetflix().get(h).title.toString();
                }
            }catch (Exception e){
                e.getMessage();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, returnStrings);
            ListView content = (ListView) findViewById(R.id.listView);
            content.setAdapter(adapter);
            content.setOnItemClickListener(new ItemList());
        }
        else if(search.equals("%hulu%")){
            LocalMovie db = new LocalMovie();
            db.readLocalMovie(getApplicationContext());
            String[] returnStrings = new String[1];
            returnStrings[0] = "No results found.";
            try{
                returnStrings = new String[db.searchHulu().size()];
                for(int h=0; h<db.searchHulu().size(); h++){
                    returnStrings[h] = db.searchHulu().get(h).title.toString();
                }
            }catch (Exception e){
                e.getMessage();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, returnStrings);
            ListView content = (ListView) findViewById(R.id.listView);
            content.setAdapter(adapter);
            content.setOnItemClickListener(new ItemList());
        }
        else {
            LocalMovie db = new LocalMovie();
            db.readLocalMovie(getApplicationContext());
            String[] returnStrings = new String[1];
            returnStrings[0] = "No results found.";
            try {
                if (db.searchTitle(search).size() != 0) {
                    returnStrings = new String[db.searchTitle(search).size()];
                    for (int i = 0; i < db.searchTitle(search).size(); i++) {
                        returnStrings[i] = db.searchTitle(search).get(i).title.toString();
                    }
                }
            } catch (Exception e) {
                e.getMessage();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, returnStrings);
            ListView content = (ListView) findViewById(R.id.listView);
            content.setAdapter(adapter);
            content.setOnItemClickListener(new ItemList());


        }

    }
    class ItemList implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            String thisMovie = (String)parent.getItemAtPosition(position);
            if(!thisMovie.equals("No results found.")) {
                Intent intent = new Intent(ListingActivity.this, MovieActivity.class);
                intent.putExtra("thisMovie", thisMovie);
                startActivity(intent);
            }
        }
    }
}