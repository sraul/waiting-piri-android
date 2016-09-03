package waitingpiri.waitingpiri;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity implements LocationListener {

    private static final long SEND_LOCATION_TIME = (5000);
    private static final int PERMISSION_LOCATION_REQUEST_COD = 1;

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        this.checkLocationAPI();
        this.verificarGps();
    }

    /**
     * verifica la API de localizacion..
     */
    private void checkLocationAPI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ){
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSION_LOCATION_REQUEST_COD);
            }
        }

        this.locationManager = (LocationManager) SplashActivity.this
                .getSystemService(Context.LOCATION_SERVICE);
        this.locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, SEND_LOCATION_TIME, 10, this);
    }

    private void showSplash() {
        Thread splash = new Thread() {
            static final int ESPERA = 3000;

            @Override
            public void run() {
                try {
                    sleep(ESPERA);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.finish();
                    SplashActivity.this.startActivity(intent);
                }
            }
        };
        splash.start();
    }

    /**
     * verifica si esta activo el gps..
     */
    private void verificarGps() {
        if (SplashActivity.this.locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            this.showSplash();
        } else {
            this.solicitarGPS();
        }
    }

    /*
	 * solicita la activacion del gps..
	 */
    private void solicitarGPS() {
        new AlertDialog.Builder(SplashActivity.this)

                .setTitle("Activar GPS")
                .setMessage("GPS deshabilitado. Desea activarlo?")
                .setCancelable(false)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent gpsSetting = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(gpsSetting);
                        SplashActivity.this.verificarGps();
                    }
                })

                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SplashActivity.this.finish();
                    }
                })
                .create().show();
    }

    @Override
    public void onLocationChanged(Location location) {

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
}
