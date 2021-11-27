// Created on 02.03.2021, 19:18

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
import de.malik.myapplication.listeners.onclick.ListenerSwitchFragment;
import de.malik.myapplication.listeners.onclick.requestsfragment.OnClickListenerButtonAddRequest;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.recyclerviews.requests.ItemTouchHelperRecyclerViewRequests;
import de.malik.myapplication.util.recyclerviews.requests.RecyclerAdapterRequests;

public class RequestsFragment extends Fragment {

    private RSKSystem system;
    private View v;
    private RecyclerView recyclerView;
    private RecyclerAdapterRequests recyclerAdapterRequests;
    private ItemTouchHelper itemTouchHelper;
    private Button buttonAddRequest, buttonBack;

    public RequestsFragment(RSKSystem system) {
        this.system = system;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.requests_fragment, container, false);
        handleGui();
        return v;
    }

    private void handleGui() {
        createComponents();
        recyclerView.setLayoutManager(new LinearLayoutManager(system.getContext()));
        recyclerView.setAdapter(recyclerAdapterRequests);
        recyclerView.addItemDecoration(new DividerItemDecoration(system.getContext(), DividerItemDecoration.VERTICAL));
        setListeners();
    }

    private void createComponents() {
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerAdapterRequests = new RecyclerAdapterRequests(system.getProjectManager().getRequests(), system);
        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperRecyclerViewRequests(0, ItemTouchHelper.LEFT, system, this));
        buttonAddRequest = v.findViewById(R.id.buttonAddRequest);
        buttonBack = v.findViewById(R.id.buttonBack);
    }

    private void setListeners() {
        buttonBack.setOnClickListener(new ListenerSwitchFragment(new OverviewFragment(system), system, R.anim.slide_down));
        buttonAddRequest.setOnClickListener(new OnClickListenerButtonAddRequest(system));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public RecyclerAdapterRequests getRecyclerAdapterRequests() {
        return recyclerAdapterRequests;
    }
}
