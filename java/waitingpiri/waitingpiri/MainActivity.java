package waitingpiri.waitingpiri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void ida(View v){
        Intent intent= new Intent(MainActivity.this,MapaIdaActivity.class);
        MainActivity.this.startActivity(intent);
    }
    public void vuelta(View v){
        Intent intent= new Intent(MainActivity.this,MapaVueltaActivity.class);
        MainActivity.this.startActivity(intent);
    }
}
