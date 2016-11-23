package waitingpiri.waitingpiri.location;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.EditText;

import waitingpiri.waitingpiri.MainActivity;
import waitingpiri.waitingpiri.SplashActivity;
import waitingpiri.waitingpiri.connection.JSONResult;

/**
 * Created by sergio on 17/09/16.
 */
public class LocationService extends Service implements LocationListener {

    private static final String TAG = "waitingPiriService";
    private static final long SEND_LOCATION_TIME = (3000);
    private static final int PERMISSION_LOCATION_REQUEST_COD = 1;

    private LocationManager locationManager;
    private JSONResult json;

    private int flag = 0;
    private String nroColectivo = "";

    @Override
    public void onCreate() {
        Log.i("waitingPiriService", "servicio iniciado..");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("waitingPiriService", "servicio ejecutado..");

        this.locationManager = SplashActivity.locationManager;
        this.flag = MainActivity.FLAG;
        this.nroColectivo = MainActivity.NRO_COLECTIVO;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ){
                ActivityCompat.requestPermissions(
                        SplashActivity.splashActivity,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSION_LOCATION_REQUEST_COD);
            }
        }

        this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, SEND_LOCATION_TIME, 0, this);
        this.json = new JSONResult();

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i("waitingPiriService", "servicio terminado..");
        super.onDestroy();
    }

    /** Location Listener **/

    @Override
    public void onLocationChanged(Location location) {
        Log.i("waitingPiriService", "location changed.. latitud: " + location.getLatitude() + " longitud: " + location.getLongitude() + " FLAG: " + this.flag + " Colectivo: " + this.nroColectivo);
        new SendLocationTask(this.nroColectivo, location.getLatitude() + "", location.getLongitude() + "", this.flag).execute();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    /**
     * Tarea asincrona que envia la ubicacion..
     */
    class SendLocationTask extends AsyncTask<String, String, String> {

        String nroChasis, latitud, longitud;
        int flag;

        public SendLocationTask(String nroChasis, String latitud, String longitud, int flag) {
            this.nroChasis = nroChasis;
            this.latitud = latitud;
            this.longitud = longitud;
            this.flag = flag;
        }

        @Override
        protected String doInBackground(String... arg0) {

            try {
                String succes = json.addLocation(this.nroChasis, this.latitud, this.longitud, this.flag);
                Log.i(TAG, succes);
            } catch (Exception e) {
                e.printStackTrace();
                Log.i(TAG, e.getMessage());
            }
            return null;
        }
    }
}
