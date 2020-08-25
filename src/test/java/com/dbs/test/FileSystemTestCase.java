/**
 * 
 */
package com.dbs.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.dbs.test.bean.FileDetailResp;
import com.dbs.test.bean.FileDirResponse;
import com.dbs.test.service.FileSystem;

/**
 * @author SD00422212
 *
 */
class FileSystemTestCase {
	private static FileSystem fs = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		fs = new FileSystem();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		fs = null;
	}

	@Test
	void testGetFileDetails() {
		FileDetailResp res = fs.getFileDetails("");
		assertEquals(null, res, "Non Existing or valid file");
	}

	@Test
	void testGetListOfFilesnFolders() {
		List<FileDirResponse> res = fs.getListOfFilesnFolders("");
		assertEquals(0, res.size());
	}
}
