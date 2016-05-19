package gen3check.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class CSVFileReader {
	public CSVFileReader(){
		
	}

	/**
	 * Gets line. CSVFileReader creates the Stream
	 * @param n
	 * @param path
	 * @return
	 */
	public String[] getLine(int n, String path){
		InputStream fis = getClass().getResourceAsStream("/database/" + path);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		String[] result = null;
		try {
			for (int i = 0; i < n + 1; i++){
				br.readLine();
			}
			result = br.readLine().split(",");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
