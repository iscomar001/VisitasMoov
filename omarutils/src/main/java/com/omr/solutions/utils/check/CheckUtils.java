package com.omr.solutions.utils.check;

import android.content.Context;
import android.content.pm.PackageManager;

import com.omr.solutions.utils.constants.Constantes;

public class CheckUtils implements Constantes {

	public static boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }
	
}
