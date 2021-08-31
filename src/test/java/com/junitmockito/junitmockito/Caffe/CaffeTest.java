package com.junitmockito.junitmockito.Caffe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaffeTest {



    @Test
    void itShouldBrewEspresso() {
        //Given
        Caffe caffe = new Caffe();
        caffe.restockBeans(7);

        //When
        Coffee coffee = caffe.brew(CoffeeType.Espresso);

        //Then
        assertEquals(7, coffee.getBeans());
        assertEquals(0, coffee.getMilk());
        assertEquals(CoffeeType.Espresso, coffee.getType());
    }

    @Test
    void itShouldBrewLatte() {
        //Given
        Caffe caffe = new Caffe();
        caffe.restockBeans(7);
        caffe.restockMilk(227);

        //When
        Coffee coffee = caffe.brew(CoffeeType.Latte);

        //Then
        assertEquals(7, coffee.getBeans());
        assertEquals(227, coffee.getMilk());
        assertEquals(CoffeeType.Latte, coffee.getType());
    }

    @Test
    void itShouldGetBeansInStock() {
        //Given
        Caffe caffe = new Caffe();
        caffe.restockBeans(7);

        //When
        int beansInStock = caffe.getBeansInStock();

        //Then
        assertEquals(7, beansInStock);
    }

    @Test
    void itShouldGetMilkInStock() {
        //Given
        Caffe caffe = new Caffe();
        caffe.restockMilk(227);

        //When
        int milkInStock = caffe.getMilkInStock();
        //Then
        assertEquals(227, milkInStock);
    }
}