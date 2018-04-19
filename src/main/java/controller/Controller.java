package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Model;
import view.View;

public class Controller {
	
	private Model model;
	private View view;

	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		
	}
	
	
	public void consultaPadronCAProv(Model myModel, View myView, String sql) {
		myModel.connectToBD();
		sql = " \r\n" + 
				"select CA as Comunidad, provincia, sum(padron) as padron from padron pa inner join provincias p1, comunidadesautonomas c1, municipios m1 where pa.CodMunicipio \r\n" + 
				"= m1.CodMunicipio and m1.CodProvincia = p1.CodProvincia and p1.CodCA = c1.CodCA group by p1.CodProvincia order by c1.CA, p1.Provincia";
		ResultSet rs = myModel.readOnBD(myModel.getDbName(), sql);
		String anteriorCA = "0" ;
		int subTotal = 0;
		int total = 0;
		try {
			while(rs.next()) {
				
				if (!(anteriorCA.equals(rs.getString(1))) ) {				
					if (subTotal != 0) {
						System.out.println("SubTotal: " + subTotal + " de " + anteriorCA);
					}
					
					System.out.println("\nCOMUNIDAD AUTONOMA: " + rs.getString(1));
					anteriorCA = rs.getString(1);
					total += subTotal;
					subTotal = 0;
				}
					
					subTotal += rs.getInt(3);
					
					System.out.println(rs.getString(2) + " - " + rs.getString(3));
					
			}
			
			System.out.println("España: " + (total + subTotal));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public void solictarConsulta(Model myModel, View myView, String sql) {
		
		myModel.connectToBD();
		ResultSet resultset = myModel.readOnBD(myModel.getDbName(), sql);
		ArrayList<ArrayList<String>> datosConsulta = new ArrayList<ArrayList<String>>();
		
		try {

			int numColumnas = resultset.getMetaData().getColumnCount();
			while(resultset.next()) {
				ArrayList<String> unRegistro = new ArrayList<String>();
				//Recorro las columnas de un registro
				for (int i = 1; i <= numColumnas; i++) {
					unRegistro.add(resultset.getString(i));
					//System.out.println(resultset.getString(i));
				}
				//añado el registro con todos los datos a la lista que contendrá todas las filas con datos.
				datosConsulta.add(unRegistro);
			}	
			System.out.println(datosConsulta.toString());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myView.mostrarDatosComAutonomasProvinciasHabitantes(datosConsulta);
		
	}

}
