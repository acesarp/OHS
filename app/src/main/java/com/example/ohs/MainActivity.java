package com.example.ohs;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.annotation.SuppressLint;it
import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    public void connect(String name) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String url = "jdbc:jtdc:sqlserver://vps342074.ovh.net/IZY309";
        //String url = "jdbc:jtdc:sqlserver://vps342074.ovh.net/MSSQLSERVER;databaseName=IZY309";
        //String url = "jdbc:sqlserver://vps342074.ovh.net\\MSSQLSERVER;databaseName=IZY309";
        String user = "x";
        String pass = "y";

        try {
            //TextView textViewToChange = (TextView) findViewById(R.id.textViewCol5);
            //textViewToChange.setText("Hello");
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);

            Statement statement = con.createStatement();
            ResultSet resultat = statement.executeQuery("SELECT CATEGORIE FROM dbo.PPROFIL WHERE CATEGORIE = 'ac' ");
            while (resultat.next()) {

                String result = resultat.getString(1);
                TextView textViewToChange = (TextView) findViewById(R.id.textViewCol5);
                textViewToChange.setText(result);
                Log.d("My Custom Tag", result);


            }resultat.close();
            statement.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
