package com.murat.movielist.ui.main.Adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.murat.movielist.R
import com.murat.movielist.core.BaseAdapter
import com.murat.movielist.databinding.RvTrailerItemBinding
import com.murat.movielist.service.response.Trailer
import com.murat.movielist.ui.main.details.DetailsActivity
import com.murat.movielist.ui.main.details.DetailsActivityViewModel


class TrailerRecyclerViewAdapter(private val callback: (Trailer, Int) -> Unit) : BaseAdapter<Trailer?>(object : DiffUtil.ItemCallback<Trailer?>() {
    override fun areContentsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem == newItem
    }

}) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = DataBindingUtil.inflate<RvTrailerItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.rv_trailer_item,
            parent,
            false
        )
        val viewModel = DetailsActivityViewModel((parent.context as DetailsActivity).application)

        mBinding.viewModel = viewModel
        mBinding.trailerThumbnailIv.setOnClickListener {
            mBinding.viewModel?.itemTrailer?.get()?.let {
                callback.invoke(it, mBinding.viewModel!!.positionTrailer)
            }
        }

        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        getItem(position)?.let { (binding as RvTrailerItemBinding).viewModel?.setTrailerModel(it, position) }
        binding.executePendingBindings()    }
}
