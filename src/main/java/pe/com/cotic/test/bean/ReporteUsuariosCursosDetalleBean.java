package pe.com.cotic.test.bean;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

import pe.com.cotic.test.dao.ReporteUsuariosCursosPuestosDao;
import pe.com.cotic.test.daoImpl.ReporteUsuariosCursosPuestosDaoImpl;
import pe.com.cotic.test.modelo.Reportecursodetalle;
import pe.com.cotic.test.modelo.Reporteusuarioscursospuestos;

@ManagedBean
@SessionScoped
public class ReporteUsuariosCursosDetalleBean implements Serializable {

	private static final long serialVersionUID = 1L;
	public boolean loggedIn;
	
	private Reportecursodetalle reportecursodetalle;
	private ReporteUsuariosCursosDetalleBean reporteusuarioscursosdetalle;
	private ReporteUsuariosCursosPuestosDao reporteusuarioscursospuestosDao;
	private List<Reportecursodetalle> listarReportecursosdetalle;
	private Reportecursodetalle selectedReportecursodetalle;
	
	private PieChartModel model;
	private BarChartModel barModel;	
	private LineChartModel lineModel;

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
	
	public LineChartModel getLineModel() {
		return lineModel;
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
		//System.out.println("CORRECTAS " + correctas + " INCORRECTAS " + incorrectas + " NOCONTESTADAS " + nocontestadas);
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
        DecimalFormat df = new DecimalFormat("#.00");
        correctas.setLabel("Correctas");                    
        for (Reportecursodetalle rr : lista2) {
        	double totalPreguntas = (rr.getCorrectas()+ rr.getIncorrectas()+rr.getNocontestadas());
        	double totalCorrectas = rr.getCorrectas();
        	double porcentajeCorrectas = (totalCorrectas / totalPreguntas) * 100 ;        	
        	correctas.set(rr.getPortafolio().getTituloPortafolio(),Double.parseDouble(df.format(porcentajeCorrectas).toString().replace(",", ".")));
        	//System.out.println("Para Correctas: Portafolio " + rr.getPortafolio().getTituloPortafolio() + " % " + porcentajeCorrectas);
        }
        
        ChartSeries incorrectas = new ChartSeries();
        incorrectas.setLabel("Incorrectas");        
        for (Reportecursodetalle rr : lista2) {
        	double totalPreguntas = (rr.getCorrectas()+ rr.getIncorrectas()+rr.getNocontestadas());
        	double totalIncorrectas = rr.getIncorrectas();
        	double porcentajeIncorrectas = (totalIncorrectas / totalPreguntas) * 100 ;
        	incorrectas.set(rr.getPortafolio().getTituloPortafolio(),Double.parseDouble(df.format(porcentajeIncorrectas).toString().replace(",", ".")));
        	//System.out.println("Para Incorrectas: Portafolio " + rr.getPortafolio().getTituloPortafolio() + " % " + porcentajeIncorrectas);        	
        }
 
        ChartSeries nocontestadas = new ChartSeries();
        nocontestadas.setLabel("No Contestadas");
        for (Reportecursodetalle rr : lista2) {
        	double totalPreguntas = (rr.getCorrectas()+ rr.getIncorrectas()+rr.getNocontestadas());
        	double totalNocontestadas = rr.getNocontestadas();
        	double porcentajeNocontestadas = (totalNocontestadas / totalPreguntas) * 100 ;
        	nocontestadas.set(rr.getPortafolio().getTituloPortafolio(),Double.parseDouble(df.format(porcentajeNocontestadas).toString().replace(",", ".")));
        	//System.out.println("Para Correctas: NoContestadas " + rr.getPortafolio().getTituloPortafolio() + " % " + porcentajeNocontestadas);        	
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

	private void graficarCursoCuestionarioLine(List<Reportecursodetalle> lista4) {
		LineChartModel model = new LineChartModel();		 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
        int columna = System.getProperty("intentos_evaluados") != null ? Integer.parseInt(System.getProperty("intentos_evaluados")) : lista4.size();
        if (lista4.size() < columna) columna = lista4.size();
        
        for (Reportecursodetalle rr : lista4) {
        	series1.set(columna--,rr.getCorrectas());
        }
        /*series1.set(1, 54.55);
        series1.set(2, 81.82);
        series1.set(3, 72.73);
        series1.set(4, 90.91);*/        
        model.addSeries(series1);
        lineModel = model;		
        //lineModel.setTitle("Linear Chart");
        //lineModel.setLegendPosition("e");
                
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
        
        /*Axis xAxis = lineModel.getAxis(AxisType.X);
        xAxis.setMin(0);
        xAxis.setMin(4);*/
         
        yAxis.setLabel("Desempeño %");
        yAxis.setMin(0);
        yAxis.setMax(100);
	}
	
	
	public List<Reportecursodetalle> btnBuscarReporteCursoDetalle(Reporteusuarioscursospuestos reporteusuariocursospuestos) {
		ReporteUsuariosCursosPuestosDao reporteusuarioscursospuestosDao = new ReporteUsuariosCursosPuestosDaoImpl();
		String msg;
		boolean consultado = true;
		int codigoUsuario = reporteusuariocursospuestos.getCodigoUsuario();
		System.out.println("[Bean] btnBuscarReporteCursoDetalle()");
		//if  ((Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("consultado"))
		this.listarReportecursosdetalle = reporteusuarioscursospuestosDao.ListarReporteUsuariosCursosDetalle(codigoUsuario);

		
		
		if (this.listarReportecursosdetalle != null) {
			/*msg = "Se muestra correctamente el listado...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);*/
			listarCursoCuestionario(reporteusuariocursospuestos);
		} else {
			/*msg = "Error al mostrar el listado...";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);*/
		}

		return listarReportecursosdetalle;
		
	}
	
	
	public List<Reportecursodetalle> btnBuscarReporteCursoDetalleUnico(Integer codigoUsuario, Integer codigoCurso) {
		ReporteUsuariosCursosPuestosDao reporteusuarioscursospuestosDao = new ReporteUsuariosCursosPuestosDaoImpl();		
		this.listarReportecursosdetalle = reporteusuarioscursospuestosDao.ListarReporteUsuariosCursosDetalleUnico(codigoUsuario, codigoCurso);
		List<Reportecursodetalle> lista3;
		List<Reportecursodetalle> lista4 = new ArrayList<Reportecursodetalle>();
		lista3 = this.listarReportecursosdetalle;
		int intentos = System.getProperty("intentos_evaluados") != null ? Integer.parseInt(System.getProperty("intentos_evaluados")) : 4;
		if (lista3.size() < intentos) intentos = lista3.size();
		for (int a=0; a<intentos; a++) {
			Reportecursodetalle rcd = new Reportecursodetalle();
			rcd.setCorrectas(Double.parseDouble(lista3.get(a).getCorrectas().toString()));
			lista4.add(rcd);
		}		
		graficarCursoCuestionarioLine(lista4);		
		
		return listarReportecursosdetalle;
		
	}
	
/*	public boolean verificaConsulta() {
		RequestContext context = RequestContext.getCurrentInstance();
		boolean consulta = (Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedIn");
		return true;
	}*/
}
