// Created on 23.01.2021, 16:51

package de.malik.myapplication.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import de.malik.myapplication.listeners.onclick.projectsfragment.OnClickListenerButtonFilterProjects;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import de.malik.myapplication.R;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.projectmanagement.ProjectManager;
import de.malik.myapplication.util.recyclerviews.projects.recyclerviewprojects.ItemTouchHelperRecyclerViewProjects;
import de.malik.myapplication.util.recyclerviews.projects.RecyclerAdapterProjects;

public class ProjectsFragment extends Fragment {

    private View v;
    private RSKSystem system;
    private RecyclerView recyclerView;
    private RecyclerAdapterProjects recyclerAdapterProjects;
    private Button buttonFilter;

    public ProjectsFragment(RSKSystem system) {
        this.system = system;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (ProjectManager.projects.size() > 0) {
            v = inflater.inflate(R.layout.projects_fragment, container, false);
            handleGui();
        }
        else system.showErrorDialog("Derzeit sind keine Projekte vorhanden.");
        return v;
    }

    private void handleGui() {
        createComponents();
        recyclerAdapterProjects = new RecyclerAdapterProjects(ProjectManager.projects, system);
        recyclerView.setAdapter(recyclerAdapterProjects);
        recyclerView.addItemDecoration(new DividerItemDecoration(system.getContext(), DividerItemDecoration.VERTICAL));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperRecyclerViewProjects(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this, system));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        setListeners();
    }

    private void createComponents() {
        buttonFilter = v.findViewById(R.id.buttonFilter);
        recyclerView = v.findViewById(R.id.recyclerView);
    }

    private void setListeners() {
        buttonFilter.setOnClickListener(new OnClickListenerButtonFilterProjects(system, recyclerAdapterProjects));
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public RecyclerAdapterProjects getRecyclerAdapterProjects() {
        return recyclerAdapterProjects;
    }
}
