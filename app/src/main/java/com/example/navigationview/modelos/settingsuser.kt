package com.example.navigationview.modelos

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class settingsuser(a: JSONObject) {
    var typeuser: String ? = null
    var settings: JSONArray ? = null
    companion object {
        @Throws(JSONException::class)
        fun JsonObjectsBuild(datos: JSONArray): ArrayList<settingsuser> {
            val lstsettingsuser: ArrayList<settingsuser> = ArrayList()
            var i = 0
            while (i < datos.length()) {
                lstsettingsuser.add(settingsuser(datos.getJSONObject(i)))
                i++
            }
            return lstsettingsuser
        }
    }
    init{
        typeuser = a.getString("typeuser")
        settings = a.getJSONArray("settings")
    }
}