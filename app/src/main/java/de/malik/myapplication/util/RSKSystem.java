// Created on 23.01.2021, 15:27

package de.malik.myapplication.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.MainActivity;
import de.malik.myapplication.gui.fragments.ErrorFragment;
import de.malik.myapplication.gui.fragments.OverviewFragment;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.customermanagement.ProjectManager;
import de.malik.myapplication.util.filemanagement.RSKFileManager;
import de.malik.myapplication.util.filter.Filter;
import de.malik.myapplication.util.filter.FilterValue;

public class RSKSystem {

    public static final int NO_ANIM = -1;

    private final Filter[] FILTERS = new Filter[FilterValue.values().length];
    private final String[] FILTER_LABELS = new String[] {
            "Datum (alt zu neu)", "Datum (neu zu alt)", "Name (A - Z)", "Erstellt (alt zu neu)"
    };
    private final MainActivity MAINActivity;
    private final ProjectManager PROJECT_MANAGER;
    private final RSKFileManager FILE_MANAGER;

    public static Filter currentFilter;

    private Fragment currentFragment = new OverviewFragment(this);
    private Project recentlyVisitedProject = null;

    public RSKSystem(MainActivity mainActivity, ProjectManager projectManager, RSKFileManager fileManager) {
        this.MAINActivity = mainActivity;
        this.PROJECT_MANAGER = projectManager;
        this.FILE_MANAGER = fileManager;
        for (int i = 0; i < FILTER_LABELS.length; i++) {
            FILTERS[i] = new Filter(FILTER_LABELS[i], FilterValue.values()[i]);
        }
        currentFilter = FILTERS[0];
    }

    public MainActivity getMain() {
        return MAINActivity;
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
        Toast.makeText(MAINActivity, text, Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @return the main activity context
     */
    public Context getContext() {
        return MAINActivity.getApplicationContext();
    }

    /**
     *
     * @return the support fragment manager of the main activity
     */
    public FragmentManager getSupportFragmentManager() {
        return MAINActivity.getSupportFragmentManager();
    }

    /**
     * shows a fragment with the given error message
     * @param message the error message
     */
    public void showErrorDialog(String message) {
        replaceCurrentFragmentWith(new ErrorFragment(message, this), android.R.anim.fade_in);
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    public String[] getFilterLabels() {
        return FILTER_LABELS;
    }

    public Filter[] getFilters() {
        return FILTERS;
    }

    public RSKFileManager getFileManager() {
        return FILE_MANAGER;
    }

    public ProjectManager getProjectManager() {
        return PROJECT_MANAGER;
    }

    public void setRecentlyVisitedProject(Project recentlyVisitedProject) {
        this.recentlyVisitedProject = recentlyVisitedProject;
    }

    public Project getRecentlyVisitedProject() {
        return recentlyVisitedProject;
    }

    public void setCurrentFragment(Fragment currentFragment) {
        this.currentFragment = currentFragment;
    }
}
