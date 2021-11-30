// Created on 31.01.2021, 15:31

package de.malik.myapplication.util.recyclerviews.pauses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import de.malik.myapplication.R;
import de.malik.myapplication.util.projectmanagement.Pause;

import java.util.ArrayList;

public class RecyclerAdapterPauses extends RecyclerView.Adapter<ViewHolderPauses> {

    private ArrayList<Pause> pauses;

    public RecyclerAdapterPauses(ArrayList<Pause> pauses) {
        this.pauses = pauses;
    }

    @Override
    public ViewHolderPauses onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.viewholder_pause_recyclerview, parent, false);
        return new ViewHolderPauses(v);
    }

    @Override
    public void onBindViewHolder(ViewHolderPauses holder, int position) {
        holder.setIsRecyclable(false);
        Pause pause = pauses.get(position);
        if (pause.getTime().getHours() == 0) {
            holder.getTextViewPauseTime().setText(pause.getTime().getMinutes() + " Min");
        }
        else holder.getTextViewPauseTime().setText(pause.getTime().getHours() + " Std " + pause.getTime().getMinutes() + " Min");
    }

    @Override
    public int getItemCount() {
        return pauses.size();
    }
}
