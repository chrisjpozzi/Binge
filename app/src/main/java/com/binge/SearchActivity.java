package com.binge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.guiteam.binge.LocalMovieObject;


public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button list;
    LocalMovieObject[] movielist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner dropdown = (Spinner)findViewById(R.id.spinner);
        String[] items = new String[]{"Title", "Genre", "Year"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);



        list = (Button) findViewById(R.id.list);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchActivity.this, ListingActivity.class);
                i.putExtra("searchElem","%listall%");
                startActivity(i);
            }
        });

    }
    /*
    *When a movie is selected the information pops up as a Toast notification
     */
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    /*
    *If nothing is selected nothing happens
     */
    public void onNothingSelected(AdapterView<?> arg0){
        return;
    }

    /*
    *Takes the values to be searched from the user input and puts it into a string.
     */
    public void search(View view) throws Exception{
        EditText text = (EditText) findViewById(R.id.editText);
        String input = text.getText().toString();

        Intent i = new Intent(SearchActivity.this, ListingActivity.class);
        i.putExtra("searchElem", input);
        startActivity(i);

    }

    public void netflix(View view) throws Exception{
        Intent intent = new Intent(SearchActivity.this, ListingActivity.class);
        intent.putExtra("searchElem", "%netflix%");
        startActivity(intent);
    }

    public void hulu(View view) throws Exception{
        Intent intent = new Intent(SearchActivity.this, ListingActivity.class);
        intent.putExtra("searchElem", "%hulu%");
        startActivity(intent);
    }

}
