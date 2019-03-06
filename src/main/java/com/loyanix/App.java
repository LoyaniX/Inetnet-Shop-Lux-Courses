package com.loyanix;

import com.loyanix.dao.ClientDao;
import com.loyanix.dao.DataSetupUitl;
import com.loyanix.dao.OrderDao;
import com.loyanix.dao.ProductDao;
import com.loyanix.dao.impl.ClientDaoImpl;
import com.loyanix.dao.impl.OrderDaoImpl;
import com.loyanix.dao.impl.ProductDaoImpl;
import com.loyanix.services.ClientService;
import com.loyanix.services.OrderService;
import com.loyanix.services.ProductService;
import com.loyanix.services.authorization.AdminAuth;
import com.loyanix.services.authorization.impl.AdminAuthImpl;
import com.loyanix.services.converter.ClientConverter;
import com.loyanix.services.converter.OrderConverter;
import com.loyanix.services.converter.ProductConverter;
import com.loyanix.services.converter.impl.ClientConverterImpl;
import com.loyanix.services.converter.impl.OrderConverterImpl;
import com.loyanix.services.converter.impl.ProductConverterImpl;
import com.loyanix.services.impl.ClientServiceImpl;
import com.loyanix.services.impl.OrderServiceImpl;
import com.loyanix.services.impl.ProductServiceImpl;
import com.loyanix.validator.Impl.ValidationServiceImpl;
import com.loyanix.validator.ValidationService;
import com.loyanix.view.AdminMenu;
import com.loyanix.view.ClientMenu;
import com.loyanix.view.MainMenu;
import com.loyanix.view.submenu.ClientSubMenu;
import com.loyanix.view.submenu.OrderSubMenu;
import com.loyanix.view.submenu.ProductSubMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        ClientConverter clientConverter = new ClientConverterImpl();
        ProductConverter productConverter = new ProductConverterImpl();
        OrderConverter orderConverter = new OrderConverterImpl(clientConverter, productConverter);

        AdminAuth auth = new AdminAuthImpl();

        ClientDao clientDao = ClientDaoImpl.getInstance();
        ProductDao productDao = new ProductDaoImpl();
        OrderDao orderDao = new OrderDaoImpl();

        ValidationService validationService = new ValidationServiceImpl();

        ClientService clientService = new ClientServiceImpl(clientDao, clientConverter, validationService);
        ProductService productService = new ProductServiceImpl(productDao, productConverter);
        OrderService orderService = new OrderServiceImpl(orderDao, orderConverter);

        ClientSubMenu clientSubMenu = new ClientSubMenu(bufferedReader, clientService);
        ProductSubMenu productSubMenu = new ProductSubMenu(bufferedReader, productService);
        OrderSubMenu orderSubMenu = new OrderSubMenu(bufferedReader, orderService);

        AdminMenu adminMenu = new AdminMenu(bufferedReader, clientSubMenu, orderSubMenu, productSubMenu);
        ClientMenu clientMenu = new ClientMenu(bufferedReader, clientSubMenu, orderSubMenu, productSubMenu);
        MainMenu mainMenu = new MainMenu(bufferedReader, adminMenu, clientMenu, auth);

        DataSetupUitl.createTebles();
        DataSetupUitl.addExampleData(clientService, productService, orderService);

        mainMenu.showMenu();
    }
}
