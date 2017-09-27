package com.example.zelios.examcontactos;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;


/**
 * Created by Zelios on 26/09/2017.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactoViewHolder> {

    private List<Contacto> contactos;

    public static class ContactoViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        private TextView nombre;
        private TextView telf;


        public ContactoViewHolder(View v) {
            super(v);

            nombre = (TextView) v.findViewById(R.id.nombre);
            telf = (TextView) v.findViewById(R.id.telf);
            Button btnEliminar = (Button) v.findViewById(R.id.btnEliminar);
            Button btnEditar = (Button) v.findViewById(R.id.btnEditar);


            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(final View v) {
                    AlertDialog.Builder cuadro = new AlertDialog.Builder(v.getContext());
                    cuadro.setMessage("¿Seguro que quieres editar este contacto?");

                    cuadro.setPositiveButton(android.R.string.yes, new Dialog.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ContentResolver resolv = v.getContext().getContentResolver();
                            Cursor phones = resolv.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
                            String finder = telf.getText().toString();

                            Uri uriContact = CRUDContacts.buscar(phones, finder, false);

                            Intent intent = CRUDContacts.editContacto(uriContact);
                            v.getContext().startActivity(intent);
                            ((Activity) v.getContext()).finish();
                        }
                    });
                    cuadro.setNegativeButton(android.R.string.no, null);
                    cuadro.show();
                }
            });


            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(final View v) {
                    AlertDialog.Builder cuadro = new AlertDialog.Builder(v.getContext());
                    cuadro.setMessage("¿Seguro que quieres eliminar el contacto?");

                    cuadro.setPositiveButton(android.R.string.yes, new Dialog.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ContentResolver resolv = v.getContext().getContentResolver();
                            Cursor phones = resolv.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
                            String finder = telf.getText().toString();

                            Uri uriContact = CRUDContacts.buscar(phones, finder, true);

                            CRUDContacts.delContacto(resolv, uriContact);

                            Intent intent = new Intent(v.getContext(), MostrarContactos.class);
                            v.getContext().startActivity(intent);
                            ((Activity) v.getContext()).finish();
                        }
                    });
                    cuadro.setNegativeButton(android.R.string.no, null);
                    cuadro.show();
                }
            });
        }
    }

    public ContactsAdapter(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemcontacto, viewGroup, false);
        return new ContactoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactoViewHolder viewHolder, int i) {
        viewHolder.nombre.setText(contactos.get(i).getNombre());
        viewHolder.telf.setText(contactos.get(i).getTelf());
    }
}

