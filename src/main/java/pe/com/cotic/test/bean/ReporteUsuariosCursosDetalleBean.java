package pe.com.cotic.test.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.PieChartModel;

import pe.com.cotic.test.dao.ReporteUsuariosCursosPuestosDao;
import pe.com.cotic.test.daoImpl.ReporteUsuariosCursosPuestosDaoImpl;
import pe.com.cotic.test.modelo.Reportecursodetalle;
import pe.com.cotic.test.modelo.Reporteusuarioscursospuestos;

@ManagedBean
@SessionScoped
public class ReporteUsuariosCursosDetalleBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Reportecursodetalle reportecursodetalle;
	private ReporteUsuariosCursosDetalleBean reporteusuarioscursosdetalle;
	private ReporteUsuariosCursosPuestosDao reporteusuarioscursospuestosDao;
	private List<Reportecursodetalle> listarReportecursosdetalle;
	private Reportecursodetalle selectedReportecursodetalle;
	
	private PieChartModel model;
	private BarChartModel barModel;	

	public ReporteUsuariosCursosDetalleBean() {
		reportecursodetalle = new Reportecursodetalle(); 
		this.reporteusuarioscursospuestosDao = new ReporteUsuariosCursosPuestosDaoImpl();
		if (this.reportecursodetalle == null) { 
			this.reportecursodetalle = new Reportecursodetalle(); 
		}
		this.listarReportecursosdetalle = new ArrayList<Reportecursodetalle>();
		this.selectedReportecursodetalle = new Reportecursodetalle();
	}

	public Reportecursodetalle getReportecursodetalle() {
		return reportecursodetalle;
	}

	public void setReportecursodetalle(Reportecursodetalle reportecursodetalle) {
		this.reportecursodetalle = reportecursodetalle;
	}

	public ReporteUsuariosCursosDetalleBean getReporteusuarioscursosdetalle() {
		return reporteusuarioscursosdetalle;
	}

	public void setReporteusuarioscursosdetalle(
			ReporteUsuariosCursosDetalleBean reporteusuarioscursosdetalle) {
		this.reporteusuarioscursosdetalle = reporteusuarioscursosdetalle;
	}

	public ReporteUsuariosCursosPuestosDao getReporteusuarioscursospuestosDao() {
		return reporteusuarioscursospuestosDao;
	}

	public void setReporteusuarioscursospuestosDao(
			ReporteUsuariosCursosPuestosDao reporteusuarioscursospuestosDao) {
		this.reporteusuarioscursospuestosDao = reporteusuarioscursospuestosDao;
	}

	public List<Reportecursodetalle> getListarReportecursosdetalle() {
		return listarReportecursosdetalle;
	}

	public void setListarReportecursosdetalle(
			List<Reportecursodetalle> listarReportecursosdetalle) {
		this.listarReportecursosdetalle = listarReportecursosdetalle;
	}

	public Reportecursodetalle getSelectedReportecursodetalle() {
		return selectedReportecursodetalle;
	}

	public void setSelectedReportecursodetalle(
			Reportecursodetalle selectedReportecursodetalle) {
		this.selectedReportecursodetalle = selectedReportecursodetalle;
	}
	
	public PieChartModel getModel() {
		return model;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void listarCursoCuestionario(Reporteusuarioscursospuestos reporteusuariocursospuestos) {
		List<Reportecursodetalle> lista;
		List<Reportecursodetalle> lista2;
		int codigoUsuario = reporteusuariocursospuestos.getCodigoUsuario();
		try {
			ReporteUsuariosCursosPuestosDao reporteusuarioscursospuestosDao = new ReporteUsuariosCursosPuestosDaoImpl();
			lista = reporteusuarioscursospuestosDao.ListarReporteUsuariosCursosDetalle(codigoUsuario);
			lista2 = lista;
			graficarCursoCuestionario(lista);
			graficarCursoCuestionarioBar(lista2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			
		}		
	}
	
	private void graficarCursoCuestionario(List<Reportecursodetalle> lista) {
		model = new PieChartModel();
		int correctas=0, incorrectas=0, nocontestadas=0;
		for (Reportecursodetalle rr : lista) {
			correctas+=rr.getCorrectas();
			incorrectas+=rr.getIncorrectas();
			nocontestadas+=rr.getNocontestadas();
			//model.set(rr.getPortafolio().toString(), rr.getIntentos());
		}
		model.set("Correctas", correctas);
		model.set("Incorrectas", incorrectas);
		model.set("No Contestadas", nocontestadas);

		/*model.setTitle("Preguntas");*/
		model.setLegendPosition("s");
		model.setFill(true);
		model.setShowDataLabels(true);
		model.setDiameter(150);
		
	}
	
	private void graficarCursoCuestionarioBar(List<Reportecursodetalle> lista2) {
		barModel = new BarChartModel();

        ChartSeries correctas = new ChartSeries();
        correctas.setLabel("Correctas");                    
        for (Reportecursodetalle rr : lista2) {
        	double totalPreguntas = (rr.getCorrectas()+ rr.getIncorrectas()+rr.getNocontestadas());
        	double totalCorrectas = rr.getCorrectas();
        	double porcentajeCorrectas = (totalCorrectas / totalPreguntas) * 100 ;        	
        	correctas.set(rr.getPortafolio().getTituloPortafolio(),porcentajeCorrectas);
        }
 
        ChartSeries incorrectas = new ChartSeries();
        incorrectas.setLabel("Incorrectas");        
        for (Reportecursodetalle rr : lista2) {
        	double totalPreguntas = (rr.getCorrectas()+ rr.getIncorrectas()+rr.getNocontestadas());
        	double totalIncorrectas = rr.getIncorrectas();
        	double porcentajeIncorrectas = (totalIncorrectas / totalPreguntas) * 100 ;
        	incorrectas.set(rr.getPortafolio().getTituloPortafolio(),porcentajeIncorrectas);
        }
 
        ChartSeries nocontestadas = new ChartSeries();
        nocontestadas.setLabel("No Contestadas");
        for (Reportecursodetalle rr : lista2) {
        	double totalPreguntas = (rr.getCorrectas()+ rr.getIncorrectas()+rr.getNocontestadas());
        	double totalNocontestadas = rr.getNocontestadas();
        	double porcentajeNocontestadas = (totalNocontestadas / totalPreguntas) * 100 ;
        	nocontestadas.set(rr.getPortafolio().getTituloPortafolio(),porcentajeNocontestadas);
        }
        
        barModel.addSeries(correctas);
        barModel.addSeries(incorrectas);
        barModel.addSeries(nocontestadas);
		
        //barModel.setTitle("Bar Chart");
        //barModel.setLegendPosition("ne");
        //barModel.setLegendPosition("ne");
        //barModel.setLegendPlacement(LegendPlacement.OUTSIDE);              
        barModel.setSeriesColors("00FF00, FF0000, BDBDBD, 0174DF");
        //barModel.setLegendRows(3);
        barModel.setStacked(true);
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Desempeño");
        xAxis.setTickAngle(-50);
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Preguntas (%)");
        yAxis.setMin(0);
        yAxis.setMax(100);
	}


	public List<Reportecursodetalle> btnBuscarReporteCursoDetalle(Reporteusuarioscursospuestos reporteusuariocursospuestos) {
		ReporteUsuariosCursosPuestosDao reporteusuarioscursospuestosDao = new ReporteUsuariosCursosPuestosDaoImpl();
		String msg;
		int codigoUsuario = reporteusuariocursospuestos.getCodigoUsuario();
		
		this.listarReportecursosdetalle = reporteusuarioscursospuestosDao.ListarReporteUsuariosCursosDetalle(codigoUsuario);
				
		if (this.listarReportecursosdetalle != null) {
			msg = "Se muestra correctamente el listado...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
			listarCursoCuestionario(reporteusuariocursospuestos);
		} else {
			msg = "Error al mostrar el listado...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		return listarReportecursosdetalle;
		
	}
	
	
	public List<Reportecursodetalle> btnBuscarReporteCursoDetalleUnico(Integer codigoUsuario, Integer codigoCurso) {
		ReporteUsuariosCursosPuestosDao reporteusuarioscursospuestosDao = new ReporteUsuariosCursosPuestosDaoImpl();
		String msg;
		
		this.listarReportecursosdetalle = reporteusuarioscursospuestosDao.ListarReporteUsuariosCursosDetalleUnico(codigoUsuario, codigoCurso);
				
		if (this.listarReportecursosdetalle != null) {
			/*msg = "Se muestra correctamente el detalle del curso...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);*/
			//listarCursoCuestionario(reporteusuariocursospuestos);
		} else {
			/*msg = "Error al mostrar el detalle del curso...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);*/
		}

		return listarReportecursosdetalle;
		
	}
}
