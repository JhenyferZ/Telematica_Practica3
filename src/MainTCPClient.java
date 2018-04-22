//Univ. Jhenny Zara Huanca Ticona   Docente: Lic. Gallardo

public class MainTCPClient {
	
	//Instanciamos el objeto TCPClient y le mandamos el host y puerto requeridos en los constructores
	public static void main(String[] args) {
		TCPClient C = new TCPClient("127.0.0.1", 8888);
		
		//Damos inicio al proceso que tendrá el Cliente
		C.iniciar();
	}

}
