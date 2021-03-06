package managedbeans;

import entities.audit;
import entities.cesfam;
import entities.clap;
import managedbeans.util.JsfUtil;
import managedbeans.util.JsfUtil.PersistAction;
import sessionbeans.auditFacadeLocal;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named("auditController")
@SessionScoped
public class auditController implements Serializable {

    @EJB
    private sessionbeans.auditFacadeLocal ejbFacade;
    private List<audit> items = null;
    private audit selected;
    private audit antiguo;
    private int sexo;

    @Inject
    AuditoriaController auditoriaCtrl;
    
    public auditController() {
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public audit getSelected() {
        return selected;
    }

    public void setSelected(audit selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private auditFacadeLocal getFacade() {
        return ejbFacade;
    }

    public audit prepareCreate() {
        selected = new audit();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        audit nuevo=(audit) selected.clone();
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("auditCreated"));
        auditoriaCtrl.audit((Object) new audit(), nuevo, "CREATE", "audit");
        antiguo=(audit) selected.clone();
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void prepareUpdate(){
        if(selected!=null){
            antiguo=(audit) selected.clone();
        }
    }
    
    
    public void update() {
        audit nuevo=(audit) selected.clone();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("auditUpdated"));
        auditoriaCtrl.audit(antiguo, nuevo, "UPDATE", "audit");
        antiguo=(audit) selected.clone();
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("auditDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<audit> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<audit> getItemsPorClap(clap clap) {
        List<audit> itemsPorClap;
        itemsPorClap = getFacade().findbyClap(clap);
        return itemsPorClap;
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

    public audit getaudit(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<audit> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<audit> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public boolean esIntervencionMinima() {
        int puntaje = selected.getP1()+selected.getP2()+selected.getP3();
        if (this.sexo == 1) {
            if (puntaje <=4){
                return true;
            }
        }else{
            if (puntaje<=3) {
                return true;
            }
        }
        return false;
    }

    @FacesConverter(forClass = audit.class)
    public static class auditControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            auditController controller = (auditController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "auditController");
            return controller.getaudit(getKey(value));
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
            if (object instanceof audit) {
                audit o = (audit) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), audit.class.getName()});
                return null;
            }
        }
        
    }
    
    public int tipoIntervencion(){
        int puntaje = calculaPuntaje();
        if (puntaje <= 7) {
            //System.out.println("Intervencion Minima");
            return 0;
        }else if (puntaje >= 8 && puntaje <= 15){
            //System.out.println("Intervencion Breve");
            return 1;
        }else{
            //System.out.println("Derivacion Asistida");
            return 2;
        }
    }

    private int calculaPuntaje() {
        //System.out.println(selected.getP1()+selected.getP2()+selected.getP3()+selected.getP4()+selected.getP5()+selected.getP6()+selected.getP7()+selected.getP8()+selected.getP9()+selected.getP10());
        return selected.getP1()+selected.getP2()+selected.getP3()+selected.getP4()+selected.getP5()+selected.getP6()+selected.getP7()+selected.getP8()+selected.getP9()+selected.getP10();
    }
}
