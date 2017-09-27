package com.example.zelios.examcontactos;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Zelios on 26/09/2017.
 */

public class AltaContacto extends AppCompatActivity implements View.OnClickListener {

    EditText addName;
    EditText addPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta);

        //Add
        Button guardar = (Button) findViewById(R.id.btnSave);
        guardar.setOnClickListener(this);
        Button cancelar = (Button) findViewById(R.id.btnAtras);
        cancelar.setOnClickListener(this);

        addName = (EditText) findViewById(R.id.addName);
        addPhone = (EditText) findViewById(R.id.addPhone);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAtras:
                finish();
                Toast.makeText(this,"Volviendo...", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnSave:
                Toast.makeText(this,"Rellenando datos de contacto...", Toast.LENGTH_LONG).show();
                Intent intent=CRUDContacts.alta(addName.getText().toString(), addPhone.getText().toString());
                startActivity(intent);
                break;
        }
    }
}