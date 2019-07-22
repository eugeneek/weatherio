package com.eugeneek.wheatherio.feature.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.Router

abstract class BaseFragment: Fragment() {
    abstract val layoutRes: Int

    protected val router: Router by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(layoutRes, container, false)

    open fun onBackPressed() {
        router.exit()
    }

    fun showSnackbarLong(message: String) {
        showSnackbar(message, Snackbar.LENGTH_LONG)
    }

    fun showSnackbarShort(message: String) {
        showSnackbar(message, Snackbar.LENGTH_SHORT)
    }

    private fun showSnackbar(message: String, duration: Int) {
        view?.let { Snackbar.make(it, message, duration).show() }
    }
}