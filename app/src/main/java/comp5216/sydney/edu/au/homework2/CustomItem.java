package comp5216.sydney.edu.au.homework2;

import android.graphics.Bitmap;

/**
 * Created by jason on 9/10/16.
 */

public class CustomItem {
    public String text;
    public Bitmap imageBitmap;
    public String location;

    public CustomItem(String web, Bitmap imagePath, String location){
        this.text = web;
        this.imageBitmap = imagePath;
        this.location = location;
    }
}