package br.com.synchro.service;

import java.util.List;

import br.com.synchro.dao.ModeloDao;
import br.com.synchro.dao.ModeloDaoImpl;
import br.com.synchro.domain.ModeloDof;

/**
 * @author cvs
 * @create Jul 15, 2015
 * 
 *         Simple Service implementation for Modelo operations
 */
public class ModeloServiceImpl implements ModeloService {

    private ModeloDao modeloDao;

    /**
     * Default Constructor to instantiate attributes
     */
    public ModeloServiceImpl() {
	this.modeloDao = new ModeloDaoImpl();
    }

    /*
     * (non-Javadoc)
     * @see br.com.synchro.service.ModeloService#listarModelosDof()
     */
    @Override
    public List<ModeloDof> listarModelosDof() {
	return this.modeloDao.listarModelosDof();
    }

}
