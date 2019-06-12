package intel.pract;

import jade.core.behaviours.CyclicBehaviour;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import jade.core.Agent;

public class Agente_Cliente extends Agent{

	private static final long serialVersionUID = 1L;

	public void setup() {
		final File archivo;
		final FileReader fr;
		final BufferedReader br;
		String content = null;
		System.out.println("Soy el agente Cliente");
		addBehaviour(new CyclicBehaviour(this){
			private static final long serialVersionUID = 1L;

			public void action() {
				try {
				Scanner scanner=new Scanner(System.in);
				System.out.print("Introduzca el fichero a buscar: ");
				String fich = scanner.nextLine();
				archivo = new File(fich);
				String ruta = archivo.getAbsolutePath();
				archivo = new File(ruta+"/"+fich);
				fr = new FileReader(archivo);
				br = new BufferedReader(fr);
				String linea;
				while((linea=br.readLine())!=null)
					content += linea;
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				finally{
			         try{                    
			            if( null != fr ){   
			               fr.close();     
			            }                  
			         }catch (Exception e2){ 
			            e2.printStackTrace();
			         }
			         Comunicaciones.enviarMensaje(this.myAgent, "busqueda", content);
				}
			}
		});
	}
}