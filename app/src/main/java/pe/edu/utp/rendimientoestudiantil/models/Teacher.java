package pe.edu.utp.rendimientoestudiantil.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.List;

public class Teacher extends SugarRecord {

    String firstName;
    String lastName;
    String password;
    @Unique
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Teacher(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    public List<TeacherInstitution> getInstitutions() {
        return TeacherInstitution.find(TeacherInstitution.class, "teacher = ?", this.getId().toString());
    }
    /*
    public void initialize(){
        this.courses = new ArrayList<>();
        this.instituciones= new ArrayList<>();

        this.instituciones.add( new Institution( 1, "UTP - UNIVERSIDAD TECNOLOGICA DEL PERU" ) );
        this.instituciones.add( new Institution( 2, "PUCP - PONTIFICIA UNIVERSIDAD CATOLICA DEL PERU" ) );
        this.instituciones.add( new Institution( 3, "UPC - UNIVERSIDAD PERUANA DE CIENCIAS APLICADAS" ) );

        this.courses.add( new Course( 1, "ESTADISTICA INFERENCIAL", this, this.instituciones.get(0) ) );
        this.courses.add( new Course( 2, "DESARROLLO WEB INTEGRADO", this, this.instituciones.get(0) ) );
        this.courses.add( new Course( 3, "DESARROLLO DE APLICACIONES EMPRESARIALES CON ANDROID", this, this.instituciones.get(0) ) );
        this.courses.add( new Course( 4, "CURSO INTEGRADOR I: INGENIERIA DE SISTEMAS-SOFTWARE", this, this.instituciones.get(1) ) );
        this.courses.add( new Course( 5, "PROGRAMACION LOGICA Y FUNCIONAL", this, this.instituciones.get(1) ) );
        this.courses.add( new Course( 6, "PROCESOS PARA INGENIERIA", this, this.instituciones.get(1) ) );
        this.courses.add( new Course( 7, "FISICA GENERAL", this, this.instituciones.get(1) ) );
        this.courses.add( new Course( 8, "CALCULO DIFERENCIAL", this, this.instituciones.get(2) ) );
        this.courses.add( new Course( 9, "INDIVIDUO Y MEDIO AMBIENTE", this, this.instituciones.get(2) ) );
        this.courses.add( new Course( 10, "CALCULO INTEGRAL", this, this.instituciones.get(2) ) );

    }
    */

    public Teacher() {

    }
    @Override
    public String toString() {
        return this.firstName + ", " + this.lastName;
    }
}
