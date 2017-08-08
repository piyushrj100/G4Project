package g4eis.ontern.g4project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
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
import java.util.Map;

public class Accounts extends AppCompatActivity {

    protected String oauth2;
    private int total=0;
    public int id;
    JSONArray message=null;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        oauth2=sharedpreferences.getString("oauth","null");
        //Toast.makeText(Accounts.this, oauth2, Toast.LENGTH_LONG).show();
        downloadData();
    }

    private void downloadData(){
        RequestQueue queue2 = Volley.newRequestQueue(Accounts.this);  // this = context
        String url = "http://tcsapp.quicfind.com/accounts";
        //Toast.makeText(Accounts.this, "oauth2"+oauth2, Toast.LENGTH_LONG).show();
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            loadList(jobj);
                        }catch (Exception e){
                            System.out.println(e.getMessage().toString());
                            Toast.makeText(Accounts.this, "catch "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Accounts.this, "volley error"+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("access_token",oauth2 );
                params.put("query","");
                return params;
            }
        };
        queue2.add(postRequest);
    }

    private void loadList(JSONObject data) {
        // TODO Auto-generated method stub
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.accounts_form);
        mainLayout.removeAllViews();
        //int total=Integer.parseInt(data.getString("total").toString());
        //this.total=total;
        JSONArray jarray=new JSONArray(data.getJSONArray("data").toString());
        message=jarray;
        for (int i=0; i<5; i++) {
            JSONObject jobj=new JSONObject(jarray.getJSONObject(i).toString());
            String name = jobj.getString("name").toString();
            id = jobj.getInt("id");

            LinearLayout ll = new LinearLayout(this);
            ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.setPadding(10, 10, 10, 10);

            final LinearLayout sll = new LinearLayout(this);
            sll.setId(id);
            sll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            sll.setPadding(15, 15, 5, 15);
            sll.setOrientation(LinearLayout.VERTICAL);
            //sll.setBackgroundColor(Color.WHITE);

            TextView tvTitle = new TextView(this);
            tvTitle.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            tvTitle.setText(Html.fromHtml("<big><b><h3><i>"+"</i></b>"+""+name+"</h3></big>"));
            tvTitle.setTextColor(Color.BLACK);
            tvTitle.setClickable(true);
            tvTitle.setGravity(Gravity.CENTER);
            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(Accounts.this,"Clicked "+sll.getId(),Toast.LENGTH_LONG).show();
                    accountDetails(sll.getId());
                }
            });

            sll.addView(tvTitle);

            ll.addView(sll);
            mainLayout.addView(ll);
        }
    }

    private void accountDetails(final int id){
        Intent chatIntent=new Intent(Accounts.this,AccDetails.class);
        String id1=Integer.toString(id);
        chatIntent.putExtra("accid",id1);
        startActivity(chatIntent);
    }
}








