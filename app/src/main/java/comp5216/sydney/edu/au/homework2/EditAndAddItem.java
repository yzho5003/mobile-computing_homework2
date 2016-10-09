package comp5216.sydney.edu.au.homework2;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditAndAddItem extends AppCompatActivity {

    //ui variables
    EditText itemText;
    TextView time;
    Button buttonPhoto;
    Button buttonSave;
    TextView buttonClose;

    //media
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public String photoFileName = "photo.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_and_add_item);

        //init ui varaibles
        itemText = (EditText) findViewById(R.id.itemText);
        time = (TextView) findViewById(R.id.time);
        buttonPhoto = (Button) findViewById(R.id.buttonPhoto);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonClose = (TextView) findViewById(R.id.buttonClose);

        //init time TextView
        SimpleDateFormat s = new SimpleDateFormat("dd-mm-yyyy");
        time.setText(s.format(new Date()));

        //get IntExtra data, e.g. position

    }

    public void saveOnClick(View view){
        //get the value
        String itemTextString = itemText.getText().toString();
        if(itemTextString != null && itemTextString.length()>0){
            Intent intentPrevious = new Intent(EditAndAddItem.this, MainActivity_list.class);
            //put extra information into the bundle for access in the mainactivity
            intentPrevious.putExtra("itemText", itemTextString);
            //media url
            //position data
            //BRING UP THE MAIN ACTIVITY
            //setResult(Activity.RESULT_OK, returnData);
            finish();
        }
    }

    public void cancel(View view){
        final Intent intentPrevious = new Intent(EditAndAddItem.this, MainActivity_list.class);

        //ALERT TO CONFIRM CANCEL
        AlertDialog.Builder builder = new AlertDialog.Builder(EditAndAddItem.this);
        builder.setTitle("Are you sure to give up this edit? Your unsaved edit will be discarded by click YES.")
                .setPositiveButton("YES", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){

                        if( intentPrevious != null ){
                            //bring up activity
                            finish();
                        }
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        //do nothing
                    }
                });
    }

    public Uri getFileUri(String fileName) {
        // Get safe storage directory for photos
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "HOMEWORK2");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs())
        { Log.d("HOMEWORK2", "failed to create directory");
        }

        // Return the file target for the photo based on filename
        return Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator + fileName));
    }

    public void onTakePhoto(View view){
        Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String fileName = "photo.jpg";
        photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, getFileUri(fileName));
        startActivityForResult(photoIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }
}
