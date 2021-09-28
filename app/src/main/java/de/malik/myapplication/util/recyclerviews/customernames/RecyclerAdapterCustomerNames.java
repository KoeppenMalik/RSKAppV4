package de.malik.myapplication.util.recyclerviews.customernames;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.malik.myapplication.R;
import de.malik.myapplication.util.RSKSystem;

public class RecyclerAdapterCustomerNames extends RecyclerView.Adapter<ViewHolderCustomerNames> {

    private ArrayList<String> customerNames;
    private View v;
    private RSKSystem system;

    public RecyclerAdapterCustomerNames(RSKSystem system) {
        customerNames = system.getProjectManager().getSavedCustomerNames();
        this.system = system;
    }

    @NonNull
    @Override
    public ViewHolderCustomerNames onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        v = inflater.inflate(R.layout.viewholder_saved_customer_data_recyclerview, parent, false);
        return new ViewHolderCustomerNames(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCustomerNames holder, int position) {
        holder.setIsRecyclable(false);
        holder.getTextViewData().setText(customerNames.get(position));
        v.setOnClickListener(new OnClickListenerEditSavedProjectName(system, position));
    }

    @Override
    public int getItemCount() {
        return customerNames.size();
    }

    public void setCustomerNames(ArrayList<String> customerNames) {
        this.customerNames = customerNames;
    }
}
