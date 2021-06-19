// Created on 31.01.2021, 15:32

package de.malik.myapplication.util.recyclerviews.pauses;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import de.malik.myapplication.R;

public class ViewHolderPauses extends RecyclerView.ViewHolder {

    private TextView textViewPauseTime;

    public ViewHolderPauses(View itemView) {
        super(itemView);
        handleGui(itemView);
    }

    private void handleGui(View v) {
        textViewPauseTime = v.findViewById(R.id.textViewPauseTime);
    }

    public TextView getTextViewPauseTime() {
        return textViewPauseTime;
    }
}
