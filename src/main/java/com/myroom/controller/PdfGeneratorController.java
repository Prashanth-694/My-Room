package com.myroom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.IOException;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.myroom.model.ProductDetails;
import com.myroom.model.Users;
import com.myroom.service.MyRoomService;
import com.myroom.service.UsersService;

@Controller
@RequestMapping("/orders")
public class PdfGeneratorController {
	@Autowired
	ServletContext servletContext;
	@Autowired
	MyRoomService myRoomService;
	@Autowired
	UsersService usersService;

	private final TemplateEngine templateEngine;

	public PdfGeneratorController(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	@RequestMapping(path = "/check/pdf")
	public ResponseEntity<?> genratePDF(HttpServletRequest request, HttpServletResponse response)
			throws IOException, MessagingException {

		WebContext context = new WebContext(request, response, servletContext);
		System.out.println("inside fet");
		List<Users> usersList = usersService.fetchAllUsers();
        int totalAmount=0;
		Map<String, Object> ite = new HashMap<>();
		for (Users us : usersList) {
			List<ProductDetails> productDetails1 = myRoomService.fetchProductDetailsById(us.getUserId());
			ite.put(us.getUserName(), productDetails1);
			totalAmount=totalAmount+us.getSpentAmount();
		}
		System.out.println(ite);
		System.out.println("total amount : "+totalAmount);
		context.setVariable("totalAmount", totalAmount);
		context.setVariable("check", ite);

		String orderHtml = templateEngine.process("orders", context);

		/* Setup Source and target I/O streams */

		ByteArrayOutputStream target = new ByteArrayOutputStream();

		/* Setup converter properties. */
		ConverterProperties converterProperties = new ConverterProperties();
		converterProperties.setBaseUri("http://localhost:9009");

		/* Call convert method */
		HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

		/* extract output as bytes */
		byte[] bytes = target.toByteArray();

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order.pdf")
				.contentType(MediaType.APPLICATION_PDF).body(bytes);
	}

}
