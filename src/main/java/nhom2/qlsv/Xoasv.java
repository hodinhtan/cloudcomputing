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

@WebServlet(name = "Xoasv", urlPatterns = { "/xoasv" })
public class Xoasv extends HttpServlet {
	Datastore datastore;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Map<String, String[]> map = req.getParameterMap();
		datastore = DatastoreOptions.getDefaultInstance().getService();
		// Reading the Map
		// Works for GET && POST Method
		String[] list = map.get("Name");
		String keyName = list[0];
		resp.getWriter().printf("Đã thêm sinh viên : \n'");
		KeyFactory keyFactory = datastore.newKeyFactory().setKind("SinhVien");
		Key key1 = datastore.newKeyFactory().setKind("SinhVien").newKey(keyName);
		boolean check = false;
		Query<Entity> query = Query.newEntityQueryBuilder().setKind("SinhVien").build();
		QueryResults<Entity> results = datastore.run(query);

		while (results.hasNext()) {
			Entity ret = results.next();
			if (ret.getString("Ma so sinh vien").equals(keyName)) {
				check = true;
			}
		}
		if (check) {
			datastore.delete(key1);
			resp.getWriter().printf("Đã xóa hồ sơ sv mssv %s", keyName);
		} else {
			resp.getWriter().printf("Không co hồ sơ sv mssv %s", keyName);
		}
	}
}
