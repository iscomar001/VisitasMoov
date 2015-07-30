package com.omr.solutions.utils.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.omr.solutions.utils.constants.Constantes;
import com.omr.solutions.utils.date.DateUtils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class FileUtils implements Constantes{

	private static final String LOG_TAG = "FileUtils";
	Context context;
	File fileDir;
	
	public FileUtils(Context context) {
		this.context = context;
		fileDir = context.getFilesDir();
	}
	
	public void createFile(String fileName){
		//File file = new File(fileDir, fileName);
		//file.c
	}
	
	public void write(String fileName, String content){
		FileOutputStream outputStream;

		try {
		  outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
		  outputStream.write(content.getBytes());
		  outputStream.close();
		} catch (Exception e) {
		  e.printStackTrace();
		}
	}
	/* Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}

	/* Checks if external storage is available to at least read */
	public boolean isExternalStorageReadable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state) ||
	        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	        return true;
	    }
	    return false;
	}
	
	public static File getAlbumStorageDir(String albumName) {
	    // Get the directory for the user's public pictures directory. 
	    File file = new File(Environment.getExternalStoragePublicDirectory(
	            Environment.DIRECTORY_PICTURES), albumName);
	    if (!file.mkdirs()) {
	        Log.e(LOG_TAG, "Directory not created");
	    }
	    return file;
	}
	
    public static File getTempImageFile(String albumName, String catTag){

        File mediaStorageDir = getAlbumStorageDir(albumName);

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d(catTag, "failed to create directory: " + albumName);
                return null;
            }
        }

        // Create a media file name
        String timeStamp = DateUtils.getStrDateActual(FORMAT_DATE_NORMAL_STRING);
        
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_"+ timeStamp + ".jpg");

        return mediaFile;
    }
    
    public static File getTempImageFile(String albumName,String fileName, String catTag){

        File mediaStorageDir = getAlbumStorageDir(albumName);

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d(catTag, "failed to create directory: " + albumName);
                return null;
            }
        }

        // Create a media file name
        
        File mediaFile = new File(mediaStorageDir.getPath()+ File.separator + fileName);

        return mediaFile;
    }
	
	public void delete(String fileName){
		context.deleteFile(fileName);
	}
	public void delete(File file){
		file.delete();
	}
	
	public static void copy(File src, File dst, String tagLog) {
		InputStream in;
		try {
			in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dst);

			// Transfer bytes from in to out
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		} catch (Exception e) {
			Log.d(tagLog, "failed to create directory: ");
		}
	}
}
