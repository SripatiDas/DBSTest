package com.dbs.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import com.dbs.test.bean.FileDetailResp;
import com.dbs.test.bean.FileDirResponse;
import com.dbs.test.service.FileSystem;

@RestController
public class FileSystemServiceController {

	@Autowired
	public FileSystem fileSystem;

	private static final Logger logger = LoggerFactory.getLogger(FileSystemServiceController.class);

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String register() {
		return "Hello world...!";
	}

	/**
	 * This method will take file with the path and returns detailed info of the
	 * file in JSON.
	 * 
	 * @param file
	 * @return FileDetailResp
	 */
	@RequestMapping(value = "/fileDetails/**", method = RequestMethod.GET)
	public Object getFileDetails(HttpServletRequest request) {
		FileDetailResp fileDetailResp = null;
		String file = "";
		try {
			file = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
			String[] values = (null != file) ? file.split("/fileDetails/") : null;
			file = null != values && values.length > 1 ? values[1] : null;
			if (null == file || file.trim().equals("")) {
				logger.error("Not a valid file path {}", file);
				return new ResponseEntity<>("File name should be valid.", HttpStatus.BAD_REQUEST);
			}

			fileDetailResp = fileSystem.getFileDetails(file);
			if (null == fileDetailResp) {
				logger.error("Not a valid file path {}", file);
				return new ResponseEntity<>("Not a valid file or path. Please check and retry.",
						HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			logger.error("Something went wrong with the file {} Exception:{}", file, e);
			return new ResponseEntity<>(
					"Went Something wrong with the file " + file + " detail extraction! Exception-" + e,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return fileDetailResp;
	}

	/**
	 * It will read passed directory and returns all detailed info of files and directories as recursive manner.
	 */
	@RequestMapping(value = "/fileDirSystem/**", method = RequestMethod.GET)
	public Object getFileFoldersList(HttpServletRequest request) {
		List<FileDirResponse> fileDirResponseList;
		String file = "";
		try {
			file = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
			String[] values = (null != file) ? file.split("/fileDirSystem/") : null;
			file = null != values && values.length > 1 ? values[1] : null;
			if (null == file || file.trim().equals("")) {
				logger.error("Not a valid file path {}", file);
				return new ResponseEntity<>("File name should be valid.", HttpStatus.BAD_REQUEST);
			}

			fileDirResponseList = fileSystem.getListOfFilesnFolders(file);
			if (null == fileDirResponseList || fileDirResponseList.size() <= 0) {
				logger.error("Not a valid file path {}", file);
				return new ResponseEntity<>("Not a valid file or path. Please check and retry.",
						HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			logger.error("Something went wrong with the file {} Exception:{}", file, e);
			return new ResponseEntity<>(
					"Went Something wrong with the file " + file + " detail extraction! Exception-" + e,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return fileDirResponseList;
	}
}
