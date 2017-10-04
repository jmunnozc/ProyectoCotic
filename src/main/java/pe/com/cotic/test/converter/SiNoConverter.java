package pe.com.cotic.test.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("sinoConverter")
public class SiNoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String estado = "";
		
		if (value.toString() != null) {
			estado = value.toString();
			switch (Integer.parseInt(estado)) {
			case 1:
				estado = "SI";
				break;
			case 0:
				estado = "NO";
				break;
			default:
				break;
			}
		}
		return estado;
	}

}
