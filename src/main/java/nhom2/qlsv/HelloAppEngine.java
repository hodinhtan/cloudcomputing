package nhom2.qlsv;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    String kind = "SinhVien";
    Query<Entity> query = Query.newEntityQueryBuilder().setKind(kind).build();
    QueryResults<Entity> results = datastore.run(query);
    
    response.getWriter().println("Danh sách sinh viên trong datastore :");
    while (results.hasNext()) {
    	Entity ret = results.next();
    	String tensv="Ten sinh vien",
    			ngaysinh = "Age",
    			mssv = "Ma so sinh vien",
    			noisinh =	"Noi sinh",
    			lop = "Lop";
    	response.getWriter().printf("Họ va tên: %s \n", ret.getString(tensv));
    	response.getWriter().printf("	Tuổi: %s \n", ret.getString(ngaysinh));
    	response.getWriter().printf("	Mã số sinh viên : %s \n", ret.getString(mssv));
    	response.getWriter().printf("	Nơi sinh: %s \n", ret.getString(noisinh));
    	response.getWriter().printf("	Lớp: %s \n", ret.getString(lop));
    }
  }
}

