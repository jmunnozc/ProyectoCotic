package pe.com.cotic.test.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.com.cotic.test.dao.ResumenRespuestaDao;
import pe.com.cotic.test.modelo.Respuestacabecera;
import pe.com.cotic.test.modelo.Resumenrespuesta;
import pe.com.cotic.test.util.HibernateUtil;

public class ResumenRespuestaDaoImpl implements ResumenRespuestaDao{

	private Session session;
	
	@Override
	public List<Resumenrespuesta> listarResumenRespuesta(Respuestacabecera respuestacabecera) {
		List<Resumenrespuesta> listarResumenRespuesta = null;	
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		
		String hql = "SELECT CASE rd.flagAlternativaCorrecta WHEN 1 THEN 'CORRECTO' ELSE 'INCORRECTO' END AS RespuestaCorrecta, " 
					+ " count(rd.flagAlternativaCorrecta) AS Total "
					+ " FROM Respuestacabecera AS rc INNER JOIN rc.respuestadetalles AS rd " 
					+ " WHERE rc.codigoRespuestaCabecera =:respuestacabecera "
					+ " GROUP BY rd.flagAlternativaCorrecta";

		try {
			Query query = session.createQuery(hql);
			query.setString("respuestacabecera", respuestacabecera.getCodigoRespuestaCabecera().toString());
			List<Object[]> res = query.list();

			listarResumenRespuesta = new ArrayList<Resumenrespuesta>();
			for (Object[] elements: res){
				Resumenrespuesta rr = new Resumenrespuesta();
				/*System.out.println("Dato1 : " + String.valueOf(String.valueOf(elements[0])));
				System.out.println("Dato2 : " + String.valueOf(String.valueOf(elements[1])));*/
				rr.setRespuestaCorrecta(elements[0].toString());
				rr.setTotal(elements[1].toString());
				listarResumenRespuesta.add(rr);
			}						
			session.close();			

		} catch (Exception e) {
			System.out.println(e.getMessage());
			transaction.rollback();
		}

		return listarResumenRespuesta;
	}

}
