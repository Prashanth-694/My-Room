package com.myroom.controller;

import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = {"*", "https://myroom401.herokuapp.com"})
@Controller
@RequestMapping("/orders")
public class PdfGeneratorController {
	@Autowired
	ServletContext servletContext;
	@Autowired
	MyRoomService myRoomService;
	@Autowired
	UsersService usersService;
	@Autowired
	private JavaMailSender mailSender;
//	String pdfDir1 = "/temp/";
	String pdfDir2 = "C:\\Other\\";
	private final TemplateEngine templateEngine;

	public PdfGeneratorController(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	@RequestMapping(path = "/check/pdf")
	public ResponseEntity<?> genratePDF(HttpServletRequest request, HttpServletResponse response)
			throws Exception, IOException, MessagingException, URISyntaxException {

		WebContext context = new WebContext(request, response, servletContext);
		System.out.println("inside fet");
		List<Users> usersList = usersService.fetchAllUsers();
        int totalAmount=0;
        System.out.println(java.time.LocalDate.now());    
		Map<String, Object> ite = new HashMap<>();
		for (Users us : usersList) {
			List<ProductDetails> productDetails1 = myRoomService.fetchProductDetailsById(us.getUserId());
//			for(ProductDetails pro : productDetails1) 
//			{
//				pro.setSpentAmount(us.getSpentAmount());
//			}
			System.out.println(productDetails1);
			System.out.println("checking : "+productDetails1.size());
			if(productDetails1.size() > 0) {
				
				productDetails1.get(0).setSpentAmount(us.getSpentAmount());
			}
			ite.put(us.getUserName(), productDetails1);
			totalAmount=totalAmount+us.getSpentAmount();
		}
		System.out.println(ite);
		System.out.println("total amount : "+totalAmount);
		context.setVariable("totalAmount", totalAmount);
		context.setVariable("check", ite);
		context.setVariable("avgAmount", totalAmount/usersList.size());
		context.setVariable("currDate",java.time.LocalDate.now() );
		LocalDate date = java.time.LocalDate.now().minusMonths(1);
		context.setVariable("dueDate", date);
//        System.out.println("check "+date);
//        String month = new SimpleDateFormat("MMM").format(date);
//        System.out.println("month "+month);
//DateFormat dateFormat=DateFormat.super
    // Get day from date
 //   int day = currentDate.getDayOfMonth();
		String orderHtml = templateEngine.process("orders", context);
		

		/* Setup Source and target I/O streams */

		ByteArrayOutputStream target = new ByteArrayOutputStream();

		/* Setup converter properties. */
		ConverterProperties converterProperties = new ConverterProperties();
//		converterProperties.setBaseUri("http://localhost:9009");

		/* Call convert method */
		HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

		/* extract output as bytes */
		byte[] bytes = target.toByteArray();
		
//		String fileName="order.pdf";
//		FileOutputStream fos = new FileOutputStream(pdfDir1+fileName);
//		fos.write(bytes);
//		fos.flush();
//		fos.close();
		System.out.println("=========>>>>> File Generated <<<<<==========");
       //------------------------------------------------------------------------------
		MimeMessage message = mailSender.createMimeMessage();
	     
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    
	    helper.setFrom("my401room@gmail.com");
	    helper.setTo(new String[]{"prashanthmp009@gmail.com","balakrishnamyakala0025@gmail.com","srinivasgoud996@gmail.com","reddynagesh7997@gmail.com","sravan17091997@gmail.com","nageshkumarduddeda@gmail.com","sathish1048@gmail.com"});
	    helper.setSubject("Invoice From MyRoom");
	    helper.setText("Please find the invoice attachment.");
	    
		
		  final InputStreamSource attachmentSource = new ByteArrayResource(bytes);
		  DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
		  helper.addAttachment("MyRoom-invoice", dataSource);
		
	   
 
 
        mailSender.send(helper.getMimeMessage());
      
		//------------------------------------------------------------------------------
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order.pdf")
				.contentType(MediaType.APPLICATION_PDF)
				.body(bytes);
	}

}
