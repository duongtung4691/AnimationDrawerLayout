package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View
import android.view.animation.*
import android.widget.LinearLayout
import android.view.animation.Animation.AnimationListener


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.setScrimColor(Color.TRANSPARENT)
        val content : LinearLayout = findViewById(R.id.content)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = object : ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        ){
            var scaleFactor = 4f
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                val slideX = drawerView.width * slideOffset
//                content.translationX = slideX
//                content.scaleX = 1 - slideOffset / scaleFactor
//                content.scaleY = 1 - slideOffset / scaleFactor
                val ant = TranslateAnimation(0f,slideX,0f,0f)
                val ans = ScaleAnimation(1f, 1f, // Start and end values for the X axis scaling
                    1 - slideOffset / scaleFactor, 1 - slideOffset / scaleFactor, // Start and end values for the Y axis scaling
                    Animation.RELATIVE_TO_SELF, 0.4f, // Pivot point of X scaling
                    Animation.RELATIVE_TO_SELF, 0.4f)
                val an : Animation
                if(!drawerLayout.isDrawerOpen(drawerView)) {
                     an = Rotate3dAnimation(
                        0f,
                        -10f,
                        centerX = content.width.toFloat() / 2,
                        centerY = content.height.toFloat() / 2,
                        depthZ = 20f,
                        reverse = false
                    )
                    Log.d("","duongtung: onDrawerSlide isDrawerOpen false")
                }else {
                     an = Rotate3dAnimation(
                        -10f,
                        0f,
                        centerX = content.width.toFloat() / 2,
                        centerY = content.height.toFloat() / 2,
                        depthZ = 0f,
                        reverse = false
                    )
                    Log.d("","duongtung: onDrawerSlide")
                }
                val anim = AnimationSet(true)
                anim.duration = 500
                anim.repeatCount = 0
                anim.isFillEnabled = true
                anim.fillAfter = true
                anim.addAnimation(an)
                anim.addAnimation(ant)
                anim.addAnimation(ans)
                content.startAnimation(anim)
                navView.setBackgroundColor(Color.TRANSPARENT)

            }

        }
        drawerLayout.drawerElevation = 0f
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
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
        menuInflater.inflate(R.menu.main, menu)
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
            R.id.nav_home -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
