package comp5216.sydney.edu.au.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jason on 9/10/16.
 */

public class MainActivity_grid extends AppCompatActivity {

    //variables
    public final static int ADD_NEW_ITEM = 100;

    //ui variables
    GridView gridView;
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
        setContentView(R.layout.activity_main_grid);

        //reference to the xml
        gridView = (GridView)findViewById(R.id.gridView);
        listView = (ListView)findViewById(R.id.listView);
        buttonNew = (Button)findViewById(R.id.btnAddItem);
        viewSwitch = (Switch)findViewById(R.id.switchView);

        //set Adapter
        items = new ArrayList<CustomItem>();
        itemsAdapter = new CustomListAdapter(this,items);
        gridView.setAdapter(itemsAdapter);
        listView.setAdapter(itemsAdapter);
        listView.setVisibility(View.GONE);
        viewSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    //Toast.makeText(getApplicationContext(),"list",Toast.LENGTH_LONG).show();
                    gridView.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                }else {
                    //Toast.makeText(getApplicationContext(),"grid",Toast.LENGTH_LONG).show();
                    listView.setVisibility(View.GONE);
                    gridView.setVisibility(View.VISIBLE);
                }
            }
        });

        //Add item to adapter
        CustomItem newItem = new CustomItem("Google", "xxxx", "location");
        itemsAdapter.add(newItem);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_NEW_ITEM) {
            if (resultCode == RESULT_OK) {
                String newItemTextValue=data.getStringExtra("itemText");
                String newItemImageUri = data.getStringExtra("imgUri");
                CustomItem newItem = new CustomItem(newItemTextValue, newItemImageUri, "location");
                Toast.makeText(this, "Picture taken!" + newItemTextValue, Toast.LENGTH_SHORT).show();
                itemsAdapter.add(newItem);
            }
        }
    }

//    public void switchViewToList(View view){
//        Intent intent = new Intent(MainActivity_grid.this, MainActivity_list.class);
//
//        if(intent != null){
//            startActivity(intent);
//        }
//    }

    public void addNewItem(View view){
        Intent intent = new Intent(this, EditAndAddItem.class);
        if(intent != null){
            startActivityForResult(intent, ADD_NEW_ITEM);
        }
    }
}
