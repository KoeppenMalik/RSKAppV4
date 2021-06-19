// Created on 23.01.2021, 15:24

package de.malik.myapplication.gui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import de.malik.myapplication.R;
import de.malik.myapplication.listeners.onclick.overviewfragment.OnClickListenerViewArchivedCustomers;
import de.malik.myapplication.listeners.onclick.overviewfragment.OnClickListenerViewRecentlyVisitedCustomer;
import de.malik.myapplication.listeners.onclick.overviewfragment.OnClickListenerViewRequests;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.listeners.onclick.overviewfragment.OnClickListenerButtonAddCustomer;

public class OverviewFragment extends Fragment {

    private View v;
    private RSKSystem system;
    private Button buttonAddCustomer, buttonSavedCustomerNames, buttonSavedWorkDescriptions;
    private TextView textViewRecentlyVisitedCustomer;
    private View viewRecentlyVisitedCustomer, viewArchivedCustomers, viewRequests;

    public OverviewFragment(RSKSystem system) {
        this.system = system;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.overview_fragment, container, false);
        handleGui();
        return v;
    }

    private void handleGui() {
        createComponents();
        if (system.getRecentlyVisitedProject() != null)
            textViewRecentlyVisitedCustomer.setText(system.getRecentlyVisitedProject().getName());
        setListeners();
    }

    private void createComponents() {
        buttonAddCustomer = v.findViewById(R.id.buttonAddCustomer);
        viewRecentlyVisitedCustomer = v.findViewById(R.id.layoutRecentlyVisitedCustomer);
        viewArchivedCustomers = v.findViewById(R.id.layoutArchivedCostumers);
        viewRequests = v.findViewById(R.id.layoutRequests);
        textViewRecentlyVisitedCustomer = v.findViewById(R.id.textViewRecentlyVisitedCustomer);
        buttonSavedCustomerNames = v.findViewById(R.id.buttonSavedCustomerNames);
        buttonSavedWorkDescriptions = v.findViewById(R.id.buttonSavedWorkDescriptions);
    }

    private void setListeners() {
        buttonAddCustomer.setOnClickListener(new OnClickListenerButtonAddCustomer(system));
        viewRecentlyVisitedCustomer.setOnClickListener(new OnClickListenerViewRecentlyVisitedCustomer(system));
        viewArchivedCustomers.setOnClickListener(new OnClickListenerViewArchivedCustomers(system));
        viewRequests.setOnClickListener(new OnClickListenerViewRequests(system));
        buttonSavedCustomerNames.setOnClickListener((View v) -> system.replaceCurrentFragmentWith(new CustomerNamesFragment(system), R.anim.nav_default_enter_anim));
        buttonSavedWorkDescriptions.setOnClickListener((View v) -> system.replaceCurrentFragmentWith(new WorkDescriptionsFragment(system), R.anim.nav_default_enter_anim));
    }
}
