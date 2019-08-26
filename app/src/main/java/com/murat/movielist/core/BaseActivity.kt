package com.murat.movielist.core

import android.arch.lifecycle.ViewModelProviders
import android.content.IntentFilter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.design.widget.BaseTransientBottomBar
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.murat.movielist.R
import com.murat.movielist.utils.ConnectivityReceiver

abstract class BaseActivity<ViewModel : BaseViewModel, DataBinding : ViewDataBinding>(private val mViewModelClass: Class<ViewModel>) :
    AppCompatActivity() , ConnectivityReceiver.ConnectivityReceiverListener {

    @LayoutRes
    abstract fun getLayoutRes(): Int
    private var snackBar: Snackbar? = null
    private val connectivityReceiver = ConnectivityReceiver()

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DataBinding
    }

    val viewModel by lazy {
        ViewModelProviders.of(this).get(mViewModelClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initViewModel(viewModel)
        super.onCreate(savedInstanceState)
        onInject()
        registerReceiver(connectivityReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this

    }
    override fun onNetworkConnectionChanged(isConnected: Boolean?) {
        isConnected?.let { showNetworkMessage(it) }
    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(connectivityReceiver)
    }
    private fun showNetworkMessage(isConnected: Boolean) {
        if (!isConnected) {

            snackBar =
                Snackbar.make(findViewById(R.id.root_layout), "Internet bağlantınızı kontrol edin.", Snackbar.LENGTH_LONG)
            snackBar?.duration = BaseTransientBottomBar.LENGTH_INDEFINITE
            snackBar?.view?.setBackgroundColor(Color.parseColor("#F48B8C"))
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )

            snackBar?.show()
        } else {
            window.clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )

            snackBar?.dismiss()
        }
    }

    /* If you want to inject Dependency Injection
     on your activity, you can override this.*/
    open fun onInject() {}

    abstract fun initViewModel(viewModel: ViewModel)

}