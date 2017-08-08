package g4eis.ontern.g4project;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Debayan on 30-07-2017.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    String FAQnames[]={"1",
                        "2",
                        "3",
                        "4",
                        "5",
                        "6",
                        "7",
                        "8",
                        "9",
                        "10",
                        "11",
                        "12",
                        "13",
                        "14",
                        "15"};//questions of the FAQs to be created

    String FAQdetails[][]={{"Inside FAQ1"},
                            {"Inside FAQ2"},
                            {"Inside FAQ3"},
                            {"Inside FAQ4"},
                            {"Inside FAQ5"},
                            {"Inside FAQ6"},
                            {"Inside FAQ7"},
                            {"Inside FAQ8"},
                            {"Inside FAQ9"},
                            {"Inside FAQ10"},
                            {"Inside FAQ11"},
                            {"Inside FAQ12"},
                            {"Inside FAQ13"},
                            {"Inside FAQ14"},
                            {"Inside FAQ15"}};//answers of FAQs to be created

    Context context;//creating an object of class Context

    public ExpandableListViewAdapter(Context context)//the constructor
    {
        this.context=context;//context of class holds the value of context used for calling constructor
    }

    @Override
    public int getGroupCount() {
        return FAQnames.length;//returns the number of views to be created
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return FAQdetails[groupPosition].length;//returns the number of details in a FAQname
    }

    @Override
    public Object getGroup(int groupPosition) {
        return FAQnames[groupPosition];//returns the name at the FAQnames
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return FAQdetails[groupPosition][childPosition];//returns content of that corresponding group and child positions
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;//returns position of that group
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;//returns position of that child
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {//for group or FAQnames
        TextView textview=new TextView(context);//creates new text view in this context
        textview.setText(FAQnames[groupPosition]);//sets text
        textview.setPadding(30,0,0,0);//sets padding
        textview.setBackgroundColor(Color.argb(255,0,0,200));//sets background color
        textview.setTextColor(Color.argb(255,255,255,255));//sets text color
        textview.setTextSize(35);//sets size
        return textview;//returns view
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {//for child or FAQdetails
        final TextView textview=new TextView(context);//creates new text view in this context
        textview.setText(FAQdetails[groupPosition][childPosition]);//sets text
        textview.setPadding(10,0,0,0);//sets padding
        textview.setBackgroundColor(Color.argb(255,100,100,250));//sets background color
        textview.setTextColor(Color.argb(255,0,0,0));//sets text color
        textview.setTextSize(25);//sets size
        return textview;//returns view
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}

