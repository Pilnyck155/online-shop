import com.pilnyck.shop.dao.jdbc.JdbcProductDao;
import com.pilnyck.shop.dao.jdbc.JdbcUserDao;
import com.pilnyck.shop.filter.SecurityFilter;
import com.pilnyck.shop.service.ProductService;
import com.pilnyck.shop.service.SecurityService;
import com.pilnyck.shop.service.UserService;
import com.pilnyck.shop.servlet.*;

import jakarta.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Starter {
    public static void main(String[] args) throws Exception {
        //config
        //List<String> userToken = new ArrayList<>();

        // dao
        JdbcProductDao jdbcProductDao = new JdbcProductDao();
        JdbcUserDao jdbcUserDao = new JdbcUserDao();

        //service
        ProductService productService = new ProductService(jdbcProductDao);
        UserService userService = new UserService(jdbcUserDao);
        SecurityService securityService = new SecurityService();

        //servlets
        LoginServlet loginServlet = new LoginServlet(userService, securityService);
        ShowAllServlet showAllServlet = new ShowAllServlet(productService);
        AddProductsServlet addProductsServlet = new AddProductsServlet(productService);
        EditProductsServlet editProductsServlet = new EditProductsServlet(productService);
        DeleteProductsServlet deleteProductsServlet = new DeleteProductsServlet(productService);

        //filters
        SecurityFilter securityFilter = new SecurityFilter(securityService);

        //handlers
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(loginServlet), "/login");
        contextHandler.addServlet(new ServletHolder(showAllServlet), "/");
        contextHandler.addServlet(new ServletHolder(showAllServlet), "/products");
        contextHandler.addServlet(new ServletHolder(addProductsServlet), "/products/add");
        contextHandler.addServlet(new ServletHolder(editProductsServlet), "/products/edit");
        contextHandler.addServlet(new ServletHolder(deleteProductsServlet), "/products/delete");

        contextHandler.addFilter(new FilterHolder(securityFilter), "/*", EnumSet.of(DispatcherType.REQUEST));

        Server server = new Server(3000);
        server.setHandler(contextHandler);
        server.start();
    }
}
