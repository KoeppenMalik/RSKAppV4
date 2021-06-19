package de.malik.myapplication.listeners.onclick.editprojectfragment;

import android.app.Dialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.CustomerNamesFragment;
import de.malik.myapplication.util.RSKSystem;

public class OnClickListenerImageButtonChooseName implements View.OnClickListener {

    private RSKSystem system;
    private EditText editTextCustomerName;
    private Button buttonCancel, buttonFinish;
    private Spinner spinner;

    public OnClickListenerImageButtonChooseName(RSKSystem system, EditText editTextCustomerName) {
        this.system = system;
        this.editTextCustomerName = editTextCustomerName;
    }

    @Override
    public void onClick(View v) {
        Dialog dialog = new Dialog(system.getMain().getDialogContext());
        dialog.setContentView(R.layout.choose_customer_name_dialog);
        dialog.setTitle("Erstellen");
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);

        spinner = dialog.findViewById(R.id.spinnerCustomerNames);
        buttonCancel = dialog.findViewById(R.id.buttonCancel);
        buttonFinish = dialog.findViewById(R.id.buttonFinish);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(system.getContext(), R.layout.spinner_item);
        adapter.addAll(system.getProjectManager().getSavedCustomerNames());
        spinner.setAdapter(adapter);
        spinner.setSelection(system.getProjectManager().getPositionOf(editTextCustomerName.getText().toString(), system.getProjectManager().getSavedCustomerNames()));
        setListeners(dialog);
        dialog.create();
        dialog.show();
    }

    private void setListeners(Dialog dialog) {
        buttonCancel.setOnClickListener((view) -> dialog.dismiss());
        buttonFinish.setOnClickListener((view) -> {
            editTextCustomerName.setText(system.getProjectManager().getSavedCustomerNames().get(spinner.getSelectedItemPosition()));
            dialog.dismiss();
        });
    }
}
