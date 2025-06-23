
/**
 * Implemente un sistema de envío de mensajes a móviles. Existen dos tipos de mensajes que se pueden enviar entre móviles
 * , mensajes de texto (SMS) y mensajes que contienen imágenes (MMS). Por un lado, los mensajes de texto contienen un mensaje
 * en caracteres que se desea enviar de un móvil a otro. Por otro lado, los mensajes que contienen imágenes almacenan
 * información sobre la imagen a enviar, la cual se representará por el nombre del fichero que la contiene.
 * Independientemente del tipo de mensaje, cada mensaje tendrá asociado un remitente de dicho mensaje y un destinatario.
 * Ambos estarán definidos obligatoriamente por un número de móvil, y opcionalmente se podrá guardar información sobre su nombre.
 * Además, los métodos enviarMensaje y visualizarMensaje deben estar definidos.
 *
 * @author Diego
 */
public class Problema_3_Mensajes {

    public static void main(String[] args) {

        SMS sms = new SMS(989590067, 999482769, "Hola, Diego como estas?.");
        sms.enviarMensaje();
        sms.visualizarMensaje();

        System.out.println();

        MMS mms = new MMS(999482769, 989590067, "foto_vacaciones.jpg");
        mms.enviarMensaje();
        mms.visualizarMensaje();
    }
}

class Mensaje {

    public int remitente;
    public int destinario;

    public Mensaje(int remitente, int destinario) {
        this.remitente = remitente;
        this.destinario = destinario;
    }

    public void enviarMensaje() {
    }

    public void visualizarMensaje() {
    }

}

class SMS extends Mensaje {

    public String mensaje;

    public SMS(int remitente, int destinatario, String mensaje) {
        super(remitente, destinatario);
        this.mensaje = mensaje;
    }

    @Override
    public void enviarMensaje() {
        System.out.println("SMS enviado a " + destinario);
    }

    @Override
    public void visualizarMensaje() {
        System.out.println("SMS de " + remitente + ": " + mensaje);
    }

}

class MMS extends Mensaje {

    public String imagen;

    public MMS(int remitente, int destinatario, String imagen) {
        super(remitente, destinatario);
        this.imagen = imagen;
    }

    public void enviarMensaje() {
        System.out.println("Imagen enviada a " + destinario);
    }

    public void visualizarMensaje() {
        System.out.println("Imagen de " + remitente + ": " + imagen);
    }
}
