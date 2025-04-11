
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class CarrilBiciManager {

    /**
     * Longitud de los tramos. 
    */
    private final Map<String, Double> tramos; // nombre del tramo -> longitud en km
    
    /**
     * Estado de los tramos. 
     */
    private final Map<String, String> estadoTramos; // nombre del tramo -> estado

    /**
     * Inicializamos las variables.
     */
    public CarrilBiciManager() {
        this.tramos = new HashMap<>();
        this.estadoTramos = new HashMap<>();
    }

    /**
     * El método añadirTramo funciona para que el usuario nos proporcione el nombre del nuevo tramo, en caso de que no fuera válido por no haber 
     * escrito nada, se devolverá por teclado que el nombre no puede estar vacío ni ser menor o igual que cero. En caso de que no lo estuviera 
     * el tramo se añadiría sin problema.
     *
     * @param String nombre
     * @param double longitud
     */
    public void añadirTramo(String nombre, double longitud) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del tramo no puede estar vacío");
        }
        if (longitud <= 0) {
            throw new IllegalArgumentException("La longitud debe ser mayor que cero");
        }
        tramos.put(nombre, longitud);
        estadoTramos.put(nombre, "En servicio");
    }

    /**
     * El método actualizarEstado sirve para sobreescribir un estado por otro nuevo.
     *
     * @param String nombre
     * @param String nuevoEstado
     */
    public void actualizarEstado(String nombre, String nuevoEstado) {
        if (!tramos.containsKey(nombre)) {
            throw new NoSuchElementException("El tramo indicado no existe: " + nombre);
        }
        estadoTramos.put(nombre, nuevoEstado);
    }
    
    /**
     * Este método llama al método actualizarEstado.
     *
     * @param String nombre
     * @param String estado
     */
    public void cambiarEstado(String nombre, String estado) {
        actualizarEstado(nombre, estado);
    }

    /**
     * Este método consulta el estado del tramo para verificar si existe o no.
     *
     * @param String nombre
     * @return nombre
     */
    public String consultarEstado(String nombre) {
        if (!estadoTramos.containsKey(nombre)) {
            throw new NoSuchElementException("El tramo indicado no existe");
        }
        return estadoTramos.get(nombre);
    }

    /**
     * Este método calcula la longitud total.
     *
     * @return longitud total
     */
    public double longitudTotal() {
        return tramos.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    /**
     * Este método devuelve todos los tramos que se han guardado.
     *
     * @return tramos
     */
    public Map<String, Double> obtenerTramos() {
        return Collections.unmodifiableMap(tramos);
    }

    /**
     * Este método genera un informe sobre los carriles bici.
     *
     * @return string
     */
    public String generarInforme() {
        StringBuilder sb = new StringBuilder("INFORME DE CARRILES BICI - Bahía de Cádiz\n");
        sb.append("===========================================\n");
        for (String nombre : tramos.keySet()) {
            sb.append("- ").append(nombre).append(" (")
              .append(tramos.get(nombre)).append(" km): ")
              .append(estadoTramos.get(nombre)).append("\n");
        }
        sb.append("Longitud total: ").append(longitudTotal()).append(" km\n");
        return sb.toString();
    }
}
