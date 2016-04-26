package com.example.guiteam.binge;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.guiteam.binge.LocalMovie;
import com.example.guiteam.binge.LocalMovieObject;

import java.util.ArrayList;

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
        db.readLocalMovie(getApplicationContext());
        String[] returnStrings = new String[1];
        returnStrings[0] = "No results found.";
        try {
            if(db.searchTitle(search).size()!=0) {
                returnStrings = new String[db.searchTitle(search).size()];
                for (int i = 0; i < db.searchTitle(search).size(); i++) {
                    returnStrings[i] = db.searchTitle(search).get(i).toString();
                }
            }
        }catch(Exception e){e.getMessage();}

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, returnStrings);
        ListView content = (ListView) findViewById(R.id.listView);
        content.setAdapter(adapter);

    }

}
