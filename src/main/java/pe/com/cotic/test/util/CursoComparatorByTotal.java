package pe.com.cotic.test.util;

import java.util.Comparator;

import pe.com.cotic.test.modelo.Usuariospuesto;

public class CursoComparatorByTotal implements Comparator<Usuariospuesto> {
	private boolean asc;
	public CursoComparatorByTotal(boolean asc){
		this.asc = asc;
	}
	
	@Override
	public int compare(Usuariospuesto o1, Usuariospuesto o2) {
		// TODO Auto-generated method stub
		int ret;
		if (asc) {
			ret = o1.getTotal().compareTo(o2.getTotal());
		} else {
			ret = o2.getTotal().compareTo(o1.getTotal());
		}
		return ret;
	}

}
