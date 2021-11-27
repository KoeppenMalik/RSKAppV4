package de.malik.myapplication.util.recyclerviews.workdescriptions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.malik.myapplication.R;
import de.malik.myapplication.util.RSKSystem;

public class RecyclerAdapterWorkDescriptions extends RecyclerView.Adapter<ViewHolderWorkDescriptions> {

    private View v;
    private ArrayList<String> savedWorkDescriptions;
    private RSKSystem system;

    public RecyclerAdapterWorkDescriptions(RSKSystem system) {
        this.system = system;
        savedWorkDescriptions = system.getProjectManager().getSavedWorkDescriptions();
    }

    @NonNull
    @Override
    public ViewHolderWorkDescriptions onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        v = inflater.inflate(R.layout.viewholder_saved_customer_data_recyclerview, parent, false);
        return new ViewHolderWorkDescriptions(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderWorkDescriptions holder, int position) {
        holder.setIsRecyclable(false);
        holder.getTextViewData().setText(savedWorkDescriptions.get(position));
        v.setOnClickListener(new OnClickListenerEditSavedWorkDescription(system, position));
    }

    @Override
    public int getItemCount() {
        return savedWorkDescriptions.size();
    }

    public void setSavedWorkDescriptions(ArrayList<String> savedWorkDescriptions) {
        this.savedWorkDescriptions = savedWorkDescriptions;
    }
}
