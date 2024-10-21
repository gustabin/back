package ar.com.santanderrio.obp.servicios.debinapi.dto;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class DebinListDTOTest {

    @Test
    public void shouldDesserializeOK() throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();
        DebinListDTO debinListDTO = objectMapper.readValue(jsonDebinApi(), DebinListDTO.class);
        Assert.assertNotNull(debinListDTO);
        Assert.assertEquals(1, debinListDTO.getTotalPages());
        DebinDetailDTO debinDetailDTO = debinListDTO.getDebins().get(0);
        Assert.assertEquals("0WGRXJE27V641W6N7MYQL1", debinDetailDTO.getId());
        Assert.assertEquals("SPOT", debinDetailDTO.getDebinType().toString());

        Assert.assertEquals("INITIALIZED", debinDetailDTO.getDebinStatusDTO().getCode());
        Assert.assertEquals("INICIADO", debinDetailDTO.getDebinStatusDTO().getDescription());
        Assert.assertEquals(true, debinDetailDTO.getDebinStatusDTO().isSuccess());

        Assert.assertEquals(false, debinDetailDTO.getDebinStatusDTO().isFinalStatus());

        DebinBuyerDTO debinBuyerDTO = debinDetailDTO.getDebinBuyerDTO();
        Assert.assertEquals("23302005044",debinBuyerDTO.getCuit());
        Assert.assertEquals("",debinBuyerDTO.getTerminal());
        Assert.assertEquals("VALE TEST",debinBuyerDTO.getFancyName());

        DebinAccountDTO debinAccountDTO = debinBuyerDTO.getDebinAccountDTO();
        Assert.assertEquals("072", debinAccountDTO.getBank());
        Assert.assertEquals("0175", debinAccountDTO.getBranch());
        Assert.assertEquals("", debinAccountDTO.getTerminal());
        Assert.assertEquals("", debinAccountDTO.getAlias());
        Assert.assertEquals("0720175888000037232054", debinAccountDTO.getCbu());
        Assert.assertEquals(true, debinAccountDTO.isOwner());
        Assert.assertEquals("ARS", debinAccountDTO.getCurrency().toString());
        Assert.assertEquals("", debinAccountDTO.getType());
        Assert.assertEquals("", debinAccountDTO.getEndpoint());

        DebinSellerDTO debinSellerDTO = debinDetailDTO.getDebinSellerDTO();

        Assert.assertEquals("30708218185",debinSellerDTO.getCuit());
        Assert.assertEquals("",debinSellerDTO.getTerminal());
        Assert.assertEquals("EMPRESA TEST",debinSellerDTO.getFancyName());

        DebinAccountDTO debinSellerAccountDTO = debinSellerDTO.getDebinAccountDTO();
        Assert.assertEquals("072", debinSellerAccountDTO.getBank());
        Assert.assertEquals("0032", debinSellerAccountDTO.getBranch());
        Assert.assertEquals("", debinSellerAccountDTO.getTerminal());
        Assert.assertEquals("", debinSellerAccountDTO.getAlias());
        Assert.assertEquals("0720032820000000703286", debinSellerAccountDTO.getCbu());
        Assert.assertEquals(true, debinSellerAccountDTO.isOwner());
        Assert.assertEquals("ARS", debinSellerAccountDTO.getCurrency().toString());
        Assert.assertEquals("", debinSellerAccountDTO.getType());
        Assert.assertEquals("", debinSellerAccountDTO.getEndpoint());

        Assert.assertEquals("ARS", debinDetailDTO.getCurrency().toString());
        Assert.assertEquals(new BigDecimal("1.1"), debinDetailDTO.getAmount());
        Assert.assertEquals("VAR", debinDetailDTO.getConcept().toString());
        Assert.assertEquals(false, debinDetailDTO.getRefund());
        Assert.assertEquals("", debinDetailDTO.getAssociatedOperationId());
        Assert.assertEquals("2021-01-21T23:10:30.261342", debinDetailDTO.getExpirationDate());
        Assert.assertEquals("2021-01-21T23:10:30.261402", debinDetailDTO.getCreationDate());

    }


    private String jsonDebinApi(){
        return "{\n" +
                "    \"debins\": [\n" +
                "        {\n" +
                "            \"id\": \"0WGRXJE27V641W6N7MYQL1\",\n" +
                "            \"debinType\": \"SPOT\",\n" +
                "            \"debinStatusDTO\": {\n" +
                "                \"code\": \"INITIALIZED\",\n" +
                "                \"description\": \"INICIADO\",\n" +
                "                \"success\": true,\n" +
                "                \"finalStatus\": false\n" +
                "            },\n" +
                "            \"debinBuyerDTO\": {\n" +
                "                \"cuit\": \"23302005044\",\n" +
                "                \"fancyName\": \"VALE TEST\",\n" +
                "                \"terminal\": \"\",\n" +
                "                \"debinAccountDTO\": {\n" +
                "                    \"bank\": \"072\",\n" +
                "                    \"branch\": \"0175\",\n" +
                "                    \"terminal\": \"\",\n" +
                "                    \"alias\": \"\",\n" +
                "                    \"cbu\": \"0720175888000037232054\",\n" +
                "                    \"owner\": true,\n" +
                "                    \"currency\": \"ARS\",\n" +
                "                    \"type\": \"\",\n" +
                "                    \"endpoint\": \"\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"debinSellerDTO\": {\n" +
                "                \"fancyName\": \"EMPRESA TEST\",\n" +
                "                \"cuit\": \"30708218185\",\n" +
                "                \"terminal\": \"\",\n" +
                "                \"debinAccountDTO\": {\n" +
                "                    \"bank\": \"072\",\n" +
                "                    \"branch\": \"0032\",\n" +
                "                    \"terminal\": \"\",\n" +
                "                    \"alias\": \"\",\n" +
                "                    \"cbu\": \"0720032820000000703286\",\n" +
                "                    \"owner\": true,\n" +
                "                    \"currency\": \"ARS\",\n" +
                "                    \"type\": \"\",\n" +
                "                    \"endpoint\": \"\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"currency\": \"ARS\",\n" +
                "            \"amount\": 1.1,\n" +
                "            \"concept\": \"VAR\",\n" +
                "            \"refund\": false,\n" +
                "            \"associatedOperationId\": \"\",\n" +
                "            \"expirationDate\": \"2021-01-21T23:10:30.261342\",\n" +
                "            \"creationDate\": \"2021-01-21T23:10:30.261402\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"totalPages\": 1\n" +
                "}";
    }
}
