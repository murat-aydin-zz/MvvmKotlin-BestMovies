package com.murat.movielist.core

import androidx.lifecycle.ViewModelProviders
import android.content.IntentFilter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.annotation.LayoutRes
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
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
        init()

    }

    open fun init() {}

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