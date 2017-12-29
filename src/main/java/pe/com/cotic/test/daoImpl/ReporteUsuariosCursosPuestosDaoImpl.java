package pe.com.cotic.test.daoImpl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.cotic.test.dao.ReporteUsuariosCursosPuestosDao;
import pe.com.cotic.test.modelo.Portafolio;
import pe.com.cotic.test.modelo.Reportecursodetalle;
import pe.com.cotic.test.modelo.Reporteusuarioscursos;
import pe.com.cotic.test.modelo.Reporteusuarioscursospuestos;
import pe.com.cotic.test.modelo.Usuario;
import pe.com.cotic.test.modelo.Usuarioportafolio;
import pe.com.cotic.test.modelo.Usuariospuesto;
import pe.com.cotic.test.util.CursoComparatorByTotal;
import pe.com.cotic.test.util.HibernateUtil;

public class ReporteUsuariosCursosPuestosDaoImpl implements ReporteUsuariosCursosPuestosDao {

	private Session session;
	private Session sessionCurso;
	private Session sessionDetalle;
	
/*	@Override
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
	}*/


	@Override
	public List<Reporteusuarioscursospuestos> listarReporteUsuariosCursosPuestos() {
		System.out.println("[DaoImpl] listarReporteUsuariosCursosPuestos()");
		//Cargar la info en la tabla para la visualizacion del reporte
		//prepararTabla();
		Usuario usuario = null;
		Usuarioportafolio usuarioPortafolio = null;
		
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		List<Reporteusuarioscursospuestos> listarReporteUsuariosCursosPuestos = new ArrayList<Reporteusuarioscursospuestos>();
		//List<Usuariospuesto> listarUsuariosPuestos = null;
		sessionCurso = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = sessionCurso.beginTransaction();
		String hql = "SELECT rc.usuario.codigoUsuario as codigoUsuario, rc.portafolio.codigoPortafolio as codigoPortafolio, count(rd.flagAlternativaCorrecta) as total "
				+	" FROM Respuestacabecera rc " 
				+	" 	INNER JOIN rc.respuestadetalles rd " 
				+	" WHERE rd.flagAlternativaCorrecta = 1 AND rc.usuario.institucion.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion()
				+	" GROUP BY rc.usuario.codigoUsuario, rc.portafolio.codigoPortafolio "
				+	" ORDER BY rc.usuario.codigoUsuario, rc.portafolio.codigoPortafolio, count(rd.flagAlternativaCorrecta) desc";
		try {
			Query query = sessionCurso.createQuery(hql);
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
				Query query2 = sessionCurso.createQuery(hql2);				
				List<Object[]> res2 = query2.list();
				for (Object[] elements2: res2){
					Reporteusuarioscursospuestos ucu = new Reporteusuarioscursospuestos();
					ucu.setCodigoUsuario(Integer.parseInt(elements2[0].toString()));
					ucu.setFullNombre(elements2[1].toString());
					ucu.setTotalCuestionarios(Integer.parseInt(elements2[2].toString()));
					ucu.setUbicacionPrimera(Collections.min(hs));
					ucu.setUbicacionUltima(Collections.max(hs));
					
					//Cantidad de cuestionarios contestados
					String hql3 = "SELECT distinct rc.portafolio.codigoPortafolio "
							+	" FROM Respuestacabecera rc " 
							+	" WHERE rc.usuario.codigoUsuario = " + listarUsuario.get(j).getCodigoUsuario();
					Query query3 = sessionCurso.createQuery(hql3);
					List<Integer> res3 = query3.list();
					ucu.setTotalCuestionariosContestados(res3.size());
					
					//Ultima fecha de todos los cuestionarios
					String hql4 = "SELECT max(rc.codigoRespuestaCabecera) "
							+	" FROM Respuestacabecera rc " 
							+	" WHERE rc.usuario.codigoUsuario = " + listarUsuario.get(j).getCodigoUsuario();
					Query query4 = sessionCurso.createQuery(hql4);
					List<Integer> res4 = query4.list();
					for (Integer elements4: res4){
						String hql5 = "SELECT rtc.fechaRespuesta "
								+	" FROM Respuestacabecera rtc " 
								+	" WHERE rtc.codigoRespuestaCabecera = " + elements4;
						Query query5 = sessionCurso.createQuery(hql5);
						List<String> res5 = query5.list();
						for (String elements5: res5){
							ucu.setFechaUltimoCuestionario(elements5.toString());	
						}
					}
					
					listarReporteUsuariosCursosPuestos.add(ucu);
				}
				//System.out.println("Usuario[" + listarUsuariosPuestos.get(j).getCodigoUsuario() + "] top[" + Collections.min(hs) + "] bottom[" + Collections.max(hs) + "]");
			}
			
			transaction.commit();
			sessionCurso.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		} 

					
		return listarReporteUsuariosCursosPuestos;	
		
	}


	@Override
	public List<Reporteusuarioscursos> listarReporteUsuariosCursos() {
		System.out.println("listarReporteUsuariosCursos()");
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
	//Trabajandolo para probarlo...
	public List<Reportecursodetalle> ListarReporteUsuariosCursosDetalle(int codigoUsuario) {
		System.out.println("ListarReporteUsuariosCursosDetalle()");
		List<Reportecursodetalle> listarReporteUsuariosCursosDetalle = new ArrayList<Reportecursodetalle>();
		Usuario usuario = null;		
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		sessionDetalle = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = sessionDetalle.beginTransaction();
		
		String administrador = System.getProperty("usuario_administrador") != null ? System.getProperty("usuario_administrador") : "";
		
		//Cantidad de Portafolios Asignados al usuario		
		String hql = "SELECT up.portafolioByCodigoPortafolio.codigoPortafolio, up.portafolioByCodigoPortafolio.tituloPortafolio "
				+ "FROM Usuarioportafolio up "
				+ "WHERE up.nivel.codigoNivel = 3 " 
				+ "	AND up.usuario.codigoUsuario = " + codigoUsuario
				+ " AND up.portafolioByCodigoPortafolio.usuario.institucion.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion();
		try {
			Query query = sessionDetalle.createQuery(hql);
			//List<Integer> res = query.list();
			List<Object[]> res = query.list();
			//System.out.println("Cuestionarios Asignados " + res.size() );
			for (Object[] elements: res){
				//System.out.println("--------- Inicia Portafolio " + elements[0]);
				Reportecursodetalle rcd = new Reportecursodetalle();
				Portafolio pt = new Portafolio();
				pt.setCodigoPortafolio(Integer.parseInt(elements[0].toString()));
				pt.setTituloPortafolio(elements[1].toString());
				//Cantidad de Intentos		
				String hql1 = "SELECT DISTINCT rc.codigoRespuestaCabecera "
						+ "FROM Respuestacabecera rc "
						+ "	LEFT JOIN rc.respuestadetalles rd "
						+ "WHERE rc.usuario.codigoUsuario = " + codigoUsuario
						+ "	AND rc.portafolio.codigoPortafolio = " + elements[0]
						+ " ORDER BY rc.codigoRespuestaCabecera ";
				try {
					Query query1 = sessionDetalle.createQuery(hql1);
					List<Integer> res1 = query1.list();
					rcd.setUsuario(usuario);
					rcd.setIntentos(res1.size());
					rcd.setPortafolio(pt);
					//System.out.println("Intentos " + res1.size() );					
					//Cantidad de Preguntas de la Respuesta Seleccionada: aciertos, erradas, nocontestadas
					List<Reporteusuarioscursos> listarReporteUsuariosCursos = new ArrayList<Reporteusuarioscursos>();
					for (Integer elements1 : res1){
						String hql2 = "SELECT rc.codigoRespuestaCabecera, rc.fechaRespuesta, rc.usuario.codigoUsuario, rc.portafolio.codigoPortafolio, 1 as cantidadPreguntas, rd.pregunta.codigoPregunta, "
								+ " CASE WHEN rd.flagAlternativaCorrecta=1 THEN 1 ELSE 0 END as correctas, "
								+ " CASE WHEN rd.flagAlternativaCorrecta=0 THEN 1 ELSE 0 END as incorrectas, "
								+ " 0 as nocontestadas, "
								+ " u.institucion.codigoInstitucion "
								+ "FROM Respuestacabecera rc "
								+ "	LEFT JOIN rc.respuestadetalles rd "
								+ "	LEFT JOIN rc.usuario u "
								+ "WHERE rc.usuario.codigoUsuario = " + codigoUsuario
								+ "	AND rc.codigoRespuestaCabecera = " + elements1;
						try {
							Query query2 = sessionDetalle.createQuery(hql2);
							List<Object[]> res2 = query2.list();
							for (Object[] elements2: res2){
								Reporteusuarioscursos ruc = new Reporteusuarioscursos();
								//System.out.println(elements2[5]);
								if (elements2[5]==null) {
									String hql21 = "SELECT " + elements1 + " as codigoRespuestaCabecera, (select fechaRespuesta from Respuestacabecera reca where reca.codigoRespuestaCabecera=" + elements1 + ") as fechaRespuesta, " + codigoUsuario + " as codigoUsuario, " 
												+	"" + elements[0] + " as codigoPortafolio, 1 as cantidadPreguntas, pr.codigoPregunta, 0 as correctas, 0 as incorrectas, 1 as nocontestadas, " + usuario.getInstitucion().getCodigoInstitucion() + " as codigoInstitucion "
												+	"FROM Portafolio p "
												+	"	INNER JOIN p.preguntas pr "
												+	"WHERE p.codigoPortafolio=" + elements[0]
												+	"	AND pr.portafolio.codigoPortafolio=" + elements[0];
									Query query21 = sessionDetalle.createQuery(hql21);
									List<Object[]> res21 = query21.list();
									for (Object[] elements21: res21){
										ruc.setCodigoRespuestaCabecera(Integer.parseInt(elements21[0].toString()));
										ruc.setFechaRespuesta(elements21[1].toString());
										ruc.setCodigoUsuario(Integer.parseInt(elements21[2].toString()));
										ruc.setCodigoPortafolio(Integer.parseInt(elements21[3].toString()));
										ruc.setCantidadPreguntas(Integer.parseInt(elements21[4].toString()));
										ruc.setCantidadCorrectas(Integer.parseInt(elements21[6].toString()));
										ruc.setCantidadIncorrectas(Integer.parseInt(elements21[7].toString()));
										ruc.setCantidadNocontestadas(Integer.parseInt(elements21[8].toString()));
										ruc.setCodigoInstitucion(Integer.parseInt(elements21[9].toString()));
										listarReporteUsuariosCursos.add(ruc);
									}
								} else {
									ruc.setCodigoRespuestaCabecera(Integer.parseInt(elements2[0].toString()));
									ruc.setFechaRespuesta(elements2[1].toString());
									ruc.setCodigoUsuario(Integer.parseInt(elements2[2].toString()));
									ruc.setCodigoPortafolio(Integer.parseInt(elements2[3].toString()));
									ruc.setCantidadPreguntas(Integer.parseInt(elements2[4].toString()));
									ruc.setCantidadCorrectas(Integer.parseInt(elements2[6].toString()));
									ruc.setCantidadIncorrectas(Integer.parseInt(elements2[7].toString()));
									ruc.setCantidadNocontestadas(Integer.parseInt(elements2[8].toString()));
									ruc.setCodigoInstitucion(Integer.parseInt(elements2[9].toString()));
									listarReporteUsuariosCursos.add(ruc);
								}
							}
							
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
						
						String hql3 = "SELECT " + elements1 + " as codigoRespuestaCabecera, (select fechaRespuesta from Respuestacabecera reca where reca.codigoRespuestaCabecera=" + elements1 + ") as fechaRespuesta, " + codigoUsuario + " as codigoUsuario, " 
								+	"" + elements[0] + " as codigoPortafolio, 1 as cantidadPreguntas, pr.codigoPregunta, 0 as correctas, 0 as incorrectas, 1 as nocontestadas, " + usuario.getInstitucion().getCodigoInstitucion() + " as codigoInstitucion "
								+	"FROM Portafolio p "
								+	"	INNER JOIN p.preguntas pr "
								+	"WHERE p.codigoPortafolio=" + elements[0]
								+	"	AND pr.portafolio.codigoPortafolio=" + elements[0] 
								+	"	AND pr.codigoPregunta NOT IN ( "
								+	"			SELECT rd.pregunta.codigoPregunta "
								+	"			FROM Respuestacabecera rc "
								+	"				LEFT JOIN rc.respuestadetalles rd "
								+	"				LEFT JOIN rc.usuario u "
								+	"			WHERE rc.usuario.codigoUsuario=" + codigoUsuario + " AND rc.codigoRespuestaCabecera=" + elements1 + ")";
						try {
							Query query3 = sessionDetalle.createQuery(hql3);
							List<Object[]> res3 = query3.list();
							for (Object[] elements3: res3){
								Reporteusuarioscursos ruc = new Reporteusuarioscursos();
								ruc.setCodigoRespuestaCabecera(Integer.parseInt(elements3[0].toString()));
								ruc.setFechaRespuesta(elements3[1].toString());
								ruc.setCodigoUsuario(Integer.parseInt(elements3[2].toString()));
								ruc.setCodigoPortafolio(Integer.parseInt(elements3[3].toString()));
								ruc.setCantidadPreguntas(Integer.parseInt(elements3[4].toString()));
								ruc.setCantidadCorrectas(Integer.parseInt(elements3[6].toString()));
								ruc.setCantidadIncorrectas(Integer.parseInt(elements3[7].toString()));
								ruc.setCantidadNocontestadas(Integer.parseInt(elements3[8].toString()));
								ruc.setCodigoInstitucion(Integer.parseInt(elements3[9].toString()));
								listarReporteUsuariosCursos.add(ruc);
							}
							
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}						
					}	
					
					//Sumando todos los registros y pasadndo a porcentaje de aciertos, erradas y no contestadas
					double aciertos=0.0; double erradas=0.0; double nocontestadas=0.0; int cantidadpreguntas=0; int codigoCabecera=0; String ultimafecha="";
					DecimalFormat df = new DecimalFormat("#.00");
					for (int a=0; a<listarReporteUsuariosCursos.size();a++) {
						
						//System.out.println("Cabecera[" + listarReporteUsuariosCursos.get(a).getCodigoRespuestaCabecera() + "]  Aciertos:" + listarReporteUsuariosCursos.get(a).getCantidadCorrectas().toString() + " Erradas:" + listarReporteUsuariosCursos.get(a).getCantidadIncorrectas().toString() + " No Contestadas:" + listarReporteUsuariosCursos.get(a).getCantidadNocontestadas().toString() + " Preguntas:" + listarReporteUsuariosCursos.get(a).getCantidadPreguntas().toString());
						
						aciertos+=Double.parseDouble(listarReporteUsuariosCursos.get(a).getCantidadCorrectas().toString());
						erradas+=Double.parseDouble(listarReporteUsuariosCursos.get(a).getCantidadIncorrectas().toString());
						nocontestadas+=Double.parseDouble(listarReporteUsuariosCursos.get(a).getCantidadNocontestadas().toString());
						cantidadpreguntas+=Integer.parseInt(listarReporteUsuariosCursos.get(a).getCantidadPreguntas().toString());
						if (a==0) {
							codigoCabecera=listarReporteUsuariosCursos.get(a).getCodigoRespuestaCabecera();
							ultimafecha=listarReporteUsuariosCursos.get(a).getFechaRespuesta();
						} if (codigoCabecera<listarReporteUsuariosCursos.get(a).getCodigoRespuestaCabecera()) {
							codigoCabecera=listarReporteUsuariosCursos.get(a).getCodigoRespuestaCabecera();
							ultimafecha=listarReporteUsuariosCursos.get(a).getFechaRespuesta();
						}
					}
					//System.out.println("Aciertos[" + aciertos + "] Erradas[" + erradas + "] No Contestadas[" + nocontestadas + "] Total Preguntas[" + cantidadpreguntas + "]" );
					
					aciertos = Double.parseDouble(df.format((aciertos/cantidadpreguntas)*100).toString().replace(",", "."));
					erradas = Double.parseDouble(df.format((erradas/cantidadpreguntas)*100).toString().replace(",", "."));
					nocontestadas = Double.parseDouble(df.format((nocontestadas/cantidadpreguntas)*100).toString().replace(",", "."));
					
					rcd.setCorrectas(aciertos);
					rcd.setIncorrectas(erradas);
					rcd.setNocontestadas(nocontestadas);
					rcd.setPreguntas(cantidadpreguntas);
					rcd.setFechaUltima(ultimafecha);
					listarReporteUsuariosCursosDetalle.add(rcd);
					//System.out.println("Aciertos[" + aciertos + "] Erradas[" + erradas + "] No Contestadas[" + nocontestadas + "] Total Preguntas[" + cantidadpreguntas + "]" );
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				//System.out.println("--------- Finaliza Portafolio " + elements);
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		} finally {
			sessionDetalle.clear();
			sessionDetalle.close();
		}
		
		return listarReporteUsuariosCursosDetalle;
	}
	
	/*public List<Reportecursodetalle> ListarReporteUsuariosCursosDetalle(int codigoUsuario) {
		System.out.println("ListarReporteUsuariosCursosDetalle(int codigoUsuario)");
		List<Reportecursodetalle> listarReporteUsuariosCursosDetalle = null;
		Usuario usuario = null;		
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		String administrador = System.getProperty("usuario_administrador") != null ? System.getProperty("usuario_administrador") : "";
		
		//Primer Query directo al Respuestacabecera
		String hql = "";		
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
				rd.setCorrectas(Double.parseDouble(df.format(Double.parseDouble(elements[4].toString())/Double.parseDouble(elements[7].toString())*100).toString().replace(",", ".")));
				rd.setIncorrectas(Double.parseDouble(df.format(Double.parseDouble(elements[5].toString())/Double.parseDouble(elements[7].toString())*100).toString().replace(",", ".")));				
				rd.setNocontestadas(Double.parseDouble(df.format(Double.parseDouble(elements[6].toString())/Double.parseDouble(elements[7].toString())*100).toString().replace(",", ".")));
				if ((rd.getCorrectas().doubleValue() + rd.getIncorrectas().doubleValue() + rd.getNocontestadas().doubleValue()) != 100.00) {
					rd.setNocontestadas(Double.parseDouble(df.format(100.00 - (rd.getCorrectas().doubleValue() + rd.getIncorrectas().doubleValue())).toString().replace(",", ".")));
				}
				rd.setPreguntas(Integer.parseInt(elements[7].toString()));
				
				String hql1 = "SELECT max(r.codigoRespuestaCabecera) "
						+ "FROM Reporteusuarioscursos r " 
						+ "WHERE r.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion()
						+ "	AND r.codigoUsuario = " + codigoUsuario
						+ "	AND r.codigoPortafolio = " + elements[0];
				Query query1 = session.createQuery(hql1);
				List<Integer> res1 = query1.list();
				for (Integer elements1: res1){
					String hql2 = "SELECT r.fechaRespuesta "
							+ "FROM Reporteusuarioscursos r " 
							+ "WHERE r.codigoRespuestaCabecera = " + elements1;
					Query query2 = session.createQuery(hql2);
					List<String> res2 = query2.list();				
					// el que parsea
				    SimpleDateFormat parseador = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
				    // el que formatea
					SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");										
					for (String elements2: res2){						
						Date fechaResp = null;
						fechaResp = parseador.parse(elements2.toString());
						rd.setFechaUltima( formateador.format(fechaResp) );
					}
				}				
								
				/// Obteniendo el Ranking 
				List<Usuariospuesto> listadoRanking = new ArrayList<Usuariospuesto>();
				//Cursos Asignados al Usuario
				Integer aciertos = 0;
				//String hql10 = "SELECT distinct p.portafolioByCodigoPortafolio.codigoPortafolio " 
				//		+	"FROM Usuarioportafolio p " 
				//		+	"WHERE p.usuario.codigoUsuario = " + codigoUsuario
				//		+ 	"	AND p.nivel.codigoNivel = 3 ";
				//Query query10 = session.createQuery(hql10);
				//List<Integer> res10 = query10.list();
				//for (Integer elements10: res10){
					//System.out.println(".::. Cursos " + elements10.toString());
					System.out.println(".::. Cursos " + rd.getPortafolio().getCodigoPortafolio());
					//Consultar todos los usuarios que tienen el Curso Asignado
					String hql11 = "SELECT distinct rc.usuario.codigoUsuario " 
							+	"FROM Respuestacabecera rc " 
							+	"WHERE rc.portafolio.codigoPortafolio = " + rd.getPortafolio().getCodigoPortafolio() //elements10
							+	"ORDER BY rc.usuario.codigoUsuario "; 
					Query query11 = session.createQuery(hql11);
					List<Integer> res11 = query11.list();
					for (Integer elements11: res11){
						Usuariospuesto usupue = new Usuariospuesto();						
						//System.out.println(" --> Usuarios " + elements11.toString());
						usupue.setCodigoUsuario(elements11);
						//Seleccionamos el ultimo intento realizado
						String hql12 = "SELECT max(rc.codigoRespuestaCabecera) "
								+	"FROM Respuestacabecera rc "
								+	"WHERE rc.usuario.codigoUsuario = " + elements11;
						Query query12 = session.createQuery(hql12);
						List<Integer> res12 = query12.list();
						for (Integer elements12: res12){
							//System.out.println(" --> Maximo <-- " + elements12.toString());
							//Obtenemos el total de respuestas correctas
							String hql13 = "SELECT count(rd.flagAlternativaCorrecta) "
									+ "FROM Respuestadetalle rd "
									+ "WHERE rd.respuestacabecera = " + elements12;
							Query query13 = session.createQuery(hql13);
							List<Long> res13 = query13.list();
							for (Long elements13: res13){							
								if (elements13>0) {
									String hql14 = "SELECT sum(rd.flagAlternativaCorrecta)  as total " 
											+	"FROM Respuestadetalle rd " 
											+	"WHERE rd.respuestacabecera = " + elements12;
									Query query14 = session.createQuery(hql14);
									List<Long> res14 = query14.list();
									for (Long elements14: res14){
										aciertos = Integer.parseInt(elements14.toString()); 
									}								
								} else {
									aciertos = Integer.parseInt(elements13.toString());
								}
								//System.out.println(" --> Total Acertadas <-- " + aciertos.toString());
								usupue.setTotal(aciertos);
							}
				
						}
						listadoRanking.add(usupue);
					}
					//Ordenar ascendentemente un list
					//Iterator itListaRanking = listadoRanking.iterator();
					//while (itListaRanking.hasNext()) {
					//	Usuariospuesto elementoLista=(Usuariospuesto) itListaRanking.next();
					//	System.out.println(elementoLista.getCodigoUsuario() + " " + elementoLista.getTotal());
					//}
					Collections.sort(listadoRanking, new CursoComparatorByTotal(false));
					Iterator itListaRanking = listadoRanking.iterator();
					Integer posicion = 1;
					while (itListaRanking.hasNext()) {
						Usuariospuesto elementoLista=(Usuariospuesto) itListaRanking.next();
						System.out.println(elementoLista.getCodigoUsuario() + " " + elementoLista.getTotal());
						if (elementoLista.getCodigoUsuario().equals(rd.getUsuario().getCodigoUsuario())) {
							rd.setPuestos(posicion);
						} else {
							posicion++;
						}
					}
				//}				
			// Termina el proceso de Obteniendo Ranking 				
				listarReporteUsuariosCursosDetalle.add(rd);
			}								

			session.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}

		
		return listarReporteUsuariosCursosDetalle;
	}*/


	@Override
	public List<Reportecursodetalle> ListarReporteUsuariosCursosDetalleUnico(int codigoUsuario, int codigoCurso) {
		System.out.println("ListarReporteUsuariosCursosDetalleUnico(int codigoUsuario, int codigoCurso)");
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
				+	"ORDER BY rc.codigoRespuestaCabecera desc";
		
		try {
			Query query = session.createQuery(hql);
			List<Integer> res = query.list();
			listarReporteUsuariosCursosDetalle = new ArrayList<Reportecursodetalle>();
			double correctas = 0.0, incorrectas = 0.0, nocontestadas = 0.0;
			int cantidadPreguntas = 0;
			String fechaResp = "";
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
				
				// Consulta Fecha Respuestas por Intento
				String hql3 = "";
				hql3 = "SELECT rc.fechaRespuesta "
						+ "FROM Respuestacabecera rc " 
						+ "	LEFT JOIN rc.respuestadetalles rd "
						+ "WHERE rc.usuario.codigoUsuario = " + codigoUsuario
						+ "	AND rc.portafolio.codigoPortafolio = " + codigoCurso
						+ "	AND rc.codigoRespuestaCabecera = " + elements;
				
				try {
					Query query3 = session.createQuery(hql3);
					List<String> res3 = query3.list();
					for (String elements3: res3){
						fechaResp = elements3.toString();						
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
				rcd.setFechaUltima(fechaResp);
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
		
		List<Reportecursodetalle> listarReporteUsuariosCursosDetalleFiltro = new ArrayList<Reportecursodetalle>();
		for (int b=0; b<listarReporteUsuariosCursosDetalle.size(); b++)
		{
			Reportecursodetalle rcdf = new Reportecursodetalle();
			if (b<5) {
				rcdf.setCorrectas(listarReporteUsuariosCursosDetalle.get(b).getCorrectas());
				rcdf.setIncorrectas(listarReporteUsuariosCursosDetalle.get(b).getIncorrectas());
				rcdf.setNocontestadas(listarReporteUsuariosCursosDetalle.get(b).getNocontestadas());
				rcdf.setPreguntas(listarReporteUsuariosCursosDetalle.get(b).getPreguntas());
				rcdf.setFechaUltima(listarReporteUsuariosCursosDetalle.get(b).getFechaUltima());
				listarReporteUsuariosCursosDetalleFiltro.add(rcdf);
			}
		}
		listarReporteUsuariosCursosDetalle = listarReporteUsuariosCursosDetalleFiltro;
		
		return listarReporteUsuariosCursosDetalle;
	}


	public void prepararTabla() {
		Usuario usuario = null;		
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "";
		hql = "SELECT distinct rc.usuario.codigoUsuario, rc.portafolio.codigoPortafolio, rc.codigoRespuestaCabecera, "
				+ "(select u.institucion.codigoInstitucion from Usuario u where u.codigoUsuario = rc.usuario.codigoUsuario) "  
				+ "FROM Respuestacabecera rc INNER JOIN rc.portafolio p "
				+ "WHERE p.nivel.codigoNivel = 3"; //and rc.usuario.institucion.codigoInstitucion = " + usuario.getInstitucion().getCodigoInstitucion();
		
		try {
			Query query = session.createQuery(hql);
			List<Integer> res = query.list();
			
			
			
			System.out.println(res.size());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		
	}

	
}
