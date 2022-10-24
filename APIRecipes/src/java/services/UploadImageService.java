/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author DELL
 */
@Stateless
@Path("/upload")
public class UploadImageService {

    @POST
    @Path("/upload")
    @Produces("text/xml")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public String uploadFile(@FormDataParam("file") InputStream uploadInputStream, @FormDataParam("fileName") String txtName) {
    public String uploadFile(InputStream uploadInputStream, String txtName) {

        saveToDisk(uploadInputStream, txtName);
        return "Success!";
    }

    private void saveToDisk(InputStream uploadFileInputStream, String txtName) {
        String uploadFileLocation = "E://upload/" + txtName;
        try {
            OutputStream out = new FileOutputStream(new File(uploadFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadFileLocation));
            while ((read = uploadFileInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadImageService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UploadImageService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
