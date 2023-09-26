package deep_pills.services.implementations;

import deep_pills.services.interfaces.PicturesService;
import org.springframework.stereotype.Service;
import java.io.File;

@Service
public class PicturesServiceImpl implements PicturesService {
    @Override
    public String uploadPicture(File file) throws Exception {
        return null;
    }

    @Override
    public File retrievePicture(String url) throws Exception {
        return null;
    }

    @Override
    public String deletePicture(String url) throws Exception {
        return null;
    }
}
