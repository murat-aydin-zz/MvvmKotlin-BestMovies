package com.murat.movielist.service

import com.murat.movielist.core.BaseCallBack

class ApiCallback<T>(baseCallBack: BaseCallBack<T>?) : BaseRetrofitCallback<T>(baseCallBack)
