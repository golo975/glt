package main.java.com.glt.webtest.SE;

import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpUtils {

	private String[] strArray = { "boot", "foob", "food", "click" };

	public void exclude() {
		// Pattern p = Pattern.compile("^(?!(oo)).*$");
		Pattern p = Pattern.compile("(?!.*(oo)).*");
		Matcher m = null;
		for (String s : strArray) {
			m = p.matcher(s);
			boolean flag = m.matches();
			if (flag) {
				System.out.println(s);
				Assert.assertEquals("click", s);
			}
		}
	}

	@Test
	public void jsonTest() {
		try {
			String s = FileUtils.readFileToString(FileUtils.getFile("d:",
					"glt", "json.txt"));
			System.out.println(s);
			JSONObject json = JSONObject.fromObject(s.replaceAll("\\r\\n","\\\\r\\\\n"));
			JSONObject jsonparam = (JSONObject) json.get("param");
			JSONObject jsonworkorder = (JSONObject) jsonparam.get("workorder");
			String jsonhfnr = (String) jsonworkorder.get("hfnr");
			System.out.println(jsonparam);
			System.out.println(jsonworkorder);
			System.out.println(jsonhfnr);

			System.out.println("abcdefg\r\n123124");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
