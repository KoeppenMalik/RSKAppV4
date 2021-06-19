// Created on 30.01.2021, 18:59

package de.malik.myapplication.util.recyclerviews.projects;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import de.malik.myapplication.R;

public class ViewHolderProjects extends RecyclerView.ViewHolder {

    private TextView textViewName, textViewWorkDescription, textViewDate, textViewTotalTime, textViewTimes;

    public ViewHolderProjects(View itemView) {
        super(itemView);
        handleGui(itemView);
    }

    private void handleGui(View v) {
        textViewName = v.findViewById(R.id.textViewAppName);
        textViewWorkDescription = v.findViewById(R.id.textViewWorkDescription);
        textViewDate = v.findViewById(R.id.textViewDate);
        textViewTotalTime = v.findViewById(R.id.textViewTotalTime);
        textViewTimes = v.findViewById(R.id.textViewTimes);
    }

    public TextView getTextViewName() {
        return textViewName;
    }

    public TextView getTextViewWorkDescription() {
        return textViewWorkDescription;
    }

    public TextView getTextViewDate() {
        return textViewDate;
    }

    public TextView getTextViewTotalTime() {
        return textViewTotalTime;
    }

    public TextView getTextViewTimes() {
        return textViewTimes;
    }
}
