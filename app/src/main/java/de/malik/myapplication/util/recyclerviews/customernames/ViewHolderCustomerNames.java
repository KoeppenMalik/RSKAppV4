package de.malik.myapplication.util.recyclerviews.customernames;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.malik.myapplication.R;

public class ViewHolderCustomerNames extends RecyclerView.ViewHolder {

    private TextView textViewData;

    public ViewHolderCustomerNames(@NonNull View itemView) {
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