package net.oneread.oneread.ui

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.Router
import com.evernote.android.state.State

import net.oneread.oneread.R
import net.oneread.oneread.data.local.OneReadSettings
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        val EXTRA_REQUIRES_INIT = "net.oneread.onereadandroid.requires_init"
    }

    @State
    var bottomNavInstanceId: String? = null
    private var router: Router? = null
    private var isPaused: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val container = find<ViewGroup>(R.id.controller_container)
        router = Conductor.attachRouter(this, container, savedInstanceState)
        determineDeviceKeys()
    }

    override fun onPause() {
        isPaused = true
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        isPaused = false
    }

    fun isActivityPaused() = isPaused

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                android.R.id.home -> {
                    onBackPressed()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    private fun determineDeviceKeys() {
        val hasSoftNavBar: Boolean
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val display = windowManager.defaultDisplay
            val displayMetrics = DisplayMetrics()
            display.getRealMetrics(displayMetrics)
            val height = displayMetrics.heightPixels
            val width = displayMetrics.widthPixels

            display.getMetrics(displayMetrics)
            val displayHeight = displayMetrics.heightPixels
            val displayWidth = displayMetrics.widthPixels
            hasSoftNavBar = width - displayWidth > 0 || height - displayHeight > 0
        } else {
            val hasMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey()
            val hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)
            hasSoftNavBar = !hasMenuKey && !hasBackKey
        }
        OneReadSettings
                .getInstance()
                .setHasSoftNavBar(hasSoftNavBar)
    }
}
