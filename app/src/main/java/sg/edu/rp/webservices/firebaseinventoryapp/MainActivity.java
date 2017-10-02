package sg.edu.rp.webservices.firebaseinventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvItem;
    private ArrayList<Items> alItem;
    private ArrayAdapter<Items> aaItem;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference studentListRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvItem = (ListView)findViewById(R.id.listViewItems);
        alItem = new ArrayList<Items>();
        aaItem = new CustomAdapter(this,R.layout.row,alItem);
        lvItem.setAdapter(aaItem);
        //aaItem.notifyDataSetChanged();

        //get from database element item
        firebaseDatabase = FirebaseDatabase.getInstance();
        studentListRef = firebaseDatabase.getReference("items");

        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Items item = alItem.get(position);
                Intent intent = new Intent(getBaseContext(), ItemDetailActivity.class);
                intent.putExtra("Items",item);
                startActivity(intent);
            }
        });
        studentListRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.i("MainActivity", "onAddChild()");
                Items item = dataSnapshot.getValue(Items.class);
                if(item!=null){
                    item.setId(dataSnapshot.getKey());
                    alItem.add(item);
                    aaItem.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.i("MainActivity", "onChildChanged()");

                String selectedId = dataSnapshot.getKey();
                Items student = dataSnapshot.getValue(Items.class);
                if (student != null) {
                    for (int i = 0; i < alItem.size(); i++) {
                        if (alItem.get(i).getId().equals(selectedId)) {
                            student.setId(selectedId);
                            alItem.set(i, student);
                        }
                    }
                    aaItem.notifyDataSetChanged();
                }


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String selectedId = dataSnapshot.getKey();
                Items item = dataSnapshot.getValue(Items.class);
                if (item != null) {
                    for (int i = 0; i < alItem.size(); i++) {
                        if (alItem.get(i).getId().equals(selectedId)) {
                            item.setId(selectedId);
                            alItem.remove(i);
                        }
                    }
                    aaItem.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddItemActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
