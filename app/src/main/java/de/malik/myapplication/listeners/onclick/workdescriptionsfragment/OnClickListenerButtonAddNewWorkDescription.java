package de.malik.myapplication.listeners.onclick.workdescriptionsfragment;

import android.app.Dialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.WorkDescriptionsFragment;
import de.malik.myapplication.util.RSKSystem;

public class OnClickListenerButtonAddNewWorkDescription implements View.OnClickListener {

    private RSKSystem system;

    public OnClickListenerButtonAddNewWorkDescription(RSKSystem system) {
        this.system = system;
    }

    @Override
    public void onClick(View v) {
        Dialog dialog = new Dialog(system.getMain().getDialogContext());
        dialog.setContentView(R.layout.add_work_description_dialog);
        dialog.setTitle("Erstellen");
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);

        EditText editTextWorkDescription = dialog.findViewById(R.id.editTextWorkDescription);
        Button buttonFinish = dialog.findViewById(R.id.buttonFinish),
                buttonCancel = dialog.findViewById(R.id.buttonCancel);

        buttonCancel.setOnClickListener((view) -> dialog.dismiss());
        buttonFinish.setOnClickListener((view) -> {
            String workDescription = editTextWorkDescription.getText().toString();
            if (workDescription.isEmpty()) {
                system.makeShortToast("Arbeitsbeschreibung darf nicht leer sein");
                return;
            }
            system.getProjectManager().getSavedWorkDescriptions().add(workDescription);
            system.getFileManager().getPrinter().reprintFiles(system.getFileManager(), system.getProjectManager());
            dialog.dismiss();
            system.replaceCurrentFragmentWith(new WorkDescriptionsFragment(system), RSKSystem.NO_ANIM);
        });
        dialog.create();
        dialog.show();
    }
}
