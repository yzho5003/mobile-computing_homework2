package comp5216.sydney.edu.au.homework2;

/**
 * Created by jason on 9/10/16.
 */

public class CustomItem {
    public String text;
    public String imagePath;
    public String location;

    public CustomItem(String web, String imagePath, String location){
        this.text = web;
        this.imagePath = imagePath;
        this.location = location;
    }
}