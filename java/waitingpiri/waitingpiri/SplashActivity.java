package waitingpiri.waitingpiri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        this.showSplash();
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
                    SplashActivity.this.startActivity(intent);
                }
            }
        };
        splash.start();
    }
}
