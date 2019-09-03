package com.murat.movielist.ui.main.adapters

import android.app.Activity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.murat.movielist.R
import com.murat.movielist.core.BaseAdapter
import com.murat.movielist.databinding.RvMovieFavItemBinding
import com.murat.movielist.db.entitiy.MovieEntity
import com.murat.movielist.ui.main.main.MainActivityViewModel


class MovieFavAdapter(private val callback: (MovieEntity, Int) -> Unit) : BaseAdapter<MovieEntity?>(object : DiffUtil.ItemCallback<MovieEntity?>() {
    override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem == newItem
    }

}) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = DataBindingUtil.inflate<RvMovieFavItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.rv_movie_fav_item,
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
        getItem(position)?.let { (binding as RvMovieFavItemBinding).viewModel?.setModel(it, position) }
        binding.executePendingBindings()
    }
}