package ar.edu.ort.bmon.rootsapp.ui.event;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;

import java.util.Calendar;
import java.util.Date;

import ar.edu.ort.bmon.rootsapp.R;
import ar.edu.ort.bmon.rootsapp.ui.plant.DatePickerFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CuttingOptions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CuttingOptions extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Switch usedHormones;
    private View viewReference;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CuttingOptions() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CuttingOptions.
     */
    // TODO: Rename and change types and number of parameters
    public static CuttingOptions newInstance(String param1, String param2) {
        CuttingOptions fragment = new CuttingOptions();
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
        viewReference = inflater.inflate(R.layout.fragment_cutting_options, container, false);
        usedHormones = viewReference.findViewById(R.id.switchHormones);
        return viewReference;
    }

    public Bundle getData() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("UsedHormones", usedHormones.isChecked());
        return bundle;
    }
}