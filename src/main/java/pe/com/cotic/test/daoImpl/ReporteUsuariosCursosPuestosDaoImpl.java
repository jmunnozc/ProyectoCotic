package pe.com.cotic.test.daoImpl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.cotic.test.dao.ReporteUsuariosCursosPuestosDao;
import pe.com.cotic.test.modelo.Institucion;
import pe.com.cotic.test.modelo.Portafolio;
import pe.com.cotic.test.modelo.Reportecurso;
import pe.com.cotic.test.modelo.Reportecursodetalle;
import pe.com.cotic.test.modelo.Reporteusuarioscursos;
import pe.com.cotic.test.modelo.Reporteusuarioscursospuestos;
import pe.com.cotic.test.modelo.Usuario;
import pe.com.cotic.test.modelo.Usuarioportafolio;
import pe.com.cotic.test.modelo.Usuarioscursos;
import pe.com.cotic.test.modelo.Usuariospuesto;
import pe.com.cotic.test.util.HibernateUtil;

public class ReporteUsuariosCursosPuestosDaoImpl implements ReporteUsuariosCursosPuestosDao {

	private Session session;
	
	@Override
	public List<Reporteusuarioscursospuestos> listarReporteUsuariosCursosPuestosxxx() {
		
		Usuario usuario = null;
		Usuarioportafolio usuarioPortafolio = null;
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		List<Reporteusuarioscursospuestos> listarReporteUsuariosCursosPuestos = new ArrayList<Reporteusuarioscursospuestos>();
		//List<Usuariospuesto> listarUsuariosPuestos = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "FROM Usuario u INNER JOIN u.usuarioportafolios p "
				+	" WHERE u.institucion.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion()
				+	" 	AND p.nivel.codigoNivel = 3 ";

		try {
			//listarReporteUsuariosCursosPuestos = session.createQuery(hql).list();
			Query query = session.createQuery(hql);
			List<Object[]> res = query.list();

			List<Reporteusuarioscursospuestos> listaReporteUsuariosCursosPuestos = new ArrayList<Reporteusuarioscursospuestos>();
			List<Usuario> listaUsuario = new ArrayList<Usuario>();
			List<Usuarioportafolio> listaUsuarioPortafolio = new ArrayList<Usuarioportafolio>();			
			for (Object[] elements: res){				
				usuario = (Usuario) elements[0];
				usuarioPortafolio = (Usuarioportafolio) elements[1];
				if (listaUsuario.size() == 0) {
					listaUsuario.add(usuario);
				} else if (listaUsuario.get(listaUsuario.size()-1).getCodigoUsuario() != usuario.getCodigoUsuario()) {
					listaUsuario.add(usuario);
				}
				listaUsuarioPortafolio.add(usuarioPortafolio);
			}
			
			String nickUsuarios = "";
			String nombreFull = "";
			Integer usuariosCuestionarios = 0;
			for (int a=0; a<listaUsuario.size(); a++) {
				nickUsuarios = listaUsuario.get(a).getUsuario();
				nombreFull = listaUsuario.get(a).getApellidoPaterno().trim() + " " + listaUsuario.get(a).getApellidoMaterno().trim() + ", " + listaUsuario.get(a).getNombres().trim();
				System.out.println(a+1 + " USUARIO [" + nickUsuarios + "]");
				for (int b=0; b<listaUsuarioPortafolio.size(); b++) {
					if (listaUsuario.get(a).getUsuario() == listaUsuarioPortafolio.get(b).getUsuario().getUsuario()) {						
						usuariosCuestionarios++;
					} 
				}
				

				String hql2 = "";
				hql2 = "SELECT us.codigoUsuario, rc.portafolio.codigoPortafolio, rc.codigoRespuestaCabecera, rd.codigoRespuestaDetalle, "
						+ "		rd.pregunta.codigoPregunta, rd.alternativa.codigoAlternativa, rd.flagAlternativaCorrecta "
						+ "FROM Respuestacabecera as rc "
						+ "	LEFT JOIN rc.respuestadetalles as rd "
						+ "	LEFT JOIN rc.portafolio as po "
						+ "	LEFT JOIN rc.usuario as us "
						+ "WHERE po.nivel.codigoNivel = 3 AND us.institucion.codigoInstitucion = 2 AND rd.codigoRespuestaDetalle is not null "
						+ "ORDER BY rc.portafolio.codigoPortafolio ";
				//listarUsuariosPuestos = session.createQuery(hql2).list();
				Query query2 = session.createQuery(hql2);
				List<Object[]> res2 = query2.list();
				List<Usuariospuesto> listarUsuariosPuestos = new ArrayList<Usuariospuesto>();

				for (Object[] elements2: res2){
					Usuariospuesto up2 = new Usuariospuesto();
					up2.setCodigoUsuario(Integer.parseInt(elements2[0].toString()));
					up2.setCodigoPortafolio(Integer.parseInt(elements2[1].toString()));
					up2.setCodigoRespuestaCabecera(Integer.parseInt(elements2[2].toString()));
					up2.setCodigoRespuestaDetalle(Integer.parseInt(elements2[3].toString()));
					up2.setCodigoPregunta(Integer.parseInt(elements2[4].toString()));
					up2.setCodigoAlternativa(Integer.parseInt(elements2[5].toString()));
					up2.setFlagAlternativa(Integer.parseInt(elements2[6].toString()));
					listarUsuariosPuestos.add(up2);
				}
				
				System.out.println(listarUsuariosPuestos.size());
				int totalPreguntas = 0;
				List<Usuariospuesto> listarUsuario = new ArrayList<Usuariospuesto>();
				for (int j=0; j<listarUsuariosPuestos.size(); j++) {
					Usuariospuesto usupue = new Usuariospuesto();
					usupue.setCodigoUsuario(listarUsuariosPuestos.get(j).getCodigoUsuario());
					if (listarUsuario.size()==0){
						listarUsuario.add(usupue);
					} else {
						int Contador = 0;
						for (int k=0; k<listarUsuario.size(); k++) {
							if (listarUsuariosPuestos.get(j).getCodigoUsuario() == listarUsuario.get(k).getCodigoUsuario()) {
								Contador++;								
							}
						}
						if (Contador == 0 && usupue != null) {
							listarUsuario.add(usupue);
						}
					}
				}
				System.out.println("Total de Usuarios: " + listarUsuario.size());
				
				
				for (int b=0; b<listarUsuario.size(); b++){
					List<Usuariospuesto> listarUsuarioPuesto = new ArrayList<Usuariospuesto>();					
					for (int c=0; c<listarUsuariosPuestos.size(); c++) {
						Usuariospuesto up = new Usuariospuesto();
						up.setCodigoUsuario(listarUsuariosPuestos.get(c).getCodigoUsuario());
						up.setCodigoPortafolio(listarUsuariosPuestos.get(c).getCodigoPortafolio());
						up.setCodigoRespuestaCabecera(listarUsuariosPuestos.get(c).getCodigoRespuestaCabecera());
						up.setCodigoRespuestaDetalle(listarUsuariosPuestos.get(c).getCodigoRespuestaDetalle());
						up.setCodigoPregunta(listarUsuariosPuestos.get(c).getCodigoPregunta());
						up.setCodigoAlternativa(listarUsuariosPuestos.get(c).getCodigoAlternativa());
						up.setFlagAlternativa(listarUsuariosPuestos.get(c).getFlagAlternativa());
						if ( (listarUsuario.get(b).getCodigoUsuario() == up.getCodigoUsuario()) && (up != null) && (up.getFlagAlternativa()==1) ) {
							listarUsuarioPuesto.add(up);
						}
					}
					
					System.out.println("Total de Usuario para Puesto [" + listarUsuario.get(b).getCodigoUsuario() + " - " + listarUsuarioPuesto.size() + "]");
					
				}
				System.out.println(listarUsuariosPuestos.get(0).getCodigoUsuario());
				
				System.out.println(nombreFull);
				System.out.println(usuariosCuestionarios);
				Reporteusuarioscursospuestos reporteusuarioscursospuestos = new Reporteusuarioscursospuestos();
				reporteusuarioscursospuestos.setFullNombre(nombreFull);
				reporteusuarioscursospuestos.setTotalCuestionarios(usuariosCuestionarios);
				usuariosCuestionarios = 0;				
				listaReporteUsuariosCursosPuestos.add(reporteusuarioscursospuestos);
				
			}

			listarReporteUsuariosCursosPuestos = listaReporteUsuariosCursosPuestos;
			transaction.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}

		
		return listarReporteUsuariosCursosPuestos;
	}


	@Override
	public List<Reporteusuarioscursospuestos> listarReporteUsuariosCursosPuestos() {

		Usuario usuario = null;
		Usuarioportafolio usuarioPortafolio = null;
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		List<Reporteusuarioscursospuestos> listarReporteUsuariosCursosPuestos = new ArrayList<Reporteusuarioscursospuestos>();
		//List<Usuariospuesto> listarUsuariosPuestos = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "SELECT rc.usuario.codigoUsuario as codigoUsuario, rc.portafolio.codigoPortafolio as codigoPortafolio, count(rd.flagAlternativaCorrecta) as total "
				+	" FROM Respuestacabecera rc " 
				+	" 	INNER JOIN rc.respuestadetalles rd " 
				+	" WHERE rd.flagAlternativaCorrecta = 1 AND rc.usuario.institucion.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion()
				+	" GROUP BY rc.usuario.codigoUsuario, rc.portafolio.codigoPortafolio "
				+	" ORDER BY rc.portafolio.codigoPortafolio, count(rd.flagAlternativaCorrecta) desc";
		try {
			Query query = session.createQuery(hql);
			List<Usuariospuesto> listarUsuarioPuesto = new ArrayList<Usuariospuesto>();
			List<Usuariospuesto> listarUsuario = new ArrayList<Usuariospuesto>();
			List<Object[]> res = query.list();
			int contadorPuesto=1;
			for (Object[] elements: res){
				Usuariospuesto up = new Usuariospuesto();
				up.setCodigoUsuario(Integer.parseInt(elements[0].toString()));
				up.setCodigoPortafolio(Integer.parseInt(elements[1].toString()));
				up.setTotal(Integer.parseInt(elements[2].toString()));
				if (listarUsuarioPuesto.size()==0) {
					up.setPuestoUsuario(contadorPuesto++);
					listarUsuarioPuesto.add(up);
				} else if (listarUsuarioPuesto.get(listarUsuarioPuesto.size()-1).getCodigoPortafolio()==Integer.parseInt(elements[1].toString())) {
					up.setPuestoUsuario(contadorPuesto++);
					listarUsuarioPuesto.add(up);
				} else if (listarUsuarioPuesto.get(listarUsuarioPuesto.size()-1).getCodigoPortafolio()!=Integer.parseInt(elements[1].toString())) {
					contadorPuesto=1;
					up.setPuestoUsuario(contadorPuesto++);
					listarUsuarioPuesto.add(up);
				} else {					
					contadorPuesto=1;
				}
				if (listarUsuario.size()==0){
					listarUsuario.add(up);
				} else {
					int contador=0;
					for (int usu=0; usu<listarUsuario.size(); usu++) {
						if (listarUsuario.get(usu).getCodigoUsuario()==up.getCodigoUsuario()) {
							contador++;
						}
					}
					if (contador==0) {
						listarUsuario.add(up);
					}					
				}
				
			}
			
			/*for (int a=0; a<listarUsuarioPuesto.size(); a++) {				
				if (a==0) {
					System.out.println("----------------------------------------------");	
				} else if (listarUsuarioPuesto.get(a).getCodigoPortafolio()!=listarUsuarioPuesto.get(a-1).getCodigoPortafolio()) {
					System.out.println("----------------------------------------------");
				}
				System.out.println("[" + listarUsuarioPuesto.get(a).getCodigoPortafolio() + "] [" + listarUsuarioPuesto.get(a).getPuestoUsuario() + "] [" + listarUsuarioPuesto.get(a).getCodigoUsuario() + "]");
			}*/
			
			for (int j=0; j<listarUsuario.size();j++) {
				//System.out.println("Usuario: " + listarUsuario.get(j).getCodigoUsuario());
				List<Usuariospuesto> listarUsuariosPuestos = new ArrayList<Usuariospuesto>();
				for (int k=0; k<listarUsuarioPuesto.size(); k++) {
					Usuariospuesto upo = new Usuariospuesto();
					upo.setCodigoUsuario(listarUsuarioPuesto.get(k).getCodigoUsuario());
					upo.setCodigoPortafolio(listarUsuarioPuesto.get(k).getCodigoPortafolio());
					upo.setPuestoUsuario(listarUsuarioPuesto.get(k).getPuestoUsuario());
					if (listarUsuario.get(j).getCodigoUsuario() == listarUsuarioPuesto.get(k).getCodigoUsuario()) {						
						//System.out.println("Codigo1: " + listarUsuario.get(j).getCodigoUsuario() + " Codigo2: " + listarUsuarioPuesto.get(k).getCodigoUsuario() + " Puesto: " + listarUsuarioPuesto.get(k).getCodigoPortafolio() + " - " + listarUsuarioPuesto.get(k).getPuestoUsuario());
						listarUsuariosPuestos.add(upo);
					}
				}
				Set<Integer> hs = new HashSet<Integer>();
				for (int l=0; l<listarUsuariosPuestos.size(); l++) {
					hs.add(listarUsuariosPuestos.get(l).getPuestoUsuario());					
				}
				
				
				String hql2 = "SELECT u.codigoUsuario as codigoUsuario, concat(concat(concat(concat(u.apellidoPaterno,' '),u.apellidoMaterno),', '),u.nombres) as fullNombre, count(p.portafolioByCodigoPortafolio.codigoPortafolio) as portafolios "
						+	" FROM Usuario u INNER JOIN u.usuarioportafolios p " 
						+	" WHERE u.institucion.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion()
						+ 	" 	AND p.nivel.codigoNivel = 3 AND u.codigoUsuario = " + listarUsuario.get(j).getCodigoUsuario() 
						+	" GROUP BY u.codigoUsuario, u.apellidoPaterno, u.apellidoMaterno, u.nombres";
				Query query2 = session.createQuery(hql2);				
				List<Object[]> res2 = query2.list();
				for (Object[] elements2: res2){
					Reporteusuarioscursospuestos ucu = new Reporteusuarioscursospuestos();
					ucu.setCodigoUsuario(Integer.parseInt(elements2[0].toString()));
					ucu.setFullNombre(elements2[1].toString());
					ucu.setTotalCuestionarios(Integer.parseInt(elements2[2].toString()));
					ucu.setUbicacionPrimera(Collections.min(hs));
					ucu.setUbicacionUltima(Collections.max(hs));
					listarReporteUsuariosCursosPuestos.add(ucu);
				}
				//System.out.println("Usuario[" + listarUsuariosPuestos.get(j).getCodigoUsuario() + "] top[" + Collections.min(hs) + "] bottom[" + Collections.max(hs) + "]");
			}
			
			transaction.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}

		
		return listarReporteUsuariosCursosPuestos;

	}


	@Override
	public List<Reporteusuarioscursos> listarReporteUsuariosCursos() {
		List<Reporteusuarioscursos> listarReporteUsuariosCursos = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Usuario usuario = null;
		String hql = "";
		
		String administrador = System.getProperty("usuario_administrador") != null ? System.getProperty("usuario_administrador") : "";
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

		hql = "FROM Reporteusuarioscursos AS r "
			+ " WHERE r.codigoInstitucion =" + usuario.getInstitucion().getCodigoInstitucion()
			+ " 	AND r.codigoUsuario = " + usuario.getCodigoUsuario()
			+ " ORDER BY r.tituloPortafolio, r.nombreUsuarioFull, r.fechaRespuesta ";
				
		try {
			listarReporteUsuariosCursos = session.createQuery(hql).list();
			transaction.commit();
			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		
		return listarReporteUsuariosCursos;
	}


	@Override
	public List<Reportecursodetalle> ListarReporteUsuariosCursosDetalle(int codigoUsuario) {
		
		List<Reportecursodetalle> listarReporteUsuariosCursosDetalle = null;
		Usuario usuario = null;		
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		String administrador = System.getProperty("usuario_administrador") != null ? System.getProperty("usuario_administrador") : "";
		
		//Primer Query directo al Respuestacabecera
		String hql = "";
		/*hql = "SELECT c.portafolio.codigoPortafolio as codigoPortafolio, c.usuario.codigoUsuario as codigoUsuario, c.usuario.nombres as nombres, c.usuario.apellidoPaterno as apellidoPaterno, c.usuario.apellidoMaterno as apellidoMaterno, "  
			+	"	(select por.tituloPortafolio from Portafolio AS por where por.codigoPortafolio = c.portafolio.codigoPortafolio) AS tituloPortafolio, "
			+	"	c.fechaRespuesta as fechaRespuesta, "
			+	"	SUM((case when d.flagAlternativaCorrecta = 1 then 1 else 0 end)) as correctas, " 
			+	"	SUM((case when d.flagAlternativaCorrecta = 0 then 1 else 0 end)) as incorrectas " 
			+	"FROM Respuestacabecera as c LEFT JOIN c.respuestadetalles as d " 
			+	"WHERE c.usuario.institucion.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion()
			+	" 	AND c.usuario.codigoUsuario = " + codigoUsuario
			+	"GROUP BY c.portafolio.codigoPortafolio, c.usuario.codigoUsuario, c.usuario.nombres, c.usuario.apellidoPaterno, c.usuario.apellidoMaterno, c.fechaRespuesta";	
		try {
			Query query = session.createQuery(hql);
			List<Object[]> res = query.list();

			listarReporteUsuariosCursosDetalle = new ArrayList<Reportecursodetalle>();			
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
				
				listarReporteUsuariosCursosDetalle.add(rd);
			}								
			session.close();*/
		hql = "SELECT r.codigoPortafolio, r.codigoUsuario, r.tituloPortafolio, sum(1) as intentos, sum(r.cantidadCorrectas) as correctas, sum(r.cantidadIncorrectas) as incorrectas, sum(r.cantidadNocontestadas) as nocontestadas, sum(r.cantidadPreguntas) as preguntas "
			+ "FROM Reporteusuarioscursos r " 
			+ "WHERE r.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion()
			+ "	AND r.codigoUsuario = " + codigoUsuario
			+ "GROUP BY r.codigoPortafolio, r.codigoUsuario, r.tituloPortafolio";
		try {
			Query query = session.createQuery(hql);
			List<Object[]> res = query.list();

			listarReporteUsuariosCursosDetalle = new ArrayList<Reportecursodetalle>();
			DecimalFormat df = new DecimalFormat("#.00");
			for (Object[] elements: res){
				Reportecursodetalle rd = new Reportecursodetalle();
				Portafolio p = new Portafolio();
				Usuario u = new Usuario();
				p.setCodigoPortafolio(Integer.parseInt(elements[0].toString()));
				p.setTituloPortafolio(elements[2].toString());
				rd.setPortafolio(p);
				u.setCodigoUsuario(Integer.parseInt(elements[1].toString()));
				rd.setUsuario(u);
				rd.setIntentos(Integer.parseInt(elements[3].toString()));
				/*rd.setCorrectas(Integer.parseInt(elements[4].toString()));
				rd.setIncorrectas(Integer.parseInt(elements[5].toString()));
				rd.setNocontestadas(Integer.parseInt(elements[6].toString()));
				rd.setPreguntas(Integer.parseInt(elements[7].toString()));*/				
				rd.setCorrectas(Double.parseDouble(df.format(Double.parseDouble(elements[4].toString())/Double.parseDouble(elements[7].toString())*100).toString().replace(",", ".")));
				rd.setIncorrectas(Double.parseDouble(df.format(Double.parseDouble(elements[5].toString())/Double.parseDouble(elements[7].toString())*100).toString().replace(",", ".")));				
				rd.setNocontestadas(Double.parseDouble(df.format(Double.parseDouble(elements[6].toString())/Double.parseDouble(elements[7].toString())*100).toString().replace(",", ".")));
				if ((rd.getCorrectas().doubleValue() + rd.getIncorrectas().doubleValue() + rd.getNocontestadas().doubleValue()) != 100.00) {
					rd.setNocontestadas(Double.parseDouble(df.format(100.00 - (rd.getCorrectas().doubleValue() + rd.getIncorrectas().doubleValue())).toString().replace(",", ".")));
				}
				//rd.setPreguntas(Double.parseDouble(df.format(Double.parseDouble(elements[7].toString())/Double.parseDouble(elements[7].toString())*100).toString().replace(",", ".")));
				rd.setPreguntas(Integer.parseInt(elements[7].toString()));
				listarReporteUsuariosCursosDetalle.add(rd);
			}								
			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}

		
		return listarReporteUsuariosCursosDetalle;
	}


	@Override
	public List<Reportecursodetalle> ListarReporteUsuariosCursosDetalleUnico(int codigoUsuario, int codigoCurso) {

		//System.out.println("--> USUARIO " + codigoUsuario + " CURSO " + codigoCurso);
		List<Reportecursodetalle> listarReporteUsuariosCursosDetalle = null;
		Usuario usuario = null;		
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		// Consulta Intentos Realizados para un Curso
		String administrador = System.getProperty("usuario_administrador") != null ? System.getProperty("usuario_administrador") : "";
		String hql = "";
		hql = "SELECT rc.codigoRespuestaCabecera "
				+	"FROM Respuestacabecera rc "
				+	"WHERE rc.usuario.codigoUsuario = " + codigoUsuario
				+	"	AND rc.portafolio.codigoPortafolio = " + codigoCurso 
				+	"ORDER BY rc.fechaRespuesta desc";
		
		try {
			Query query = session.createQuery(hql);
			List<Integer> res = query.list();
			listarReporteUsuariosCursosDetalle = new ArrayList<Reportecursodetalle>();
			double correctas = 0.0, incorrectas = 0.0, nocontestadas = 0.0;
			int cantidadPreguntas = 0;
			DecimalFormat df = new DecimalFormat("#.00");
			//System.out.println("-------------------------------------------------------------");
			for (Integer elements: res){
				//System.out.println(elements.toString());
				// Consulta Respuestas Contestadas por cada Intento
				String hql1 = "";
				hql1 = "SELECT SUM(1) as cantidadPreguntas, " 
						+ "	SUM( (CASE WHEN rd.flagAlternativaCorrecta = 1 THEN 1 ELSE 0 END) ) as correctas, "
						+ "	SUM( (CASE WHEN rd.flagAlternativaCorrecta = 0 THEN 1 ELSE 0 END) ) as incorrectas, "
						+ "	SUM(0) as nocontestadas "
						+ "FROM Respuestacabecera rc " 
						+ "	LEFT JOIN rc.respuestadetalles rd "
						+ "WHERE rc.usuario.codigoUsuario = " + codigoUsuario
						+ "	AND rc.portafolio.codigoPortafolio = " + codigoCurso
						+ "	AND rc.codigoRespuestaCabecera = " + elements;
				
				try {
					Query query1 = session.createQuery(hql1);
					List<Object[]> res1 = query1.list();
					for (Object[] elements1: res1){
						//System.out.println(elements1[0].toString() + " - " + elements1[1].toString() + " - " + elements1[2].toString() + " - " + elements1[3].toString());
						cantidadPreguntas += Integer.parseInt(elements1[0].toString());
						correctas += Double.parseDouble(elements1[1].toString());
						incorrectas += Double.parseDouble(elements1[2].toString());
						nocontestadas += Double.parseDouble(elements1[3].toString());
					}
				} catch (Exception e) {
					//System.out.println(e.getMessage());
				}
				
				
				// Consulta Respuestas No Contestadas por cada Intento
				String hql2 = "";
				hql2 = "select SUM(1) as cantidadPreguntas, "
						+ "	SUM(0) as correctas, "
						+ "	SUM(0) as incorrectas, "
						+ "	SUM(1) as nocontestadas "
						+ "from Portafolio p "
						+ "	inner join p.preguntas pr "
						+ "where p.codigoPortafolio = " + codigoCurso
						+ "	and pr.portafolio.codigoPortafolio = " + codigoCurso
						+ "	and codigoPregunta not in  (select d.pregunta.codigoPregunta "
						+ "								from Respuestacabecera c left join c.respuestadetalles d "
						+ "								where c.usuario.codigoUsuario = " + codigoUsuario
						+ "									and c.codigoRespuestaCabecera = " + elements
						+ ")";
				
				try {
					Query query2 = session.createQuery(hql2);
					List<Object[]> res2 = query2.list();
					for (Object[] elements2: res2){
						//System.out.println(elements2[0].toString() + " - " + elements2[1].toString() + " - " + elements2[2].toString() + " - " + elements2[3].toString());
						cantidadPreguntas += Integer.parseInt(elements2[0].toString());
						correctas += Double.parseDouble(elements2[1].toString());
						incorrectas += Double.parseDouble(elements2[2].toString());
						nocontestadas += Double.parseDouble(elements2[3].toString());						
					}
				} catch (Exception e) {
					//System.out.println(e.getMessage());
				}
				
				//System.out.println("-------------------------------------------------------------");
				Reportecursodetalle rcd = new Reportecursodetalle();
				rcd.setCorrectas(Double.parseDouble(df.format((correctas*100)/cantidadPreguntas).toString().replace(",", ".")));
				rcd.setIncorrectas(Double.parseDouble(df.format((incorrectas*100)/cantidadPreguntas).toString().replace(",", ".")));
				rcd.setNocontestadas(Double.parseDouble(df.format((nocontestadas*100)/cantidadPreguntas).toString().replace(",", ".")));
				rcd.setPreguntas(cantidadPreguntas);
				listarReporteUsuariosCursosDetalle.add(rcd);
				correctas = 0.0; 
				incorrectas = 0.0; 
				nocontestadas = 0.0;
				cantidadPreguntas = 0;
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		
		return listarReporteUsuariosCursosDetalle;
	}


}
