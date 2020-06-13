package com.app.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.model.Product;
import com.app.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Override
	public String filterFile(String loc) throws IOException {
		//Read Files
		Path path = Paths.get(loc);
		List<Product> filterProducts = Files.lines(path)
									 		.map(this::splitCommaSeparatedStrint)
									 		.filter(product-> product.getPrice() >= 5000.00)
									 		.collect(Collectors.toList());
		//Write File
		List<String> filterString = filterProducts.stream()
					  							  .map(this::createCommaSeparatedStringFromProduct)
					  							  .collect(Collectors.toList());
		Path writePath = Files.write(Paths.get("D:\\files\\filterProduct.txt"), filterString, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
		
		return writePath.toUri().toString();
		
	}

	private Product splitCommaSeparatedStrint(String str){
		String[] split = str.split(",");
		Product product = Product.builder()
								 .id(Integer.parseInt(split[0]))
								 .name(split[1])
								 .price(Double.parseDouble(split[2]))
								 .build();
		return product;
	}
	
	private String createCommaSeparatedStringFromProduct(Product product) {
		return String.join(",", Arrays.asList(String.valueOf(product.getId()),
					 						  product.getName(),
					 						  String.valueOf(product.getPrice())));
	}
}
