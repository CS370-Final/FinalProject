package edu.cuny.qcc.cs.mod;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class Main extends AppCompatActivity {
    private final String TAG = "Main";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        boolean hasFile = checkForFile();
//        Log.d(TAG, String.valueOf(hasFile));
            setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




//
//        try {
//            c.run("1001");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        /*Unused button but can help with layout
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
//
//    public boolean checkForFile() {
//        Log.d(TAG, getFilesDir().toString());
//        File uuidFile = new File(getFilesDir(), filename);
//
//        Log.d(TAG, uuidFile.exists() + " ");
//
//        boolean hasFile;
//        if(!uuidFile.exists()) {
//            hasFile = false;
//            try {
//                uniqueID = "-1";
//                FileOutputStream fOut = openFileOutput(filename, Context.MODE_PRIVATE);
//                fOut.write(uniqueID.getBytes());
//                fOut.close();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        else{
//            hasFile = true;
//            try {
//                FileInputStream fin = openFileInput(filename);
//                int c;
//
//                while( (c = fin.read()) != -1){
//                    uniqueID = uniqueID + Character.toString((char)c);
//                }
//                fin.close();
//            }
//            catch(Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        Log.d(TAG, uuidFile.exists() + " ");
//        Log.i(TAG, uniqueID);
//        return hasFile;
//    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
