package com.codecool.shop.model;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by michal on 24.05.17.
 */
class CartTest {

	@Test
	public void testAddNewProduct() {
		Cart cart = new Cart();
		Product product = mock(Product.class);
		cart.add(product);
		assertEquals(1,cart.numOfitemsInCart());
	}

	@Test
	public void testAddTwiceTheSameProduct(){
		Cart cart = new Cart();
		Product product1 = mock(Product.class);
		Product product2 = product1;
		cart.add(product1);
		cart.add(product2);
		assertEquals(2,cart.numOfitemsInCart());
	}

	@Test
	public void testAddTwiceTheSameProductDifferentQuantity(){
		Cart cart = new Cart();
		Product product1 = mock(Product.class);
		Product product2 = product1;
		cart.add(product1,1);
		cart.add(product2,2);
		assertEquals(3,cart.numOfitemsInCart());
	}

	@Test
	public void testRemoveProduct(){
		Cart cart = new Cart();
		Product product = mock(Product.class);
		cart.add(product,1);
		cart.remove(0);
		assertEquals(0,cart.numOfitemsInCart());
	}

	@Test
	public void testChangeQuantity(){
		Cart cart = new Cart();
		Product product = mock(Product.class);
		cart.add(product,3);
		cart.changeQuantity(0, 2);
		assertEquals(2,cart.numOfitemsInCart());
	}

	@Test
	public void testChangeQuantityInMinus(){
		Cart cart = new Cart();
		Product product = mock(Product.class);
		cart.add(product,1);
		cart.changeQuantity(0, -2);
		assertEquals(0,cart.numOfitemsInCart());
	}

	@Test
	public void testSize(){
		Cart cart = new Cart();
		Product product = mock(Product.class);
		cart.add(product);
		assertEquals(1, cart.size());
	}

	@Test
	public void testGetSum() throws SQLException {
		Cart cart = new Cart();
		Product product = new Product(1,"testName", 100f, "PLN", "testDesc", mock(ProductCategory.class), mock(Supplier.class));
		product.setPrice(100f, "PLN");
		cart.add(product,3);
		Float sum = 300f;
		assertEquals(sum, cart.getSum());
	}

	@Test
	public void testGetIndexIfExists(){
		Product product = new Product(1,"testName", 100f, "PLN", "testDesc", mock(ProductCategory.class), mock(Supplier.class));
		Cart cart = new Cart();
		cart.add(product);
		Integer index = 1;
		assertEquals(index, cart.getIndexIfExists(1));
	}

	@Test
	public void testGetIterator(){
		Cart cart = new Cart();
		assertEquals(Iterator.class, cart.iterator());
	}
}