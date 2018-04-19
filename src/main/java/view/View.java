package view;

import java.util.ArrayList;
import java.util.HashMap;

public class View {

	


	
	
public  void mostrarDatosComAutonomasProvinciasHabitantes(ArrayList<ArrayList<String>> comAutoProvYPadron ) {
		int subTotal = 0;
		int total = 0;
		int indiceCA = 1;
		int indiceProv = 0;
		
		System.out.println("\r\r***************************************************");
		System.out.println("******Comunidades Autónomas y sus Provincias*******");
		System.out.println("***************************************************\r");
		

		while (indiceCA <20) {
			
			System.out.println("\u001B[47m\u001B[32m----------   \t" + comAutoProvYPadron.get(indiceProv).get(1) + "   \t----------\u001b[0m");
			while (indiceCA == Integer.parseInt(comAutoProvYPadron.get(indiceProv).get(0))) {
				
				//System.out.println(comAutoProvYPadron.get(indiceProv).get(2) + " -\t " + comAutoProvYPadron.get(indiceProv).get(3) + " -\t " + comAutoProvYPadron.get(indiceProv).get(4));
				System.out.printf("%-5s", comAutoProvYPadron.get(indiceProv).get(2) + "");
				System.out.printf("%-25s", comAutoProvYPadron.get(indiceProv).get(3) + "");
				System.out.printf("%-5s", comAutoProvYPadron.get(indiceProv).get(4) + "");
				System.out.println();
				subTotal += Integer.parseInt(comAutoProvYPadron.get(indiceProv).get(4));
				indiceProv++;
			}
			
			System.out.println("\u001B[36mTOTAL de habitantes de " + comAutoProvYPadron.get(indiceProv-1).get(1) + " = \u001B[41m\u001B[37m" + subTotal +  "\u001b[0m");

			System.out.println();
			total += subTotal;
			subTotal = 0;
			
			indiceCA++;
		}
			
		
		System.out.println("\u001B[33mTOTAL de habitantes de España = \u001b[0m" + total);
	}


	
}
