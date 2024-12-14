package uopeople.assignment.unit4;



import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.stream.Stream;
import java.util.List;
import java.util.stream.Collectors;


public class Image {

    private InputStream imageStream;

    public Image(InputStream inputStream) {
        this.imageStream = inputStream;
    }

    public InputStream getInputStream() {
        return this.imageStream;
    }
}

abstract class ExternalImageStream extends InputStream {

    public ExternalImageStream() {
    }

    public int read() throws IOException {
        return -1; // Simulating end-of-stream
    }
}

class LocalImageStream extends FileInputStream {

    public LocalImageStream(String filePath) throws FileNotFoundException {
        super(filePath);
    }
}

class AwsStream extends ExternalImageStream {
    private String bucketName;

    public AwsStream(String bucketName) {
        this.bucketName = bucketName;
    }
}

class AzureFileShareImageStream extends ExternalImageStream {
    private String fileSharePath;

    public AzureFileShareImageStream(String fileSharePath) {
        this.fileSharePath = fileSharePath;
    }
}

class FacebookImageStream extends ExternalImageStream {
    private String albumName;
    public FacebookImageStream(String albumName) {
        this.albumName = albumName;
    }
}

class InstagramImageStream extends ExternalImageStream {
    private String userHandle;
    public InstagramImageStream(String userHandle) {
        this.userHandle = userHandle;
    }
}

class GoogleImageStream extends ExternalImageStream {
    private String driveFolderPath;
    public GoogleImageStream(String driveFolderPath) {
        this.driveFolderPath = driveFolderPath;
    }
}

// Main Application
class EcommerceImageManager {
    public static void main(String[] args) throws FileNotFoundException {

        // Create instances of different stream types
        InputStream awsStream = new AwsStream("ecommerce-bucket");
        InputStream azureStream = new AzureFileShareImageStream("/shared/images");
        InputStream facebookStream = new FacebookImageStream("Holiday_Album");
        InputStream instagramStream = new InstagramImageStream("@ecommerce_insta");
        InputStream googleStream = new GoogleImageStream("/drive/ecommerce/images");
        InputStream fileStream = new LocalImageStream("C:/ecommerce/images/product1.jpg");

        // Create Image objects and fetch data from streams
        //java.stream this namespace is for manage data, objects,

        Stream<Image> imageStream = Stream.of(
                new Image(awsStream),
                new Image(azureStream),
                new Image(facebookStream),
                new Image(instagramStream),
                new Image(googleStream),
                new Image(fileStream));

        
        List<Image> externalImagens = imageStream
                .filter(image -> image.getInputStream() instanceof ExternalImageStream)
                .collect(Collectors.toList());

    }
}
