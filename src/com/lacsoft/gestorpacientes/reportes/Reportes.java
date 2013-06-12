package com.lacsoft.gestorpacientes.reportes;

import java.sql.Connection;
import java.util.ArrayList;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import com.lacsoft.gestorpacientes.entidades.Receta;
import com.lacsoft.gestorpacientes.entidades.ResultadoDeLaboratorio;
import com.lacsoft.gestorpacientes.persistencia.Conexion;

public class Reportes {

	private JasperPrint jasperPrint;
	private JasperReport reporte;
	private ArrayList<ResultadoDeLaboratorio> resultados = new ArrayList<ResultadoDeLaboratorio>();
	private ArrayList<Receta> recetas = new ArrayList<Receta>();
	private Connection conexion = Conexion.getInstancia().crearConexion();

	public void reportadorResultadosDeLaboratorio(ResultadoDeLaboratorio resul) {
		try {
			resultados.add(resul);
			reporte = (JasperReport) JRLoader
					.loadObjectFromFile("reportes/ResultadosPruebasLaboratorio.jasper");
			jasperPrint = JasperFillManager.fillReport(reporte, null,
					new JRBeanCollectionDataSource(resultados));
			JasperViewer.viewReport(jasperPrint, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	public void reportadorDeReceta(Receta receta) {
		try {
			recetas.add(receta);
			reporte = (JasperReport) JRLoader
					.loadObjectFromFile("reportes/RecetasPacientes.jasper");
			jasperPrint = JasperFillManager.fillReport(reporte, null,
					new JRBeanCollectionDataSource(recetas));
			JasperViewer.viewReport(jasperPrint, false);
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

	public void GenerarReportes(String archivo) {
		try {
			reporte = (JasperReport) JRLoader.loadObjectFromFile(archivo);
			jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);
			JasperViewer.viewReport(jasperPrint, false);
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

}
