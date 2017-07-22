package g4eis.ontern.g4project;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class MainActivity extends AppCompatActivity {

    EditText etUId, etPwd;
    Button btnNewUsr, btnLogin, btnForgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUId = (EditText) findViewById(R.id.etid);
        etPwd = (EditText) findViewById(R.id.etPwd);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnNewUsr = (Button) findViewById(R.id.btnNewUsr);
        btnForgot = (Button) findViewById(R.id.btnForgot);


        btnNewUsr.setOnClickListener(new View.OnClickListener() {           //Button for New User Registration
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


        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
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
                builder.setMessage("Enter your registered e-mail id");
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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {        //Button for Login
                String uid=etUId.getText().toString();
                String pwd=etPwd.getText().toString();
                if(!pwd.equals("")){
                    loginUser(uid,pwd);
                }
                else{
                    Snackbar snackbar = Snackbar
                            .make(view, "Empty Password field, Please fill to continue...", Snackbar.LENGTH_LONG);

                    snackbar.show();

                    //Toast.makeText(MainActivity.this,"Empty Password field, Please fill to continue...",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void loginUser( final String email, final String password){         //method for login request to server and checking response

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);  // this = context
        String url = "http://api.mrasif.in/demo/gchat/login.php?";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            String rslt=jobj.getString("status").toString();
                            if(rslt.equals("true"))
                            {
                                Toast.makeText(MainActivity.this,"Login successful...",Toast.LENGTH_LONG).show();
                                //Intent chatIntent=new Intent(MainActivity.this,ChatActivity.class);
                                //chatIntent.putExtra("userid",email);
                                //startActivity(chatIntent);
                                //finish();
                            }
                            else{
                                Toast.makeText(MainActivity.this,"Wrong Credentials entered!! Try Again...",Toast.LENGTH_LONG).show();
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
                //params.put("userid",email );
                //params.put("pass",password )    ;
                return params;
            }
        };
        queue.add(postRequest);
    }


    private void getPwd( final String email){       //method for password request to server

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);  // this = context
        String url = "http://api.mrasif.in/demo/gchat/login.php?";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            String rslt=jobj.getString("status").toString();
                            if(rslt.equals("true"))
                            {
                                Toast.makeText(MainActivity.this,"Login successful...",Toast.LENGTH_LONG).show();
                                //Intent chatIntent=new Intent(MainActivity.this,ChatActivity.class);
                                //chatIntent.putExtra("userid",email);
                                //startActivity(chatIntent);
                                //finish();
                            }
                            else{
                                Toast.makeText(MainActivity.this,"Wrong Credentials entered!! Try Again...",Toast.LENGTH_LONG).show();
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
                //params.put("userid",email );
                //params.put("pass",password )    ;
                return params;
            }
        };
        queue.add(postRequest);
    }

}


