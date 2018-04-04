package com.kodilla.hibernate.invoice.dao;

import com.kodilla.hibernate.invoice.Invoice;
import com.kodilla.hibernate.invoice.Item;
import com.kodilla.hibernate.invoice.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceDaoTestSuite {
    @Autowired
    private InvoiceDao invoiceDao;

    @Test
    public void testInvoiceDaoSave(){
        //Given

        Product product1 = new Product("Beer");
        Product product2 = new Product("Whisky");
        Item item1 = new Item(new BigDecimal(200),10);
        Item item2 = new Item(new BigDecimal(100),10);
        product1.getItems().add(item1);
        product2.getItems().add(item2);

        Invoice invoice = new Invoice("11/11/11");
        item1.setInvoice(invoice);
        //when
        invoiceDao.save(invoice);
        int id =invoice.getId();
        //Then
        Assert.assertNotEquals(0,id);

        //CleanUp
        invoiceDao.delete(id);
    }
}


