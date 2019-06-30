package com.t2aq.wallet.ui.mainpage

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.t2aq.wallet.R
import com.t2aq.wallet.ui.currencylist.CurrencyListActivity
import com.t2aq.wallet.ui.exchange.ExchangeActivity
import com.t2aq.wallet.ui.registration.RegistrationActivity
import com.t2aq.wallet.ui.walletlist.WalletListActivity
import com.t2aq.wallet.ui.walletlist.WalletListAdapter
import com.t2aq.wallet.utils.LoginUtils

class MainPageActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainpage)


        firstSetup()

    }

    private fun firstSetup(){
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(this,
                                           drawerLayout,
                                           toolbar,
                                           R.string.navigation_drawer_open,
                                           R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        supportFragmentManager.beginTransaction()
            .add(R.id.constrainlayout_mainpage_container,MainPageFragment()).commit()
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_page, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.item_navigationdrawer_currencylist -> {
                val intent = Intent(this,CurrencyListActivity::class.java)
                startActivity(intent)
            }
            R.id.item_navigationdrawer_exchange -> {
                val intent = Intent(this,ExchangeActivity::class.java)
                startActivity(intent)
            }
            R.id.item_navigationdrawer_wallets -> {
                val intent = Intent(this,WalletListActivity::class.java)
                startActivity(intent)

            }
            R.id.item_navigationdrawer_signout -> {
                LoginUtils.saveTokenInSharedPreferences("")
                val intent = Intent(this, RegistrationActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
