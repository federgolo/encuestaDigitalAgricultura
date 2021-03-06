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
                    fos.write(("Fecha\tN?? de visita \tNombre del encuestador\t" +
                            "Departamento\tMunicipio\tResguardo ind??gena\tBarrio/Corregimiento/Vereda\tCoordenadas GPS\tNombre\tN??mero telef??nico\tTipo de D.I.\tN??mero de D.I.\tTama??o de grupo familia\tGrupos ??tnicos\tEdad\tG??nero\tHace cuantos a??os reside en la zona\tHace cuantos a??os se dedica a esta actividad\tUsted est?? organizado como persona natural o jur??dica\tHa recibido asistencia t??cnica y/o asesoria\tQu?? entidades realizaron la asistencia y/o asesoria\tEn cu??l de los siguientes temas fue o es  asesorado/asistido\tLe sirvi?? la asesor??a\tLa asistencia t??cnica recibida fue\tUsted ha solicitado alguna vez cr??dito o financiaci??n para su producci??n\tHa asistido a alg??n diplomado, taller, curso, seminario, capacitaci??n, otros\tLa formaci??n complementaria fue ofrecida por\tEn cu??l de las siguientes ??reas recibi?? formaci??n complementaria\tLa formaci??n complementaria fue de utilidad\tLa formaci??n complementaria  recibida fue\t" +
                            "Tipo de cultivo\tEsta actividad econ??mica es principal o complementaria\t??Cu??ntas unidades de producci??n est??n bajo su  cargo?\t??La unidad de producci??n es?\t??Cu??l es el ??rea destinada a la producci??n (ha)?\t??La fuente de agua para la produccion proviene de?\t??Qu?? m??todo utiliza para transportar el agua desde la fuente de captaci??n hacia la unidad de producci??n?\t??Cu??ntos m??tros c??bicos mensuales emplea en la upa?\t??Paga por el suministro de agua empleado en la unidad de produccion?\t??Conoce  el tipo de suelos  de la unidad de produccion?\t??Qu?? tipo de suelos hay en la unidad de produccion?\t??Los productos de la unidad de produccion se destinan para?\t??Qu?? porcentaje se destina para el autoconsumo?\t??Qu?? porcentaje se destina para la comercializaci??n?\tCuanto es el promedio de produccion (kilogramos/hectarea/a??o)\t??Cu??ntos meses dura el ciclo de producci??n o cosecha?\tEn que presentacion vende la produccion\t??Cu??nto es el precio de venta de esa presentacion?\t??De ese valor de venta que porcentaje corresponde a los costos de produccion?\t??Ha recibido alguna informaci??n sobre tecnolog??as para su sistema de producci??n por parte de la Universidad Nacional?\tPor qu?? medio la universidad le suministr?? informaci??n sobre las tecnologias para su sistema de produccion\tAdopt?? algun tipo de tecnologia de la cual recibio informacion\t??Por qu?? no adopt?? alguna de las tecnolog??as?\t??Qu?? tipo de tecnologia adopt???\t??Qu?? porcentaje de la tecnologia ha adoptado en la actualidad? (porcentajes individuales)\t??Qu?? porcentaje de la tecnologia ha adoptado en la actualidad? (promedio final)\t??Hace cu??ntos meses inici?? con la adopci??n de esa tecnologia?\t??Ha presentado alguna mejora en el sistema de despu??s de la adopci??n de esa tecnolog??a? \t??En qu?? indicadores ve reflejada la mejora en su sistema de producci??n luego de la adopci??n de la tecnolog??a?\t??Ha sufrido p??rdidas por alguno de los siguientes desastres naturales?\t??Cu??l fue el porcentaje (%) de perdida?\t??Ha sufrido p??rdida por enfermedades o patolog??as asociadas a?\t??Cu??l fue el porcentaje (%) de perdida?\t??La principal fuente de energ??a utilizada en la unidad de produccion es?\t??Es miembro activo  de  alguna asociaci??n?\t??Qu?? tipo de asociaci??n?\t??Cu??ntas personas  participan en la asociaci??n?\t??Cu??l es el nombre de la asociaci??n?\tNombre del representante legal\tN??mero de tel??fono fijo o m??vil del representante legal\tDepartamento\tMunicipio\t??D??nde vende?\t??Qui??n le compra?\t??Los productos de la unidad de produccion  cuentan con alg??n certificado de calidad?\t??Cu??l es el certificado de calidad con el que cuenta la unidad de produccion?\t??Qui??n expide el certificado?\t").getBytes());
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
