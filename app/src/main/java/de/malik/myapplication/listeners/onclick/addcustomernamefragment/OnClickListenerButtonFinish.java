package de.malik.myapplication.listeners.onclick.addcustomernamefragment;

import android.view.View;
import android.widget.EditText;

import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.CustomerNamesFragment;
import de.malik.myapplication.util.RSKSystem;

public class OnClickListenerButtonFinish implements View.OnClickListener {

    private RSKSystem system;
    private EditText editTextCustomerName;

    public OnClickListenerButtonFinish(RSKSystem system, EditText editTextCustomerName) {
        this.system = system;
        this.editTextCustomerName = editTextCustomerName;
    }

    @Override
    public void onClick(View v) {
        String customerName = editTextCustomerName.getText().toString();
        if (!customerName.isEmpty()) {
            system.getProjectManager().getSavedCustomerNames().add(customerName);
            system.getFileManager().getPrinter().reprintFiles(system.getProjectManager());
            system.replaceCurrentFragmentWith(new CustomerNamesFragment(system), R.anim.slide_down);
        }
        else system.makeShortToast("Name darf nicht leer sein");
    }
}
