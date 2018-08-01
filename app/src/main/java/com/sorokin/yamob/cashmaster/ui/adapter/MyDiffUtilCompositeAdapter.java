package com.sorokin.yamob.cashmaster.ui.adapter;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.example.delegateadapter.delegate.IDelegateAdapter;
import com.example.delegateadapter.delegate.diff.DiffUtilCompositeAdapter;
import com.example.delegateadapter.delegate.diff.IComparableItem;

public class MyDiffUtilCompositeAdapter extends DiffUtilCompositeAdapter {
    private MyDiffUtilCompositeAdapter(@NonNull SparseArray<IDelegateAdapter> typeToAdapterMap) {
        super(typeToAdapterMap);
    }

    public IComparableItem getItem(int pos){
        return data.get(pos);
    }

    public static class Builder {
        private int count;
        private final SparseArray<IDelegateAdapter> typeToAdapterMap = new SparseArray();

        public Builder() {
        }

        public Builder add(@NonNull IDelegateAdapter<?, ? extends IComparableItem> delegateAdapter) {
            this.typeToAdapterMap.put(this.count++, delegateAdapter);
            return this;
        }

        public MyDiffUtilCompositeAdapter build() {
            if (this.count == 0) {
                throw new IllegalArgumentException("Register at least one adapter");
            } else {
                return new MyDiffUtilCompositeAdapter(this.typeToAdapterMap);
            }
        }
    }


}
