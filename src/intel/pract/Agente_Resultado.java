package intel.pract;

import java.util.Scanner;
import jade.core.Agent;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Agente_Resultado extends Agent{
	public void setup() {
		System.out.println("Soy el agente Cliente");
		addBehaviour(new CyclicBehaviour(this){
			public void action() {
				Scanner scanner=new Scanner(System.in);
				System.out.print("Introduzca el texto a buscar: ");
				String temp=scanner.nextLine();
				ACLMessage msg=blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
			}
		});
	}
}
