package com.poc.resttemplate.rest_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;



/**
 * Hello world!
 *
 */
public class RestClientApplication 
{
	private static Logger logger = LoggerFactory.getLogger("RestClientApplication -> ");
	
    final static String uri_add_book = "http://localhost:8081/api/books/add-book";
	final static String uri_findall_books = "http://localhost:8081/api/books/find-books";
	final static String uri_find_book = "http://localhost:8081/api/books/23";

	
    public static void main( String[] args )
    {
        logger.info( "Hello World!" );
//        addBook();
//        testaddbook_success();
//      addBook_RestTemplateClient();
//      get_books();
        findall_books();
    }
    
    private static void addBook()
    {  
     
        Book newBook = new Book(1L,"Adam", "Gilly");
        RestTemplate restTemplate = new RestTemplate();
        Book result = restTemplate.postForObject( uri_add_book, newBook, Book.class);
        logger.info(result.toString());
    }
    
    public static void addBook_RestTemplateClient() {

   	try {
//   	   double randomNumber = (Math.random()*((100-1)+1))+1;
   		   int randomInt = new Random().nextInt(9999);
           RestTemplate restTemplate = new RestTemplate();
           JSONArray json = new JSONArray();

           JSONObject bookJsonObject = new JSONObject();
           bookJsonObject.put("id", randomInt);
           bookJsonObject.put("title", "SherlockHolmes"+randomInt);   // Unique key, so should be new for every run
           bookJsonObject.put("author", "DetectiveSherlock"+randomInt);
   
           json.put(bookJsonObject);
           logger.info("Book ............................... "+bookJsonObject);
           HttpHeaders headers = new HttpHeaders();
           headers.add("Accept", MediaType.APPLICATION_JSON.toString());
           headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
           HttpEntity<String> entity = new HttpEntity<String>(bookJsonObject.toString(), headers);
           ResponseEntity<String> response = restTemplate.exchange(uri_add_book, HttpMethod.POST, entity, String.class);
           logger.info("Response ............................... "+response.getBody().toString());
           String book = response.getBody();
//   	    JsonNode root = objectMapper.readTree(resultAsJsonStr.getBody());
       } catch(Exception ex) {
       	logger.info("Rest Client Execution error ............................... "+ex.toString());
       }
   }
    
    public static void findall_books() {
 	       try {    		   
	 	       URL url = new URL(uri_findall_books);
	 	       HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	 	       connection.setRequestMethod("GET");
	 	       //connection.setDoOutput(true);
	 	       InputStream content = (InputStream) connection.getInputStream();
	 	       BufferedReader in = new BufferedReader(new InputStreamReader(content));
	 	       String line;
				while ((line = in.readLine()) != null) {
					logger.info(line);
				   }
			} catch (IOException e) {
				e.printStackTrace();
			}
 	       
 	       
 }
    
    
    public static void findbook() {
    	   RestTemplate restTemplate = new RestTemplate();
//    	 Book response1 =
//    	       restTemplate.getForObject(uri_find_book, Book.class);
//    	       logger.info("Response ............................... "+  response1.toString());
//  
    	       
//    	       JSONObject bookJsonObject = new JSONObject();
//    	       HttpHeaders headers = new HttpHeaders();
//    	       headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//    	       headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
//    	       HttpEntity<String> entity = new HttpEntity<String>(bookJsonObject.toString(), headers);
//    	       ResponseEntity<Book> response = 
//    	    		   	restTemplate.exchange(uri_find_book, HttpMethod.GET, entity, Book.class);

    }
    
   public static void get_books() {
	   RestTemplate restTemplate = new RestTemplate();
	   
       JSONObject bookJsonObject = new JSONObject();
       HttpHeaders headers = new HttpHeaders();
       headers.add("Accept", MediaType.APPLICATION_JSON.toString());
       headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
       HttpEntity<String> entity = new HttpEntity<String>(bookJsonObject.toString(), headers);

	   ResponseEntity<Book[]> response =
			   restTemplate.getForEntity(uri_findall_books, Book[].class);
       logger.info("Response ............................... "+response.getBody().toString());

//       Book[] response1 =
//       restTemplate.getForObject(uri_findall_books, Book[].class);
//       logger.info("Response ............................... "+response1.length + response1.toString());
	   
//       HttpHeaders headers = new HttpHeaders();
//       headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
//       HttpEntity<String> entity = new HttpEntity<String>(headers);
//    
//       ResponseEntity<Book> response = 
//         restTemplate.exchange(uri_findall_books, HttpMethod.GET, entity, Book.class);
      
//       ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
//       RestTemplate restTemplate = new RestTemplate(requestFactory);
       
//       RestTemplate restTemplate1 = new RestTemplate();
//       MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//       mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
//       restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
       
//	   Book[] itr = response.getBody();
	   
//	   Book[] bookList = response.getBody();
//	   for (Book book : bookList){
//		   logger.info(book.toString());
//		}
	   
//	   List<String> names = new ArrayList<>();
//	   names.add("Sergey");
//	   names.add("Bill");
//	   names.add("John");
//	   
//	   Iterator iter = names.iterator();
//	   while(iter.hasNext())
//	   {
//	      System.out.println(iter.next());
//	   }
   }
    
    
//    @Test
    public  void testaddbook_success() 
    {
        
        
        RestTemplate restTemplate = new RestTemplate();    
        Book newBook = new Book(1L,"Adam", "Gilly");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
         
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");    
        headers.set("X-COM-LOCATION", "USA");      
     
        HttpEntity<Book> request = new HttpEntity<>(newBook,httpHeaders);
         
        ResponseEntity<String> result = restTemplate.postForEntity(uri_add_book, request, String.class);
         
        //Verify request succeed
//        Assert.assertEquals(201, result.getStatusCodeValue());
    }
    
    

//    Post a List of Objects with RestTemplate
//    
//    List<Employee> newEmployees = new ArrayList<>();
//    newEmployees.add(new Employee(3, "Intern"));
//    newEmployees.add(new Employee(4, "CEO"));
//     
//    restTemplate.postForObject(
//      "http://localhost:8080/employees/",
//      newEmployees,
//      ResponseEntity.class);
//    
//    Using a Wrapper Class
//    
//    List<Employee> newEmployees = new ArrayList<>();
//    newEmployees.add(new Employee(3, "Intern"));
//    newEmployees.add(new Employee(4, "CEO"));
//     
//    restTemplate.postForObject(
//      "http://localhost:8080/employees",
//      new EmployeeList(newEmployees),
//      ResponseEntity.class);
}

