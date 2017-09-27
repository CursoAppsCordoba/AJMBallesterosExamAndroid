package com.example.zelios.examcontactos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Main
        Button add = (Button) findViewById(R.id.btnAlta);
        add.setOnClickListener(this);
        Button search = (Button) findViewById(R.id.btnBuscar);
        search.setOnClickListener(this);
        Button show = (Button) findViewById(R.id.btnMostrar);
        show.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnAlta:
                //Toast.makeText(this,"Boton Alta", Toast.LENGTH_LONG).show();
                intent = new Intent(this, AltaContacto.class);
                startActivity(intent);
                break;

            case R.id.btnBuscar:
                //Toast.makeText(this,"Boton Buscar", Toast.LENGTH_LONG).show();
                intent = new Intent(this, BuscarContacto.class);
                startActivity(intent);
                break;

            case R.id.btnMostrar:
                //Toast.makeText(this,"Boton Mostrar", Toast.LENGTH_LONG).show();
                intent = new Intent(this, MostrarContactos.class);
                startActivity(intent);
                break;
        }
    }
}
