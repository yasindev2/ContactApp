package uz.yasindev.contactapp.core

import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment


fun Fragment.setItemStatusBarColor(color: Int, darkStatusBarTint: Boolean) {

    val window: Window = (requireActivity().window).also {
        it.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        it.statusBarColor = color
    }

    val decor = window.decorView
    if (darkStatusBarTint) {
        decor.systemUiVisibility = decor.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    } else {
        decor.systemUiVisibility = 0
    }

}

