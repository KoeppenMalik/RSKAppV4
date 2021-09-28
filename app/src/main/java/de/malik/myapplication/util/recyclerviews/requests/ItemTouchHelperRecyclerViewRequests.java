// Created on 02.03.2021, 19:36

package de.malik.myapplication.util.recyclerviews.requests;

import android.graphics.Canvas;
import android.graphics.Color;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.RequestsFragment;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.ProjectManager;
import de.malik.myapplication.util.customermanagement.Request;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class ItemTouchHelperRecyclerViewRequests extends ItemTouchHelper.SimpleCallback {

    private ProjectManager projectManager;
    private RSKSystem system;
    private RequestsFragment requestsFragment;

    public ItemTouchHelperRecyclerViewRequests(int dragDirs, int swipeDirs, RSKSystem system, RequestsFragment requestsFragment) {
        super(dragDirs, swipeDirs);
        this.system = system;
        this.projectManager = system.getProjectManager();
        this.requestsFragment = requestsFragment;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        final int INDEX = viewHolder.getAdapterPosition();
        Request deletedRequest;

        if (direction == ItemTouchHelper.LEFT) {
            deletedRequest =  projectManager.getRequests().get(INDEX);
            projectManager.getRequests().remove(INDEX);
            requestsFragment.getRecyclerAdapterRequests().notifyDataSetChanged();
            Snackbar.make(requestsFragment.getRecyclerView(), "Anfrage von \"" + deletedRequest.getName() + "\" gelöscht", Snackbar.LENGTH_LONG)
                    .setAction("Rückgängig machen", new OnClickListenerUndoDeleteRequest(INDEX, deletedRequest, system, requestsFragment)).show();
        }
        requestsFragment.getRecyclerAdapterRequests().setRequests(projectManager.getRequests());
        system.getFileManager().getPrinter().reprintFiles(projectManager);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                .addSwipeLeftActionIcon(R.drawable.ic_delete)
                .addSwipeLeftBackgroundColor(Color.parseColor("#CC0000"))
                .create().decorate();
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
