/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class UploadImageDao {
    public String uploadImage(String base64){
        String location = "E://upload/" + "demoname.png";
        byte[] data = Base64.getDecoder()
                    .decode(base64.getBytes(StandardCharsets.UTF_8));
        try (OutputStream stream = new FileOutputStream(location)) {
            stream.write(data);
            stream.flush();
            stream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadImageDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UploadImageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return location;
    }
}
