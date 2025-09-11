// 代码生成时间: 2025-09-11 16:03:55
@SpringBootApplication
public class BatchFileRenamerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchFileRenamerApplication.class, args);
    }
}

/**
 * 控制器类，用于处理REST API请求
 */
@RestController
@RequestMapping("/api/files")
public class FileRenamerController {

    private final FileService fileService;

    @Autowired
    public FileRenamerController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/rename")
    public ResponseEntity<?> renameFiles(@RequestBody List<FileInfo> fileInfoList) {
        try {
            fileService.renameFiles(fileInfoList);
            return ResponseEntity.ok().body("Files have been renamed successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error occurred during file renaming: " + e.getMessage());
        }
    }
}

/**
 * 文件信息类，用于封装文件信息
 */
public class FileInfo {
    private String oldName;
    private String newName;

    // Getters and setters
    public String getOldName() {
        return oldName;
    }
    public void setOldName(String oldName) {
        this.oldName = oldName;
    }
    public String getNewName() {
        return newName;
    }
    public void setNewName(String newName) {
        this.newName = newName;
    }
}

/**
 * 服务类，用于处理文件重命名逻辑
 */
@Service
public class FileService {

    public void renameFiles(List<FileInfo> fileInfoList) throws IOException {
        for (FileInfo fileInfo : fileInfoList) {
            File oldFile = new File(fileInfo.getOldName());
            File newFile = new File(fileInfo.getNewName());
            if (oldFile.exists() && oldFile.renameTo(newFile)) {
                // Rename successful
            } else {
                throw new IOException("Failed to rename file from " + fileInfo.getOldName() + " to " + fileInfo.getNewName());
            }
        }
    }
}

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        // Log the exception
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
