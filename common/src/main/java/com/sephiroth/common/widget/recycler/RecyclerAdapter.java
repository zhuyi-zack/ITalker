package com.sephiroth.common.widget.recycler;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sephiroth.common.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Sephiroth on 2019/3/13.
 */

public abstract class RecyclerAdapter<Data>
        extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder<Data>>
        implements View.OnClickListener, View.OnLongClickListener, AdapterCallback<Data> {

    private final List<Data> mDataList;
    private onClickListener<Data> mClickListener;

    public RecyclerAdapter(ArrayList<Data> dataList) {
        mDataList = dataList;
    }

    @Override
    public int getItemViewType(int position) {
        return getItemViewType(position, mDataList.get(position));
    }

    /**
     * 获取条目类型
     *
     * @param position 条目的坐标
     * @param data     当前条目数据
     * @return 约定xml文件的资源id作为itemViewType
     */
    @LayoutRes
    protected abstract int getItemViewType(int position, Data data);

    /**
     * 创建ViewHolder
     *
     * @param parent   RecyclerVeiw
     * @param viewType 布局的类型，自定义指定一个int类型的变量，这里约定为布局文件的资源id
     * @return RecyclerViewHolder
     */
    @NonNull
    @Override
    public RecyclerViewHolder<Data> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rootView = inflater.inflate(viewType, parent, false);
        RecyclerViewHolder holder = onCreateViewHolder(rootView, viewType);
        holder.mCallback = this;
        rootView.setTag(R.id.tag_recylcer_holder, holder);
        rootView.setOnClickListener(this);
        rootView.setOnLongClickListener(this);
        holder.mUnbinder = ButterKnife.bind(holder, rootView);
        return holder;
    }

    /**
     * 创建ViewHolder，子类必须复写
     *
     * @param rootVeiew 根布局
     * @param viewType  其实就是xml布局文件的id
     * @return ViewHolder
     */
    protected abstract RecyclerViewHolder<Data> onCreateViewHolder(View rootVeiew, int viewType);

    /**
     * 绑定ViewHolder
     *
     * @param holder   ViewHolder
     * @param position 条目的坐标(位置)
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder<Data> holder, int position) {
        Data data = mDataList.get(position);
        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return (mDataList != null && mDataList.size() > 0) ? mDataList.size() : 0;
    }

    public void setOnClickListener(onClickListener<Data> listener) {
        mClickListener = listener;
    }

    public interface onClickListener<Data> {
        void onItemClick(RecyclerViewHolder<Data> holder, Data data);

        void onItemLongClick(RecyclerViewHolder<Data> holder, Data data);
    }

    @Override
    public void onClick(View v) {
        if (mClickListener != null) {
            RecyclerViewHolder viewHolder = (RecyclerViewHolder) v.getTag(R.id.tag_recylcer_holder);
            int position = viewHolder.getAdapterPosition();
            Data data = mDataList.get(position);
            mClickListener.onItemClick(viewHolder, data);
        }
    }

    /**
     * 向当前RecyclerView的末尾添加一条数据
     *
     * @param data item的数据
     */
    public void add(Data data) {
        if (data == null) {
            return;
        }
        mDataList.add(data);
        notifyItemChanged(mDataList.size() - 1);
    }

    public void add(Data... data) {
        if (data == null) {
            return;
        }
        Collections.addAll(mDataList, data);
        int positionStart = mDataList.size();
        notifyItemRangeChanged(positionStart, data.length);
    }

    @Override
    public boolean onLongClick(View v) {
        if (mClickListener != null) {
            RecyclerViewHolder viewHolder = (RecyclerViewHolder) v.getTag(R.id.tag_recylcer_holder);
            int position = viewHolder.getAdapterPosition();
            Data data = mDataList.get(position);
            mClickListener.onItemLongClick(viewHolder, data);
            return true;
        }
        return false;
    }

    public static abstract class RecyclerViewHolder<Data> extends RecyclerView.ViewHolder {
        protected Data mData;
        private Unbinder mUnbinder;
        private AdapterCallback<Data> mCallback;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
        }

        void bind(Data data) {
            mData = data;
            onBind(mData);
        }

        protected abstract void onBind(Data data);

        public void onUpdata(Data data) {
            if (mCallback != null) {
                mCallback.updata(data, this);
            }
        }
    }
}
