
import java.util.ArrayList;

/**
 * Se desea desarrollar un sistema de nómina para los trabajadores de una empresa. Los datos personales de los trabajadores 
 son nombre y apellidos, dirección y DNI. Además, existen diferentes tipos de trabajadores Cada empleado tiene obligatoriamente un jefe 
 (exceptuando los jefes que no tienen ninguno).
Trabajadores:
•	Fijos Mensuales: que cobran una cantidad fija al mes.
•	Comisionistas: cobran un porcentaje fijo por las ventas que han realizado
•	Por Horas: cobran un precio por cada una de las horas que han realizado durante el mes. 
El precio es fijo para las primeras 40 horas y es otro para las horas realizadas a partir de la 40 hora mensual.
•   Jefe: cobra un sueldo fijo (no hay que calcularlo)

 El programa debe permitir dar de alta a trabajadores, así como fijar horas o ventas realizadas e imprimir la nómina correspondiente al final de mes.

 * @author Diego
 */
public class Problema_4_NominaTrabajadores {

    public static void main(String[] args) {
        trabajadorFijo fijo = new trabajadorFijo("Diego Espinosa", "Loja", "1105778714", 1200, "Estuardo");

        trabajadorComisionista comisionista = new trabajadorComisionista("Javier Vinan", "Cuenca"
                , "1122070204", 20,500, 0.35, "Carlos");

        trabajadorPorHora hora = new trabajadorPorHora("Antonio Ojeda", "Quito", "1107871429", 45, 10, 12.5, "Jose");

        Jefe jefe = new Jefe("Paulo Bustamante", "Guayaquil", "0199887766", 3000);

        Empresa.registrarTrabajador(fijo);
        Empresa.registrarTrabajador(comisionista);
        Empresa.registrarTrabajador(hora);
        Empresa.registrarTrabajador(jefe);

        comisionista.calcularSueldo();
        hora.calcularSueldo();

        Empresa.mostrarNomina();
    }
}

class nominaDeTrabajadores {

    public String nombre;
    public String direccion;
    public String dni;

    public nominaDeTrabajadores(String nombre, String direccion, String dni) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.dni = dni;
    }
}

class trabajadorFijo extends nominaDeTrabajadores {

    protected double sueldo;
    public String jefe;

    public trabajadorFijo(String nombre, String direccion, String dni, double sueldo, String jefe) {
        super(nombre, direccion, dni);
        this.sueldo = sueldo;
        this.jefe = jefe;
    }
}

class trabajadorComisionista extends nominaDeTrabajadores {

    protected int ventasRealizadas;
    protected double sueldoF;
    public double porcentajeGanancia;
    public String jefe;

    public trabajadorComisionista(String nombre, String direccion, String dni,
            int ventasRealizadas, double sueldoF, double porcentajeGanancia, String jefe) {
        super(nombre, direccion, dni);
        this.ventasRealizadas = ventasRealizadas;
        this.sueldoF = sueldoF;
        this.porcentajeGanancia = porcentajeGanancia;
        this.jefe = jefe;
    }

    public void calcularSueldo() {
        double sueldoTotal = sueldoF + (ventasRealizadas * porcentajeGanancia);
        System.out.println("Sueldo del comisionista llamado: " + nombre + " " + sueldoTotal + " $");
    }
}

class trabajadorPorHora extends nominaDeTrabajadores {

    protected int horasTrabajadas;
    protected double valorHoraN;
    protected double valorHoraE;
    public String jefe;

    public trabajadorPorHora(String nombre, String direccion, String dni,
            int horasTrabajadas, double valorHoraN, double valorHoraE, String jefe) {
        super(nombre, direccion, dni);
        this.horasTrabajadas = horasTrabajadas;
        this.valorHoraN = valorHoraN;
        this.valorHoraE = valorHoraE;
        this.jefe = jefe;
    }

    public void calcularSueldo() {
        double sueldoTotal;
        if (horasTrabajadas <= 40) {
            sueldoTotal = horasTrabajadas * valorHoraN;
        } else {
            int horasExtras = horasTrabajadas - 40;
            sueldoTotal = (40 * valorHoraN) + (horasExtras * valorHoraE);
        }
        System.out.println("Sueldo del trabajador por hora llamado: " + nombre + " " + sueldoTotal + " $");
    }
}

class Jefe extends nominaDeTrabajadores {

    protected double sueldo;

    public Jefe(String nombre, String direccion, String dni, double sueldo) {
        super(nombre, direccion, dni);
        this.sueldo = sueldo;
    }
}

class Empresa {

    public static ArrayList<nominaDeTrabajadores> trabajadores = new ArrayList<>();

    public static void registrarTrabajador(nominaDeTrabajadores n) {
        trabajadores.add(n);
        System.out.println("Trabajador " + n.nombre + " dado de alta correctamente.\n");
    }

    public static void mostrarNomina() {
        System.out.println("\nLISTADO DE TRABAJADORES:");
        System.out.println("----------------------------------------");
        for (nominaDeTrabajadores t : trabajadores) {
            System.out.println("Nombre: " + t.nombre + ", DNI: " + t.dni );
        }
    }
}
