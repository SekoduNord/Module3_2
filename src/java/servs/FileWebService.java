/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servs;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.xml.bind.annotation.XmlMimeType;
import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 *
 * @author Olivier
 */
@WebService(endpointInterface = "servs.FileManager",
        serviceName = "FileWebService ")

public class FileWebService implements FileManager {

    /**
     * This is a sample web service operation
     *
     * @param file
     * @return
     */
    @Override
    public int uploadFile(DataHandler file) {
        int total = 0;
        OutputStream output = null;
        try {

            String pathUpload = "../../files/file.txt";
            File f = new File(getClass().getResource(pathUpload).toURI());
            pathUpload = f.toString();

            InputStream input = file.getInputStream();
            output = new FileOutputStream(pathUpload);
            byte[] b = new byte[100000];
            int bytesRead = 0;
            while ((bytesRead = input.read(b)) != -1) {
                output.write(b, 0, bytesRead);
                total += bytesRead;
            }
        } catch (Exception ex) {
            Logger.getLogger(FileWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    @Override
    public @XmlMimeType("application/octet-stream")
    DataHandler downloadFile() {
        DataSource dataSource = null;
        try {
            String pathDownload = "../../files/file.txt";
            File f = new File(getClass().getResource(pathDownload).toURI());
            pathDownload = f.toString();

            dataSource = new FileDataSource(
                    new File(pathDownload));

        } catch (URISyntaxException ex) {
            Logger.getLogger(FileWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new DataHandler(dataSource);
    }

}
