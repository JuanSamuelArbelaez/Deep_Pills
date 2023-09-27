package deep_pills.services.interfaces;

import java.io.File;

public interface PicturesService {
    String uploadPicture(File file) throws Exception;
    File retrievePicture(String url) throws Exception;
    String deletePicture(String url) throws Exception;
}