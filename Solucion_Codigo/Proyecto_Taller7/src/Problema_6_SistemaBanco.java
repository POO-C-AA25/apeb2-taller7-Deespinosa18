/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Diego
 */
public class Problema_6_SistemaBanco {
       public static void main(String[] args) {
        CuentaCheques cuentaCheckings = new CuentaCheques("1000", "Tachito");
        CuentaAhorros cuentaSaves = new CuentaAhorros("300", "Diego", 5);
        CuentaPlatino cuentaPlatino = new CuentaPlatino("450", "Antonio");

        cuentaCheckings.depositar(1500);
        cuentaCheckings.retirar(500);
        System.out.println("Cuenta Cheques: " + cuentaCheckings.toString());

        cuentaSaves.depositar(2000);
        cuentaSaves.calcularInteres();
        cuentaSaves.retirar(500);
        System.out.println("Cuenta Ahorros: " + cuentaSaves.toString());

        cuentaPlatino.depositar(3000);
        cuentaPlatino.calcularInteres();
        cuentaPlatino.retirar(500);
        System.out.println("Cuenta Platino: " + cuentaPlatino.toString());

    }
}

class CuentaBancaria {

    public String numeroC;
    public String nombreC;
    public double saldo;

    public CuentaBancaria(String numeroCuenta, String nombreCliente) {
        this.numeroC = numeroCuenta;
        this.nombreC = nombreCliente;
        this.saldo = 0.0;
    }

    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
        }
    }

    public void retirar(double cantidad) {
        if (cantidad > 0) {
            saldo -= cantidad;
        }
    }

    @Override
    public String toString() {
        return "Cuenta Bancaria{" + "numero Cuenta =" + numeroC + ", nombre Client e=" + nombreC + ", saldo =" + saldo + '}';
    }

}

class CuentaCheques extends CuentaBancaria {

    public CuentaCheques(String numeroC, String nombreC) {
        super(numeroC, nombreC);
    }

}

class CuentaAhorros extends CuentaBancaria {

    public double interes;

    public CuentaAhorros(String numeroC, String nombreC, double tasaInteres) {
        super(numeroC, nombreC);
        this.interes = tasaInteres;
    }

    public void calcularInteres() {
        saldo += saldo * interes / 100;
    }

    @Override
    public void retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
        }
    }
}

class CuentaPlatino extends CuentaBancaria {

    public double interes = 10.0;

    public CuentaPlatino(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }

    public void calcularInteres() {
        saldo += saldo * interes / 100;
    }

}
