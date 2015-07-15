package br.com.synchro.jsf;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.synchro.domain.ModeloDof;
import br.com.synchro.service.ModeloService;
import br.com.synchro.service.ModeloServiceImpl;

/**
 * @author cvs
 * @create Jul 8, 2015
 * 
 *         Simple Managed Bean implementation for Modelo DOF
 */
@ManagedBean
@SessionScoped
public class ModeloBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private ModeloService modeloService;

    private String modelos;

    private List<ModeloDof> listModelo;

    /**
     * Default Constructor to instantiate attributes
     */
    public ModeloBean() {
	this.modeloService = new ModeloServiceImpl();
    }

    /**
     * @return the listModelo
     */
    public List<ModeloDof> getListModelo() {
	return listModelo;
    }

    /**
     * @return the modelos
     */
    public String getModelos() {
	return modelos;
    }

    /**
     * List all Modelo DOF objects found to be exhibited on the screen
     */
    public void listarModelos() {
	this.listModelo = this.modeloService.listarModelosDof();
    }

    /**
     * @param pListModelo
     *            the listModelo to set
     */
    public void setListModelo(final List<ModeloDof> pListModelo) {
	listModelo = pListModelo;
    }

    /**
     * @param pModelos
     *            the modelos to set
     */
    public void setModelos(final String pModelos) {
	modelos = pModelos;
    }
}
