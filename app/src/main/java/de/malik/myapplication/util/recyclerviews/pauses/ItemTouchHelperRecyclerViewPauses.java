// Created on 31.01.2021, 15:31

package de.malik.myapplication.util.recyclerviews.pauses;

import android.graphics.Canvas;
import android.graphics.Color;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.ProjectFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Project;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class ItemTouchHelperRecyclerViewPauses extends ItemTouchHelper.SimpleCallback {

    private Project project;
    private ProjectFragment projectFragment;
    private RSKSystem system;

    public ItemTouchHelperRecyclerViewPauses(int dragDirs, int swipeDirs, Project project, ProjectFragment projectFragment, RSKSystem system) {
        super(dragDirs, swipeDirs);
        this.project = project;
        this.projectFragment = projectFragment;
        this.system = system;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        final int INDEX = viewHolder.getAdapterPosition();

        if (direction == ItemTouchHelper.LEFT) {
            project.getPauses().remove(INDEX);
            projectFragment.getRecyclerAdapterPauses().notifyDataSetChanged();
            projectFragment.notifyCustomerTimeChange();
            system.getFileManager().getPrinter().reprintFiles(system.getProjectManager());
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                .addSwipeLeftBackgroundColor(Color.parseColor("#CC0000"))
                .addSwipeLeftActionIcon(R.drawable.ic_delete)
                .create().decorate();
    }
}
