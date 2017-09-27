package com.example.zelios.examcontactos;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Zelios on 26/09/2017.
 */

public class BuscarContacto extends AppCompatActivity implements View.OnClickListener {


    private TextView edtFinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar);

        edtFinder = (EditText) findViewById(R.id.finder);
        Button search = (Button) findViewById(R.id.goContact);
        search.setOnClickListener(this);
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        String finder = edtFinder.getText().toString();
        Uri uriContact;

        switch (v.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.goContact:
                uriContact = CRUDContacts.buscar(phones, finder, false);
                if (uriContact != null) {
                    Toast.makeText(getApplicationContext(), "Se ha encontrado el contacto", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No se han encontrado coincidencias", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}



