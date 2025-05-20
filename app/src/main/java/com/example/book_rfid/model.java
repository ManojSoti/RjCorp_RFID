package com.example.book_rfid;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class model {

private String name;

    public model(String name) {
        this.name = name;
    }

    public model() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        model model = (model) o;
        return Objects.equals(name, model.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }

    public static DiffUtil.ItemCallback<model> itemCallback=new DiffUtil.ItemCallback<model>() {
        @Override
        public boolean areItemsTheSame(@NonNull model oldItem, @NonNull model newItem) {
            return oldItem.equals(newItem);
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull model oldItem, @NonNull model newItem) {
            return oldItem.equals(newItem);
        }
    };

}
