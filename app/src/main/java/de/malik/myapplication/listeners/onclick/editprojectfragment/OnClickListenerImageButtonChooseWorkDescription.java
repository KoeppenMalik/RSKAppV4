package de.malik.myapplication.listeners.onclick.editprojectfragment;

import android.app.Dialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import de.malik.myapplication.R;
import de.malik.myapplication.util.RSKSystem;

public class OnClickListenerImageButtonChooseWorkDescription implements View.OnClickListener {

    private RSKSystem system;
    private EditText editTextWorkDescription;
    private Spinner spinner;
    private Button buttonCancel, buttonFinish;

    public OnClickListenerImageButtonChooseWorkDescription(RSKSystem system, EditText editTextWorkDescription) {
        this.system = system;
        this.editTextWorkDescription = editTextWorkDescription;
    }

    @Override
    public void onClick(View viewParam) {
        Dialog dialog = new Dialog(system.getMain().getDialogContext());
        dialog.setContentView(R.layout.select_project_data_dialog);
        dialog.setTitle("Erstellen");
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);

        spinner = dialog.findViewById(R.id.spinnerData);
        buttonCancel = dialog.findViewById(R.id.buttonCancel);
        buttonFinish = dialog.findViewById(R.id.buttonFinish);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(system.getContext(), R.layout.spinner_item);
        adapter.addAll(system.getProjectManager().getSavedWorkDescriptions());
        spinner.setAdapter(adapter);
        spinner.setSelection(system.getProjectManager().getPositionOf(editTextWorkDescription.getText().toString(), system.getProjectManager().getSavedWorkDescriptions()));
        setListeners(dialog);
        dialog.create();
        dialog.show();
    }

    private void setListeners(Dialog dialog) {
        buttonCancel.setOnClickListener((view) -> dialog.dismiss());
        buttonFinish.setOnClickListener((view) -> {
            editTextWorkDescription.setText(system.getProjectManager().getSavedWorkDescriptions().get(spinner.getSelectedItemPosition()));
            dialog.dismiss();
        });
    }
}
