package waitingpiri.waitingpiri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import waitingpiri.waitingpiri.location.LocationService;

public class MainActivity extends AppCompatActivity {

    public final static int FLAG_IDA = 0;
    public final static int FLAG_VUELTA = 1;
    public static int FLAG;
    public static String NRO_COLECTIVO;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.editText = (EditText) findViewById(R.id.nro_colectivo);
    }
    public void ida(View v){
        FLAG = FLAG_IDA;
        NRO_COLECTIVO = editText.getText().toString();
        Intent intent = new Intent(this, LocationService.class);
        startService(intent);
    }
    public void vuelta(View v){
        FLAG = FLAG_VUELTA;
        NRO_COLECTIVO = editText.getText().toString();
        Intent intent = new Intent(this, LocationService.class);
        startService(intent);
    }
}
