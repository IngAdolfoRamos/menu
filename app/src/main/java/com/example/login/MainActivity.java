package com.example.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    SQLiteDatabase login;

    TextInputEditText usernameTIET, passwordTIET;
    Button registerB, loginB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = DatabaseHelper.getInstance(this);
        getViews();
        goToRegister();
        login();
    }

    private void login(){
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = databaseHelper.getReadableDatabase();
                String[] columns = new String[] {databaseHelper.COLUMN_USER, databaseHelper.COLUMN_PASSWORD};
                String[] values = new String[]{usernameTIET.getText().toString(),passwordTIET.getText().toString()};
                Cursor cursor = login.query(databaseHelper.TABLE_USERS,columns,"username = ? and password = ?",values,null,null,null);
                int count = cursor.getCount();
                if (count == 1){
                    Intent goToHomeActivity = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(goToHomeActivity);
                    Toast.makeText(MainActivity.this, "Bienvenido " + usernameTIET.getText().toString(), Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Verifica tus credenciales", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void goToRegister(){
        registerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(goToRegister);
            }
        });
    }

    private void getViews(){
        passwordTIET = findViewById(R.id.passwordTIET);
        usernameTIET = findViewById(R.id.usernameTIET);
        registerB = findViewById(R.id.registerB);
        loginB = findViewById(R.id.loginB);
    }
}
