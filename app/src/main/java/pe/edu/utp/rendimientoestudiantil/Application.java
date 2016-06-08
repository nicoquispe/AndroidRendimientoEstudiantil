package pe.edu.utp.rendimientoestudiantil;

import android.content.res.Configuration;

import com.orm.SugarApp;
import com.orm.SugarContext;

import pe.edu.utp.rendimientoestudiantil.models.Teacher;

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

        Teacher teacher = new Teacher("Ubaldo","Lizardo Silva","c0021@grupoutp.edu.pe","123456");
        teacher.save();
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
