package com.panjiazhi.mdmusic

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TableLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.panjiazhi.mdmusic.common.config.AppConfig
import com.panjiazhi.mdmusic.common.utils.MyPreference
import com.panjiazhi.mdmusic.module.discovery.DiscoveryFragment
import com.panjiazhi.mdmusic.module.login.LoginActivity
import com.panjiazhi.mdmusic.ui.adapter.FragmentPagerViewAdapter

import kotlinx.android.synthetic.main.activity_main.*


private val TAB_TITLES = arrayOf("我的", "发现", "云村", "视频")

class MainActivity : AppCompatActivity() {
    private val isLogin by MyPreference(AppConfig.IS_LOGIN, false)
    val fragmentList: Array<Fragment> = arrayOf(DiscoveryFragment(), DiscoveryFragment(), DiscoveryFragment(), DiscoveryFragment())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isLogin) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            return
        }
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu_black)
        }
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor = Color.TRANSPARENT
        initViewPager()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.app_bar_search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViewPager() {
        tabLayout.apply {
            setSelectedTabIndicator(null)
//            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//                override fun onTabReselected(tab: TabLayout.Tab?) {
//
//                }
//
//                override fun onTabUnselected(tab: TabLayout.Tab?) {
//                    tab?.customView = null
//                }
//
//                override fun onTabSelected(tab: TabLayout.Tab?) {
//                    val textView = TextView(this@MainActivity);
//                    val selectedSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 18F, resources.displayMetrics)
//                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, selectedSize)
//                    textView.setTextColor(resources.getColor(R.color.textColorPrimary))
//                    textView.gravity = TextView.TEXT_ALIGNMENT_CENTER
//                    textView.text = tab?.text
//                    tab?.customView = textView
//                }
//            })
        }
        viewPager.adapter = FragmentPagerViewAdapter(fragmentList, this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = TAB_TITLES[position]
        }.attach()
        tabLayout.getTabAt(1)?.select()
    }
}
