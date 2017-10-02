package sg.edu.rp.webservices.firebaseinventoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItemActivity extends AppCompatActivity {


    private EditText etName, etCost;
    private Button btnAdd;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference studentListRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        etName = (EditText)findViewById(R.id.editTextName);
        etCost = (EditText)findViewById(R.id.editTextCost);
        btnAdd = (Button)findViewById(R.id.buttonAdd);

        //in firebase database create a ity
        firebaseDatabase = FirebaseDatabase.getInstance();
        studentListRef = firebaseDatabase.getReference("ity/");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                int age = Integer.parseInt(etCost.getText().toString());
                Items item = new Items(name,age);
                studentListRef.push().setValue(item);
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);

            }
        });


    }
}
