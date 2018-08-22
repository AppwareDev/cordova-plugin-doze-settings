package cordova.plugins.DozeSettings;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;

public class DozeSettings extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		
		if(action.equals("isSupported")) {
			String result = "false";
			if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) result = "true";
			callbackContext.success(result);
		}
		
		if(action.equals("isWhitelisted")) {
            try {
				if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
					Context context = cordova.getActivity().getApplicationContext();
					String packageName = context.getPackageName();
					PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
					if (pm.isIgnoringBatteryOptimizations(packageName)) {
						callbackContext.success("true");
					} else {
						callbackContext.success("false");
					}		
					return true;
				} else {
					callbackContext.error("Doze Unsupported.");
					return false;
				}
            } catch (Exception e) {
                callbackContext.error(e.getMessage());
                return false;
            }
        }
		
		if(action.equals("openSettings")) {
            try {
				if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
					
					Context context = cordova.getActivity().getApplicationContext();
					Intent intent = new Intent();
					intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
					context.startActivity(intent);		
							
					callbackContext.success("Settings opened.");
					return true;
				} else {
					callbackContext.error("Doze Unsupported.");
					return false;
				}
            } catch (Exception e) {
                callbackContext.error(e.getMessage());
                return false;
            }
        }
		
        return false;
    }

   
}
