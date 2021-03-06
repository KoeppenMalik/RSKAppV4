// Created on 28.02.2021, 19:48

package de.malik.myapplication.util.recyclerviews.projects.recyclerviewarchivedprojects;

import android.graphics.Canvas;
import android.graphics.Color;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.ArchivedProjectsFragment;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.customermanagement.ProjectManager;
import de.malik.myapplication.util.recyclerviews.projects.RecyclerAdapterProjects;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class ItemTouchHelperRecyclerViewArchivedProjects extends ItemTouchHelper.SimpleCallback {

    private ProjectManager projectManager;
    private ArchivedProjectsFragment archivedProjectsFragment;

    public ItemTouchHelperRecyclerViewArchivedProjects(ProjectManager projectManager, ArchivedProjectsFragment archivedProjectsFragment, int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
        this.projectManager = projectManager;
        this.archivedProjectsFragment = archivedProjectsFragment;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        final int INDEX = viewHolder.getAdapterPosition();
        Project deletedProject, unarchivedProject;
        RecyclerAdapterProjects recyclerAdapterProjects = archivedProjectsFragment.getRecyclerAdapter();

        if (direction == ItemTouchHelper.LEFT) {
            // delete
            deletedProject = projectManager.getArchivedProjects().get(INDEX);
            projectManager.getArchivedProjects().remove(INDEX);
            recyclerAdapterProjects.notifyDataSetChanged();
            Snackbar.make(archivedProjectsFragment.getRecyclerView(), "Kunde \"" + deletedProject.getName() + "\" gel??scht", Snackbar.LENGTH_LONG)
                    .setAction("R??ckg??ngig machen", new OnClickListenerUndoDeleteArchivedCustomer(INDEX, projectManager, deletedProject, recyclerAdapterProjects))
                    .show();
        }
        else if (direction == ItemTouchHelper.RIGHT) {
            // unarchive
            unarchivedProject = projectManager.getArchivedProjects().get(INDEX);
            projectManager.getArchivedProjects().remove(INDEX);
            projectManager.getProjects().add(unarchivedProject);
            recyclerAdapterProjects.notifyDataSetChanged();
            Snackbar.make(archivedProjectsFragment.getRecyclerView(), "Kunde \"" + unarchivedProject.getName() + "\" unarchiviert", Snackbar.LENGTH_LONG)
                    .setAction("R??ckg??ngig machen", new OnClickListenerUndoUnarchiveCustomer(INDEX, projectManager, unarchivedProject, recyclerAdapterProjects))
                    .show();
        }
        recyclerAdapterProjects.setFilteredProjects(projectManager.getArchivedProjects());
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                .addSwipeLeftBackgroundColor(Color.parseColor("#CC0000"))
                .addSwipeLeftActionIcon(R.drawable.ic_delete)
                .addSwipeRightBackgroundColor(Color.parseColor("#00CC00"))
                .addSwipeRightActionIcon(R.drawable.ic_unarchive)
                .create().decorate();
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
