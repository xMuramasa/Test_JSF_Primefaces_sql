package com.TestDB.Demo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class VistaEmpleadoManagedBean {

	private List<Empleado> empleados = new ArrayList<Empleado>();

	public VistaEmpleadoManagedBean() {

	}

	@PostConstruct
	public void GenerarListaEmpleados() {
		for(int i=0; i < 10; i++) {
			
			Empleado temp = new Empleado();
			temp.setEmpleadoId(String.valueOf(i));
			temp.setNombreEmpleado("Empleado_"+String.valueOf(i));

			this.empleados.add(temp);
		}
	}


	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
}
