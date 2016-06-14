package pe.edu.utp.rendimientoestudiantil.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import pe.edu.utp.rendimientoestudiantil.R;
import pe.edu.utp.rendimientoestudiantil.items.ChartItem;
import pe.edu.utp.rendimientoestudiantil.items.LineChartItem;
import pe.edu.utp.rendimientoestudiantil.models.Course;
import pe.edu.utp.rendimientoestudiantil.models.Evaluation;
import pe.edu.utp.rendimientoestudiantil.models.Student;

public class ChartActivity extends AppCompatActivity {

    private Course course;
    private long idCourse;
    private List<Student> students;
    private List<Evaluation> evaluations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView lv = (ListView) findViewById(R.id.listView1);

        Bundle extras = getIntent().getExtras();
        idCourse = extras.getLong("idCourse");
        idCourse = extras.getLong("idCourse");
        course = Course.findById(Course.class, idCourse);

        students = course.findStudentsByCourse( );
        evaluations = course.findEvaluationsByCourse( );

        ArrayList<ChartItem> list = new ArrayList<ChartItem>();

        list.add(new LineChartItem(generateDataLine(), getApplicationContext()));

        ChartDataAdapter cda = new ChartDataAdapter(getApplicationContext(), list);
        lv.setAdapter(cda);
    }

    /** adapter that supports 3 different item types */
    private class ChartDataAdapter extends ArrayAdapter<ChartItem> {

        public ChartDataAdapter(Context context, List<ChartItem> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getItem(position).getView(position, convertView, getContext());
        }

        @Override
        public int getItemViewType(int position) {
            // return the views type
            return getItem(position).getItemType();
        }

        @Override
        public int getViewTypeCount() {
            return 3; // we have 3 different item-types
        }
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private LineData generateDataLine() {

        ArrayList<Entry> e1 = new ArrayList<Entry>();

        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        int count = 0;
        for (Student student : students ) {

            ArrayList<Entry> e2 = new ArrayList<Entry>();
            int i = 0;
            for( Evaluation evaluation: evaluations ){
                e2.add(new Entry((int) student.getNote( evaluation ) , i));
                i++;
            }

            LineDataSet ds = new LineDataSet(e2, student.toString() );

            ds.setLineWidth(2.5f);
            ds.setCircleRadius(4.5f);
            ds.setHighLightColor(Color.rgb(244, 117, 117));
            ds.setColor(ColorTemplate.VORDIPLOM_COLORS[count]);
            ds.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[count]);
            ds.setDrawValues(false);
            sets.add( ds );
            count++;
        }

        LineData cd = new LineData(getEvaluations(), sets);
        return cd;
    }
    private ArrayList<String> getEvaluations() {
        ArrayList<String> m = new ArrayList<String>();
        for ( Evaluation evaluation : evaluations  ) {
            m.add( evaluation.toString() );
        }
        return m;
    }

}
