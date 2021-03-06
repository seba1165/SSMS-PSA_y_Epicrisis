/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.region;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author obi
 */
@Stateless
public class regionFacade extends AbstractFacade<region> implements regionFacadeLocal {

    @PersistenceContext(unitName = "cl.diinf_SSMS-PSA_y_Epicrisis-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public regionFacade() {
        super(region.class);
    }
    
}
