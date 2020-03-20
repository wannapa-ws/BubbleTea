package com.example.fb_login

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        debugHashKey()

        val authen = authen()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.contentContainer, authen,"fragment_authen")
        transaction.addToBackStack("fragment_authen")
        transaction.commit()

        val ShowData = ShowData()
        transaction.replace(R.id.show, ShowData,"fragment_ShowData")
        transaction.addToBackStack("fragment_ShowData")

        val DataRealtime = DataRealtime()
        transaction.replace(R.id.add, DataRealtime,"fragment_DataRealtime")
        transaction.addToBackStack("fragment_DataRealtime")
    }

    private fun debugHashKey() {
        try {
            val info = packageManager.getPackageInfo(
                "com.example.fb_login",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.getEncoder().encodeToString(md.digest()))
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }
    }

    override fun onBackPressed() {
        val manager = supportFragmentManager.findFragmentById(R.id.contentContainer)
        if (manager is authen) {
            finish()
        }else if(manager is  Recycler_view){
            finish()
        }
        else{
            super.onBackPressed();
        }
    }
}