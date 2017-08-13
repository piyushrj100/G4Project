package g4eis.ontern.g4project;



/**
 * Created by piyush on 23/7/17.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.famoussoft.libs.JSON.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Splashscreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    private String oauth2,oauth1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        getOauth1();
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
                    String email=sharedpreferences.getString("uid",null);
                    String pwd=sharedpreferences.getString("pwd",null);
                    getOauth(email,pwd);
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



    private void getOauth1(){

        RequestQueue queue = Volley.newRequestQueue(Splashscreen.this);  // this = context
        String url = "http://tcsapp.quicfind.com/oauth/access_token";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            oauth1=jobj.getString("access_token");
                            if(!oauth1.equals(""))
                            {
                                //Do Nothing..... Signifies recieved Oauth correctly
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString("oauth_login",oauth1);
                                editor.commit();
                                //Toast.makeText(Splashscreen.this, "print"+oauth2, Toast.LENGTH_LONG).show();
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

                        Toast.makeText(Splashscreen.this, "oauth1 "+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                //params to login url
                Map<String, String>  params = new HashMap<String, String>();
                params.put("password","password" );
                params.put("grant_type","password");
                params.put("client_id","0");
                params.put("client_secret","public_secret");
                params.put("username","public@tcs.com");
                return params;
            }
        };
        queue.add(postRequest);
    }

    private void getOauth(final String em, final String pw){

        RequestQueue queue = Volley.newRequestQueue(Splashscreen.this);  // this = context
        String url = "http://tcsapp.quicfind.com/oauth/access_token";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            oauth1=jobj.getString("access_token");
                            if(!oauth1.equals(""))
                            {
                                //Do Nothing..... Signifies recieved Oauth correctly
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString("oauth",oauth1);
                                editor.commit();
                                //Toast.makeText(Splashscreen.this, "print"+oauth2, Toast.LENGTH_LONG).show();
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

                        Toast.makeText(Splashscreen.this, "oauth1 "+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                //params to login url
                Map<String, String>  params = new HashMap<String, String>();
                params.put("password",pw );
                params.put("grant_type","password");
                params.put("client_id","0");
                params.put("client_secret","public_secret");
                params.put("username",em);
                return params;
            }
        };
        queue.add(postRequest);
    }
}