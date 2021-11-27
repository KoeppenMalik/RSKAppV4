// Created on 16.02.2021, 18:37

package de.malik.myapplication.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.MainActivity;
import de.malik.myapplication.listeners.onclick.ListenerSwitchFragment;
import de.malik.myapplication.util.RSKSystem;

public class InfoFragment extends Fragment {

    private View v;
    private RSKSystem system;
    private Button buttonBack;
    private TextView textViewVersion;

    public InfoFragment(RSKSystem system) {
        this.system = system;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.info_fragment, container, false);
        handleGui();
        return v;
    }

    private void handleGui() {
        createComponents();
        textViewVersion.setText(textViewVersion.getText() + MainActivity.APP_VERSION);
        setListeners();
    }

    private void createComponents() {
        textViewVersion = v.findViewById(R.id.textViewVersion);
        buttonBack = v.findViewById(R.id.buttonBack);
    }

    private void setListeners() {
        buttonBack.setOnClickListener(new ListenerSwitchFragment(new OverviewFragment(system), system, R.anim.slide_up));
    }
}
