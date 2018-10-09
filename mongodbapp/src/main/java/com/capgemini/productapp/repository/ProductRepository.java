package com.capgemini.productapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.productapp.entity.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer>{

	@Query("{'productName': ?0}")
	public List<Product> findByProductName(String productName);
	
	@Query("{'productPrice' : { '$lt' : 1100}}")
	public List<Product> findByProductPrice();
	
	@Query("{'productPrice' : { '$lt' : 1100}, '$and' :[{'productCategory' : ?0 }]}")
	public List<Product> findByCategoryAndPrice(String productCategory);
	
	@Query("{'productPrice' : { '$lt' : 25000}, '$and' :[{'productPrice' : { '$gte' : 900}}]}")
	public List<Product> findByPriceInterval();
	
	@Query("{'productPrice' : { '$lt' : ?0,  '$gt' : ?0}}")
	public List<Product> findProductByPriceInterval(double upperbound,double lowerbound);
	
}
