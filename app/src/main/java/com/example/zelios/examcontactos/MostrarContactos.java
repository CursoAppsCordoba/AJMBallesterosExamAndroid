package com.example.zelios.examcontactos;


import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Zelios on 26/09/2017.
 */

public class MostrarContactos extends AppCompatActivity {

    //Declarar instancias globales
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private List contactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar);

//Inicializo los contactos
        contactos = new ArrayList();

//Obtengo el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

//Uso un administrador para LinearLayout
        lManager = new GridLayoutManager(this, 1);
        recycler.setLayoutManager(lManager);

//Creo un nuevo adaptador

        adapter = new ContactsAdapter(contactos);
        recycler.setAdapter(adapter);
        consulta();
    }


    public void consulta() {
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contactos.add(new Contacto(name, phoneNumber));
        }

        Collections.sort(contactos, new Comparator<Contacto>() {
            public int compare(Contacto obj1, Contacto obj2) {
                return obj1.getNombre().compareTo(obj2.getNombre());
            }
        });
        phones.close();
    }
}

