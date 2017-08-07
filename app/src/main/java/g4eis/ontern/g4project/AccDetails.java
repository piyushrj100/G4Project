package g4eis.ontern.g4project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
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

/**
 * Created by peekay on 8/8/17.
 */

public class AccDetails extends AppCompatActivity {

    TextView acc_name,accHead,accOdc,accMgr;

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    private String oauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acc_details);

        acc_name = (TextView) findViewById(R.id.acc_name);
        accHead = (TextView) findViewById(R.id.accHead);
        acc_name = (TextView) findViewById(R.id.acc_name);
        acc_name = (TextView) findViewById(R.id.acc_name);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        oauth = sharedpreferences.getString("oauth","null");

        Intent chatIntent=getIntent();
        String id=chatIntent.getStringExtra("id");

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
                            if(!data.equals(""))
                            {
                                acc_name.setText(data.getString("name"));
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

                        Toast.makeText(AccDetails.this, "volley error: "+error.getMessage(), Toast.LENGTH_LONG).show();
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

