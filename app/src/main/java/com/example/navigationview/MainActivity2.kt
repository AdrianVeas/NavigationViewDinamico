package com.example.navigationview

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.navigationview.modelos.settingsuser
import com.example.navigationview.modelos.users
import com.google.android.material.navigation.NavigationView
import org.json.JSONArray

class MainActivity2 : AppCompatActivity(), View.OnClickListener,
    NavigationView.OnNavigationItemSelectedListener {
    var drawerLayout: DrawerLayout? = null
    var toolbar: Toolbar? = null
    private lateinit var navView: NavigationView
    private lateinit var cabecera: View
    private lateinit var urlimage : String
    private lateinit var typeuser : String
    private lateinit var imagev : ImageView
    private lateinit var nickname: TextView
    private lateinit var txttypeuser: TextView
    private lateinit var  lstsettinguserArray: JSONArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val listsettinguser : String =
                "[{\"typeuser\" : \"Admin\", \"settings\" : [\"Admin Setting 1\", \"Admin Setting 2\", \"Admin Setting 3\"]}," +
                "{\"typeuser\" : \"Student\", \"settings\" : [\"Student Setting 1\", \"Student Setting 2\", \"Student Setting 3\"]}," +
                "{\"typeuser\" : \"Profesor\", \"settings\" : [\"Profesor Setting 1\", \"Profesor Setting 2\", \"Profesor Setting 3\"]}]"
        try {
            toolbar = findViewById(R.id.toolbar);
            toolbar!!.title = "App UTEQ"
            setSupportActionBar(toolbar);

            getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.iconmenu)
            getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

            navView = findViewById(R.id.nav_view)
            navView.setNavigationItemSelectedListener(this)

            //Extract data sent of main activity
            val bundle = intent.extras
            val user = bundle?.getString("user")
            val listauser = bundle?.getString("listusers")

            val lstUser = JSONArray(listauser)
            val lstUsuarios: ArrayList<users> = users.JsonObjectsBuild(lstUser)

            //Search information in json list
            for (users in lstUsuarios) {
                if (users.nickname == user) {
                    urlimage = users.urlimage.toString()
                    typeuser = users.typeuser.toString()
                    break
                }
            }


            cabecera = navView.getHeaderView(0)
            //Change Name

            nickname = cabecera.findViewById(R.id.txtusername)
            nickname.text = user

            //Change Image
            val idimage: Int = resources.getIdentifier(urlimage, "drawable", packageName)

            //Extraer Permisos
            navView.menu.clear()
            lstsettinguserArray = JSONArray(listsettinguser)
            val lstsettinguser: ArrayList<settingsuser> = settingsuser.JsonObjectsBuild(lstsettinguserArray)
            for (settinguser in lstsettinguser){
                if(settinguser.typeuser==typeuser){
                    lstsettinguserArray = settinguser.settings!!
                    for (i in 0 until lstsettinguserArray.length()){
                        navView.menu.add(lstsettinguserArray.getString(i))
                        navView.menu.getItem(i).setIcon(R.drawable.iconmenu)
                    }
                }
            }

            imagev = cabecera.findViewById(R.id.profile_image)
            imagev.setImageResource(idimage)
            //Change type user

            txttypeuser = cabecera.findViewById(R.id.txttypeuser)
            txttypeuser.text = typeuser

            when (typeuser){
                "Admin" ->{
                    Toast.makeText(applicationContext, "Welcome Admin", Toast.LENGTH_SHORT).show()
                }
                "Student"-> {
                    Toast.makeText(applicationContext, "Welcome Student", Toast.LENGTH_SHORT).show()
                }
                "Profesor"->{
                    Toast.makeText(applicationContext, "Welcome Profesor", Toast.LENGTH_SHORT).show()
                }
            }

        } catch (ex: Exception) {
            Toast.makeText(applicationContext, "Error: " + ex.message, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout = findViewById(R.id.drawer_layout)
                drawerLayout?.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {


        var fragment: Fragment? = null
        when (typeuser){
            "Admin" ->{
                when (item.title){
                    "Admin Setting 1"->{
                        fragment = Fragment1()
                    }
                    "Admin Setting 2"->{
                        fragment = Fragment2()
                    }
                    "Admin Setting 3"->{
                        fragment = Fragment3()
                    }
                }
            }
            "Student"->{
                when (item.title){
                    "Student Setting 1"->{
                        fragment = Fragment1()
                    }
                    "Student Setting 2"->{
                        fragment = Fragment2()
                    }
                    "Student Setting 3"->{
                        fragment = Fragment3()
                    }
                }
            }
            "Profesor"->{
                when (item.title){
                    "Profesor Setting 1"->{
                        fragment = Fragment1()
                    }
                    "Profesor Setting 2"->{
                        fragment = Fragment2()
                    }
                    "Profesor Setting 3"->{
                        fragment = Fragment3()
                    }
                }
            }
        }

        if (fragment != null) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit()

            item.setChecked(true)
            getSupportActionBar()?.setTitle(item.getTitle());
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true

    }

    override fun onClick(v: View?) {
        val intent = Intent(this, MainActivity::class.java )
        startActivity(intent)
    }
}
