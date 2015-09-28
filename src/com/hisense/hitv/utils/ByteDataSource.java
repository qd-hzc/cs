package com.hisense.hitv.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;

/**
 * @author wangheping
 */
public class ByteDataSource implements DataSource {
    private byte[] filebyte = null;
    private String filetype = "application/octet-stream";
    private String filename = "";
    private OutputStream outputstream = null;
    private InputStream inputstream = null;

    /**
     * @param fileName 文件名
     */
    public ByteDataSource(String fileName) {
        File f = new File(fileName);
        filename = f.getName();
        try {
            inputstream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            inputstream.read(filebyte);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param filebyte 文件字节
     * @param displayfilename 显示文件名
     */
    public ByteDataSource(byte[] filebyte, String displayfilename) {
        this.filebyte = filebyte;
        this.filename = displayfilename;
    }

    /**
     *{@inheritDoc}
     */
    public String getContentType() {
        return filetype;
    }

    /**
     *{@inheritDoc}
     */
    public InputStream getInputStream() throws IOException {
        InputStream input = new ByteArrayInputStream(filebyte);
        return input;
    }

    /**
     *{@inheritDoc}
     */
    public String getName() {
        return filename;
    }

    /**
     *{@inheritDoc}
     */
    public OutputStream getOutputStream() throws IOException {

        outputstream.write(filebyte);
        return outputstream;
    }
}
