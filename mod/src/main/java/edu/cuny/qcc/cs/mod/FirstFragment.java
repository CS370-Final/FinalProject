package edu.cuny.qcc.cs.mod;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment {
    private final String TAG = "RecursionApp";
    ToggleButton rank1button;
    ToggleButton rank2button;
    ToggleButton rank3button;
    Button start;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rank1button = (ToggleButton) view.findViewById(R.id.rank1button);
        rank2button = (ToggleButton) view.findViewById(R.id.rank2button);
        rank3button = (ToggleButton) view.findViewById(R.id.rank3button);
        start = (Button) view.findViewById(R.id.start);


        view.findViewById(R.id.rank1button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                printToggleButtonStates();
                if(!rank1button.isChecked() && !rank2button.isChecked() && !rank3button.isChecked()) {
                    start.setEnabled(false);
                }
                else {
                    start.setEnabled(true);
                }
            }
        });
        view.findViewById(R.id.rank2button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                printToggleButtonStates();
                if(!rank1button.isChecked() && !rank2button.isChecked() && !rank3button.isChecked()) {
                    start.setEnabled(false);
                }
                else {
                    start.setEnabled(true);
                }
            }
        });

        view.findViewById(R.id.rank3button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                printToggleButtonStates();
                if(!rank1button.isChecked() && !rank2button.isChecked() && !rank3button.isChecked()) {
                    start.setEnabled(false);
                }
                else {
                    start.setEnabled(true);
                }
            }
        });

        view.findViewById(R.id.statistics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        view.findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               //do soemthing
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_Question);
                printToggleButtonStates();
            }
        });
    }
    public void printToggleButtonStates() {
        if(!rank1button.isChecked() && !rank2button.isChecked() && !rank3button.isChecked()) {
            Log.d(TAG,"r1: All difficulties not true");
        }
        else {
            Log.d(TAG, "Some or all are true.");
        }
        Log.d(TAG, "rank1button: " + rank1button.isChecked());
        Log.d(TAG, "rank2button: " + rank2button.isChecked());
        Log.d(TAG, "rank3button: " + rank3button.isChecked());
    }
}
