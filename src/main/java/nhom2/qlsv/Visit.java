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

@WebServlet(name = "Visit", urlPatterns = { "/visit" })
@SuppressWarnings("serial")

public class Visit extends HelloAppEngine {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// store only the first two octets of a users ip address
		String userIp = req.getRemoteAddr();
		InetAddress address = InetAddress.getByName(userIp);
		if (address instanceof Inet6Address) {
			// nest indexOf calls to find the second occurrence of a character in a string
			// an alternative is to use Apache Commons Lang: StringUtils.ordinalIndexOf()
			userIp = userIp.substring(0, userIp.indexOf(":", userIp.indexOf(":") + 1)) + ":*:*:*:*:*:*";
		} else if (address instanceof Inet4Address) {
			userIp = userIp.substring(0, userIp.indexOf(".", userIp.indexOf(".") + 1)) + ".*.*";
		}

		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		KeyFactory keyFactory = datastore.newKeyFactory().setKind("visit");
		IncompleteKey key = keyFactory.setKind("visit").newKey();

		// Record a visit to the datastore, storing the IP and timestamp.
		FullEntity<IncompleteKey> curVisit = FullEntity.newBuilder(key).set("user_ip", userIp)
				.set("timestamp", Timestamp.now()).build();
		datastore.add(curVisit);

		// Retrieve the last 10 visits from the datastore, ordered by timestamp.
		Query<Entity> query = Query.newEntityQueryBuilder().setKind("visit")
				.setOrderBy(StructuredQuery.OrderBy.desc("timestamp")).setLimit(10).build();
		QueryResults<Entity> results = datastore.run(query);

		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();
		out.print("Last 10 visits:\n");
		while (results.hasNext()) {
			Entity entity = results.next();
			out.format("Time: %s Addr: %s\n", entity.getTimestamp("timestamp"), entity.getString("user_ip"));
		}

	}
}
