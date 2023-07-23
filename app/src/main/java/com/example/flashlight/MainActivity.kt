package com.example.flashlight

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.cardview.widget.CardView


class MainActivity : AppCompatActivity() {
    //varables

    private lateinit var cameraManager: CameraManager
    private var cameraId: String? = null
    private var isTorchOn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //asses the camera service
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        //find the Id of View
        val light_btn = findViewById<CardView>(R.id.light_btn)
        val imageView = findViewById<ImageView>(R.id.image_view)

        //set OnclickListener in CardView
        light_btn.setOnClickListener {
            //set On off image
            if (isTorchOn) {

                imageView.setImageResource(R.drawable.off_light)
            } else {

                imageView.setImageResource(R.drawable.on_light)
            }
            btnflashlight()


        }
    }

    private fun btnflashlight() {
        try {
            if (isTorchOn) {
                turnOffFlashlight()
            } else {
                turnOnFlashlight()
            }
        } catch (e: CameraAccessException) {
            e.printStackTrace()

        }
    }

    private fun turnOnFlashlight() {
        try {
            cameraId = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraId!!, true)
            isTorchOn = true

        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    private fun turnOffFlashlight() {
        try {
            cameraManager.setTorchMode(cameraId!!, false)
            isTorchOn = false
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }
}