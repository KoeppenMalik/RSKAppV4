package de.malik.myapplication.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import de.malik.myapplication.R;
import de.malik.myapplication.listeners.onclick.OnClickListenerSwitchFragment;
import de.malik.myapplication.listeners.onclick.customernamesfragment.OnClickListenerButtonAddNewCustomerName;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.recyclerviews.customernames.ItemTouchHelperRecyclerViewCustomerNames;
import de.malik.myapplication.util.recyclerviews.customernames.RecyclerAdapterCustomerNames;

public class ProjectNamesFragment extends Fragment {

    private View v;
    private RSKSystem system;
    private Button buttonAddNewCustomerName, buttonBack;
    private RecyclerView recyclerView;
    private RecyclerAdapterCustomerNames recyclerAdapter;
    private ItemTouchHelper itemTouchHelper;

    public ProjectNamesFragment(RSKSystem system) {
        this.system = system;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.customer_names_fragment, container, false);
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
        recyclerView = v.findViewById(R.id.recyclerView);
        buttonAddNewCustomerName = v.findViewById(R.id.buttonAddCustomerName);
        recyclerAdapter = new RecyclerAdapterCustomerNames(system);
        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperRecyclerViewCustomerNames(0, ItemTouchHelper.LEFT, system, this));
        buttonBack = v.findViewById(R.id.buttonBack);
    }

    private void setListeners() {
        buttonAddNewCustomerName.setOnClickListener(new OnClickListenerButtonAddNewCustomerName(system));
        buttonBack.setOnClickListener(new OnClickListenerSwitchFragment(new OverviewFragment(system), system, R.anim.nav_default_enter_anim));
    }

    public RecyclerAdapterCustomerNames getRecyclerAdapter() {
        return recyclerAdapter;
    }
}
