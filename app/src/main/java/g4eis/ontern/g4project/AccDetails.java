package g4eis.ontern.g4project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.famoussoft.libs.JSON.JSONArray;
import com.famoussoft.libs.JSON.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by peekay on 8/8/17.
 */

public class AccDetails extends AppCompatActivity {

    TextView acc_name,accHead,accOdc,accMgr,accDetail;

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    private String oauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acc_details);

        acc_name = (TextView) findViewById(R.id.acc_name);
        accHead = (TextView) findViewById(R.id.accHead);
        accOdc = (TextView) findViewById(R.id.accOdc);
        accMgr = (TextView) findViewById(R.id.accMgr);
        accDetail=(TextView) findViewById(R.id.user_profile_short_bio);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        oauth = sharedpreferences.getString("oauth","null");

        //Intent chatIntent=getIntent();
        String id=getIntent().getStringExtra("accid");

        getData(oauth,id);

    }

    private void getData(final String oaut, final String acc_id){

        RequestQueue queue = Volley.newRequestQueue(AccDetails.this);  // this = context
        String url = "http://tcsapp.quicfind.com/accounts/get";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONObject data=jobj.getJSONObject("data");
                            JSONArray jarray=new JSONArray(data.getJSONArray("contacts").toString());
                            if(!data.equals(""))
                            {
                                String accnm=data.getString("name").toString();
                                acc_name.setGravity(Gravity.CENTER);
                                acc_name.setText(accnm);
                                String desc=data.getString("descp").toString();
                                accDetail.setGravity(Gravity.CENTER);
                                accDetail.setText(Html.fromHtml("<big>"+desc+"</big>"));
                                JSONObject jobj1=new JSONObject(jarray.getJSONObject(0).toString());
                                String role = jobj1.getString("role").toString();
                                String name=jobj1.getString("name").toString();
                                String email=jobj1.getString("email").toString();
                                String desk=jobj1.getString("deskno").toString();
                                accOdc.setTextColor(Color.BLACK);
                                accOdc.setText(Html.fromHtml("<big>"+role+":</big> <br>Name : "+name+"<br>Email : "+email+
                                        "<br>Desk No : "+desk));

                                JSONObject jobj2=new JSONObject(jarray.getJSONObject(1).toString());
                                role = jobj2.getString("role").toString();
                                name=jobj2.getString("name").toString();
                                email=jobj2.getString("email").toString();
                                desk=jobj2.getString("deskno").toString();
                                accHead.setTextColor(Color.BLACK);
                                accHead.setText(Html.fromHtml("<big>"+role+":</big> <br>Name : "+name+"<br>Email : "+email+
                                        "<br>Desk No : "+desk));

                                //Toast.makeText(Splashscreen.this, "print"+oauth2, Toast.LENGTH_LONG).show();
                            }
                            else{

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

                        //Toast.makeText(AccDetails.this, "volley error: "+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                //params to login url
                Map<String, String>  params = new HashMap<String, String>();
                params.put("access_token",oaut );
                params.put("aid",acc_id);
                return params;
            }
        };
        queue.add(postRequest);
    }
}

