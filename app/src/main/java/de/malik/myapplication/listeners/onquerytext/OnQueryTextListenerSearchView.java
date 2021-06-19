// Created on 31.01.2021, 13:10

package de.malik.myapplication.listeners.onquerytext;

import androidx.appcompat.widget.SearchView;
import de.malik.myapplication.util.recyclerviews.projects.RecyclerAdapterProjects;

public class OnQueryTextListenerSearchView implements SearchView.OnQueryTextListener {

    private RecyclerAdapterProjects recyclerAdapterProjects;

    public OnQueryTextListenerSearchView(RecyclerAdapterProjects recyclerAdapterProjects) {
        this.recyclerAdapterProjects = recyclerAdapterProjects;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        recyclerAdapterProjects.getFilter().filter(newText);
        return true;
    }
}
