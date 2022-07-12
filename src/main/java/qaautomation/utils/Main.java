package qaautomation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class Main {



	public static <T> List<T> read(Class<T> clazz, InputStream stream, boolean withHeaders, char separator) throws IOException {
	    CsvMapper mapper = new CsvMapper();

	    mapper.enable(CsvParser.Feature.TRIM_SPACES);
	    //mapper.enable(CsvParser.Feature.ALLOW_TRAILING_COMMA);
	   // mapper.enable(CsvParser.Feature.INSERT_NULLS_FOR_MISSING_COLUMNS);
	   // mapper.enable(CsvParser.Feature.SKIP_EMPTY_LINES);
	    mapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
	    CsvSchema schema = mapper.schemaFor(clazz).withColumnReordering(true);
	    ObjectReader reader;
	    if (separator == '\t') {
	        schema = schema.withColumnSeparator('\t');
	    }
	    else {
	        schema = schema.withColumnSeparator(',');
	    }
	    if (withHeaders) {
	        schema = schema.withHeader();
	    }
	    else {
	        schema = schema.withoutHeader();
	    }
	    reader = mapper.readerFor(clazz).with(schema);
	    return reader.<T>readValues(stream).readAll();
	}

	private static void loadDataFromCsv() throws Exception {

		File f = new File("src/resources/loginDetails.csv");

		String absolute = f.getAbsolutePath()+"\\loginDetails.csv";

		System.out.println(absolute);

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
		Map<String, String> rowMap = outerMap.get("T1");

		System.out.println(rowMap.get("UserPass"));

	}


		public static void main(String[] args) throws Exception {

			//loadDataFromCsv();
			//Tue Jun 08 2021
			String dateRequired=SetupUtilities.getNDaysfromToday("E MMM dd yyyy",4);
			String dateReturn=SetupUtilities.getNDaysfromToday("E,MMM dd",5);

			System.out.println(dateRequired);

		}
}
