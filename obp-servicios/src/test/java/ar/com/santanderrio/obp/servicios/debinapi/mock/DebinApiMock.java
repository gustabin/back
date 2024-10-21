package ar.com.santanderrio.obp.servicios.debinapi.mock;

import ar.com.santanderrio.obp.servicios.debinapi.dto.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DebinApiMock {

    public static DebinSellerDTO getDefaultSellerDTO(){
        DebinSellerDTO debinSellerDTO = new DebinSellerDTO();
        debinSellerDTO.setFancyName("MARA MARIA MARTIN");
        debinSellerDTO.setCuit("20049958243");
        debinSellerDTO.setOwner("uno");
        debinSellerDTO.setTerminal("one terminal");
        debinSellerDTO.setDebinAccountDTO(getDefaultDebinAccountDTO());
        return debinSellerDTO;
    }

    public static DebinAccountDTO getDefaultDebinAccountDTO(){
        DebinAccountDTO debinAccountDTO = new DebinAccountDTO();
        debinAccountDTO.setAlias("MITO.PLAYA.PLUMA");
        debinAccountDTO.setBank("072");
        debinAccountDTO.setCbu("0720009088000036828504");
        debinAccountDTO.setCurrency(CurrencyType.ARS);
        debinAccountDTO.setType("10");
        debinAccountDTO.setBranch("0009");
        debinAccountDTO.setTerminal("");
        debinAccountDTO.setEndpoint("89");
        debinAccountDTO.setOwner(Boolean.FALSE);
        return debinAccountDTO;
    }

    public static DebinBuyerDTO getDefaultDebinBuyerDTO(){
        DebinBuyerDTO debinBuyerDTO = new DebinBuyerDTO();
        debinBuyerDTO.setFancyName("LARA LAURA LOPEZ");
        debinBuyerDTO.setCuit("23302005044");
        debinBuyerDTO.setTerminal("one terminal");
        debinBuyerDTO.setDebinAccountDTO(getDefaultDebinAccountDTO());
        return debinBuyerDTO;
    }


    public static DebinDetailDTO getDefaultDetailDTO(){
        DebinDetailDTO debinDetailDTO = new DebinDetailDTO();
        debinDetailDTO.setDebinBuyerDTO(getDefaultDebinBuyerDTO());
        debinDetailDTO.setDebinSellerDTO(getDefaultSellerDTO());
        debinDetailDTO.setCurrency(CurrencyType.ARS);
        DebinStatusDTO debinStatusDTO = new DebinStatusDTO();
        debinStatusDTO.setCode(DebinStatus.INITIALIZED.toString());
        debinDetailDTO.setDebinStatusDTO(debinStatusDTO);
        debinDetailDTO.setAmount(new BigDecimal("2.50"));
        debinDetailDTO.setDebinType(DebinType.SPOT);
        debinDetailDTO.setExpirationDate("2020-12-18T14:35:50.6895343-03:00");
        debinDetailDTO.setCreationDate("2020-12-15T14:35:50.6895343-03:00");
        debinDetailDTO.setRefund(Boolean.FALSE);
        debinDetailDTO.setAssociatedOperationId("123");
        debinDetailDTO.setId("X76V4MR2Z568YOY9DEZOL1");
        debinDetailDTO.setConcept(ConceptType.HON);
        return debinDetailDTO;
    }

    public static DebinDetailDTO getDefaultDetailDTOWithNullDates(){
        DebinDetailDTO debinDetailDTO = new DebinDetailDTO();
        debinDetailDTO.setDebinBuyerDTO(getDefaultDebinBuyerDTO());
        debinDetailDTO.setDebinSellerDTO(getDefaultSellerDTO());
        debinDetailDTO.setCurrency(CurrencyType.ARS);
        DebinStatusDTO debinStatusDTO = new DebinStatusDTO();
        debinStatusDTO.setCode(DebinStatus.INITIALIZED.toString());
        debinDetailDTO.setDebinStatusDTO(debinStatusDTO);
        debinDetailDTO.setAmount(new BigDecimal("2.50"));
        debinDetailDTO.setDebinType(DebinType.SPOT);
        debinDetailDTO.setExpirationDate(null);
        debinDetailDTO.setCreationDate(null);
        debinDetailDTO.setRefund(Boolean.FALSE);
        debinDetailDTO.setAssociatedOperationId("123");
        debinDetailDTO.setId("X76V4MR2Z568YOY9DEZOL1");
        debinDetailDTO.setConcept(ConceptType.HON);
        return debinDetailDTO;
    }

    public static DebinListDTO getDefaultDebinListDTO(){
        DebinListDTO debinListDTO = new DebinListDTO();
        debinListDTO.setTotalPages(2);
        List<DebinDetailDTO> debinDetailDTOS = new ArrayList<DebinDetailDTO>();
        debinDetailDTOS.add(DebinApiMock.getDefaultDetailDTO());
        debinListDTO.setDebins(debinDetailDTOS);
        return debinListDTO;
    }

    public static ProviderErrorDTO getDefaultProviderErrorDTO(int rootCode){
        ProviderErrorDTO providerErrorDTO = new ProviderErrorDTO();
        providerErrorDTO.setProvider("COELSA");
        providerErrorDTO.setRootCode(rootCode);
        DetailErrorDTO detailErrorDTO = new DetailErrorDTO();
        List<ErrorDTO> errorDTOS = new ArrayList<ErrorDTO>() ;
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode("C0-0798");
        errorDTO.setType("invalid_field");
        errorDTO.setMessage("JSON INCORRECTO");
        errorDTOS.add(errorDTO);
        detailErrorDTO.setErrors(errorDTOS);
        providerErrorDTO.setRootDetail(detailErrorDTO);
        return providerErrorDTO;
    }
}
