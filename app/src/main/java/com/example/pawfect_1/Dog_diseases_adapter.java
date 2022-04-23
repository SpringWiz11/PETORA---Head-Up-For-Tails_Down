package com.example.pawfect_1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Dog_diseases_adapter extends RecyclerView.Adapter<Dog_diseases_adapter.ViewHolder> {
    public final String TAG = "TAG";
    LayoutInflater inflator;
    List<String>  causes, treatment;
    static List<String> diseases;
    private List<String> list;
    LinearLayout cardView;

    public Dog_diseases_adapter(Context context, int dog_diseases_layout, List<String> treatment, List<String> causes, List<String> diseases) {
        Log.d(TAG, "Adapter : " + diseases);
        this.inflator = LayoutInflater.from(context);
        this.diseases = diseases;
        this.causes = causes;
        this.treatment = treatment;
        list = new ArrayList<>(causes);
    }

//    public void setFilteredList(List<String> filteredList)
//    {
//        diseases = filteredList;
//        notifyDataSetChanged();
//
//    }
    @Override
    public int getItemCount() {
        return diseases.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.dog_diseases_layout, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        String title = diseases.get(position);
        String desc = causes.get(position);
        String treat = treatment.get(position);
        holder.diseases.setText(title);
        holder.causes.setText(desc);
        holder.treatment.setText(treat);
    }

//    @Override
//    public Filter getFilter() {
//        return FilterUser;
//    }
//    private Filter FilterUser = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence charSequence) {
//            String searchText = charSequence.toString().toLowerCase();
//            List<String> templist = new ArrayList<>();
//            if (searchText.length() == 0 || searchText.isEmpty()){
//                templist.addAll(list);
//            }
//            else {
//                for(String item1 : list){
//                    if (item1.toLowerCase().contains(searchText)){
//                        templist.add(item1);
//                    }
//                }
//            }
//            FilterResults filterResults = new FilterResults();
//            filterResults.values = templist;
//            return filterResults;
//        }
//
//        @Override
//        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//            causes.clear();
//            causes.addAll((Collection<? extends String>) filterResults.values);
//        }
//    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView diseases, causes, treatment;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            diseases = itemView.findViewById(R.id.diseases);
            causes = itemView.findViewById(R.id.causes);
            treatment = itemView.findViewById(R.id.treatment);
        }
    }
}



