package com.example.zelios.examcontactos;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Zelios on 26/09/2017.
 */

public class CRUDContacts extends AppCompatActivity {

    //ALTA DEL CONTACTO
    public static Intent alta(String name, String phone) {
        // Creates a new Intent to insert a contact
        Intent contactIntent = new Intent(Intent.ACTION_INSERT);
        contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        //contactIntent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        contactIntent.putExtra(ContactsContract.Intents.Insert.NAME, name);
        contactIntent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
        return contactIntent;
    }

    //BUSQUEDA DE CONTACTO, RETORNO LA URI DEL CONTACTO
    public static Uri buscar(Cursor phones, String telf, Boolean del) {
        //Cursor mCursor;
        // The index of the lookup key column in the cursor
        int mLookupKeyIndex;
        // The index of the contact's _ID value
        int mIdIndex;
        // The lookup key from the Cursor
        String mCurrentLookupKey;
        // The _ID value from the Cursor
        long mCurrentId;
        // A content URI pointing to the contact
        Uri mSelectedContactUri = null;

        while (phones.moveToNext()) {

            //String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            if (phoneNumber.equals(telf)) {

                mLookupKeyIndex = phones.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY);
                mCurrentLookupKey = phones.getString(mLookupKeyIndex);
                mIdIndex = phones.getColumnIndex(ContactsContract.Contacts._ID);
                mCurrentId = phones.getLong(mIdIndex);


                mSelectedContactUri = ContactsContract.Contacts.getLookupUri(mCurrentId, mCurrentLookupKey);

                if(del){
                    mSelectedContactUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, mCurrentLookupKey);
                }

                break;
            }
        }
        phones.close();
        return mSelectedContactUri;
    }

    //EDITAR CONTACTO
    public static Intent editContacto(Uri uriContact) {

        Intent editIntent;
        editIntent = new Intent(Intent.ACTION_EDIT);
        editIntent.setDataAndType(uriContact, ContactsContract.RawContacts.CONTENT_ITEM_TYPE);

        return editIntent;
    }

    //ELIMINAR CONTACTO
    public static void delContacto(ContentResolver resolv, Uri uriContact) {
        resolv.delete(uriContact, null, null);
    }

}
