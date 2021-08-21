package de.malik.myapplication.listeners.onclick.projectsfragment;

import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.ProjectsFragment;
import de.malik.myapplication.gui.fragments.WorkDescriptionsFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.filter.Filter;
import de.malik.myapplication.util.filter.FilterValue;
import de.malik.myapplication.util.recyclerviews.projects.RecyclerAdapterProjects;

public class OnClickListenerButtonFilterProjects implements View.OnClickListener {

    private RSKSystem system;
    private RecyclerAdapterProjects recyclerAdapterProjects;
    private Filter[] filters;
    private String[] filterNames;

    public OnClickListenerButtonFilterProjects(RSKSystem system, RecyclerAdapterProjects recyclerAdapterProjects) {
        this.system = system;
        this.recyclerAdapterProjects = recyclerAdapterProjects;
        filters = system.getFilters();
        filterNames = new String[filters.length];
        for (int i = 0; i < filters.length; i++) {
            filterNames[i] = filters[i].getText();
        }
    }

    @Override
    public void onClick(View v) {
        Dialog dialog = new Dialog(system.getMain().getDialogContext());
        dialog.setContentView(R.layout.select_filter_layout);
        dialog.setTitle("Filter");
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);

        Spinner spinner = dialog.findViewById(R.id.spinnerFilters);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(system.getContext(), R.layout.spinner_item, filterNames);
        spinner.setAdapter(adapter);
        Button buttonFinish = dialog.findViewById(R.id.buttonFinish),
                buttonCancel = dialog.findViewById(R.id.buttonCancel);

        buttonCancel.setOnClickListener((view) -> dialog.dismiss());
        buttonFinish.setOnClickListener((view) -> {
            Filter newFilter = filters[spinner.getSelectedItemPosition()];
            ArrayList<Project> filteredProjects = Filter.sort(system.getCurrentFilter().getValue(), newFilter.getValue(), system.getProjectManager().getProjects());
            system.setCurrentFilter(newFilter);
            system.getProjectManager().setProjects(filteredProjects);
            recyclerAdapterProjects.notifyDataSetChanged();
            system.getFileManager().getPrinter().reprintFiles(system.getProjectManager());
            dialog.dismiss();
            system.replaceCurrentFragmentWith(new ProjectsFragment(system), RSKSystem.NO_ANIM);
        });
        Log.i("TAG", system.getCurrentFilter() + "");
        dialog.create();
        dialog.show();
    }
}
