package edu.umbc.coffee_art_final_exam;
/*CORY RAFKIN | IS413 | COFFEE ART PROJ FINAL EXAM*/
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements RecycleViewAdapter.MyClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    /*-----------------------------------------------------------------------
    * -----------------------------------------------------------------------*/
    //empty array that will hold artists from strings → string-array
    String[] artists;
//    String[] artists = {"artist"};
    //array containing references to images in drawable directory
    int[] images = {
        R.drawable.coffee_1_icon,
        R.drawable.coffee_2_icon,
        R.drawable.coffee_3_icon,
        R.drawable.coffee_4_icon,
        R.drawable.coffee_5_icon,
        R.drawable.coffee_6_icon
    };
    //container for displaying ViewItems, recycling views
    RecyclerView recyclerView;
    //override onViewCreated when using fragments
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        //pass artists string-array to artists[]
        artists = this.getResources().getStringArray(R.array.artists);
        //instantiate RecyclerView Adapter class
        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(this.getContext(), artists,
                images, this);
        //set the adapter for the RecyclerView to the RecyclerViewAdapter
        recyclerView.setAdapter(recycleViewAdapter);
        //set the type of layout using the LayoutManager (grid layout provided by professor)
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2,
                LinearLayoutManager.VERTICAL, true));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }
    /*--------------------------------------------------------------------------
    * -----------------------------------------------------------------------------*/
    //implements onClickItem(int position) abstract method from interface
    @Override
    public void onClickItem(int position) {
        //navigate to fragments using the fragment position (int)
        //RecyclerView stacking fragments, lowest position at bottom
        switch (position) {
            case 0:
                Navigation.findNavController(this.getView()).navigate(
                        R.id.action_homeFragment_to_coffeeFragmentOne);
                break;
            case 1:
                Navigation.findNavController(this.getView()).navigate(
                        R.id.action_homeFragment_to_coffeeFragmentTwo);
                break;
            case 2:
                Navigation.findNavController(this.getView()).navigate(
                        R.id.action_homeFragment_to_coffeeFragmentThree);
                break;
            case 3:
                Navigation.findNavController(this.getView()).navigate(
                        R.id.action_homeFragment_to_coffeeFragmentFour);
                break;
            case 4:
                Navigation.findNavController(this.getView()).navigate(
                        R.id.action_homeFragment_to_coffeeFragmentFive);
                break;
            case 5:
                Navigation.findNavController(this.getView()).navigate(
                        R.id.action_homeFragment_to_coffeeFragmentSix);
                break;
        }
    }
}