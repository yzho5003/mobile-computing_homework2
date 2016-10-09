package comp5216.sydney.edu.au.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;

public class MainActivity_list extends AppCompatActivity {

    //ui variables
    ListView listView;
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
        setContentView(R.layout.activity_main_list);

        //reference to the xml
        listView = (ListView)findViewById(R.id.listView);
        buttonNew = (Button)findViewById(R.id.btnAddItem);
        viewSwitch = (Switch)findViewById(R.id.switchView);

        //set Adapter
        items = new ArrayList<CustomItem>();
        itemsAdapter = new CustomListAdapter(this,items);
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(itemsAdapter);

        //Add item to adapter
        CustomItem newItem = new CustomItem("Google", "xxxx", "location");
        itemsAdapter.add(newItem);
    }

    public void switchViewToGrid(View view){
        Intent intent = new Intent(MainActivity_list.this, MainActivity_grid.class);

        if(intent!=null){
            startActivity(intent);
        }
    }
}