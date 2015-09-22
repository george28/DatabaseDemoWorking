package com.rabor.databasedemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    EditText inputEditText;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEditText = (EditText) findViewById(R.id.inputEditText);

        dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();
    }

    // add a product to the database
    public void addClick(View view) {
        Products products = new Products(inputEditText.getText().toString());
        dbHandler.addProduct(products);
    }

    // delete items
    public void deleteClick(View view) {
        String inputText = inputEditText.getText().toString();
        dbHandler.deleteProduct(inputText);
        printDatabase();
    }

    public void printDatabase() {
        List<Products> dbString = dbHandler.databaseToString();
        String log="";
        for(Products pn : dbString) {
            log += "Id: " + pn.get_id() + " Name: " + pn.get_productname() + "\n";
        }

        // print in logcat
        Log.i(TAG, log);
        inputEditText.setText("");
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
