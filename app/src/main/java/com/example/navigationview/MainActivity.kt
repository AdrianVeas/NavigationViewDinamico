package com.example.navigationview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.navigationview.modelos.users
import com.example.navigationview.modelos.users.Companion.JsonObjectsBuild
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity(), View.OnClickListener {
    //var toolbar: Toolbar? = null
    lateinit var user: EditText
    lateinit var password: EditText

    val listauser: String = "[{\"nickname\":\"USER1\", \"password\":\"12345\", \"urlimage\":\"ic_user1_foreground\", \"typeuser\":\"Admin\" }," +
            "{\"nickname\":\"USER2\", \"password\":\"12345\", \"urlimage\":\"ic_user2_foreground\" , \"typeuser\":\"Student\"}," +
            "{\"nickname\":\"USER3\", \"password\":\"12345\", \"urlimage\":\"ic_user3_foreground\", \"typeuser\":\"Profesor\"}]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        var text : String = R.id.toolbar.toString()
        toolbar = findViewById(text.toInt());
        toolbar!!.title="App UTEQ"
        setSupportActionBar(toolbar);

        getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.iconmenu)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        */

        user = findViewById(R.id.editusername)
        password = findViewById(R.id.editpassword)

        user.setText("USER1")
        password.setText("12345")
    }

    override fun onClick(v: View?) {
        try{
            user = findViewById(R.id.editusername)
            password = findViewById(R.id.editpassword)

            val lstUserArray = JSONArray(listauser)
            val lstUsuarios: ArrayList<users> = JsonObjectsBuild(lstUserArray)
            var confirmation = "N"
            for (users in lstUsuarios){
                if(users.nickname == user.text.toString()){
                    if (users.password == password.text.toString()){
                        confirmation = "Y"
                    }
                }
            }

            val intent = Intent(this, MainActivity2::class.java)
            val bundle = Bundle()

            if(confirmation == "Y") {
                bundle.putString("user", user.text.toString())
                bundle.putString("listusers", listauser)

                intent.putExtras(bundle)
                startActivity(intent)
            }else{
                Toast.makeText(applicationContext, "Error User or Password", Toast.LENGTH_SHORT).show()
            }
        }
        catch (ex: Exception){
            Toast.makeText(applicationContext, "Error: "+ex.message, Toast.LENGTH_SHORT).show()
        }

    }
}