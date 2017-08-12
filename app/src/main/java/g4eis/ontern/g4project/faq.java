package g4eis.ontern.g4project;

import android.app.ActionBar;
import android.app.ExpandableListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class faq extends AppCompatActivity {

    ExpandableListView expandableListView;//Creating an object of ExpandibleListView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        expandableListView=(ExpandableListView) findViewById(R.id.expandableListView);//Linking the object with that in layout

        ExpandableListViewAdapter adapter=new ExpandableListViewAdapter(faq.this);//creates an adapter

        expandableListView.setAdapter(adapter);//sets the adapter created

    }
}