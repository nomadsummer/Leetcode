package google.phone;

import java.io.File;

/*
 * 给个directory，找出其下面所有的java文件
 */
public class FindAllFilesUnderDirectory {

	public void search(String dir) {
		File[] files = new File(dir).listFiles();
		
		for(File file : files) {
			if(file.isDirectory()) {
				search(file.getAbsolutePath());
			} else if(file.getName().matches("\\w+.java")) {
				System.out.println(file.getAbsolutePath());
			}
		}
	}
}
