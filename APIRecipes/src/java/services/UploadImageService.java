/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.sun.jersey.multipart.FormDataParam;
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
    @Path("/image")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String uploadFile(@FormDataParam("file") InputStream fileStream) {
        return saveToDisk(fileStream);
    }

    private String saveToDisk(InputStream uploadInputStream) {
        String location = "E://upload/" + "demoname.jpg";
        try {
            OutputStream out = new FileOutputStream(new File(location));
            int size = 0;
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(location));
            while ((read = uploadInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
                size += read;
            }
            out.flush();
            out.close();
            return String.valueOf(size);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadImageService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UploadImageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Success!";
    }
}
