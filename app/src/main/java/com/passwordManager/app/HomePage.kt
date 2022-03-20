package com.passwordManager.app

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.passwordManager.app.databinding.ActivityHomeBinding
import com.passwordManager.app.databinding.SideMenuHeaderBinding

private lateinit var binding: ActivityHomeBinding
private lateinit var bindingSideMenu: SideMenuHeaderBinding

class HomePage : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        bindingSideMenu = SideMenuHeaderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)

        val drawerToggle = ActionBarDrawerToggle(
            this,
            binding.sideDrawer,
            binding.toolBar,
            R.string.openDrawer,
            R.string.closeDrawer
        )
        binding.sideDrawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        val navView: BottomNavigationView = binding.homeBottomNavigation
        val navController = findNavController(R.id.nav_home)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.myPassword,
                R.id.categories,
                R.id.tags,
                R.id.profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.sideNavigation.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (binding.sideDrawer.isDrawerOpen(GravityCompat.START)) {
            binding.sideDrawer.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                Toast.makeText(this, "Logout Successfully", Toast.LENGTH_LONG).show()
                binding.sideDrawer.closeDrawers()
                finish()
                return true
            }
        }
        return true
    }
}