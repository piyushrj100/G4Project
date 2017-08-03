package g4eis.ontern.g4project;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Campus extends AppCompatActivity {


    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    private WebView webView;
    private ProgressBar progressBar;
    private ImageView imgHeader;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus);

        webView  = (WebView) findViewById(R.id.webView);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableData.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                /*Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();*/
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                /*Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();*/

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                webView = (WebView) findViewById(R.id.webView);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.canGoBack();
                if( expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals("Search all campuses!"))
                {
                    webView.loadUrl("http://www.google.com/search?q=TCS "+expandableListTitle.get(groupPosition)+" all campuses details");
                }
                else {
                    webView.loadUrl("http://www.google.com/search?q=TCS " + expandableListTitle.get(groupPosition) + " " +
                            expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition) + " campus details");
                }
                return false;
            }
        });

    }
    /*@Override
    public void onBackPressed(){
        Intent chatIntent=new Intent(getApplicationContext(),Campus.class);
        //chatIntent.putExtra("userid",email);
        startActivity(chatIntent);
        finish();

    }*/

}
