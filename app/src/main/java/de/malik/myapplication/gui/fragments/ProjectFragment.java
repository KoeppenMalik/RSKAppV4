// Created on 25.01.2021, 15:32

package de.malik.myapplication.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

import de.malik.myapplication.R;
import de.malik.myapplication.listeners.onclick.projectfragment.OnClickListenerButtonAddPause;
import de.malik.myapplication.listeners.onclick.projectfragment.OnClickListenerButtonSetStartTime;
import de.malik.myapplication.listeners.onclick.projectfragment.OnClickListenerButtonSetStopTime;
import de.malik.myapplication.listeners.onclick.projectfragment.OnClickListenerImageButtonEditCustomerData;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Pause;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.recyclerviews.pauses.ItemTouchHelperRecyclerViewPauses;
import de.malik.myapplication.util.recyclerviews.pauses.RecyclerAdapterPauses;
import de.malik.mylibrary.managers.TimeManager;

public class ProjectFragment extends Fragment {

    private RSKSystem system;
    private View v;
    private Project project;
    private RecyclerAdapterPauses recyclerAdapterPauses;
    private TextView textViewCurrentName, textViewDate, textViewWorkDescription;
    private ImageButton imageButtonEditData, imageButtonAddPause;
    private RecyclerView recyclerView;
    private Button buttonSetStartTime, buttonSetStopTime;
    private EditText editTextStartTime, editTextStopTime, editTextTotalTime;

    public ProjectFragment(RSKSystem system, Project project) {
        this.system = system;
        this.project = project;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.project_fragment, container, false);
        handleGui();
        return v;
    }

    private void handleGui() {
        Date startTime = project.getStartTime();
        Date stopTime = project.getStopTime();
        long diff = TimeManager.diff(stopTime, startTime);
        for (Pause pause : project.getPauses()) {
            diff -= pause.getTime().getTime();
        }
        Date timeDiff = new Date(diff);

        createComponents();
        textViewCurrentName.setText(project.getName());
        recyclerView.setAdapter(recyclerAdapterPauses);
        recyclerView.addItemDecoration(new DividerItemDecoration(system.getContext(), DividerItemDecoration.VERTICAL));
        textViewDate.setText(project.getDate());
        editTextStartTime.setText(TimeManager.formatTimeString(TimeManager.toTimeString(startTime, false)));
        editTextStopTime.setText(TimeManager.formatTimeString(TimeManager.toTimeString(stopTime, false)));
        editTextTotalTime.setText(TimeManager.toTimeString(timeDiff, false));
        textViewWorkDescription.setText(project.getWorkDescription());
        setListeners();
    }

    private void createComponents() {
        textViewWorkDescription = v.findViewById(R.id.textViewWorkDescription);
        textViewCurrentName = v.findViewById(R.id.textViewCurrentName);
        textViewDate = v.findViewById(R.id.textViewDate);
        imageButtonEditData = v.findViewById(R.id.imageButtonEditData);
        imageButtonAddPause = v.findViewById(R.id.imageButtonAddPause);
        recyclerView = v.findViewById(R.id.recyclerView);
        buttonSetStartTime = v.findViewById(R.id.buttonSetStartTime);
        buttonSetStopTime = v.findViewById(R.id.buttonSetStopTime);
        editTextStartTime = v.findViewById(R.id.editTextStartTime);
        editTextStopTime = v.findViewById(R.id.editTextStopTime);
        editTextTotalTime = v.findViewById(R.id.editTextTotalTime);
        recyclerAdapterPauses = new RecyclerAdapterPauses(project.getPauses());
    }

    private void setListeners() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperRecyclerViewPauses(0, ItemTouchHelper.LEFT, project, this, system));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        buttonSetStartTime.setOnClickListener(new OnClickListenerButtonSetStartTime(system, project, this));
        buttonSetStopTime.setOnClickListener(new OnClickListenerButtonSetStopTime(system, project, this));
        imageButtonAddPause.setOnClickListener(new OnClickListenerButtonAddPause(system, project));
        imageButtonEditData.setOnClickListener(new OnClickListenerImageButtonEditCustomerData(system, project));
    }

    public void notifyCustomerTimeChange() {
        long diff = TimeManager.diff(project.getStopTime(), project.getStartTime());
        for (Pause pause : project.getPauses()) {
            diff -= pause.getTime().getTime();
        }
        editTextTotalTime.setText(TimeManager.toTimeString(new Date(diff), false));
    }

    public RecyclerAdapterPauses getRecyclerAdapterPauses() {
        return recyclerAdapterPauses;
    }

    public EditText getEditTextStartTime() {
        return editTextStartTime;
    }

    public EditText getEditTextStopTime() {
        return editTextStopTime;
    }

    public EditText getEditTextTotalTime() {
        return editTextTotalTime;
    }
}
