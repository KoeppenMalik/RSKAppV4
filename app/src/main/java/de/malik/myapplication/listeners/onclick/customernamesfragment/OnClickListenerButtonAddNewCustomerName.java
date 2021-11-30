package de.malik.myapplication.listeners.onclick.customernamesfragment;

import android.app.Dialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.ProjectNamesFragment;
import de.malik.myapplication.util.RSKSystem;

public class OnClickListenerButtonAddNewCustomerName implements View.OnClickListener {

    private RSKSystem system;

    public OnClickListenerButtonAddNewCustomerName(RSKSystem system) {
        this.system = system;
    }

    @Override
    public void onClick(View paramView) {
        Dialog dialog = new Dialog(system.getMainActivity().getDialogContext());
        dialog.setContentView(R.layout.add_customer_name_dialog);
        dialog.setTitle("Erstellen");
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);

        EditText editTextCustomerName = dialog.findViewById(R.id.editTextWorkDescription);
        Button buttonFinish = dialog.findViewById(R.id.buttonFinish),
                buttonCancel = dialog.findViewById(R.id.buttonCancel);

        buttonCancel.setOnClickListener((view) -> dialog.dismiss());
        buttonFinish.setOnClickListener((view) -> {
            String customerName = editTextCustomerName.getText().toString();
            if (customerName.isEmpty()) {
                system.makeToast("Name darf nicht leer sein");
                return;
            }
            system.getProjectManager().getSavedCustomerNames().add(customerName);
            system.saveData();
            dialog.dismiss();
            system.replaceCurrentFragmentWith(new ProjectNamesFragment(system), RSKSystem.NO_ANIM);
        });
        dialog.create();
        dialog.show();
    }
}
