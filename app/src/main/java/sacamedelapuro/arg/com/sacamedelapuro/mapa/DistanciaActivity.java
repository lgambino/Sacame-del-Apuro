package sacamedelapuro.arg.com.sacamedelapuro.mapa;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sacamedelapuro.arg.com.sacamedelapuro.R;

public class DistanciaActivity extends AppCompatActivity {

    Button btnAceptar;
    EditText txtKms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_distancia);

        //final Bundle extras = getIntent().getExtras();

        txtKms = (EditText) findViewById(R.id.editTextKilometros);

        btnAceptar = (Button) findViewById(R.id.btnMarcar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                if(txtKms.getText().toString().matches("") || "".equals(txtKms.getText().toString())){
                    setResult(MapaActivity.RESULT_CANCELED,returnIntent);
                    finish();
                }
                else {
                    float res = Float.valueOf(txtKms.getText().toString());
                    returnIntent.putExtra("activity_pedido_distancia", res);
                    setResult(MapaActivity.RESULT_OK, returnIntent);
                    finish();
                }
            }
        });
        txtKms.requestFocus();
    }
}
