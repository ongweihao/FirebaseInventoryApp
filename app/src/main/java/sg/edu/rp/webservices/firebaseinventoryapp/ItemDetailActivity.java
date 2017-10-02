package sg.edu.rp.webservices.firebaseinventoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ItemDetailActivity extends AppCompatActivity {
    private static final String TAG = "StudentDetailsActivity";

    private EditText etName, etCost;
    private Button btnUpdate, btnDelete;
    private Items item;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference studentListRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        etName = (EditText)findViewById(R.id.editTextName);
        etCost = (EditText)findViewById(R.id.editTextCost);
        btnUpdate = (Button)findViewById(R.id.buttonUpdate);
        btnDelete = (Button)findViewById(R.id.buttonDelete);

        //get the item under items in database
        firebaseDatabase = FirebaseDatabase.getInstance();
        studentListRef = firebaseDatabase.getReference("/items");

        Intent intent = getIntent();
        item = (Items) intent.getSerializableExtra("Items");

        //Display Student details as retrieved from the intent
        etName.setText(item.getName());
        etCost.setText(String.valueOf(item.getCost()));


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = item.getId();

                item.setId(null);
                item.setName(etName.getText().toString());
                item.setCost(Integer.parseInt(etCost.getText().toString()));

                studentListRef.child(id).setValue(item);
                Toast.makeText(getApplicationContext(), "Item record updated successfully", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();


            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = item.getId();
                studentListRef.child(id).removeValue();
                setResult(RESULT_OK);
                Toast.makeText(getApplicationContext(), "Item record deleted successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
