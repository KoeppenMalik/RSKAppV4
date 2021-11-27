package de.malik.myapplication.listeners.onclick.projectsfragment;

import android.app.Dialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.ProjectsFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.filter.Filter;
import de.malik.myapplication.util.recyclerviews.projects.RecyclerAdapterProjects;

public class OnClickListenerButtonFilterProjects implements View.OnClickListener {

    private RSKSystem system;
    private RecyclerAdapterProjects recyclerAdapterProjects;

    public OnClickListenerButtonFilterProjects(RSKSystem system, RecyclerAdapterProjects recyclerAdapterProjects) {
        this.system = system;
        this.recyclerAdapterProjects = recyclerAdapterProjects;
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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(system.getContext(), R.layout.spinner_item, system.getFilterLabels());
        spinner.setAdapter(adapter);
        Button buttonFinish = dialog.findViewById(R.id.buttonFinish),
                buttonCancel = dialog.findViewById(R.id.buttonCancel);

        buttonCancel.setOnClickListener((view) -> dialog.dismiss());
        buttonFinish.setOnClickListener((view) -> {
            Filter newFilter = system.getFilters()[spinner.getSelectedItemPosition()];
            ArrayList<Project> filteredProjects = Filter.sort(newFilter.getValue(), system.getProjectManager());
            RSKSystem.currentFilter = newFilter;
            system.getProjectManager().setProjects(filteredProjects);
            recyclerAdapterProjects.notifyDataSetChanged();
            system.getFileManager().getPrinter().reprintFiles(system.getProjectManager());
            dialog.dismiss();
            system.replaceCurrentFragmentWith(new ProjectsFragment(system), RSKSystem.NO_ANIM);
        });
        dialog.create();
        dialog.show();
    }
}
