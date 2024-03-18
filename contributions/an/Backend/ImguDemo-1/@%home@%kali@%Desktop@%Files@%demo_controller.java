import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@RestController
@RequestMapping("/api")
public class ImageController {

    @GetMapping("/")
    public string quickStart(){
        return "Hello";
    }

    // Define the folder where uploaded images will be saved
    private static final String UPLOAD_FOLDER = "/media/img/";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        // Check if the file is not empty
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please upload a file", HttpStatus.BAD_REQUEST);
        }

        try {
            // Get the bytes from the uploaded file
            byte[] bytes = file.getBytes();
            // Construct the file path where the image will be saved
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            // Write the bytes to the file
            Files.write(path, bytes);
            
            // Return success message
            return new ResponseEntity<>("Uploaded successfully: " + file.getOriginalFilename(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/image/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
        // Load the image file from the specified folder
        Path imagePath = Paths.get(UPLOAD_FOLDER + imageName);
        
        try {
            // Read the bytes of the image file
            byte[] imageBytes = Files.readAllBytes(imagePath);
            // Return the image bytes with appropriate content type
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageBytes);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private byte[] getDummyImage() {
        // Dummy image generation logic
        return new byte[0]; // Replace this with actual image bytes
    }
}
