package com.amud.thevoid

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amud.thevoid.utils.DetectionHelper


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val REQUEST_CHOOSE_IMAGE = 1002
    lateinit var uploadImageTv: TextView
    lateinit var findOyoTv: TextView
    lateinit var changeImage: TextView
    lateinit var imageVew: ImageView
    var bitmap: Bitmap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        uploadImageTv = findViewById(R.id.buttonLoadPicture)
        findOyoTv = findViewById(R.id.find_oyo)
        changeImage = findViewById(R.id.change_image)
        imageVew = findViewById(R.id.imgView)

        findOyoTv.setOnClickListener(this)
        changeImage.setOnClickListener(this)
        uploadImageTv.setOnClickListener(this)
    }


    private fun startChooseImageIntentForResult() {
        bitmap = null
        val i = Intent(
            Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(i, REQUEST_CHOOSE_IMAGE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHOOSE_IMAGE && resultCode == Activity.RESULT_OK && null != data && data.data != null) {
            val selectedImage: Uri = data.data!!
            val filePathColumn =
                arrayOf(MediaStore.Images.Media.DATA)
            val cursor: Cursor? = contentResolver.query(
                selectedImage,
                filePathColumn, null, null, null
            )
            cursor?.moveToFirst()
            cursor?.getColumnIndex(filePathColumn[0])?.run {
                val picturePath: String? = cursor.getString(this)
                cursor.close()
                setImage(picturePath)
            }

        }
    }

    private fun setImage(picturePath: String?) {
        if (picturePath != null) {
            val bitmap = BitmapFactory.decodeFile(picturePath)
            if (bitmap != null) {
                this.bitmap = bitmap
                imageVew.visibility = View.VISIBLE
                findOyoTv.visibility = View.VISIBLE
                uploadImageTv.visibility = View.GONE
                changeImage.visibility = View.VISIBLE
                imageVew.setImageBitmap(bitmap)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.change_image -> {
                startChooseImageIntentForResult()
            }
            R.id.find_oyo -> {
                DetectionHelper().recognizeLandmarksCloud(bitmap) { location ->
                    if (location != null) {
                        val intent = Intent().also {
                            it.putExtra("name", location.name)
                            it.putExtra("lat", location.lat)
                            it.putExtra("long", location.long)
                        }
                        setResult(RESULT_OK, intent);
                        finish()
                    } else {
                        Toast.makeText(this, "Unable to recorganse monument", Toast.LENGTH_LONG)
                            .show()
                    }
                }

            }
            R.id.buttonLoadPicture -> {
                startChooseImageIntentForResult()
            }
        }
    }
}
