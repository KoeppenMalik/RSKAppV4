package de.malik.myapplication.util.recyclerviews.customernames;

import android.app.Dialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import de.malik.myapplication.R;
import de.malik.myapplication.gui.fragments.ProjectNamesFragment;
import de.malik.myapplication.util.RSKSystem;

public class OnClickListenerEditSavedProjectName implements View.OnClickListener {

    private RSKSystem system;
    private int index;

    public OnClickListenerEditSavedProjectName(RSKSystem system, int index) {
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

        EditText editTextProjectName = dialog.findViewById(R.id.editTextData);
        editTextProjectName.setText(system.getProjectManager().getSavedCustomerNames().get(index));
        Button buttonFinish = dialog.findViewById(R.id.buttonFinish),
                buttonCancel = dialog.findViewById(R.id.buttonCancel);

        buttonCancel.setOnClickListener((view) -> dialog.dismiss());
        buttonFinish.setOnClickListener((view) -> {
            String projectName = editTextProjectName.getText().toString();
            if (projectName.isEmpty()) {
                system.makeToast("Projektname darf nicht leer sein");
                return;
            }
            system.getProjectManager().getSavedCustomerNames().set(index, projectName);
            system.saveData();
            dialog.dismiss();
            system.replaceCurrentFragmentWith(new ProjectNamesFragment(system), RSKSystem.NO_ANIM);
        });
        dialog.create();
        dialog.show();
    }
}
