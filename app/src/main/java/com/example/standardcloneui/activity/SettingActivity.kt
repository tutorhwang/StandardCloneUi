package com.example.standardcloneui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.standardclonui.R

private const val TAG = "SettingActivity.LifeCycle"

class SettingActivity : AppCompatActivity() {
    private val sharedPref by lazy { getSharedPreferences("MyPref", Context.MODE_PRIVATE) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate()")
        setContentView(R.layout.activity_settings)
        findViewById<Button>(R.id.pick_button)?.setOnClickListener { openGalleryForImage() }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy()")
    }

//    private val pickImageLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                val imageUri: Uri? = result.data?.data
//                findViewById<ImageView>(R.id.picture)?.apply {
//                    setImageURI(imageUri)
//                }
//            }
//        }
//
//    private fun openGalleryForImage() {
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"
//        pickImageLauncher.launch(intent)
//    }

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            uri?.also { imageUri ->
                findViewById<ImageView>(R.id.picture)?.setImageURI(imageUri)
                contentResolver.takePersistableUriPermission(
                    imageUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            }
        }

    private fun openGalleryForImage() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

}