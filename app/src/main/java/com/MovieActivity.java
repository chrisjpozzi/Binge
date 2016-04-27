package com.example.guiteam.binge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.guiteam.binge.LocalMovie;

import com.example.guiteam.binge.LocalMovieObject;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        String search;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                search = null;
            } else {
                search = extras.getString("thisMovie");
            }
        } else {
            search = (String) savedInstanceState.getSerializable("thisMovie");
        }
        LocalMovie db = new LocalMovie();
        db.readLocalMovie(getApplicationContext());
        LocalMovieObject thisMovie = db.exactMatch(search);
        TextView nameView = (TextView) findViewById(R.id.movieName);
        nameView.setText(thisMovie.title);
        TextView genreView = (TextView) findViewById(R.id.movieGenre);
        genreView.setText(thisMovie.genre);
        Integer year = new Integer(thisMovie.year);
        TextView yearView = (TextView) findViewById(R.id.movieYear);
        yearView.setText(year.toString());

    }
}
