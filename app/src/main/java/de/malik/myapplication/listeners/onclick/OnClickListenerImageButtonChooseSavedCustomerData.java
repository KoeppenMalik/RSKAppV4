package de.malik.myapplication.listeners.onclick;

import android.app.Dialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import de.malik.myapplication.R;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.projectmanagement.CustomerData;

public class OnClickListenerImageButtonChooseSavedCustomerData implements View.OnClickListener {

    private RSKSystem system;
    private EditText targetEditText;
    private Button buttonCancel, buttonFinish;
    private Spinner spinner;
    private CustomerData customerData;

    public OnClickListenerImageButtonChooseSavedCustomerData(RSKSystem system, EditText targetEditText, @NonNull CustomerData data) {
        this.system = system;
        this.targetEditText = targetEditText;
        customerData = data;
    }

    @Override
    public void onClick(View v) {
        ArrayList<String> data;
        if (customerData == CustomerData.CHOOSE_SAVED_NAMES)
            data = system.getProjectManager().getSavedCustomerNames();
        else
            data = system.getProjectManager().getSavedWorkDescriptions();

        if (data.size() == 0) {
            system.makeToast("Noch keine gespeicherten Daten");
            return;
        }
        Dialog dialog = new Dialog(system.getMainActivity().getDialogContext());
        dialog.setContentView(R.layout.select_project_data_dialog);
        dialog.setTitle("Wählen");
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);

        spinner = dialog.findViewById(R.id.spinnerData);
        buttonCancel = dialog.findViewById(R.id.buttonCancel);
        buttonFinish = dialog.findViewById(R.id.buttonFinish);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(system.getContext(), R.layout.spinner_item);
        adapter.addAll(data);
        spinner.setAdapter(adapter);
        spinner.setSelection(system.getProjectManager().getPositionOf(targetEditText.getText().toString(), data));
        setListeners(dialog, data);
        dialog.create();
        dialog.show();
    }

    private void setListeners(Dialog dialog, ArrayList<String> data) {
        buttonCancel.setOnClickListener((view) -> dialog.dismiss());
        buttonFinish.setOnClickListener((view) -> {
            targetEditText.setText(data.get(spinner.getSelectedItemPosition()));
            dialog.dismiss();
        });
    }
}
