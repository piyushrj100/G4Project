package g4eis.ontern.g4project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    Button btnPwd, btnLogin;
    private PreferenceManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUId = (EditText) findViewById(R.id.etid);
        etPwd = (EditText) findViewById(R.id.etPwd);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnPwd = (Button) findViewById(R.id.btnPwd);

        prefManager = new PreferenceManager(this);
        if (!prefManager.isFirstTimeLaunch()) {

            btnLogin.setEnabled(false);
        }

        btnPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid=etUId.getText().toString();
                if(!uid.equals("")) {
                    getPwd(uid);
                    if (!prefManager.isFirstTimeLaunch()) {
                        etUId.setEnabled(false);
                        btnLogin.setEnabled(true);
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Empty e-mail field, Please fill to continue...",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid=etUId.getText().toString();
                String pwd=etPwd.getText().toString();
                if(!pwd.equals("")){
                    loginUser(uid,pwd);
                }
                else{
                    Toast.makeText(MainActivity.this,"Empty Password field, Please fill to continue...",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void loginUser( final String email, final String password){

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


    private void getPwd( final String email){

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


