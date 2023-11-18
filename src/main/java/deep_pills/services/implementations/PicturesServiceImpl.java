package deep_pills.services.implementations;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import deep_pills.services.interfaces.PicturesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PicturesServiceImpl implements PicturesService {
    private final Cloudinary cloudinary;
    public PicturesServiceImpl(){
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dqa9pp8om",
                "api_key", "766135483155282",
                "api_secret", "Hqb1CxZLD7tmp4embwjwklgHM1A",
                "secure", true));
    }

    @Override
    public Map subirImagen(MultipartFile imagen) throws Exception {
        File file = convertir(imagen);
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder",
                "DeepPills"));
    }
    @Override
    public Map eliminarImagen(String idImagen) throws Exception {
        return cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());
    }
    private File convertir(MultipartFile imagen) throws IOException {
        File file = File.createTempFile(imagen.getOriginalFilename(), null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());
        fos.close();
        return file;
    }
}
