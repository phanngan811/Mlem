package com.example.mlem.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.databinding.ItemTagChipBinding;

import java.util.ArrayList;
import java.util.List;

public class TagChipRVAdapter extends RecyclerView.Adapter<TagChipRVAdapter.ChipViewHolder> {

    private List<String> chips;

    public TagChipRVAdapter() {
        chips = new ArrayList<>();
    }

    public void setChips(List<String> chips) {
        this.chips = chips;
        notifyDataSetChanged();
    }

    public List<String> getChips() {
        return chips;
    }

    public void removeChip(String name) {
        int size = getItemCount();
        for (int i = 0; i < size; i++) {
            if (chips.get(i).equals(name)) {
                chips.remove(i);
                notifyDataSetChanged();
                return;
            }
        }
    }

    @NonNull
    @Override
    public ChipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTagChipBinding binding = ItemTagChipBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ChipViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChipViewHolder holder, int position) {
        String chip = chips.get(position);
        if (chip == null) return;
        holder.binding.chip.setText(chip);
        holder.binding.chip.setOnClickListener(view -> removeChip(chip));
    }

    @Override
    public int getItemCount() {
        return chips.size();
    }

    public static class ChipViewHolder extends RecyclerView.ViewHolder {
        private final ItemTagChipBinding binding;

        public ChipViewHolder(@NonNull ItemTagChipBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}