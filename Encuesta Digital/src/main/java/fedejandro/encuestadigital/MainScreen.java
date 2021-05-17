package fedejandro.encuestadigital;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.*;
import java.util.Calendar;


public class MainScreen extends AppCompatActivity implements View.OnClickListener {

    EditText fechaText;
    EditText encuestadorText;
    EditText consecutivoText;
    Button nextButton, fechaButton, downloadButton;
    private int day, month, year;

    private String filename = "datos.txt";
    private String filepath = "EncuestaDatos";
    File myExternalFile;
    String myData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            nextButton.setEnabled(false);
        }
        else {
            myExternalFile = new File(getExternalFilesDir(filepath), filename);
        }

        StringBuffer datax = new StringBuffer("");
        try {
            FileInputStream fis = new FileInputStream(myExternalFile);
            InputStreamReader isr = new InputStreamReader ( fis ) ;
            BufferedReader buffreader = new BufferedReader ( isr ) ;

            String readString = buffreader.readLine( ) ;
            while ( readString != null ) {
                datax.insert(0,readString);
                readString = buffreader.readLine ( ) ;
            }

            isr.close( ) ;

            consecutivoText = (EditText) findViewById(R.id.consecutivoText);
            String str = datax.toString();
            String consecutivo = String.valueOf(Integer.parseInt(str.split("\t")[1])+1);
            consecutivoText.setText(consecutivo);

        }catch (IOException e){
            try {
                FileOutputStream fos = new FileOutputStream(myExternalFile, true);
                    fos.write(("Fecha\tN° de visita \tNombre del encuestador\t" +
                            "Departamento\tMunicipio\tResguardo indígena\tBarrio/Corregimiento/Vereda\tCoordenadas GPS\tNombre\tNúmero telefónico\tTipo de D.I.\tNúmero de D.I.\tTamaño de grupo familia\tGrupos étnicos\tEdad\tGénero\tHace cuantos años reside en la zona\tHace cuantos años se dedica a esta actividad\tUsted está organizado como persona natural o jurídica\tHa recibido asistencia técnica y/o asesoria\tQué entidades realizaron la asistencia y/o asesoria\tEn cuál de los siguientes temas fue o es  asesorado/asistido\tLe sirvió la asesoría\tLa asistencia técnica recibida fue\tUsted ha solicitado alguna vez crédito o financiación para su producción\tHa asistido a algún diplomado, taller, curso, seminario, capacitación, otros\tLa formación complementaria fue ofrecida por\tEn cuál de las siguientes áreas recibió formación complementaria\tLa formación complementaria fue de utilidad\tLa formación complementaria  recibida fue\t" +
                            "Tipo de cultivo\tEsta actividad económica es principal o complementaria\t¿Cuántas unidades de producción están bajo su  cargo?\t¿La unidad de producción es?\t¿Cuál es el área destinada a la producción (ha)?\t¿La fuente de agua para la produccion proviene de?\t¿Qué método utiliza para transportar el agua desde la fuente de captación hacia la unidad de producción?\t¿Cuántos métros cúbicos mensuales emplea en la upa?\t¿Paga por el suministro de agua empleado en la unidad de produccion?\t¿Conoce  el tipo de suelos  de la unidad de produccion?\t¿Qué tipo de suelos hay en la unidad de produccion?\t¿Los productos de la unidad de produccion se destinan para?\t¿Qué porcentaje se destina para el autoconsumo?\t¿Qué porcentaje se destina para la comercialización?\tCuanto es el promedio de produccion (kilogramos/hectarea/año)\t¿Cuántos meses dura el ciclo de producción o cosecha?\tEn que presentacion vende la produccion\t¿Cuánto es el precio de venta de esa presentacion?\t¿De ese valor de venta que porcentaje corresponde a los costos de produccion?\t¿Ha recibido alguna información sobre tecnologías para su sistema de producción por parte de la Universidad Nacional?\tPor qué medio la universidad le suministró información sobre las tecnologias para su sistema de produccion\tAdoptó algun tipo de tecnologia de la cual recibio informacion\t¿Por qué no adoptó alguna de las tecnologías?\t¿Qué tipo de tecnologia adoptó?\t¿Qué porcentaje de la tecnologia ha adoptado en la actualidad? (porcentajes individuales)\t¿Qué porcentaje de la tecnologia ha adoptado en la actualidad? (promedio final)\t¿Hace cuántos meses inició con la adopción de esa tecnologia?\t¿Ha presentado alguna mejora en el sistema de después de la adopción de esa tecnología? \t¿En qué indicadores ve reflejada la mejora en su sistema de producción luego de la adopción de la tecnología?\t¿Ha sufrido pérdidas por alguno de los siguientes desastres naturales?\t¿Cuál fue el porcentaje (%) de perdida?\t¿Ha sufrido pérdida por enfermedades o patologías asociadas a?\t¿Cuál fue el porcentaje (%) de perdida?\t¿La principal fuente de energía utilizada en la unidad de produccion es?\t¿Es miembro activo  de  alguna asociación?\t¿Qué tipo de asociación?\t¿Cuántas personas  participan en la asociación?\t¿Cuál es el nombre de la asociación?\tNombre del representante legal\tNúmero de teléfono fijo o móvil del representante legal\tDepartamento\tMunicipio\t¿Dónde vende?\t¿Quién le compra?\t¿Los productos de la unidad de produccion  cuentan con algún certificado de calidad?\t¿Cuál es el certificado de calidad con el que cuenta la unidad de produccion?\t¿Quién expide el certificado?\t").getBytes());
            } catch (IOException err) {
                err.printStackTrace();
            }
            consecutivoText = (EditText) findViewById(R.id.consecutivoText);
            consecutivoText.setText("1");
        }


        fechaText = (EditText) findViewById(R.id.fechaText);
        encuestadorText = (EditText) findViewById(R.id.encuestadorText);
        consecutivoText = (EditText) findViewById(R.id.consecutivoText);

        encuestadorText.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        fechaButton = (Button) findViewById(R.id.fechaButton);
        fechaButton.setOnClickListener(this);

        nextButton =  (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = new FileOutputStream(myExternalFile,true);
                    fos.write(("\n" +
                            "").getBytes());
                    fos.write(fechaText.getText().toString().getBytes());
                    fos.write("\t".getBytes());
                    fos.write(consecutivoText.getText().toString().getBytes());
                    fos.write("\t".getBytes());
                    fos.write(encuestadorText.getText().toString().getBytes());
                    fos.close();

                    Intent preg1 = new Intent(getApplicationContext(), Preguntas1.class);
                    startActivity(preg1);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        downloadButton =  (Button) findViewById(R.id.downloadButton);
        downloadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                compartirArchivo();
            }
        });
    }

    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v==fechaButton){
            final Calendar myCalendar = Calendar.getInstance();
            day=myCalendar.get(Calendar.DAY_OF_MONTH);
            month=myCalendar.get(Calendar.MONTH);
            year=myCalendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    fechaText.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            }
            , year, month, day);
            datePickerDialog.show();
        }

    }

    private void compartirArchivo(){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/*");
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + myExternalFile.getAbsolutePath()));
        startActivity(Intent.createChooser(sharingIntent, "share file with"));
    }

}
