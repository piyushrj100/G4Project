package g4eis.ontern.g4project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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

import static g4eis.ontern.g4project.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {

    EditText etUId, etPwd;
    Button btnNewUsr, btnLogin, btnForgot;
    SharedPreferences sharedpreferences;
    private static final String MyPREFERENCES = "MyPrefs";
    private String oauth1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        etUId = (EditText) findViewById(R.id.etid);
        etPwd = (EditText) findViewById(R.id.etPwd);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnNewUsr = (Button) findViewById(R.id.btnNewUsr);
        btnForgot = (Button) findViewById(R.id.btnForgot);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        getOauth();
        //Toast.makeText(MainActivity.this,"Oauth1: "+oauth1,Toast.LENGTH_SHORT).show();

        //For New User Registration
        btnNewUsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //Dialog Box for registration
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this,R.style.InvitationDialog);
                final EditText input = new EditText(MainActivity.this);      //Text Box for email input
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                builder.setView(input);
                builder.setTitle("New User Registration");
                builder.setCancelable(false);
                builder.setMessage("Enter Your e-mail to recieve password");
                builder.setPositiveButton("Send Password", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if(!input.getText().toString().equals("")) {        //Checking for empty Input
                                    getPwd(input.getText().toString());  //method call for making password request
                                }
                                else{
                                    Snackbar snackbar = Snackbar.make(view, "Empty e-mail field, Please fill and try again...",
                                            Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                }
                            }
                        }
                );
                builder.setNegativeButton("Cancel", null);
                builder.show();

            }
        });

        // method for Forgot password
        btnForgot.setOnClickListener(new View.OnClickListener() {       //Method for forgot password
            @Override
            public void onClick(final View view) {
                //Dialog box to get email from user and send him his old password
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.InvitationDialog);
                final EditText input = new EditText(MainActivity.this);      //Text Box for email input
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                lp.setMargins(5,5,5,5);
                input.setLayoutParams(lp);
                builder.setView(input);
                builder.setTitle("Recover Your Password");
                builder.setCancelable(false);
                Snackbar.make(view, "Forgot Password will be available soon...",
                        Snackbar.LENGTH_LONG).show();
                builder.setMessage("Enter your registered e-mail id");
                builder.setPositiveButton("Send Password", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if(!input.getText().toString().equals("")) {        //Checking for empty Input
                                    //getPwd(input.getText().toString());  //method call for making password request
                                }
                                else{
                                    Snackbar snackbar = Snackbar.make(view, "Empty e-mail field, Please fill and try again...",
                                            Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                }
                            }
                        }
                );
                builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {        //for Login
                String uid=etUId.getText().toString();
                String pwd=etPwd.getText().toString();
                if(!pwd.equals("")&&!uid.equals("")){
                    loginUser(uid,pwd);
                }
                else{
                    Snackbar snackbar = Snackbar
                            .make(view, "Empty Email/Password field, Please fill to continue...", Snackbar.LENGTH_LONG);

                    snackbar.show();
                }
            }
        });
    }

    //method for login request to server and checking server response
    private void loginUser( final String email, final String password){

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);  // this = context
        String url = "http://tcsapp.quicfind.com/oauth/access_token";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            if(jobj.length()==0)
                            {
                                Snackbar.make(findViewById(R.id.mainLayout), "Empty JSON...", Snackbar.LENGTH_LONG).show();
                                return;
                            }
                            String rslt=jobj.getString("access_token").toString();
                            String token=jobj.getString("token_type").toString();
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            if(!rslt.equals(null)&&token.equals("Bearer"))
                            {
                                editor.putString("uid",email);
                                editor.putString("pwd",password);
                                editor.putString("token",rslt);
                                editor.putBoolean("login",true);
                                editor.commit();
                                Toast.makeText(MainActivity.this, "Login Successful...", Toast.LENGTH_SHORT).show();
                                //Snackbar.make(findViewById(R.id.dashLayout), "Login Successful...", Snackbar.LENGTH_LONG).show();
                                Intent chatIntent=new Intent(MainActivity.this,Main2Activity.class);
                                //chatIntent.putExtra("userid",email);
                                startActivity(chatIntent);
                                finish();
                            }
                            else {
                                Snackbar.make(findViewById(R.id.mainLayout), "Wrong Credentials entered!! Try Again...", Snackbar.LENGTH_LONG).show();
                            }
                        }catch (Exception e){
                            System.out.println(e.getMessage().toString());
                            //Toast.makeText(MainActivity.this,e.getMessage().toString(),Toast.LENGTH_LONG);
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Snackbar.make(findViewById(R.id.mainLayout), "Wrong Credentials entered or Server too Busy!! Try Again Later...", Snackbar.LENGTH_LONG).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                //params to login url
                Map<String, String>  params = new HashMap<String, String>();
                params.put("password",password );
                params.put("grant_type","password");
                params.put("client_id","0");
                params.put("client_secret","public_secret");
                params.put("username",email);
                return params;
            }
        };
        queue.add(postRequest);
    }

    //method for password request to server for 1st time users
    private void getPwd( final String email){
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);  // this = context
        String url = "http://tcsapp.quicfind.com/users/new";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject jobj = new JSONObject(response);  //Response string to JSON
                            String rslt=jobj.getString("data").toString(); //extract response value from JSON
                            if(!rslt.equals(""))
                            {
                                Snackbar.make(findViewById(R.id.mainLayout), "Registration successful!!!Please login with password \"abcd1234\"", Snackbar.LENGTH_INDEFINITE).show();
                            }
                            else{
                                Snackbar.make(findViewById(R.id.mainLayout), "Failed to Register!! Please Try Again...", Snackbar.LENGTH_LONG).show();
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
                    }
                }
        ) {
            @Override
            //params to for the url for post request
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email",email );
                params.put("password","abcd1234");
                params.put("access_token",oauth1);
                return params;
            }
        };
        queue.add(postRequest);
    }


    private void getOauth(){

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);  // this = context
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
                            }
                            else{
                                Intent chatIntent=new Intent(getApplicationContext(),MainActivity.class);
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
}
