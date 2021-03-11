package cnmi.it.asthmaprototype;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class DashbordFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    BottomNavigationView bottomNavigationView;
    ImageView profilepic;
    ImageView dashboardPic;
    ProgressBar progressBar;
    ImageView SOS;
    TextView name;
    Uri gPhoto;
    Callback callBack;

    public DashbordFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
//    public static DashbordFragment newInstance(String param1, String param2) {
//        DashbordFragment fragment = new DashbordFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }


    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            callBack = (Callback) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString()+ " must implement Callback");
        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        profilepic = container.findViewById(R.id.profilepic);
//        profilepic.setImageURI();
        name = container.findViewById(R.id.user_name);
        //name.setText(nname);
        name.setText("TESTNAME");
        dashboardPic = container.findViewById(R.id.dashboardimage);
        progressBar = container.findViewById(R.id.dashboardProgressBar);
        SOS = container.findViewById(R.id.sosbtn);
        View view = inflater.inflate(R.layout.fragment_dashbord, container, false);

        return view;
    }
}