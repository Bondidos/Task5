package com.bondidos.task5.utils

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bondidos.task5.adapter.cat_holder.Cat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.ByteBuffer


@RequiresApi(Build.VERSION_CODES.Q)////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
fun Cat.downloadAndSave(context: Context){
    //todo dialog for save name

    val dirPath = Environment.DIRECTORY_DCIM + "/savedCats"                 //path to directory with saved images
    val mime = "image/*"                                                    //Mime type of the content
    // name of the saved image
    val fileName = this.picture.substring(this.picture.lastIndexOf('/') +1)
    val fileType = this.picture.substring(this.picture.lastIndexOf('.')+1)

    val values = ContentValues().apply {                                    // describe image
        put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
        put(MediaStore.MediaColumns.MIME_TYPE, mime)
        put(MediaStore.MediaColumns.RELATIVE_PATH, dirPath)
    }

    val resolver = context.contentResolver                                  //provides app. access to content model

    if(fileType == "gif")
        downloadGif(context,this.picture,resolver,values,dirPath,fileName)
    else downloadImage(context,this.picture,resolver,values)

}
private fun downloadGif(context: Context,downloadUri:String,resolver: ContentResolver,values: ContentValues,dirPath: String,fileName: String) {
    CoroutineScope(Dispatchers.IO).launch {
        val gifImage = Glide.with(context)
            .asGif()
            .load(downloadUri)
            .submit()
            .get()

        val buffer = gifImage.buffer
        saveGif(buffer, resolver, values, context)
    }
}
private fun saveGif(buffer: ByteBuffer,resolver: ContentResolver, values: ContentValues, context: Context){

    val bytes = ByteArray(buffer.capacity())
    //duplicate buffer and copy into bytes
    (buffer.duplicate().clear() as ByteBuffer).get(bytes)
/*******************************************************************************************/
    val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!
    resolver.openOutputStream(uri).use {
     it?.write(bytes,0,bytes.size)
    }

/*******************************************************************************************/
}
private fun saveImage(context: Context, resolver: ContentResolver, values: ContentValues, bitmap: Bitmap){
    var uri: Uri? = null
    try{
        uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            ?: throw IOException("failed to create new MediaStore record.")

        resolver.openOutputStream(uri)?.use {
            if (!bitmap.compress(Bitmap.CompressFormat.JPEG,100, it)) {
                Toast.makeText(context, "Failed to save Image...", Toast.LENGTH_SHORT).show()
                throw IOException("Failed to save bitmap.")
            }
            Toast.makeText(context, "Image saved.", Toast.LENGTH_SHORT).show()
        } ?: throw IOException("Failed to open output stream.")
    } catch (e: IOException){
        uri?.let {
                Uri ->
            resolver.delete(Uri,null,null)
        }
        throw e
    }
}

private fun downloadImage(context: Context,downloadUri:String,resolver: ContentResolver,values: ContentValues){
    Glide.with(context)
        .load(downloadUri)
        //save image to the store
        .into(object: CustomTarget<Drawable>(){
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                val bitmap = ((resource as BitmapDrawable).bitmap)
                Toast.makeText(context, "Saving Image...", Toast.LENGTH_SHORT).show()
                saveImage(context,resolver,values,bitmap)
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


