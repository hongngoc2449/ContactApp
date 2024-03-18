package com.example.contactapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Contacts.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {
    public abstract ContactDao contactDao();

    private static AppDataBase instance;
    public static AppDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,
                    AppDataBase.class, "contact").build();
        }
        return instance;
    }
}
