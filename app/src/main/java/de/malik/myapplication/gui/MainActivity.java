package de.malik.myapplication.gui;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
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
import android.view.WindowManager;
import android.widget.Button;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

    public static final String APP_VERSION = "5.0.0";

    public static final String TAG = MainActivity.class.getName();

    public static final int STORAGE_PERMISSIONS_REQUEST_CODE_API_R = 1;
    public static final int STORAGE_PERMISSIONS_REQUEST_CODE_API_BELOW_R = 2;

    private RSKSystem system;
    private BottomNavigationView bottomNav;
    private MenuItem infoItem;
    private Context dialogContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        askForStoragePermissions();
        if (!checkStoragePermissions())
            showPermissionsInfoDialog();
        dialogContext = MainActivity.this;
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"" + getColor(android.R.color.white) + "\">" + getString(R.string.title) + "</font>"));
        RSKFileManager fileManager = new RSKFileManager(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(), new Printer());
        ProjectManager projectManager = new ProjectManager();
        system = new RSKSystem(this, projectManager, fileManager);
        system.replaceCurrentFragmentWith(new OverviewFragment(system), RSKSystem.NO_ANIM);
        handleGui();
    }

    public void askForStoragePermissions() {
        if (checkStoragePermissions()) {
            Log.i(TAG, "Permissions Granted");
        }
        else requestStoragePermissions();
    }

    public boolean checkStoragePermissions() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        }
        else {
            return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void requestStoragePermissions() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageName())));
                startActivityForResult(intent, STORAGE_PERMISSIONS_REQUEST_CODE_API_R);
            } catch (Exception ex) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, STORAGE_PERMISSIONS_REQUEST_CODE_API_R);
            }
        }
        else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE_API_BELOW_R);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == STORAGE_PERMISSIONS_REQUEST_CODE_API_R) {
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
                    if (Environment.isExternalStorageManager()) {
                        Log.i(TAG, "Permissions Granted: Android 11");
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
            if (requestCode == STORAGE_PERMISSIONS_REQUEST_CODE_API_BELOW_R) {
                boolean readExternalStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (readExternalStorage) {
                    Log.i(TAG, "Permissions Granted: Android 10 and below");
                }
                else requestStoragePermissions();
            }
        }
    }

    private void showPermissionsInfoDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.permissions_dialog_layout);
        dialog.setTitle("Berechtigungen");
        dialog.setCancelable(false);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);

        Button posButton = dialog.findViewById(R.id.posButton);
        posButton.setOnClickListener(v -> finishAndRemoveTask());

        dialog.create();
        dialog.show();
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
        bottomNav.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListenerBottomNav(system));
    }

    MenuItem.OnMenuItemClickListener menuItemOnClickListener = (menuItem) -> {
        system.replaceCurrentFragmentWith(new InfoFragment(system), R.anim.slide_down);
        return true;
    };

    public Context getDialogContext() {
        return dialogContext;
    }

    public BottomNavigationView getBottomNav() {
        return bottomNav;
    }
}
