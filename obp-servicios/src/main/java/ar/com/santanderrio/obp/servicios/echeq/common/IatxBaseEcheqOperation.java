package ar.com.santanderrio.obp.servicios.echeq.common;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAO;

import java.text.ParseException;

public abstract class IatxBaseEcheqOperation extends AbstractOperacionECheq {

    protected final ECheqDAO eCheqDAO;

    protected IatxBaseEcheqOperation(ECheqDAO eCheqDAO) {
        this.eCheqDAO = eCheqDAO;
    }

    @Override
    public void execute() throws DAOException, ParseException {
        this.operationResult = eCheqDAO.ejecutar(cliente, getInEntity());
    }
}
