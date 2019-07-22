package com.eugeneek.wheatherio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eugeneek.wheatherio.feature.base.BaseFragment
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : AppCompatActivity() {

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.flContent) as? BaseFragment

    private val navigatorHolder: NavigatorHolder by inject()
    private val router: Router by inject()
    private val navigator = object: SupportAppNavigator(this, R.id.flContent) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            router.newRootScreen(Page.Weather())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }
}

