package ar.com.santanderrio.obp.servicios.premify.bo;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.premify.bo.impl.MemberBOImpl;
import ar.com.santanderrio.obp.servicios.premify.contracts.MemberResponse;
import ar.com.santanderrio.obp.servicios.premify.dao.PremifyDAO;
import ar.com.santanderrio.obp.servicios.premify.entity.MemberEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MemberBOImplTest {

    @InjectMocks
    private MemberBOImpl memberBO;

    @Mock
    PremifyDAO premifyDAO;

    @Test
    public void getMemberOk() throws DAOException {
        String nup = "12345678";
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.points = 1234;

        // When
        Mockito.when(premifyDAO.getMember(Matchers.any(String.class))).thenReturn(memberResponse);
        MemberEntity member = memberBO.getMember(nup);

        // Then
        assertEquals(memberResponse.points, member.getPoints());
    }

    @Test
    public void getMemberThrowException() throws DAOException {
        String nup = "12345678";
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.points = 1234;
        Integer expectedPoints = 0;

        // When
        Mockito.when(premifyDAO.getMember(nup)).thenThrow(new DAOException());
        MemberEntity member = memberBO.getMember(nup);

        // Then
        assertEquals(expectedPoints, member.getPoints());
    }

    @Test
    public void getMember_whenDAOReturnNull_ExpectedCeroPoints() throws DAOException {
        String nup = "12345678";
        Integer expectedPoints = 0;

        // When
        Mockito.when(premifyDAO.getMember(Matchers.any(String.class))).thenReturn(null);
        MemberEntity member = memberBO.getMember(nup);

        // Then
        assertEquals(expectedPoints, member.getPoints());
    }
}
