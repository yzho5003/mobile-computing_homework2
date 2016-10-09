package comp5216.sydney.edu.au.homework2;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    public String photoFileName;
    public File imageFile;
    public Uri tempuri;
    public static int mediaIndex;

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

        //init media fiel number
        mediaIndex = 0;

        //get IntExtra data, e.g. position

    }

    public void saveOnClick(View view){
        //get the value
        String itemTextString = itemText.getText().toString();
        if(itemTextString != null && itemTextString.length()>0){
            Intent intentPrevious = new Intent(EditAndAddItem.this, MainActivity_list.class);
            //put extra information into the bundle for access in the main activity
            intentPrevious.putExtra("itemText", itemTextString);
            intentPrevious.putExtra("imgUri", tempuri.toString());
            setResult(RESULT_OK,intentPrevious);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Picture taken!" + tempuri.toString(), Toast.LENGTH_SHORT).show();
                // Load the taken image into a preview
            } else { // Result was a failure
                Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onTakePhoto(View view){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mediaIndex++;
        photoFileName = "test" + mediaIndex + ".jpg";
        imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), photoFileName);
        tempuri = Uri.fromFile(imageFile);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, tempuri.toString());
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }
}
