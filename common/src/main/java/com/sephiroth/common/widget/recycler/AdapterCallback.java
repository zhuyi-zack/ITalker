package com.sephiroth.common.widget.recycler;

/**
 * @author Sephiroth on 2019/3/13.
 */

public interface AdapterCallback<Data> {
    void updata(Data data, RecyclerAdapter.RecyclerViewHolder holder);
}
