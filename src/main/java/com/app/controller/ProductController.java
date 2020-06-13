package com.app.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.impl.ProductServiceImpl;

@RestController
@RequestMapping(ProductController.URL)
public class ProductController {
	public static final String URL = "/v1/products";
	private ProductServiceImpl productServiceImpl;

	public ProductController(ProductServiceImpl productServiceImpl) {
		this.productServiceImpl = productServiceImpl;
	};
	
	@GetMapping
	public String filterFile(@RequestParam("loc")String loc) throws IOException {
		return productServiceImpl.filterFile(loc);
	}
}
