package com.murat.movielist.ui.main.Adapter

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.murat.movielist.R
import com.murat.movielist.core.BaseAdapter
import com.murat.movielist.databinding.RvMovieItemBinding
import com.murat.movielist.db.entitiy.MovieEntity
import com.murat.movielist.ui.main.MainActivityViewModel


class MovieAdapter(private val callback: (MovieEntity, Int) -> Unit) : BaseAdapter<MovieEntity?>(object : DiffUtil.ItemCallback<MovieEntity?>() {
    override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem == newItem
    }

}) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = DataBindingUtil.inflate<RvMovieItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.rv_movie_item,
            parent,
            false
        )
        val viewModel =
            MainActivityViewModel((parent.context as Activity).application)


        mBinding.viewModel = viewModel

        mBinding.posterCardView.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {
                callback.invoke(it, mBinding.viewModel!!.position)
            }
        }
        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        getItem(position)?.let { (binding as RvMovieItemBinding).viewModel?.setModel(it, position) }
        binding.executePendingBindings()
    }
}