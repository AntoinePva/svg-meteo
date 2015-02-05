package izi.meteo.menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import izi.meteo.R;

/**
 * Created by Antoine on 03/02/2015.
 */
public class PageFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        create view to display weather
        View viewMeteo = inflater.inflate(R.layout.fragment_meteo, container, false);
        View viewSettings = inflater.inflate(R.layout.fragment_favoris, container, false);
        switch (mPage) {
            case 1:

                return viewMeteo;
            case 2:
                return viewSettings;
            default:

                return viewMeteo;
        }
    }


}

