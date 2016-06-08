package pe.edu.utp.rendimientoestudiantil.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
