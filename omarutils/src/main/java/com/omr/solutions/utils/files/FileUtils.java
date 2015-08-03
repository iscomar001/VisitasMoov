package com.omr.solutions.utils.files;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.omr.solutions.utils.constants.Constantes;
import com.omr.solutions.utils.date.DateUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils implements Constantes{

	private static final String TAG = "FileUtils";
	Context context;
	File fileDir;
	
	public FileUtils(Context context) {
		this.context = context;
		fileDir = context.getFilesDir();
	}
	
	public long freeSpace(){
		return  context.getExternalFilesDir(null).getFreeSpace();
	}
	
	public void write(String fileName, String content) throws FileUtilsException{


		FileOutputStream outputStream;

		if (isExternalStorageWritable()){

			try {
				outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
				outputStream.write(content.getBytes());
				outputStream.close();
			} catch (Exception e) {
				Log.e(TAG, "write " + e.getMessage());
				throw new FileUtilsException("write " + e.getMessage());
			}
		}else {
			Log.d(TAG, "write storage no writable");
			throw new FileUtilsException("write storage no writable");
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
	
	public static File getPublicAlbumStorageDir(String albumName) throws FileUtilsException {
	    // Get the directory for the user's public pictures directory. 
	    File file = new File(Environment.getExternalStoragePublicDirectory(
	            Environment.DIRECTORY_PICTURES), albumName);
	    if (!file.mkdirs()) {
	        Log.e(TAG, "Directory not created");
			throw new FileUtilsException("Directory not created");
	    }
	    return file;
	}


	
    public static File getTempImageFile(String albumName, String catTag) throws FileUtilsException{

        File mediaStorageDir = getPublicAlbumStorageDir(albumName);

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
    
    public static File getTempImageFile(String albumName,String fileName, String catTag) throws FileUtilsException{

        File mediaStorageDir = getPublicAlbumStorageDir(albumName);

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
	
	public boolean delete(String fileName) throws FileUtilsException{
		return new File(fileName).delete();
	}
	public boolean deletePrivate(String fileName) throws FileUtilsException{
		return context.deleteFile(fileName);
	}
	public boolean delete(File file) throws FileUtilsException{
		return file.delete();
	}
	public boolean deletePrivate(File file) throws FileUtilsException{
		return context.deleteFile(file.getName());
	}
	
	public static void copy(File src, File dst, String tagLog) throws FileUtilsException {
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
			Log.d(tagLog, "copy : " + e.getMessage());
			throw new FileUtilsException("copy : " + e.getMessage());
		}
	}
}
