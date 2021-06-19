package de.malik.myapplication.util.recyclerviews.customernames;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.malik.myapplication.R;
import de.malik.myapplication.util.RSKSystem;

public class RecyclerAdapterCustomerNames extends RecyclerView.Adapter<ViewHolderCustomerNames> {

    private ArrayList<String> customerNames;

    public RecyclerAdapterCustomerNames(RSKSystem system) {
        customerNames = system.getProjectManager().getSavedCustomerNames();
    }

    @NonNull
    @Override
    public ViewHolderCustomerNames onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolderCustomerNames(inflater.inflate(R.layout.viewholder_saved_customer_data_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCustomerNames holder, int position) {
        holder.setIsRecyclable(false);
        holder.getTextViewData().setText(customerNames.get(position));
    }

    @Override
    public int getItemCount() {
        return customerNames.size();
    }

    public void setCustomerNames(ArrayList<String> customerNames) {
        this.customerNames = customerNames;
    }
}
