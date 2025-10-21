package com.example.dao;

import com.example.dao.IDao;
import com.example.entities.Product;
import com.example.metier.ProductDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.util.HibernateConfig;

public class Presentation2 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        // Récupération du bean ProductDaoImpl
        IDao<Product> productDao = context.getBean(ProductDaoImpl.class);

        // Création d'un produit
        Product product = new Product();
        product.setName("Produit 1");
        product.setPrice(100.0);

        // Sauvegarde en base
        productDao.create(product);

        System.out.println("Produit sauvegardé : " + product.getName());
    }
}
