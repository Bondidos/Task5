package com.bondidos.task5.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.res.TypedArrayUtils.getString
import com.bondidos.task5.R
import com.bondidos.task5.adapter.cat_holder.Cat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.File
import java.io.FileOutputStream


fun Cat.downloadAndSave(context: Context){

    val dirPath = Environment.getExternalStorageDirectory().absolutePath + "/" + Environment.DIRECTORY_DOWNLOADS
    val dir = File(dirPath)
    //todo dialog for save name
    val fileName = this.picture.substring(this.picture.lastIndexOf('/') +1)

    Glide.with(context)
        .load(this.picture)
        .into(object: CustomTarget<Drawable>(){
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                val bitmap = ((resource as BitmapDrawable).bitmap)
                Toast.makeText(context, "Saving Image...", Toast.LENGTH_SHORT).show()
                saveImage(bitmap, dir,fileName,context)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                TODO("Not yet implemented")
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                super.onLoadFailed(errorDrawable)
                Toast.makeText(context, "Failed to download Image...", Toast.LENGTH_SHORT).show()
            }
        })
}
private fun saveImage(image: Bitmap,storageDir: File,imageFileName: String, context: Context){

    var successDirCreated = false
    if (!storageDir.exists()) {
        successDirCreated = storageDir.mkdir()
    }

    val imageFile = File(storageDir,imageFileName)
    val savedImagePath = imageFile.absolutePath
    //try {
        //open outStream and write image into file
        val fOut = FileOutputStream(imageFile)
        image.compress(Bitmap.CompressFormat.JPEG,100,fOut)
        fOut.close()
        Toast.makeText(context, "Image saved!", Toast.LENGTH_SHORT).show()
   /* } catch (e: Exception){
        Toast.makeText(context, "Error while saving Image", Toast.LENGTH_SHORT).show()
        e.stackTrace
    }*/
}