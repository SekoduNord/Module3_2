/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servs;

import javax.activation.DataHandler;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Olivier
 */
@WebService
public interface FileManager {

    int uploadFile(@WebParam(name = "file") DataHandler file);

    DataHandler downloadFile();
}
