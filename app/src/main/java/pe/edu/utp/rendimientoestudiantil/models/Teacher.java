package pe.edu.utp.rendimientoestudiantil.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.List;

public class Teacher extends SugarRecord {

    String first_name;
    String last_name;
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

    public Teacher(String first_name, String last_name, String email, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
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

        this.students = new ArrayList<>();
        this.students.add( new Student( 1412974, "ABARCA SANTOS", "LUIS JESUS", this.courses.get(0) ) );
        this.students.add( new Student( 1311998, "AGUIRRE GUTIERREZ", "ALEXIS", this.courses.get(0)) );
        this.students.add( new Student( 1220921, "ARCE GUTIERREZ", "YORKA YEIVIN", this.courses.get(0)) );
        this.students.add( new Student( 1421505, "CABRERA SANCHEZ", "JUNIOR JESUS", this.courses.get(0)) );
        this.students.add( new Student( 1331417, "CAMPUSMANA CALDERON", "JULIO SANTIAGO", this.courses.get(0)) );
        this.students.add( new Student( 1511398, "CHOQUE LEAÑO", "CESAR", this.courses.get(1)) );
        this.students.add( new Student( 1420112, "CONDORI CARRILLO", "ELEN CATHERINE", this.courses.get(1)) );
        this.students.add( new Student( 1412981, "CONZA ROJAS", "YOEL ANTONIO", this.courses.get(1)) );
        this.students.add( new Student( 1410313, "CURAY CORNEJO", "XIMENA DE LOS MILAGROS", this.courses.get(1)) );
        this.students.add( new Student( 1320317, "CUTIPA LOPEZ", "JULIO CESAR", this.courses.get(1)) );
        this.students.add( new Student( 1523042, "CUZCANO ORTIZ", "GERALD ROGELIO", this.courses.get(2)) );
        this.students.add( new Student( 1310898, "ESPINO ALVARADO", "PABLO CESAR", this.courses.get(2)) );
        this.students.add( new Student( 1321128, "FUENTES GUILLEN", "JULEYSI JAZMIN", this.courses.get(2)) );
        this.students.add( new Student( 1410454, "GARCIA PUMA", "EDWIN JESUS", this.courses.get(2)) );
        this.students.add( new Student( 1211397, "GERONIMO LEON", "YELTHSIN GHENRY", this.courses.get(2)) );
        this.students.add( new Student( 1412341, "GOMEZ QUESQUEN", "YURI KENNY", this.courses.get(3)) );
        this.students.add( new Student( 1410301, "HUAYANAY RURUSH", "RENZON",  this.courses.get(3)) );
        this.students.add( new Student( 1910370, "INGA QUISPE", "EDWAR DANNY", this.courses.get(3)) );
        this.students.add( new Student( 1411094, "IPANAQUE ALAYA", "EVERT LUIS", this.courses.get(3)) );
        this.students.add( new Student( 1220802, "ALAMA VISITACION", "GLORIA MARIA DE LOS MILAGROS", this.courses.get(3) ) ) ;
        this.students.add( new Student( 1330554, "ALVAREZ HANCCO", "KARINA ROCIO", this.courses.get(4) ) ) ;
        this.students.add( new Student( 1310113, "APAESTEGUI ORTEGA", "ROGER", this.courses.get(4) ) ) ;
        this.students.add( new Student( 1011545, "BAZAN CHACA", "KEVIN EDGAR", this.courses.get(4) ) ) ;
        this.students.add( new Student( 1312429, "CALISAYA APAZA", "IVAN EMILIO", this.courses.get(4) ) ) ;
        this.students.add( new Student( 1420051, "CALLAÑAUPA BARBOZA", "ALBERT ROGGER", this.courses.get(4) ) ) ;
        this.students.add( new Student( 1420652, "CASTILLO QUISPE", "SHAROON DENISSE", this.courses.get(5) ) ) ;
        this.students.add( new Student( 1111764, "FUENTES GARNIQUE", "EDGARD MARTIN", this.courses.get(5) ) ) ;
        this.students.add( new Student( 1112015, "GARCIA OCHOA", "ELVIS LENON", this.courses.get(5) ) ) ;
        this.students.add( new Student( 1220430, "GASPAR SANCHEZ", "EDWAR ALEXANDER", this.courses.get(5) ) ) ;
        this.students.add( new Student( 1411881, "GUARDIA DURAND", "ALEX JESUS", this.courses.get(5) ) ) ;
        this.students.add( new Student( 1411069, "GUTIERREZ TELLES", "WALBERTH FELIPE", this.courses.get(6) ) ) ;
        this.students.add( new Student( 1330532, "HANCO FLORES", "BEATRIZ VANESSA", this.courses.get(6) ) ) ;
        this.students.add( new Student( 1310952, "HUACCHO AVILA", "MIGUEL DANTE", this.courses.get(6) ) ) ;
        this.students.add( new Student( 1910497, "LAUREANO LAZARO", "KEVIN PABLO", this.courses.get(6) ) ) ;
        this.students.add( new Student( 1012280, "LEONARDO PAREDES", "JULIO CESAR", this.courses.get(6) ) ) ;
        this.students.add( new Student( 1212600, "MEZA AVENDAÑO", "CAROLINA", this.courses.get(7) ) ) ;
        this.students.add( new Student( 1310258, "MIGUEL DIAZ", "HANS ALEJANDRO", this.courses.get(7) ) ) ;
        this.students.add( new Student( 1122039, "MILLA TARAZONA", "MARITZA YANINA", this.courses.get(7) ) ) ;
        this.students.add( new Student( 1413294, "MORALES SEGOVIA", "LUCHO GREGORIO", this.courses.get(7) ) ) ;

    }
    */

    public Teacher() {

    }
}
