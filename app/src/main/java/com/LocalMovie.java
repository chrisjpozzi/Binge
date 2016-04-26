package com.example.guiteam.binge;
//import java.util.FileReader;
import android.content.Context;

import java.io.File;
import java.io.PrintStream;
import java.io.*;
import java.lang.Integer;
import java.util.*;

import com.example.guiteam.binge.LocalMovieObject;
/**
 * Created by Samantha on 4/21/2016.
 *
 * Reads movie objects from local text file and searches objects by title.
 */
public class LocalMovie{
    LocalMovieObject[] movies; //array of movie objects
    String movieList="com/example/guiteam/binge/tinymovielist.txt"; //file movies are stored in
    int max=10000; //max amount of movies
    int n; //number of movies

    /*
    * Reads movie file and puts the movies into movie object.
     */
    public void readLocalMovie(Context context)
    {
        BufferedReader f;
        try
        {
            InputStream in = context.getAssets().open("tinymovielist.txt");
            f = new BufferedReader(new InputStreamReader(in));
            movies = new LocalMovieObject[max];

            n=0;
            String title = f.readLine();
            while(title!=null)
            {
                movies[n]=new LocalMovieObject(title, f.readLine(), new Integer(f.readLine()).intValue());
                title = f.readLine();
                n++;
            }
            f.close();
        }
        catch (Exception e)
        {
            System.err.println("Invalid data file: "+movieList);
            System.err.println(e);
        }
    }

    public ArrayList<LocalMovieObject> searchTitle(String search)
    {
        ArrayList<LocalMovieObject> result = new ArrayList<LocalMovieObject>(1);
        for (int i=0; i<n; i++)
            if(movies[i].matchTitle(search)) {
                result.add(movies[i]);
            }

        return result;
    }
}