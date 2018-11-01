
package com.master_vision.trfihi.databinding;

public class AppDataBindingComponent implements android.databinding.DataBindingComponent {


    @Override
    public RecyclerViewDataBinding getRecyclerViewDataBinding() {
        return new RecyclerViewDataBinding();
    }

}
