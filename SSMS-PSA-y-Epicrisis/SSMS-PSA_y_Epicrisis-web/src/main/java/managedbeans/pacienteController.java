package managedbeans;

import entities.cesfam;
import entities.comuna;
import entities.paciente;
import java.io.IOException;
import java.io.OutputStream;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.pacienteFacadeLocal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

@Named("pacienteController")
@SessionScoped
public class pacienteController implements Serializable {

    @EJB
    private sessionbeans.pacienteFacadeLocal ejbFacade;
    private List<paciente> items = null;
    private paciente selected;
    private paciente antiguo;
    private int RUN;
    private String DV;
    private cesfam cesfam = null;
    private List<paciente> itemsRiesgo = null;
    private List<paciente> itemsCESFAM = null;
    
    @Inject
    private LoginController loginCtrl;
    
    @Inject
    private clapController clapCtrl;
    
    @Inject
    private AuditoriaController auditoriaCtrl;
    
    
    public pacienteController() {
    }

    public int getRUN() {
        return RUN;
    }

    public void setRUN(int RUN) {
        this.RUN = RUN;
    }

    public String getDV() {
        return DV;
    }

    public void setDV(String DV) {
        this.DV = DV;
    }

    public List<paciente> getItemsCESFAM() {
        return itemsCESFAM;
    }

    public void setItemsCESFAM(List<paciente> itemsCESFAM) {
        this.itemsCESFAM = itemsCESFAM;
    }
    
    public cesfam getCesfam() {
        return cesfam;
    }

    public void setCesfam(cesfam cesfam) {
        this.cesfam = cesfam;
    }
 
    public paciente getSelected() {
        return selected;
    }

    public void setSelected(paciente selected) {
        this.selected = selected; 
   }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private pacienteFacadeLocal getFacade() {
        return ejbFacade;
    }

    public boolean esFONASA(){
        if(selected.getPrevision()!=null){
            return selected.getPrevision().getNombre().equals("FONASA");
        }
        return false;
    }
    
    public boolean regionSelected(){
        if(selected.getRegion_residencia()!=null){
            return true;
        }
        return false;
    }
    
    public void prepareUpdate(){
        if(selected!=null){
            antiguo=(paciente) selected.clone();
        }
    }
    
    public paciente prepareCreate() {
        selected = new paciente();
        initializeEmbeddableKey();
        selected.setRUN(RUN);
        selected.setDV(DV);
        selected.setCesfam(loginCtrl.getUsuarioLogueado().getCESFAM());
        return selected;
    }

    public String create() {
        FacesContext context;
        context = FacesContext.getCurrentInstance();
        FacesMessage message = null; 
        
        boolean flag = verificaRun();
                
        if (flag) {
            selected.setEstado("Ingresado");
            selected.setFecha_estado(new java.util.Date());
            paciente nuevo=(paciente) selected.clone();
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("pacienteCreated"));
            auditoriaCtrl.audit((Object) new paciente(), nuevo, "CREATE", "paciente");
            prepareUpdate();
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
                selected = getFacade().findbyRUN(selected.getRUN()).get(0);
                clapCtrl.setSelected(null);
                return "/faces/paciente/View.xhtml";
            }else{
                return "/faces/paciente/Create.xhtml";
            }
        }else{
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: el RUN ingresado no es válido",  "El rut ingresado no es válido.") );
            return "/faces/paciente/Create.xhtml";
        }
    }

    public void riesgosNoTratados() {
        prepareUpdate();
        selected.setEstado("Riesgos no Tratados");
        selected.setFecha_estado(new java.util.Date());
        paciente nuevo=(paciente) selected.clone();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("pacienteUpdated"));
        auditoriaCtrl.audit(antiguo, nuevo, "UPDATE", "paciente");
        prepareUpdate();
    }

    public void tratarRiesgo(){
        prepareUpdate();
        selected.setEstado("Riesgos tratados o en tratamiento");
        selected.setFecha_estado(new java.util.Date());
        paciente nuevo=(paciente) selected.clone();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("pacienteUpdated"));
        auditoriaCtrl.audit(antiguo, nuevo, "UPDATE", "paciente");
        prepareUpdate();
        clapCtrl.setActividadElegida(true);
    }
    
    public void sinRiesgo(){
        prepareUpdate();
        selected.setEstado("Sin riesgo");
        selected.setFecha_estado(new java.util.Date());
        paciente nuevo=(paciente) selected.clone();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("pacienteUpdated"));
        auditoriaCtrl.audit(antiguo, nuevo, "UPDATE", "paciente");
        prepareUpdate();
    }
    
    public void clapIncompleto(){
        prepareUpdate();
        selected.setEstado("Clap Incompleto");
        selected.setFecha_estado(new java.util.Date());
        paciente nuevo=(paciente) selected.clone();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("pacienteUpdated"));
        auditoriaCtrl.audit(antiguo, nuevo, "UPDATE", "paciente");
        prepareUpdate();
    }
    
    public String update() {
        paciente nuevo=(paciente) selected.clone();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("pacienteUpdated"));
        auditoriaCtrl.audit(antiguo, nuevo, "UPDATE", "paciente");
        prepareUpdate();
        return "/faces/paciente/View.xhtml?faces-redirect=true";
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("pacienteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<paciente> getItemsRiesgoHome(int num) {
        //Si es super usuario, no hay filtro por cesfam
        Date fecha = new java.util.Date();
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        c.add(Calendar.DATE, -60);
        fecha = c.getTime();
        
        if (loginCtrl.esSuperUsuario()) {
            if (num == 1) {
               itemsRiesgo = getFacade().findbyEstadoFecha("Riesgos no Tratados", fecha);
               return itemsRiesgo;
           }else{
               return itemsRiesgo;
           }   
        }else{
            if (num == 1) {
                itemsRiesgo = getFacade().findbyEstadoFecha("Riesgos no Tratados",fecha);
                return itemsRiesgo;
            }else{
                return itemsRiesgo;
            }
        }
    }
    
    public List<paciente> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                    if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public paciente getpaciente(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<paciente> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<paciente> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public boolean verificaRun(){
        String rut2 = "";

        rut2 = String.valueOf(selected.getRUN());
 
        rut2 = Integer.toString(selected.getRUN());
        
        boolean flag = false; 
        String rut = rut2.trim(); 

        String posibleVerificador = selected.getDV().trim(); 
        int cantidad = rut.length(); 
        int factor = 2; 
        int suma = 0; 
        String verificador = ""; 

        for(int i = cantidad; i > 0; i--) 
        { 
            if(factor > 7) 
            { 
                factor = 2; 
            } 
            suma += (Integer.parseInt(rut.substring((i-1), i)))*factor; 
            factor++; 

        } 
        verificador = String.valueOf(11 - suma%11); 
        if(verificador.equals(posibleVerificador)) 
        { 
            flag = true; 
        } 
        else 
        { 
            if((verificador.equals("10")) && (posibleVerificador.toLowerCase().equals("k"))) 
            { 
                flag = true; 
            } 
            else 
            { 
                if((verificador.equals("11") && posibleVerificador.equals("0"))) 
                { 
                    flag = true; 
                } 
                else 
                { 
                    flag = false; 
                } 
            } 
        }
        return flag;
    }

    
    @FacesConverter(forClass = paciente.class)
    public static class pacienteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            pacienteController controller = (pacienteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pacienteController");
            return controller.getpaciente(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof paciente) {
                paciente o = (paciente) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), paciente.class.getName()});
                return null;
            }
        }

    }
    
    public List<comuna> completeComuna(String query) {
        List<comuna> allComunas = selected.getRegion_residencia().getComunas();
        List<comuna> filteredComunas = new ArrayList<comuna>();
         
        for (int i = 0; i < allComunas.size(); i++) {
            comuna Comuna = allComunas.get(i);
            if(Comuna.getNombre().toLowerCase().startsWith(query.toLowerCase())) {
                filteredComunas.add(Comuna);
            }
        }
         
        return filteredComunas;
    }
    
    public paciente prepareEdit() {
        selected = getSelected();
        return selected;
    }
    
    public List<paciente> completePaciente(String query) {
        List<paciente> allPacientes = getItems();
        List<paciente> filteredPacientes = new ArrayList<paciente>();
         
        for (int i = 0; i < allPacientes.size(); i++) {
            paciente Paciente = allPacientes.get(i);
            String rut = String.valueOf(Paciente.getRUN());
            if(rut.startsWith(query)) {
                filteredPacientes.add(Paciente);
            }
        }
        return filteredPacientes;
    }
    
    public String buscarPorRUN(){
        selected = new paciente();
        selected.setRUN(RUN);
        selected.setDV(DV);
        boolean flag = verificaRun();
        selected = null;
        if (!flag) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "RUN Invalido",  null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "/faces/paciente/Buscar.xhtml";
        }
        List<paciente> pacientes = getFacade().findbyRUN(RUN);;      
        if (pacientes.isEmpty()) {
            /////////////////
            //PARTE DE FONASA
            /////////////////
            //Si no encuentra en Fonasa, se crea desde 0
            if (true) {
            //Si es funcionario o encargado se puede crear
                if (loginCtrl.esFuncionario() || loginCtrl.esEncargadoPrograma()) {
                    prepareCreate();
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no Encontrado. Cree el registro",  null);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return "/faces/paciente/Create.xhtml";
                //Si es SuperUsuario, no puede crear
                }else{
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no Encontrado",  null);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return "/faces/paciente/Buscar.xhtml";
                }       
            }else{
                //Si encuentra en fonasa
                selected = new paciente();
                initializeEmbeddableKey();
                return "/faces/paciente/Create.xhtml"; 
            }
            
        }else{
            paciente paciente = pacientes.get(0);
            if (!paciente.getDV().equals(DV)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "RUN Invalido",  null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return "/faces/paciente/Buscar.xhtml";
            }else{
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Encontrado",  null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                selected = paciente;
                return "/faces/paciente/View.xhtml";
            }
        }
    }
    
    public String pacienteClap(paciente paciente){
        setSelected(paciente);
        return "/paciente/View?faces-redirect=true";
    }
    
    public String reporteCESFAM(){
        setItemsCESFAM(getFacade().findbyCESFAM(cesfam));
        return "/faces/reportes/resultado_poblacion.xhtml";
    }
    
    public int getTamano(){
        if (loginCtrl.esSuperUsuario()) {
            itemsCESFAM = getFacade().findbyCESFAM(cesfam);
        }else{
            itemsCESFAM = getFacade().findbyCESFAM(loginCtrl.getUsuarioLogueado().getCESFAM());
        }
        return itemsCESFAM.size();        
    }
    
    public void generarCSV(int condicion)
    {
        String nombreCesfam;
        String title;
        if (condicion == 0 ) {
            title = "poblacion.csv";
        }else{
            if (loginCtrl.esSuperUsuario()) {
                nombreCesfam = getCesfam().getNombre();
                title = nombreCesfam+".csv";
            }else{
                nombreCesfam = loginCtrl.getUsuarioLogueado().getCESFAM().getNombre();
                title = nombreCesfam+".csv";
            }
        }
        
        try
        {
            
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

            response.reset();
            response.setContentType("text/comma-separated-values");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + title + "\"");
            
            OutputStream output = response.getOutputStream();
            
            //Nombre de las columnas
            output.write("RUN;".getBytes());
            output.write("Nombre".getBytes());
            output.write("\n".getBytes());
            
            if (condicion == 0) {
                items = getItems();
                for (int i =0;i<items.size();i++) {
                    paciente item =items.get(i);
                    String fila = item.getRUN()+"-"+item.getDV()+";"+item.getNombres()+" "+item.getPrimer_apellido()+" "+item.getSegundo_apellido();
                    output.write(fila.getBytes());
                    output.write("\n".getBytes());
                }
            }else{
                for (int i =0;i<itemsCESFAM.size();i++) {
                    paciente item =itemsCESFAM.get(i);
                    String fila = item.getRUN()+"-"+item.getDV()+";"+item.getNombres()+" "+item.getPrimer_apellido()+" "+item.getSegundo_apellido();
                    output.write(fila.getBytes());
                    output.write("\n".getBytes());
                }
            }

            output.flush();
            output.close();
            
            fc.responseComplete();

            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}