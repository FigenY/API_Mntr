package POJOTEMP;

public class PetStore {

    /**
     * {
     *     "id": 5,
     *     "category": {
     *         "id": 0,
     *         "name": "string"
     *     },
     *     "name": "doggie",
     *     "photoUrls": [
     *         "string"
     *     ],
     *     "tags": [
     *         {
     *             "id": 0,
     *             "name": "string"
     *         }
     *     ],
     *     "status": "string"
     * }
     */
    // local variables
    private int id;

    private String name;
    private String status;

    //constructor
    public PetStore(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    //getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "PetStore{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
