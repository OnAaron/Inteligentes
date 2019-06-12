package intel.pract;

import jade.core.behaviours.CyclicBehaviour;
import java.io.*;
import java.util.Scanner;

import jade.core.Agent;

public class Agente_Cliente extends Agent{

	private static final long serialVersionUID = 1L;

	public void setup() {
		System.out.println("Soy el agente Cliente");
		addBehaviour(new CyclicBehaviour(this){
			private static final long serialVersionUID = 1L;

			public void action() {
				Scanner scanner=new Scanner(System.in);
				System.out.print("Introduzca el fichero a buscar: ");
				String temp=scanner.nextLine();
				String content = getContenido(temp);
				System.out.println(content);
				Comunicaciones.enviarMensaje(this.myAgent, "busqueda", content);
			}
		});
	}
	public String getContenido(String file) {
		try {
			File archivo;
			FileReader fr;
			BufferedReader br;
			String content = "";
			archivo = new File(file);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			while((linea=br.readLine())!=null)
				content += linea;
			br.close();
			return content;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}