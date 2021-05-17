package fedejandro.encuestadigital;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.*;

import org.w3c.dom.Text;

import java.io.*;

public class Preguntas3 extends AppCompatActivity {

    Button saveButton;
    private String filename = "datos.txt";
    private String filepath = "EncuestaDatos";
    File myExternalFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas3);

        String[] formaPesca = new String[] {
                "Permanente", "Ocasional", "Temporal", "Otra, ¿Cuál?"
        };
        String[] tipoActividad = new String[] {
                "Principal", "Complementaria"
        };
        String[] actividadPrincipal = new String[] {
                "No aplica", "Agricultura", "Comercio", "Construcción", "Ganadería", "Minería", "Turismo", "Acuicultura", "Transporte", "Otra, ¿Cuál?"
        };
        String[] siNo = new String[] {
                "Sí", "No"
        };
        String[] permiso = new String[] {
                "No aplica", "Autoridad pesquera", "La comunidad", "Asociación de pescadores", "Otro, ¿Cuál?"
        };
        String[] lugarPesca = new String[] {
                "Río", "Ciénaga", "Embalse", "Quebrada", "Caño"
        };
        String[] tiempoDia = new String[] {
                "Mañana", "Tarde", "Noche", "Madrugada", "Madrugada y mañana", "Madrugada y tarde", "Madrugada y noche", "Mañana y tarde", "Mañana y noche", "Tarde y noche"
        };
        String[] destinoProductos = new String[] {
                "Autoconsumo", "Comercialización"
        };
        String[] disminucionCaptura = new String[] {
                "No aplica", "Menor al 20% (Pérdidas mínimas)", "Entre el 20% y el 40% (Pérdidas moderadas)", "Mayor al 40% (Perdidas graves)"
        };
        String[] predadores = new String[] {
                "Aves", "Anfibios", "Réptiles", "Insectos", "Peces", "Manatíes", "Nutrias", "Otros, ¿Cuáles?", "Ninguno"
        };
        String[] tipoEmbarcacion = new String[] {
                "No aplica", "Bote", "Lancha", "Canoa", "Cayuco", "Otro, ¿Cuál?"
        };
        String[] materialEmbarcacion = new String[] {
                "No aplica", "Madera", "Fibra de vidrio", "Madera revestida en fibra", "Otro, ¿Cuál?"
        };
        String[] propulsion = new String[] {
                "No aplica", "Vela", "Remo", "Motor fuera de borda ", "Otro, ¿Cuál?"
        };
        String[] duenoEmbarcacion = new String[] {
                "No aplica", "Propia", "Alquilada", "Prestada", "Cooperativa", "Otro, ¿Cuál?"
        };
        String[] siNoNoAplica = new String[] {
                "No aplica", "Si", "No"
        };
        String[] instalacion = new String[] {
                "No aplica", "Propia", "Arrendada", "Otro tipo de tenencia, ¿Cuál?"
        };
        String[] fuentesEnergia = new String[] {
                "No aplica", "Red eléctrica publica", "Generador eléctrico a gasolina o diésel", "Energía solar", "Energía eólica", "Combustibles fósiles", "Otra, ¿Cuál?"
        };
        String[] tipoAsociacion = new String[] {
                "No aplica", "Cooperativa", "Asociación ", "Sindicato", "Otra, ¿Cuál?"
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

        String[] lugarVenta = new String[] {
                "Venta en el lugar de desembarco", "Centro de acopio", "Centro de comercialización"
        };
        String[] compradores = new String[] {
                "Intermediario", "Almacén de cadena", "Restaurante", "Consumidor final"
        };
        String[] presentacion = new String[] {
                "Entero", "Vivo", "Eviscerado", "Empacado en canastillas", "Empacado al vacío", "Seco", "Salado", "Sin escama", "Sin branquias", "Postas", "Descabezado", "Otro, ¿Cuál?"
        };
        String[] unidadVenta = new String[] {
                "Libra", "Kilogramo", "Arroba"
        };


        Spinner ans42 = (Spinner) findViewById(R.id.ans42);
        ArrayAdapter<String> adapterAns42 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, formaPesca);
        adapterAns42.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans42.setAdapter(adapterAns42);

        Spinner ans43 = (Spinner) findViewById(R.id.ans43);
        ArrayAdapter<String> adapterAns43 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipoActividad);
        adapterAns43.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans43.setAdapter(adapterAns43);

        Spinner ans43_1 = (Spinner) findViewById(R.id.ans43_1);
        ArrayAdapter<String> adapterAns43_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, actividadPrincipal);
        adapterAns43_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans43_1.setAdapter(adapterAns43_1);

        Spinner ans44 = (Spinner) findViewById(R.id.ans44);
        ArrayAdapter<String> adapterAns44 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns44.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans44.setAdapter(adapterAns44);

        Spinner ans44_1 = (Spinner) findViewById(R.id.ans44_1);
        ArrayAdapter<String> adapterAns44_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, permiso);
        adapterAns44_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans44_1.setAdapter(adapterAns44_1);

        Spinner ans45 = (Spinner) findViewById(R.id.ans45);
        ArrayAdapter<String> adapterAns45 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns45.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans45.setAdapter(adapterAns45);

        Spinner ans46 = (Spinner) findViewById(R.id.ans46);
        ArrayAdapter<String> adapterAns46 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lugarPesca);
        adapterAns46.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans46.setAdapter(adapterAns46);

        Spinner ans48 = (Spinner) findViewById(R.id.ans48);
        ArrayAdapter<String> adapterAns48 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tiempoDia);
        adapterAns48.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans48.setAdapter(adapterAns48);

        Spinner ans49 = (Spinner) findViewById(R.id.ans49);
        ArrayAdapter<String> adapterAns49 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, destinoProductos);
        adapterAns49.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans49.setAdapter(adapterAns49);

        Spinner ans50 = (Spinner) findViewById(R.id.ans50);
        ArrayAdapter<String> adapterAns50 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns50.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans50.setAdapter(adapterAns50);

        Spinner ans51 = (Spinner) findViewById(R.id.ans51);
        ArrayAdapter<String> adapterAns51 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns51.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans51.setAdapter(adapterAns51);

        Spinner ans64 = (Spinner) findViewById(R.id.ans64);
        ArrayAdapter<String> adapterAns64 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, destinoProductos);
        adapterAns64.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans64.setAdapter(adapterAns64);

        Spinner ans75 = (Spinner) findViewById(R.id.ans75);
        ArrayAdapter<String> adapterAns75 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns75.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans75.setAdapter(adapterAns75);

        Spinner ans77_1 = (Spinner) findViewById(R.id.ans77_1);
        ArrayAdapter<String> adapterAns77_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns77_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans77_1.setAdapter(adapterAns77_1);

        Spinner ans78 = (Spinner) findViewById(R.id.ans78);
        ArrayAdapter<String> adapterAns78 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns78.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans78.setAdapter(adapterAns78);

        Spinner ans79_1 = (Spinner) findViewById(R.id.ans79_1);
        ArrayAdapter<String> adapterAns79_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, disminucionCaptura);
        adapterAns79_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans79_1.setAdapter(adapterAns79_1);

        Spinner ans80 = (Spinner) findViewById(R.id.ans80);
        ArrayAdapter<String> adapterAns80 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, predadores);
        adapterAns80.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans80.setAdapter(adapterAns80);

        Spinner ans81 = (Spinner) findViewById(R.id.ans81);
        ArrayAdapter<String> adapterAns81 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns81.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans81.setAdapter(adapterAns81);

        Spinner ans82 = (Spinner) findViewById(R.id.ans82);
        ArrayAdapter<String> adapterAns82 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns82.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans82.setAdapter(adapterAns82);

        Spinner ans82_2 = (Spinner) findViewById(R.id.ans82_2);
        ArrayAdapter<String> adapterAns82_2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipoEmbarcacion);
        adapterAns82_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans82_2.setAdapter(adapterAns82_2);

        Spinner ans82_3 = (Spinner) findViewById(R.id.ans82_3);
        ArrayAdapter<String> adapterAns82_3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, materialEmbarcacion);
        adapterAns82_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans82_3.setAdapter(adapterAns82_3);

        Spinner ans82_4 = (Spinner) findViewById(R.id.ans82_4);
        ArrayAdapter<String> adapterAns82_4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, propulsion);
        adapterAns82_4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans82_4.setAdapter(adapterAns82_4);

        Spinner ans82_6 = (Spinner) findViewById(R.id.ans82_6);
        ArrayAdapter<String> adapterAns82_6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, duenoEmbarcacion);
        adapterAns82_6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans82_6.setAdapter(adapterAns82_6);

        Spinner ans82_7 = (Spinner) findViewById(R.id.ans82_7);
        ArrayAdapter<String> adapterAns82_7 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNoNoAplica);
        adapterAns82_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans82_7.setAdapter(adapterAns82_7);

        Spinner ans82_8 = (Spinner) findViewById(R.id.ans82_8);
        ArrayAdapter<String> adapterAns82_8 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNoNoAplica);
        adapterAns82_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans82_8.setAdapter(adapterAns82_8);

        Spinner ans84 = (Spinner) findViewById(R.id.ans84);
        ArrayAdapter<String> adapterAns84 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns84.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans84.setAdapter(adapterAns84);

        Spinner ans84_1 = (Spinner) findViewById(R.id.ans84_1);
        ArrayAdapter<String> adapterAns84_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, instalacion);
        adapterAns84_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans84_1.setAdapter(adapterAns84_1);

        Spinner ans85 = (Spinner) findViewById(R.id.ans85);
        ArrayAdapter<String> adapterAns85 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns85.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans85.setAdapter(adapterAns85);

        Spinner ans85_1 = (Spinner) findViewById(R.id.ans85_1);
        ArrayAdapter<String> adapterAns85_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, instalacion);
        adapterAns85_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans85_1.setAdapter(adapterAns85_1);

        Spinner ans86 = (Spinner) findViewById(R.id.ans86);
        ArrayAdapter<String> adapterAns86 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns86.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans86.setAdapter(adapterAns86);

        Spinner ans86_1 = (Spinner) findViewById(R.id.ans86_1);
        ArrayAdapter<String> adapterAns86_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, instalacion);
        adapterAns86_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans86_1.setAdapter(adapterAns86_1);

        Spinner ans87 = (Spinner) findViewById(R.id.ans87);
        ArrayAdapter<String> adapterAns87 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns87.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans87.setAdapter(adapterAns87);

        Spinner ans88 = (Spinner) findViewById(R.id.ans88);
        ArrayAdapter<String> adapterAns88 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns88.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans88.setAdapter(adapterAns88);

        Spinner ans89 = (Spinner) findViewById(R.id.ans89);
        ArrayAdapter<String> adapterAns89 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns89.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans89.setAdapter(adapterAns89);

        Spinner ans90 = (Spinner) findViewById(R.id.ans90);
        ArrayAdapter<String> adapterAns90 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns90.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans90.setAdapter(adapterAns90);

        Spinner ans91 = (Spinner) findViewById(R.id.ans91);
        ArrayAdapter<String> adapterAns91 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns91.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans91.setAdapter(adapterAns91);

        Spinner ans92 = (Spinner) findViewById(R.id.ans92);
        ArrayAdapter<String> adapterAns92 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns92.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans92.setAdapter(adapterAns92);

        Spinner ans92_1 = (Spinner) findViewById(R.id.ans92_1);
        ArrayAdapter<String> adapterAns92_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns92_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans92_1.setAdapter(adapterAns92_1);

        Spinner ans93 = (Spinner) findViewById(R.id.ans93);
        ArrayAdapter<String> adapterAns93 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns93.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans93.setAdapter(adapterAns93);

        Spinner ans93_1 = (Spinner) findViewById(R.id.ans93_1);
        ArrayAdapter<String> adapterAns93_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns93_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans93_1.setAdapter(adapterAns93_1);

        Spinner ans94 = (Spinner) findViewById(R.id.ans94);
        ArrayAdapter<String> adapterAns94 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns94.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans94.setAdapter(adapterAns94);

        Spinner ans94_1 = (Spinner) findViewById(R.id.ans94_1);
        ArrayAdapter<String> adapterAns94_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns94_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans94_1.setAdapter(adapterAns94_1);

        Spinner ans95 = (Spinner) findViewById(R.id.ans95);
        ArrayAdapter<String> adapterAns95 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fuentesEnergia);
        adapterAns95.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans95.setAdapter(adapterAns95);

        Spinner ans96 = (Spinner) findViewById(R.id.ans96);
        ArrayAdapter<String> adapterAns96 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns96.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans96.setAdapter(adapterAns96);

        Spinner ans96_1 = (Spinner) findViewById(R.id.ans96_1);
        ArrayAdapter<String> adapterAns96_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipoAsociacion);
        adapterAns96_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans96_1.setAdapter(adapterAns96_1);

        Spinner ans96_2 = (Spinner) findViewById(R.id.ans96_2);
        ArrayAdapter<String> adapterAns96_2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns96_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans96_2.setAdapter(adapterAns96_2);

        Spinner ans96_7 = (Spinner) findViewById(R.id.ans96_7);
        ArrayAdapter<String> adapterAns96_7 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, departamentos);
        adapterAns96_7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans96_7.setAdapter(adapterAns96_7);

        Spinner ans96_8 = (Spinner) findViewById(R.id.ans96_8);
        ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, municipiosAmazonas);
        adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans96_8.setAdapter(adapterAns96_8);

        Spinner ans96_9 = (Spinner) findViewById(R.id.ans96_9);
        ArrayAdapter<String> adapterAns96_9 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns96_9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans96_9.setAdapter(adapterAns96_9);

        Spinner ans97 = (Spinner) findViewById(R.id.ans97);
        ArrayAdapter<String> adapterAns97 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns97.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans97.setAdapter(adapterAns97);

        Spinner ans98_1 = (Spinner) findViewById(R.id.ans98_1);
        ArrayAdapter<String> adapterAns98_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unidadVenta);
        adapterAns98_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans98_1.setAdapter(adapterAns98_1);

        Spinner ans99_1 = (Spinner) findViewById(R.id.ans99_1);
        ArrayAdapter<String> adapterAns99_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unidadVenta);
        adapterAns99_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans99_1.setAdapter(adapterAns99_1);

        Spinner ans100_1 = (Spinner) findViewById(R.id.ans100_1);
        ArrayAdapter<String> adapterAns100_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unidadVenta);
        adapterAns100_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans100_1.setAdapter(adapterAns100_1);

        Spinner ans101 = (Spinner) findViewById(R.id.ans101);
        ArrayAdapter<String> adapterAns101 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lugarVenta);
        adapterAns101.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans101.setAdapter(adapterAns101);

        Spinner ans102 = (Spinner) findViewById(R.id.ans102);
        ArrayAdapter<String> adapterAns102 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, compradores);
        adapterAns102.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans102.setAdapter(adapterAns102);

        Spinner ans103 = (Spinner) findViewById(R.id.ans103);
        ArrayAdapter<String> adapterAns103 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, presentacion);
        adapterAns103.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans103.setAdapter(adapterAns103);

        Spinner ans104 = (Spinner) findViewById(R.id.ans104);
        ArrayAdapter<String> adapterAns104 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns104.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans104.setAdapter(adapterAns104);

        Spinner ans105 = (Spinner) findViewById(R.id.ans105);
        ArrayAdapter<String> adapterAns105 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns105.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans105.setAdapter(adapterAns105);

        Spinner ans106 = (Spinner) findViewById(R.id.ans106);
        ArrayAdapter<String> adapterAns106 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns106.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans106.setAdapter(adapterAns106);

        Spinner ans107 = (Spinner) findViewById(R.id.ans107);
        ArrayAdapter<String> adapterAns107 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns107.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans107.setAdapter(adapterAns107);

        Spinner ans108 = (Spinner) findViewById(R.id.ans108);
        ArrayAdapter<String> adapterAns108 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, siNo);
        adapterAns108.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ans108.setAdapter(adapterAns108);

        EditText ans42other = (EditText) findViewById(R.id.ans42other);
        ans42other.setVisibility(View.GONE);

        EditText ans43_1other = (EditText) findViewById(R.id.ans43_1other);
        ans43_1other.setVisibility(View.GONE);

        EditText ans44_1other = (EditText) findViewById(R.id.ans44_1other);
        ans44_1other.setVisibility(View.GONE);

        EditText ans76other = (EditText) findViewById(R.id.ans76other);
        ans76other.setVisibility(View.GONE);

        EditText ans79other = (EditText) findViewById(R.id.ans79other);
        ans79other.setVisibility(View.GONE);

        EditText ans80other = (EditText) findViewById(R.id.ans80other);
        ans80other.setVisibility(View.GONE);

        EditText ans82_2other = (EditText) findViewById(R.id.ans82_2other);
        ans82_2other.setVisibility(View.GONE);

        EditText ans82_3other = (EditText) findViewById(R.id.ans82_3other);
        ans82_3other.setVisibility(View.GONE);

        EditText ans82_4other = (EditText) findViewById(R.id.ans82_4other);
        ans82_4other.setVisibility(View.GONE);

        EditText ans82_6other = (EditText) findViewById(R.id.ans82_6other);
        ans82_6other.setVisibility(View.GONE);

        EditText ans83other = (EditText) findViewById(R.id.ans83other);
        ans83other.setVisibility(View.GONE);

        EditText ans84_1other = (EditText) findViewById(R.id.ans84_1other);
        ans84_1other.setVisibility(View.GONE);

        EditText ans85_1other = (EditText) findViewById(R.id.ans85_1other);
        ans85_1other.setVisibility(View.GONE);

        EditText ans86_1other = (EditText) findViewById(R.id.ans86_1other);
        ans86_1other.setVisibility(View.GONE);

        EditText ans95other = (EditText) findViewById(R.id.ans95other);
        ans95other.setVisibility(View.GONE);

        EditText ans96_1other = (EditText) findViewById(R.id.ans96_1other);
        ans96_1other.setVisibility(View.GONE);

        EditText ans103other = (EditText) findViewById(R.id.ans103other);
        ans103other.setVisibility(View.GONE);

        CheckBox ans76k = (CheckBox) findViewById(R.id.ans76k);
        CheckBox ans79e = (CheckBox) findViewById(R.id.ans79e);
        CheckBox ans79f = (CheckBox) findViewById(R.id.ans79f);
        CheckBox ans83j = (CheckBox) findViewById(R.id.ans83j);

        ans42.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans42 = (Spinner) findViewById(R.id.ans42);
                EditText ans42other = (EditText) findViewById(R.id.ans42other);
                if(ans42.getSelectedItemPosition()==3) {
                    ans42other.setVisibility(View.VISIBLE);
                }else{
                    ans42other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans43.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans43 = (Spinner) findViewById(R.id.ans43);
                TextView ques43_1 = (TextView) findViewById(R.id.ques43_1);
                Spinner ans43_1 = (Spinner) findViewById(R.id.ans43_1);
                if(ans43.getSelectedItemPosition()==0) {
                    ques43_1.setVisibility(View.GONE);
                    ans43_1.setVisibility(View.GONE);
                }else{
                    ques43_1.setVisibility(View.VISIBLE);
                    ans43_1.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans43_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans43_1 = (Spinner) findViewById(R.id.ans43_1);
                EditText ans43_1other = (EditText) findViewById(R.id.ans43_1other);
                if(ans43_1.getSelectedItemPosition()==9) {
                    ans43_1other.setVisibility(View.VISIBLE);
                }else{
                    ans43_1other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans44.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans44 = (Spinner) findViewById(R.id.ans44);
                TextView ques44_1 = (TextView) findViewById(R.id.ques44_1);
                Spinner ans44_1 = (Spinner) findViewById(R.id.ans44_1);
                if(ans44.getSelectedItemPosition()==1) {
                    ques44_1.setVisibility(View.GONE);
                    ans44_1.setVisibility(View.GONE);
                }else{
                    ques44_1.setVisibility(View.VISIBLE);
                    ans44_1.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans44_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans44_1 = (Spinner) findViewById(R.id.ans44_1);
                EditText ans44_1other = (EditText) findViewById(R.id.ans44_1other);
                if(ans44_1.getSelectedItemPosition()==4) {
                    ans44_1other.setVisibility(View.VISIBLE);
                }else{
                    ans44_1other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans76k.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans76k = (CheckBox) findViewById(R.id.ans76k);
                EditText ans76other = (EditText) findViewById(R.id.ans76other);
                if(ans76k.isChecked()){
                    ans76other.setVisibility(View.VISIBLE);
                }
                else {
                    ans76other.setVisibility(View.GONE);
                }
            }
        });

        ans78.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans78 = (Spinner) findViewById(R.id.ans78);
                TextView ques78_1 = (TextView) findViewById(R.id.ques78_1);
                EditText ans78_1 = (EditText) findViewById(R.id.ans78_1);
                if(ans78.getSelectedItemPosition()==1) {
                    ques78_1.setVisibility(View.GONE);
                    ans78_1.setVisibility(View.GONE);
                }else{
                    ques78_1.setVisibility(View.VISIBLE);
                    ans78_1.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans79e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans79e = (CheckBox) findViewById(R.id.ans79e);
                EditText ans79other = (EditText) findViewById(R.id.ans79other);
                if(ans79e.isChecked()){
                    ans79other.setVisibility(View.VISIBLE);
                }
                else {
                    ans79other.setVisibility(View.GONE);
                }
            }
        });

        ans79f.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans79f = (CheckBox) findViewById(R.id.ans79f);
                TextView ques79_1 = (TextView) findViewById(R.id.ques79_1);
                Spinner ans79_1 = (Spinner) findViewById(R.id.ans79_1);
                if(ans79f.isChecked()){
                    ques79_1.setVisibility(View.GONE);
                    ans79_1.setVisibility(View.GONE);
                }
                else {

                    ques79_1.setVisibility(View.VISIBLE);
                    ans79_1.setVisibility(View.VISIBLE);
                }
            }
        });

        ans80.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans80 = (Spinner) findViewById(R.id.ans80);
                EditText ans80other = (EditText) findViewById(R.id.ans80other);
                if(ans80.getSelectedItemPosition()==7) {
                    ans80other.setVisibility(View.VISIBLE);
                }else{
                    ans80other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans82.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans82 = (Spinner) findViewById(R.id.ans82);
                TextView ques82_1 = (TextView) findViewById(R.id.ques82_1);
                EditText ans82_1 = (EditText) findViewById(R.id.ans82_1);
                TextView ques82_2 = (TextView) findViewById(R.id.ques82_2);
                Spinner ans82_2 = (Spinner) findViewById(R.id.ans82_2);
                TextView ques82_3 = (TextView) findViewById(R.id.ques82_3);
                Spinner ans82_3 = (Spinner) findViewById(R.id.ans82_3);
                TextView ques82_4 = (TextView) findViewById(R.id.ques82_4);
                Spinner ans82_4 = (Spinner) findViewById(R.id.ans82_4);
                TextView ques82_5 = (TextView) findViewById(R.id.ques82_5);
                EditText ans82_5 = (EditText) findViewById(R.id.ans82_5);
                TextView ques82_6 = (TextView) findViewById(R.id.ques82_6);
                Spinner ans82_6 = (Spinner) findViewById(R.id.ans82_6);
                TextView ques82_7 = (TextView) findViewById(R.id.ques82_7);
                Spinner ans82_7 = (Spinner) findViewById(R.id.ans82_7);
                TextView ques82_8 = (TextView) findViewById(R.id.ques82_8);
                Spinner ans82_8 = (Spinner) findViewById(R.id.ans82_8);
                TextView ques82_9 = (TextView) findViewById(R.id.ques82_9);
                EditText ans82_9 = (EditText) findViewById(R.id.ans82_9);

                if(ans82.getSelectedItemPosition()==1) {
                    ques82_1.setVisibility(View.GONE);
                    ans82_1.setVisibility(View.GONE);
                    ques82_2.setVisibility(View.GONE);
                    ans82_2.setVisibility(View.GONE);
                    ques82_3.setVisibility(View.GONE);
                    ans82_3.setVisibility(View.GONE);
                    ques82_4.setVisibility(View.GONE);
                    ans82_4.setVisibility(View.GONE);
                    ques82_5.setVisibility(View.GONE);
                    ans82_5.setVisibility(View.GONE);
                    ques82_6.setVisibility(View.GONE);
                    ans82_6.setVisibility(View.GONE);
                    ques82_7.setVisibility(View.GONE);
                    ans82_7.setVisibility(View.GONE);
                    ques82_8.setVisibility(View.GONE);
                    ans82_8.setVisibility(View.GONE);
                    ques82_9.setVisibility(View.GONE);
                    ans82_9.setVisibility(View.GONE);
                }else{
                    ques82_1.setVisibility(View.VISIBLE);
                    ans82_1.setVisibility(View.VISIBLE);
                    ques82_2.setVisibility(View.VISIBLE);
                    ans82_2.setVisibility(View.VISIBLE);
                    ques82_3.setVisibility(View.VISIBLE);
                    ans82_3.setVisibility(View.VISIBLE);
                    ques82_4.setVisibility(View.VISIBLE);
                    ans82_4.setVisibility(View.VISIBLE);
                    ques82_5.setVisibility(View.VISIBLE);
                    ans82_5.setVisibility(View.VISIBLE);
                    ques82_6.setVisibility(View.VISIBLE);
                    ans82_6.setVisibility(View.VISIBLE);
                    ques82_7.setVisibility(View.VISIBLE);
                    ans82_7.setVisibility(View.VISIBLE);
                    ques82_8.setVisibility(View.VISIBLE);
                    ans82_8.setVisibility(View.VISIBLE);
                    ques82_9.setVisibility(View.VISIBLE);
                    ans82_9.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans82_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans82_2 = (Spinner) findViewById(R.id.ans82_2);
                EditText ans82_2other = (EditText) findViewById(R.id.ans82_2other);
                if(ans82_2.getSelectedItemPosition()==5) {
                    ans82_2other.setVisibility(View.VISIBLE);
                }else{
                    ans82_2other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans82_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans82_3 = (Spinner) findViewById(R.id.ans82_3);
                EditText ans82_3other = (EditText) findViewById(R.id.ans82_3other);
                if(ans82_3.getSelectedItemPosition()==4) {
                    ans82_3other.setVisibility(View.VISIBLE);
                }else{
                    ans82_3other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans82_4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans82_4 = (Spinner) findViewById(R.id.ans82_4);
                EditText ans82_4other = (EditText) findViewById(R.id.ans82_4other);
                TextView ques82_5 = (TextView) findViewById(R.id.ques82_5);
                EditText ans82_5 = (EditText) findViewById(R.id.ans82_5);
                if(ans82_4.getSelectedItemPosition()==3) {
                    ques82_5.setVisibility(View.VISIBLE);
                    ans82_5.setVisibility(View.VISIBLE);
                }else{
                    ques82_5.setVisibility(View.GONE);
                    ans82_5.setVisibility(View.GONE);
                }
                if(ans82_4.getSelectedItemPosition()==4){
                    ans82_4other.setVisibility(View.VISIBLE);
                }else{
                    ans82_4other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans82_6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans82_6 = (Spinner) findViewById(R.id.ans82_6);
                EditText ans82_6other = (EditText) findViewById(R.id.ans82_6other);
                if(ans82_6.getSelectedItemPosition()==5) {
                    ans82_6other.setVisibility(View.VISIBLE);
                }else{
                    ans82_6other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans82_8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans82_8 = (Spinner) findViewById(R.id.ans82_8);
                TextView ques82_5 = (TextView) findViewById(R.id.ques82_5);
                EditText ans82_5 = (EditText) findViewById(R.id.ans82_5);
                TextView ques82_9 = (TextView) findViewById(R.id.ques82_9);
                EditText ans82_9 = (EditText) findViewById(R.id.ans82_9);
                if(ans82_8.getSelectedItemPosition()==2) {
                    ques82_9.setVisibility(View.GONE);
                    ans82_9.setVisibility(View.GONE);
                }else{
                    ques82_9.setVisibility(View.VISIBLE);
                    ans82_9.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TextView ques83 = (TextView)findViewById(R.id.ques83);
        String q83normalText1 = "83. De la siguiente lista ¿Qué ";
        String q83boldText = "artes de pesca";
        String q83normalText2 = " utiliza?";
        SpannableString str83 = new SpannableString(q83normalText1 + q83boldText + q83normalText2);
        str83.setSpan(new StyleSpan(Typeface.BOLD), q83normalText1.length(), q83normalText1.length()+q83boldText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ques83.setText(str83);

        ans83j.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox ans83j = (CheckBox) findViewById(R.id.ans83j);
                EditText ans83other = (EditText) findViewById(R.id.ans83other);
                if(ans83j.isChecked()){
                    ans83other.setVisibility(View.VISIBLE);
                }
                else {
                    ans83other.setVisibility(View.GONE);
                }
            }
        });

        ans84.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans84 = (Spinner) findViewById(R.id.ans84);
                TextView ques84_1 = (TextView) findViewById(R.id.ques84_1);
                Spinner ans84_1 = (Spinner) findViewById(R.id.ans84_1);
                if(ans84.getSelectedItemPosition()==1) {
                    ques84_1.setVisibility(View.GONE);
                    ans84_1.setVisibility(View.GONE);
                }else{
                    ques84_1.setVisibility(View.VISIBLE);
                    ans84_1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans84_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans84_1 = (Spinner) findViewById(R.id.ans84_1);
                EditText ans84_1other = (EditText) findViewById(R.id.ans84_1other);
                if(ans84_1.getSelectedItemPosition()==3) {
                    ans84_1other.setVisibility(View.VISIBLE);
                }else{
                    ans84_1other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans85.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans85 = (Spinner) findViewById(R.id.ans85);
                TextView ques85_1 = (TextView) findViewById(R.id.ques85_1);
                Spinner ans85_1 = (Spinner) findViewById(R.id.ans85_1);
                if(ans85.getSelectedItemPosition()==1) {
                    ques85_1.setVisibility(View.GONE);
                    ans85_1.setVisibility(View.GONE);
                }else{
                    ques85_1.setVisibility(View.VISIBLE);
                    ans85_1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans85_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans85_1 = (Spinner) findViewById(R.id.ans85_1);
                EditText ans85_1other = (EditText) findViewById(R.id.ans85_1other);
                if(ans85_1.getSelectedItemPosition()==3) {
                    ans85_1other.setVisibility(View.VISIBLE);
                }else{
                    ans85_1other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans86.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans86 = (Spinner) findViewById(R.id.ans86);
                TextView ques86_1 = (TextView) findViewById(R.id.ques86_1);
                Spinner ans86_1 = (Spinner) findViewById(R.id.ans86_1);
                if(ans86.getSelectedItemPosition()==1) {
                    ques86_1.setVisibility(View.GONE);
                    ans86_1.setVisibility(View.GONE);
                }else{
                    ques86_1.setVisibility(View.VISIBLE);
                    ans86_1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans86_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans86_1 = (Spinner) findViewById(R.id.ans86_1);
                EditText ans86_1other = (EditText) findViewById(R.id.ans86_1other);
                if(ans86_1.getSelectedItemPosition()==3) {
                    ans86_1other.setVisibility(View.VISIBLE);
                }else{
                    ans86_1other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TextView ques87 = (TextView)findViewById(R.id.ques87);
        String q87normalText1 = "87. ¿Realiza disposición adecuada de los desechos sólidos como ";
        String q87boldText = "basuras";
        String q87normalText2 = ", restos de redes y otros residuos producto de la faena de pesca?";
        SpannableString str87 = new SpannableString(q87normalText1 + q87boldText + q87normalText2);
        str87.setSpan(new StyleSpan(Typeface.BOLD), q87normalText1.length(), q87normalText1.length()+q87boldText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ques87.setText(str87);

        ans87.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans87 = (Spinner) findViewById(R.id.ans87);
                TextView ques87_1 = (TextView) findViewById(R.id.ques87_1);
                EditText ans87_1 = (EditText) findViewById(R.id.ans87_1);
                if(ans87.getSelectedItemPosition()==1) {
                    ques87_1.setVisibility(View.GONE);
                    ans87_1.setVisibility(View.GONE);
                }else{
                    ques87_1.setVisibility(View.VISIBLE);
                    ans87_1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TextView ques89 = (TextView)findViewById(R.id.ques89);
        String q89normalText1 = "89. ¿Realiza disposición adecuada de los ";
        String q89boldText = "desechos sólidos";
        String q89normalText2 = " por faenado y/o transformación  (vísceras, huesos, escamas, otros)?";
        SpannableString str89 = new SpannableString(q89normalText1 + q89boldText + q89normalText2);
        str89.setSpan(new StyleSpan(Typeface.BOLD), q89normalText1.length(), q89normalText1.length()+q89boldText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ques89.setText(str89);

        ans89.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans89 = (Spinner) findViewById(R.id.ans89);
                TextView ques89_1 = (TextView) findViewById(R.id.ques89_1);
                EditText ans89_1 = (EditText) findViewById(R.id.ans89_1);
                if(ans89.getSelectedItemPosition()==1) {
                    ques89_1.setVisibility(View.GONE);
                    ans89_1.setVisibility(View.GONE);
                }else{
                    ques89_1.setVisibility(View.VISIBLE);
                    ans89_1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans95.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans95 = (Spinner) findViewById(R.id.ans95);
                EditText ans95other = (EditText) findViewById(R.id.ans95other);
                if(ans95.getSelectedItemPosition()==6) {
                    ans95other.setVisibility(View.VISIBLE);
                }else{
                    ans95other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans96.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans96 = (Spinner) findViewById(R.id.ans96);
                TextView ques96_1 = (TextView) findViewById(R.id.ques96_1);
                Spinner ans96_1 = (Spinner) findViewById(R.id.ans96_1);
                TextView ques96_2 = (TextView) findViewById(R.id.ques96_2);
                Spinner ans96_2 = (Spinner) findViewById(R.id.ans96_2);
                TextView ques96_3 = (TextView) findViewById(R.id.ques96_3);
                EditText ans96_3 = (EditText) findViewById(R.id.ans96_3);
                TextView ques96_4 = (TextView) findViewById(R.id.ques96_4);
                EditText ans96_4 = (EditText) findViewById(R.id.ans96_4);
                TextView ques96_5 = (TextView) findViewById(R.id.ques96_5);
                EditText ans96_5 = (EditText) findViewById(R.id.ans96_5);
                TextView ques96_6 = (TextView) findViewById(R.id.ques96_6);
                EditText ans96_6 = (EditText) findViewById(R.id.ans96_6);
                TextView ques96_7 = (TextView) findViewById(R.id.ques96_7);
                Spinner ans96_7 = (Spinner) findViewById(R.id.ans96_7);
                TextView ques96_8 = (TextView) findViewById(R.id.ques96_8);
                Spinner ans96_8 = (Spinner) findViewById(R.id.ans96_8);
                TextView ques96_9 = (TextView) findViewById(R.id.ques96_9);
                Spinner ans96_9 = (Spinner) findViewById(R.id.ans96_9);
                if(ans96.getSelectedItemPosition()==1) {
                    ques96_1.setVisibility(View.GONE);
                    ans96_1.setVisibility(View.GONE);
                    ques96_2.setVisibility(View.GONE);
                    ans96_2.setVisibility(View.GONE);
                    ques96_3.setVisibility(View.GONE);
                    ans96_3.setVisibility(View.GONE);
                    ques96_4.setVisibility(View.GONE);
                    ans96_4.setVisibility(View.GONE);
                    ques96_5.setVisibility(View.GONE);
                    ans96_5.setVisibility(View.GONE);
                    ques96_6.setVisibility(View.GONE);
                    ans96_6.setVisibility(View.GONE);
                    ques96_7.setVisibility(View.GONE);
                    ans96_7.setVisibility(View.GONE);
                    ques96_8.setVisibility(View.GONE);
                    ans96_8.setVisibility(View.GONE);
                    ques96_9.setVisibility(View.VISIBLE);
                    ans96_9.setVisibility(View.VISIBLE);
                }else{
                    ques96_1.setVisibility(View.VISIBLE);
                    ans96_1.setVisibility(View.VISIBLE);
                    ques96_2.setVisibility(View.VISIBLE);
                    ans96_2.setVisibility(View.VISIBLE);
                    ques96_3.setVisibility(View.VISIBLE);
                    ans96_3.setVisibility(View.VISIBLE);
                    ques96_4.setVisibility(View.VISIBLE);
                    ans96_4.setVisibility(View.VISIBLE);
                    ques96_5.setVisibility(View.VISIBLE);
                    ans96_5.setVisibility(View.VISIBLE);
                    ques96_6.setVisibility(View.VISIBLE);
                    ans96_6.setVisibility(View.VISIBLE);
                    ques96_7.setVisibility(View.VISIBLE);
                    ans96_7.setVisibility(View.VISIBLE);
                    ques96_8.setVisibility(View.VISIBLE);
                    ans96_8.setVisibility(View.VISIBLE);
                    ques96_9.setVisibility(View.GONE);
                    ans96_9.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans96_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans96_1 = (Spinner) findViewById(R.id.ans96_1);
                EditText ans96_1other = (EditText) findViewById(R.id.ans96_1other);
                if(ans96_1.getSelectedItemPosition()==4) {
                    ans96_1other.setVisibility(View.VISIBLE);
                }else{
                    ans96_1other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans96_7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans96_8 = (Spinner) findViewById(R.id.ans96_8);
                Spinner ans96_7 = (Spinner) findViewById(R.id.ans96_7);
                if(ans96_7.getSelectedItemPosition() == 0){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosAmazonas);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 1){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosAntioquia);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 2){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosArauca);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 3){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosAtlantico);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 4){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosBolivar);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 5){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosBoyaca);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 6){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosCaldas);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 7){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosCaqueta);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 8){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosCasanare);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 9){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosCauca);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 10){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosCesar);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 11){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosChoco);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 12){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosCordoba);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 13){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosCundinamarca);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 14){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosGuainia);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 15){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosGuaviare);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 16){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosHuila);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 17){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosGuajira);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 18){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosMagdalena);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 18){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosMeta);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 20){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosNarino);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 21){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosNdeSantander);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 22){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosPutumayo);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 22){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosQuindio);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 23){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosRisaralda);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 24){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosSanAndres);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 25){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosSantander);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 26){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosSucre);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 27){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosTolima);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 28){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosValle);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 29){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosVaupes);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }else if(ans96_7.getSelectedItemPosition() == 30){
                    ArrayAdapter<String> adapterAns96_8 = new ArrayAdapter<String>(Preguntas3.this, android.R.layout.simple_spinner_item, municipiosVichada);
                    adapterAns96_8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ans96_8.setAdapter(adapterAns96_8);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans97.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans97 = (Spinner) findViewById(R.id.ans97);
                TextView ques97_1 = (TextView) findViewById(R.id.ques97_1);
                EditText ans97_1 = (EditText) findViewById(R.id.ans97_1);
                if(ans97.getSelectedItemPosition()==1) {
                    ques97_1.setVisibility(View.GONE);
                    ans97_1.setVisibility(View.GONE);
                }else{
                    ques97_1.setVisibility(View.VISIBLE);
                    ans97_1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans103.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans103 = (Spinner) findViewById(R.id.ans103);
                EditText ans103other = (EditText) findViewById(R.id.ans103other);
                if(ans103.getSelectedItemPosition()==11) {
                    ans103other.setVisibility(View.VISIBLE);
                }else{
                    ans103other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ans105.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner ans105 = (Spinner) findViewById(R.id.ans105);
                TextView ques105_1 = (TextView) findViewById(R.id.ques105_1);
                EditText ans105_1 = (EditText) findViewById(R.id.ans105_1);
                if(ans105.getSelectedItemPosition()==1) {
                    ques105_1.setVisibility(View.GONE);
                    ans105_1.setVisibility(View.GONE);
                }else{
                    ques105_1.setVisibility(View.VISIBLE);
                    ans105_1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        saveButton =  (Button) findViewById(R.id.endButton2);
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

    private void escribir(){

        try {

            EditText ans46_1 = (EditText) findViewById(R.id.ans46_1);
            EditText ans47 = (EditText) findViewById(R.id.ans47);
            EditText ans52 = (EditText) findViewById(R.id.ans52);
            EditText ans52_1 = (EditText) findViewById(R.id.ans52_1);
            EditText ans52_2 = (EditText) findViewById(R.id.ans52_2);
            EditText ans52_3 = (EditText) findViewById(R.id.ans52_3);
            EditText ans52_4 = (EditText) findViewById(R.id.ans52_4);
            EditText ans53 = (EditText) findViewById(R.id.ans53);
            EditText ans54a = (EditText) findViewById(R.id.ans54a);
            EditText ans54b = (EditText) findViewById(R.id.ans54b);
            EditText ans54c = (EditText) findViewById(R.id.ans54c);
            EditText ans54_1 = (EditText) findViewById(R.id.ans54_1);
            EditText ans54_2 = (EditText) findViewById(R.id.ans54_2);
            EditText ans54_3 = (EditText) findViewById(R.id.ans54_3);
            EditText ans54_4 = (EditText) findViewById(R.id.ans54_4);
            EditText ans54_5 = (EditText) findViewById(R.id.ans54_5);
            EditText ans54_6 = (EditText) findViewById(R.id.ans54_6);
            EditText ans55 = (EditText) findViewById(R.id.ans55);
            EditText ans56 = (EditText) findViewById(R.id.ans56);
            EditText ans57 = (EditText) findViewById(R.id.ans57);
            EditText ans58 = (EditText) findViewById(R.id.ans58);
            EditText ans59 = (EditText) findViewById(R.id.ans59);
            EditText ans60 = (EditText) findViewById(R.id.ans60);
            EditText ans61 = (EditText) findViewById(R.id.ans61);
            EditText ans62 = (EditText) findViewById(R.id.ans62);
            EditText ans63 = (EditText) findViewById(R.id.ans63);
            EditText ans64_1 = (EditText) findViewById(R.id.ans64_1);
            EditText ans64_2 = (EditText) findViewById(R.id.ans64_2);
            EditText ans65 = (EditText) findViewById(R.id.ans65);
            EditText ans65_1 = (EditText) findViewById(R.id.ans65_1);
            EditText ans66 = (EditText) findViewById(R.id.ans66);
            EditText ans66_1 = (EditText) findViewById(R.id.ans66_1);
            EditText ans67 = (EditText) findViewById(R.id.ans67);
            EditText ans67_1 = (EditText) findViewById(R.id.ans67_1);
            EditText ans68 = (EditText) findViewById(R.id.ans68);
            EditText ans68_1 = (EditText) findViewById(R.id.ans68_1);
            EditText ans69 = (EditText) findViewById(R.id.ans69);
            EditText ans69_1 = (EditText) findViewById(R.id.ans69_1);
            EditText ans70 = (EditText) findViewById(R.id.ans70);
            EditText ans70_1 = (EditText) findViewById(R.id.ans70_1);
            EditText ans71 = (EditText) findViewById(R.id.ans71);
            EditText ans71_1 = (EditText) findViewById(R.id.ans71_1);
            EditText ans72 = (EditText) findViewById(R.id.ans72);
            EditText ans72_1 = (EditText) findViewById(R.id.ans72_1);
            EditText ans73 = (EditText) findViewById(R.id.ans73);
            EditText ans73_1 = (EditText) findViewById(R.id.ans73_1);
            EditText ans74 = (EditText) findViewById(R.id.ans74);
            EditText ans74_1 = (EditText) findViewById(R.id.ans74_1);
            EditText ans77 = (EditText) findViewById(R.id.ans77);
            EditText ans78_1 = (EditText) findViewById(R.id.ans78_1);
            EditText ans82_1 = (EditText) findViewById(R.id.ans82_1);
            EditText ans82_5 = (EditText) findViewById(R.id.ans82_5);
            EditText ans82_9 = (EditText) findViewById(R.id.ans82_9);
            EditText ans83_1 = (EditText) findViewById(R.id.ans83_1);
            EditText ans83_2 = (EditText) findViewById(R.id.ans83_2);
            EditText ans83_3 = (EditText) findViewById(R.id.ans83_3);
            EditText ans83_4 = (EditText) findViewById(R.id.ans83_4);
            EditText ans83_5 = (EditText) findViewById(R.id.ans83_5);
            EditText ans83_6 = (EditText) findViewById(R.id.ans83_6);
            EditText ans83_7 = (EditText) findViewById(R.id.ans83_7);
            EditText ans83_8 = (EditText) findViewById(R.id.ans83_8);
            EditText ans83_9 = (EditText) findViewById(R.id.ans83_9);
            EditText ans83_10 = (EditText) findViewById(R.id.ans83_10);
            EditText ans87_1 = (EditText) findViewById(R.id.ans87_1);
            EditText ans89_1 = (EditText) findViewById(R.id.ans89_1);
            EditText ans96_3 = (EditText) findViewById(R.id.ans96_3);
            EditText ans96_4 = (EditText) findViewById(R.id.ans96_4);
            EditText ans96_5 = (EditText) findViewById(R.id.ans96_5);
            EditText ans96_6 = (EditText) findViewById(R.id.ans96_6);
            EditText ans97_1 = (EditText) findViewById(R.id.ans97_1);
            EditText ans98 = (EditText) findViewById(R.id.ans98);
            EditText ans98_2 = (EditText) findViewById(R.id.ans98_2);
            EditText ans99 = (EditText) findViewById(R.id.ans99);
            EditText ans99_2 = (EditText) findViewById(R.id.ans99_2);
            EditText ans100 = (EditText) findViewById(R.id.ans100);
            EditText ans100_2 = (EditText) findViewById(R.id.ans100_2);
            EditText ans105_1 = (EditText) findViewById(R.id.ans105_1);

            Spinner ans42 = (Spinner) findViewById(R.id.ans42);
            Spinner ans43 = (Spinner) findViewById(R.id.ans43);
            Spinner ans43_1 = (Spinner) findViewById(R.id.ans43_1);
            Spinner ans44 = (Spinner) findViewById(R.id.ans44);
            Spinner ans44_1 = (Spinner) findViewById(R.id.ans44_1);
            Spinner ans45 = (Spinner) findViewById(R.id.ans45);
            Spinner ans46 = (Spinner) findViewById(R.id.ans46);
            Spinner ans48 = (Spinner) findViewById(R.id.ans48);
            Spinner ans49 = (Spinner) findViewById(R.id.ans49);
            Spinner ans50 = (Spinner) findViewById(R.id.ans50);
            Spinner ans51 = (Spinner) findViewById(R.id.ans51);
            Spinner ans64 = (Spinner) findViewById(R.id.ans64);
            Spinner ans75 = (Spinner) findViewById(R.id.ans75);
            Spinner ans77_1 = (Spinner) findViewById(R.id.ans77_1);
            Spinner ans78 = (Spinner) findViewById(R.id.ans78);
            Spinner ans79_1 = (Spinner) findViewById(R.id.ans79_1);
            Spinner ans80 = (Spinner) findViewById(R.id.ans80);
            Spinner ans81 = (Spinner) findViewById(R.id.ans81);
            Spinner ans82 = (Spinner) findViewById(R.id.ans82);
            Spinner ans82_2 = (Spinner) findViewById(R.id.ans82_2);
            Spinner ans82_3 = (Spinner) findViewById(R.id.ans82_3);
            Spinner ans82_4 = (Spinner) findViewById(R.id.ans82_4);
            Spinner ans82_6 = (Spinner) findViewById(R.id.ans82_6);
            Spinner ans82_7 = (Spinner) findViewById(R.id.ans82_7);
            Spinner ans82_8 = (Spinner) findViewById(R.id.ans82_8);
            Spinner ans84 = (Spinner) findViewById(R.id.ans84);
            Spinner ans84_1 = (Spinner) findViewById(R.id.ans84_1);
            Spinner ans85 = (Spinner) findViewById(R.id.ans85);
            Spinner ans85_1 = (Spinner) findViewById(R.id.ans85_1);
            Spinner ans86 = (Spinner) findViewById(R.id.ans86);
            Spinner ans86_1 = (Spinner) findViewById(R.id.ans86_1);
            Spinner ans87 = (Spinner) findViewById(R.id.ans87);
            Spinner ans88 = (Spinner) findViewById(R.id.ans88);
            Spinner ans89 = (Spinner) findViewById(R.id.ans89);
            Spinner ans90 = (Spinner) findViewById(R.id.ans90);
            Spinner ans91 = (Spinner) findViewById(R.id.ans91);
            Spinner ans92 = (Spinner) findViewById(R.id.ans92);
            Spinner ans92_1 = (Spinner) findViewById(R.id.ans92_1);
            Spinner ans93 = (Spinner) findViewById(R.id.ans93);
            Spinner ans93_1 = (Spinner) findViewById(R.id.ans93_1);
            Spinner ans94 = (Spinner) findViewById(R.id.ans94);
            Spinner ans94_1 = (Spinner) findViewById(R.id.ans94_1);
            Spinner ans95 = (Spinner) findViewById(R.id.ans95);
            Spinner ans96 = (Spinner) findViewById(R.id.ans96);
            Spinner ans96_1 = (Spinner) findViewById(R.id.ans96_1);
            Spinner ans96_2 = (Spinner) findViewById(R.id.ans96_2);
            Spinner ans96_7 = (Spinner) findViewById(R.id.ans96_7);
            Spinner ans96_8 = (Spinner) findViewById(R.id.ans96_8);
            Spinner ans96_9 = (Spinner) findViewById(R.id.ans96_9);
            Spinner ans97 = (Spinner) findViewById(R.id.ans97);
            Spinner ans98_1 = (Spinner) findViewById(R.id.ans98_1);
            Spinner ans99_1 = (Spinner) findViewById(R.id.ans99_1);
            Spinner ans100_1 = (Spinner) findViewById(R.id.ans100_1);
            Spinner ans101 = (Spinner) findViewById(R.id.ans101);
            Spinner ans102 = (Spinner) findViewById(R.id.ans102);
            Spinner ans103 = (Spinner) findViewById(R.id.ans103);
            Spinner ans104 = (Spinner) findViewById(R.id.ans104);
            Spinner ans105 = (Spinner) findViewById(R.id.ans105);
            Spinner ans106 = (Spinner) findViewById(R.id.ans106);
            Spinner ans107 = (Spinner) findViewById(R.id.ans107);
            Spinner ans108 = (Spinner) findViewById(R.id.ans108);

            EditText ans42other = (EditText) findViewById(R.id.ans42other);
            EditText ans43_1other = (EditText) findViewById(R.id.ans43_1other);
            EditText ans44_1other = (EditText) findViewById(R.id.ans44_1other);
            EditText ans76other = (EditText) findViewById(R.id.ans76other);
            EditText ans79other = (EditText) findViewById(R.id.ans79other);
            EditText ans80other = (EditText) findViewById(R.id.ans80other);
            EditText ans82_2other = (EditText) findViewById(R.id.ans82_2other);
            EditText ans82_3other = (EditText) findViewById(R.id.ans82_3other);
            EditText ans82_4other = (EditText) findViewById(R.id.ans82_4other);
            EditText ans82_6other = (EditText) findViewById(R.id.ans82_6other);
            EditText ans83other = (EditText) findViewById(R.id.ans83other);
            EditText ans84_1other = (EditText) findViewById(R.id.ans84_1other);
            EditText ans85_1other = (EditText) findViewById(R.id.ans85_1other);
            EditText ans86_1other = (EditText) findViewById(R.id.ans86_1other);
            EditText ans95other = (EditText) findViewById(R.id.ans95other);
            EditText ans96_1other = (EditText) findViewById(R.id.ans96_1other);
            EditText ans103other = (EditText) findViewById(R.id.ans103other);
            
            CheckBox ans76a = (CheckBox) findViewById(R.id.ans76a);
            CheckBox ans76b = (CheckBox) findViewById(R.id.ans76b);
            CheckBox ans76c = (CheckBox) findViewById(R.id.ans76c);
            CheckBox ans76d = (CheckBox) findViewById(R.id.ans76d);
            CheckBox ans76e = (CheckBox) findViewById(R.id.ans76e);
            CheckBox ans76f = (CheckBox) findViewById(R.id.ans76f);
            CheckBox ans76g = (CheckBox) findViewById(R.id.ans76g);
            CheckBox ans76h = (CheckBox) findViewById(R.id.ans76h);
            CheckBox ans76i = (CheckBox) findViewById(R.id.ans76i);
            CheckBox ans76j = (CheckBox) findViewById(R.id.ans76j);
            CheckBox ans76k = (CheckBox) findViewById(R.id.ans76k);
            CheckBox ans76l = (CheckBox) findViewById(R.id.ans76l);
            CheckBox ans77a = (CheckBox) findViewById(R.id.ans77a);
            CheckBox ans77b = (CheckBox) findViewById(R.id.ans77b);
            CheckBox ans77c = (CheckBox) findViewById(R.id.ans77c);
            CheckBox ans77d = (CheckBox) findViewById(R.id.ans77d);
            CheckBox ans77e = (CheckBox) findViewById(R.id.ans77e);
            CheckBox ans77f = (CheckBox) findViewById(R.id.ans77f);
            CheckBox ans77g = (CheckBox) findViewById(R.id.ans77g);
            CheckBox ans77h = (CheckBox) findViewById(R.id.ans77h);
            CheckBox ans79a = (CheckBox) findViewById(R.id.ans79a);
            CheckBox ans79b = (CheckBox) findViewById(R.id.ans79b);
            CheckBox ans79c = (CheckBox) findViewById(R.id.ans79c);
            CheckBox ans79d = (CheckBox) findViewById(R.id.ans79d);
            CheckBox ans79e = (CheckBox) findViewById(R.id.ans79e);
            CheckBox ans79f = (CheckBox) findViewById(R.id.ans79f);
            CheckBox ans83a = (CheckBox) findViewById(R.id.ans83a);
            CheckBox ans83b = (CheckBox) findViewById(R.id.ans83b);
            CheckBox ans83c = (CheckBox) findViewById(R.id.ans83c);
            CheckBox ans83d = (CheckBox) findViewById(R.id.ans83d);
            CheckBox ans83e = (CheckBox) findViewById(R.id.ans83e);
            CheckBox ans83f = (CheckBox) findViewById(R.id.ans83f);
            CheckBox ans83g = (CheckBox) findViewById(R.id.ans83g);
            CheckBox ans83h = (CheckBox) findViewById(R.id.ans83h);
            CheckBox ans83i = (CheckBox) findViewById(R.id.ans83i);
            CheckBox ans83j = (CheckBox) findViewById(R.id.ans83j);
            
            FileOutputStream fos = new FileOutputStream(myExternalFile, true);
            for (int i = 0; i < 162; i++) {
                fos.write("\t".getBytes());
            }

            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans42.getSelectedItemPosition()+1).getBytes());
            fos.write((", " + ans42other.getText().toString()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans43.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans43_1.getSelectedItemPosition()).getBytes());
            fos.write((", " + ans43_1other.getText().toString()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans44.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans44_1.getSelectedItemPosition()).getBytes());
            fos.write((", " + ans44_1other.getText().toString()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans45.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans46.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans46_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans47.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans48.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans49.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans50.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans51.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans52.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans52_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans52_2.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans52_3.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans52_4.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans53.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans54a.getText().toString().getBytes());
            fos.write(("," + ans54b.getText().toString()).getBytes());
            fos.write(("," + ans54c.getText().toString()).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans54_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans54_2.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans54_3.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans54_4.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans54_5.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans54_6.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans55.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans56.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans57.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans58.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans59.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans60.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans61.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans62.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans63.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans64.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans64_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans64_2.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans65.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans65_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans66.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans66_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans67.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans67_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans68.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans68_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans69.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans69_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans70.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans70_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans71.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans71_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans72.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans72_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans73.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans73_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans74.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans74_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans75.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            if(ans76a.isChecked()){
                fos.write("1, ".getBytes());
            }
            if(ans76b.isChecked()){
                fos.write("2, ".getBytes());
            }
            if(ans76c.isChecked()){
                fos.write("3, ".getBytes());
            }
            if(ans76d.isChecked()){
                fos.write("4, ".getBytes());
            }
            if(ans76e.isChecked()){
                fos.write("5, ".getBytes());
            }
            if(ans76f.isChecked()){
                fos.write("6, ".getBytes());
            }
            if(ans76g.isChecked()){
                fos.write("7, ".getBytes());
            }
            if(ans76h.isChecked()){
                fos.write("8, ".getBytes());
            }
            if(ans76i.isChecked()){
                fos.write("9, ".getBytes());
            }
            if(ans76j.isChecked()){
                fos.write("10, ".getBytes());
            }
            if(ans76k.isChecked()){
                fos.write("11, ".getBytes());
            }
            if(ans76l.isChecked()){
                fos.write("12".getBytes());
            }
            fos.write((", " + ans76other.getText().toString()).getBytes());
            fos.write("\t".getBytes());
            if(ans77a.isChecked()){
                fos.write("1, ".getBytes());
            }
            if(ans77b.isChecked()){
                fos.write("2, ".getBytes());
            }
            if(ans77c.isChecked()){
                fos.write("3, ".getBytes());
            }
            if(ans77d.isChecked()){
                fos.write("4, ".getBytes());
            }
            if(ans77e.isChecked()){
                fos.write("5, ".getBytes());
            }
            if(ans77f.isChecked()){
                fos.write("6, ".getBytes());
            }
            if(ans77g.isChecked()){
                fos.write("7, ".getBytes());
            }
            if(ans77h.isChecked()){
                fos.write("8".getBytes());
            }
            fos.write("\t".getBytes());
            fos.write(ans77.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans77_1.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans78.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans78_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            if(ans79a.isChecked()){
                fos.write("1, ".getBytes());
            }
            if(ans79b.isChecked()){
                fos.write("2, ".getBytes());
            }
            if(ans79c.isChecked()){
                fos.write("3, ".getBytes());
            }
            if(ans79d.isChecked()){
                fos.write("4, ".getBytes());
            }
            if(ans79e.isChecked()){
                fos.write("5, ".getBytes());
            }
            if(ans79f.isChecked()){
                fos.write("6, ".getBytes());
            }
            fos.write((", " + ans79other.getText().toString()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans79_1.getSelectedItemPosition()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans80.getSelectedItemPosition()+1).getBytes());
            fos.write((", " + ans80other.getText().toString()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans81.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans82.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans82_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans82_2.getSelectedItemPosition()).getBytes());
            fos.write((", " + ans82_2other.getText().toString()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans82_3.getSelectedItemPosition()).getBytes());
            fos.write((", " + ans82_3other.getText().toString()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans82_4.getSelectedItemPosition()).getBytes());
            fos.write((", " + ans82_4other.getText().toString()).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans82_5.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans82_6.getSelectedItemPosition()).getBytes());
            fos.write((", " + ans82_6other.getText().toString()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans82_7.getSelectedItemPosition()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans82_8.getSelectedItemPosition()).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans82_9.getText().toString().getBytes());
            fos.write("\t".getBytes());
            if(ans83a.isChecked()){
                fos.write("1, ".getBytes());
            }
            if(ans83b.isChecked()){
                fos.write("2, ".getBytes());
            }
            if(ans83c.isChecked()){
                fos.write("3, ".getBytes());
            }
            if(ans83d.isChecked()){
                fos.write("4, ".getBytes());
            }
            if(ans83e.isChecked()){
                fos.write("5, ".getBytes());
            }
            if(ans83f.isChecked()){
                fos.write("6, ".getBytes());
            }
            if(ans83g.isChecked()){
                fos.write("7, ".getBytes());
            }
            if(ans83h.isChecked()){
                fos.write("8, ".getBytes());
            }
            if(ans83i.isChecked()){
                fos.write("9, ".getBytes());
            }
            if(ans83j.isChecked()){
                fos.write("10, ".getBytes());
            }
            fos.write((", " + ans83other.getText().toString()).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans83_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans83_2.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans83_3.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans83_4.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans83_5.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans83_6.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans83_7.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans83_8.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans83_9.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans83_10.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans84.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans84_1.getSelectedItemPosition()).getBytes());
            fos.write((", " + ans84_1other.getText().toString()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans85.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans85_1.getSelectedItemPosition()).getBytes());
            fos.write((", " + ans85_1other.getText().toString()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans86.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans86_1.getSelectedItemPosition()).getBytes());
            fos.write((", " + ans86_1other.getText().toString()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans87.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans87_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans88.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans89.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans89_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans90.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans91.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans92.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans92_1.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans93.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans93_1.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans94.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans94_1.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans95.getSelectedItemPosition()).getBytes());
            fos.write((", " + ans95other.getText().toString()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans96.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans96_1.getSelectedItemPosition()).getBytes());
            fos.write((", " + ans96_1other.getText().toString()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans96_2.getSelectedItemPosition()).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans96_3.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans96_4.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans96_5.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans96_6.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans96_7.getItemAtPosition(ans96_7.getSelectedItemPosition()).toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans96_8.getItemAtPosition(ans96_8.getSelectedItemPosition()).toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans96_9.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans97.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans97_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans98.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans98_1.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans98_2.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans99.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans99_1.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans99_2.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(ans100.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans100_1.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans100_2.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans101.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans102.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans103.getSelectedItemPosition()+1).getBytes());
            fos.write((", " + ans103other.getText().toString()).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans104.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans105.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(ans105_1.getText().toString().getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans106.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans107.getSelectedItemPosition()+1).getBytes());
            fos.write("\t".getBytes());
            fos.write(Integer.toString(ans108.getSelectedItemPosition()+1).getBytes());

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}