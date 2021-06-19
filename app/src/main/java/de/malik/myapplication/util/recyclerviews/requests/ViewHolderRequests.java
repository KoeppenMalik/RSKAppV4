// Created on 02.03.2021, 19:39

package de.malik.myapplication.util.recyclerviews.requests;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import de.malik.myapplication.R;

public class ViewHolderRequests extends RecyclerView.ViewHolder {

    private TextView textViewName, textViewDescription, textViewDate;

    public ViewHolderRequests(View itemView) {
        super(itemView);
        handleGui(itemView);
    }

    private void handleGui(View v) {
        textViewName = v.findViewById(R.id.textViewName);
        textViewDescription = v.findViewById(R.id.textViewDescription);
        textViewDate = v.findViewById(R.id.textViewDate);
    }

    public TextView getTextViewName() {
        return textViewName;
    }

    public TextView getTextViewDescription() {
        return textViewDescription;
    }

    public TextView getTextViewDate() {
        return textViewDate;
    }
}
