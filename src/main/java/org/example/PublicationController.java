import java.util.*;
import java.util.regex.*;

// Custom exception for validation errors
class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}

// Publication class
record Publication(String name, String description, double price) {}

// Repository simulating a database
class PublicationRepository {
    private final Set<String> publications = new HashSet<>();

    public boolean existsByName(String name) {
        return publications.contains(name);
    }

    public void save(Publication publication) {
        publications.add(publication.name());
    }
}

// Service layer
class PublicationService {
    private final PublicationRepository repository;
    private static final Pattern PRICE_PATTERN = Pattern.compile("\\d+\\.00");

    public PublicationService(PublicationRepository repository) {
        this.repository = repository;
    }

    public void createPublication(String name, String description, String priceStr) {
        validatePublication(name, description, priceStr);
        double price = Double.parseDouble(priceStr);

        if (repository.existsByName(name)) {
            throw new ValidationException("Publication already exists.");
        }

        Publication publication = new Publication(name, description, price);
        repository.save(publication);
        System.out.println("Publication created successfully!");
    }

    private void validatePublication(String name, String description, String priceStr) {
        if (name == null || name.isBlank() || name.length() > 255) {
            throw new ValidationException("Publication name must be between 1 and 255 characters.");
        }
        if (description == null || description.isBlank() || description.length() > 1024) {
            throw new ValidationException("Description must be between 1 and 1024 characters.");
        }
        if (!PRICE_PATTERN.matcher(priceStr).matches()) {
            throw new ValidationException("Price must be in .00 format.");
        }
    }
}

// Main controller
public class PublicationController {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PublicationRepository repository = new PublicationRepository();
        PublicationService service = new PublicationService(repository);

        System.out.println("Enter publication name:");
        String name = scanner.nextLine();

        System.out.println("Enter description:");
        String description = scanner.nextLine();

        System.out.println("Enter price (.00 format):");
        String priceStr = scanner.nextLine();

        try {
            service.createPublication(name, description, priceStr);
        } catch (ValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

