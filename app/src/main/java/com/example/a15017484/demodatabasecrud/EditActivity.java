package com.example.a15017484.demodatabasecrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EditActivity extends AppCompatActivity {
    TextView tvID;
    EditText etContent;
    Button btnUpdate, btnDelete;
    Note data;
    DBHelper dbh = new DBHelper(EditActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        tvID = (TextView) findViewById(R.id.tvID);
        etContent = (EditText) findViewById(R.id.etContent);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        Intent i = getIntent();
        data = (Note) i.getSerializableExtra("data");

        tvID.setText("ID: " + data.getId());
        etContent.setText(data.getNoteContent());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                data.setNoteContent(etContent.getText().toString());
                dbh.updateNote(data);
                dbh.close();

                setResult(RESULT_OK, i);
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                dbh.deleteNote(data.getId());
                dbh.close();
                setResult(RESULT_OK, i);
                finish();
            }
        });

    }
}
