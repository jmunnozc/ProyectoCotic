package pe.com.cotic.test.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.cotic.test.dao.ReporteCursoDao;
import pe.com.cotic.test.modelo.Portafolio;
import pe.com.cotic.test.modelo.Reportecurso;
import pe.com.cotic.test.modelo.Reportecursodetalle;
import pe.com.cotic.test.modelo.Usuario;
import pe.com.cotic.test.util.HibernateUtil;

public class ReporteCursosDaoImpl implements ReporteCursoDao {

	private Session session;
	
	@Override
	public List<Reportecurso> ListarReporteCursos() {

		List<Reportecurso> listarReporteCursosFiltro1 = null;
		List<Reportecurso> listarReporteCursosFiltro2 = null;
		List<Reportecurso> listarReporteCursosFinal = null;
		Usuario usuario = null;
		int intentosEvaluados = 0;
		
		intentosEvaluados = Integer.parseInt(System.getProperty("intentos_evaluados") != null ? System.getProperty("intentos_evaluados") : "1");
		String administrador = System.getProperty("usuario_administrador") != null ? System.getProperty("usuario_administrador") : "";
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "";
		if (administrador.toUpperCase().equals(usuario.getCorreo().toUpperCase().trim()) ) {
			hql = "SELECT rc.portafolio.codigoPortafolio AS codigoPortafolio, "
					+	" 	(select por.tituloPortafolio from Portafolio AS por where por.codigoPortafolio = rc.portafolio.codigoPortafolio) AS tituloPortafolio, "
					+	" 	count(rc.portafolio.codigoPortafolio) AS intentos "
					+	" FROM Respuestacabecera AS rc "
					+	" WHERE rc.usuario.institucion.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion()					
					+	" GROUP BY rc.portafolio.codigoPortafolio";
		} else {
			hql = "SELECT rc.portafolio.codigoPortafolio AS codigoPortafolio, "
					+	" 	(select por.tituloPortafolio from Portafolio AS por where por.codigoPortafolio = rc.portafolio.codigoPortafolio) AS tituloPortafolio, "
					+	" 	count(rc.portafolio.codigoPortafolio) AS intentos "
					+	" FROM Respuestacabecera AS rc "
					+	" WHERE rc.usuario.institucion.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion()
					+	" 	AND rc.usuario.codigoUsuario = " + usuario.getCodigoUsuario()
					+	" GROUP BY rc.portafolio.codigoPortafolio";			
		}
		
		try {
			Query query = session.createQuery(hql);
			List<Object[]> res = query.list();

			listarReporteCursosFiltro1 = new ArrayList<Reportecurso>();
			for (Object[] elements: res){
				Reportecurso rc = new Reportecurso();
				rc.setCodigoPortafolio(Integer.parseInt(elements[0].toString()));
				rc.setTituloPortafolio(elements[1].toString());
				rc.setIntentos(Integer.parseInt(elements[2].toString()));			
				listarReporteCursosFiltro1.add(rc);
			}						
			
			
			/*Consulta para Filtrar por total de Intentos*/
			listarReporteCursosFiltro2 = new ArrayList<Reportecurso>();
			for (int a=0; a<listarReporteCursosFiltro1.size(); a++){
				//System.out.println("CURSO: [" + listarReporteCursosFiltro1.get(a).getTituloPortafolio() + "] Intentos: [" +  listarReporteCursosFiltro1.get(a).getIntentos() + "]");
				String hql1 = "";
				if (administrador.toUpperCase().equals(usuario.getCorreo().toUpperCase().trim()) ) {
					hql1 = "SELECT rc.portafolio.codigoPortafolio AS codigoPortafolio, "
						+	" 	(select por.tituloPortafolio from Portafolio AS por where por.codigoPortafolio = rc.portafolio.codigoPortafolio) AS tituloPortafolio, "
						+	" 	rc.codigoRespuestaCabecera AS codigoRespuestaCabecera, "
						+	" 	count(rc.portafolio.codigoPortafolio) AS intentos "
						+	" FROM Respuestacabecera AS rc "
						+	" WHERE rc.usuario.institucion.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion()
						+ 	" 	AND rc.portafolio.codigoPortafolio = " + listarReporteCursosFiltro1.get(a).getCodigoPortafolio()
						+	" GROUP BY rc.portafolio.codigoPortafolio, rc.codigoRespuestaCabecera "
						+	" ORDER BY rc.portafolio.codigoPortafolio, rc.codigoRespuestaCabecera desc ";
				} else {
					hql1 = "SELECT rc.portafolio.codigoPortafolio AS codigoPortafolio, "
							+	" 	(select por.tituloPortafolio from Portafolio AS por where por.codigoPortafolio = rc.portafolio.codigoPortafolio) AS tituloPortafolio, "
							+	" 	rc.codigoRespuestaCabecera AS codigoRespuestaCabecera, "
							+	" 	count(rc.portafolio.codigoPortafolio) AS intentos "
							+	" FROM Respuestacabecera AS rc "
							+	" WHERE rc.usuario.institucion.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion()
							+ 	" 	AND rc.portafolio.codigoPortafolio = " + listarReporteCursosFiltro1.get(a).getCodigoPortafolio()
							+ 	" 	AND rc.usuario.codigoUsuario = " + usuario.getCodigoUsuario()
							+	" GROUP BY rc.portafolio.codigoPortafolio, rc.codigoRespuestaCabecera "
							+	" ORDER BY rc.portafolio.codigoPortafolio, rc.codigoRespuestaCabecera desc ";
				}
				Query query1 = session.createQuery(hql1);
				List<Object[]> res1 = query1.list();
				int contador = 1;
				for (Object[] elements1: res1){
					if (contador <= intentosEvaluados) {
						Reportecurso rc1 = new Reportecurso();
						rc1.setCodigoPortafolio(Integer.parseInt(elements1[0].toString()));
						rc1.setTituloPortafolio(elements1[1].toString());
						rc1.setCodigoRespuestaCabecera(Integer.parseInt(elements1[2].toString()));
						rc1.setIntentos(listarReporteCursosFiltro1.get(a).getIntentos());					

						String hql2 = "";
						if (administrador.toUpperCase().equals(usuario.getCorreo().toUpperCase().trim()) ) {
							hql2 = "SELECT c.portafolio.codigoPortafolio as codigoPortafolio, " 
								+	"	(select por.tituloPortafolio from Portafolio AS por where por.codigoPortafolio = c.portafolio.codigoPortafolio) AS tituloPortafolio, "
								+	"	SUM((case when d.flagAlternativaCorrecta = 1 then 1 else 0 end)) as correctas, "
								+	"	SUM((case when d.flagAlternativaCorrecta = 0 then 1 else 0 end)) as incorrectas " 
								+	"FROM Respuestacabecera as c " 
								+	"LEFT JOIN c.respuestadetalles as d "
								+	"WHERE c.portafolio.codigoPortafolio = " + listarReporteCursosFiltro1.get(a).getCodigoPortafolio()
								+ 	"	AND c.usuario.institucion.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion()
								+	"	AND c.codigoRespuestaCabecera = " + Integer.parseInt(elements1[2].toString());
						} else {
							hql2 = "SELECT c.portafolio.codigoPortafolio as codigoPortafolio, " 
									+	"	(select por.tituloPortafolio from Portafolio AS por where por.codigoPortafolio = c.portafolio.codigoPortafolio) AS tituloPortafolio, "
									+	"	SUM((case when d.flagAlternativaCorrecta = 1 then 1 else 0 end)) as correctas, "
									+	"	SUM((case when d.flagAlternativaCorrecta = 0 then 1 else 0 end)) as incorrectas " 
									+	"FROM Respuestacabecera as c " 
									+	"LEFT JOIN c.respuestadetalles as d "
									+	"WHERE c.portafolio.codigoPortafolio = " + listarReporteCursosFiltro1.get(a).getCodigoPortafolio()
									+ 	"	AND c.usuario.institucion.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion()
									+ 	"	AND c.usuario.codigoUsuario = " + usuario.getCodigoUsuario()									
									+	"	AND c.codigoRespuestaCabecera = " + Integer.parseInt(elements1[2].toString());
						}
						Query query2 = session.createQuery(hql2);
						List<Object[]> res2 = query2.list();
						int correctas = 0, incorrectas = 0;
						for (Object[] elements2: res2){
							correctas += Integer.parseInt(elements2[2].toString());
							incorrectas += Integer.parseInt(elements2[3].toString());
						}
						rc1.setCorrectas(correctas);
						rc1.setIncorrectas(incorrectas);
						
						
						listarReporteCursosFiltro2.add(rc1);
						contador++;
					}
				}
				
			}
			
			
			/*Consolidacion de Intentos*/
			int codigoPortafolio = 0, totalCorrectas = 0, totalIncorrectas = 0, intentosAnalizados = 0, intentos = 0;
			double porcentajeAciertos = 0.0;
			String tituloPortafolio = "";
			listarReporteCursosFinal = new ArrayList<Reportecurso>();
			for (int b=0; b<listarReporteCursosFiltro2.size(); b++) {				
				if (codigoPortafolio==0) {
					totalCorrectas += listarReporteCursosFiltro2.get(b).getCorrectas();
					totalIncorrectas += listarReporteCursosFiltro2.get(b).getIncorrectas();
					codigoPortafolio = listarReporteCursosFiltro2.get(b).getCodigoPortafolio();
					tituloPortafolio = listarReporteCursosFiltro2.get(b).getTituloPortafolio();
					intentos = listarReporteCursosFiltro2.get(b).getIntentos();
					intentosAnalizados++;
				} else if (codigoPortafolio == listarReporteCursosFiltro2.get(b).getCodigoPortafolio()) {
					intentosAnalizados++;
					totalCorrectas += listarReporteCursosFiltro2.get(b).getCorrectas();
					totalIncorrectas += listarReporteCursosFiltro2.get(b).getIncorrectas();
					codigoPortafolio = listarReporteCursosFiltro2.get(b).getCodigoPortafolio();
					tituloPortafolio = listarReporteCursosFiltro2.get(b).getTituloPortafolio();
					intentos = listarReporteCursosFiltro2.get(b).getIntentos();
				} else if (codigoPortafolio != listarReporteCursosFiltro2.get(b).getCodigoPortafolio()) {
					if (intentosEvaluados == 1) intentosAnalizados = 1;
					if (intentosEvaluados != 1) intentosAnalizados++;
					//System.out.println("1. Curso: [" + codigoPortafolio + "-"+ tituloPortafolio + "] - Correctas: " + totalCorrectas + " - Incorrectas: " + totalIncorrectas);
					Reportecurso rcFinal = new Reportecurso();
					rcFinal.setCodigoPortafolio(codigoPortafolio);
					rcFinal.setCorrectas(totalCorrectas);
					rcFinal.setIncorrectas(totalIncorrectas);
					rcFinal.setIntentosAnalizados(intentosAnalizados);
					rcFinal.setTituloPortafolio(tituloPortafolio);
					rcFinal.setIntentos(intentos);
					porcentajeAciertos = (totalCorrectas * 100) / (totalCorrectas+totalIncorrectas);
					rcFinal.setPorcentajeAciertos(porcentajeAciertos);
					if (rcFinal.getCodigoPortafolio() != null)
						listarReporteCursosFinal.add(rcFinal);
					
					totalCorrectas = 0;
					totalIncorrectas = 0;
					intentosAnalizados = 0;
					intentos = 0;
					totalCorrectas += listarReporteCursosFiltro2.get(b).getCorrectas();
					totalIncorrectas += listarReporteCursosFiltro2.get(b).getIncorrectas();
					codigoPortafolio = listarReporteCursosFiltro2.get(b).getCodigoPortafolio();
					tituloPortafolio = listarReporteCursosFiltro2.get(b).getTituloPortafolio();
					intentos = listarReporteCursosFiltro2.get(b).getIntentos();
					
				} 
				
				if (b+1 == listarReporteCursosFiltro2.size()){
					//System.out.println("2. Curso: [" + codigoPortafolio + "-"+ tituloPortafolio + "] - Correctas: " + totalCorrectas + " - Incorrectas: " + totalIncorrectas);
					intentosAnalizados++;
					Reportecurso rcFinal = new Reportecurso();
					rcFinal = new Reportecurso();
					rcFinal.setCodigoPortafolio(codigoPortafolio);
					rcFinal.setCorrectas(totalCorrectas);
					rcFinal.setIncorrectas(totalIncorrectas);
					rcFinal.setIntentosAnalizados(intentosAnalizados);
					rcFinal.setTituloPortafolio(tituloPortafolio);
					rcFinal.setIntentos(intentos);
					rcFinal.setIntentos(listarReporteCursosFiltro2.get(b).getIntentos());
					porcentajeAciertos = (totalCorrectas * 100) / (totalCorrectas+totalIncorrectas);
					rcFinal.setPorcentajeAciertos(porcentajeAciertos);
					if (rcFinal.getCodigoPortafolio() != null)
						listarReporteCursosFinal.add(rcFinal);
				}
				
			}												
			session.close();
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		
		//return listarReporteCursos;
		return listarReporteCursosFinal;
		
	}

	@Override
	public List<Reportecursodetalle> ListarReporteCursosDetalle(Reportecurso reportecurso) {

		List<Reportecursodetalle> listarReporteCursosDetalle = null;
		Usuario usuario = null;		
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		/*String hql = "SELECT c.portafolio.codigoPortafolio as codigoPortafolio, c.usuario.codigoUsuario, c.usuario.nombres, c.usuario.apellidoPaterno, c.usuario.apellidoMaterno, "
				+	"	(select por.tituloPortafolio from Portafolio AS por where por.codigoPortafolio = c.portafolio.codigoPortafolio) AS tituloPortafolio,"
				+	" 	SUM((case when d.flagAlternativaCorrecta = 1 then 1 else 0 end)) as correctas, " 
				+	"	SUM((case when d.flagAlternativaCorrecta = 0 then 1 else 0 end)) as incorrectas " 
				+	"FROM Respuestacabecera as c LEFT JOIN c.respuestadetalles as d " 
				+	"WHERE c.portafolio.codigoPortafolio = " + reportecurso.getCodigoPortafolio()
				+	"	AND c.usuario.institucion.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion();*/
		String administrador = System.getProperty("usuario_administrador") != null ? System.getProperty("usuario_administrador") : "";
		String hql = "";
		if (administrador.toUpperCase().equals(usuario.getCorreo().toUpperCase().trim()) ) {		
			hql = "SELECT c.portafolio.codigoPortafolio as codigoPortafolio, c.usuario.codigoUsuario as codigoUsuario, c.usuario.nombres as nombres, c.usuario.apellidoPaterno as apellidoPaterno, c.usuario.apellidoMaterno as apellidoMaterno, "  
				+	"	(select por.tituloPortafolio from Portafolio AS por where por.codigoPortafolio = c.portafolio.codigoPortafolio) AS tituloPortafolio, "
				+	"	c.fechaRespuesta as fechaRespuesta, "
				+	"	SUM((case when d.flagAlternativaCorrecta = 1 then 1 else 0 end)) as correctas, " 
				+	"	SUM((case when d.flagAlternativaCorrecta = 0 then 1 else 0 end)) as incorrectas " 
				+	"FROM Respuestacabecera as c LEFT JOIN c.respuestadetalles as d " 
				+	"WHERE c.portafolio.codigoPortafolio = " + reportecurso.getCodigoPortafolio()
				+	"	AND c.usuario.institucion.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion()
				+	"GROUP BY c.portafolio.codigoPortafolio, c.usuario.codigoUsuario, c.usuario.nombres, c.usuario.apellidoPaterno, c.usuario.apellidoMaterno, c.fechaRespuesta";
		} else {
			hql = "SELECT c.portafolio.codigoPortafolio as codigoPortafolio, c.usuario.codigoUsuario as codigoUsuario, c.usuario.nombres as nombres, c.usuario.apellidoPaterno as apellidoPaterno, c.usuario.apellidoMaterno as apellidoMaterno, "  
					+	"	(select por.tituloPortafolio from Portafolio AS por where por.codigoPortafolio = c.portafolio.codigoPortafolio) AS tituloPortafolio, "
					+	"	c.fechaRespuesta as fechaRespuesta, "
					+	"	SUM((case when d.flagAlternativaCorrecta = 1 then 1 else 0 end)) as correctas, " 
					+	"	SUM((case when d.flagAlternativaCorrecta = 0 then 1 else 0 end)) as incorrectas " 
					+	"FROM Respuestacabecera as c LEFT JOIN c.respuestadetalles as d " 
					+	"WHERE c.portafolio.codigoPortafolio = " + reportecurso.getCodigoPortafolio()
					+	"	AND c.usuario.institucion.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion()
					+	"	AND c.usuario.codigoUsuario = " + usuario.getCodigoUsuario()
					+	"GROUP BY c.portafolio.codigoPortafolio, c.usuario.codigoUsuario, c.usuario.nombres, c.usuario.apellidoPaterno, c.usuario.apellidoMaterno, c.fechaRespuesta";
		}
		
		try {
			Query query = session.createQuery(hql);
			List<Object[]> res = query.list();

			listarReporteCursosDetalle = new ArrayList<Reportecursodetalle>();			
			for (Object[] elements: res){
				Reportecursodetalle rd = new Reportecursodetalle();
				Portafolio p = new Portafolio();
				Usuario u = new Usuario();
				p.setCodigoPortafolio(Integer.parseInt(elements[0].toString()));
				p.setTituloPortafolio(elements[5].toString());
				rd.setPortafolio(p);
				u.setCodigoUsuario(Integer.parseInt(elements[1].toString()));
				u.setNombres(elements[2].toString());
				u.setApellidoPaterno(elements[3].toString());
				u.setApellidoMaterno(elements[4].toString());
				rd.setUsuario(u);
				rd.setCorrectas(Integer.parseInt(elements[7].toString()));
				rd.setIncorrectas(Integer.parseInt(elements[8].toString()));
				
				/*System.out.println("Detalle de Reporte: " + res.size());
				System.out.println("Dato1 : " + elements[0].toString());*/
				
				listarReporteCursosDetalle.add(rd);
			}								
			session.close();		

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}
	
		return listarReporteCursosDetalle;
	}
}
