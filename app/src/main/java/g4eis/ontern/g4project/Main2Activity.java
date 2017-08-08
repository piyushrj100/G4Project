package g4eis.ontern.g4project;
//Written by Piyush Raja
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

import g4eis.ontern.g4project.R;
import g4eis.ontern.g4project.ViewPagerActivity.TabWithIconActivity;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    private String oauth2;
    public CardView card1;
    public CardView card2;
    public CardView card3;
    public CardView card4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String uname=sharedpreferences.getString("uid","null");
        String pass=sharedpreferences.getString("pwd","null");
        getOauth(uname,pass);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        CardView card1=(CardView) findViewById(R.id.cardView1);
        CardView card2=(CardView) findViewById(R.id.cardView2);
        CardView card3=(CardView) findViewById(R.id.cardView3);
        CardView card4=(CardView) findViewById(R.id.cardView4);


        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, Accounts.class);
                startActivity(intent);
                //finish();
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, ImportantContacts.class);
                startActivity(intent);

            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, TabWithIconActivity.class);
                startActivity(intent);

            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, Campus.class);
                startActivity(intent);

            }
        });
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.action_logout){
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.commit();
            Intent signInIntent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(signInIntent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the variation actions
        } else if (id == R.id.nav_Profile) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_faq) {

        } else if (id == R.id.nav_Feedback) {

        } else if (id == R.id.nav_about_us) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void getOauth(final String email, final String pwd){

        RequestQueue queue = Volley.newRequestQueue(Main2Activity.this);  // this = context
        String url = "http://tcsapp.quicfind.com/oauth/access_token";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            oauth2=jobj.getString("access_token");
                            if(!oauth2.equals(""))
                            {
                                //Do Nothing..... Signifies recieved Oauth correctly
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString("oauth",oauth2);
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

                        Toast.makeText(Main2Activity.this, "oauth"+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                //params to login url
                Map<String, String>  params = new HashMap<String, String>();
                params.put("password",pwd );
                params.put("grant_type","password");
                params.put("client_id","0");
                params.put("client_secret","public_secret");
                params.put("username",email);
                return params;
            }
        };
        queue.add(postRequest);
    }
}
