package com.rp.hitta.api

/**
 * Created by roba
 */
interface OnDoneListener<T> {
    fun onSuccess(displayName: T)
    fun onFail()
}