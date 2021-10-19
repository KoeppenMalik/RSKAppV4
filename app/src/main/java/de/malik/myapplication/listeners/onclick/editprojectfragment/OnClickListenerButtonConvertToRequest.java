package de.malik.myapplication.listeners.onclick.editprojectfragment;

import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.ProjectsFragment;
import de.malik.myapplication.gui.fragments.RequestFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.customermanagement.ProjectManager;
import de.malik.myapplication.util.customermanagement.Request;

public class OnClickListenerButtonConvertToRequest implements View.OnClickListener {

    private RSKSystem system;
    private Project project;

    public OnClickListenerButtonConvertToRequest(RSKSystem system, Project project) {
        this.system = system;
        this.project = project;
    }

    @Override
    public void onClick(View viewParam) {
        ProjectManager projectManager = system.getProjectManager();
        Request request = new Request(projectManager.getNextRequestId(), project.getName(), project.getDate(), project.getWorkDescription());
        projectManager.getRequests().add(request);
        system.getFileManager().getPrinter().reprintFiles(system.getProjectManager());
        system.replaceCurrentFragmentWith(new RequestFragment(system, request), R.anim.slide_left);
        Snackbar.make(system.getMain().getBottomNav(), project.getName() + " wurde zu Planung konvertiert", Snackbar.LENGTH_LONG)
        .setAction("Rückgängig machen", (View v) -> {
            projectManager.getRequests().remove(request);
            projectManager.getProjects().add(project);
            system.getFileManager().getPrinter().reprintFiles(system.getProjectManager());
            system.replaceCurrentFragmentWith(new ProjectsFragment(system), R.anim.slide_down);
        }).show();
    }
}
