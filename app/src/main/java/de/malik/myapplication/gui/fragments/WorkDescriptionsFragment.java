package de.malik.myapplication.gui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import de.malik.myapplication.R;
import de.malik.myapplication.listeners.onclick.workdescriptionsfragment.OnClickListenerButtonAddNewWorkDescription;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.recyclerviews.workdescriptions.ItemTouchHelperRecyclerViewWorkDescriptions;
import de.malik.myapplication.util.recyclerviews.workdescriptions.RecyclerAdapterWorkDescriptions;

public class WorkDescriptionsFragment extends Fragment {

    private View v;
    private RSKSystem system;
    private Button buttonBack, buttonAddNewWorkDescription;
    private RecyclerView recyclerView;
    private RecyclerAdapterWorkDescriptions recyclerAdapter;
    private ItemTouchHelper itemTouchHelper;

    public WorkDescriptionsFragment(RSKSystem system) {
        this.system = system;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.work_descriptions_fragment, container, false);
        handleGui();
        return v;
    }

    private void handleGui() {
        createComponents();
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(system.getContext(), DividerItemDecoration.VERTICAL));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        setListeners();
    }

    private void createComponents() {
        buttonBack = v.findViewById(R.id.buttonBack);
        buttonAddNewWorkDescription = v.findViewById(R.id.buttonAddNewWorkDescription);
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapterWorkDescriptions(system);
        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperRecyclerViewWorkDescriptions(0, ItemTouchHelper.LEFT, system, recyclerAdapter));
    }

    private void setListeners() {
        buttonBack.setOnClickListener((View v) -> system.replaceCurrentFragmentWith(new OverviewFragment(system), R.anim.nav_default_enter_anim));
        buttonAddNewWorkDescription.setOnClickListener(new OnClickListenerButtonAddNewWorkDescription(system));
    }
}
