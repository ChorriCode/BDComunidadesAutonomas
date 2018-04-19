package main;


import controller.Controller;
import model.Model;
import view.View;

public class Main {
	

	public static void main(String[] args) {
		
		Model myModel = new Model(	"jdbc:mysql://localhost:3306/", 
									"paro",
									"com.mysql.jdbc.Driver", 
									"root", 
									"");
		View myView = new View();
		Controller myController = new Controller(myModel, myView);
		String sql = "SELECT provincias.CodCA, comunidadesautonomas.CA, municipios.codProvincia, provincia, sum(padron) from municipios LEFT JOIN padron on municipios.CodMunicipio = padron.CodMunicipio LEFT JOIN provincias on municipios.CodProvincia = provincias.CodProvincia LEFT JOIN comunidadesautonomas on provincias.CodCA = comunidadesautonomas.CodCA GROUP BY CodProvincia, provincia, CA order by CodCA, provincia;";
		
		//myController.consultaPadronCAProv(myModel, myView, sql);
		myController.solictarConsulta(myModel, myView, sql);


		
	}

}
