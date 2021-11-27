// Created on 30.01.2021, 19:10

package de.malik.myapplication.util.recyclerviews.projects.recyclerviewprojects;

import android.graphics.Canvas;
import android.graphics.Color;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.ProjectsFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.customermanagement.ProjectManager;
import de.malik.myapplication.util.recyclerviews.projects.RecyclerAdapterProjects;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class ItemTouchHelperRecyclerViewProjects extends ItemTouchHelper.SimpleCallback {

    private RSKSystem system;
    private ProjectsFragment projectsFragment;

    public ItemTouchHelperRecyclerViewProjects(int dragDirs, int swipeDirs, ProjectsFragment projectsFragment, RSKSystem system) {
        super(dragDirs, swipeDirs);
        this.system = system;
        this.projectsFragment = projectsFragment;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        final int INDEX = viewHolder.getAdapterPosition();
        Project deletedProject, archivedProject;
        RecyclerAdapterProjects recyclerAdapterProjects = projectsFragment.getRecyclerAdapterProjects();
        ProjectManager projectManager = system.getProjectManager();

        if (direction == ItemTouchHelper.LEFT) {
            // delete
            deletedProject = projectManager.getProjects().get(INDEX);
            projectManager.getProjects().remove(INDEX);
            recyclerAdapterProjects.notifyDataSetChanged();
            Snackbar.make(projectsFragment.getRecyclerView(), "Kunde \"" + deletedProject.getName() + "\" gelöscht", Snackbar.LENGTH_LONG)
                    .setAction("Rückgängig machen", new OnClickListenerUndoDeleteCustomer(system, recyclerAdapterProjects, INDEX, deletedProject))
                    .show();
        }
        else if (direction == ItemTouchHelper.RIGHT) {
            // archive
            archivedProject = projectManager.getProjects().get(INDEX);
            if (system.getRecentlyVisitedProject() == archivedProject)
                system.setRecentlyVisitedProject(archivedProject);
            projectManager.getProjects().remove(INDEX);
            projectManager.getArchivedProjects().add(archivedProject);
            recyclerAdapterProjects.notifyDataSetChanged();
            Snackbar.make(projectsFragment.getRecyclerView(), "Kunde \"" + archivedProject.getName() + "\" archiviert", Snackbar.LENGTH_LONG)
                    .setAction("Rückgängig machen", new OnClickListenerUndoArchiveCustomer(INDEX, recyclerAdapterProjects, archivedProject, system))
                    .show();
        }
        recyclerAdapterProjects.setProjects(projectManager.getProjects());
        system.getFileManager().getPrinter().reprintFiles(projectManager);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                .addSwipeLeftBackgroundColor(Color.parseColor("#CC0000"))
                .addSwipeLeftActionIcon(R.drawable.ic_delete)
                .addSwipeRightBackgroundColor(Color.parseColor("#00CC00"))
                .addSwipeRightActionIcon(R.drawable.ic_archive)
                .create().decorate();
    }
}
