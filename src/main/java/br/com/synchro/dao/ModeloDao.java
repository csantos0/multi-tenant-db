package br.com.synchro.dao;

import java.util.List;

import br.com.synchro.domain.ModeloDof;

/**
 * @author cvs
 * @create Jul 15, 2015
 * 
 *         Simple DAO for Modelo Domain
 */
public interface ModeloDao {

    /**
     * Query database for modelos DOF
     * 
     * @return lista de modelos DOF
     */
    public List<ModeloDof> listarModelosDof();
}
