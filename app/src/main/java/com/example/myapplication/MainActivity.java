package com.example.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import static java.lang.Class.*;


public class MainActivity extends AppCompatActivity {

    Thread thrd1,thrd2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
        new DB().execute();
    }
    public void testDB() {
        // TextView tv = (TextView) this.findViewById(R.id.tv_data);

    }

    private class DB extends AsyncTask<String , Void, String>

    {
        Connection con;
        @Override
        protected String doInBackground(String ...params) {
            try {

                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String connectionString = "jdbc:mysql://192.168.0.103:3306/" + "employee" + "?user=" + "elearn" + "&password=" + "lakhinsu" + "&useUnicode=true&characterEncoding=UTF-8&";
                Connection con = DriverManager
                        .getConnection(connectionString);

                // perfect

                // localhost

                /*
                 * Connection con = DriverManager .getConnection(
                 * "jdbc:mysql://192.168.1.5:3306/databasename?user=root&password=123"
                 * );
                 */

                // online testing


                String result = "Database connection success\n";
                Statement st;
                st = con.createStatement();

                ResultSet rs = st.executeQuery("select * from employee.user ");
                ResultSetMetaData rsmd;
                rsmd = rs.getMetaData();


                while (rs.next()) {

                    result += rsmd.getColumnName(1) + ": " + rs.getString(1) + "\n";
                    Log.d("testing",""+rs.getString("Name"));
                    
                }
                // tv.setText(result);
            } catch (Exception e) {
                e.printStackTrace();
                // tv.setText(e.toString());
            }
            return null;
        }
    }

}