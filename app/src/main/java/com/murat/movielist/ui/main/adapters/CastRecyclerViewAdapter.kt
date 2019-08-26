package com.murat.movielist.ui.main.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.murat.movielist.R
import com.murat.movielist.core.BaseAdapter
import com.murat.movielist.databinding.RvCastItemBinding
import com.murat.movielist.service.response.Cast
import com.murat.movielist.ui.main.details.DetailsActivity
import com.murat.movielist.ui.main.details.DetailsActivityViewModel


class CastRecyclerViewAdapter(private val callback: (Cast, Int) -> Unit) : BaseAdapter<Cast?>(object : DiffUtil.ItemCallback<Cast?>() {
    override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem == newItem
    }

}) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = DataBindingUtil.inflate<RvCastItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.rv_cast_item,
            parent,
            false
        )
        val viewModel = DetailsActivityViewModel((parent.context as DetailsActivity).application)

        mBinding.viewModel = viewModel

        mBinding.castIv.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {
                callback.invoke(it, mBinding.viewModel!!.position)
            }
        }
        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        getItem(position)?.let { (binding as RvCastItemBinding).viewModel?.setModel(it, position) }
        binding.executePendingBindings()    }
}