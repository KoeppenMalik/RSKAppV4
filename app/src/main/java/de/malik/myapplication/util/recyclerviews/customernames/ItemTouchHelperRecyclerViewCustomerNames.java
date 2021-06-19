package de.malik.myapplication.util.recyclerviews.customernames;

import android.graphics.Canvas;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.CustomerNamesFragment;
import de.malik.myapplication.util.RSKSystem;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class ItemTouchHelperRecyclerViewCustomerNames extends ItemTouchHelper.SimpleCallback {

    private RSKSystem system;
    private CustomerNamesFragment fragment;

    public ItemTouchHelperRecyclerViewCustomerNames(int dragDirs, int swipeDirs, RSKSystem system, CustomerNamesFragment fragment) {
        super(dragDirs, swipeDirs);
        this.system = system;
        this.fragment = fragment;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int INDEX = viewHolder.getAdapterPosition();

        if (direction == ItemTouchHelper.LEFT) {
            system.getProjectManager().getSavedCustomerNames().remove(INDEX);
            fragment.getRecyclerAdapter().notifyDataSetChanged();
        }
        system.makeShortToast("Name '" + system.getProjectManager().getSavedCustomerNames().get(INDEX) + "' entfernt");
        fragment.getRecyclerAdapter().setCustomerNames(system.getProjectManager().getSavedCustomerNames());
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                .addSwipeLeftActionIcon(R.drawable.ic_delete_small)
                .addSwipeLeftBackgroundColor(Color.parseColor("#CC0000"))
                .create().decorate();
    }
}
