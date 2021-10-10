package com.example.wtechbitirmeprojesi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.wtechbitirmeprojesi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private lateinit var navHostFragment:NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        setUpNavigation()
    }


    private fun setUpNavigation(){
        navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
         NavigationUI.setupWithNavController(binding.bottomNavigationView,navHostFragment.navController)


        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.productsFragment,
            R.id.cardFragment,
            R.id.discountedProductsFragment,
        ).build()
        binding.toolbar.setupWithNavController(navHostFragment.navController, appBarConfiguration)

        setSupportActionBar(binding.toolbar )

     }
}