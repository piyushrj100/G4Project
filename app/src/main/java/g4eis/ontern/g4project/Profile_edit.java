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
    EditText curpwd,nwpwd,newpass,curname;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        //curpwd=(EditText) findViewById(R.id.curpwd);
        nwpwd=(EditText) findViewById(R.id.newpwd);
        newpass=(EditText) findViewById(R.id.newpass);
        update=(Button) findViewById(R.id.btnUpdate);
        curname=(EditText) findViewById(R.id.curname);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String pass=sharedpreferences.getString("pwd","User not Logged in !!!!");
        String name=sharedpreferences.getString("usrName","Enter your name here");
        final String token=sharedpreferences.getString("oauth","null");
        nwpwd.setText(pass);
        newpass.setText(pass);
        curname.setText(name);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwd1=nwpwd.getText().toString();
                String pwd2=newpass.getText().toString();
                String nm=curname.getText().toString();
                if(pwd1.equals(pwd2))
                {
                    changeData(token,pwd1,nm);
                }
                else
                {
                    Snackbar.make(findViewById(R.id.editProfile), "Please enter same password in both places!!", Snackbar.LENGTH_LONG).show();
                }
            }
        });//For storing the value of names,address and account of the user.

    }
    private void changeData(final String token, final String pwd,final String name){
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
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString("pwd",pwd);
                                editor.putString("usrName",name);
                                editor.commit();
                                Intent chatIntent=new Intent(Profile_edit.this,profile_fragment.class);
                                startActivity(chatIntent);
                                finish();
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

                        Snackbar.make(findViewById(R.id.mainLayout), error.getMessage(), Snackbar.LENGTH_LONG).show();

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
                params.put("name",name);
                return params;
            }
        };
        queue.add(postRequest);
    }
}

