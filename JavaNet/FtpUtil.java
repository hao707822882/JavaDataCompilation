package com.itjh.javaUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * ��������ftp���ۺ��ࡣ<br/>
 * ��Ҫ����jar��commons-net-3.1.jar��
 * 
 * @author ������
 * @date 2014��06��25��
 */
public class FtpUtil {
	// ftp ��ַ
	private String url;
	// ftp�˿�
	private int port;
	// �û���
	private String userName;
	// ����
	private String password;

	/**
	 * ���캯��
	 * 
	 * @param url
	 *            ftp��ַ
	 * @param port
	 *            ftp�˿�
	 * @param userName
	 *            �û���
	 * @param password
	 *            ����
	 * @author ������
	 * @date 2014��06��25��
	 *
	 */
	public FtpUtil(String url, int port, String userName, String password) {
		this.url = url;
		this.port = port;
		this.userName = userName;
		this.password = password;
	}

	/**
	 * ��FTP����������ָ���ļ������ļ���
	 * 
	 * @param remotePath
	 *            FTP�������ϵ����·��
	 * @param fileName
	 *            Ҫ���ص��ļ���
	 * @param localPath
	 *            ���غ󱣴浽���ص�·��
	 * @return �ɹ����ط���true�����򷵻�false��
	 * @throws IOException
	 * @author ������
	 * @date 2014��06��25��
	 */
	public boolean downFile(String remotePath, String fileName, String localPath)
			throws IOException {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);
			// �������Ĭ�϶˿ڣ�����ʹ��ftp.connect(url)�ķ�ʽֱ������FTP������
			ftp.login(userName, password);// ��¼
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.changeWorkingDirectory(remotePath);// ת�Ƶ�FTP������Ŀ¼
			FTPFile[] fs = ftp.listFiles();
			FTPFile ff;
			for (int i = 0; i < fs.length; i++) {
				ff = fs[i];
				if (null != ff && null != ff.getName()
						&& ff.getName().equals(fileName)) {
					File localFile = new File(localPath + "/" + ff.getName());
					OutputStream is = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), is);
					is.close();
				}
			}
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}

	/**
	 * ��FTP�������г�ָ���ļ������ļ����б�
	 * 
	 * @param remotePath
	 *            FTP�������ϵ����·��
	 * @return List<String> �ļ����б���������쳣����null��
	 * @throws IOException
	 * @author ������
	 * @date 2014��06��25��
	 */
	public List<String> getFileNameList(String remotePath) throws IOException {
		// Ŀ¼�б��¼
		List<String> fileNames = new ArrayList<String>();
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);
			// �������Ĭ�϶˿ڣ�����ʹ��ftp.connect(url)�ķ�ʽֱ������FTP������
			ftp.login(userName, password);// ��¼
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return null;
			}
			ftp.changeWorkingDirectory(remotePath);// ת�Ƶ�FTP������Ŀ¼
			FTPFile[] fs = ftp.listFiles();
			for (FTPFile file : fs) {
				fileNames.add(file.getName());
			}
			ftp.logout();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return fileNames;
	}

}