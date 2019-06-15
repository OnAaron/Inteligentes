package intel.pract;

import jade.core.behaviours.CyclicBehaviour;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import intel.pract.Comunicaciones;
import jade.core.Agent;

public class Agente_Cliente extends Agent{

	private static final long serialVersionUID = 1L;
	private Integer[] opciones = {1,2,3,4};

	public void setup() {
		System.out.println("Soy el agente Cliente");
		addBehaviour(new CyclicBehaviour(this){
			private static final long serialVersionUID = 1L;

			public void action() {
				Scanner scanner=new Scanner(System.in);
				System.out.print("Introduzca el fichero a buscar: ");
				String temp=scanner.nextLine();
				String content = getContenido(temp);
				if (content != null) {
					System.out.print("Introduzca el patrón a buscar a buscar: ");
					String patron = scanner.nextLine();
					//System.out.println(content);
					System.out.println("Elija una de las opciones de búsqueda:");
					System.out.println("1.- Búsqueda por palabra no completa y no coinciden mayúsculas");
					System.out.println("2.- Búsqueda por palabra no completa y coinciden mayúsculas");
					System.out.println("3.- Búsqueda por palabra completa y no coinciden mayúsculas");
					System.out.println("4.- Búsqueda por palabra completa y coinciden mayúsculas");
					String modo = scanner.nextLine();
					if(!Arrays.asList(opciones).contains(Integer.parseInt(modo))) {
						System.out.println("Opción incorrecta, elija una opcion entre 1, 2, 3 o 4.");
					}
					else {
						Comunicaciones.enviarMensaje(this.myAgent, "busqueda", content);
						Comunicaciones.enviarMensaje(this.myAgent, "busqueda", patron);
						Comunicaciones.enviarMensaje(this.myAgent, "busqueda", modo);
					}
				}
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
				content += linea+"\n";
			br.close();
			return content;
		}
		catch(Exception e) {
			System.out.println("Fichero no existente");
		}
		return null;
	}
}