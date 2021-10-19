package de.malik.myapplication.gui;

import android.content.Context;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.InfoFragment;
import de.malik.myapplication.gui.fragments.OverviewFragment;
import de.malik.myapplication.listeners.onnavigationitemselected.OnNavigationItemSelectedListenerBottomNav;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Project;
import de.malik.myapplication.util.customermanagement.ProjectManager;
import de.malik.myapplication.util.filemanagement.RSKFileManager;
import de.malik.myapplication.util.filemanagement.Printer;

public class Main extends AppCompatActivity {

    private Fragment currentFragment;

    private RSKSystem system;
    private BottomNavigationView bottomNav;
    private SearchView searchView;
    private MenuItem infoItem;
    private Context dialogContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialogContext = Main.this;
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"" + getColor(android.R.color.white) + "\">" + getString(R.string.title) + "</font>"));
        RSKFileManager fileManager = new RSKFileManager(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getPath(), new Printer());
        ProjectManager projectManager = new ProjectManager();
        system = new RSKSystem(this, projectManager, fileManager);
        system.replaceCurrentFragmentWith(currentFragment = new OverviewFragment(system), RSKSystem.NO_ANIM);
        handleGui();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Schließen")
                .setMessage("Bist du dir sicher, dass du die App schließen willst?")
                .setPositiveButton("Ja", (dialog, which) -> finishAndRemoveTask()).setNegativeButton("Nein", null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.searchView);
        searchView = (SearchView) item.getActionView();
        infoItem = menu.findItem(R.id.infoTab);
        infoItem.setOnMenuItemClickListener(menuItemOnClickListener);
        return super.onCreateOptionsMenu(menu);
    }

    private void handleGui() {
        createComponents();
        setListeners();
    }

    private void createComponents() {
        bottomNav = findViewById(R.id.bottomNav);
    }

    private void setListeners() {
        bottomNav.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListenerBottomNav(system, currentFragment));
    }

    MenuItem.OnMenuItemClickListener menuItemOnClickListener = (menuItem) -> {
        system.replaceCurrentFragmentWith(new InfoFragment(system), R.anim.slide_down);
        return true;
    };

    public SearchView getSearchView() {
        return searchView;
    }

    public Context getDialogContext() {
        return dialogContext;
    }

    public BottomNavigationView getBottomNav() {
        return bottomNav;
    }
}
