package net.oneread.oneread.ui.base

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import butterknife.BindView
import com.oila.oneaccount.ui.navdrawer.DrawerAdapter
import com.oila.oneaccount.ui.navdrawer.getDrawerItems
import net.oneread.oneread.OneReadApplication
import net.oneread.oneread.R
import net.oneread.oneread.data.model.DrawerItem
import net.oneread.oneread.injection.component.ActivityComponent
import net.oneread.oneread.injection.component.ConfigPersistentComponent
import net.oneread.oneread.injection.component.DaggerConfigPersistentComponent
import net.oneread.oneread.injection.module.ActivityModule
import org.jetbrains.anko.find
import timber.log.Timber
import java.util.*
import java.util.concurrent.atomic.AtomicLong


abstract class BaseActivity : AppCompatActivity() {

    companion object {
        @JvmStatic private val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
        @JvmStatic private val NEXT_ID = AtomicLong(0)
        @JvmStatic private val componentsMap = HashMap<Long, ConfigPersistentComponent>()
    }

    private lateinit var mBackPressedListeners: MutableList<OnBackPressedListener>
    abstract fun getLayoutId(): Int

    @BindView(R.id.progressBar) @Nullable
    lateinit var mProgress: ProgressBar
    @BindView(R.id.toolbar) @Nullable
    lateinit var mToolbar: Toolbar
    @BindView(R.id.drawer_layout) @Nullable
    lateinit var mDrawer: DrawerLayout
    @BindView(R.id.navigation_view) @Nullable
    lateinit var navigationView: NavigationView
    private var mDrawerAdapter: DrawerAdapter? = null
    private var mActionBarDrawerToggle: ActionBarDrawerToggle? = null

    private var activityId: Long = 0
    lateinit var activityComponent: ActivityComponent
        get


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mToolbar = find(R.id.toolbar)
        setSupportActionBar(mToolbar)
        mBackPressedListeners = mutableListOf()
        initDI(savedInstanceState)
    }

    private fun initDI(savedInstanceState: Bundle?) {
        activityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NEXT_ID.getAndIncrement()
        val configPersistentComponent = componentsMap.getOrPut(activityId, {
            val component = (applicationContext as OneReadApplication).applicationComponent

            DaggerConfigPersistentComponent.builder()
                    //depends on ApplicationComponent, so set it
                    .applicationComponent(component)
                    .build()
        })

        activityComponent = configPersistentComponent.activityComponent(ActivityModule(this))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_ACTIVITY_ID, activityId)
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) {
            componentsMap.remove(activityId)
        }
        super.onDestroy()
    }

    override fun onBackPressed() {
        var consumed = false

        // if any listener consumes the event, we don't call super
        for (listener in mBackPressedListeners) {
            Timber.d("onbackpressed " + listener.javaClass.getName())
            if (listener.onBackPressed()) {
                consumed = true
            }
        }

        if (!consumed) {
            Timber.d("super onbackpressed")
            super.onBackPressed()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }

    protected fun setupToolbar(title: String, vararg homeAsUpIndicator: Int): Toolbar? {
        mToolbar.title = title
        setSupportActionBar(mToolbar)
        if (homeAsUpIndicator.isNotEmpty()) {
            if (supportActionBar != null) {
                supportActionBar!!.setHomeAsUpIndicator(homeAsUpIndicator[0])
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                supportActionBar!!.setDisplayShowHomeEnabled(true)
            }
        }
        return mToolbar
    }

    protected fun setupToolbar(title: String, drawable: Drawable): Toolbar? {
        mToolbar.title = title
        setSupportActionBar(mToolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setHomeAsUpIndicator(drawable)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
        return mToolbar
    }

    //=================Setup methods================//
    fun setupDrawer(name: String, profileUri: String, icons: Array<Int>,
                    titles: Array<String>, layout: Int) {
        val contentFrameLayout = find<FrameLayout>(R.id.content_frame)
        layoutInflater.inflate(layout, contentFrameLayout)
        val items = getDrawerItems(name = name, profileUrl = profileUri, icons = icons, titles = titles)
        setupDrawerView(items)
    }

    private fun setupDrawerView(drawerListItems: List<DrawerItem>) {
        mDrawerAdapter = DrawerAdapter(drawerListItems)
        val mRecyclerView = find<RecyclerView>(R.id.left_drawer)
        val mLinearLayoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = mLinearLayoutManager
        mRecyclerView.adapter = mDrawerAdapter

        mActionBarDrawerToggle = ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.drawer_open, R.string.drawer_closed)
        if (mActionBarDrawerToggle != null) {
            mDrawer.addDrawerListener(mActionBarDrawerToggle!!)
            mDrawer.addDrawerListener(mActionBarDrawerToggle!!)
        }
        navigationView.setNavigationItemSelectedListener({ item ->
            when (item.itemId) {
                R.id.menu_home -> mDrawer.closeDrawers()
            }
            false
        })
        mDrawer.post({ mActionBarDrawerToggle?.syncState() })
    }
    //===========================================================================================//

    fun showProgress() {
        mProgress.visibility = View.VISIBLE
    }

    fun hideProgress() {
        mProgress.visibility = View.GONE
    }

    interface OnBackPressedListener {
        /**
         * Called when back is pressed. Return true is back pressed event is consumed.
         *
         * @return Event consumed.
         */
        fun onBackPressed(): Boolean
    }

    fun registerOnBackPressedListener(listener: OnBackPressedListener) {
        mBackPressedListeners.add(listener)
    }

    fun unregisterOnBackPressedListener(listener: OnBackPressedListener) {
        mBackPressedListeners.remove(listener)
    }

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

}
