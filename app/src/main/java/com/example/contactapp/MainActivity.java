package com.example.contactapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.contactapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<Contacts> contactList;
    private ContactsAdapter contactsAdapter;
    private AppDataBase appDataBase;
    private ContactDao contactDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        binding.rvContacts.setLayoutManager(new LinearLayoutManager(this));
        contactList = new ArrayList<Contacts>();
        contactsAdapter = new ContactsAdapter(contactList);
        binding.rvContacts.setAdapter(contactsAdapter);

        contactList.add(new Contacts("Ngoc","123456","ngoc244@gmail.com"));
        contactList.add(new Contacts("Nam","154362","nam@gmail.com"));
        contactList.add(new Contacts("Dung","496523","dung@gmail.com"));
        contactsAdapter.notifyDataSetChanged();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                appDataBase = AppDataBase.getInstance(getApplicationContext());
                contactDao = appDataBase.contactDao();
                contactDao.insert(new Contacts("Ngoc","123456","ngoc244@gmail.com"));
                contactDao.insert(new Contacts("Nam","154362","nam@gmail.com"));
                contactDao.insert(new Contacts("Dung","496523","dung@gmail.com"));
            }
        });
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NewContactActivity.class);
                startActivity(intent);
            }
        });
        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}