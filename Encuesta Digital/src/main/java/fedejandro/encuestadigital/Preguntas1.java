package fedejandro.encuestadigital;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.*;

import java.io.*;

public class Preguntas1 extends AppCompatActivity {

    Button nextButtonAT;
    private String filename = "datos.txt";
    private String filepath = "EncuestaDatos";
    File myExternalFile;

    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 0; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 10; // in Milliseconds
    protected LocationManager locationManager;
    protected LocationManager locationManagerGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas1);

        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },
                    11);
        }

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                MINIMUM_TIME_BETWEEN_UPDATES,
                MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                new MyLocationListener()
        );

        locationManagerGPS = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManagerGPS.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MINIMUM_TIME_BETWEEN_UPDATES,
                MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                new MyLocationListener()
        );

        String[] departamentos = new String[] {
                "Amazonas","Antioquia","Arauca","Atl??ntico","Bol??var","Boyac??","Caldas","Caquet??","Casanare","Cauca","Cesar",
                "Choc??","C??rdoba","Cundinamarca","Guain??a","Guaviare","Huila","La Guajira","Magdalena","Meta","Nari??o",
                "Norte de Santander","Putumayo","Quind??o","Risaralda","San Andr??s y Providencia","Santander","Sucre","Tolima",
                "Valle del Cauca","Vaup??s","Vichada"
        };
        final String[] municipiosAmazonas = new String[]{
                "Leticia", "Puerto Nari??o"
        };
        final String[] municipiosAntioquia = new String[] {
                "Medell??n", "Abejorral", "Abriaqui", "Alejandr??a", "Amaga", "Amalfi", "Andes", "Angelopolis", "Angostura",
                "Anor??", "Anza", "Apartado", "Arboletes", "Argelia", "Armenia", "Barbosa", "Bello", "Belmira", "Betania",
                "Betulia", "Bol??var", "Brice??o", "Buritica", "C??ceres", "Caicedo", "Caldas", "Campamento", "Ca??asgordas",
                "Caracol??", "Caramanta", "Carepa", "Carmen De Viboral", "Carolina", "Caucasia", "Chigorod??", "Cisneros",
                "Cocorn??", "Concepci??n", "Concordia", "Copacabana", "Dabeiba", "Don Mat??as", "Ebejico", "El Bagre",
                "Entrerrios", "Envigado", "Fredonia", "Frontino", "Giraldo", "Girardota", "G??mez Plata", "Granada",
                "Guadalupe", "Guarne", "Guatap??", "Heliconia", "Hispania", "Itagui", "Ituango", "Jard??n", "Jeric??", "La Ceja",
                "La Estrella", "La Pintada", "La Uni??n", "Liborina", "Maceo", "Marinilla", "Montebello", "Murind??", "Mutat??",
                "Nari??o", "Nech??", "Necocl??", "Olaya", "Pe??ol", "Peque", "Pueblorrico", "Puerto Berr??o", "Puerto Nare",
                "Puerto Triunfo", "Remedios", "Retiro", "Rionegro", "Sabanalarga", "Sabaneta", "Salgar", "San Andr??s",
                "San Carlos", "San Francisco", "San Pedro De Uraba", "San Jos?? De La Monta??a", "San Juan De Uraba",
                "Santa Rosa De Osos", "San Pedro", "San Jer??nimo", "San Rafael", "San Roque", "San Vicente", "Santa B??rbara",
                "San Luis", "Santaf?? de Antioquia", "Santo Domingo", "Santuario", "Segovia", "Sonson", "Sopetr??n", "T??mesis",
                "Taraza", "Tarso", "Titirib??", "Toledo", "Turbo", "Uramita", "Urrao", "Valdivia", "Valpara??so", "Vegachi",
                "Venecia", "Vig??a Del Fuerte", "Yali", "Yarumal", "Yolomb??", "Yond??", "Zaragoza"
        };
        final String[] municipiosArauca = new String[] {
                "Arauca", "Arauquita", "Cravo Norte", "Fortul", "Puerto Rond??n", "Saravena", "Tame"
        };
        final String[] municipiosAtlantico = new String[]{
                "Baranoa", "Barranquilla", "Campo De La Cruz", "Candelaria", "Galapa", "Juan De Acosta", "Luruaco", "Malambo",
                "Manat??", "Palmar De Varela", "Pioj??", "Polonuevo", "Ponedera", "Puerto Colombia", "Repel??n", "Sabanagrande",
                "Sabanalarga", "Santa Luc??a", "Santo Tom??s", "Soledad", "Suan", "Tubar??", "Usiacur??"
        };
        final String[] municipiosBolivar = new String[]{
                "Ach??", "Altos Del Rosario", "Arenal", "Arjona", "Arroyohondo", "Barranco De Loba", "Calamar", "Cantagallo",
                "Cartagena", "Cicuco", "Clemencia", "C??rdoba", "El Carmen De Bol??var", "El Guamo", "El Pe????n", "Hatillo De Loba",
                "Magangu??", "Mahates", "Margarita", "Mar??a La Baja", "Momp??s", "Montecristo", "Morales", "Pinillos", "Regidor",
                "R??o Viejo", "San Crist??bal", "San Estanislao", "San Fernando", "San Jacinto", "San Jacinto Del Cauca",
                "San Juan Nepomuceno", "San Mart??n De Loba", "San Pablo", "Santa Catalina", "Santa Rosa", "Santa Rosa Del Sur",
                "Simit??", "Soplaviento", "Talaigua Nuevo", "Tiquisio", "Turbaco", "Turban??", "Villanueva", "Zambrano"
        };
        final String[] municipiosBoyaca = new String[]{
                "Almeida", "Aquitania", "Arcabuco", "Bel??n", "Berbeo", "Bet??itiva", "Boavita", "Boyac??", "Brice??o", "Buenavista",
                "Busbanz??", "Caldas", "Campohermoso", "Cerinza", "Chinavita", "Chiquinquir??", "Ch??quiza", "Chiscas", "Chita",
                "Chitaraque", "Chivat??", "Chivor", "Ci??nega", "C??mbita", "Coper", "Corrales", "Covarach??a", "Cubar??", "Cucaita",
                "Cu??tiva", "Duitama", "El Cocuy", "El Espino", "Firavitoba", "Floresta", "Gachantiv??", "Gameza", "Garagoa",
                "Guacamayas", "Guateque", "Guayat??", "G??ic??n", "Iza", "Jenesano", "Jeric??", "La Capilla", "La Uvita",
                "La Victoria", "Labranzagrande", "Macanal", "Marip??", "Miraflores", "Mongua", "Mongu??", "Moniquir??", "Motavita",
                "Muzo", "Nobsa", "Nuevo Col??n", "Oicat??", "Otanche", "Pachavita", "P??ez", "Paipa", "Pajarito", "Panqueba",
                "Pauna", "Paya", "Paz De R??o", "Pesca", "Pisba", "Puerto Boyac??", "Qu??pama", "Ramiriqu??", "R??quira", "Rond??n",
                "Saboy??", "S??chica", "Samac??", "San Eduardo", "San Jos?? De Pare", "San Luis De Gaceno", "San Mateo",
                "San Miguel De Sema", "San Pablo De Borbur", "Santa Mar??a", "Santa Rosa De Viterbo", "Santa Sof??a", "Santana",
                "Sativanorte", "Sativasur", "Siachoque", "Soat??", "Socha", "Socot??", "Sogamoso", "Somondoco", "Sora", "Sorac??",
                "Sotaquir??", "Susac??n", "Sutamarch??n", "Sutatenza", "Tasco", "Tenza", "Tiban??", "Tibasosa", "Tinjac??",
                "Tipacoque", "Toca", "Tog????", "T??paga", "Tota", "Tunja", "Tunungu??", "Turmequ??", "Tuta", "Tutaz??", "Umbita",
                "Ventaquemada", "Villa De Leyva", "Viracach??", "Zetaquira"
        };
        final String[] municipiosCaldas = new String[]{
                "Aguadas", "Anserma", "Aranzazu", "Belalc??zar", "Chinchin??", "Filadelfia", "La Dorada", "La Merced", "Manizales",
                "Manzanares", "Marmato", "Marquetalia", "Marulanda", "Neira", "Norcasia", "P??cora", "Palestina", "Pensilvania",
                "Riosucio", "Risaralda", "Salamina", "Saman??", "San Jos??", "Sup??a", "Victoria", "Villamar??a", "Viterbo"
        };
        final String[] municipiosCaqueta = new String[]{
                "Albania", "Bel??n De Los Andaquies", "Cartagena Del Chair??", "Curillo", "El Doncello", "El Paujil", "Florencia",
                "La Monta??ita", "Mil??n", "Morelia", "Puerto Rico", "San Jos?? Del Fragua", "San Vicente Del Cagu??n", "Solano",
                "Solita", "Valpara??so"
        };
        final String[] municipiosCasanare = new String[]{
                "Aguazul", "Chameza", "Hato Corozal", "La Salina", "Man??", "Monterrey", "Nunch??a", "Orocu??", "Paz De Ariporo",
                "Pore", "Recetor", "Sabanalarga", "S??cama", "San Luis De Palenque", "T??mara", "Tauramena", "Trinidad",
                "Villanueva", "Yopal"
        };
        final String[] municipiosCauca = new String[]{
                "Almaguer", "Argelia", "Balboa", "Bol??var", "Buenos Aires", "Cajib??o", "Caldon??", "Caloto", "Corinto",
                "El Tambo", "Florencia", "Guapi", "Inz??", "Jambal??", "La Sierra", "La Vega", "L??pez de Micay", "Mercaderes",
                "Miranda", "Morales", "Padilla", "Paez", "Pat??a", "Piamonte", "Piendam??", "Popay??n", "Puerto Tejada", "Purac??",
                "Rosas", "San Sebasti??n", "Santa Rosa", "Santander De Quilichao", "Silvia", "Sotar??", "Su??rez", "Sucre",
                "Timb??o", "Timbiqu??", "Torib??o", "Totor??", "Villa Rica"
        };
        final String[] municipiosCesar = new String[]{
                "Aguachica", "Agust??n Codazzi", "Astrea", "Becerril", "Bosconia", "Chimichagua", "Chiriguan??", "Curuman??",
                "El Copey", "El Paso", "Gamarra", "Gonz??lez", "La Gloria", "La Jagua De Ibirico", "La Paz", "Manaure",
                "Pailitas", "Pelaya", "Pueblo Bello", "R??o De Oro", "San Alberto", "San Diego", "San Mart??n", "Tamalameque",
                "Valledupar"
        };
        final String[] municipiosChoco = new String[]{
                "Acand??", "Alto Baud??", "Atrato", "Bagad??", "Bah??a Solano", "Bajo Baud??", "Bojaya", "Carmen Del Dari??n",
                "C??rtegui", "Condoto", "El Cant??n Del San Pablo", "El Carmen De Atrato", "El Litoral Del San Juan", "Istmina",
                "Jurad??", "Llor??", "Medio Atrato", "Medio Baud??", "Medio San Juan", "N??vita", "Nuqu??", "Quibd??", "R??o Iro",
                "R??o Quito", "Riosucio", "San Jos?? Del Palmar", "Sip??", "Tad??", "Ungu??a", "Uni??n Panamericana"
        };
        final String[] municipiosCordoba = new String[]{
                "Ayapel", "Buenavista", "Canalete", "Ceret??", "Chim??", "Chin??", "Ci??naga De Oro", "Cotorra", "La Apartada",
                "Los C??rdobas", "Momil", "Mo??itos", "Montel??bano", "Monter??a", "Planeta Rica", "Pueblo Nuevo",
                "Puerto Escondido", "Puerto Libertador", "Pur??sima", "Sahag??n", "San Andr??s de Sotavento", "San Antero",
                "San Bernardo Del Viento", "San Carlos", "San Pelayo", "Santa Cruz de Lorica", "Tierralta", "Tuch??n", "Valencia"
        };
        final String[] municipiosCundinamarca = new String[]{
                "Agua De Dios", "Alb??n", "Anapoima", "Anolaima", "Apulo", "Arbel??ez", "Beltr??n", "Bituima", "Bogot??", "Bojac??",
                "Cabrera", "Cachipay", "Cajic??", "Caparrap??", "C??queza", "Carmen De Carupa", "Chaguan??", "Ch??a", "Chipaque",
                "Choach??", "Chocont??", "Cogua", "Cota", "Cucunub??", "El Colegio", "El Pe????n", "El Rosal", "Facatativ??", "Fomeque",
                "Fosca", "Funza", "F??quene", "Fusagasug??", "Gachal??", "Gachancip??", "Gachet??", "Gama", "Girardot", "Granada",
                "Guachet??", "Guaduas", "Guasca", "Guataqu??", "Guatavita", "Guayabal De Siquima", "Guayabetal", "Guti??rrez",
                "Jerusal??n", "Jun??n", "La Calera", "La Mesa", "La Palma", "La Pe??a", "La Vega", "Lenguazaque", "Macheta",
                "Madrid", "Manta", "Medina", "Mosquera", "Nari??o", "Nemoc??n", "Nilo", "Nimaima", "Nocaima", "Pacho", "Paime",
                "Pandi", "Paratebueno", "Pasca", "Puerto Salgar", "Pul??", "Quebradanegra", "Quetame", "Quipile", "Ricaurte",
                "San Antonio Del Tequendama", "San Bernardo", "San Cayetano", "San Francisco", "San Juan De R??o Seco", "Sasaima",
                "Sesquil??", "Sibat??", "Silvania", "Simijaca", "Soacha", "Sop??", "Subachoque", "Suesca", "Supat??", "Susa",
                "Sutatausa", "Tabio", "Tausa", "Tena", "Tenjo", "Tibacuy", "Tibirita", "Tocaima", "Tocancip??", "Topaip??",
                "Ubal??", "Ubaque", "Ubat??", "Une", "??tica", "Venecia", "Vergara", "Vian??", "Villag??mez", "Villapinz??n", "Villeta",
                "Viot??", "Yacop??", "Zipac??n", "Zipaquir??"
        };
        final String[] municipiosGuainia = new String[]{
                "In??rida"
        };
        final String[] municipiosGuaviare = new String[]{
                "Calamar", "El Retorno", "Miraflores", "San Jos?? Del Guaviare"
        };
        final String[] municipiosHuila = new String[]{
                "Acevedo", "Agrado", "Aipe", "Algeciras", "Altamira", "Baraya", "Campoalegre", "Colombia", "El??as", "Garz??n",
                "Gigante", "Guadalupe", "Hobo", "Iquira", "Isnos", "La Argentina", "La Plata", "N??taga", "Neiva", "Oporapa",
                "Paicol", "Palermo", "Palestina", "Pital", "Pitalito", "Rivera", "Saladoblanco", "San Agust??n", "Santa Mar??a",
                "Suaza", "Tarqui", "Tello", "Teruel", "Tesalia", "Timan??", "Villavieja", "Yaguar??"
        };
        final String[] municipiosGuajira = new String[]{
                "Albania", "Barrancas", "Dibulla", "Distracci??n", "El Molino", "Fonseca", "Hatonuevo", "La Jagua Del Pilar",
                "Maicao", "Manaure", "Riohacha", "San Juan Del Cesar", "Uribia", "Urumita", "Villanueva"
        };
        final String[] municipiosMagdalena = new String[]{
                "Algarrobo", "Aracataca", "Ariguan??", "Cerro de San Antonio", "Chibolo", "Ci??naga", "Concordia", "El Banco",
                "El Pi??on", "El Ret??n", "Fundaci??n", "Guamal", "Nueva Granada", "Pedraza", "Piji??o Del Carmen", "Pivijay",
                "Plato", "Puebloviejo", "Remolino", "Sabanas De San Angel", "Salamina", "San Sebasti??n De Buenavista",
                "San Zen??n", "Santa Ana", "Santa B??rbara De Pinto", "Santa Marta", "Sitionuevo", "Tenerife", "Zapay??n",
                "Zona Bananera"
        };
        final String[] municipiosMeta = new String[]{
                "Acac??as", "Barranca De Up??a", "Cabuyaro", "Castilla La Nueva", "Cubarral", "Cumaral", "El Calvario",
                "El Castillo", "El Dorado", "Fuente De Oro", "Granada", "Guamal", "La Macarena", "Lejan??as", "Mapirip??n",
                "Mesetas", "Puerto Concordia", "Puerto Gait??n", "Puerto Lleras", "Puerto L??pez", "Puerto Rico", "Restrepo",
                "San Carlos De Guaroa", "San Juan De Arama", "San Juanito", "San Mart??n", "Uribe", "Villavicencio",
                "Vistahermosa"
        };
        final String[] municipiosNarino = new String[]{
                "Alb??n", "Aldana", "Ancuy??", "Arboleda", "Barbacoas", "Bel??n", "Buesaco", "Chachag????", "Col??n", "Consac??",
                "Contadero", "C??rdoba", "Cuaspud", "Cumbal", "Cumbitara", "El Charco", "El Pe??ol", "El Rosario",
                "El Tabl??n De G??mez", "El Tambo", "Francisco Pizarro", "Funes", "Guachucal", "Guaitarilla", "Gualmat??n", "Iles",
                "Imu??s", "Ipiales", "La Cruz", "La Florida", "La Llanada", "La Tola", "La Uni??n", "Leiva", "Linares", "Los Andes",
                "Mag??i Pay??n", "Mallama", "Mosquera", "Nari??o", "Olaya Herrera", "Ospina", "Pasto", "Policarpa", "Potos??",
                "Providencia", "Puerres", "Pupiales", "Ricaurte", "Roberto Pay??n", "Samaniego", "San Bernardo", "San Lorenzo",
                "San Pablo", "San Pedro De Cartago", "Sandon??", "Santa B??rbara", "Santacruz", "Sapuyes", "Taminango", "Tangua",
                "Tumaco", "T??querres", "Yacuanquer"
        };
        final String[] municipiosNdeSantander = new String[]{
                "Abrego", "Arboledas", "Bochalema", "Bucarasica", "Cachir??", "C??cota", "Chin??cota", "Chitag??", "Convenci??n",
                "C??cuta", "Cucutilla", "Durania", "El Carmen", "El Tarra", "El Zulia", "Gramalote", "Hacar??", "Herr??n",
                "La Esperanza", "La Playa", "Labateca", "Los Patios", "Lourdes", "Mutiscua", "Oca??a", "Pamplona", "Pamplonita",
                "Puerto Santander", "Ragonvalia", "Salazar de las palmas", "San Calixto", "San Cayetano", "Santiago", "Sardinata",
                "Silos", "Teorama", "Tib??", "Toledo", "Villa Caro", "Villa Del Rosario"
        };
        final String[] municipiosPutumayo = new String[]{
                "Col??n", "Mocoa", "Orito", "Puerto As??s", "Puerto Caicedo", "Puerto Guzm??n", "Puerto Legu??zamo", "San Francisco",
                "San Miguel", "Santiago", "Sibundoy", "Valle Del Guamuez", "Villagarz??n"
        };
        final String[] municipiosQuindio = new String[]{
                "Armenia", "Buenavista", "Calarc??", "Circasia", "C??rdoba", "Filandia", "G??nova", "La Tebaida", "Montenegro",
                "Pijao", "Quimbaya", "Salento"
        };
        final String[] municipiosRisaralda = new String[]{
                "Ap??a", "Balboa", "Bel??n De Umbr??a", "Dosquebradas", "Gu??tica", "La Celia", "La Virginia", "Marsella", "Mistrat??",
                "Pereira", "Pueblo Rico", "Quinch??a", "Santa Rosa De Cabal", "Santuario"
        };
        final String[] municipiosSanAndres = new String[]{
                "San Andr??s", "Providencia y Santa Catalina"
        };
        final String[] municipiosSantander = new String[]{
                "Aguada", "Albania", "Aratoca", "Barbosa", "Barichara", "Barrancabermeja", "Betulia", "Bol??var", "Bucaramanga",
                "Cabrera", "California", "Capitanejo", "Carcas??", "Cepit??", "Cerrito", "Charal??", "Charta", "Chima", "Chipat??",
                "Cimitarra", "Concepci??n", "Confines", "Contrataci??n", "Coromoro", "Curit??", "El Carmen De Chucur??",
                "El Guacamayo", "El Pe????n", "El Play??n", "Encino", "Enciso", "Flori??n", "Floridablanca", "Gal??n", "G??mbita",
                "Gir??n", "Guaca", "Guadalupe", "Guapot??", "Guavat??", "G??epsa", "Hato", "Jes??s Mar??a", "Jord??n", "La Belleza",
                "La Paz", "Land??zuri", "Lebr??ja", "Los Santos", "Macaravita", "M??laga", "Matanza", "Mogotes", "Molagavita",
                "Ocamonte", "Oiba", "Onzaga", "Palmar", "Palmas Del Socorro", "P??ramo", "Piedecuesta", "Pinchote",
                "Puente Nacional", "Puerto Parra", "Puerto Wilches", "Rionegro", "Sabana De Torres", "San Andr??s", "San Benito",
                "San Gil", "San Joaqu??n", "San Jos?? De Miranda", "San Miguel", "San Vicente De Chucur??", "Santa B??rbara",
                "Santa Helena Del Op??n", "Simacota", "Socorro", "Suaita", "Sucre", "Surat??", "Tona", "Valle De San Jos??",
                "V??lez", "Vetas", "Villanueva", "Zapatoca"
        };
        final String[] municipiosSucre = new String[]{
                "Buenavista", "Caimito", "Chal??n", "Colos??", "Corozal", "Cove??as", "El Roble", "Galeras", "Guaranda", "La Uni??n",
                "Los Palmitos", "Majagual", "Morroa", "Ovejas", "Palmito", "Sampu??s", "San Benito Abad", "San Juan De Betulia",
                "San Marcos", "San Onofre", "San Pedro", "Santiago De Tol??", "Sinc??", "Sincelejo", "Sucre", "Tol?? Viejo"
        };
        final String[] municipiosTolima = new String[]{
                "Alpujarra", "Alvarado", "Ambalema", "Anzo??tegui", "Armero Guayabal", "Ataco", "Cajamarca", "Carmen De Apical??",
                "Casabianca", "Chaparral", "Coello", "Coyaima", "Cunday", "Dolores", "Espinal", "Falan", "Flandes", "Fresno",
                "Guamo", "Herveo", "Honda", "Ibagu??", "Icononzo", "L??rida", "L??bano", "Mariquita", "Melgar", "Murillo",
                "Natagaima", "Ortega", "Palocabildo", "Piedras", "Planadas", "Prado", "Purificaci??n", "Rioblanco", "Roncesvalles",
                "Rovira", "Salda??a", "San Antonio", "San Luis", "Santa Isabel", "Su??rez", "Valle De San Juan", "Venadillo",
                "Villahermosa", "Villarrica"
        };
        final String[] municipiosValle = new String[]{
                "Alcal??", "Andaluc??a", "Ansermanuevo", "Argelia", "Bol??var", "Buenaventura", "Bugalagrande", "Caicedonia", "Cali",
                "Calima el Dari??n", "Candelaria", "Cartago", "Dagua", "El ??guila", "El Cairo", "El Cerrito", "El Dovio",
                "Florida", "Ginebra", "Guacar??", "Guadalajara De Buga", "Jamund??", "La Cumbre", "La Uni??n", "La Victoria",
                "Obando", "Palmira", "Pradera", "Restrepo", "Riofr??o", "Roldanillo", "San Pedro", "Sevilla", "Toro", "Trujillo",
                "Tulu??", "Ulloa", "Versalles", "Vijes", "Yotoco", "Yumbo", "Zarzal"
        };
        final String[] municipiosVaupes = new String[]{
                "Taruru", "Mit??", "Taraira"
        };
        final String[] municipiosVichada = new String[]{
                "Cumaribo", "La Primavera", "Puerto Carre??o", "Santa Rosal??a"
        };
        String[] tipoDoc = new String[] {
                "Tarjeta de Identidad","C??dula de Ciudadan??a","C??dula de Extranjer??a","Visa de Trabajo"
        };
        String[] gruposEtnicos = new String[] {
                "No Aplica","Afrocolombiano","Ind??gena","Mestizo","Otro, ??Cu??l?"
        };
        String[] genero = new String[] {
                "Masculino","Femenino"
        };
        String[] tipoPersona = new String[] {
                "Natural","Jur??dica"
        };
        String[] sino = new String[] {
                "S??","No"
        };
        String[] entidadesAsesoria = new String[] {
                "No Aplica","UNAL","MADR","CVC","ICA","AGROSAVIA","UMATA","Otra, ??Cu??l?"
        };
        String[] temasAsesoria = new String[] {
                "No Aplica","Gesti??n de la unidad de producci??n","Uso de insumos fertilizantes y otros","Cr??dito","Tecnificaci??n de la unidad de producci??n en todas las fases","Desarrollo sostenible","Agremiaci??n","Comercializaci??n","Otros, ??Cu??les?"
        };
        String[] siNoNoAplica = new String[] {
                "No aplica", "Si", "No"
        };
        String[] gratisPago = new String[] {
                "No Aplica","Gratis","Pagada por el productor"
        };
        String[] entidadesFormacion = new String[] {
                "No Aplica","Instituciones/Universidades","Organizaciones gubernamentales","ONG, ??cu??l?","Asociaciones de productores","Otro, ??cu??l?"
        };
        String[] areasFormacion = new String[] {
                "No Aplica","Ambiental","Empresarial","Productivo","Tecnol??gico /innovaci??n","Buenas pr??cticas de agricolas","Otros ??Cu??les?"
        };

        Spinner ans1 = (Spinner) findViewById(R.id.ans1);
        ArrayAdapter<String> adapterAns1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, departamentos);
        adapterAns1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans1.setAdapter(adapterAns1);

        Spinner ans2 = (Spinner) findViewById(R.id.ans2);
        ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, municipiosAmazonas);
        adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans2.setAdapter(adapterAns2);

        Spinner ans8 = (Spinner) findViewById(R.id.ans8);
        ArrayAdapter<String> adapterAns8 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipoDoc);
        adapterAns8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans8.setAdapter(adapterAns8);

        Spinner ans9 = (Spinner) findViewById(R.id.ans9);
        ArrayAdapter<String> adapterAns9 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gruposEtnicos);
        adapterAns9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans9.setAdapter(adapterAns9);

        Spinner ans11 = (Spinner) findViewById(R.id.ans11);
        ArrayAdapter<String> adapterAns11 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genero);
        adapterAns11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans11.setAdapter(adapterAns11);

        Spinner ans14 = (Spinner) findViewById(R.id.ans14);
        ArrayAdapter<String> adapterAns14 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipoPersona);
        adapterAns14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans14.setAdapter(adapterAns14);

        Spinner ans15 = (Spinner) findViewById(R.id.ans15);
        ArrayAdapter<String> adapterAns15 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sino);
        adapterAns15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans15.setAdapter(adapterAns15);

        Spinner ans15_1 = (Spinner) findViewById(R.id.ans15_1);
        ArrayAdapter<String> adapterAns15_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, entidadesAsesoria);
        adapterAns15_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans15_1.setAdapter(adapterAns15_1);

        Spinner ans15_2 = (Spinner) findViewById(R.id.ans15_2);
        ArrayAdapter<String> adapterAns15_2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, temasAsesoria);
        adapterAns15_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans15_2.setAdapter(adapterAns15_2);

        Spinner ans15_3 = (Spinner) findViewById(R.id.ans15_3);
        ArrayAdapter<String> adapterAns15_3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sino);
        adapterAns15_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans15_3.setAdapter(adapterAns15_3);

        Spinner ans15_4 = (Spinner) findViewById(R.id.ans15_4);
        ArrayAdapter<String> adapterAns15_4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gratisPago);
        adapterAns15_4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans15_4.setAdapter(adapterAns15_4);

        Spinner ans16 = (Spinner) findViewById(R.id.ans16);
        ArrayAdapter<String> adapterAns16 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sino);
        adapterAns16.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans16.setAdapter(adapterAns16);

        Spinner ans17 = (Spinner) findViewById(R.id.ans17);
        ArrayAdapter<String> adapterAns17 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sino);
        adapterAns17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans17.setAdapter(adapterAns17);

        Spinner ans17_1 = (Spinner) findViewById(R.id.ans17_1);
        ArrayAdapter<String> adapterAns17_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, entidadesFormacion);
        adapterAns17_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans17_1.setAdapter(adapterAns17_1);

        Spinner ans17_2 = (Spinner) findViewById(R.id.ans17_2);
        ArrayAdapter<String> adapterAns17_2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, areasFormacion);
        adapterAns17_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans17_2.setAdapter(adapterAns17_2);

        Spinner ans17_3 = (Spinner) findViewById(R.id.ans17_3);
        ArrayAdapter<String> adapterAns17_3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNoNoAplica);
        adapterAns17_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans17_3.setAdapter(adapterAns17_3);

        Spinner ans17_4 = (Spinner) findViewById(R.id.ans17_4);
        ArrayAdapter<String> adapterAns17_4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gratisPago);
        adapterAns17_4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans17_4.setAdapter(adapterAns17_4);

        EditText ans9other = (EditText) findViewById(R.id.ans9other);
        ans9other.setVisibility(View.GONE);

        EditText ans15_1other = (EditText) findViewById(R.id.ans15_1other);
        ans15_1other.setVisibility(View.GONE);

        EditText ans15_2other = (EditText) findViewById(R.id.ans15_2other);
        ans15_2other.setVisibility(View.GONE);

        EditText ans17_1other = (EditText) findViewById(R.id.ans17_1other);
        ans17_1other.setVisibility(View.GONE);

        EditText ans17_2other = (EditText) findViewById(R.id.ans17_2other);
        ans17_2other.setVisibility(View.GONE);

        ans1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans2 = (Spinner) findViewById(R.id.ans2);
                Spinner ans1 = (Spinner) findViewById(R.id.ans1);
                if(ans1.getSelectedItemPosition() == 0){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosAmazonas);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 1){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosAntioquia);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 2){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosArauca);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 3){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosAtlantico);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 4){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosBolivar);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 5){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosBoyaca);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 6){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosCaldas);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 7){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosCaqueta);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 8){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosCasanare);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 9){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosCauca);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 10){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosCesar);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 11){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosChoco);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 12){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosCordoba);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 13){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosCundinamarca);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 14){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosGuainia);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 15){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosGuaviare);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 16){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosHuila);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 17){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosGuajira);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 18){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosMagdalena);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 18){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosMeta);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 20){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosNarino);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 21){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosNdeSantander);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 22){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosPutumayo);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 23){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosQuindio);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 24){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosRisaralda);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 25){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosSanAndres);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 26){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosSantander);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 27){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosSucre);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 28){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosTolima);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 29){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosValle);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 30){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosVaupes);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }else if(ans1.getSelectedItemPosition() == 31){
                    ArrayAdapter<String> adapterAns2 = new ArrayAdapter<String>(Preguntas1.this, android.R.layout.simple_spinner_item, municipiosVichada);
                    adapterAns2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans2.setAdapter(adapterAns2);
                }
             }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans9 = (Spinner) findViewById(R.id.ans9);
                EditText ans9other = (EditText) findViewById(R.id.ans9other);
                if(ans9.getSelectedItemPosition()==4) {
                    ans9other.setVisibility(View.VISIBLE);
                }else{
                    ans9other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans15.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans15 = (Spinner) findViewById(R.id.ans15);
                TextView ques15_1 = (TextView) findViewById(R.id.ques15_1);
                Spinner ans15_1 = (Spinner) findViewById(R.id.ans15_1);
                TextView ques15_2 = (TextView) findViewById(R.id.ques15_2);
                Spinner ans15_2 = (Spinner) findViewById(R.id.ans15_2);
                TextView ques15_3 = (TextView) findViewById(R.id.ques15_3);
                Spinner ans15_3 = (Spinner) findViewById(R.id.ans15_3);
                TextView ques15_4 = (TextView) findViewById(R.id.ques15_4);
                Spinner ans15_4 = (Spinner) findViewById(R.id.ans15_4);
                if(ans15.getSelectedItemPosition()==1) {
                    ques15_1.setVisibility(View.GONE);
                    ans15_1.setVisibility(View.GONE);
                    ques15_2.setVisibility(View.GONE);
                    ans15_2.setVisibility(View.GONE);
                    ques15_3.setVisibility(View.GONE);
                    ans15_3.setVisibility(View.GONE);
                    ques15_4.setVisibility(View.GONE);
                    ans15_4.setVisibility(View.GONE);
                }else{
                    ques15_1.setVisibility(View.VISIBLE);
                    ans15_1.setVisibility(View.VISIBLE);
                    ques15_2.setVisibility(View.VISIBLE);
                    ans15_2.setVisibility(View.VISIBLE);
                    ques15_3.setVisibility(View.VISIBLE);
                    ans15_3.setVisibility(View.VISIBLE);
                    ques15_4.setVisibility(View.VISIBLE);
                    ans15_4.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans15_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans15_1 = (Spinner) findViewById(R.id.ans15_1);
                EditText ans15_1other = (EditText) findViewById(R.id.ans15_1other);
                if(ans15_1.getSelectedItemPosition()==7) {
                    ans15_1other.setVisibility(View.VISIBLE);
                }else{
                    ans15_1other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans15_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans15_2 = (Spinner) findViewById(R.id.ans15_2);
                EditText ans15_2other = (EditText) findViewById(R.id.ans15_2other);
                if(ans15_2.getSelectedItemPosition()==8) {
                    ans15_2other.setVisibility(View.VISIBLE);
                }else{
                    ans15_2other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans17.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans17 = (Spinner) findViewById(R.id.ans17);
                TextView ques17_1 = (TextView) findViewById(R.id.ques17_1);
                Spinner ans17_1 = (Spinner) findViewById(R.id.ans17_1);
                TextView ques17_2 = (TextView) findViewById(R.id.ques17_2);
                Spinner ans17_2 = (Spinner) findViewById(R.id.ans17_2);
                TextView ques17_3 = (TextView) findViewById(R.id.ques17_3);
                Spinner ans17_3 = (Spinner) findViewById(R.id.ans17_3);
                TextView ques17_4 = (TextView) findViewById(R.id.ques17_4);
                Spinner ans17_4 = (Spinner) findViewById(R.id.ans17_4);
                if(ans17.getSelectedItemPosition()==1) {
                    ques17_1.setVisibility(View.GONE);
                    ans17_1.setVisibility(View.GONE);
                    ques17_2.setVisibility(View.GONE);
                    ans17_2.setVisibility(View.GONE);
                    ques17_3.setVisibility(View.GONE);
                    ans17_3.setVisibility(View.GONE);
                    ques17_4.setVisibility(View.GONE);
                    ans17_4.setVisibility(View.GONE);
                }else{
                    ques17_1.setVisibility(View.VISIBLE);
                    ans17_1.setVisibility(View.VISIBLE);
                    ques17_2.setVisibility(View.VISIBLE);
                    ans17_2.setVisibility(View.VISIBLE);
                    ques17_3.setVisibility(View.VISIBLE);
                    ans17_3.setVisibility(View.VISIBLE);
                    ques17_4.setVisibility(View.VISIBLE);
                    ans17_4.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans17_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans17_1 = (Spinner) findViewById(R.id.ans17_1);
                EditText ans17_1other = (EditText) findViewById(R.id.ans17_1other);
                if(ans17_1.getSelectedItemPosition()==3 || ans17_1.getSelectedItemPosition()==5) {
                    ans17_1other.setVisibility(View.VISIBLE);
                }else{
                    ans17_1other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans17_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans17_2 = (Spinner) findViewById(R.id.ans17_2);
                EditText ans17_2other = (EditText) findViewById(R.id.ans17_2other);
                if(ans17_2.getSelectedItemPosition()==6) {
                    ans17_2other.setVisibility(View.VISIBLE);
                }else{
                    ans17_2other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        nextButtonAT =  (Button) findViewById(R.id.nextButtonAT);
        nextButtonAT.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                escribir();
                Intent preg2 = new Intent(getApplicationContext(), Preguntas2.class);
                startActivity(preg2);
            }
        });

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {

        }
        else {
            myExternalFile = new File(getExternalFilesDir(filepath), filename);
        }

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

    private void escribir(){
        try {
            EditText ans3 = (EditText) findViewById(R.id.ans3);
            EditText ans4 = (EditText) findViewById(R.id.ans4);
            EditText ans5 = (EditText) findViewById(R.id.ans5);
            EditText ans6 = (EditText) findViewById(R.id.ans6);
            EditText ans7 = (EditText) findViewById(R.id.ans7);
            EditText ans8_1 = (EditText) findViewById(R.id.ans8_1);
            EditText ans8_2 = (EditText) findViewById(R.id.ans8_2);
            EditText ans10 = (EditText) findViewById(R.id.ans10);
            EditText ans12 = (EditText) findViewById(R.id.ans12);
            EditText ans13 = (EditText) findViewById(R.id.ans13);
            EditText ans9other = (EditText) findViewById(R.id.ans9other);
            EditText ans15_1other = (EditText) findViewById(R.id.ans15_1other);
            EditText ans15_2other = (EditText) findViewById(R.id.ans15_2other);
            EditText ans17_1other = (EditText) findViewById(R.id.ans17_1other);
            EditText ans17_2other = (EditText) findViewById(R.id.ans17_2other);

            Spinner ans1 = (Spinner) findViewById(R.id.ans1);
            Spinner ans2 = (Spinner) findViewById(R.id.ans2);
            Spinner ans8 = (Spinner) findViewById(R.id.ans8);
            Spinner ans9 = (Spinner) findViewById(R.id.ans9);
            Spinner ans11 = (Spinner) findViewById(R.id.ans11);
            Spinner ans14 = (Spinner) findViewById(R.id.ans14);
            Spinner ans15 = (Spinner) findViewById(R.id.ans15);
            Spinner ans15_1 = (Spinner) findViewById(R.id.ans15_1);
            Spinner ans15_2 = (Spinner) findViewById(R.id.ans15_2);
            Spinner ans15_3 = (Spinner) findViewById(R.id.ans15_3);
            Spinner ans15_4 = (Spinner) findViewById(R.id.ans15_4);
            Spinner ans16 = (Spinner) findViewById(R.id.ans16);
            Spinner ans17 = (Spinner) findViewById(R.id.ans17);
            Spinner ans17_1 = (Spinner) findViewById(R.id.ans17_1);
            Spinner ans17_2 = (Spinner) findViewById(R.id.ans17_2);
            Spinner ans17_3 = (Spinner) findViewById(R.id.ans17_3);
            Spinner ans17_4 = (Spinner) findViewById(R.id.ans17_4);

            FileOutputStream fos = new FileOutputStream(myExternalFile,true);

            fos.write("\t".getBytes());
            fos.write(ans1.getItemAtPosition(ans1.getSelectedItemPosition()).toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans2.getItemAtPosition(ans2.getSelectedItemPosition()).toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans3.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans4.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans5.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans6.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans7.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans8.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans8_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans8_2.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans9.getSelectedItemPosition()).getBytes());
            if(ans9.getSelectedItemPosition()==4){
                fos.write(", ".getBytes());
            }
            fos.write(ans9other.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans10.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans11.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans12.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans13.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans14.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans15.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans15_1.getSelectedItemPosition()).getBytes());
            if(ans15_1.getSelectedItemPosition()==7){
                fos.write(", ".getBytes());
            }
            fos.write(ans15_1other.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans15_2.getSelectedItemPosition()).getBytes());
            if(ans15_2.getSelectedItemPosition()==8){
                fos.write(", ".getBytes());
            }
            fos.write(ans15_2other.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans15_3.getSelectedItemPosition()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans15_4.getSelectedItemPosition()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans16.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans17.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans17_1.getSelectedItemPosition()).getBytes());
            if(ans17_1.getSelectedItemPosition()==3 || ans17_1.getSelectedItemPosition()==5){
                fos.write(", ".getBytes());
            }
            fos.write(ans17_1other.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans17_2.getSelectedItemPosition()).getBytes());
            if(ans17_2.getSelectedItemPosition()==6){
                fos.write(", ".getBytes());
            }
            fos.write(ans17_2other.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans17_3.getSelectedItemPosition()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans17_4.getSelectedItemPosition()).getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class MyLocationListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
            EditText ans5 = (EditText) findViewById(R.id.ans5);
            ans5.setText(Double.toString(location.getLatitude()) + ", " + Double.toString(location.getLongitude()));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            //Toast.makeText(Preguntas1.this, "Provider status changed",
            //        Toast.LENGTH_LONG).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            //Toast.makeText(Preguntas1.this, "Provider enabled by the user. GPS turned on",
            //        Toast.LENGTH_LONG).show();
        }

        @Override
        public void onProviderDisabled(String provider) {
            //Toast.makeText(Preguntas1.this, "Provider enabled by the user. GPS turned off",
            //        Toast.LENGTH_LONG).show();
        }
    }
}
