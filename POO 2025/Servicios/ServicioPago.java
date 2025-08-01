package Servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServicioPago {
    // Mapa: ID usuario -> lista de pagos (monto)
    private final Map<Integer, ArrayList<Double>> pagos;

    public ServicioPago() {
        this.pagos = new HashMap<>();
    }

    public void registrarPago(int idUsuario, double monto) {
        pagos.putIfAbsent(idUsuario, new ArrayList<>());
        pagos.get(idUsuario).add(monto);
    }

    public ArrayList<Double> getPagos(int idUsuario) {
        return pagos.getOrDefault(idUsuario, new ArrayList<>());
    }

    public double getTotalPagado(int idUsuario) {
        ArrayList<Double> lista = getPagos(idUsuario);
        double total = 0;
        for (Double d : lista) {
            total += d;
        }
        return total;
    }
}
