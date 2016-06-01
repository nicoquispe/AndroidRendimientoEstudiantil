package pe.edu.utp.rendimientoestudiantil.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.db.DatabaseAccess;
import pe.edu.utp.rendimientoestudiantil.models.Institution;

public class AddInstitutionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_institution);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

        Button addInstitution = (Button) findViewById(R.id.addInstitution);
        addInstitution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseAccess.open();
                Institution newInstitution = new Institution();
                newInstitution.setName( nameEditText.getText().toString() );
                databaseAccess.insertInstitution( newInstitution, teacherId );
                databaseAccess.close();
                setResult(RESULT_OK);
                finish();
            }
        });

    }

}
