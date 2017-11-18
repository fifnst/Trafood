package id.trafood.trafood.Home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.trafood.trafood.LoginActivity;
import id.trafood.trafood.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Profil extends Fragment {

    Button login;
    public Fragment_Profil() {
        // Required empty public constructor.
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_profil, container, false);

        login = (Button) view.findViewById(R.id.btnLoginView);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        return view;
    }

}
