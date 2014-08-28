package com.itjh.javaUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;

/**
 * Zip�������࣬������commons-compress-1.5.jar��
 * 
 * @author ������
 * @date 2014��06��25��
 */
public class ZipUtil {

	// public static void main(String[] args){
	// try {
	// //new ZipUtil().decompressZip(new
	// File("d://img.zip"),"img/pic20140626.jpg","d://");
	// new ZipUtil().decompressZip(new File("d://img.zip"),"flight.log","d://");
	// //new File("d://flight.log").delete();
	// //ZipUtil.compress(new File("D://����ѹ���ļ�"),new File("d://img.zip"));
	// // ZipUtil.compress(new File[]{new
	// File("F:/testZIP/testzip.txt"),new File("d://ftp"),new
	// File("e://ftp")},new File("d://ѹ���ļ�.zip"));
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * ��N���ļ����ļ���ѹ����zip��
	 * 
	 * @param files
	 *            ��Ҫѹ�����ļ����ļ��С�
	 * @param zipFilePath
	 *            ѹ�����zip�ļ�
	 * @throws IOException
	 *             ѹ��ʱIO�쳣��
	 * @author ������
	 * @date 2014��06��25��
	 */
	public static void compress(File[] files, File zipFile) throws IOException {
		if (CollectionUtil.isEmpty(files)) {
			return;
		}
		ZipArchiveOutputStream out = new ZipArchiveOutputStream(zipFile);
		out.setUseZip64(Zip64Mode.AsNeeded);
		// ��ÿ���ļ���ZipArchiveEntry��װ
		for (File file : files) {
			if (file == null) {
				continue;
			}
			compressOneFile(file, out, "");
		}
		if (out != null) {
			out.close();
		}
	}

	/**
	 * ���ܣ�ѹ���ļ����ļ��С�
	 * 
	 * @author ������
	 * @date 2014��06��25��
	 * @param srcFile
	 *            Դ�ļ���
	 * @param destFile
	 *            ѹ������ļ�
	 * @throws IOException
	 *             ѹ��ʱ�������쳣��
	 */
	public static void compress(File srcFile, File destFile) throws IOException {
		ZipArchiveOutputStream out = null;
		try {
			out = new ZipArchiveOutputStream(new BufferedOutputStream(
					new FileOutputStream(destFile), 1024));
			compressOneFile(srcFile, out, "");
		} finally {
			out.close();
		}
	}

	/**
	 * ���ܣ�ѹ�������ļ�,���ļ��С�˽�У������⿪�š�
	 * 
	 * @author ������
	 * @date 2014��06��25��
	 * @param srcFile
	 *            Դ�ļ����������ļ��С�
	 * @param out
	 *            ѹ���ļ����������
	 * @param destFile
	 *            ѹ������ļ�
	 * @param dir
	 *            ��ѹ�����е�λ��,��Ŀ¼����/��
	 * @throws IOException
	 *             ѹ��ʱ�������쳣��
	 */
	private static void compressOneFile(File srcFile,
			ZipArchiveOutputStream out, String dir) throws IOException {
		if (srcFile.isDirectory()) {// ���ļ��н��д���
			ZipArchiveEntry entry = new ZipArchiveEntry(dir + srcFile.getName()
					+ "/");
			out.putArchiveEntry(entry);
			out.closeArchiveEntry();
			// ѭ���ļ����е������ļ�����ѹ������
			String[] subFiles = srcFile.list();
			for (String subFile : subFiles) {
				compressOneFile(new File(srcFile.getPath() + "/" + subFile),
						out, (dir + srcFile.getName() + "/"));
			}
		} else { // ��ͨ�ļ���
			InputStream is = null;
			try {
				is = new BufferedInputStream(new FileInputStream(srcFile));
				// ����һ��ѹ������
				ZipArchiveEntry entry = new ZipArchiveEntry(srcFile, dir
						+ srcFile.getName());
				out.putArchiveEntry(entry);
				IOUtils.copy(is, out);
				out.closeArchiveEntry();
			} finally {
				if (is != null)
					is.close();
			}
		}
	}

	/**
	 * ���ܣ���ѹ��zipѹ�����µ������ļ���
	 * 
	 * @author ������
	 * @date 2014��06��25��
	 * @param zipFile
	 *            zipѹ���ļ�
	 * @param dir
	 *            ��ѹ�������·����
	 * @throws IOException
	 *             �ļ����쳣
	 */
	public void decompressZip(File zipFile, String dir) throws IOException {
		ZipFile zf = new ZipFile(zipFile);
		try {
			for (Enumeration<ZipArchiveEntry> entries = zf.getEntries(); entries
					.hasMoreElements();) {
				ZipArchiveEntry ze = entries.nextElement();
				// �������򴴽�Ŀ���ļ��С�
				File targetFile = new File(dir, ze.getName());
				// ������Ŀ¼ʱ������
				if (ze.getName().lastIndexOf("/") == (ze.getName().length() - 1)) {
					continue;
				}
				// ����ļ��в����ڣ������ļ��С�
				if (!targetFile.getParentFile().exists()) {
					targetFile.getParentFile().mkdirs();
				}

				InputStream i = zf.getInputStream(ze);
				OutputStream o = null;
				try {
					o = new FileOutputStream(targetFile);
					IOUtils.copy(i, o);
				} finally {
					if (i != null) {
						i.close();
					}
					if (o != null) {
						o.close();
					}
				}
			}
		} finally {
			zf.close();
		}
	}

	/**
	 * ���ܣ���ѹ��zipѹ�����µ�ĳ���ļ���Ϣ��
	 * 
	 * @author ������
	 * @date 2014��06��25��
	 * @param zipFile
	 *            zipѹ���ļ�
	 * @param fileName
	 *            ĳ���ļ���,����abc.zip�����a.jpg����Ҫ����/abc/a.jpg��
	 * @param dir
	 *            ��ѹ�������·����
	 * @throws IOException
	 *             �ļ����쳣
	 */
	public void decompressZip(File zipFile, String fileName, String dir)
			throws IOException {
		// �������򴴽�Ŀ���ļ��С�
		File targetFile = new File(dir, fileName);
		if (!targetFile.getParentFile().exists()) {
			targetFile.getParentFile().mkdirs();
		}

		ZipFile zf = new ZipFile(zipFile);
		Enumeration<ZipArchiveEntry> zips = zf.getEntries();
		ZipArchiveEntry zip = null;
		while (zips.hasMoreElements()) {
			zip = zips.nextElement();
			if (fileName.equals(zip.getName())) {
				OutputStream o = null;
				InputStream i = zf.getInputStream(zip);
				try {
					o = new FileOutputStream(targetFile);
					IOUtils.copy(i, o);
				} finally {
					if (i != null) {
						i.close();
					}
					if (o != null) {
						o.close();
					}
				}
			}
		}
	}

	/**
	 * ���ܣ��õ�zipѹ�����µ�ĳ���ļ���Ϣ,ֻ���ڸ�Ŀ¼�²��ҡ�
	 * 
	 * @author ������
	 * @date 2014��06��25��
	 * @param zipFile
	 *            zipѹ���ļ�
	 * @param fileName
	 *            ĳ���ļ���,����abc.zip�����a.jpg����Ҫ����/abc/a.jpg��
	 * @return ZipArchiveEntry ѹ���ļ��е�����ļ�,û���ҵ�����null��
	 * @throws IOException
	 *             �ļ����쳣
	 */
	public ZipArchiveEntry readZip(File zipFile, String fileName)
			throws IOException {
		ZipFile zf = new ZipFile(zipFile);
		Enumeration<ZipArchiveEntry> zips = zf.getEntries();
		ZipArchiveEntry zip = null;
		while (zips.hasMoreElements()) {
			zip = zips.nextElement();
			if (fileName.equals(zip.getName())) {
				return zip;
			}
		}
		return null;
	}

	/**
	 * ���ܣ��õ�zipѹ�����µ������ļ���Ϣ��
	 * 
	 * @author ������
	 * @date 2014��06��25��
	 * @param zipFile
	 *            zipѹ���ļ�
	 * @return Enumeration<ZipArchiveEntry> ѹ���ļ��е��ļ�ö�١�
	 * @throws IOException
	 *             �ļ����쳣
	 */
	public Enumeration<ZipArchiveEntry> readZip(File zipFile)
			throws IOException {
		ZipFile zf = new ZipFile(zipFile);
		Enumeration<ZipArchiveEntry> zips = zf.getEntries();
		return zips;
	}
}