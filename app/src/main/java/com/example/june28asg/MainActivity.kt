package com.asg.june28asg

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest
import android.Manifest.permission
import android.Manifest.permission.CALL_PHONE
import android.app.SearchManager
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    val REQUEST_IMAGE_GET = 1
    val REQUEST_PHONECALL_GET = 2
    lateinit var url : String
    companion object {
        fun newIntent(context: Context): Intent {
            var intent = Intent(context,MainActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        url = edSearch.text.toString()
        btnCamera.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            if(intent.resolveActivity(packageManager) != null)
            {
                startActivityForResult(intent,REQUEST_IMAGE_GET)
            }
        }
        btnPhCall.setOnClickListener {
            if (!edPhoneNumber.text.toString().equals(""))
                callPhoneNumber()
            else
                Toast.makeText(this,"Phone number is invalid!",Toast.LENGTH_SHORT).show()
        }
        btnSearch.setOnClickListener {
            Toast.makeText(this,url,Toast.LENGTH_LONG).show()
            searchUrl()
        }
    }
    fun searchUrl()
    {
        val intent = Intent(Intent.ACTION_WEB_SEARCH).apply { putExtra(SearchManager.QUERY,url)}
        if(intent.resolveActivity(packageManager) != null)
            startActivity(intent)
    }

    fun callPhoneNumber()
    {
        try {
            if (Build.VERSION.SDK_INT > 29) {
                if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE), REQUEST_PHONECALL_GET)
                    return
                }

                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:"+edPhoneNumber.text.trim())
                startActivity(callIntent)

            } else {
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:"+edPhoneNumber.text.trim())
                startActivity(callIntent)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_PHONECALL_GET )
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                callPhoneNumber()
            }
            else
            {
                Log.e("error","Permission is granted")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if( requestCode == REQUEST_IMAGE_GET && resultCode == Activity.RESULT_OK)
        {
            val photoUri: Uri = data!!.data!!
            Glide.with(this).load(photoUri).into(ivPhoto)
        }/*
        else if(requestCode == REQUEST_PHONECALL_GET && resultCode == Activity.RESULT_OK)
        {
            val phoneNumber : Uri = data!!.data!!
        }*/
        }
    }