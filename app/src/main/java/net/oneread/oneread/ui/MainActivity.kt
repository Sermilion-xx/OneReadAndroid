package net.oneread.oneread.ui

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.KeyCharacterMap
import android.view.KeyEvent
import android.view.ViewConfiguration
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.Router
import com.evernote.android.state.State
import com.evernote.android.state.StateSaver

import net.oneread.oneread.R
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    companion object {
        @JvmStatic val EXTRA_REQUIRES_INIT = "net.oneread.onereadandroid.requires_init"
    }

    @State
    var bottomNavInstanceId: String? = null
    private var router: Router? = null
    private var changeListener: ControllerChangeHandler.ControllerChangeListener? = null
    private var isPaused: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val container = find<ViewGroup>(R.id.controller_container)
        router = Conductor.attachRouter(this, container, savedInstanceState)
        val requiresInit = intent.getBooleanExtra(EXTRA_REQUIRES_INIT, true)
        determineDeviceKeys()

    }

    private fun determineDeviceKeys() {
        val hasSoftKeys: Boolean
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val display = windowManager.defaultDisplay
            val displayMetrics = DisplayMetrics()
            display.getRealMetrics(displayMetrics)
            val realHeight = displayMetrics.heightPixels
            val realWidth = displayMetrics.widthPixels

            display.getMetrics(displayMetrics)
            val displayHeight = displayMetrics.heightPixels
            val displayWidth = displayMetrics.widthPixels
            hasSoftKeys = realWidth - displayWidth > 0 || realHeight - displayHeight > 0
        } else {
            val hasMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey()
            val hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)
            hasSoftKeys = !hasMenuKey && !hasBackKey
        }
//        FrontpageSettings
//                .getInstance()
//                .setDeviceHasSoftwareKeys(hasSoftKeys)
    }
}
