package com.example.guiteam.binge;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String search;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                search = null;
            } else {
                search = extras.getString("searchElem");
            }
        } else {
            search = (String) savedInstanceState.getSerializable("searchElem");
        }

        LocalMovie db = new LocalMovie();
        db.readLocalMovie();
        LocalMovieObject[] movies = new LocalMovieObject[1000];
        try {
            System.arraycopy(db.searchTitle(search), 0, movies, 0, 1000 );
            //movies = db.searchTitle(search);
        }catch(Exception e){e.getMessage();}

        String[] returnStrings = new String[movies.length];
        for(int i=0; i<movies.length; i++){
            returnStrings[i] = search;
            if(movies[i]!=null) {
                returnStrings[i] = movies[i].toString();
            }
            else{
                //i=movies.length;
            }
        }

/*
        String[] test = new String[25];
        for(int j=0; j<25; j++){
            test[j] = "New String"+j;
        }
        */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, returnStrings);
        ListView content = (ListView) findViewById(R.id.listView);
        content.setAdapter(adapter);

    }

}
