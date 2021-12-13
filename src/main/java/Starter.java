import com.pilnyck.shop.dao.jdbc.JdbcProductDao;
import com.pilnyck.shop.service.ProductService;
import com.pilnyck.shop.servlet.AddProductsServlet;

import com.pilnyck.shop.servlet.DeleteProductsServlet;
import com.pilnyck.shop.servlet.EditProductsServlet;
import com.pilnyck.shop.servlet.ShowAllServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Starter {
    public static void main(String[] args) throws Exception {
        //config

        // dao
        JdbcProductDao jdbcProductDao = new JdbcProductDao();

        //service
        ProductService productService = new ProductService(jdbcProductDao);

        //servlet
        ShowAllServlet showAllServlet = new ShowAllServlet(productService);
        AddProductsServlet addProductsServlet = new AddProductsServlet(productService);
        EditProductsServlet editProductsServlet = new EditProductsServlet(productService);
        DeleteProductsServlet deleteProductsServlet = new DeleteProductsServlet(productService);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(showAllServlet), "/products");
        contextHandler.addServlet(new ServletHolder(addProductsServlet), "/products/add");
        contextHandler.addServlet(new ServletHolder(editProductsServlet), "/products/edit");
        contextHandler.addServlet(new ServletHolder(deleteProductsServlet), "/products/delete");

        Server server = new Server(3000);
        server.setHandler(contextHandler);
        server.start();
    }
}
