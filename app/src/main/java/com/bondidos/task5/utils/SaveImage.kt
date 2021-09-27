package com.bondidos.task5.utils

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.widget.Toast
import com.bondidos.task5.adapter.cat_holder.Cat
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.nio.ByteBuffer

fun downloadAndSave(context: Context,cat: Cat){
    //todo dialog for save name

    CoroutineScope(Dispatchers.IO).launch {

        val dirPath =
            Environment.DIRECTORY_DCIM + "/savedCats"                           //path to directory with saved images
        val mime =  "image/*"                                                   //Mime type of the content
                                                                                // name of the saved image
        val fileName = cat.url.substring(cat.url.lastIndexOf('/') + 1)
                                                                                // type of the image .gif or .jpg from Uri
        val fileType = cat.url.substring(cat.url.lastIndexOf('.') + 1)

        val values = ContentValues().apply {                                    // describe image
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, mime)
            put(MediaStore.MediaColumns.RELATIVE_PATH, dirPath)
        }

        val resolver = context.contentResolver                                  //provides app. access to content model

        if (fileType == "gif") downloadGif(context, cat.url, resolver, values)
        else downloadImage(context, cat.url, resolver, values)
    }
}

private fun downloadImage(context: Context, uri: String, resolver: ContentResolver, values: ContentValues){
                                                                                // download as Bitmap
        val bitmap = Glide.with(context)
            .asBitmap()
            .load(uri)
            .submit()
            .get()

        saveImage(context,resolver,values,bitmap,null)
}

private fun downloadGif(context: Context, uri: String, resolver: ContentResolver, values: ContentValues) {
                                                                                //download as GifDrawable
        val gifImage = Glide.with(context)
            .asGif()
            .load(uri)
            .submit()
            .get()

        val buffer = gifImage.buffer                                            //GifDrawable as ByteBuffer
        val bytes = ByteArray(buffer.capacity())                                //create ByteArray with size = buffer.size
        (buffer.duplicate().clear() as ByteBuffer).get(bytes)                   //duplicate buffer and copy into bytes

        saveImage(context,resolver,values,null,bytes)
}

private fun saveImage(context: Context, resolver: ContentResolver, values: ContentValues, bitmap: Bitmap?,gif: ByteArray?){

    var uri: Uri? = null

    try{
        uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            ?: throw IOException("failed to create new MediaStore record.")

        when {
            bitmap != null && gif == null ->                                    // if bitmap

                resolver.openOutputStream(uri)?.use {
                if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)) {
                    showToast("Failed to save Image...", context)
                } else showToast("Image saved.", context)

            } ?: throw IOException("Failed to open output stream.")

            bitmap == null && gif != null ->                                    // if gif

                resolver.openOutputStream(uri)?.use {
                    it.write(gif, 0, gif.size)
                    showToast("Image saved.", context)
                } ?: throw IOException("Failed to open output stream.")
            }
        } catch (e: IOException){
        uri?.let {
                Uri ->
            resolver.delete(Uri,null,null)
        }
        throw e
    }
}

private fun showToast(toast: String,context: Context){
    val handler = Handler(Looper.getMainLooper())
    handler.post { Toast.makeText(context, toast, Toast.LENGTH_SHORT).show() }
}
