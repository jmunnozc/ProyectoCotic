package pe.com.cotic.test.modelo;

public class Resumenrespuesta implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String respuestaCorrecta;
	private String total;
	
	public Resumenrespuesta() {
		
	}

	public String getRespuestaCorrecta() {
		return respuestaCorrecta;
	}

	public void setRespuestaCorrecta(String respuestaCorrecta) {
		this.respuestaCorrecta = respuestaCorrecta;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	

}
