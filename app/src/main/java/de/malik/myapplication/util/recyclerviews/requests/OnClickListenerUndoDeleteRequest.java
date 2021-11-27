// Created on 07.03.2021, 18:53

package de.malik.myapplication.util.recyclerviews.requests;

import android.view.View;
import de.malik.myapplication.gui.fragments.RequestsFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.ProjectManager;
import de.malik.myapplication.util.customermanagement.Request;

public class OnClickListenerUndoDeleteRequest implements View.OnClickListener {

    private int index;
    private Request deletedRequest;
    private RSKSystem system;
    private ProjectManager projectManager;
    private RequestsFragment requestsFragment;

    public OnClickListenerUndoDeleteRequest(int index, Request deletedRequest, RSKSystem system, RequestsFragment requestsFragment) {
        this.index = index;
        this.deletedRequest = deletedRequest;
        this.projectManager = system.getProjectManager();
        this.requestsFragment = requestsFragment;
        this.system = system;
    }

    @Override
    public void onClick(View v) {
        projectManager.getRequests().add(index, deletedRequest);
        requestsFragment.getRecyclerAdapterRequests().notifyDataSetChanged();
        system.getFileManager().getPrinter().reprintFiles(projectManager);
    }
}
