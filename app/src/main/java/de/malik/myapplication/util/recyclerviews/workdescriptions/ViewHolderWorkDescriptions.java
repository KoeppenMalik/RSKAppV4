package de.malik.myapplication.util.recyclerviews.workdescriptions;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.malik.myapplication.R;

public class ViewHolderWorkDescriptions extends RecyclerView.ViewHolder {

    TextView textViewData;

    public ViewHolderWorkDescriptions(@NonNull View itemView) {
        super(itemView);
        createComponents(itemView);
    }

    private void createComponents(View v) {
        textViewData = v.findViewById(R.id.editTextWorkDescription);
    }

    public TextView getTextViewData() {
        return textViewData;
    }
}