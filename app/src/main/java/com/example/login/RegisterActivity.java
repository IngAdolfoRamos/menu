package com.example.login;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText usernameTIET, passwordTIET, nameTIET, emailTIET, phoneTIET;
    TextInputLayout nameTIL, emailTIL, usernameTIL, passwordTIL, phoneTIL;
    Button registerB, cancelB;
    ListView listView;

    DatabaseHelper databaseHelper;
    SQLiteDatabase register, getList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper = DatabaseHelper.getInstance(this);

        getViews();
        saveNewUser();
        goToMainActivity();
        showAllInListView();

    }

    private void showAllInListView(){
        getList = databaseHelper.getWritableDatabase();
        ArrayList<HashMap<String,String>> userList = new ArrayList<>();
        String getAllRecords = "SELECT * FROM users";
        Cursor cursor = getList.rawQuery(getAllRecords,null);
        while(cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex("name")));
            user.put("email", cursor.getString(cursor.getColumnIndex("email")));
            user.put("phone",cursor.getString(cursor.getColumnIndex("phone")));
            userList.add(user);
        }

        ListAdapter listAdapter = new SimpleAdapter(RegisterActivity.this, userList, R.layout.list_item, new String[]{"name","email","phone"}, new int[]{R.id.idTV, R.id.nameTV, R.id.phoneTV});
        listView.setAdapter(listAdapter);
    }

    public void goToMainActivity(){
        cancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });
    }

    public void saveNewUser(){
        /*Save button*/
        registerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
validateData();
                /*Getting the value fo the fields*/
/*                String name = nameTIET.getText().toString();
                String email = emailTIET.getText().toString();
                String username = usernameTIET.getText().toString();
                String password = passwordTIET.getText().toString();

                *//*Validating empty fields*//*
                if (!name.isEmpty() && !email.isEmpty() && !username.isEmpty() && !password.isEmpty()){

                    *//*Connecting to the DB*//*
                    register = databaseHelper.getWritableDatabase();

                    *//*Saving values*//*
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name",name);
                    contentValues.put("email",email);
                    contentValues.put("username",username);
                    contentValues.put("password",password);

                    //validateData();

                    *//*Inserting into DB*//*
                    register.insert("users",null, contentValues);

                    *//*Giving feedback to the user*//*
                    Toast.makeText(RegisterActivity.this, "Registrado satisfactoriamente", Toast.LENGTH_SHORT).show();

                    *//*Focusing name field*//*
                    nameTIET.requestFocus();

                    *//*Redirecting to login*//*
                    goToLogin();
                }
                else{
                    //validateData();
                    *//*Giving error feedback to the user*//*
                    Toast.makeText(RegisterActivity.this, "Debes de completar todos los campos", Toast.LENGTH_SHORT).show();
                }*/

            }
        });
    }

    public void goToLogin(){
        Intent goToMainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(goToMainActivity);
    }

    private void getViews(){
        passwordTIET = findViewById(R.id.passwordTIET);
        emailTIET = findViewById(R.id.emailTIET);
        nameTIET = findViewById(R.id.nameTIET);
        usernameTIET = findViewById(R.id.usernameTIET);
        registerB = findViewById(R.id.registerB);
        cancelB = findViewById(R.id.cancelB);
        nameTIL = findViewById(R.id.nameTIL);
        emailTIL = findViewById(R.id.emailTIL);
        phoneTIL = findViewById(R.id.phoneTIL);
        phoneTIET = findViewById(R.id.phoneTIET);
        /*usernameTIL = findViewById(R.id.usernameTIL);
        passwordTIL = findViewById(R.id.passwordTIL);*/
        listView = findViewById(R.id.listView);
    }

    private boolean validateName(String name){
        Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
        if(!pattern.matcher(name).matches() || name.length() > 10){
            nameTIET.setError("Nombre invalido");
            return false;
        }
        else{
            nameTIET.setError(null);
        }
        return true;
    }

    private boolean validatePho(String phone){
        Pattern pattern = Pattern.compile("^[0-9 ]+$");
        if(!pattern.matcher(phone).matches() || phone.length() > 10){
            phoneTIET.setError("Telefono invalido");
            return false;
        }
        else{
            phoneTIET.setError(null);
        }
        return true;
    }


    private boolean validateUsername(String username){
        Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
        if(!pattern.matcher(username).matches() || username.length() > 10){
            usernameTIET.setError("Usuario invalido");
            return false;
        }
        else{
            usernameTIET.setError(null);
        }
        return true;
    }

    private boolean validatePassword(String password){
        Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
        if(!pattern.matcher(password).matches() || password.length() > 10){
            passwordTIET.setError("Contraseña invalida");
            return false;
        }
        else{
            passwordTIET.setError(null);
        }
        return true;
    }

        private boolean validatePhone(String telefono) {
        if (!Patterns.PHONE.matcher(telefono).matches())
        {
            phoneTIET.setError("Teléfono inválido");
            return false;
        }
        else
        {
            phoneTIET.setError(null);         }
            return true;
        }

    private boolean validateEmail(String email){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailTIET.setError("Email invalido");
            return false;
        }
        else{
            emailTIET.setError(null);
        }
        return true;
    }

    private void validateData(){
        String name = nameTIL.getEditText().getText().toString();
        String email = emailTIL.getEditText().getText().toString();
        String phone = phoneTIL.getEditText().getText().toString();
        /*String username = usernameTIL.getEditText().getText().toString();
        String password = passwordTIL.getEditText().getText().toString();*/
//        int phone = Integer.parseInt(phoneTIL.getEditText().toString());

        boolean a = validateName(name);
        boolean b = validateEmail(email);
        boolean e = validatePhone(phone);
/*        boolean c = validateUsername(username);
        boolean d = validatePassword(password);*/

        if (a && b && e){

            String names = nameTIET.getText().toString();

            String phones = phoneTIET.getText().toString();

            String emails = emailTIET.getText().toString();
            /*String usernames = usernameTIET.getText().toString();
            String passwords = passwordTIET.getText().toString();*/
            register = databaseHelper.getWritableDatabase();

            /*Saving values*/
            ContentValues contentValues = new ContentValues();
            contentValues.put("name",names);
            contentValues.put("email",emails);
/*            contentValues.put("username",usernames);
            contentValues.put("password",passwords);*/
            contentValues.put("phone",phones);

            //validateData();

            /*Inserting into DB*/
            register.insert("users",null, contentValues);

            /*Giving feedback to the user*/
            Toast.makeText(RegisterActivity.this, "Registrado satisfactoriamente", Toast.LENGTH_SHORT).show();

            /*Focusing name field*/
            nameTIL.requestFocus();

            /*Redirecting to login*/
            //goToLogin();
            Toast.makeText(this, "Se guarda el registro", Toast.LENGTH_LONG).show();
            showAllInListView();
        }

    }
}
