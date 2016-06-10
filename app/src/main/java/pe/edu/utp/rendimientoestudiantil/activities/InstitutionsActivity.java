package pe.edu.utp.rendimientoestudiantil.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.adapters.InstitutionAdapter;
import pe.edu.utp.rendimientoestudiantil.models.Institution;

public class InstitutionsActivity extends BaseActivity {


    RecyclerView.LayoutManager mInstitutionLayoutManager;
    FloatingActionButton fab;
    RecyclerView mInstitutionRecyclerView;
    RecyclerView.Adapter mInstitutionAdapter;
    List<Institution> instituciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mInstitutionRecyclerView = (RecyclerView) findViewById(R.id.InstitutionsRecyclerView);
        mInstitutionRecyclerView.setHasFixedSize(true);
        mInstitutionLayoutManager = new LinearLayoutManager(this);
        mInstitutionRecyclerView.setLayoutManager(mInstitutionLayoutManager);

        instituciones = Institution.listAll(Institution.class);

        mInstitutionAdapter = new InstitutionAdapter( instituciones );
        mInstitutionRecyclerView.setAdapter(mInstitutionAdapter);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddInstitutionActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        try {
            backupDatabase();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

    private static void backupDatabase () throws IOException{
        String inFileName = "/data/data/pe.edu.utp.rendimientoestudiantil/databases/rendimiento_academico_3.db";
        File dbFile = new File(inFileName);
        FileInputStream fis = new FileInputStream(dbFile);

        String outFileName = Environment.getExternalStorageDirectory()+"/rendimiento_academico_3.db";
        //Open the empty db as the output stream
        OutputStream output = new FileOutputStream(outFileName);
        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = fis.read(buffer))>0){
            output.write(buffer, 0, length);
        }
        //Close the streams
        output.flush();
        output.close();
        fis.close();
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
}
