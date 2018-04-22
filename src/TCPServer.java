//Univ. Jhenny Zara Huanca Ticona   Docente: Lic. Gallardo

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer {
	//Inicializamos las variables 
	
	ServerSocket sServidor;
	Socket sCliente;
	int puerto;
	PrintStream salida;
	Scanner entrada;
	String mensajeSolicitud = "";
	String mensajeRespuesta = "";
	
	//Constructor con el puerto que ingresaremos al instanciar el objeto
	public TCPServer(int p){
		puerto = p;
	}
	
	/*Con �ste m�todo iniciamos el servidor lanzando el mensaje de "Esperando Cliente -" 
	ya que est� en estado de espera*/
	public void iniciar(){
		try {
			/*Todo el codigo lo ponemos dentro de �ste try para cuidar las exceptions*/
			
			sServidor = new ServerSocket(puerto);
			System.out.println("- Esperando Cliente -");
			boolean sw = true;
			
			//Esperamos al cliente si es que llega est� en true
			while(sw){
				// Si logra entrar el cliente es aceptado
				sCliente = sServidor.accept();
				entrada = new Scanner(sCliente.getInputStream());
				salida = new PrintStream(sCliente.getOutputStream());
				mensajeSolicitud = entrada.nextLine();
				
				//Aqu� condicionamos el estado del servidor es decir: si el cliente es igual a " " cerrar� se conexi�n
				if(mensajeSolicitud.equals(" "))
				{
					sw = false;
					System.out.println("Cerrando conexi�n...");
					mensajeRespuesta = "El servidor ha cerrado su conexi�n";
					salida.println(mensajeRespuesta);
				}
				else
				{
				//Si el cliente no es " " entonces procesaremos la solicitud
					System.out.println("Solicitud del Cliente :"+ mensajeSolicitud );
					
					/*Llamamos a la funci�n esPrimo(int a) el cu�l procesar� nuestra solicitud
					Si es primo o no es primo imprimira los mensajes correspondientes*/
					if(esPrimo(Integer.parseInt(mensajeSolicitud)))
					{
						mensajeRespuesta = "El n�mero "+ mensajeSolicitud + " es primo";
						salida.println(mensajeRespuesta);
					}
					else
					{
						mensajeRespuesta = "El n�mero "+ mensajeSolicitud + " no es primo";
						salida.println(mensajeRespuesta);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			finalizar();
		}
		finally{
			finalizar();
		}
	}
	
	//Funci�n d�nde procesaremos el n�mero ingresado si es primo o no y nos devolver� True o False
	public boolean esPrimo(int numero)
	{
		int contador = 0;
		boolean sw = false;
		for(int i = 1; i<= numero ; i++)
		{
			if(numero % i == 0)
			{
				contador++;
			}
		}
		if(contador <= 2)
		{
			sw = true;
		}
		return sw;
	}
	
	//Con �ste m�todo cerramos todos los flujos abiertos con .close()
	public void finalizar(){
		try {
			salida.close();
			entrada.close();
			sServidor.close();
			sCliente.close();
			System.out.println("Conexion Finalizada...");
		} catch (Exception e) {
			// Imprimir� error en caso de haber error
			e.printStackTrace();
		}
	}
}
