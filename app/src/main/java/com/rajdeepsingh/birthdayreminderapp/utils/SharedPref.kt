package com.rajdeepsingh.birthdayreminderapp.activities.utils

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rajdeepsingh.birthdayreminderapp.model.BirthdayModelDataClass
import java.lang.reflect.Type

class SharedPref {

    companion object {
        val TAG: String = SharedPref::class.java.name

        fun saveObject(context: Context, list: ArrayList<BirthdayModelDataClass>):Boolean {
            try {
                val sharedPreferences = context.getSharedPreferences(
                    Constant.sharedPreferences,
                    AppCompatActivity.MODE_PRIVATE
                )

                val editor = sharedPreferences.edit()

                val gson = Gson()

                val value: String = gson.toJson(list)

                editor.putString("birthDayList", value)

                editor.apply()
                return true
            }catch (e: Exception){
                Log.d(TAG, e.message.toString())
                return false
            }

        }

        fun getObject(context: Context): ArrayList<BirthdayModelDataClass> {
            var arrayList: ArrayList<BirthdayModelDataClass>? = null

            try {

                val sharedPreferences = context.getSharedPreferences(
                    Constant.sharedPreferences,
                    AppCompatActivity.MODE_PRIVATE
                )

                val gson = Gson()

                val data = sharedPreferences.getString("birthDayList", null)
                val type: Type = object : TypeToken<ArrayList<BirthdayModelDataClass?>?>() {}.type
                if (data != null && data.length > 0) {
                    arrayList = gson.fromJson<Any>(data, type) as ArrayList<BirthdayModelDataClass>
                } else {
                    arrayList = ArrayList()
                }

                return arrayList

            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                return ArrayList()
            }


        }

    }


}