package com.example.a15017484.demodatabasecrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAdd, btnEdit, btnRetrieve;
    TextView tvDBContent;
    EditText etContent;
    ArrayList<String> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize the variables with UI here
        al = new ArrayList<String>();
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnRetrieve = (Button) findViewById(R.id.btnRetrieve);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        etContent = (EditText) findViewById(R.id.etContent);
        tvDBContent = (TextView) findViewById(R.id.tvDBContent);
        //Add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = etContent.getText().toString();
                DBHelper dbh = new DBHelper(MainActivity.this);
                long row_affected = dbh.insertNote(data);
                dbh.close();

                if (row_affected != -1) {
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Retrieve
        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                al.clear();
                al.addAll(dbh.getAllNotes());
                dbh.close();

                String txt = "";
                for (int i = 0; i < al.size(); i++) {
                    String tmp = al.get(i);
                    txt += tmp + "\n";
                }
                tvDBContent.setText(txt);
            }
        });
    }

}
