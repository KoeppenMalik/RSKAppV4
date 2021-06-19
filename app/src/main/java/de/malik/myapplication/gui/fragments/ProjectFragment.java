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
import de.malik.myapplication.R;
import de.malik.myapplication.listeners.onclick.projectfragment.OnClickListenerButtonAddPause;
import de.malik.myapplication.listeners.onclick.projectfragment.OnClickListenerButtonSetStartTime;
import de.malik.myapplication.listeners.onclick.projectfragment.OnClickListenerButtonSetStopTime;
import de.malik.myapplication.listeners.onclick.projectfragment.OnClickListenerImageButtonEditCustomerData;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Time;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.recyclerviews.pauses.ItemTouchHelperRecyclerViewPauses;
import de.malik.myapplication.util.recyclerviews.pauses.RecyclerAdapterPauses;

import static de.malik.myapplication.util.RSKSystem.TimeManager.*;

public class ProjectFragment extends Fragment {

    private RSKSystem system;
    private View v;
    private Project project;
    private RecyclerAdapterPauses recyclerAdapterPauses;
    private TextView textViewCurrentName, textViewDate, textViewWorkDescription;
    private ImageButton imageButtonEditData;
    private RecyclerView recyclerView;
    private Button buttonSetStartTime, buttonSetStopTime, buttonAddPause;
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
        Time startTime = project.getStartTime();
        Time stopTime = project.getStopTime();
        Time diff = getDiffInMinutes(startTime, stopTime, project.getPauses());

        createComponents();
        textViewCurrentName.setText(project.getName());
        recyclerView.setAdapter(recyclerAdapterPauses);
        recyclerView.addItemDecoration(new DividerItemDecoration(system.getContext(), DividerItemDecoration.VERTICAL));
        textViewDate.setText(project.getDate());
        editTextStartTime.setText(startTime.asString("Uhr"));
        editTextStopTime.setText(stopTime.asString("Uhr"));
        editTextTotalTime.setText(diff.asDiffString());
        textViewWorkDescription.setText(project.getWorkDescription());
        setListeners();
    }

    private void createComponents() {
        textViewWorkDescription = v.findViewById(R.id.textViewWorkDescription);
        textViewCurrentName = v.findViewById(R.id.textViewCurrentName);
        textViewDate = v.findViewById(R.id.textViewDate);
        imageButtonEditData = v.findViewById(R.id.imageButtonEditData);
        recyclerView = v.findViewById(R.id.recyclerView);
        buttonSetStartTime = v.findViewById(R.id.buttonSetStartTime);
        buttonSetStopTime = v.findViewById(R.id.buttonSetStopTime);
        buttonAddPause = v.findViewById(R.id.buttonAddPause);
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
        buttonAddPause.setOnClickListener(new OnClickListenerButtonAddPause(system, project));
        imageButtonEditData.setOnClickListener(new OnClickListenerImageButtonEditCustomerData(system, project));
    }

    public void notifyCustomerTimeChange() {
        Time diff = getDiffInMinutes(project.getStartTime(), project.getStopTime(), project.getPauses());
        editTextTotalTime.setText(diff.asDiffString());
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
