package g4eis.ontern.g4project;



/**
 * Created by piyush on 23/7/17.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Splashscreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                Boolean data=sharedpreferences.getBoolean("login",false);
                if (data==true) {
                    startActivity(new Intent(Splashscreen.this, Main2Activity.class));
                    finish();
                }
                else {
                    Intent i = new Intent(Splashscreen.this, WelcomeActivity.class);
                    startActivity(i);


                    // close this activity
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }

}