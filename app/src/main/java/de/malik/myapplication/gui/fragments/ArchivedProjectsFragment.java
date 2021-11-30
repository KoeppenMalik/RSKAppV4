// Created on 28.02.2021, 15:09

package de.malik.myapplication.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.malik.myapplication.R;
import de.malik.myapplication.listeners.onclick.OnClickListenerSwitchFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.recyclerviews.projects.recyclerviewarchivedprojects.ItemTouchHelperRecyclerViewArchivedProjects;
import de.malik.myapplication.util.recyclerviews.projects.RecyclerAdapterProjects;

public class ArchivedProjectsFragment extends Fragment {

    private RSKSystem system;
    private View v;
    private Button buttonBack;
    private RecyclerView recyclerView;
    private RecyclerAdapterProjects recyclerAdapter;

    public ArchivedProjectsFragment(RSKSystem system) {
        this.system = system;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.archived_customers_fragment, container, false);
        handleGui();
        return v;
    }

    private void handleGui() {
        createComponents();
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(system.getContext(), DividerItemDecoration.VERTICAL));
        setListeners();
    }

    private void createComponents() {
        recyclerAdapter = new RecyclerAdapterProjects(system.getProjectManager().getArchivedProjects(), system);
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(system.getContext()));
        buttonBack = v.findViewById(R.id.buttonBack);
    }

    private void setListeners() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperRecyclerViewArchivedProjects(system, this, 0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        buttonBack.setOnClickListener(new OnClickListenerSwitchFragment(new OverviewFragment(system), system, R.anim.slide_down));
    }

    public RecyclerAdapterProjects getRecyclerAdapter() {
        return recyclerAdapter;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
