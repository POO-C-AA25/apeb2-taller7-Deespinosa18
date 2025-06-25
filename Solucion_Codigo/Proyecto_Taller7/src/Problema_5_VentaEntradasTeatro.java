
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Diego
 */
public class Problema_5_VentaEntradasTeatro {

    public static void main(String[] args) {
        ArrayList<Zona> zonas = new ArrayList<>();
        zonas.add(new Zona(25, 17.5, "VIP", 200));
        zonas.add(new Zona(70, 40, "Box", 40));
        zonas.add(new Zona(20, 14, "Preferencia", 400));
        zonas.add(new Zona(15.5, 10, "General", 100));

        int seguir = 0;
        int tipoEntrada;
        int idBuscado;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Entrada> entradasVendidas = new ArrayList<>();

        while (seguir == 0) {
            System.out.println("\n--- SISTEMA DE ENTRADAS TEATRO ---");
            System.out.println("[1]. Vender entrada");
            System.out.println("[2]. Buscar entrada por ID");
            System.out.println("[3]. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("\nSeleccione zona:");
                    for (int i = 0; i < zonas.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " + zonas.get(i).nombreZona);
                    }

                    int opcionZona = scanner.nextInt();
                    Zona zonaElegida = zonas.get(opcionZona - 1);

                    if (zonaElegida.tieneEspacio()) {
                        System.out.print("Nombre del comprador: ");
                        String nombre = scanner.next();

                        System.out.println("Tipo de entrada:");
                        System.out.println("[1] Normal");
                        System.out.println("[2] Miembro");
                        System.out.println("[3] Reducida");

                        tipoEntrada = scanner.nextInt();
                        Entrada nuevaEntrada = null;

                        switch (tipoEntrada) {
                            case 1:
                                nuevaEntrada = new Normal(zonaElegida, nombre);
                                break;
                            case 2:
                                nuevaEntrada = new Miembro(zonaElegida, nombre);
                                break;
                            case 3:
                                nuevaEntrada = new Reducida(zonaElegida, nombre);
                                break;
                            default:
                                System.out.println("Tipo no válido.");
                        }

                        if (nuevaEntrada != null) {
                            zonaElegida.restarLugar();
                            nuevaEntrada.calcularPrecio();
                            nuevaEntrada.generarId();
                            entradasVendidas.add(nuevaEntrada);
                            System.out.println("\nEntrada generada: " + nuevaEntrada);
                        }
                    } else {
                        System.out.println("No quedan lugares disponibles en esta zona.");
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el ID de la entrada: ");
                    idBuscado = scanner.nextInt();
                    boolean encontrado = false;

                    for (Entrada e : entradasVendidas) {
                        if (e.idEntrada == idBuscado) {
                            System.out.println("\n>> Comprador: " + e.nombreCliente);
                            System.out.println(">> Precio: $" + e.precioFinal);
                            System.out.println(">> Zona: " + e.sector.nombreZona + "\n");
                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("No se encontró ninguna entrada con ese ID.");
                    }
                    break;

                case 3:
                    seguir = 1;
                    System.out.println("\u00a1Hasta luego!");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}

class Zona {

    public double tarifaBase;
    public double tarifaMiembro;
    public String nombreZona;
    public int lugaresDisponibles;

    public Zona(double tarifaBase, double tarifaMiembro, String nombreZona, int lugaresDisponibles) {
        this.tarifaBase = tarifaBase;
        this.tarifaMiembro = tarifaMiembro;
        this.nombreZona = nombreZona;
        this.lugaresDisponibles = lugaresDisponibles;
    }

    public boolean tieneEspacio() {
        return this.lugaresDisponibles > 0;
    }

    public void restarLugar() {
        this.lugaresDisponibles--;
    }
}

class Entrada {

    public Zona sector;
    public int idEntrada;
    public String nombreCliente;
    public double precioFinal;

    public Entrada(Zona sector, String nombreCliente) {
        this.sector = sector;
        this.nombreCliente = nombreCliente;
    }

    public double calcularPrecio() {
        this.precioFinal = this.sector.tarifaBase;
        return precioFinal;
    }

    public void generarId() {
        Random random = new Random();
        this.idEntrada = 10000 + random.nextInt(90000);
    }

    @Override
    public String toString() {
        return "Entrada{" + "ID=" + idEntrada + ", Precio=$" + precioFinal + '}';
    }
}

class Normal extends Entrada {

    public Normal(Zona sector, String nombreCliente) {
        super(sector, nombreCliente);
    }
}

class Miembro extends Entrada {

    public Miembro(Zona sector, String nombreCliente) {
        super(sector, nombreCliente);
    }

    @Override
    public double calcularPrecio() {
        this.precioFinal = this.sector.tarifaMiembro;
        return precioFinal;
    }
}

class Reducida extends Entrada {

    public Reducida(Zona sector, String nombreCliente) {
        super(sector, nombreCliente);
    }

    @Override
    public double calcularPrecio() {
        this.precioFinal = super.calcularPrecio() * 0.85;
        return precioFinal;
    }
}
