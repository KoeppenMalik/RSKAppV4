package de.malik.myapplication.listeners.onclick.editprojectfragment;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.ProjectsFragment;
import de.malik.myapplication.gui.fragments.RequestFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.projectmanagement.Project;
import de.malik.myapplication.util.projectmanagement.ProjectManager;
import de.malik.myapplication.util.projectmanagement.Request;

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
        Request request = new Request(projectManager.getNextId(projectManager.getRequests()), project.getName(), project.getDate(), project.getWorkDescription());
        projectManager.getRequests().add(request);
        system.saveData();
        system.replaceCurrentFragmentWith(new RequestFragment(system, request), R.anim.slide_left);
        Snackbar.make(system.getMainActivity().getBottomNav(), project.getName() + " wurde zu Anfrage konvertiert", Snackbar.LENGTH_LONG)
        .setAction("Rückgängig machen", (View v) -> {
            projectManager.getRequests().remove(request);
            ProjectManager.projects.add(project);
            system.saveData();
            system.replaceCurrentFragmentWith(new ProjectsFragment(system), R.anim.slide_down);
        }).show();
    }
}
