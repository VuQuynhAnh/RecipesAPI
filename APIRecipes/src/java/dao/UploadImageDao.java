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
import java.io.InputStream;
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

    public String uploadImage(String base64, String path, String folder, String imageName) {
        path += "/" + folder;
        imageName += ".png";
        String location = path + "/" + imageName;
        byte[] data = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        try (OutputStream stream = new FileOutputStream(location)) {
            stream.write(data);
            stream.flush();
            stream.close();
            return folder + "/" + imageName;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadImageDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UploadImageDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public String uploadImage(InputStream uploadedInputStream, String path, String folder, String imageName) {
        path += "/" + folder;
        imageName += ".png";
        String location = path + "/" + imageName;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            OutputStream out = new FileOutputStream(new File(location));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(location));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            return folder + "/" + imageName;
        } catch (IOException e) {
            System.err.println(e);
        }
        return "";
    }
}
