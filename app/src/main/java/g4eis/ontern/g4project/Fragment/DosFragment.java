package g4eis.ontern.g4project.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import g4eis.ontern.g4project.R;



public class DosFragment extends Fragment {
    private RecyclerView mRecyclerView;
   // private RecyclerView.Adapter mAdapter;
   // private RecyclerView.LayoutManager mLayoutManager;


    public DosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dos, container, false);

    }


}