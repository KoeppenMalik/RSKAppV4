package de.malik.myapplication.util.recyclerviews.workdescriptions;

import android.app.Dialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.WorkDescriptionsFragment;
import de.malik.myapplication.util.RSKSystem;

public class OnClickListenerEditSavedWorkDescription implements View.OnClickListener {

    private RSKSystem system;
    private int index;

    public OnClickListenerEditSavedWorkDescription(RSKSystem system, int index) {
        this.system = system;
        this.index = index;
    }

    @Override
    public void onClick(View viewParam) {
        Dialog dialog = new Dialog(system.getMainActivity().getDialogContext());
        dialog.setContentView(R.layout.edit_saved_project_data);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);

        EditText editTextWorkDescription = dialog.findViewById(R.id.editTextData);
        editTextWorkDescription.setText(system.getProjectManager().getSavedWorkDescriptions().get(index));
        Button buttonFinish = dialog.findViewById(R.id.buttonFinish),
                buttonCancel = dialog.findViewById(R.id.buttonCancel);

        buttonCancel.setOnClickListener((view) -> dialog.dismiss());
        buttonFinish.setOnClickListener((view) -> {
            String workDescription = editTextWorkDescription.getText().toString();
            if (workDescription.isEmpty()) {
                system.makeToast("Arbeitsbeschreibung darf nicht leer sein");
                return;
            }
            system.getProjectManager().getSavedWorkDescriptions().set(index, workDescription);
            system.saveData();
            dialog.dismiss();
            system.replaceCurrentFragmentWith(new WorkDescriptionsFragment(system), RSKSystem.NO_ANIM);
        });
        dialog.create();
        dialog.show();
    }
}
