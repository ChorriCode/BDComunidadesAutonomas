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

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myView.mostrarDatosComAutonomasProvinciasHabitantes(datosConsulta);
		
	}

}
