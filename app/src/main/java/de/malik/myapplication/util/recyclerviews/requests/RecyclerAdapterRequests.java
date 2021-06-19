// Created on 02.03.2021, 19:36

package de.malik.myapplication.util.recyclerviews.requests;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import de.malik.myapplication.R;
import de.malik.myapplication.util.RSKSystem;
import de.malik.myapplication.util.customermanagement.Request;

import java.util.ArrayList;

public class RecyclerAdapterRequests extends RecyclerView.Adapter<ViewHolderRequests> {

    private ArrayList<Request> requests;
    private View v;
    private RSKSystem system;

    public RecyclerAdapterRequests(ArrayList<Request> requests, RSKSystem system) {
        this.requests = requests;
        this.system = system;
    }

    @Override
    public ViewHolderRequests onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        v = inflater.inflate(R.layout.viewholder_requests_recyclerview, parent, false);
        return new ViewHolderRequests(v);
    }



    @Override
    public void onBindViewHolder(ViewHolderRequests holder, int position) {
        holder.setIsRecyclable(false);
        Request request = requests.get(holder.getAdapterPosition());
        holder.getTextViewName().setText(request.getName());
        holder.getTextViewDate().setText(request.getDate());
        holder.getTextViewDescription().setText(request.getDescription());
        v.setOnClickListener(new OnClickListenerViewHolderRequests(system, request));
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }
}
