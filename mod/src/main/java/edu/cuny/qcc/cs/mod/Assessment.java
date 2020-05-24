package edu.cuny.qcc.cs.mod;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class Assessment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.assessment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Assessment", "here");
        view.findViewById(R.id.assessment_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Assessment","OK");
                NavHostFragment.findNavController(Assessment.this)
                        .navigate(R.id.action_Assessment_to_Question);
            }
        });
        view.findViewById(R.id.assessment_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Assessment","NO");
                NavHostFragment.findNavController(Assessment.this)
                        .navigate(R.id.action_Assessment_to_FirstFragment);
            }
        });
    }
}
