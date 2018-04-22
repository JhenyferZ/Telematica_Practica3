//Univ. Jhenny Zara Huanca Ticona   Docente: Lic. Gallardo

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
	
	//Declaramos las variables
	
	Socket sCliente;
	Scanner entrada;
	PrintStream salida;
	String host;
	int puerto;
	String mensajeSolicitud = "";
	String mensajeRespuesta= "";
	private Scanner lectura;
	
	//El constructor con el puerto y el host que le daremos desde el Main
	public TCPClient (String h,int p) {
		host = h;
		puerto = p;
	}
	
	//Con éste método iniciamos la conexión con el servidor
	public void iniciar(){
		try {
			sCliente = new Socket(host,puerto);
			//Mostramos el mensaje "Conectado a: " y la dirección del socket  
			System.out.println("Conectado a: " + sCliente.getRemoteSocketAddress());
			
			//Obtenemos una referencia a los flujos de datos de entrada y salida
			salida = new PrintStream(sCliente.getOutputStream());
			entrada = new Scanner(sCliente.getInputStream());
			
			//Pedimos el número que procesaremos en el servidor
			lectura = new Scanner(System.in);
			System.out.print("\nIngresa un número :");
			
			//Ingresamos el número y lo mandamos a procesar
			mensajeSolicitud = lectura.nextLine();
			salida.println(mensajeSolicitud);
			
			//Recibimos el mensaje respuesta del servidor 
			mensajeRespuesta = entrada.nextLine();
			
			//Mostramos el resultado enviado del servidor
			System.out.println("Respuesta del servidor: "+ mensajeRespuesta);
	
		} catch (Exception e) {
			e.printStackTrace();
			finalizar();
		}
	}
	
	//Cerramos todos los flujos abiertos con .close() y finalizamos la conexión
	public void finalizar(){
		try {
			salida.close();
			entrada.close();
			sCliente.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
