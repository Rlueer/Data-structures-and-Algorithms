import java.sql.Timestamp;

/**
 * Represents an abstract file system element.
 */
abstract class FileSystemElement {
    protected String name;
    protected Timestamp dateCreated;
    protected FileSystemElement parent;

    /**
     * Constructs a new FileSystemElement with the specified name and parent element.
     *
     * @param name    the name of the file system element
     * @param parent  the parent element (directory or null if root)
     */
    public FileSystemElement(String name, FileSystemElement parent) {
        this.name = name;
        this.parent = parent;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Retrieves the name of the file system element.
     *
     * @return the name of the file system element
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the creation date of the file system element.
     *
     * @return the creation date of the file system element
     */
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    /**
     * Retrieves the parent directory of the file system element.
     *
     * @return the parent directory of the file system element
     */
    public FileSystemElement getParent() {
        return parent;
    }

    /**
     * Sets the parent directory of the file system element.
     *
     * @param parent the parent directory to set
     */
    public void setParent(FileSystemElement parent) {
        this.parent = parent;
    }

    /**
     * Prints the file system element.
     *
     * @param prefix the prefix for indentation
     */
    public abstract void print(String prefix);
}
