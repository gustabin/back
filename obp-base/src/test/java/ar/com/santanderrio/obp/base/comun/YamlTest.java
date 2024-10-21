package ar.com.santanderrio.obp.base.comun;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.yaml.snakeyaml.Yaml;

import ar.com.santanderrio.obp.base.config.SegurosUrls;

@RunWith(MockitoJUnitRunner.class)
public class YamlTest {

    @Test
    public void whenLoadMultipleYAMLDocuments_thenLoadCorrectJavaObjects() {
        SegurosUrls segurosUrls;
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("prestamos.yaml");
        segurosUrls = yaml.loadAs(inputStream, SegurosUrls.class);
        Assert.assertNotNull(segurosUrls);
        Assert.assertEquals("pp", segurosUrls.getOfertaDefault());
        Assert.assertEquals(288, segurosUrls.getData().size());
    }
}
