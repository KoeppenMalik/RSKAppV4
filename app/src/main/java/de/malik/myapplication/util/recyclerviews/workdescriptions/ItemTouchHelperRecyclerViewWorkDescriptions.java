package de.malik.myapplication.util.recyclerviews.workdescriptions;

import android.graphics.Canvas;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import de.malik.myapplication.R;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.ProjectManager;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class ItemTouchHelperRecyclerViewWorkDescriptions extends ItemTouchHelper.SimpleCallback {

    private RSKSystem system;
    private RecyclerAdapterWorkDescriptions recyclerAdapterWorkDescriptions;

    public ItemTouchHelperRecyclerViewWorkDescriptions(int dragDirs, int swipeDirs, RSKSystem system, RecyclerAdapterWorkDescriptions recyclerAdapterWorkDescriptions) {
        super(dragDirs, swipeDirs);
        this.system = system;
        this.recyclerAdapterWorkDescriptions = recyclerAdapterWorkDescriptions;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int INDEX = viewHolder.getAdapterPosition();
        ProjectManager projectManager = system.getProjectManager();

        if (direction == ItemTouchHelper.LEFT) {
            projectManager.getSavedWorkDescriptions().remove(INDEX);
            recyclerAdapterWorkDescriptions.notifyDataSetChanged();
        }
        system.makeShortToast("Arbeitsbeschreibung '" + projectManager.getSavedWorkDescriptions().get(INDEX) + "' entfernt");
        recyclerAdapterWorkDescriptions.setSavedWorkDescriptions(system.getProjectManager().getSavedWorkDescriptions());
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                .addSwipeLeftBackgroundColor(Color.parseColor("#CC0000"))
                .addSwipeLeftActionIcon(R.drawable.ic_delete_small)
                .create().decorate();
    }
}
