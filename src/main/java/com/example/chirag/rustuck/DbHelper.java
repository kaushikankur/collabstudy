package com.example.chirag.rustuck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ankur kaushik on 10-11-2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="RUapp_db";
    // Database Version
    private static final int DATABASE_VERSION = 1;

    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_PASSWORD= "password";
   // public static final String CONTACTS_COLUMN_CITY = "place";
    public static final String CONTACTS_COLUMN_PHONE = "phone";


    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table contacts " +
                "(id integer primary key, name text,phone text,email text,password text)");
    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + CONTACTS_TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    boolean addContact(ContactData contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CONTACTS_COLUMN_NAME, contact.getName()); // Contact Name
        values.put(CONTACTS_COLUMN_EMAIL, contact.getEmail()); // Contact Phone
        values.put(CONTACTS_COLUMN_PHONE, contact.getPhoneNumber()); // Contact Phone
        values.put(CONTACTS_COLUMN_PASSWORD, contact.getPassword()); // Contact Phone

        // Inserting Row
       long result= db.insert(CONTACTS_TABLE_NAME, null, values);
        db.close(); // Closing database connection
        if(result==-1)
            return false;
        else
            return true;
    }

    /*// Getting single contact
    ContactData getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(CONTACTS_TABLE_NAME, new String[] { CONTACTS_COLUMN_ID,
                        CONTACTS_COLUMN_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return contact;
    }*/

    // Getting All Contacts
    public List<ContactData> getAllContacts() {
        List<ContactData> contactList = new ArrayList<ContactData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + CONTACTS_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ContactData contact = new ContactData();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setEmail(cursor.getString(2));
                contact.setPassword(cursor.getString(3));
                contact.setPhoneNumber(cursor.getString(4));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

   /* // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CONTACTS_TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }
*/

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + CONTACTS_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
