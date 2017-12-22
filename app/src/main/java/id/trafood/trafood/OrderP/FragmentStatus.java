package id.trafood.trafood.OrderP;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.trafood.trafood.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentStatus extends Fragment {


    public FragmentStatus() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment_status, container, false);

        return view;
    }

}
