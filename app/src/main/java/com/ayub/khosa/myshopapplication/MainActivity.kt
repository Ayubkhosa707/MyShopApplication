package com.ayub.khosa.myshopapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ayub.khosa.myshopapplication.model.USER
import com.ayub.khosa.myshopapplication.repository.MainActivityRepository
import com.ayub.khosa.myshopapplication.utils.PrintLogs
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val repository: MainActivityRepository by lazy {
        MainActivityRepository(this)
    }
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var linearLayout_busybox: LinearLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        linearLayout_busybox = findViewById<LinearLayout>(R.id.linearLayout_busybox)

        var tv_hellow = findViewById<TextView>(R.id.tv_hellow)
        tv_hellow.setOnClickListener(View.OnClickListener {
            getLoginUser()

        })

        toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        navigationView = findViewById<NavigationView>(R.id.nav_view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        //    appBarConfiguration = AppBarConfiguration(navController.graph)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.categoryFragment, R.id.productFragment, R.id.settingFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)

        getLoginUser()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        PrintLogs.printD("onOptionsItemSelected -------------- > " + item.itemId)

        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)

    }

    override fun onSupportNavigateUp(): Boolean {

        PrintLogs.printD(" onSupportNavigateUp .... ")

        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    private fun getLoginUser() {

        PrintLogs.printD(" MainActivity getLoginUser  getLoginUser ")
        linearLayout_busybox.visibility = View.VISIBLE
        PrintLogs.printD("************* MainActivity getLoginUser *****************************")

        Toast.makeText(
            this.applicationContext,
            " MainActivity getLoginUser api call ",
            Toast.LENGTH_SHORT
        )
        lifecycleScope.launch {
            try {
                PrintLogs.printD(" lifecycleScope.launch  ok ")
                val response = repository.getLoginUser(
                    "ayub.khosa@gmail.com",
                    "ayub"
                )

                if (response.response == "Success") {
                    PrintLogs.printD(" onResponse Success :  " + response.data)
                    PrintLogs.printD(" onResponse Success data email :  " + response.data.email_id)
                    PrintLogs.printD(" onResponse Success data first_name :  " + response.data.first_name)
                    PrintLogs.printD(" onResponse Success data last_name :  " + response.data.last_name)
                    PrintLogs.printD(" onResponse Success data user_id :  " + response.data.user_id)
                    PrintLogs.printD(" onResponse Success data password :  " + response.data.password)

                    response.data.let {

                        //  addUserinDB(it)
                        val hView = navigationView.getHeaderView(0)
                        val nav_header_title =
                            hView.findViewById<View>(R.id.nav_header_title) as TextView
                        val nav_header_subtitle =
                            hView.findViewById<View>(R.id.nav_header_subtitle) as TextView
                        nav_header_title.text = it.email_id
                        nav_header_subtitle.text = "User id:" + it.user_id

                    }

                }

            } catch (e: Exception) {
                PrintLogs.printD(" Exception " + e.message)
            }

        }
        linearLayout_busybox.visibility = View.GONE


    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (count == 0) {
            super.onBackPressed()
            //additional code
        } else {
            supportFragmentManager.popBackStack()
        }
    }
    fun addUserinDB(user: USER) {
        PrintLogs.printD("addUserinDB  ")
        try {
            if (repository.fetchUSERByName(user.email_id, user.password) != null) {
                repository.updateUSERinDB(user)
                PrintLogs.printD("updateUSERinDB  ")
            } else {
                repository.insertUSERinDB(user)
                PrintLogs.printD("insertUSERinDB  ")
            }
        } catch (e: Exception) {
            PrintLogs.printD("Exception: ${e.message}")
        }
    }
}