package com.example.mlem.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.databinding.ItemTagChipBinding;

import java.util.ArrayList;
import java.util.List;

public class TagChipRVAdapter extends RecyclerView.Adapter<TagChipRVAdapter.ChipViewHolder> {

    private final MutableLiveData<List<String>> chips;

    public TagChipRVAdapter() {
        chips = new MutableLiveData<>(new ArrayList<>());
    }

    public LiveData<List<String>> getChips() {
        return chips;
    }

    public void setChips(List<String> chips) {
        this.chips.setValue(chips);
        notifyDataSetChanged();
    }

    public void addChip(String chip) {
        List<String> tmpChips = chips.getValue();
        if (tmpChips == null) return;
        tmpChips.add(chip);
        chips.setValue(tmpChips);
        notifyDataSetChanged();
    }

    public void removeChip(String name) {
        List<String> tmpChips = chips.getValue();
        if (tmpChips == null) return;
        int size = getItemCount();
        for (int i = 0; i < size; i++) {
            if (tmpChips.get(i).equals(name)) {
                tmpChips.remove(i);
                chips.setValue(tmpChips);
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
        if (chips.getValue() == null) return;
        String chip = chips.getValue().get(position);
        if (chip == null) return;
        holder.binding.chip.setText(chip);
        holder.binding.chip.setOnClickListener(view -> removeChip(chip));
    }

    @Override
    public int getItemCount() {
        if (chips.getValue() == null) return 0;
        return chips.getValue().size();
    }

    public static class ChipViewHolder extends RecyclerView.ViewHolder {
        private final ItemTagChipBinding binding;

        public ChipViewHolder(@NonNull ItemTagChipBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}