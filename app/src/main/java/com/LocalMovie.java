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
 * Reads movie objects from local text file "tinymovielist.txt" and searches objects by title.
 */
public class LocalMovie{
    LocalMovieObject[] movies; //array of movie objects
    String movieList="com/example/guiteam/binge/tinymovielist.txt"; //file movies are stored in
    int max=10000; //max amount of movies
    int n; //number of movies

    /*
    * Reads movie file and puts the movies into movie object.
    * @param context
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
    /*
    *Lists all of the movies in the text file.
    *@return an array list of all of the movies in the text file.
     */
    public ArrayList<LocalMovieObject> listAll(){
        ArrayList<LocalMovieObject> result = new ArrayList<LocalMovieObject>(1);
        for(int i=0; i<n; i++){
            result.add(movies[i]);
        }
        return result;
    }
    /*
    *Searches through movies by title and returns any/all matches of search.
    * @param String to be searched for through the titles
    * @return an ArrayList of the results that match the string searched for.
     */
    public ArrayList<LocalMovieObject> searchTitle(String search)
    {
        ArrayList<LocalMovieObject> result = new ArrayList<LocalMovieObject>(1);
        for (int i=0; i<n; i++)
            if(movies[i].matchTitle(search)) {
                result.add(movies[i]);
            }

        return result;
    }
    /*
    *Search through movie titles for the exact match of the value searched.
    * @param Accepts a String value of what was searched for.
    * @Return If an exact match is found it returns that element of the movies array.
    * If no exact match is found null is returned.
     */
    public LocalMovieObject exactMatch(String elem){
        for(int i=0; i<n; i++){
            if(movies[i].title.equals(elem)){
                return movies[i];
            }
        }
        return null;
    }
}