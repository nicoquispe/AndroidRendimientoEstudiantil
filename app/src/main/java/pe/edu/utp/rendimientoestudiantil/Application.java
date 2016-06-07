package pe.edu.utp.rendimientoestudiantil;

import android.content.res.Configuration;

import com.orm.SugarApp;
import com.orm.SugarContext;

/**
 * Created by elbuenpixel on 07/06/16.
 */
public class Application extends SugarApp {

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
