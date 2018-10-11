package nhom2.qlsv;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class Csdldatastore {
	static DatastoreService datastore;
	public static void createEntity( ) throws ServletException {
	  Map<String, String> blogContent = null;
	  Entity post = new Entity("Blogpost"); // create a new entity

	  post.setProperty("title", blogContent.get("blogContent_title"));
	  post.setProperty("author", blogContent.get("blogContent_author"));
	  post.setProperty("body", blogContent.get("blogContent_description"));
	  post.setProperty("timestamp", new Date().getTime());

	  try {
	    datastore.put(post); // store the entity

	    // Send the user to the confirmation page with personalised confirmation text
	//    String confirmation = "Post with title " + blogContent.get("blogContent_title") + " created.";
    
	  } catch (DatastoreFailureException e) {
	    throw new ServletException("Datastore error", e);
	  }
	}
}
