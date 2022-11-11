/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author DELL
 */
public class UploadImageDao {

    public String uploadImage(String base64, String folder, String imageName) {

//        try {
//            ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
//                    .getContext();
//            String path = context.getRealPath("/WEB-INF/image/");
//        } catch (Exception e) {
//            System.err.println(e);
//        }

        String home = System.getProperty("user.home") + "/upload";
        File file = new File(home);
        if (!file.exists()) {
            file.mkdir();
        }
        String path = home + "/" + folder;
        String location = path + "/" + imageName + ".png";
        byte[] data = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));
        file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        try (OutputStream stream = new FileOutputStream(location)) {
            stream.write(data);
            stream.flush();
            stream.close();
            return location;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadImageDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UploadImageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
