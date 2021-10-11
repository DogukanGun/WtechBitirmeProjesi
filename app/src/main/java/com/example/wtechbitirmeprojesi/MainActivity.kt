package com.example.wtechbitirmeprojesi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.wtechbitirmeprojesi.databinding.ActivityMainBinding
import com.example.wtechbitirmeprojesi.resources.Constants

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        setUpNavigation()
    }


    private fun setUpNavigation(){
         Constants.navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
         NavigationUI.setupWithNavController(binding.bottomNavigationView,Constants.navHostFragment.navController)


        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.productsFragment,
            R.id.cardFragment,
            R.id.discountedProductsFragment,
        ).build()
        binding.toolbar.setupWithNavController(Constants.navHostFragment.navController, appBarConfiguration)


     }
}