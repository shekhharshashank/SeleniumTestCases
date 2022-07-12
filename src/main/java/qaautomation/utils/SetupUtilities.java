package qaautomation.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class SetupUtilities {

	public static String getNDaysfromToday(String dateFormat, int days) {
		if (!(dateFormat.isEmpty() || days < 0)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
			LocalDateTime now = LocalDateTime.now().plusDays(days);
			return dtf.format(now);
		} else {
			return "Null Date format or Negative days Provided";
		}

	}
	FileReader fileReader;
	private static Properties prop;
	static {
		InputStream is = null;
		try {
			prop = new Properties();
			is = ClassLoader.class.getResourceAsStream("/envDetails.properties");
			prop.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getPropertyValue(String key) {
		return prop.getProperty(key);
	}


	@SuppressWarnings("deprecation")
	public static Map<String, String> loadDataFromCsv(String dataNumber,String csvFileName) throws Exception {

		File f = new File("src/resources/"+csvFileName+".csv");
		byte[] bytes = FileUtils.readFileToByteArray(f);
		String data = new String(bytes);
		data = StringUtils.replaceAll(data, "\r", "");
		String[] dataArray = data.split("\n");
		String keys = dataArray[0];

		Map<String, Map<String, String>> outerMap = new HashMap<String, Map<String, String>>();
		List<String> keysFromFile = new ArrayList<String>();

		String[] keyArr = keys.split(",");
		keysFromFile.addAll(Arrays.asList(keyArr));
		keysFromFile.remove(0);

		for(int d = 1; d < dataArray.length; d++) {
			Map<String, String> mp = new HashMap<String, String>();
			List<String> row = new ArrayList<String>();

			String[] rowArr = dataArray[d].split(",");
			row.addAll(Arrays.asList(rowArr));
			String keyForTestCase = row.get(0);
			row.remove(0);

		for( int i = 0; i < keysFromFile.size(); i++) {
			mp.put(keysFromFile.get(i).trim(), row.get(i).trim());
		}
		outerMap.put(keyForTestCase, mp);
		}

		return outerMap.get(dataNumber);


	}
}
