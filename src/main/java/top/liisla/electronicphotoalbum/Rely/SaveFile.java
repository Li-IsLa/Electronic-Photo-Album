package top.liisla.electronicphotoalbum.Rely;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Component
public class SaveFile {
    private static final String UPLOAD_DIR = "D:\\nginx-1.27.4\\nginx-1.27.4\\html\\web-Electronic-Photo-Album\\upFileImg";
    public boolean saveFileToLocal(MultipartFile file, String newFileName) {
        try {
            // 创建目标目录（如果不存在）
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            // 获取文件的扩展名
            String originalFileName = Objects.requireNonNull(file.getOriginalFilename());
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            // 保存文件到服务器，使用新的文件名
            File targetFile = new File(uploadDir, newFileName + extension);
            file.transferTo(targetFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public String getFileSuffix(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        if (originalFileName != null && originalFileName.contains(".")) {
            return originalFileName.substring(originalFileName.lastIndexOf("."));
        }
        return "";
    }
}
