package comp5216.sydney.edu.au.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Switch;

import java.util.ArrayList;

/**
 * Created by jason on 9/10/16.
 */

public class MainActivity_grid extends AppCompatActivity {
    //ui variables
    GridView gridView;
    Button buttonNew;
    Switch viewSwitch;

    //Construct the data source
    ArrayList<CustomItem> items;
    //Create the adapter to convert the array to views
    CustomListAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //use "activity_main_list" as the layout
        setContentView(R.layout.activity_main_grid);

        //reference to the xml
        gridView = (GridView)findViewById(R.id.gridView);
        buttonNew = (Button)findViewById(R.id.btnAddItem);
        viewSwitch = (Switch)findViewById(R.id.switchView);

        //set Adapter
        items = new ArrayList<CustomItem>();
        itemsAdapter = new CustomListAdapter(this,items);
        gridView = (GridView)findViewById(R.id.gridView);
        gridView.setAdapter(itemsAdapter);

        //Add item to adapter
        CustomItem newItem = new CustomItem("Google", "xxxx", "location");
        itemsAdapter.add(newItem);
    }

    public void switchViewToList(View view){
        Intent intent = new Intent(MainActivity_grid.this, MainActivity_list.class);

        if(intent != null){
            startActivity(intent);
        }
    }

    public void addNewItem(View view){
        Intent intent = new Intent(this, EditAndAddItem.class);
        if(intent != null){
            startActivity(intent);
        }
    }
}
