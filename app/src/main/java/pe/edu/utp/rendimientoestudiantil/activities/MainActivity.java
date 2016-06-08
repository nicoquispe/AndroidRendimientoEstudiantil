package pe.edu.utp.rendimientoestudiantil.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.SessionManager;
import pe.edu.utp.rendimientoestudiantil.adapters.InstitutionAdapter;
import pe.edu.utp.rendimientoestudiantil.db.DatabaseAccess;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.Institution;
import pe.edu.utp.rendimientoestudiantil.models.Student;
import pe.edu.utp.rendimientoestudiantil.models.Teacher;
import pe.edu.utp.rendimientoestudiantil.models.TeacherInstitution;

public class MainActivity extends BaseActivity {


    RecyclerView.LayoutManager mInstitutionLayoutManager;
    FloatingActionButton fab;
    RecyclerView mInstitutionRecyclerView;
    RecyclerView.Adapter mInstitutionAdapter;
    List<Institution> instituciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mInstitutionRecyclerView = (RecyclerView) findViewById(R.id.InstitutionsRecyclerView);
        mInstitutionRecyclerView.setHasFixedSize(true);
        mInstitutionLayoutManager = new LinearLayoutManager(this);
        mInstitutionRecyclerView.setLayoutManager(mInstitutionLayoutManager);

        instituciones = Institution.listAll(Institution.class);

        mInstitutionAdapter = new InstitutionAdapter( instituciones );
        mInstitutionRecyclerView.setAdapter(mInstitutionAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddInstitutionActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            // Si es así mostramos mensaje de cancelado por pantalla.
            Toast.makeText(this, "Registro cancelado", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(this, "Institución creada", Toast.LENGTH_SHORT)
                    .show();
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
        if (id == R.id.action_logout) {
            logoutUser();
        }

        return super.onOptionsItemSelected(item);
    }
}
