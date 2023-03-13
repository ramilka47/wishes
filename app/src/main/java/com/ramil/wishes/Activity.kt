package com.ramil.wishes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

class Activity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val count = getPreferences(MODE_PRIVATE).getInt("count_of_click", 0)
        getPreferences(MODE_PRIVATE).edit().putInt("count_of_click", count + 1).apply()
        if (count == 0) {
            Toast.makeText(this, "Привет, Мадина. Удачного пользования!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            finish()
        }

        applicationContext.startForegroundService(Intent(applicationContext, NativeService::class.java))
    }

}