package com.wappiter.app.module.main.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wappiter.app.R
import com.wappiter.app.constants.AppConstants
import com.wappiter.app.module.main.fragments.myrestaurants.MyRestaurantsFragment
import com.wappiter.app.module.main.fragments.scanner.ScannerFragment
import com.wappiter.app.module.main.fragments.settings.SettingsFragment
import java.lang.ref.WeakReference

class MainViewPagerAdapter(fm: FragmentManager, fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val contextWeakReference: WeakReference<Context> = WeakReference(fragmentActivity)

    val TAB_SCANNER_INDEX = 0
    val TAB_MY_RESTAURANTS_INDEX = 1
    val TAB_SETTINGS_INDEX = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = ScannerFragment()
            1 -> fragment = MyRestaurantsFragment()
            2 -> fragment = SettingsFragment()
        }
        return fragment!!
    }

    override fun getItemCount(): Int {
        return AppConstants.MAIN_NUM_TABS
    }

    fun getTabTitle(position: Int): CharSequence {
        var title: CharSequence = ""
        when (position) {
            0 -> title = contextWeakReference.get()!!.resources.getString(R.string.main_tab_scanner_title)
            1 -> title = contextWeakReference.get()!!.resources.getString(R.string.main_tab_restaurants_title)
            2 -> title = contextWeakReference.get()!!.resources.getString(R.string.main_tab_settings_title)
        }
        return title
    }

    fun getTabIconOn(position: Int): Drawable {
        var icon: Drawable = contextWeakReference.get()!!.resources.getDrawable(R.drawable.ic_search_on)
        when (position) {
            0 -> icon = contextWeakReference.get()!!.resources.getDrawable(R.drawable.ic_search_on, null)
            1 -> icon = contextWeakReference.get()!!.resources.getDrawable(R.drawable.ic_restaurant_on, null)
            2 -> icon = contextWeakReference.get()!!.resources.getDrawable(R.drawable.ic_settings_on, null)
        }
        return icon
    }

    fun getTabIconOff(position: Int): Drawable {
        var icon: Drawable = contextWeakReference.get()!!.resources.getDrawable(R.drawable.ic_search_off)
        when (position) {
            0 -> icon = contextWeakReference.get()!!.resources.getDrawable(R.drawable.ic_search_off, null)
            1 -> icon = contextWeakReference.get()!!.resources.getDrawable(R.drawable.ic_restaurant_off, null)
            2 -> icon = contextWeakReference.get()!!.resources.getDrawable(R.drawable.ic_settings_off, null)
        }
        return icon
    }

}