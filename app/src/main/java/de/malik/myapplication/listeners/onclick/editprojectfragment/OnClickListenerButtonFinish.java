// Created on 05.02.2021, 18:52

package de.malik.myapplication.listeners.onclick.editprojectfragment;

import android.view.View;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.ProjectFragment;
import de.malik.myapplication.gui.fragments.EditProjectDataFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Project;

public class OnClickListenerButtonFinish implements View.OnClickListener {

    private RSKSystem system;
    private Project project;
    private EditProjectDataFragment editDataFragment;

    public OnClickListenerButtonFinish(RSKSystem system, Project project, EditProjectDataFragment editDataFragment) {
        this.system = system;
        this.project = project;
        this.editDataFragment = editDataFragment;
    }

    @Override
    public void onClick(View v) {
        String name, workDescription;

        if (!editDataFragment.getEditTextName().getText().toString().equals("")) {
            name = editDataFragment.getEditTextName().getText().toString();
        }
        else name = "-";
        if (!editDataFragment.getEditTextWorkDescription().getText().toString().equals("")) {
            workDescription = editDataFragment.getEditTextWorkDescription().getText().toString();
        }
        else workDescription = "-";
        editDataFragment.getTextViewCurrentName().setText(name);
        project.setName(name);
        project.setWorkDescription(workDescription);
        system.getFileManager().getPrinter().reprintFiles(system.getFileManager(), system.getProjectManager());
        system.replaceCurrentFragmentWith(new ProjectFragment(system, project), R.anim.slide_down);
    }
}
