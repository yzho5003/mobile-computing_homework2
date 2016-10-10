package comp5216.sydney.edu.au.homework2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jason on 9/10/16.
 */

public class CustomListAdapter extends ArrayAdapter<CustomItem> {

    public CustomListAdapter(Context context, ArrayList<CustomItem> items){
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
//        LayoutInflater inflater = context.getLayoutInflater();
//        View rowView = inflater.inflate(R.layout.list_single, null, true);
//        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
//
//        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
//        txtTitle.setText(items[position].getText());
//
//        // imageView.setImageResource(items[position].getImagePath());
//        imageView.setImageResource(R.drawable.image1);
//        return rowView;
        //Get the Data item for this position
        CustomItem item = getItem(position);

        //Check if an existing view is being reused, otherwise inflate the view
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_single, parent, false);
            Log.e("in the loop","converview is not been used");
        }
        //Lookup view for data population
        ImageView img = (ImageView) convertView.findViewById(R.id.img);
        TextView text = (TextView) convertView.findViewById(R.id.txt);
        //populate the data into the template view using the data object
        try {
//            img.setImageURI(Uri.fromFile(new File(item.imagePath)));
//            File imageFile = new File(item.imagePath);
//            img.setImageResource(imageFile);


        } catch (Exception e) {
            img.setImageResource(R.drawable.image1);
            return null;
        }
        img.setImageBitmap(item.imageBitmap);
        text.setText(item.text);
        //Return the completed view to render on screen
        return convertView;
    }
}
