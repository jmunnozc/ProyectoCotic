package pe.com.cotic.test.daoImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.cotic.test.dao.ReporteUsuariosCursosPuestosDao;
import pe.com.cotic.test.modelo.Institucion;
import pe.com.cotic.test.modelo.Reporteusuarioscursospuestos;
import pe.com.cotic.test.modelo.Usuario;
import pe.com.cotic.test.modelo.Usuarioportafolio;
import pe.com.cotic.test.modelo.Usuariospuesto;
import pe.com.cotic.test.util.HibernateUtil;

public class ReporteUsuariosCursosPuestosDaoImpl implements ReporteUsuariosCursosPuestosDao {

	private Session session;
	
	@Override
	public List<Reporteusuarioscursospuestos> listarReporteUsuariosCursosPuestos() {
		
		Usuario usuario = null;
		Usuarioportafolio usuarioPortafolio = null;
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		List<Reporteusuarioscursospuestos> listarReporteUsuariosCursosPuestos = null;
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
				int correctas = 0, incorrectas = 0;
				int totalPreguntas = 0;
				List<Usuariospuesto> listarUsuarioPuesto = new ArrayList<Usuariospuesto>();
				for (int b=0; b<listarUsuariosPuestos.size(); b++) {
					Usuariospuesto up = new Usuariospuesto();
					up.setCodigoUsuario(listarUsuariosPuestos.get(b).getCodigoUsuario());
					up.setCodigoPortafolio(listarUsuariosPuestos.get(b).getCodigoPortafolio());
					up.setCodigoRespuestaCabecera(listarUsuariosPuestos.get(b).getCodigoRespuestaCabecera());
					up.setCodigoRespuestaDetalle(listarUsuariosPuestos.get(b).getCodigoRespuestaDetalle());
					up.setCodigoPregunta(listarUsuariosPuestos.get(b).getCodigoPregunta());
					up.setCodigoAlternativa(listarUsuariosPuestos.get(b).getCodigoAlternativa());
					up.setFlagAlternativa(listarUsuariosPuestos.get(b).getFlagAlternativa());
					if (totalPreguntas == 0) {
						listarUsuarioPuesto.add(up);
						totalPreguntas++;
					} else if (up.getCodigoUsuario()==listarUsuariosPuestos.get(b-1).getCodigoUsuario()) {
						listarUsuarioPuesto.add(up);
						totalPreguntas++;
					} else {
						//System.out.println(Collections.max(listarUsuarioPuesto));
						listarUsuarioPuesto = new ArrayList<Usuariospuesto>();
						totalPreguntas = 0;
					}
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


}
