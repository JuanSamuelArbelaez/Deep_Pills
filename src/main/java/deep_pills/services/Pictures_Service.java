package deep_pills.services;

import java.io.File;

public interface Pictures_Service {
    String uploadPicture(File file) throws Exception;
    File retrievePicture(String url) throws Exception;
    String deletePicture(String url) throws Exception;
}
