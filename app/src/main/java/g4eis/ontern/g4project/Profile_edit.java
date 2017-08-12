package g4eis.ontern.g4project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
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

public class Profile_edit extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    private static final String MyPREFERENCES = "MyPrefs";
    EditText curpwd,nwpwd,newpass;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        curpwd=(EditText) findViewById(R.id.curpwd);
        nwpwd=(EditText) findViewById(R.id.newpwd);
        newpass=(EditText) findViewById(R.id.newpass);
        update=(Button) findViewById(R.id.btnUpdate);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String pass=sharedpreferences.getString("pwd","User not Logged in !!!!");
        final String token=sharedpreferences.getString("oauth","null");
        curpwd.setText(pass);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwd1=nwpwd.getText().toString();
                String pwd2=newpass.getText().toString();
                if(pwd1.equals(pwd2))
                {
                    changeData(token,pwd1);
                }
                else
                {
                    Snackbar.make(findViewById(R.id.editProfile), "Please enter same password in both places!!", Snackbar.LENGTH_LONG).show();
                }
            }
        });


    }
    private void changeData(final String token, final String pwd){
        RequestQueue queue = Volley.newRequestQueue(Profile_edit.this);  // this = context
        String url = "http://tcsapp.quicfind.com/users/update";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            String rslt=jobj.getString("data").toString();
                            if(!rslt.equals(""))
                            {

                                Toast.makeText(Profile_edit.this, "Update Successful...", Toast.LENGTH_SHORT).show();
                                //Snackbar.make(findViewById(R.id.dashLayout), "Login Successful...", Snackbar.LENGTH_LONG).show();
                                //Intent chatIntent=new Intent(Profile_edit.this,Main2Activity.class);
                                //chatIntent.putExtra("userid",email);
                                //startActivity(chatIntent);
                                //finish();
                            }
                            else {
                                Snackbar.make(findViewById(R.id.editProfile), "Unable to update!! Try Again later...", Snackbar.LENGTH_LONG).show();
                            }
                        }catch (Exception e){
                            System.out.println(e.getMessage().toString());
                            //Toast.makeText(MainActivity.this,e.getMessage().toString(),Toast.LENGTH_LONG);
                            //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Snackbar.make(findViewById(R.id.mainLayout), "Wrong Credentials entered or Server too Busy!! Try Again Later...", Snackbar.LENGTH_LONG).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                //params to login url
                Map<String, String>  params = new HashMap<String, String>();
                params.put("access_token",token );
                params.put("password",pwd);
                return params;
            }
        };
        queue.add(postRequest);
    }
}

