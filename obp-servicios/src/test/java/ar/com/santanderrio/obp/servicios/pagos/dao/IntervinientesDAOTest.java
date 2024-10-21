package ar.com.santanderrio.obp.servicios.pagos.dao;

import org.junit.Before;
import org.junit.Test;
import org.beanio.InvalidRecordException;
import org.junit.Assert;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.IntervinientesDAOImpl;
import ar.com.santanderrio.obp.servicios.pagos.entities.IntervinientesOutEntity;


public class IntervinientesDAOTest extends IatxBaseDAOTest {

	private IntervinientesDAOImpl intervinientesDAOImpl;

	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		intervinientesDAOImpl = new IntervinientesDAOImpl();
	}

    
    
    @Test
    public void test() throws DAOException {

        String tramaResponse ="200000000000P04HTML00099000103FREEUSER  XXXXXXXX00360054000000192016083116015500000000IBF37371000000000000CNSINTERVI1200000            00240000    00        00 015155749201703021511530000000000                            000000000000000XXXX000000000000000XXXX000000000000000XXXX000000000000000XXXX000000000000000XXXX00DHõ0088000000000õDR EIZAGUIRRE       õ03882õ   õ  õSAN JUSTO    õ1754õBõFNFõ02õ080õ47958065õ072õ004õ00313687õTIõ001õAõ31129999õ  õOSEWSKYZ                      õRAIQUEN ALVARO                õEõ00008257248õNõTõ20082572482õFõ01031946õ00000õSõ2010-07-08-14.18.49.557268õ  õ  õ   õ   õ  õ  õ23101995õ00430375õCTõ002õAõ31129999õ  õSTANCATO DE LOPRESTI          õLISA IDA                      õNõ00005866693õNõDõ27058666934õFõ18031948õ00000õSõ2010-07-08-14.18.49.545283õ  õ  õ   õ   õ  õ  õ22111995õ00313690õCTõ003õAõ31129999õ  õOSGA                          õRAIQUEN ASAF                  õNõ00024966240õNõTõ20249662403õFõ26101975õ00000õSõ2010-07-08-14.18.49.545568õ  õ  õ   õ   õ  õ  õ10121998õ00313686õCTõ004õAõ31129999õ  õOSEWSKYJ                      õRAIQUEN ALBERICO              õNõ00022247935õNõLõ20222479356õFõ13041973õ00000õSõ2010-07-08-14.18.49.545757õ  õ  õ   õ   õ  õ  õ08072010õ";
        IntervinientesOutEntity salidav = intervinientesDAOImpl.processTrama(tramaResponse, IntervinientesOutEntity.class);
        Assert.assertEquals(4,salidav.getListaRepeticiones().size());
        System.out.println(salidav);
        
    }
    

    
    @Test(expected = InvalidRecordException.class)
    public void test_Error() throws DAOException {

        String tramaResponse = "200000000000P04HTML00099000103FREEUSER  XXXXXXXX00360054000000192016083116015500000000IBF34389000000000000CNSINTERVI1200000            00240000    00        00 012119978201702011216000000000000                            000000000000000XXXX000000000000000XXXX000000000000000XXXX000000000000000XXXX000000000000000XXXX00DHõ0048600000000õBAUCIS              õ00212õ14 õPBõPARANA CP 310õ3100õEõAJIõ08õ080õ42486114õ002õ002õ00239957õTIõ001õAõ31129999õ  õLOSASSO DE AITA               õMIRTHA TRINIDAD               õNõ00025659672õNõLõ27256596720õFõ27011977õ00000õSõ2008-04-22-15.54.16.913602õ  õ  õ   õ   õ  õ  õ22042008õ00240000õCTõ002õAõ31129999õ  õ";        
        IntervinientesOutEntity salidav = intervinientesDAOImpl.processTrama(tramaResponse, IntervinientesOutEntity.class);
        System.out.println(salidav);
        
    }

}
