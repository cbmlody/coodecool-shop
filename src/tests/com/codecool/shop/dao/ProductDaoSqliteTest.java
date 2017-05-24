package com.codecool.shop.dao;

import com.codecool.shop.App;
import org.junit.jupiter.api.BeforeEach;
import com.codecool.shop.model.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by michal on 23.05.17.
 */
class ProductDaoSqliteTest {

	@BeforeEach
	private ProductDaoSqlite createProductDaoSqlite(){
		return new ProductDaoSqlite();
	}


	public void testIfThrowsSQLExceptionBeforeApplicationInitialization(){
		assertThrows(SQLException.class, () -> {
			this.createProductDaoSqlite().getAll();
		});
	}

}