package sg.edu.rp.webservices.firebaseinventoryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Items> toDoList;

    public CustomAdapter(Context context, int resource,
                         ArrayList<Items> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        toDoList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater) parent_context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Inflate a new view hierachy from the specified xml resource (layout_id)
        //and return the root View of the inflated hierachy.
        View rowView = inflater.inflate(layout_id,parent,false);

        //obtain the UI element
        final TextView tvTv = (TextView) rowView.findViewById(R.id.textView);
        final TextView tvTv2 = (TextView) rowView.findViewById(R.id.textView2);
        Items currentItem = toDoList.get(position);

        //Set the TextView to display corresponding information
        tvTv.setText(currentItem.getName());
        tvTv2.setText("$"+currentItem.getCost());


        //set the image depending on 'isImportant' field
        //return the View corresponding to the data at the specific position.
        return rowView;
    }



}