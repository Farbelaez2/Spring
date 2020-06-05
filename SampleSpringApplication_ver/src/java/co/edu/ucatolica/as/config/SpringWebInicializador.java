/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucatolica.as.config;

/**
 *
 * @author wcarl
 */
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
 
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
 
public class SpringWebInicializador implements WebApplicationInitializer {
 
    @Override
    public void onStartup(ServletContext contenedor) throws ServletException {
 
     
        AnnotationConfigWebApplicationContext contexto = new AnnotationConfigWebApplicationContext();
        contexto.register(ConfiguracionSpring.class);
        contexto.setServletContext(contenedor);
 
        ServletRegistration.Dynamic servlet = contenedor.addServlet("dispatcher", new DispatcherServlet(contexto));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
 
}
