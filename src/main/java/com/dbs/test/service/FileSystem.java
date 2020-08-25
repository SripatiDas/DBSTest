/**
 * 
 */
package com.dbs.test.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dbs.test.bean.FileDetailResp;
import com.dbs.test.bean.FileDirResponse;

/**
 *
 * This class is used for File System directory and files detail reading
 * Author @author Sripati Das
 */
@Component
public class FileSystem {

	private static final Logger logger = LoggerFactory.getLogger(FileSystem.class);

	/**
	 * This will return size of folder or file if its valid
	 * 
	 * @param path
	 * @return Long
	 */
	private long getSize(String path) {
		File file = Paths.get(path).toFile();
		try {
			if (file.exists()) {
				if (file.isDirectory()) {
					return Files.walk(Paths.get(path)).filter(p -> p.toFile().isFile())
							.mapToLong(p -> p.toFile().length()).sum();
				} else {
					return file.length();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// If not exist or exception then return 0
		return 0;
	}

	private String getFileName(String path) {
		File file = Paths.get(path).toFile();
		if (file.exists()) {
			return file.getName();
		}
		// If not exist or exception then return ""
		return "";
	}

	/**
	 * It will list all of folders and files recursively and return with the file
	 * name including path, size and flag for direcoty or not
	 * 
	 * @param path
	 * @return List
	 */
	public List<FileDirResponse> getListOfFilesnFolders(String path) {
		final List<FileDirResponse> result = new ArrayList<FileDirResponse>();
		try {
			if (Paths.get(path).toFile().exists()) {
				List<String> files = Files.walk(Paths.get(path)).filter(Files::isRegularFile).map(x -> x.toString())
						.collect(Collectors.toList());
				files.forEach(d -> {
					FileDirResponse resp = new FileDirResponse(d, getSize(d), false);
					resp.setFileName(getFileName(d));
					result.add(resp);
				});
				List<String> directories = Files.walk(Paths.get(path)).filter(Files::isDirectory).map(x -> x.toString())
						.collect(Collectors.toList());
				directories.forEach(d -> {
					FileDirResponse resp = new FileDirResponse(d, getSize(d), true);
					resp.setFileName(getFileName(d));
					result.add(resp);
				});
			} else {
				logger.error("Not a valid path {}", path);
				System.err.println("Not a valid path");
			}
		} catch (IOException e) {
			logger.error("Error {} path {}", e, path);
			// System.err.println(e);
			// e.printStackTrace();
		}
		return result;
	}

	/**
	 * It will return a valid file detail informations
	 * 
	 * @param path
	 * @return FileDetailResp
	 */
	public FileDetailResp getFileDetails(String path) {
		FileDetailResp fileDetailResp = null;
		BasicFileAttributes attrs;
		try {
			File file = Paths.get(path).toFile();
			if (file.isFile()) {
				attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
				fileDetailResp = new FileDetailResp();
				fileDetailResp.setFilePath(file.getAbsolutePath());
				fileDetailResp.setName(file.getName());
				fileDetailResp.setLastModified(attrs.lastModifiedTime() + "");
				fileDetailResp.setExecute(file.canExecute());
				fileDetailResp.setRead(file.canRead());
				fileDetailResp.setWrite(file.canWrite());
				fileDetailResp.setCreatedOn(attrs.creationTime() + "");
				fileDetailResp.setRegularFile(attrs.isRegularFile());
				fileDetailResp.setSymbolicLink(attrs.isSymbolicLink());
				fileDetailResp.setLastAccessTime(attrs.lastAccessTime() + "");
			} else {
				logger.error("Not a valid path {}", path);
				// System.err.println("Not a valid File.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// System.err.println(e);
			logger.error("Error {} path {}", e, path);
		}
		return fileDetailResp;
	}

//	/**
//	 * Following code is to see values in JSON without controllers
//	 */
//	public static void main(String[] args) {
//		ObjectMapper mapper = new ObjectMapper();
//		// TODO Auto-generated method stub
//		try {
//			System.out.println(
//					"Running--" + mapper.writeValueAsString(getListOfFilesnFolders("D:\\Prsnl\\Learn\\DBS\\Main")));
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

}
