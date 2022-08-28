package com.dynamic.myapplication.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.Volatile;


/**
 * @version ：
 * @description ：结合dataBinding的RecyclerView Adapter
 * @data ：2022/8/27
 * @auther ：Zengxiaoping
 */
public abstract class BaseDBRVAdapter<Data, DB extends ViewDataBinding> extends RecyclerView.Adapter<BaseDBRVHolder> {

    @Volatile
    protected List<Data> data;
    protected Context context;

    private int layoutId;
    private int variableId;
    private OnItemClickListener<Data> itemClickListener;
    private OnItemLongClickListener<Data> itemLongClickListener;

    /***
     * @param layoutId item的数据类型
     * @param variableId xml里面的variable属性
     */
    public BaseDBRVAdapter(@LayoutRes int layoutId, int variableId) {
        this.layoutId = layoutId;
        this.variableId = variableId;
        data = new ArrayList<>();
    }

    public BaseDBRVAdapter(List<Data> data, @LayoutRes int layoutId, int variableId) {
        this.data = data == null ? new ArrayList<Data>() : data;
        this.layoutId = layoutId;
        this.variableId = variableId;
    }


    @NonNull
    @Override
    public BaseDBRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        DB binding = DataBindingUtil.inflate(inflater, layoutId, parent, false);
        return new BaseDBRVHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull final BaseDBRVHolder holder, final int position) {
        DB binding = DataBindingUtil.getBinding(holder.itemView);
        final Data itemData = data.get(position);
        binding.setVariable(variableId, itemData);
        onBindData(itemData, holder, position);
        //迫使数据立即绑定
        binding.executePendingBindings();
        //设置点击事件
        if (itemClickListener != null) {
            holder.itemView.setOnClickListener(v -> itemClickListener.onItemClick(holder.itemView, itemData, position));
            holder.itemView.setOnLongClickListener(v -> {
                if (itemLongClickListener != null) {
                    itemLongClickListener.onItemLongClick(holder.itemView, itemData, position);
                }
                return true;
            });
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * 绑定数据
     */
    protected void onBindData(Data data, BaseDBRVHolder holder, int position) {
    }

    /**
     * 设置新数据
     *
     * @param data 数据
     */
    public void setNewData(List<Data> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param data 数据
     */
    public void addData(int position, Data data) {
        this.data.add(position, data);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, getItemCount() - position);
    }

    /**
     * 添加数据
     *
     * @param data 数据
     */
    public void addData(List<Data> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void removeDataAll() {
        this.data.clear();
        notifyDataSetChanged();
    }

    /**
     * 清空某个数据
     *
     * @param position 位置
     */
    public void removeData(int position) {
        if (position >= data.size()) {
            return;
        }
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size() - position);
    }

    /**
     * 设置Item 长按、点击事件
     */
    public void setOnItemListener(OnItemClickListener<Data> listener) {
        this.itemClickListener = listener;
    }

    public void setOnItemClongListener(OnItemLongClickListener<Data> listener) {
        this.itemLongClickListener = listener;
    }

    public List<Data> getData() {
        return data;
    }
}
