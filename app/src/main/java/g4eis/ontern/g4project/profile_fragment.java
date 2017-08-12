package g4eis.ontern.g4project;

/**
 * Created by piyush on 8/8/17.
 */
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class profile_fragment extends AppCompatActivity {
   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_fragment, container, false);
        return rootView;
    }*/
   ImageView propic;
   TextView chng;
    ImageButton pro;
    //String[] FILE;
    String ImageDecode;
    private static final int LOAD_IMAGE=1;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       propic=(ImageView) findViewById(R.id.propic) ;
       chng=(TextView) findViewById(R.id.changepass);
       pro=(ImageButton) findViewById(R.id.pro);
       setContentView(R.layout.profile_fragment);
      Toolbar toolbar2=(Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar2);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      /*chng.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(profile_fragment.this, Profile_edit.class);
               startActivity(intent);
               //finish();
           }
       });*/
     /*pro.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent galleryIntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
               startActivityForResult(galleryIntent,LOAD_IMAGE);

           }
       });

   }

   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode ==LOAD_IMAGE && resultCode == RESULT_OK
                    && null != data) {


                Uri URI = data.getData();
                String[] FILE = { MediaStore.Images.Media.DATA };


                Cursor cursor = getContentResolver().query(URI,
                        FILE, null, null, null);

                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(FILE[0]);
                ImageDecode = cursor.getString(columnIndex);


                propic.setImageBitmap(BitmapFactory
                        .decodeFile(ImageDecode));
                cursor.close();

            }
        } catch (Exception e) {
            Toast.makeText(this, "Please try again", Toast.LENGTH_LONG)
                    .show();
        }*/
    }
}
