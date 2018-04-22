//Univ. Jhenny Zara Huanca Ticona   Docente: Lic. Gallardo

public class MainTCPServer {

	//Instanciamos el objeto TCPServer y le mandamos el host y puerto requeridos en los constructores
	public static void main(String[] args) {
		TCPServer Server = new TCPServer(8888);
		
		//Iniciamos el servidor para empezar a recibir clientes
		Server.iniciar();
	}

}
