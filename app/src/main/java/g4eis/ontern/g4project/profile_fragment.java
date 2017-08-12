package g4eis.ontern.g4project;

/**
 * Created by piyush on 8/8/17.
 */
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class profile_fragment extends AppCompatActivity {
   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_fragment, container, false);
        return rootView;
    }*/
   TextView chng=(TextView) findViewById(R.id.changepass);
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.profile_fragment);
       chng.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(profile_fragment.this, Profile_edit.class);
               startActivity(intent);
               //finish();
           }
       });

   }


}
