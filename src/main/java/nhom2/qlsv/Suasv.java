package nhom2.qlsv;

import com.google.appengine.api.memcache.MemcacheSerialization.ValueAndFlags;
import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
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
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Suasv", urlPatterns = { "/suasv" })
public class Suasv extends HttpServlet {
	Datastore datastore;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Map<String, String[]> map = req.getParameterMap();
		datastore = DatastoreOptions.getDefaultInstance().getService();
		// Reading the Map
		// Works for GET && POST Method
		String[] list = map.get("Name");
		String keyName = list[0];
		resp.getWriter().printf("Đã sửa thông tin sinh viên : \n'");
		KeyFactory keyFactory = datastore.newKeyFactory().setKind("SinhVien");
		IncompleteKey key = keyFactory.setKind("SinhVien").newKey(keyName);
		String tensv="Ten sinh vien",
    			ngaysinh = "Age",
    			mssv = "Ma so sinh vien",
    			noisinh =	"Noi sinh",
    			lop = "Lop";
		
			String[] ValuesTen = map.get(tensv);
			String[] ValuesTuoi = map.get(ngaysinh);
			String[] ValuesMSSV = map.get(mssv);
			String[] ValuesTenNoisinh = map.get(noisinh);
			String[] ValuesTenLop = map.get(lop);
			FullEntity<IncompleteKey> entity = FullEntity.newBuilder(key)
					.set(tensv, ValuesTen[0])
					.set(ngaysinh, ValuesTuoi[0])
					.set(noisinh, ValuesTenNoisinh[0])
					.set(mssv, ValuesMSSV[0])
					.set(lop, ValuesTenLop[0])
					.build();
			datastore.put(entity);
		
		resp.getWriter().printf("Ten: %s \n  Tuoi: %s \n Mssv: %s\n Tennoisinh: %s\n Lop: %s \n",ValuesTen[0],ValuesTuoi[0],ValuesMSSV[0],ValuesTenNoisinh[0],ValuesTenLop[0] );
	}
}
