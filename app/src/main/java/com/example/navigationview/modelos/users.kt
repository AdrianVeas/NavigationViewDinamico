package com.example.navigationview.modelos
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
class users (a: JSONObject) {
    var nickname: String ? = null
    var password: String ? = null
    var urlimage: String ? = null
    var typeuser: String ? = null
    companion object {
        @Throws(JSONException::class)
        fun JsonObjectsBuild(datos: JSONArray): java.util.ArrayList<users> {
            val usuarios: ArrayList<users> = ArrayList()
            var i = 0
            while (i < datos.length()) {
                usuarios.add(users(datos.getJSONObject(i)))
                i++
            }
            return usuarios
        }
    }

    init{
        nickname = a.getString("nickname")
        password = a.getString("password")
        urlimage = a.getString("urlimage")
        typeuser = a.getString("typeuser")
    }
}
