package pe.com.cotic.test.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("estadoConverter")
public class EstadoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String estado = "";
		
		if (value != null) {
			estado = (String) value;
			switch (estado) {
			case "1":
				estado = "ACTIVO";
				break;
			case "2":
				estado = "INACTIVO";
				break;
			default:
				break;
			}
		}
		return estado;
	}

}
