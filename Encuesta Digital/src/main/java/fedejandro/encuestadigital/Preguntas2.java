package fedejandro.encuestadigital;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.io.*;

public class Preguntas2 extends AppCompatActivity {

    Button saveButton;
    private String filename = "datos.txt";
    private String filepath = "EncuestaDatos";
    File myExternalFile;

    public int ans21code = 0;
    public int ans32_1code = 0;
    public int ans33_2code = 0;
    public int ans34code = 0;
    public int ans35code = 0;
    public int sumaFertirriego = 0;
    public int sumaEstacion = 0;
    public int sumaTanques = 0;
    public int sumaMacrotuneles = 0;
    public int sumaProcesosAgro = 0;
    public int sumaAlmacenamiento = 0;
    public int sumaRiego = 0;
    public int sumaOtraTecnologia = 0;
    public int promedioSumas = 0;
    public int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas2);

        String[] tipoCultivo = new String[] {
                "Piña MD2", "Mora", "Aguacate"
        };
        String[] tipoActividad = new String[] {
                "Principal", "Complementaria"
        };
        String[] fuenteAgua = new String[] {
                "Agua lluvia", "Ríos/canales", "Lagos/embalses", "Diques", "Pozos profundos/pozos entubados", "Pozos poco profundos", "Abastecimiento municipal de aguas", "Aguas residuales tratadas", "Otra, ¿Cuál?"
        };
        String[] transporteAgua = new String[] {
                "Por gravedad", "Por bombeo", "Desvio de cauce", "Otra, ¿Cuál?"
        };
        String[] siNo = new String[] {
                "Sí", "No"
        };
        String[] destinoProd = new String[] {
                "Autoconsumo", "Comercialización", "Ambas"
        };
        String[] unidadVenta = new String[] {
                "Kilogramos", "Toneladas", "Bultos", "Otra, ¿Cuál?"
        };
        String[] noAdopcionTecnologia = new String[] {
                "No aplica", "Costo elevado de la tecnologia", "La tecnologia no resuelve un problema en su sistema de producción", "No cree que la inversion se justifique", "Otro, ¿Cuál?"
        };
        String[] porcentajeAdopcionTecnologia = new String[] {
                "No aplica", "Un lote", "Menos de la mitad de la UPA", "Mitad de la UPA", "Más de la mitad de la UPA", "Todo"
        };
        String[] siNoNA = new String[] {
                "No aplica", "Sí", "No"
        };
        String[] indicadoresMejora = new String[] {
                "No aplica", "Mejores rendimientos productivos", "Disminución de costos de producción", "Menos uso de agua para riego", "Cadena de distribución mas eficiente", "Otro, ¿Cuál?"
        };
        String[] perdidas = new String[] {
                "No aplica", "Menor al 20% (Pérdidas mínimas)", "Entre el 20% y el 40% (Pérdidas moderadas)", "Mayor al 40% (Perdidas graves)"
        };
        String[] fuenteEnergia = new String[] {
                "Red eléctrica publica", "Generador eléctrico", "Energía solar", "Energía eólica", "Otro, ¿Cuál?"
        };
        String[] tipoAsociacion = new String[] {
                "No aplica", "Cooperativa", "Asociacion", "Sindicato", "Otro, ¿Cuál?"
        };
        String[] departamentos = new String[] {
                "Amazonas","Antioquia","Arauca","Atlántico","Bolívar","Boyacá","Caldas","Caquetá","Casanare","Cauca","Cesar",
                "Chocó","Córdoba","Cundinamarca","Guainía","Guaviare","Huila","La Guajira","Magdalena","Meta","Nariño",
                "Norte de Santander","Putumayo","Quindío","Risaralda","San Andrés y Providencia","Santander","Sucre","Tolima",
                "Valle del Cauca","Vaupés","Vichada"
        };
        final String[] municipiosAmazonas = new String[]{
                "Leticia", "Puerto Nariño"
        };
        final String[] municipiosAntioquia = new String[] {
                "Medellín", "Abejorral", "Abriaqui", "Alejandría", "Amaga", "Amalfi", "Andes", "Angelopolis", "Angostura",
                "Anorí", "Anza", "Apartado", "Arboletes", "Argelia", "Armenia", "Barbosa", "Bello", "Belmira", "Betania",
                "Betulia", "Bolívar", "Briceño", "Buritica", "Cáceres", "Caicedo", "Caldas", "Campamento", "Cañasgordas",
                "Caracolí", "Caramanta", "Carepa", "Carmen De Viboral", "Carolina", "Caucasia", "Chigorodó", "Cisneros",
                "Cocorná", "Concepción", "Concordia", "Copacabana", "Dabeiba", "Don Matías", "Ebejico", "El Bagre",
                "Entrerrios", "Envigado", "Fredonia", "Frontino", "Giraldo", "Girardota", "Gómez Plata", "Granada",
                "Guadalupe", "Guarne", "Guatapé", "Heliconia", "Hispania", "Itagui", "Ituango", "Jardín", "Jericó", "La Ceja",
                "La Estrella", "La Pintada", "La Unión", "Liborina", "Maceo", "Marinilla", "Montebello", "Murindó", "Mutatá",
                "Nariño", "Nechí", "Necoclí", "Olaya", "Peñol", "Peque", "Pueblorrico", "Puerto Berrío", "Puerto Nare",
                "Puerto Triunfo", "Remedios", "Retiro", "Rionegro", "Sabanalarga", "Sabaneta", "Salgar", "San Andrés",
                "San Carlos", "San Francisco", "San Pedro De Uraba", "San José De La Montaña", "San Juan De Uraba",
                "Santa Rosa De Osos", "San Pedro", "San Jerónimo", "San Rafael", "San Roque", "San Vicente", "Santa Bárbara",
                "San Luis", "Santafé de Antioquia", "Santo Domingo", "Santuario", "Segovia", "Sonson", "Sopetrán", "Támesis",
                "Taraza", "Tarso", "Titiribí", "Toledo", "Turbo", "Uramita", "Urrao", "Valdivia", "Valparaíso", "Vegachi",
                "Venecia", "Vigía Del Fuerte", "Yali", "Yarumal", "Yolombó", "Yondó", "Zaragoza"
        };
        final String[] municipiosArauca = new String[] {
                "Arauca", "Arauquita", "Cravo Norte", "Fortul", "Puerto Rondón", "Saravena", "Tame"
        };
        final String[] municipiosAtlantico = new String[]{
                "Baranoa", "Barranquilla", "Campo De La Cruz", "Candelaria", "Galapa", "Juan De Acosta", "Luruaco", "Malambo",
                "Manatí", "Palmar De Varela", "Piojó", "Polonuevo", "Ponedera", "Puerto Colombia", "Repelón", "Sabanagrande",
                "Sabanalarga", "Santa Lucía", "Santo Tomás", "Soledad", "Suan", "Tubará", "Usiacurí"
        };
        final String[] municipiosBolivar = new String[]{
                "Achí", "Altos Del Rosario", "Arenal", "Arjona", "Arroyohondo", "Barranco De Loba", "Calamar", "Cantagallo",
                "Cartagena", "Cicuco", "Clemencia", "Córdoba", "El Carmen De Bolívar", "El Guamo", "El Peñón", "Hatillo De Loba",
                "Magangué", "Mahates", "Margarita", "María La Baja", "Mompós", "Montecristo", "Morales", "Pinillos", "Regidor",
                "Río Viejo", "San Cristóbal", "San Estanislao", "San Fernando", "San Jacinto", "San Jacinto Del Cauca",
                "San Juan Nepomuceno", "San Martín De Loba", "San Pablo", "Santa Catalina", "Santa Rosa", "Santa Rosa Del Sur",
                "Simití", "Soplaviento", "Talaigua Nuevo", "Tiquisio", "Turbaco", "Turbaná", "Villanueva", "Zambrano"
        };
        final String[] municipiosBoyaca = new String[]{
                "Almeida", "Aquitania", "Arcabuco", "Belén", "Berbeo", "Betéitiva", "Boavita", "Boyacá", "Briceño", "Buenavista",
                "Busbanzá", "Caldas", "Campohermoso", "Cerinza", "Chinavita", "Chiquinquirá", "Chíquiza", "Chiscas", "Chita",
                "Chitaraque", "Chivatá", "Chivor", "Ciénega", "Cómbita", "Coper", "Corrales", "Covarachía", "Cubará", "Cucaita",
                "Cuítiva", "Duitama", "El Cocuy", "El Espino", "Firavitoba", "Floresta", "Gachantivá", "Gameza", "Garagoa",
                "Guacamayas", "Guateque", "Guayatá", "Güicán", "Iza", "Jenesano", "Jericó", "La Capilla", "La Uvita",
                "La Victoria", "Labranzagrande", "Macanal", "Maripí", "Miraflores", "Mongua", "Monguí", "Moniquirá", "Motavita",
                "Muzo", "Nobsa", "Nuevo Colón", "Oicatá", "Otanche", "Pachavita", "Páez", "Paipa", "Pajarito", "Panqueba",
                "Pauna", "Paya", "Paz De Río", "Pesca", "Pisba", "Puerto Boyacá", "Quípama", "Ramiriquí", "Ráquira", "Rondón",
                "Saboyá", "Sáchica", "Samacá", "San Eduardo", "San José De Pare", "San Luis De Gaceno", "San Mateo",
                "San Miguel De Sema", "San Pablo De Borbur", "Santa María", "Santa Rosa De Viterbo", "Santa Sofía", "Santana",
                "Sativanorte", "Sativasur", "Siachoque", "Soatá", "Socha", "Socotá", "Sogamoso", "Somondoco", "Sora", "Soracá",
                "Sotaquirá", "Susacón", "Sutamarchán", "Sutatenza", "Tasco", "Tenza", "Tibaná", "Tibasosa", "Tinjacá",
                "Tipacoque", "Toca", "Togüí", "Tópaga", "Tota", "Tunja", "Tununguá", "Turmequé", "Tuta", "Tutazá", "Umbita",
                "Ventaquemada", "Villa De Leyva", "Viracachá", "Zetaquira"
        };
        final String[] municipiosCaldas = new String[]{
                "Aguadas", "Anserma", "Aranzazu", "Belalcázar", "Chinchiná", "Filadelfia", "La Dorada", "La Merced", "Manizales",
                "Manzanares", "Marmato", "Marquetalia", "Marulanda", "Neira", "Norcasia", "Pácora", "Palestina", "Pensilvania",
                "Riosucio", "Risaralda", "Salamina", "Samaná", "San José", "Supía", "Victoria", "Villamaría", "Viterbo"
        };
        final String[] municipiosCaqueta = new String[]{
                "Albania", "Belén De Los Andaquies", "Cartagena Del Chairá", "Curillo", "El Doncello", "El Paujil", "Florencia",
                "La Montañita", "Milán", "Morelia", "Puerto Rico", "San José Del Fragua", "San Vicente Del Caguán", "Solano",
                "Solita", "Valparaíso"
        };
        final String[] municipiosCasanare = new String[]{
                "Aguazul", "Chameza", "Hato Corozal", "La Salina", "Maní", "Monterrey", "Nunchía", "Orocué", "Paz De Ariporo",
                "Pore", "Recetor", "Sabanalarga", "Sácama", "San Luis De Palenque", "Támara", "Tauramena", "Trinidad",
                "Villanueva", "Yopal"
        };
        final String[] municipiosCauca = new String[]{
                "Almaguer", "Argelia", "Balboa", "Bolívar", "Buenos Aires", "Cajibío", "Caldonó", "Caloto", "Corinto",
                "El Tambo", "Florencia", "Guapi", "Inzá", "Jambaló", "La Sierra", "La Vega", "López de Micay", "Mercaderes",
                "Miranda", "Morales", "Padilla", "Paez", "Patía", "Piamonte", "Piendamó", "Popayán", "Puerto Tejada", "Puracé",
                "Rosas", "San Sebastián", "Santa Rosa", "Santander De Quilichao", "Silvia", "Sotará", "Suárez", "Sucre",
                "Timbío", "Timbiquí", "Toribío", "Totoró", "Villa Rica"
        };
        final String[] municipiosCesar = new String[]{
                "Aguachica", "Agustín Codazzi", "Astrea", "Becerril", "Bosconia", "Chimichagua", "Chiriguaná", "Curumaní",
                "El Copey", "El Paso", "Gamarra", "González", "La Gloria", "La Jagua De Ibirico", "La Paz", "Manaure",
                "Pailitas", "Pelaya", "Pueblo Bello", "Río De Oro", "San Alberto", "San Diego", "San Martín", "Tamalameque",
                "Valledupar"
        };
        final String[] municipiosChoco = new String[]{
                "Acandí", "Alto Baudó", "Atrato", "Bagadó", "Bahía Solano", "Bajo Baudó", "Bojaya", "Carmen Del Darién",
                "Cértegui", "Condoto", "El Cantón Del San Pablo", "El Carmen De Atrato", "El Litoral Del San Juan", "Istmina",
                "Juradó", "Lloró", "Medio Atrato", "Medio Baudó", "Medio San Juan", "Nóvita", "Nuquí", "Quibdó", "Río Iro",
                "Río Quito", "Riosucio", "San José Del Palmar", "Sipí", "Tadó", "Unguía", "Unión Panamericana"
        };
        final String[] municipiosCordoba = new String[]{
                "Ayapel", "Buenavista", "Canalete", "Cereté", "Chimá", "Chinú", "Ciénaga De Oro", "Cotorra", "La Apartada",
                "Los Córdobas", "Momil", "Moñitos", "Montelíbano", "Montería", "Planeta Rica", "Pueblo Nuevo",
                "Puerto Escondido", "Puerto Libertador", "Purísima", "Sahagún", "San Andrés de Sotavento", "San Antero",
                "San Bernardo Del Viento", "San Carlos", "San Pelayo", "Santa Cruz de Lorica", "Tierralta", "Tuchín", "Valencia"
        };
        final String[] municipiosCundinamarca = new String[]{
                "Agua De Dios", "Albán", "Anapoima", "Anolaima", "Apulo", "Arbeláez", "Beltrán", "Bituima", "Bogotá", "Bojacá",
                "Cabrera", "Cachipay", "Cajicá", "Caparrapí", "Cáqueza", "Carmen De Carupa", "Chaguaní", "Chía", "Chipaque",
                "Choachí", "Chocontá", "Cogua", "Cota", "Cucunubá", "El Colegio", "El Peñón", "El Rosal", "Facatativá", "Fomeque",
                "Fosca", "Funza", "Fúquene", "Fusagasugá", "Gachalá", "Gachancipá", "Gachetá", "Gama", "Girardot", "Granada",
                "Guachetá", "Guaduas", "Guasca", "Guataquí", "Guatavita", "Guayabal De Siquima", "Guayabetal", "Gutiérrez",
                "Jerusalén", "Junín", "La Calera", "La Mesa", "La Palma", "La Peña", "La Vega", "Lenguazaque", "Macheta",
                "Madrid", "Manta", "Medina", "Mosquera", "Nariño", "Nemocón", "Nilo", "Nimaima", "Nocaima", "Pacho", "Paime",
                "Pandi", "Paratebueno", "Pasca", "Puerto Salgar", "Pulí", "Quebradanegra", "Quetame", "Quipile", "Ricaurte",
                "San Antonio Del Tequendama", "San Bernardo", "San Cayetano", "San Francisco", "San Juan De Río Seco", "Sasaima",
                "Sesquilé", "Sibaté", "Silvania", "Simijaca", "Soacha", "Sopó", "Subachoque", "Suesca", "Supatá", "Susa",
                "Sutatausa", "Tabio", "Tausa", "Tena", "Tenjo", "Tibacuy", "Tibirita", "Tocaima", "Tocancipá", "Topaipí",
                "Ubalá", "Ubaque", "Ubaté", "Une", "Útica", "Venecia", "Vergara", "Vianí", "Villagómez", "Villapinzón", "Villeta",
                "Viotá", "Yacopí", "Zipacón", "Zipaquirá"
        };
        final String[] municipiosGuainia = new String[]{
                "Inírida"
        };
        final String[] municipiosGuaviare = new String[]{
                "Calamar", "El Retorno", "Miraflores", "San José Del Guaviare"
        };
        final String[] municipiosHuila = new String[]{
                "Acevedo", "Agrado", "Aipe", "Algeciras", "Altamira", "Baraya", "Campoalegre", "Colombia", "Elías", "Garzón",
                "Gigante", "Guadalupe", "Hobo", "Iquira", "Isnos", "La Argentina", "La Plata", "Nátaga", "Neiva", "Oporapa",
                "Paicol", "Palermo", "Palestina", "Pital", "Pitalito", "Rivera", "Saladoblanco", "San Agustín", "Santa María",
                "Suaza", "Tarqui", "Tello", "Teruel", "Tesalia", "Timaná", "Villavieja", "Yaguará"
        };
        final String[] municipiosGuajira = new String[]{
                "Albania", "Barrancas", "Dibulla", "Distracción", "El Molino", "Fonseca", "Hatonuevo", "La Jagua Del Pilar",
                "Maicao", "Manaure", "Riohacha", "San Juan Del Cesar", "Uribia", "Urumita", "Villanueva"
        };
        final String[] municipiosMagdalena = new String[]{
                "Algarrobo", "Aracataca", "Ariguaní", "Cerro de San Antonio", "Chibolo", "Ciénaga", "Concordia", "El Banco",
                "El Piñon", "El Retén", "Fundación", "Guamal", "Nueva Granada", "Pedraza", "Pijiño Del Carmen", "Pivijay",
                "Plato", "Puebloviejo", "Remolino", "Sabanas De San Angel", "Salamina", "San Sebastián De Buenavista",
                "San Zenón", "Santa Ana", "Santa Bárbara De Pinto", "Santa Marta", "Sitionuevo", "Tenerife", "Zapayán",
                "Zona Bananera"
        };
        final String[] municipiosMeta = new String[]{
                "Acacías", "Barranca De Upía", "Cabuyaro", "Castilla La Nueva", "Cubarral", "Cumaral", "El Calvario",
                "El Castillo", "El Dorado", "Fuente De Oro", "Granada", "Guamal", "La Macarena", "Lejanías", "Mapiripán",
                "Mesetas", "Puerto Concordia", "Puerto Gaitán", "Puerto Lleras", "Puerto López", "Puerto Rico", "Restrepo",
                "San Carlos De Guaroa", "San Juan De Arama", "San Juanito", "San Martín", "Uribe", "Villavicencio",
                "Vistahermosa"
        };
        final String[] municipiosNarino = new String[]{
                "Albán", "Aldana", "Ancuyá", "Arboleda", "Barbacoas", "Belén", "Buesaco", "Chachagüí", "Colón", "Consacá",
                "Contadero", "Córdoba", "Cuaspud", "Cumbal", "Cumbitara", "El Charco", "El Peñol", "El Rosario",
                "El Tablón De Gómez", "El Tambo", "Francisco Pizarro", "Funes", "Guachucal", "Guaitarilla", "Gualmatán", "Iles",
                "Imués", "Ipiales", "La Cruz", "La Florida", "La Llanada", "La Tola", "La Unión", "Leiva", "Linares", "Los Andes",
                "Magüi Payán", "Mallama", "Mosquera", "Nariño", "Olaya Herrera", "Ospina", "Pasto", "Policarpa", "Potosí",
                "Providencia", "Puerres", "Pupiales", "Ricaurte", "Roberto Payán", "Samaniego", "San Bernardo", "San Lorenzo",
                "San Pablo", "San Pedro De Cartago", "Sandoná", "Santa Bárbara", "Santacruz", "Sapuyes", "Taminango", "Tangua",
                "Tumaco", "Túquerres", "Yacuanquer"
        };
        final String[] municipiosNdeSantander = new String[]{
                "Abrego", "Arboledas", "Bochalema", "Bucarasica", "Cachirá", "Cácota", "Chinácota", "Chitagá", "Convención",
                "Cúcuta", "Cucutilla", "Durania", "El Carmen", "El Tarra", "El Zulia", "Gramalote", "Hacarí", "Herrán",
                "La Esperanza", "La Playa", "Labateca", "Los Patios", "Lourdes", "Mutiscua", "Ocaña", "Pamplona", "Pamplonita",
                "Puerto Santander", "Ragonvalia", "Salazar de las palmas", "San Calixto", "San Cayetano", "Santiago", "Sardinata",
                "Silos", "Teorama", "Tibú", "Toledo", "Villa Caro", "Villa Del Rosario"
        };
        final String[] municipiosPutumayo = new String[]{
                "Colón", "Mocoa", "Orito", "Puerto Asís", "Puerto Caicedo", "Puerto Guzmán", "Puerto Leguízamo", "San Francisco",
                "San Miguel", "Santiago", "Sibundoy", "Valle Del Guamuez", "Villagarzón"
        };
        final String[] municipiosQuindio = new String[]{
                "Armenia", "Buenavista", "Calarcá", "Circasia", "Córdoba", "Filandia", "Génova", "La Tebaida", "Montenegro",
                "Pijao", "Quimbaya", "Salento"
        };
        final String[] municipiosRisaralda = new String[]{
                "Apía", "Balboa", "Belén De Umbría", "Dosquebradas", "Guática", "La Celia", "La Virginia", "Marsella", "Mistrató",
                "Pereira", "Pueblo Rico", "Quinchía", "Santa Rosa De Cabal", "Santuario"
        };
        final String[] municipiosSanAndres = new String[]{
                "San Andrés", "Providencia y Santa Catalina"
        };
        final String[] municipiosSantander = new String[]{
                "Aguada", "Albania", "Aratoca", "Barbosa", "Barichara", "Barrancabermeja", "Betulia", "Bolívar", "Bucaramanga",
                "Cabrera", "California", "Capitanejo", "Carcasí", "Cepitá", "Cerrito", "Charalá", "Charta", "Chima", "Chipatá",
                "Cimitarra", "Concepción", "Confines", "Contratación", "Coromoro", "Curití", "El Carmen De Chucurí",
                "El Guacamayo", "El Peñón", "El Playón", "Encino", "Enciso", "Florián", "Floridablanca", "Galán", "Gámbita",
                "Girón", "Guaca", "Guadalupe", "Guapotá", "Guavatá", "Güepsa", "Hato", "Jesús María", "Jordán", "La Belleza",
                "La Paz", "Landázuri", "Lebríja", "Los Santos", "Macaravita", "Málaga", "Matanza", "Mogotes", "Molagavita",
                "Ocamonte", "Oiba", "Onzaga", "Palmar", "Palmas Del Socorro", "Páramo", "Piedecuesta", "Pinchote",
                "Puente Nacional", "Puerto Parra", "Puerto Wilches", "Rionegro", "Sabana De Torres", "San Andrés", "San Benito",
                "San Gil", "San Joaquín", "San José De Miranda", "San Miguel", "San Vicente De Chucurí", "Santa Bárbara",
                "Santa Helena Del Opón", "Simacota", "Socorro", "Suaita", "Sucre", "Suratá", "Tona", "Valle De San José",
                "Vélez", "Vetas", "Villanueva", "Zapatoca"
        };
        final String[] municipiosSucre = new String[]{
                "Buenavista", "Caimito", "Chalán", "Colosó", "Corozal", "Coveñas", "El Roble", "Galeras", "Guaranda", "La Unión",
                "Los Palmitos", "Majagual", "Morroa", "Ovejas", "Palmito", "Sampués", "San Benito Abad", "San Juan De Betulia",
                "San Marcos", "San Onofre", "San Pedro", "Santiago De Tolú", "Sincé", "Sincelejo", "Sucre", "Tolú Viejo"
        };
        final String[] municipiosTolima = new String[]{
                "Alpujarra", "Alvarado", "Ambalema", "Anzoátegui", "Armero Guayabal", "Ataco", "Cajamarca", "Carmen De Apicalá",
                "Casabianca", "Chaparral", "Coello", "Coyaima", "Cunday", "Dolores", "Espinal", "Falan", "Flandes", "Fresno",
                "Guamo", "Herveo", "Honda", "Ibagué", "Icononzo", "Lérida", "Líbano", "Mariquita", "Melgar", "Murillo",
                "Natagaima", "Ortega", "Palocabildo", "Piedras", "Planadas", "Prado", "Purificación", "Rioblanco", "Roncesvalles",
                "Rovira", "Saldaña", "San Antonio", "San Luis", "Santa Isabel", "Suárez", "Valle De San Juan", "Venadillo",
                "Villahermosa", "Villarrica"
        };
        final String[] municipiosValle = new String[]{
                "Alcalá", "Andalucía", "Ansermanuevo", "Argelia", "Bolívar", "Buenaventura", "Bugalagrande", "Caicedonia", "Cali",
                "Calima el Darién", "Candelaria", "Cartago", "Dagua", "El Águila", "El Cairo", "El Cerrito", "El Dovio",
                "Florida", "Ginebra", "Guacarí", "Guadalajara De Buga", "Jamundí", "La Cumbre", "La Unión", "La Victoria",
                "Obando", "Palmira", "Pradera", "Restrepo", "Riofrío", "Roldanillo", "San Pedro", "Sevilla", "Toro", "Trujillo",
                "Tuluá", "Ulloa", "Versalles", "Vijes", "Yotoco", "Yumbo", "Zarzal"
        };
        final String[] municipiosVaupes = new String[]{
                "Taruru", "Mitú", "Taraira"
        };
        final String[] municipiosVichada = new String[]{
                "Cumaribo", "La Primavera", "Puerto Carreño", "Santa Rosalía"
        };
        String[] lugarVenta = new String[]{
                "Venta en la unidad de producción", "Centro de acopio", "Centro de comercialización", "Otro, ¿Cuál?"
        };
        String[] compradores = new String[] {
                "Intermediario", "Almacén de cadena", "Consumidor", "Tienda", "Restaurante", "Industria", "Exportador"
        };

        Spinner ans18 = (Spinner) findViewById(R.id.ans18);
        ArrayAdapter<String> adapterAns18 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipoCultivo);
        adapterAns18.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans18.setAdapter(adapterAns18);

        Spinner ans19 = (Spinner) findViewById(R.id.ans19);
        ArrayAdapter<String> adapterAns19 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipoActividad);
        adapterAns19.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans19.setAdapter(adapterAns19);

        Spinner ans23 = (Spinner) findViewById(R.id.ans23);
        ArrayAdapter<String> adapterAns23 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fuenteAgua);
        adapterAns23.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans23.setAdapter(adapterAns23);

        Spinner ans24 = (Spinner) findViewById(R.id.ans24);
        ArrayAdapter<String> adapterAns24 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, transporteAgua);
        adapterAns24.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans24.setAdapter(adapterAns24);

        Spinner ans26 = (Spinner) findViewById(R.id.ans26);
        ArrayAdapter<String> adapterAns26 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns26.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans26.setAdapter(adapterAns26);

        Spinner ans27 = (Spinner) findViewById(R.id.ans27);
        ArrayAdapter<String> adapterAns27 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns27.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans27.setAdapter(adapterAns27);

        Spinner ans28 = (Spinner) findViewById(R.id.ans28);
        ArrayAdapter<String> adapterAns28 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, destinoProd);
        adapterAns28.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans28.setAdapter(adapterAns28);

        Spinner ans30 = (Spinner) findViewById(R.id.ans30);
        ArrayAdapter<String> adapterAns30 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unidadVenta);
        adapterAns30.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans30.setAdapter(adapterAns30);

        Spinner ans32 = (Spinner) findViewById(R.id.ans32);
        ArrayAdapter<String> adapterAns32 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns32.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans32.setAdapter(adapterAns32);

        Spinner ans33 = (Spinner) findViewById(R.id.ans33);
        ArrayAdapter<String> adapterAns33 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns33.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans33.setAdapter(adapterAns33);

        Spinner ans33_1 = (Spinner) findViewById(R.id.ans33_1);
        ArrayAdapter<String> adapterAns33_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, noAdopcionTecnologia);
        adapterAns33_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans33_1.setAdapter(adapterAns33_1);

        Spinner ans33_3a = (Spinner) findViewById(R.id.ans33_3a);
        ArrayAdapter<String> adapterAns33_3a = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, porcentajeAdopcionTecnologia);
        adapterAns33_3a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans33_3a.setAdapter(adapterAns33_3a);
        ans33_3a.setVisibility(View.GONE);

        Spinner ans33_3d = (Spinner) findViewById(R.id.ans33_3d);
        ArrayAdapter<String> adapterAns33_3d = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, porcentajeAdopcionTecnologia);
        adapterAns33_3d.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans33_3d.setAdapter(adapterAns33_3d);
        ans33_3d.setVisibility(View.GONE);

        Spinner ans33_3g = (Spinner) findViewById(R.id.ans33_3g);
        ArrayAdapter<String> adapterAns33_3g = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, porcentajeAdopcionTecnologia);
        adapterAns33_3g.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans33_3g.setAdapter(adapterAns33_3g);
        ans33_3g.setVisibility(View.GONE);

        Spinner ans33_3h = (Spinner) findViewById(R.id.ans33_3h);
        ArrayAdapter<String> adapterAns33_3h = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, porcentajeAdopcionTecnologia);
        adapterAns33_3h.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans33_3h.setAdapter(adapterAns33_3h);
        ans33_3h.setVisibility(View.GONE);

        Spinner ans33_5 = (Spinner) findViewById(R.id.ans33_5);
        ArrayAdapter<String> adapterAns33_5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNoNA);
        adapterAns33_5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans33_5.setAdapter(adapterAns33_5);

        Spinner ans33_6 = (Spinner) findViewById(R.id.ans33_6);
        ArrayAdapter<String> adapterAns33_6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, indicadoresMejora);
        adapterAns33_6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans33_6.setAdapter(adapterAns33_6);

        Spinner ans34_1 = (Spinner) findViewById(R.id.ans34_1);
        ArrayAdapter<String> adapterAns34_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, perdidas);
        adapterAns34_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans34_1.setAdapter(adapterAns34_1);

        Spinner ans35_1 = (Spinner) findViewById(R.id.ans35_1);
        ArrayAdapter<String> adapterAns35_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, perdidas);
        adapterAns35_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans35_1.setAdapter(adapterAns35_1);

        Spinner ans36 = (Spinner) findViewById(R.id.ans36);
        ArrayAdapter<String> adapterAns36 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fuenteEnergia);
        adapterAns36.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans36.setAdapter(adapterAns36);

        Spinner ans37 = (Spinner) findViewById(R.id.ans37);
        ArrayAdapter<String> adapterAns37 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns37.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans37.setAdapter(adapterAns37);

        Spinner ans37_1 = (Spinner) findViewById(R.id.ans37_1);
        ArrayAdapter<String> adapterAns37_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipoAsociacion);
        adapterAns37_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans37_1.setAdapter(adapterAns37_1);

        Spinner ans37_6 = (Spinner) findViewById(R.id.ans37_6);
        ArrayAdapter<String> adapterAns37_6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, departamentos);
        adapterAns37_6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans37_6.setAdapter(adapterAns37_6);

        Spinner ans37_7 = (Spinner) findViewById(R.id.ans37_7);
        ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, municipiosAmazonas);
        adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans37_7.setAdapter(adapterAns37_7);

        Spinner ans38 = (Spinner) findViewById(R.id.ans38);
        ArrayAdapter<String> adapterAns38 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lugarVenta);
        adapterAns38.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans38.setAdapter(adapterAns38);

        Spinner ans39 = (Spinner) findViewById(R.id.ans39);
        ArrayAdapter<String> adapterAns39 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, compradores);
        adapterAns39.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans39.setAdapter(adapterAns39);

        Spinner ans40 = (Spinner) findViewById(R.id.ans40);
        ArrayAdapter<String> adapterAns40 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns40.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans40.setAdapter(adapterAns40);

        EditText ans21other = (EditText) findViewById(R.id.ans21other);
        ans21other.setVisibility(View.GONE);

        EditText ans22a = (EditText) findViewById(R.id.ans22a);
        ans22a.setVisibility(View.GONE);

        EditText ans22b = (EditText) findViewById(R.id.ans22b);
        ans22b.setVisibility(View.GONE);

        EditText ans22c = (EditText) findViewById(R.id.ans22c);
        ans22c.setVisibility(View.GONE);

        EditText ans22d = (EditText) findViewById(R.id.ans22d);
        ans22d.setVisibility(View.GONE);

        EditText ans22e = (EditText) findViewById(R.id.ans22e);
        ans22e.setVisibility(View.GONE);

        EditText ans23other = (EditText) findViewById(R.id.ans23other);
        ans23other.setVisibility(View.GONE);

        EditText ans24other = (EditText) findViewById(R.id.ans24other);
        ans24other.setVisibility(View.GONE);

        EditText ans30other = (EditText) findViewById(R.id.ans30other);
        ans30other.setVisibility(View.GONE);

        EditText ans32_1other = (EditText) findViewById(R.id.ans32_1other);
        ans32_1other.setVisibility(View.GONE);

        EditText ans33_1other = (EditText) findViewById(R.id.ans33_1other);
        ans33_1other.setVisibility(View.GONE);

        EditText ans33_2other = (EditText) findViewById(R.id.ans33_2other);
        ans33_2other.setVisibility(View.GONE);

        EditText ans34other = (EditText) findViewById(R.id.ans34other);
        ans34other.setVisibility(View.GONE);

        EditText ans35other = (EditText) findViewById(R.id.ans35other);
        ans35other.setVisibility(View.GONE);

        EditText ans36other = (EditText) findViewById(R.id.ans36other);
        ans36other.setVisibility(View.GONE);

        EditText ans37_1other = (EditText) findViewById(R.id.ans37_1other);
        ans37_1other.setVisibility(View.GONE);

        EditText ans38other = (EditText) findViewById(R.id.ans38other);
        ans38other.setVisibility(View.GONE);

        TextView textFertirriego = (TextView) findViewById(R.id.textFertirriego);
        textFertirriego.setVisibility(View.GONE);
        TextView textEstacionM = (TextView) findViewById(R.id.textEstacionM);
        textEstacionM.setVisibility(View.GONE);
        TextView textTanques = (TextView) findViewById(R.id.textTanques);
        textTanques.setVisibility(View.GONE);
        TextView textMacrotuneles = (TextView) findViewById(R.id.textMacrotuneles);
        textMacrotuneles.setVisibility(View.GONE);
        TextView textProcesosAgro = (TextView) findViewById(R.id.textProcesosAgro);
        textProcesosAgro.setVisibility(View.GONE);
        TextView textAlmacenamiento = (TextView) findViewById(R.id.textAlmacenamiento);
        textAlmacenamiento.setVisibility(View.GONE);
        TextView textRiego = (TextView) findViewById(R.id.textRiego);
        textRiego.setVisibility(View.GONE);
        TextView textOtraTecnologia = (TextView) findViewById(R.id.textOtraTecnologia);
        textOtraTecnologia.setVisibility(View.GONE);

        CheckBox ans21a = (CheckBox) findViewById(R.id.ans21a);
        CheckBox ans21b = (CheckBox) findViewById(R.id.ans21b);
        CheckBox ans21c = (CheckBox) findViewById(R.id.ans21c);
        CheckBox ans21d = (CheckBox) findViewById(R.id.ans21d);
        CheckBox ans21e = (CheckBox) findViewById(R.id.ans21e);
        CheckBox ans32_1a = (CheckBox) findViewById(R.id.ans32_1a);
        CheckBox ans32_1b = (CheckBox) findViewById(R.id.ans32_1b);
        CheckBox ans32_1c = (CheckBox) findViewById(R.id.ans32_1c);
        CheckBox ans32_1d = (CheckBox) findViewById(R.id.ans32_1d);
        CheckBox ans32_1e = (CheckBox) findViewById(R.id.ans32_1e);
        CheckBox ans32_1f = (CheckBox) findViewById(R.id.ans32_1f);
        CheckBox ans32_1g = (CheckBox) findViewById(R.id.ans32_1g);
        CheckBox ans33_2a = (CheckBox) findViewById(R.id.ans33_2a);
        CheckBox ans33_2b = (CheckBox) findViewById(R.id.ans33_2b);
        CheckBox ans33_2c = (CheckBox) findViewById(R.id.ans33_2c);
        CheckBox ans33_2d = (CheckBox) findViewById(R.id.ans33_2d);
        CheckBox ans33_2e = (CheckBox) findViewById(R.id.ans33_2e);
        CheckBox ans33_2f = (CheckBox) findViewById(R.id.ans33_2f);
        CheckBox ans33_2g = (CheckBox) findViewById(R.id.ans33_2g);
        CheckBox ans33_2h = (CheckBox) findViewById(R.id.ans33_2h);
        CheckBox ans33_2i = (CheckBox) findViewById(R.id.ans33_2i);
        CheckBox ans33_3b_1 = (CheckBox) findViewById(R.id.ans33_3b_1);
        ans33_3b_1.setVisibility(View.GONE);
        CheckBox ans33_3b_2 = (CheckBox) findViewById(R.id.ans33_3b_2);
        ans33_3b_2.setVisibility(View.GONE);
        CheckBox ans33_3b_3 = (CheckBox) findViewById(R.id.ans33_3b_3);
        ans33_3b_3.setVisibility(View.GONE);
        CheckBox ans33_3b_4 = (CheckBox) findViewById(R.id.ans33_3b_4);
        ans33_3b_4.setVisibility(View.GONE);
        CheckBox ans33_3b_5 = (CheckBox) findViewById(R.id.ans33_3b_5);
        ans33_3b_5.setVisibility(View.GONE);
        CheckBox ans33_3b_6 = (CheckBox) findViewById(R.id.ans33_3b_6);
        ans33_3b_6.setVisibility(View.GONE);
        CheckBox ans33_3b_7 = (CheckBox) findViewById(R.id.ans33_3b_7);
        ans33_3b_7.setVisibility(View.GONE);
        CheckBox ans33_3b_8 = (CheckBox) findViewById(R.id.ans33_3b_8);
        ans33_3b_8.setVisibility(View.GONE);
        CheckBox ans33_3b_9 = (CheckBox) findViewById(R.id.ans33_3b_9);
        ans33_3b_9.setVisibility(View.GONE);
        CheckBox ans33_3c = (CheckBox) findViewById(R.id.ans33_3c);
        ans33_3c.setVisibility(View.GONE);
        CheckBox ans33_3e_1 = (CheckBox) findViewById(R.id.ans33_3e_1);
        ans33_3e_1.setVisibility(View.GONE);
        CheckBox ans33_3e_2 = (CheckBox) findViewById(R.id.ans33_3e_2);
        ans33_3e_2.setVisibility(View.GONE);
        CheckBox ans33_3e_3 = (CheckBox) findViewById(R.id.ans33_3e_3);
        ans33_3e_3.setVisibility(View.GONE);
        CheckBox ans33_3e_4 = (CheckBox) findViewById(R.id.ans33_3e_4);
        ans33_3e_4.setVisibility(View.GONE);
        CheckBox ans33_3e_5 = (CheckBox) findViewById(R.id.ans33_3e_5);
        ans33_3e_5.setVisibility(View.GONE);
        CheckBox ans33_3e_6 = (CheckBox) findViewById(R.id.ans33_3e_6);
        ans33_3e_6.setVisibility(View.GONE);
        CheckBox ans33_3f_1 = (CheckBox) findViewById(R.id.ans33_3f_1);
        ans33_3f_1.setVisibility(View.GONE);
        CheckBox ans33_3f_2 = (CheckBox) findViewById(R.id.ans33_3f_2);
        ans33_3f_2.setVisibility(View.GONE);
        CheckBox ans33_3f_3 = (CheckBox) findViewById(R.id.ans33_3f_3);
        ans33_3f_3.setVisibility(View.GONE);
        CheckBox ans33_3f_4 = (CheckBox) findViewById(R.id.ans33_3f_4);
        ans33_3f_4.setVisibility(View.GONE);
        CheckBox ans33_3f_5 = (CheckBox) findViewById(R.id.ans33_3f_5);
        ans33_3f_5.setVisibility(View.GONE);
        CheckBox ans34a = (CheckBox) findViewById(R.id.ans34a);
        CheckBox ans34b = (CheckBox) findViewById(R.id.ans34b);
        CheckBox ans34c = (CheckBox) findViewById(R.id.ans34c);
        CheckBox ans34d = (CheckBox) findViewById(R.id.ans34d);
        CheckBox ans34e = (CheckBox) findViewById(R.id.ans34e);
        CheckBox ans34f = (CheckBox) findViewById(R.id.ans34f);
        CheckBox ans35a = (CheckBox) findViewById(R.id.ans35a);
        CheckBox ans35b = (CheckBox) findViewById(R.id.ans35b);
        CheckBox ans35c = (CheckBox) findViewById(R.id.ans35c);
        CheckBox ans35d = (CheckBox) findViewById(R.id.ans35d);
        CheckBox ans35e = (CheckBox) findViewById(R.id.ans35e);
        CheckBox ans35f = (CheckBox) findViewById(R.id.ans35f);
        CheckBox ans35g = (CheckBox) findViewById(R.id.ans35g);

        ans21a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans21a = (CheckBox) findViewById(R.id.ans21a);
                EditText ans22a = (EditText) findViewById(R.id.ans22a);

                if(ans21a.isChecked()){
                    ans21code += 1;
                    ans22a.setVisibility(View.VISIBLE);
                }
                else {
                    ans21code -= 1;
                    ans22a.setVisibility(View.GONE);
                }
            }
        });

        ans21b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans21b = (CheckBox) findViewById(R.id.ans21b);
                EditText ans22b = (EditText) findViewById(R.id.ans22b);
                if(ans21b.isChecked()){
                    ans21code += 2;
                    ans22b.setVisibility(View.VISIBLE);
                }
                else {
                    ans21code -= 2;
                    ans22b.setVisibility(View.GONE);
                }
            }
        });

        ans21c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans21c = (CheckBox) findViewById(R.id.ans21c);
                EditText ans22c = (EditText) findViewById(R.id.ans22c);
                if(ans21c.isChecked()){
                    ans21code += 4;
                    ans22c.setVisibility(View.VISIBLE);
                }
                else {
                    ans21code -= 4;
                    ans22c.setVisibility(View.GONE);
                }
            }
        });

        ans21d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans21d = (CheckBox) findViewById(R.id.ans21d);
                EditText ans22d = (EditText) findViewById(R.id.ans22d);
                if(ans21d.isChecked()){
                    ans21code += 8;
                    ans22d.setVisibility(View.VISIBLE);
                }
                else {
                    ans21code -= 8;
                    ans22d.setVisibility(View.GONE);
                }
            }
        });

        ans21e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans21e = (CheckBox) findViewById(R.id.ans21e);
                EditText ans21other = (EditText) findViewById(R.id.ans21other);
                EditText ans22e = (EditText) findViewById(R.id.ans22e);
                if(ans21e.isChecked()){
                    ans21code += 16;
                    ans21other.setVisibility(View.VISIBLE);
                    ans22e.setVisibility(View.VISIBLE);
                }
                else {
                    ans21code -= 16;
                    ans21other.setVisibility(View.GONE);
                    ans22e.setVisibility(View.GONE);

                }
            }
        });

        ans23.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans23 = (Spinner) findViewById(R.id.ans23);
                EditText ans23other = (EditText) findViewById(R.id.ans23other);
                if(ans23.getSelectedItemPosition()==8) {
                    ans23other.setVisibility(View.VISIBLE);
                }else{
                    ans23other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans24.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans24 = (Spinner) findViewById(R.id.ans24);
                EditText ans24other = (EditText) findViewById(R.id.ans24other);
                if(ans24.getSelectedItemPosition()==3) {
                    ans24other.setVisibility(View.VISIBLE);
                }else{
                    ans24other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans27.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans27 = (Spinner) findViewById(R.id.ans27);
                TextView ques27_1 = (TextView) findViewById(R.id.ques27_1);
                EditText ans27_1 = (EditText) findViewById(R.id.ans27_1);
                if(ans27.getSelectedItemPosition()==1) {
                    ques27_1.setVisibility(View.GONE);
                    ans27_1.setVisibility(View.GONE);
                }else{
                    ques27_1.setVisibility(View.VISIBLE);
                    ans27_1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans28.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans28 = (Spinner) findViewById(R.id.ans28);
                TextView ques28_1 = (TextView) findViewById(R.id.ques28_1);
                EditText ans28_1 = (EditText) findViewById(R.id.ans28_1);
                TextView ques28_2 = (TextView) findViewById(R.id.ques28_2);
                EditText ans28_2 = (EditText) findViewById(R.id.ans28_2);
                if(ans28.getSelectedItemPosition()==2) {
                    ques28_1.setVisibility(View.VISIBLE);
                    ans28_1.setVisibility(View.VISIBLE);
                    ques28_2.setVisibility(View.VISIBLE);
                    ans28_2.setVisibility(View.VISIBLE);
                }else{
                    ques28_1.setVisibility(View.GONE);
                    ans28_1.setVisibility(View.GONE);
                    ques28_2.setVisibility(View.GONE);
                    ans28_2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans30.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans30 = (Spinner) findViewById(R.id.ans30);
                EditText ans30other = (EditText) findViewById(R.id.ans30other);
                if(ans30.getSelectedItemPosition()==3) {
                    ans30other.setVisibility(View.VISIBLE);
                }else{
                    ans30other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans32.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans32 = (Spinner) findViewById(R.id.ans32);
                TextView ques32_1 = (TextView) findViewById(R.id.ques32_1);
                CheckBox ans32_1a = (CheckBox) findViewById(R.id.ans32_1a);
                CheckBox ans32_1b = (CheckBox) findViewById(R.id.ans32_1b);
                CheckBox ans32_1c = (CheckBox) findViewById(R.id.ans32_1c);
                CheckBox ans32_1d = (CheckBox) findViewById(R.id.ans32_1d);
                CheckBox ans32_1e = (CheckBox) findViewById(R.id.ans32_1e);
                CheckBox ans32_1f = (CheckBox) findViewById(R.id.ans32_1f);
                CheckBox ans32_1g = (CheckBox) findViewById(R.id.ans32_1g);
                TextView ques33 = (TextView) findViewById(R.id.ques33);
                Spinner ans33 = (Spinner) findViewById(R.id.ans33);
                TextView ques33_1 = (TextView) findViewById(R.id.ques33_1);
                Spinner ans33_1 = (Spinner) findViewById(R.id.ans33_1);
                TextView ques33_2 = (TextView) findViewById(R.id.ques33_2);
                CheckBox ans33_2a = (CheckBox) findViewById(R.id.ans33_2a);
                CheckBox ans33_2b = (CheckBox) findViewById(R.id.ans33_2b);
                CheckBox ans33_2c = (CheckBox) findViewById(R.id.ans33_2c);
                CheckBox ans33_2d = (CheckBox) findViewById(R.id.ans33_2d);
                CheckBox ans33_2e = (CheckBox) findViewById(R.id.ans33_2e);
                CheckBox ans33_2f = (CheckBox) findViewById(R.id.ans33_2f);
                CheckBox ans33_2g = (CheckBox) findViewById(R.id.ans33_2g);
                CheckBox ans33_2h = (CheckBox) findViewById(R.id.ans33_2h);
                CheckBox ans33_2i = (CheckBox) findViewById(R.id.ans33_2i);
                TextView ques33_3 = (TextView) findViewById(R.id.ques33_3);
                TextView ques33_4 = (TextView) findViewById(R.id.ques33_4);
                EditText ans33_4 = (EditText) findViewById(R.id.ans33_4);
                TextView ques33_5 = (TextView) findViewById(R.id.ques33_5);
                Spinner ans33_5 = (Spinner) findViewById(R.id.ans33_5);
                TextView ques33_6 = (TextView) findViewById(R.id.ques33_6);
                Spinner ans33_6 = (Spinner) findViewById(R.id.ans33_6);
                if(ans32.getSelectedItemPosition()==0) {
                    ques32_1.setVisibility(View.VISIBLE);
                    ans32_1a.setVisibility(View.VISIBLE);
                    ans32_1b.setVisibility(View.VISIBLE);
                    ans32_1c.setVisibility(View.VISIBLE);
                    ans32_1d.setVisibility(View.VISIBLE);
                    ans32_1e.setVisibility(View.VISIBLE);
                    ans32_1f.setVisibility(View.VISIBLE);
                    ans32_1g.setVisibility(View.VISIBLE);
                    ques33.setVisibility(View.VISIBLE);
                    ans33.setVisibility(View.VISIBLE);
                    ques33_1.setVisibility(View.VISIBLE);
                    ans33_1.setVisibility(View.VISIBLE);
                    ques33_2.setVisibility(View.VISIBLE);
                    ans33_2a.setVisibility(View.VISIBLE);
                    ans33_2b.setVisibility(View.VISIBLE);
                    ans33_2c.setVisibility(View.VISIBLE);
                    ans33_2d.setVisibility(View.VISIBLE);
                    ans33_2e.setVisibility(View.VISIBLE);
                    ans33_2f.setVisibility(View.VISIBLE);
                    ans33_2g.setVisibility(View.VISIBLE);
                    ans33_2h.setVisibility(View.VISIBLE);
                    ans33_2i.setVisibility(View.VISIBLE);
                    ques33_3.setVisibility(View.VISIBLE);
                    ques33_4.setVisibility(View.VISIBLE);
                    ans33_4.setVisibility(View.VISIBLE);
                    ques33_5.setVisibility(View.VISIBLE);
                    ans33_5.setVisibility(View.VISIBLE);
                    ques33_6.setVisibility(View.VISIBLE);
                    ans33_6.setVisibility(View.VISIBLE);
                }else{
                    ques32_1.setVisibility(View.GONE);
                    ans32_1a.setVisibility(View.GONE);
                    ans32_1b.setVisibility(View.GONE);
                    ans32_1c.setVisibility(View.GONE);
                    ans32_1d.setVisibility(View.GONE);
                    ans32_1e.setVisibility(View.GONE);
                    ans32_1f.setVisibility(View.GONE);
                    ans32_1g.setVisibility(View.GONE);
                    ques33.setVisibility(View.GONE);
                    ans33.setVisibility(View.GONE);
                    ques33_1.setVisibility(View.GONE);
                    ans33_1.setVisibility(View.GONE);
                    ques33_2.setVisibility(View.GONE);
                    ans33_2a.setVisibility(View.GONE);
                    ans33_2b.setVisibility(View.GONE);
                    ans33_2c.setVisibility(View.GONE);
                    ans33_2d.setVisibility(View.GONE);
                    ans33_2e.setVisibility(View.GONE);
                    ans33_2f.setVisibility(View.GONE);
                    ans33_2g.setVisibility(View.GONE);
                    ans33_2h.setVisibility(View.GONE);
                    ans33_2i.setVisibility(View.GONE);
                    ques33_3.setVisibility(View.GONE);
                    ques33_4.setVisibility(View.GONE);
                    ans33_4.setVisibility(View.GONE);
                    ques33_5.setVisibility(View.GONE);
                    ans33_5.setVisibility(View.GONE);
                    ques33_6.setVisibility(View.GONE);
                    ans33_6.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans32_1a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans32_1a = (CheckBox) findViewById(R.id.ans32_1a);
                CheckBox ans32_1b = (CheckBox) findViewById(R.id.ans32_1b);
                CheckBox ans32_1c = (CheckBox) findViewById(R.id.ans32_1c);
                CheckBox ans32_1d = (CheckBox) findViewById(R.id.ans32_1d);
                CheckBox ans32_1e = (CheckBox) findViewById(R.id.ans32_1e);
                CheckBox ans32_1f = (CheckBox) findViewById(R.id.ans32_1f);
                CheckBox ans32_1g = (CheckBox) findViewById(R.id.ans32_1g);
                if(ans32_1a.isChecked()){
                    ans32_1code += 1;
                    ans32_1b.setChecked(false);
                    ans32_1c.setChecked(false);
                    ans32_1d.setChecked(false);
                    ans32_1e.setChecked(false);
                    ans32_1f.setChecked(false);
                    ans32_1g.setChecked(false);
                }
                else {
                    ans32_1code -= 1;
                }
            }
        });

        ans32_1b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans32_1a = (CheckBox) findViewById(R.id.ans32_1a);
                CheckBox ans32_1b = (CheckBox) findViewById(R.id.ans32_1b);
                if(ans32_1a.isChecked()){
                    ans32_1b.setChecked(false);
                }
                if(ans32_1b.isChecked()){
                    ans32_1code += 2;
                }
                else {
                    ans32_1code -= 2;
                }
            }
        });

        ans32_1c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans32_1a = (CheckBox) findViewById(R.id.ans32_1a);
                CheckBox ans32_1c = (CheckBox) findViewById(R.id.ans32_1c);
                if(ans32_1a.isChecked()){
                    ans32_1c.setChecked(false);
                }
                if(ans32_1c.isChecked()){
                    ans32_1code += 4;
                }
                else {
                    ans32_1code -= 4;
                }
            }
        });

        ans32_1d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans32_1a = (CheckBox) findViewById(R.id.ans32_1a);
                CheckBox ans32_1d = (CheckBox) findViewById(R.id.ans32_1d);
                if(ans32_1a.isChecked()){
                    ans32_1d.setChecked(false);
                }
                if(ans32_1d.isChecked()){
                    ans32_1code += 8;
                }
                else {
                    ans32_1code -= 8;
                }
            }
        });

        ans32_1e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans32_1a = (CheckBox) findViewById(R.id.ans32_1a);
                CheckBox ans32_1e = (CheckBox) findViewById(R.id.ans32_1e);
                if(ans32_1a.isChecked()){
                    ans32_1e.setChecked(false);
                }
                if(ans32_1e.isChecked()){
                    ans32_1code += 16;
                }
                else {
                    ans32_1code -= 16;
                }
            }
        });

        ans32_1f.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans32_1a = (CheckBox) findViewById(R.id.ans32_1a);
                CheckBox ans32_1f = (CheckBox) findViewById(R.id.ans32_1f);
                if(ans32_1a.isChecked()){
                    ans32_1f.setChecked(false);
                }
                if(ans32_1f.isChecked()){
                    ans32_1code += 32;
                }
                else {
                    ans32_1code -= 32;
                }
            }
        });

        ans32_1g.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans32_1a = (CheckBox) findViewById(R.id.ans32_1a);
                CheckBox ans32_1g = (CheckBox) findViewById(R.id.ans32_1g);
                EditText ans32_1other = (EditText) findViewById(R.id.ans32_1other);
                if(ans32_1a.isChecked()){
                    ans32_1g.setChecked(false);
                }
                if(ans32_1g.isChecked()){
                    ans32_1other.setVisibility(View.VISIBLE);
                    ans32_1code += 64;
                }
                else {
                    ans32_1other.setVisibility(View.GONE);
                    ans32_1code -= 64;
                }
            }
        });

        ans33.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans33 = (Spinner) findViewById(R.id.ans33);
                TextView ques33_1 = (TextView) findViewById(R.id.ques33_1);
                Spinner ans33_1 = (Spinner) findViewById(R.id.ans33_1);
                TextView ques33_2 = (TextView) findViewById(R.id.ques33_2);
                CheckBox ans33_2a = (CheckBox) findViewById(R.id.ans33_2a);
                CheckBox ans33_2b = (CheckBox) findViewById(R.id.ans33_2b);
                CheckBox ans33_2c = (CheckBox) findViewById(R.id.ans33_2c);
                CheckBox ans33_2d = (CheckBox) findViewById(R.id.ans33_2d);
                CheckBox ans33_2e = (CheckBox) findViewById(R.id.ans33_2e);
                CheckBox ans33_2f = (CheckBox) findViewById(R.id.ans33_2f);
                CheckBox ans33_2g = (CheckBox) findViewById(R.id.ans33_2g);
                CheckBox ans33_2h = (CheckBox) findViewById(R.id.ans33_2h);
                CheckBox ans33_2i = (CheckBox) findViewById(R.id.ans33_2i);
                TextView ques33_3 = (TextView) findViewById(R.id.ques33_3);
                TextView ques33_4 = (TextView) findViewById(R.id.ques33_4);
                EditText ans33_4 = (EditText) findViewById(R.id.ans33_4);
                TextView ques33_5 = (TextView) findViewById(R.id.ques33_5);
                Spinner ans33_5 = (Spinner) findViewById(R.id.ans33_5);
                TextView ques33_6 = (TextView) findViewById(R.id.ques33_6);
                Spinner ans33_6 = (Spinner) findViewById(R.id.ans33_6);
                if(ans33.getSelectedItemPosition()==1) {
                    ques33_1.setVisibility(View.VISIBLE);
                    ans33_1.setVisibility(View.VISIBLE);
                    ques33_2.setVisibility(View.GONE);
                    ans33_2a.setVisibility(View.GONE);
                    ans33_2b.setVisibility(View.GONE);
                    ans33_2c.setVisibility(View.GONE);
                    ans33_2d.setVisibility(View.GONE);
                    ans33_2e.setVisibility(View.GONE);
                    ans33_2f.setVisibility(View.GONE);
                    ans33_2g.setVisibility(View.GONE);
                    ans33_2h.setVisibility(View.GONE);
                    ans33_2i.setVisibility(View.GONE);
                    ques33_3.setVisibility(View.GONE);
                    ques33_4.setVisibility(View.GONE);
                    ans33_4.setVisibility(View.GONE);
                    ques33_5.setVisibility(View.GONE);
                    ans33_5.setVisibility(View.GONE);
                    ques33_6.setVisibility(View.GONE);
                    ans33_6.setVisibility(View.GONE);
                }else{
                    ques33_1.setVisibility(View.GONE);
                    ans33_1.setVisibility(View.GONE);
                    ques33_2.setVisibility(View.VISIBLE);
                    ans33_2a.setVisibility(View.VISIBLE);
                    ans33_2b.setVisibility(View.VISIBLE);
                    ans33_2c.setVisibility(View.VISIBLE);
                    ans33_2d.setVisibility(View.VISIBLE);
                    ans33_2e.setVisibility(View.VISIBLE);
                    ans33_2f.setVisibility(View.VISIBLE);
                    ans33_2g.setVisibility(View.VISIBLE);
                    ans33_2h.setVisibility(View.VISIBLE);
                    ans33_2i.setVisibility(View.VISIBLE);
                    ques33_3.setVisibility(View.VISIBLE);
                    ques33_4.setVisibility(View.VISIBLE);
                    ans33_4.setVisibility(View.VISIBLE);
                    ques33_5.setVisibility(View.VISIBLE);
                    ans33_5.setVisibility(View.VISIBLE);
                    ques33_6.setVisibility(View.VISIBLE);
                    ans33_6.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans33_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans33_1 = (Spinner) findViewById(R.id.ans33_1);
                EditText ans33_1other = (EditText) findViewById(R.id.ans33_1other);
                if(ans33_1.getSelectedItemPosition()==4) {
                    ans33_1other.setVisibility(View.VISIBLE);
                }else{
                    ans33_1other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans33_2a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_2a = (CheckBox) findViewById(R.id.ans33_2a);
                CheckBox ans33_2b = (CheckBox) findViewById(R.id.ans33_2b);
                CheckBox ans33_2c = (CheckBox) findViewById(R.id.ans33_2c);
                CheckBox ans33_2d = (CheckBox) findViewById(R.id.ans33_2d);
                CheckBox ans33_2e = (CheckBox) findViewById(R.id.ans33_2e);
                CheckBox ans33_2f = (CheckBox) findViewById(R.id.ans33_2f);
                CheckBox ans33_2g = (CheckBox) findViewById(R.id.ans33_2g);
                CheckBox ans33_2h = (CheckBox) findViewById(R.id.ans33_2h);
                CheckBox ans33_2i = (CheckBox) findViewById(R.id.ans33_2i);
                if(ans33_2a.isChecked()){
                    ans33_2code = 0;
                    ans33_2b.setChecked(false);
                    ans33_2c.setChecked(false);
                    ans33_2d.setChecked(false);
                    ans33_2e.setChecked(false);
                    ans33_2f.setChecked(false);
                    ans33_2g.setChecked(false);
                    ans33_2h.setChecked(false);
                    ans33_2i.setChecked(false);
                }
                else {
                    ans33_2code = 0;
                }
            }
        });

        ans33_2b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_2a = (CheckBox) findViewById(R.id.ans33_2a);
                CheckBox ans33_2b = (CheckBox) findViewById(R.id.ans33_2b);
                TextView text = (TextView) findViewById(R.id.textFertirriego);
                Spinner ans33_3a = (Spinner) findViewById(R.id.ans33_3a);
                if(ans33_2a.isChecked()){
                    ans33_2b.setChecked(false);
                }
                if(ans33_2b.isChecked()){
                    ans33_2code += 1;
                    text.setVisibility(View.VISIBLE);
                    ans33_3a.setVisibility(View.VISIBLE);
                }
                else {
                    ans33_2code -= 1;
                    text.setVisibility(View.GONE);
                    ans33_3a.setVisibility(View.GONE);
                }
            }
        });

        ans33_2c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_2a = (CheckBox) findViewById(R.id.ans33_2a);
                CheckBox ans33_2c = (CheckBox) findViewById(R.id.ans33_2c);
                TextView text = (TextView) findViewById(R.id.textEstacionM);
                CheckBox ans33_3b_1 = (CheckBox) findViewById(R.id.ans33_3b_1);
                CheckBox ans33_3b_2 = (CheckBox) findViewById(R.id.ans33_3b_2);
                CheckBox ans33_3b_3 = (CheckBox) findViewById(R.id.ans33_3b_3);
                CheckBox ans33_3b_4 = (CheckBox) findViewById(R.id.ans33_3b_4);
                CheckBox ans33_3b_5 = (CheckBox) findViewById(R.id.ans33_3b_5);
                CheckBox ans33_3b_6 = (CheckBox) findViewById(R.id.ans33_3b_6);
                CheckBox ans33_3b_7 = (CheckBox) findViewById(R.id.ans33_3b_7);
                CheckBox ans33_3b_8 = (CheckBox) findViewById(R.id.ans33_3b_8);
                CheckBox ans33_3b_9 = (CheckBox) findViewById(R.id.ans33_3b_9);
                if(ans33_2a.isChecked()){
                    ans33_2c.setChecked(false);
                }
                if(ans33_2c.isChecked()){
                    ans33_2code += 2;
                    text.setVisibility(View.VISIBLE);
                    ans33_3b_1.setVisibility(View.VISIBLE);
                    ans33_3b_2.setVisibility(View.VISIBLE);
                    ans33_3b_3.setVisibility(View.VISIBLE);
                    ans33_3b_4.setVisibility(View.VISIBLE);
                    ans33_3b_5.setVisibility(View.VISIBLE);
                    ans33_3b_6.setVisibility(View.VISIBLE);
                    ans33_3b_7.setVisibility(View.VISIBLE);
                    ans33_3b_8.setVisibility(View.VISIBLE);
                    ans33_3b_9.setVisibility(View.VISIBLE);
                }
                else {
                    ans33_2code -= 2;
                    text.setVisibility(View.GONE);
                    ans33_3b_1.setVisibility(View.GONE);
                    ans33_3b_2.setVisibility(View.GONE);
                    ans33_3b_3.setVisibility(View.GONE);
                    ans33_3b_4.setVisibility(View.GONE);
                    ans33_3b_5.setVisibility(View.GONE);
                    ans33_3b_6.setVisibility(View.GONE);
                    ans33_3b_7.setVisibility(View.GONE);
                    ans33_3b_8.setVisibility(View.GONE);
                    ans33_3b_9.setVisibility(View.GONE);
                }
            }
        });

        ans33_2d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_2a = (CheckBox) findViewById(R.id.ans33_2a);
                CheckBox ans33_2d = (CheckBox) findViewById(R.id.ans33_2d);
                TextView text = (TextView) findViewById(R.id.textTanques);
                CheckBox ans33_3c = (CheckBox) findViewById(R.id.ans33_3c);
                if(ans33_2a.isChecked()){
                    ans33_2d.setChecked(false);
                }
                if(ans33_2d.isChecked()){
                    ans33_2code += 4;
                    text.setVisibility(View.VISIBLE);
                    ans33_3c.setVisibility(View.VISIBLE);
                    ans33_3c.setChecked(true);
                }
                else {
                    ans33_2code -= 4;
                    ans33_3c.setChecked(false);
                    text.setVisibility(View.GONE);
                    ans33_3c.setVisibility(View.GONE);
                }
            }
        });

        ans33_2e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_2a = (CheckBox) findViewById(R.id.ans33_2a);
                CheckBox ans33_2e = (CheckBox) findViewById(R.id.ans33_2e);
                TextView text = (TextView) findViewById(R.id.textMacrotuneles);
                Spinner ans33_3d = (Spinner) findViewById(R.id.ans33_3d);
                if(ans33_2a.isChecked()){
                    ans33_2e.setChecked(false);
                }
                if(ans33_2e.isChecked()){
                    ans33_2code += 8;
                    text.setVisibility(View.VISIBLE);
                    ans33_3d.setVisibility(View.VISIBLE);
                }
                else {
                    ans33_2code -= 8;
                    text.setVisibility(View.GONE);
                    ans33_3d.setVisibility(View.GONE);
                }
            }
        });

        ans33_2f.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_2a = (CheckBox) findViewById(R.id.ans33_2a);
                CheckBox ans33_2f = (CheckBox) findViewById(R.id.ans33_2f);
                TextView text = (TextView) findViewById(R.id.textProcesosAgro);
                CheckBox ans33_3e_1 = (CheckBox) findViewById(R.id.ans33_3e_1);
                CheckBox ans33_3e_2 = (CheckBox) findViewById(R.id.ans33_3e_2);
                CheckBox ans33_3e_3 = (CheckBox) findViewById(R.id.ans33_3e_3);
                CheckBox ans33_3e_4 = (CheckBox) findViewById(R.id.ans33_3e_4);
                CheckBox ans33_3e_5 = (CheckBox) findViewById(R.id.ans33_3e_5);
                CheckBox ans33_3e_6 = (CheckBox) findViewById(R.id.ans33_3e_6);
                if(ans33_2a.isChecked()){
                    ans33_2f.setChecked(false);
                }
                if(ans33_2f.isChecked()){
                    ans33_2code += 16;
                    text.setVisibility(View.VISIBLE);
                    ans33_3e_1.setVisibility(View.VISIBLE);
                    ans33_3e_2.setVisibility(View.VISIBLE);
                    ans33_3e_3.setVisibility(View.VISIBLE);
                    ans33_3e_4.setVisibility(View.VISIBLE);
                    ans33_3e_5.setVisibility(View.VISIBLE);
                    ans33_3e_6.setVisibility(View.VISIBLE);
                }
                else {
                    ans33_2code -= 16;
                    text.setVisibility(View.GONE);
                    ans33_3e_1.setVisibility(View.GONE);
                    ans33_3e_2.setVisibility(View.GONE);
                    ans33_3e_3.setVisibility(View.GONE);
                    ans33_3e_4.setVisibility(View.GONE);
                    ans33_3e_5.setVisibility(View.GONE);
                    ans33_3e_6.setVisibility(View.GONE);
                }
            }
        });

        ans33_2g.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_2a = (CheckBox) findViewById(R.id.ans33_2a);
                CheckBox ans33_2g = (CheckBox) findViewById(R.id.ans33_2g);
                TextView text = (TextView) findViewById(R.id.textAlmacenamiento);
                CheckBox ans33_3f_1 = (CheckBox) findViewById(R.id.ans33_3f_1);
                CheckBox ans33_3f_2 = (CheckBox) findViewById(R.id.ans33_3f_2);
                CheckBox ans33_3f_3 = (CheckBox) findViewById(R.id.ans33_3f_3);
                CheckBox ans33_3f_4 = (CheckBox) findViewById(R.id.ans33_3f_4);
                CheckBox ans33_3f_5 = (CheckBox) findViewById(R.id.ans33_3f_5);
                if(ans33_2a.isChecked()){
                    ans33_2g.setChecked(false);
                }
                if(ans33_2g.isChecked()){
                    ans33_2code += 32;
                    text.setVisibility(View.VISIBLE);
                    ans33_3f_1.setVisibility(View.VISIBLE);
                    ans33_3f_2.setVisibility(View.VISIBLE);
                    ans33_3f_3.setVisibility(View.VISIBLE);
                    ans33_3f_4.setVisibility(View.VISIBLE);
                    ans33_3f_5.setVisibility(View.VISIBLE);
                }
                else {
                    ans33_2code -= 32;
                    text.setVisibility(View.GONE);
                    ans33_3f_1.setVisibility(View.GONE);
                    ans33_3f_2.setVisibility(View.GONE);
                    ans33_3f_3.setVisibility(View.GONE);
                    ans33_3f_4.setVisibility(View.GONE);
                    ans33_3f_5.setVisibility(View.GONE);
                }
            }
        });

        ans33_2h.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_2a = (CheckBox) findViewById(R.id.ans33_2a);
                CheckBox ans33_2h = (CheckBox) findViewById(R.id.ans33_2h);
                TextView text = (TextView) findViewById(R.id.textRiego);
                Spinner ans33_3g = (Spinner) findViewById(R.id.ans33_3g);
                if(ans33_2a.isChecked()){
                    ans33_2h.setChecked(false);
                }
                if(ans33_2h.isChecked()){
                    ans33_2code += 64;
                    text.setVisibility(View.VISIBLE);
                    ans33_3g.setVisibility(View.VISIBLE);
                }
                else {
                    ans33_2code -= 64;
                    text.setVisibility(View.GONE);
                    ans33_3g.setVisibility(View.GONE);
                }
            }
        });

        ans33_2i.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_2a = (CheckBox) findViewById(R.id.ans33_2a);
                CheckBox ans33_2i = (CheckBox) findViewById(R.id.ans33_2i);
                EditText ans33_2other = (EditText) findViewById(R.id.ans33_2other);
                TextView text = (TextView) findViewById(R.id.textOtraTecnologia);
                Spinner ans33_3h = (Spinner) findViewById(R.id.ans33_3h);
                if(ans33_2a.isChecked()){
                    ans33_2i.setChecked(false);
                }
                if(ans33_2i.isChecked()){
                    ans33_2code += 128;
                    ans33_2other.setVisibility(View.VISIBLE);
                    text.setVisibility(View.VISIBLE);
                    ans33_3h.setVisibility(View.VISIBLE);
                }
                else {
                    ans33_2code -= 128;
                    ans33_2other.setVisibility(View.GONE);
                    text.setVisibility(View.GONE);
                    ans33_3h.setVisibility(View.GONE);
                }
            }
        });

        ans33_3a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans33_3a = (Spinner) findViewById(R.id.ans33_3a);
                if(ans33_3a.getSelectedItemPosition()==0) {
                    sumaFertirriego = 0;
                }else if (ans33_3a.getSelectedItemPosition()==1){
                    sumaFertirriego = 10;
                }else if (ans33_3a.getSelectedItemPosition()==2){
                    sumaFertirriego = 25;
                }else if (ans33_3a.getSelectedItemPosition()==3){
                    sumaFertirriego = 50;
                }else if (ans33_3a.getSelectedItemPosition()==4){
                    sumaFertirriego = 75;
                }else if (ans33_3a.getSelectedItemPosition()==5){
                    sumaFertirriego = 100;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans33_3b_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3b_1 = (CheckBox) findViewById(R.id.ans33_3b_1);
                if(ans33_3b_1.isChecked()){
                    sumaEstacion += 10;
                }
                else {
                    sumaEstacion -= 10;
                }
            }
        });
        ans33_3b_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3b_2 = (CheckBox) findViewById(R.id.ans33_3b_2);
                if(ans33_3b_2.isChecked()){
                    sumaEstacion += 10;
                }
                else {
                    sumaEstacion -= 10;
                }
            }
        });
        ans33_3b_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3b_3 = (CheckBox) findViewById(R.id.ans33_3b_3);
                if(ans33_3b_3.isChecked()){
                    sumaEstacion += 10;
                }
                else {
                    sumaEstacion -= 10;
                }
            }
        });
        ans33_3b_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3b_4 = (CheckBox) findViewById(R.id.ans33_3b_4);
                if(ans33_3b_4.isChecked()){
                    sumaEstacion += 10;
                }
                else {
                    sumaEstacion -= 10;
                }
            }
        });
        ans33_3b_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3b_5 = (CheckBox) findViewById(R.id.ans33_3b_5);
                if(ans33_3b_5.isChecked()){
                    sumaEstacion += 10;
                }
                else {
                    sumaEstacion -= 10;
                }
            }
        });
        ans33_3b_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3b_6 = (CheckBox) findViewById(R.id.ans33_3b_6);
                if(ans33_3b_6.isChecked()){
                    sumaEstacion += 10;
                }
                else {
                    sumaEstacion -= 10;
                }
            }
        });
        ans33_3b_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3b_7 = (CheckBox) findViewById(R.id.ans33_3b_7);
                if(ans33_3b_7.isChecked()){
                    sumaEstacion += 10;
                }
                else {
                    sumaEstacion -= 10;
                }
            }
        });
        ans33_3b_8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3b_8 = (CheckBox) findViewById(R.id.ans33_3b_8);
                if(ans33_3b_8.isChecked()){
                    sumaEstacion += 20;
                }
                else {
                    sumaEstacion -= 20;
                }
            }
        });
        ans33_3b_9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3b_9 = (CheckBox) findViewById(R.id.ans33_3b_9);
                if(ans33_3b_9.isChecked()){
                    sumaEstacion += 10;
                }
                else {
                    sumaEstacion -= 10;
                }
            }
        });

        ans33_3c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3c = (CheckBox) findViewById(R.id.ans33_3c);
                if(ans33_3c.isChecked()){
                    sumaTanques += 100;
                }
                else {
                    sumaTanques -= 100;
                }
            }
        });

        ans33_3d.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans33_3d = (Spinner) findViewById(R.id.ans33_3d);
                if(ans33_3d.getSelectedItemPosition()==0) {
                    sumaMacrotuneles = 0;
                }else if (ans33_3d.getSelectedItemPosition()==1){
                    sumaMacrotuneles = 10;
                }else if (ans33_3d.getSelectedItemPosition()==2){
                    sumaMacrotuneles = 25;
                }else if (ans33_3d.getSelectedItemPosition()==3){
                    sumaMacrotuneles = 50;
                }else if (ans33_3d.getSelectedItemPosition()==4){
                    sumaMacrotuneles = 75;
                }else if (ans33_3d.getSelectedItemPosition()==5){
                    sumaMacrotuneles = 100;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans33_3e_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3e_1 = (CheckBox) findViewById(R.id.ans33_3e_1);
                if(ans33_3e_1.isChecked()){
                    sumaProcesosAgro += 100;
                }
                else {
                    sumaProcesosAgro -= 100;
                }
            }
        });
        ans33_3e_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3e_2 = (CheckBox) findViewById(R.id.ans33_3e_2);
                if(ans33_3e_2.isChecked()){
                    sumaProcesosAgro += 100;
                }
                else {
                    sumaProcesosAgro -= 100;
                }
            }
        });
        ans33_3e_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3e_3 = (CheckBox) findViewById(R.id.ans33_3e_3);
                if(ans33_3e_3.isChecked()){
                    sumaProcesosAgro += 100;
                }
                else {
                    sumaProcesosAgro -= 100;
                }
            }
        });
        ans33_3e_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3e_4 = (CheckBox) findViewById(R.id.ans33_3e_4);
                if(ans33_3e_4.isChecked()){
                    sumaProcesosAgro += 100;
                }
                else {
                    sumaProcesosAgro -= 100;
                }
            }
        });
        ans33_3e_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3e_5 = (CheckBox) findViewById(R.id.ans33_3e_5);
                if(ans33_3e_5.isChecked()){
                    sumaProcesosAgro += 100;
                }
                else {
                    sumaProcesosAgro -= 100;
                }
            }
        });
        ans33_3e_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3e_6 = (CheckBox) findViewById(R.id.ans33_3e_6);
                if(ans33_3e_6.isChecked()){
                    sumaProcesosAgro += 100;
                }
                else {
                    sumaProcesosAgro -= 100;
                }
            }
        });

        ans33_3f_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3f_1 = (CheckBox) findViewById(R.id.ans33_3f_1);
                if(ans33_3f_1.isChecked()){
                    sumaAlmacenamiento += 100;
                }
                else {
                    sumaAlmacenamiento -= 100;
                }
            }
        });
        ans33_3f_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3f_2 = (CheckBox) findViewById(R.id.ans33_3f_2);
                if(ans33_3f_2.isChecked()){
                    sumaAlmacenamiento += 100;
                }
                else {
                    sumaAlmacenamiento -= 100;
                }
            }
        });
        ans33_3f_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3f_3 = (CheckBox) findViewById(R.id.ans33_3f_3);
                if(ans33_3f_3.isChecked()){
                    sumaAlmacenamiento += 100;
                }
                else {
                    sumaAlmacenamiento -= 100;
                }
            }
        });
        ans33_3f_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3f_4 = (CheckBox) findViewById(R.id.ans33_3f_4);
                if(ans33_3f_4.isChecked()){
                    sumaAlmacenamiento += 100;
                }
                else {
                    sumaAlmacenamiento -= 100;
                }
            }
        });
        ans33_3f_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans33_3f_5 = (CheckBox) findViewById(R.id.ans33_3f_5);
                if(ans33_3f_5.isChecked()){
                    sumaAlmacenamiento += 100;
                }
                else {
                    sumaAlmacenamiento -= 100;
                }
            }
        });

        ans33_3g.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans33_3g = (Spinner) findViewById(R.id.ans33_3g);
                if(ans33_3g.getSelectedItemPosition()==0) {
                    sumaRiego = 0;
                }else if (ans33_3g.getSelectedItemPosition()==1){
                    sumaRiego = 10;
                }else if (ans33_3g.getSelectedItemPosition()==2){
                    sumaRiego = 25;
                }else if (ans33_3g.getSelectedItemPosition()==3){
                    sumaRiego = 50;
                }else if (ans33_3g.getSelectedItemPosition()==4){
                    sumaRiego = 75;
                }else if (ans33_3g.getSelectedItemPosition()==5){
                    sumaRiego = 100;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans33_3h.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans33_3h = (Spinner) findViewById(R.id.ans33_3h);
                if(ans33_3h.getSelectedItemPosition()==0) {
                    sumaOtraTecnologia = 0;
                }else if (ans33_3h.getSelectedItemPosition()==1){
                    sumaOtraTecnologia = 10;
                }else if (ans33_3h.getSelectedItemPosition()==2){
                    sumaOtraTecnologia = 25;
                }else if (ans33_3h.getSelectedItemPosition()==3){
                    sumaOtraTecnologia = 50;
                }else if (ans33_3h.getSelectedItemPosition()==4){
                    sumaOtraTecnologia = 75;
                }else if (ans33_3h.getSelectedItemPosition()==5){
                    sumaOtraTecnologia = 100;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ans33_6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans33_6 = (Spinner) findViewById(R.id.ans33_6);
                EditText ans33_6other = (EditText) findViewById(R.id.ans33_6other);
                if(ans33_6.getSelectedItemPosition()==5) {
                    ans33_6other.setVisibility(View.VISIBLE);
                }else{
                    ans33_6other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans34a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans34a = (CheckBox) findViewById(R.id.ans34a);
                CheckBox ans34f = (CheckBox) findViewById(R.id.ans34f);
                if(ans34f.isChecked()){
                    ans34a.setChecked(false);
                }
                if(ans34a.isChecked()){
                    ans34code += 1;
                }
                else {
                    ans34code -= 1;
                }
            }
        });

        ans34b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans34b = (CheckBox) findViewById(R.id.ans34b);
                CheckBox ans34f = (CheckBox) findViewById(R.id.ans34f);
                if(ans34f.isChecked()){
                    ans34b.setChecked(false);
                }
                if(ans34b.isChecked()){
                    ans34code += 2;
                }
                else {
                    ans34code -= 2;
                }
            }
        });

        ans34c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans34c = (CheckBox) findViewById(R.id.ans34c);
                CheckBox ans34f = (CheckBox) findViewById(R.id.ans34f);
                if(ans34f.isChecked()){
                    ans34c.setChecked(false);
                }
                if(ans34c.isChecked()){
                    ans34code += 4;
                }
                else {
                    ans34code -= 4;
                }
            }
        });

        ans34d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans34d = (CheckBox) findViewById(R.id.ans34d);
                CheckBox ans34f = (CheckBox) findViewById(R.id.ans34f);
                if(ans34f.isChecked()){
                    ans34d.setChecked(false);
                }
                if(ans34d.isChecked()){
                    ans34code += 8;
                }
                else {
                    ans34code -= 8;
                }
            }
        });

        ans34e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans34e = (CheckBox) findViewById(R.id.ans34e);
                CheckBox ans34f = (CheckBox) findViewById(R.id.ans34f);
                EditText ans34other = (EditText) findViewById(R.id.ans34other);
                if(ans34f.isChecked()){
                    ans34e.setChecked(false);
                }
                if(ans34e.isChecked()){
                    ans34other.setVisibility(View.VISIBLE);
                    ans34code += 16;

                }
                else {
                    ans34other.setVisibility(View.GONE);
                    ans34code -= 16;
                }
            }
        });

        ans34f.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans34a = (CheckBox) findViewById(R.id.ans34a);
                CheckBox ans34b = (CheckBox) findViewById(R.id.ans34b);
                CheckBox ans34c = (CheckBox) findViewById(R.id.ans34c);
                CheckBox ans34d = (CheckBox) findViewById(R.id.ans34d);
                CheckBox ans34e = (CheckBox) findViewById(R.id.ans34e);
                CheckBox ans34f = (CheckBox) findViewById(R.id.ans34f);
                TextView ques34_1 = (TextView) findViewById(R.id.ques34_1);
                Spinner ans34_1 = (Spinner) findViewById(R.id.ans34_1);
                if(ans34f.isChecked()){
                    ans34code = 0;
                    ques34_1.setVisibility(View.GONE);
                    ans34_1.setVisibility(View.GONE);
                    ans34a.setChecked(false);
                    ans34b.setChecked(false);
                    ans34c.setChecked(false);
                    ans34d.setChecked(false);
                    ans34e.setChecked(false);
                }
                else {
                    ans34code = 0;
                    ques34_1.setVisibility(View.VISIBLE);
                    ans34_1.setVisibility(View.VISIBLE);
                }
            }
        });

        ans35a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans35a = (CheckBox) findViewById(R.id.ans35a);
                CheckBox ans35g = (CheckBox) findViewById(R.id.ans35g);
                if(ans35g.isChecked()){
                    ans35a.setChecked(false);
                }
                if(ans35a.isChecked()){
                    ans35code += 1;
                }
                else {
                    ans35code -= 1;
                }
            }
        });

        ans35b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans35b = (CheckBox) findViewById(R.id.ans35b);
                CheckBox ans35g = (CheckBox) findViewById(R.id.ans35g);
                if(ans35g.isChecked()){
                    ans35b.setChecked(false);
                }
                if(ans35b.isChecked()){
                    ans35code += 2;
                }
                else {
                    ans35code -= 2;
                }
            }
        });

        ans35c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans35c = (CheckBox) findViewById(R.id.ans35c);
                CheckBox ans35g = (CheckBox) findViewById(R.id.ans35g);
                if(ans35g.isChecked()){
                    ans35c.setChecked(false);
                }
                if(ans35c.isChecked()){
                    ans35code += 4;
                }
                else {
                    ans35code -= 4;
                }
            }
        });

        ans35d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans35d = (CheckBox) findViewById(R.id.ans35d);
                CheckBox ans35g = (CheckBox) findViewById(R.id.ans35g);
                if(ans35g.isChecked()){
                    ans35d.setChecked(false);
                }
                if(ans35d.isChecked()){
                    ans35code += 8;
                }
                else {
                    ans35code -= 8;
                }
            }
        });

        ans35e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans35e = (CheckBox) findViewById(R.id.ans35e);
                CheckBox ans35g = (CheckBox) findViewById(R.id.ans35g);
                if(ans35g.isChecked()){
                    ans35e.setChecked(false);
                }
                if(ans35e.isChecked()){
                    ans35code += 16;

                }
                else {
                    ans35code -= 16;
                }
            }
        });

        ans35f.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans35f = (CheckBox) findViewById(R.id.ans35f);
                CheckBox ans35g = (CheckBox) findViewById(R.id.ans35g);
                EditText ans35other = (EditText) findViewById(R.id.ans35other);
                if(ans35g.isChecked()){
                    ans35f.setChecked(false);
                }
                if(ans35f.isChecked()){
                    ans35other.setVisibility(View.VISIBLE);
                    ans35code += 32;
                }
                else {
                    ans35other.setVisibility(View.GONE);
                    ans35code -= 32;
                }
            }
        });

        ans35g.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans35a = (CheckBox) findViewById(R.id.ans35a);
                CheckBox ans35b = (CheckBox) findViewById(R.id.ans35b);
                CheckBox ans35c = (CheckBox) findViewById(R.id.ans35c);
                CheckBox ans35d = (CheckBox) findViewById(R.id.ans35d);
                CheckBox ans35e = (CheckBox) findViewById(R.id.ans35e);
                CheckBox ans35f = (CheckBox) findViewById(R.id.ans35f);
                CheckBox ans35g = (CheckBox) findViewById(R.id.ans35g);
                TextView ques35_1 = (TextView) findViewById(R.id.ques35_1);
                Spinner ans35_1 = (Spinner) findViewById(R.id.ans35_1);
                if(ans35g.isChecked()){
                    ans35code = 0;
                    ques35_1.setVisibility(View.GONE);
                    ans35_1.setVisibility(View.GONE);
                    ans35a.setChecked(false);
                    ans35b.setChecked(false);
                    ans35c.setChecked(false);
                    ans35d.setChecked(false);
                    ans35e.setChecked(false);
                    ans35f.setChecked(false);
                }
                else {
                    ans35code = 0;
                    ques35_1.setVisibility(View.VISIBLE);
                    ans35_1.setVisibility(View.VISIBLE);
                }
            }
        });

        ans36.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans36 = (Spinner) findViewById(R.id.ans36);
                EditText ans36other = (EditText) findViewById(R.id.ans36other);
                if(ans36.getSelectedItemPosition()==4) {
                    ans36other.setVisibility(View.VISIBLE);
                }else{
                    ans36other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans37_6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans37_6 = (Spinner) findViewById(R.id.ans37_6);
                Spinner ans37_7 = (Spinner) findViewById(R.id.ans37_7);
                if(ans37_6.getSelectedItemPosition() == 0){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosAmazonas);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 1){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosAntioquia);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 2){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosArauca);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 3){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosAtlantico);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 4){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosBolivar);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 5){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosBoyaca);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 6){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosCaldas);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 7){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosCaqueta);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 8){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosCasanare);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 9){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosCauca);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 10){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosCesar);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 11){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosChoco);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 12){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosCordoba);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 13){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosCundinamarca);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 14){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosGuainia);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 15){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosGuaviare);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 16){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosHuila);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 17){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosGuajira);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 18){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosMagdalena);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 19){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosMeta);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 20){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosNarino);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 21){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosNdeSantander);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 22){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosPutumayo);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 23){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosQuindio);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 24){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosRisaralda);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 25){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosSanAndres);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 26){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosSantander);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 27){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosSucre);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 28){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosTolima);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 29){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosValle);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 30){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosVaupes);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }else if(ans37_6.getSelectedItemPosition() == 31){
                    ArrayAdapter<String> adapterAns37_7 = new ArrayAdapter<String>(Preguntas2.this, android.R.layout.simple_spinner_item, municipiosVichada);
                    adapterAns37_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans37_7.setAdapter(adapterAns37_7);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans37.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans37 = (Spinner) findViewById(R.id.ans37);
                TextView ques37_1 = (TextView) findViewById(R.id.ques37_1);
                Spinner ans37_1 = (Spinner) findViewById(R.id.ans37_1);
                TextView ques37_2 = (TextView) findViewById(R.id.ques37_2);
                EditText ans37_2 = (EditText) findViewById(R.id.ans37_2);
                TextView ques37_3 = (TextView) findViewById(R.id.ques37_3);
                EditText ans37_3 = (EditText) findViewById(R.id.ans37_3);
                TextView ques37_4 = (TextView) findViewById(R.id.ques37_4);
                EditText ans37_4 = (EditText) findViewById(R.id.ans37_4);
                TextView ques37_5 = (TextView) findViewById(R.id.ques37_5);
                EditText ans37_5 = (EditText) findViewById(R.id.ans37_5);
                TextView ques37_6 = (TextView) findViewById(R.id.ques37_6);
                Spinner ans37_6 = (Spinner) findViewById(R.id.ans37_6);
                TextView ques37_7 = (TextView) findViewById(R.id.ques37_7);
                Spinner ans37_7 = (Spinner) findViewById(R.id.ans37_7);
                if(ans37.getSelectedItemPosition()==1) {
                    ques37_1.setVisibility(View.GONE);
                    ans37_1.setVisibility(View.GONE);
                    ques37_2.setVisibility(View.GONE);
                    ans37_2.setVisibility(View.GONE);
                    ques37_3.setVisibility(View.GONE);
                    ans37_3.setVisibility(View.GONE);
                    ques37_4.setVisibility(View.GONE);
                    ans37_4.setVisibility(View.GONE);
                    ques37_5.setVisibility(View.GONE);
                    ans37_5.setVisibility(View.GONE);
                    ques37_6.setVisibility(View.GONE);
                    ans37_6.setVisibility(View.GONE);
                    ques37_7.setVisibility(View.GONE);
                    ans37_7.setVisibility(View.GONE);
                }else{
                    ques37_1.setVisibility(View.VISIBLE);
                    ans37_1.setVisibility(View.VISIBLE);
                    ques37_2.setVisibility(View.VISIBLE);
                    ans37_2.setVisibility(View.VISIBLE);
                    ques37_3.setVisibility(View.VISIBLE);
                    ans37_3.setVisibility(View.VISIBLE);
                    ques37_4.setVisibility(View.VISIBLE);
                    ans37_4.setVisibility(View.VISIBLE);
                    ques37_5.setVisibility(View.VISIBLE);
                    ans37_5.setVisibility(View.VISIBLE);
                    ques37_6.setVisibility(View.VISIBLE);
                    ans37_6.setVisibility(View.VISIBLE);
                    ques37_7.setVisibility(View.VISIBLE);
                    ans37_7.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans37_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans37_1 = (Spinner) findViewById(R.id.ans37_1);
                EditText ans37_1other = (EditText) findViewById(R.id.ans37_1other);
                if(ans37_1.getSelectedItemPosition()==4) {
                    ans37_1other.setVisibility(View.VISIBLE);
                }else{
                    ans37_1other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans38.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans38 = (Spinner) findViewById(R.id.ans38);
                EditText ans38other = (EditText) findViewById(R.id.ans38other);
                if(ans38.getSelectedItemPosition()==3) {
                    ans38other.setVisibility(View.VISIBLE);
                }else{
                    ans38other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans40.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans40 = (Spinner) findViewById(R.id.ans40);
                TextView ques40_1 = (TextView) findViewById(R.id.ques40_1);
                EditText ans40_1 = (EditText) findViewById(R.id.ans40_1);
                TextView ques40_2 = (TextView) findViewById(R.id.ques40_2);
                EditText ans40_2 = (EditText) findViewById(R.id.ans40_2);
                if(ans40.getSelectedItemPosition()==1) {
                    ques40_1.setVisibility(View.GONE);
                    ans40_1.setVisibility(View.GONE);
                    ques40_2.setVisibility(View.GONE);
                    ans40_2.setVisibility(View.GONE);
                }else{
                    ques40_1.setVisibility(View.VISIBLE);
                    ans40_1.setVisibility(View.VISIBLE);
                    ques40_2.setVisibility(View.VISIBLE);
                    ans40_2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        saveButton =  (Button) findViewById(R.id.saveButton1);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                escribir();
                Intent main = new Intent(getApplicationContext(), MainScreen.class);
                startActivity(main);
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

    private void escribir() {
        try {
            EditText ans20 = (EditText) findViewById(R.id.ans20);
            EditText ans22a = (EditText) findViewById(R.id.ans22a);
            EditText ans22b = (EditText) findViewById(R.id.ans22b);
            EditText ans22c = (EditText) findViewById(R.id.ans22c);
            EditText ans22d = (EditText) findViewById(R.id.ans22d);
            EditText ans22e = (EditText) findViewById(R.id.ans22e);
            EditText ans25 = (EditText) findViewById(R.id.ans25);
            EditText ans27_1 = (EditText) findViewById(R.id.ans27_1);
            EditText ans28_1 = (EditText) findViewById(R.id.ans28_1);
            EditText ans28_2 = (EditText) findViewById(R.id.ans28_2);
            EditText ans29 = (EditText) findViewById(R.id.ans29);
            EditText ans29_1 = (EditText) findViewById(R.id.ans29_1);
            EditText ans31 = (EditText) findViewById(R.id.ans31);
            EditText ans31_1 = (EditText) findViewById(R.id.ans31_1);
            //EditText ans33_3 = (EditText) findViewById(R.id.ans33_3);
            EditText ans33_4 = (EditText) findViewById(R.id.ans33_4);
            EditText ans37_2 = (EditText) findViewById(R.id.ans37_2);
            EditText ans37_3 = (EditText) findViewById(R.id.ans37_3);
            EditText ans37_4 = (EditText) findViewById(R.id.ans37_4);
            EditText ans37_5 = (EditText) findViewById(R.id.ans37_5);
            EditText ans40_1 = (EditText) findViewById(R.id.ans40_1);
            EditText ans40_2 = (EditText) findViewById(R.id.ans40_2);

            Spinner ans18 = (Spinner) findViewById(R.id.ans18);
            Spinner ans19 = (Spinner) findViewById(R.id.ans19);
            Spinner ans23 = (Spinner) findViewById(R.id.ans23);
            Spinner ans24 = (Spinner) findViewById(R.id.ans24);
            Spinner ans26 = (Spinner) findViewById(R.id.ans26);
            Spinner ans27 = (Spinner) findViewById(R.id.ans27);
            Spinner ans28 = (Spinner) findViewById(R.id.ans28);
            Spinner ans30 = (Spinner) findViewById(R.id.ans30);
            Spinner ans32 = (Spinner) findViewById(R.id.ans32);
            Spinner ans33 = (Spinner) findViewById(R.id.ans33);
            Spinner ans33_1 = (Spinner) findViewById(R.id.ans33_1);
            Spinner ans33_3a = (Spinner) findViewById(R.id.ans33_3a);
            Spinner ans33_3d = (Spinner) findViewById(R.id.ans33_3d);
            Spinner ans33_3g = (Spinner) findViewById(R.id.ans33_3g);
            Spinner ans33_3h = (Spinner) findViewById(R.id.ans33_3h);
            Spinner ans33_5 = (Spinner) findViewById(R.id.ans33_5);
            Spinner ans33_6 = (Spinner) findViewById(R.id.ans33_6);
            Spinner ans34_1 = (Spinner) findViewById(R.id.ans34_1);
            Spinner ans35_1 = (Spinner) findViewById(R.id.ans35_1);
            Spinner ans36 = (Spinner) findViewById(R.id.ans36);
            Spinner ans37 = (Spinner) findViewById(R.id.ans37);
            Spinner ans37_1 = (Spinner) findViewById(R.id.ans37_1);
            Spinner ans37_6 = (Spinner) findViewById(R.id.ans37_6);
            Spinner ans37_7 = (Spinner) findViewById(R.id.ans37_7);
            Spinner ans38 = (Spinner) findViewById(R.id.ans38);
            Spinner ans39 = (Spinner) findViewById(R.id.ans39);
            Spinner ans40 = (Spinner) findViewById(R.id.ans40);

            EditText ans21other = (EditText) findViewById(R.id.ans21other);
            EditText ans23other = (EditText) findViewById(R.id.ans23other);
            EditText ans24other = (EditText) findViewById(R.id.ans24other);
            EditText ans32_1other = (EditText) findViewById(R.id.ans32_1other);
            EditText ans33_1other = (EditText) findViewById(R.id.ans33_1other);
            EditText ans33_2other = (EditText) findViewById(R.id.ans33_2other);
            EditText ans33_6other = (EditText) findViewById(R.id.ans33_6other);
            EditText ans34other = (EditText) findViewById(R.id.ans34other);
            EditText ans35other = (EditText) findViewById(R.id.ans35other);
            EditText ans36other = (EditText) findViewById(R.id.ans36other);
            EditText ans37_1other = (EditText) findViewById(R.id.ans37_1other);
            EditText ans38other = (EditText) findViewById(R.id.ans38other);

            CheckBox ans21e = (CheckBox) findViewById(R.id.ans21e);
            CheckBox ans32_1g = (CheckBox) findViewById(R.id.ans32_1g);
            CheckBox ans33_2a = (CheckBox) findViewById(R.id.ans33_2a);
            CheckBox ans33_2b = (CheckBox) findViewById(R.id.ans33_2b);
            CheckBox ans33_2c = (CheckBox) findViewById(R.id.ans33_2c);
            CheckBox ans33_2d = (CheckBox) findViewById(R.id.ans33_2d);
            CheckBox ans33_2e = (CheckBox) findViewById(R.id.ans33_2e);
            CheckBox ans33_2f = (CheckBox) findViewById(R.id.ans33_2f);
            CheckBox ans33_2g = (CheckBox) findViewById(R.id.ans33_2g);
            CheckBox ans33_2h = (CheckBox) findViewById(R.id.ans33_2h);
            CheckBox ans33_2i = (CheckBox) findViewById(R.id.ans33_2i);
            CheckBox ans34e = (CheckBox) findViewById(R.id.ans34e);
            CheckBox ans35f = (CheckBox) findViewById(R.id.ans35f);

            FileOutputStream fos = new FileOutputStream(myExternalFile,true);

            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans18.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans19.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans20.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans21code).getBytes());
            if(ans21e.isChecked()){
                fos.write(", ".getBytes());
            }
            fos.write(ans21other.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans22a.getText().toString().getBytes());
            fos.write(", ".getBytes());
            fos.write(ans22b.getText().toString().getBytes());
            fos.write(", ".getBytes());
            fos.write(ans22c.getText().toString().getBytes());
            fos.write(", ".getBytes());
            fos.write(ans22d.getText().toString().getBytes());
            fos.write(", ".getBytes());
            fos.write(ans22e.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans23.getSelectedItemPosition()+1).getBytes());
            if(ans23.getSelectedItemPosition() == 8){
                fos.write(", ".getBytes());
            }
            fos.write(ans23other.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans24.getSelectedItemPosition()+1).getBytes());
            if(ans24.getSelectedItemPosition() == 3){
                fos.write(", ".getBytes());
            }
            fos.write(ans24other.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans25.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans26.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans27.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans27_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans28.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans28_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans28_2.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans29.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans29_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans30.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans31.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans31_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans32.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans32_1code).getBytes());
            if(ans32_1g.isChecked()){
                fos.write(", ".getBytes());
            }
            fos.write(ans32_1other.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans33.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans33_1.getSelectedItemPosition()).getBytes());
            if (ans33_1.getSelectedItemPosition() == 4){
                fos.write(", ".getBytes());
            }
            fos.write(ans33_1other.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans33_2code).getBytes());
            if(ans33_2i.isChecked()){
                fos.write(", ".getBytes());
            }
            fos.write(ans33_2other.getText().toString().getBytes());
            fos.write("\t".getBytes());
            if(ans33_2a.isChecked()){
                fos.write(Integer.toString(0).getBytes());
                promedioSumas = 0;
            }
            if(ans33_2b.isChecked()){
                fos.write(Integer.toString(sumaFertirriego).getBytes());
                fos.write(", ".getBytes());
                promedioSumas += sumaFertirriego;
                contador +=1;
            }
            if(ans33_2c.isChecked()){
                fos.write(Integer.toString(sumaEstacion).getBytes());
                fos.write(", ".getBytes());
                promedioSumas += sumaEstacion;
                contador +=1;
            }
            if(ans33_2d.isChecked()){
                fos.write(Integer.toString(sumaTanques).getBytes());
                fos.write(", ".getBytes());
                promedioSumas += sumaTanques;
                contador +=1;
            }
            if(ans33_2e.isChecked()){
                fos.write(Integer.toString(sumaMacrotuneles).getBytes());
                fos.write(", ".getBytes());
                promedioSumas += sumaMacrotuneles;
                contador +=1;
            }
            if(ans33_2f.isChecked()){
                if(sumaProcesosAgro > 0)
                    sumaProcesosAgro = 100;
                fos.write(Integer.toString(sumaProcesosAgro).getBytes());
                fos.write(", ".getBytes());
                promedioSumas += sumaProcesosAgro;
                contador +=1;
            }
            if(ans33_2g.isChecked()){
                if(sumaAlmacenamiento > 0)
                    sumaAlmacenamiento = 100;
                fos.write(Integer.toString(sumaAlmacenamiento).getBytes());
                fos.write(", ".getBytes());
                promedioSumas += sumaAlmacenamiento;
                contador +=1;
            }
            if(ans33_2h.isChecked()){
                fos.write(Integer.toString(sumaRiego).getBytes());
                fos.write(", ".getBytes());
                promedioSumas += sumaRiego;
                contador +=1;
            }
            if(ans33_2i.isChecked()){
                fos.write(Integer.toString(sumaOtraTecnologia).getBytes());
                promedioSumas += sumaOtraTecnologia;
                contador +=1;
            }
            fos.write("\t".getBytes());
            fos.write(Integer.toString(promedioSumas/contador).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans33_4.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans33_5.getSelectedItemPosition()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans33_6.getSelectedItemPosition()).getBytes());
            if (ans33_6.getSelectedItemPosition() == 5){
                fos.write(", ".getBytes());
            }
            fos.write(ans33_6other.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans34code).getBytes());
            if(ans34e.isChecked()){
                fos.write(", ".getBytes());
            }
            fos.write(ans34other.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans34_1.getSelectedItemPosition()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans35code).getBytes());
            if(ans35f.isChecked()){
                fos.write(", ".getBytes());
            }
            fos.write(ans35other.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans35_1.getSelectedItemPosition()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans36.getSelectedItemPosition()+1).getBytes());
            if (ans36.getSelectedItemPosition() == 4){
                fos.write(", ".getBytes());
            }
            fos.write(ans36other.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans37.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans37_1.getSelectedItemPosition()+1).getBytes());
            if(ans37_1.getSelectedItemPosition() == 4){
                fos.write(", ".getBytes());
            }
            fos.write(ans37_1other.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans37_2.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans37_3.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans37_4.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans37_5.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans37_6.getItemAtPosition(ans37_6.getSelectedItemPosition()).toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans37_7.getItemAtPosition(ans37_7.getSelectedItemPosition()).toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans38.getSelectedItemPosition()+1).getBytes());
            if(ans38.getSelectedItemPosition() == 3){
                fos.write(", ".getBytes());
            }
            fos.write(ans38other.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans39.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans40.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans40_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans40_2.getText().toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}