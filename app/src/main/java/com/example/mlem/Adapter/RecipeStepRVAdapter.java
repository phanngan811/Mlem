package com.example.mlem.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.databinding.ItemRecipeStepBinding;

import java.util.ArrayList;
import java.util.List;

public class RecipeStepRVAdapter extends RecyclerView.Adapter<RecipeStepRVAdapter.StepViewHolder> {

    private List<String> steps;

    public RecipeStepRVAdapter() {
        steps = new ArrayList<>();
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecipeStepBinding itemRecipeStepBinding = ItemRecipeStepBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new StepViewHolder(itemRecipeStepBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        String step = steps.get(position);
        if (step == null) {
            return;
        }
        holder.binding.tvStep.setText(String.valueOf(position + 1));
        holder.binding.tvContent.setText(step);
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public static class StepViewHolder extends RecyclerView.ViewHolder {
        private final ItemRecipeStepBinding binding;

        public StepViewHolder(@NonNull ItemRecipeStepBinding itemRecipeStepBinding) {
            super(itemRecipeStepBinding.getRoot());
            this.binding = itemRecipeStepBinding;
        }
    }
}
