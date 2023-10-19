package deep_pills.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

public interface PicturesService {
    Map subirImagen(MultipartFile imagen) throws Exception;
    Map eliminarImagen(String idImagen) throws Exception;
}
