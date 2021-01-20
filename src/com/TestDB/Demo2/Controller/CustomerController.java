package com.TestDB.Demo2.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

import com.TestDB.Demo2.DAO.CustomerDAO;
import com.TestDB.Demo2.DAO.IMP.CustomerDaoImp;
import com.TestDB.Demo2.Model.Customer;

@ManagedBean(name = "customerController")
@Named
@RequestScoped
public class CustomerController implements Serializable{

	private String name;

	private Customer temp = new Customer();
	
	private List<String> genero;

	private List<Customer> customers;
	private List<Customer> filteredCustomers;
	private CustomerDAO dao = new CustomerDaoImp();

	private PieChartModel pieModel2;

	private LineChartModel lineModel2;

	private LineChartModel zoomModel;

	public CustomerController() {
	}

	@PostConstruct
	public void init() {
		createPieModel2();
		createLineModels();
		genero = new ArrayList<String>();
		genero.add("Masculino");
		genero.add("Femenino");
		genero.add("Otro");
		
		try {
			customers = dao.getFilter(name);
			
			Map<String, String> params = FacesContext.getCurrentInstance().
	                   getExternalContext().getRequestParameterMap();
			String id = params.get("id");
			
			if(id != null) {
				this.getCustomer(Long.parseLong(id));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String AddCustomer() {
		try {
			dao.AddCustomer(temp);
			return "Customers.xhtml?faces-redirect=true"; 

		} catch (Exception e) {
			System.out.println("Cant add customer");
			return "Not Work";
		}
	}
	
	public void getCustomer(Long id) {
		try {
			this.temp = dao.getCustomer(id);
		} catch (Exception e) {
			System.out.println("Cant get customer");
		}
	}

	public String DeleteCustomer(Customer customer) {
		try {
			dao.DeleteCustomer(customer);
			return "Customers.xhtml?faces-redirect=true"; 
		} catch (Exception e) {
			System.out.println("Cant remove customer");
			return "Not Work";
		}
	}
	
	public String UpdateHandler(Customer customer) {
		this.setTemp(customer);
		return "ModifyCustomer.xhtml?faces-redirect=true&id="+customer.getId();
	}
	
	public String UpdateCustomer() {
		try {
			
			System.out.println(temp.toString());
			dao.UpdateCustomer(temp);
			return "Customers.xhtml?faces-redirect=true"; 
		} catch (Exception e) {
			System.out.println("Cant upadte customer");
			return "Not Work";
		}
	}
	
	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
 
        Customer c = (Customer) value;
        return c.getEmail().toLowerCase().contains(filterText)
                || c.getFirstName().toLowerCase().contains(filterText)
                || c.getGender().toLowerCase().contains(filterText);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer getTemp() {
		return temp;
	}

	public void setTemp(Customer temp) {
		this.temp = temp;
	}

	public List<Customer> getFilteredCustomers() {
		return filteredCustomers;
	}

	public void setFilteredCustomers(List<Customer> filteredCustomers) {
		this.filteredCustomers = filteredCustomers;
	}
	
	public String returnToVistaCustomers() {
		return "Customers.xhtml?faces-redirect=true";
	}
	
	public String returnToVistaGraphs() {
		return "graphsCustomers.xhtml?faces-redirect=true";
	}

	public List<String> getGenero() {
		return genero;
	}

	public void setGenero(List<String> gender) {
		this.genero = gender;
	}

	@Override
	public String toString() {
		return "CustomerController [gender=" + genero + "]";
	}
	
	
	
    public PieChartModel getPieModel2() {
		return pieModel2;
	}

	public void setPieModel2(PieChartModel pieModel2) {
		this.pieModel2 = pieModel2;
	}
	
	

	public LineChartModel getLineModel2() {
		return lineModel2;
	}

	public void setLineModel2(LineChartModel lineModel2) {
		this.lineModel2 = lineModel2;
	}

	public LineChartModel getZoomModel() {
		return zoomModel;
	}

	public void setZoomModel(LineChartModel zoomModel) {
		this.zoomModel = zoomModel;
	}

	private void createPieModel2() {
        pieModel2 = new PieChartModel();
 
        try {
			pieModel2.set("Masculino", dao.countGenero("Masculino"));
		} catch (Exception e) {
			pieModel2.set("Masculino", 0);
		}
        try {
			pieModel2.set("Femenino", dao.countGenero("Femenino"));
		} catch (Exception e) {
			pieModel2.set("Femenino", 0);
		}
        try {
			pieModel2.set("Otro", dao.countGenero("Otro"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			pieModel2.set("Otro", 0);
		}
 
        pieModel2.setTitle("Géneros Customers");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
        pieModel2.setShadow(false);
    }
	
    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();
        List<String> dates = new ArrayList<String>();
        
        try {
			dates = dao.getColumn("created_date");
			System.out.println(dates);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        ChartSeries boys = new ChartSeries();
        
        for (int i=0; i< dates.size();i++) {
        	String[] arr = dates.get(i).split(" ");
        	boys.set(arr[0], Integer.parseInt(arr[2]));
        }
 
        model.addSeries(boys);
 
        return model;
    }
	
    private void createLineModels() {
 
        lineModel2 = initCategoryModel();
        lineModel2.setTitle("Nacimientos Customers");
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Years"));
        Axis yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(10);
        
        zoomModel = initCategoryModel();
        zoomModel.setTitle("Zoom");
        zoomModel.setZoom(true);
        zoomModel.setLegendPosition("e");
        yAxis = zoomModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
    }


}
