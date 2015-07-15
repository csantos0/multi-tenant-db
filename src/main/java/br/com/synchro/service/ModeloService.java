package br.com.synchro.service;

import java.util.List;

import br.com.synchro.domain.ModeloDof;

/**
 * @author cvs
 * @create Jul 15, 2015
 * 
 *         Simple Service for Modelo operations
 */
public interface ModeloService {

    /**
     * List all Modelo DOFs found
     * 
     * @return lista de modelos DOF
     */
    public List<ModeloDof> listarModelosDof();
}
