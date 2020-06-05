package com.panjiazhi.mdmusic.module.login

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.panjiazhi.mdmusic.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    val mobileLoginViewModel by viewModels<MobileLoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.statusBarColor = Color.TRANSPARENT
        setSupportActionBar(loginToolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        loginToolbar.setupWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { controller, _, _ ->
            if (controller.currentDestination?.id == R.id.LoginFragment) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                supportActionBar?.hide()
            } else {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                supportActionBar?.show()
            }
        }
    }
}