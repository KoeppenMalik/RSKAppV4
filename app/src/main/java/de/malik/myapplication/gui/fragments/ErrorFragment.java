// Created on 26.02.2021, 14:58

package de.malik.myapplication.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import de.malik.myapplication.R;
import de.malik.myapplication.listeners.onclick.OnClickListenerSwitchFragment;
import de.malik.myapplication.util.RSKSystem;

public class ErrorFragment extends Fragment {

    private String message;
    private RSKSystem system;
    private Button buttonBack;
    private TextView textViewErrorMessage;
    private View v;

    public ErrorFragment(String message, RSKSystem system) {
        this.message = message;
        this.system = system;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.error_fragment, container, false);
        handleGui();
        return v;
    }

    private void handleGui() {
        createComponents();
        textViewErrorMessage.setText(message);
        setListeners();
    }

    private void createComponents() {
        this.textViewErrorMessage = v.findViewById(R.id.textViewErrorMessage);
        this.buttonBack = v.findViewById(R.id.buttonBack);
    }

    private void setListeners() {
        buttonBack.setOnClickListener(new OnClickListenerSwitchFragment(new OverviewFragment(system), system, android.R.anim.fade_in));
    }
}
