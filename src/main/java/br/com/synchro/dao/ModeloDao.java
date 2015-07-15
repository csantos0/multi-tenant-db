package br.com.synchro.dao;

import java.util.List;

import br.com.synchro.domain.ModeloDof;

/**
 * @author cvs
 * @create Jul 15, 2015
 */
public interface ModeloDao {

    /**
     * @return lista de modelos DOF
     */
    public List<ModeloDof> listarModelosDof();
}
