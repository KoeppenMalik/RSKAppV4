package de.malik.myapplication.gui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.InfoFragment;
import de.malik.myapplication.gui.fragments.OverviewFragment;
import de.malik.myapplication.listeners.onnavigationitemselected.OnNavigationItemSelectedListenerBottomNav;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.ProjectManager;
import de.malik.myapplication.util.filemanagement.RSKFileManager;
import de.malik.myapplication.util.filemanagement.Printer;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "MainActivity";
    public static final String FOLDER_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();

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
        dialogContext = MainActivity.this;
        takePermissions();
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"" + getColor(android.R.color.white) + "\">" + getString(R.string.title) + "</font>"));
        RSKFileManager fileManager = new RSKFileManager(new Printer());
        ProjectManager projectManager = new ProjectManager();
        system = new RSKSystem(this, projectManager, fileManager);
        system.replaceCurrentFragmentWith(currentFragment = new OverviewFragment(system), RSKSystem.NO_ANIM);
        handleGui();
    }

    private void takePermissions() {
        if (hasStoragePermissions()) {
            Log.i(LOG_TAG, "Permissions Granted");
        }
        else {
            requestStoragePermissions();
        }
    }

    private void requestStoragePermissions() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageName())));
                startActivityForResult(intent, 100);
            } catch (Exception ex) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 100);
            }
        }
        else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
        }
    }

    private boolean hasStoragePermissions() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        }
        else {
            return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
                    if (Environment.isExternalStorageManager()) {
                        Log.i(LOG_TAG, "Permissions Granted: Android 11");
                    }
                    else {
                        requestStoragePermissions();
                    }
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            if (requestCode == 101) {
                boolean readExternalStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (readExternalStorage) {
                    Log.i(LOG_TAG, "Permissions Granted: Android 10 and below");
                }
                else {
                    requestStoragePermissions();
                }
            }
        }
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
