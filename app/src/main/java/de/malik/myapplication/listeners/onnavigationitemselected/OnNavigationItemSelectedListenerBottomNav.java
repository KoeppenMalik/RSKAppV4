// Created on 23.01.2021, 15:31

package de.malik.myapplication.listeners.onnavigationitemselected;

import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.OverviewFragment;
import de.malik.myapplication.gui.fragments.ProjectsFragment;
import de.malik.myapplication.util.RSKSystem;

public class OnNavigationItemSelectedListenerBottomNav implements BottomNavigationView.OnNavigationItemSelectedListener {

    private RSKSystem system;
    private Fragment currentFragment;

    public OnNavigationItemSelectedListenerBottomNav(RSKSystem system, Fragment currentFragment) {
        this.system = system;
        this.currentFragment = currentFragment;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int anim = 0;
        if (menuItem.getItemId() == R.id.tabOverview && !(currentFragment instanceof OverviewFragment)) {
            currentFragment = new OverviewFragment(system);
            anim = R.anim.slide_right;
        }
        else if (menuItem.getItemId() == R.id.tabProjects && !(currentFragment instanceof ProjectsFragment)) {
            currentFragment = new ProjectsFragment(system);
            anim = R.anim.slide_left;
        }
        system.replaceCurrentFragmentWith(currentFragment, anim);
        return true;
    }
}