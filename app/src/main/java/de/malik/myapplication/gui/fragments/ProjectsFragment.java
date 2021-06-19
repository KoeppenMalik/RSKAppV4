// Created on 23.01.2021, 16:51

package de.malik.myapplication.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import de.malik.myapplication.listeners.onclick.OnClickListenerButtonSave;
import de.malik.myapplication.listeners.onclick.projectsfragment.OnClickListenerButtonFilterProjects;
import de.malik.myapplication.listeners.onquerytext.OnQueryTextListenerSearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import de.malik.myapplication.R;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.recyclerviews.projects.recyclerviewprojects.ItemTouchHelperRecyclerViewProjects;
import de.malik.myapplication.util.recyclerviews.projects.RecyclerAdapterProjects;

public class ProjectsFragment extends Fragment {

    private View v;
    private RSKSystem system;
    private RecyclerView recyclerView;
    private RecyclerAdapterProjects recyclerAdapterProjects;
    private Button buttonSave, buttonFilter;

    public ProjectsFragment(RSKSystem system) {
        this.system = system;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (system.getProjectManager().getProjects().size() > 0) {
            v = inflater.inflate(R.layout.projects_fragment, container, false);
            handleGui();
        }
        else system.replaceCurrentFragmentWith(new ErrorFragment("Derzeit sind keine Kunden vorhanden.", system), R.anim.slide_left);
        return v;
    }

    private void handleGui() {
        createComponents();
        recyclerAdapterProjects = new RecyclerAdapterProjects(system.getProjectManager().getProjects(), system);
        recyclerView.setAdapter(recyclerAdapterProjects);
        recyclerView.addItemDecoration(new DividerItemDecoration(system.getContext(), DividerItemDecoration.VERTICAL));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperRecyclerViewProjects(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this, system));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        setListeners();
    }

    private void createComponents() {
        buttonFilter = v.findViewById(R.id.buttonFilter);
        recyclerView = v.findViewById(R.id.recyclerView);
        buttonSave = v.findViewById(R.id.buttonSave);
    }

    private void setListeners() {
        buttonFilter.setOnClickListener(new OnClickListenerButtonFilterProjects(system, recyclerAdapterProjects));
        system.getMainActivitySearchView().setOnQueryTextListener(new OnQueryTextListenerSearchView(recyclerAdapterProjects));
        buttonSave.setOnClickListener(new OnClickListenerButtonSave(system));
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public RecyclerAdapterProjects getRecyclerAdapterProjects() {
        return recyclerAdapterProjects;
    }
}
