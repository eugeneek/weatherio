package com.eugeneek.wheatherio.feature.core.domain

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

@Suppress("UNCHECKED_CAST")
inline fun <reified VM : ViewModel> Fragment.viewModelProvider(
    crossinline provider: () -> VM) = lazy {
    ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        override fun <T1 : ViewModel> create(aClass: Class<T1>) =
            provider() as T1
    }).get(VM::class.java)
}