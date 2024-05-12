import java.util.LinkedList;
import java.util.List;

/**
 * Represents a directory in a file system.
 */
class Directory extends FileSystemElement {
    private List<FileSystemElement> children;

    /**
     * Constructs a new Directory object with the specified name and parent element.
     *
     * @param name    the name of the directory
     * @param parent  the parent element (directory or null if root)
     */
    public Directory(String name, FileSystemElement parent) {
        super(name, parent);
        children = new LinkedList<>();
    }

    /**
     * Adds a FileSystemElement (either File or Directory) to the directory.
     *
     * @param element the element to add
     */
    public void addElement(FileSystemElement element) {
        children.add(element);
    }

    /**
     * Removes a FileSystemElement (either File or Directory) from the directory.
     *
     * @param element the element to remove
     */
    public void removeElement(FileSystemElement element) {
        children.remove(element);
    }

    /**
     * Prints the directory structure recursively.
     *
     * @param prefix the prefix for indentation
     */
    @Override
    public void print(String prefix) {
        System.out.print(prefix + "Directory: " + getName());
        for (FileSystemElement elem : children) {
            elem.print(prefix + " ");
        }
    }

    /**
     * Retrieves the File with the specified name if it exists in the directory.
     *
     * @param fileName the name of the file to search for
     * @return the File object if found, otherwise null
     */
    public File getChildFile(String fileName) {
        for (FileSystemElement element : children) {
            if (element instanceof File && element.getName().equals(fileName)) {
                return (File) element;
            }
        }
        return null; // File not found
    }

    /**
     * Retrieves the subdirectory with the specified name if it exists in the directory.
     *
     * @param directoryName the name of the subdirectory to search for
     * @return the Directory object if found, otherwise null
     */
    public Directory getChildDirectory(String directoryName) {
        for (FileSystemElement element : children) {
            if (element instanceof Directory && element.getName().equals(directoryName)) {
                return (Directory) element;
            }
        }
        return null; // Directory not found
    }

    /**
     * Returns the list of children (both files and directories) contained in this directory.
     *
     * @return the list of children FileSystemElements
     */
    public List<FileSystemElement> getChildren() {
        return children;
    }

    /**
     * Recursively deletes all files and subdirectories within this directory.
     * Clears the children list after deletion.
     */
    public void deleteRecursive() {
        for (FileSystemElement element : children) {
            if (element instanceof File) {
                // If it's a file, remove it
                removeElement(element);
            } else if (element instanceof Directory) {
                // If it's a directory, recursively delete it
                ((Directory) element).deleteRecursive();
            }
        }
        // Clear the children list after deletion
        children.clear();
    }
}