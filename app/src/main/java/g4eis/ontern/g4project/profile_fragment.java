package g4eis.ontern.g4project;

/**
 * Created by piyush on 8/8/17.
 */
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.famoussoft.libs.JSON.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class profile_fragment extends AppCompatActivity {

    ImageView propic;
    TextView name,email;
    Button btnEdit;
    FloatingActionButton fab;
    //String[] FILE;
    // String ImageDecode;
    CollapsingToolbarLayout collapsing_toolbar;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    private final int PICK_IMAGE_REQUEST = 1;
    public final int EDIT_PROFILE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_fragment);

        name=(TextView) findViewById(R.id.nam);
        email=(TextView) findViewById(R.id.add);
        propic = (ImageView) findViewById(R.id.propic);
       // chng = (TextView) findViewById(R.id.changepass);
        btnEdit = (Button) findViewById(R.id.changepass);
        fab=(FloatingActionButton) findViewById(R.id.fab);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String tkn=sharedpreferences.getString("oauth","null");
        getDetails(tkn);
        String eml=sharedpreferences.getString("uid","User not Logged in");
        String nm=sharedpreferences.getString("usrName","Enter your Name");
        email.setText(eml);
        name.setText(nm);

        collapsing_toolbar=(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsing_toolbar.setTitle(nm);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(profile_fragment.this,Profile_edit.class),EDIT_PROFILE);
                Toast.makeText(profile_fragment.this,"Password should be at least 6 characters long",Toast.LENGTH_LONG).show();
            }
        });
         //makes the title bar transparent.
       if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        //for floating action button
       fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
// Show only images, no videos or anything else
              intent.setType("image/*");

                //intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
               startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
            /// Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            // photoPickerIntent.setType("image/*");
            //startActivityForResult(photoPickerIntent, SELECT_PHOTO);
        });

        sharedpreferences=getSharedPreferences("profilePicture",MODE_PRIVATE);

        if(!sharedpreferences.getString("dp","").equals("")){
            byte[] decodedString = Base64.decode(sharedpreferences.getString("dp", ""), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            propic.setImageBitmap(decodedByte);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case PICK_IMAGE_REQUEST:
                if (resultCode == RESULT_OK) {
                    try {
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);

                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                         propic.setImageBitmap(selectedImage);



                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                        byte[] b = baos.toByteArray();
                        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                        sharedpreferences.edit().putString("dp", encodedImage).commit();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void getDetails(final String token){

        RequestQueue queue = Volley.newRequestQueue(profile_fragment.this);  // this = context
        String url = "http://tcsapp.quicfind.com/users";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            String nm=jobj.getString("name");
                            String email=jobj.getString("email");
                            if(!email.equals(""))
                            {
                                //Do Nothing..... Signifies recieved Oauth correctly
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString("usrName",nm);
                                editor.commit();
                                name.setText(nm);
                                //Toast.makeText(profile_fragment.this, "print "+name, Toast.LENGTH_LONG).show();
                            }
                            else{
                                Intent chatIntent=new Intent(getApplicationContext(),Splashscreen.class);
                                startActivity(chatIntent);
                                finish();
                            }
                        }catch (Exception e){
                            System.out.println(e.getMessage().toString());
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Toast.makeText(Splashscreen.this, "oauth1 splash "+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                //params to login url
                Map<String, String>  params = new HashMap<String, String>();
                params.put("access_token",token );
                return params;
            }
        };
        queue.add(postRequest);
    }

}