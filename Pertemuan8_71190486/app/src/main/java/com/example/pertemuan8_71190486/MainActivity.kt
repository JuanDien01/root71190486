package com.example.pertemuan8_71190486

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Toolbar
        setSupportActionBar(findViewById(R.id.toolbar_default))
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_default, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.Shop -> {
            val i = Intent(this, ShopActivity::class.java)
            startActivity(i)
            true
        }
        R.id.Favorite -> {
            val i = Intent(this, FavoriteActivity::class.java)
            startActivity(i)
            true
        }
        R.id.Message -> {
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}