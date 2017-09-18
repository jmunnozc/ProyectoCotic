package pe.com.cotic.test.dao;

import java.util.List;

import pe.com.cotic.test.modelo.Alternativa;
import pe.com.cotic.test.modelo.Pregunta;

public interface AlternativaDao {

	public List<Alternativa> ListarAlternativas();
	public boolean grabarAlternativa(Alternativa alternativa);
	public boolean modificarAlternativa(Alternativa alternativa);
	public boolean eliminarAlternativa(Alternativa alternativa);

	public List<Pregunta> ListarPreguntas(Alternativa alternativa);
	public List<Alternativa> ListarAlternativasPregunta(Pregunta pregunta);

}
