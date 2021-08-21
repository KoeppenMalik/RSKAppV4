// Created on 23.01.2021, 15:27

package de.malik.myapplication.util;

import android.content.Context;
import android.widget.Toast;
import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.Main;
import de.malik.myapplication.gui.fragments.ErrorFragment;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.customermanagement.ProjectManager;
import de.malik.myapplication.util.filemanagement.RSKFileManager;
import de.malik.myapplication.util.filter.Filter;
import de.malik.myapplication.util.filter.FilterValue;

public class RSKSystem {

    public static final int NO_ANIM = -1;

    private Filter[] filters;
    private Filter currentFilter = new Filter("ID", FilterValue.ID);
    private Main main;
    private ProjectManager projectManager;
    private RSKFileManager fileManager;
    private Project recentlyVisitedProject = null;

    public RSKSystem(Main main, ProjectManager projectManager, RSKFileManager fileManager) {
        this.main = main;
        this.projectManager = projectManager;
        this.fileManager = fileManager;
        filters = new Filter[] {
                new Filter("NEU ZU ALT", FilterValue.NEW_TO_OLD),
                new Filter("ID", FilterValue.ID)
        };
    }

    public Main getMain() {
        return main;
    }

    /**
     * replaces the current layout with a new one
     * @param fragment the fragment which should be displayed
     */
    public void replaceCurrentFragmentWith(Fragment fragment, @AnimatorRes @AnimRes int anim) {
        if (anim == NO_ANIM) {
            getSupportFragmentManager().beginTransaction().replace(R.id.containerLayout, fragment).commit();
        }
        getSupportFragmentManager().beginTransaction().setCustomAnimations(anim, 0).replace(R.id.containerLayout, fragment).commit();
    }

    /**
     * creates a Toast with length: Toast.LENGTH_SHORT
     * @param text the text that will be displayed
     */
    public void makeShortToast(String text) {
        Toast.makeText(main, text, Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @return the main activity context
     */
    public Context getContext() {
        return main.getApplicationContext();
    }

    /**
     *
     * @return the support fragment manager of the main activity
     */
    public FragmentManager getSupportFragmentManager() {
        return main.getSupportFragmentManager();
    }

    /**
     *
     * @return the search view of the main activity
     */
    public SearchView getMainActivitySearchView() {
        return main.getSearchView();
    }

    /**
     * shows a fragment with the given error message
     * @param message the error message
     */
    public void showErrorDialog(String message) {
        replaceCurrentFragmentWith(new ErrorFragment(message, this), android.R.anim.fade_in);
    }

    public Filter[] getFilters() {
        return filters;
    }

    public Filter getCurrentFilter() {
        return currentFilter;
    }

    public void setCurrentFilter(Filter currentFilter) {
        this.currentFilter = currentFilter;
    }

    public RSKFileManager getFileManager() {
        return fileManager;
    }

    public ProjectManager getProjectManager() {
        return projectManager;
    }

    public void setRecentlyVisitedProject(Project recentlyVisitedProject) {
        this.recentlyVisitedProject = recentlyVisitedProject;
    }

    public Project getRecentlyVisitedProject() {
        return recentlyVisitedProject;
    }
}
