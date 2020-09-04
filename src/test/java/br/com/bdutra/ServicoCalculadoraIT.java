package br.com.bdutra;

import java.io.File;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@Default
@RunWith(Arquillian.class)
public class ServicoCalculadoraIT {

	@Deployment
	public static WebArchive createDeployment()
	{
	   WebArchive webArchive = ShrinkWrap
	        .create(ZipImporter.class, "teste.war")
	        .importFrom(new File("target/arquillian-jboss.war"))
            .as(WebArchive.class)
            .addPackage(ServicoCalculadora.class.getPackage());
	   
	   return webArchive;
	}
	
	@Inject
	private ServicoCalculadora servicoCalculadora;
	
	@Test
	public void testCalcular() {
		int resultado = servicoCalculadora.calcular(4, 9);
		Assert.assertEquals(13, resultado);
	}

}
