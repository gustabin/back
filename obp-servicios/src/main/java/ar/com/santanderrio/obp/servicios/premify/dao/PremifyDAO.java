package ar.com.santanderrio.obp.servicios.premify.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.premify.contracts.MemberResponse;
import ar.com.santanderrio.obp.servicios.premify.contracts.NotificacionResponse;

import java.util.List;

public interface PremifyDAO {
    List<NotificacionResponse> getNotifications(String nup) throws DAOException;
    MemberResponse getMember(String nup) throws DAOException;
}
