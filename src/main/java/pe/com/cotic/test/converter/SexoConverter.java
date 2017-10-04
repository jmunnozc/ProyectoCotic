package pe.com.cotic.test.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("sexoConverter")
public class SexoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String sexo = "";
		
		if (value != null) {
			sexo = (String) value;
			if (sexo.equals("M")) sexo = "MASCULINO";
			if (sexo.equals("F")) sexo = "FEMENINO";
		}
		return sexo;
	}

}
