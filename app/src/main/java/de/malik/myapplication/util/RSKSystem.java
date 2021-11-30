// Created on 23.01.2021, 15:27

package de.malik.myapplication.util;

import static de.malik.myapplication.util.filter.Filter.*;

import android.content.Context;
import android.widget.Toast;
import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import de.malik.myapplication.R;
import de.malik.myapplication.gui.MainActivity;
import de.malik.myapplication.gui.fragments.ErrorFragment;
import de.malik.myapplication.gui.fragments.OverviewFragment;
import de.malik.myapplication.util.projectmanagement.Project;
import de.malik.myapplication.util.projectmanagement.ProjectManager;
import de.malik.myapplication.util.filemanagement.RSKFileManager;
import de.malik.myapplication.util.filter.Filter;

public class RSKSystem {

    public static final int NO_ANIM = -1;

    public static Filter currentFilter;

    private final Filter[] FILTERS = new Filter[FILTER_LABELS.length];
    private final MainActivity MAIN_ACTIVITY;
    private final ProjectManager PROJECT_MANAGER;
    private final RSKFileManager FILE_MANAGER;
    private Fragment currentFragment = new OverviewFragment(this);
    private Project recentlyVisitedProject = null;

    public RSKSystem(MainActivity mainActivity, ProjectManager projectManager, RSKFileManager fileManager) {
        this.MAIN_ACTIVITY = mainActivity;
        this.PROJECT_MANAGER = projectManager;
        this.FILE_MANAGER = fileManager;
        for (int i = 0; i < FILTER_LABELS.length; i++)
            FILTERS[i] = new Filter(i+1, FILTER_LABELS[i], FILTER_VALUE_TYPES[i], FILTER_ORIENTATIONS[i]);
        currentFilter = FILTERS[0];
    }

    public void saveData() {
        PROJECT_MANAGER.updateRecords();
        FILE_MANAGER.getPrinter().reprintFiles(PROJECT_MANAGER);
    }

    public void replaceCurrentFragmentWith(Fragment fragment, @AnimatorRes @AnimRes int anim) {
        if (anim == NO_ANIM) {
            getSupportFragmentManager().beginTransaction().replace(R.id.containerLayout, fragment).commit();
            return;
        }
        getSupportFragmentManager().beginTransaction().setCustomAnimations(anim, 0).replace(R.id.containerLayout, fragment).commit();
    }

    public MainActivity getMainActivity() {
        return MAIN_ACTIVITY;
    }

    public void makeToast(String text) {
        Toast.makeText(MAIN_ACTIVITY, text, Toast.LENGTH_SHORT).show();
    }

    public Context getContext() {
        return MAIN_ACTIVITY.getApplicationContext();
    }

    public FragmentManager getSupportFragmentManager() {
        return MAIN_ACTIVITY.getSupportFragmentManager();
    }

    public void showErrorDialog(String message) {
        replaceCurrentFragmentWith(new ErrorFragment(message, this), android.R.anim.fade_in);
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    public Filter[] getFilters() {
        return FILTERS;
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
