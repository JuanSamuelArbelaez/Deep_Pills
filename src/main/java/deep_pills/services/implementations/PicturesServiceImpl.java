package deep_pills.services.implementations;

import deep_pills.services.interfaces.PicturesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Service
public class PicturesServiceImpl implements PicturesService {
    @Override
    @Transactional
    public String uploadPicture(File file) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public File retrievePicture(String url) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public String deletePicture(String url) throws Exception {
        return null;
    }
}
