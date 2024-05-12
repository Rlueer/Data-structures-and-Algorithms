import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Date;

/**
 * Class representing the file system.
 */
class FileSystem {
    private Directory root;
    private LinkedList<Directory> currentPath;
    private Directory currentDirectory;
    /**
     * Constructs a new FileSystem with a root directory.
     */
    public FileSystem() {
        this.root = new Directory("home", null);
        this.currentPath = new LinkedList<>();
        this.currentPath.add(root); // Add root directory to the current path
        currentDirectory=this.root;
    }
    /**
     * Creates a new file in the specified parent directory.
     *
     * @param fileName         the name of the file to create
     * @param parentDirectory  the parent directory where the file will be created
     */
    public void createFile(String fileName, Directory parentDirectory) {
        // Check if the file already exists in the parent directory
        if (parentDirectory.getChildFile(fileName) != null) {
            System.out.println("File already exists: " + fileName);
            return;
        }
    
        // Create a new file and add it to the parent directory
        File newFile = new File(fileName, parentDirectory);
        parentDirectory.addElement(newFile);
    }
    /**
     * Creates a new directory in the specified parent directory.
     *
     * @param name    the name of the directory to create
     * @param parent  the parent directory where the directory will be created
     */
    public void createDirectory(String name, Directory parent) {
        // Check if the directory already exists in the parent directory
        if (parent.getChildDirectory(name) != null) {
            System.out.println("Directory already exists: " + name);
            return;
        }
    
        // Create a new directory and add it to the parent directory
        Directory newDirectory = new Directory(name, parent);
        parent.addElement(newDirectory);
    }
    /**
     * Deletes the specified file from the parent directory.
     *
     * @param name    the name of the file to delete
     * @param parent  the parent directory from which the file will be deleted
     */
    public void deleteFile(String name, Directory parent) {
        // Find the file with the given name in the parent directory's children
        FileSystemElement target = parent.getChildFile(name);
    
        // If the file is found, remove it from the parent directory
        if (target instanceof File) {
            parent.removeElement(target);
        } else {
            System.out.println("File not found: " + name);
        }
    }
    /**
     * Deletes the specified directory from the file system.
     *
     * @param name  the name of the directory to delete
     */
    public void deleteDirectory(String name) {
        // Start searching from the root directory
        Directory directoryToDelete = root;
    
        // Iterate through the currentPath linked list
        for (Directory dir : currentPath) {
            // Check if the current directory contains the directory to delete
            Directory foundDir = dir.getChildDirectory(name);
            if (foundDir != null) {
                // If found, set the directoryToDelete to the found directory
                directoryToDelete = foundDir;
                break; // Stop searching further
            }
        }
    
        // Check if the directory to delete is the root directory
        if (directoryToDelete.equals(root)) {
            System.out.println("Cannot delete the root directory.");
            return;
        }
    
        // Find the parent directory of the directory to delete
        Directory parentDir = (Directory) directoryToDelete.getParent();
        if (parentDir != null) {
            // Remove the directory from its parent's children list
            parentDir.removeElement(directoryToDelete);
            System.out.println("Directory deleted: " + directoryToDelete.getName());
        } else {
            System.out.println("Directory not found: " + name);
        }
    }
    /**
     * Moves the specified file or directory to a new parent directory.
     *
     * @param name       the name of the file or directory to move
     * @param newParent  the new parent directory where the file or directory will be moved
     */
    public void moveElement(String name, Directory newParent) {
        // Search for the element to move within the current directory
        FileSystemElement elementToMove = findElementByName(name, root);
        
        if (elementToMove != null) {
            // Remove the element from its current parent's children
            Directory currentParent = (Directory) elementToMove.getParent();
            currentParent.removeElement(elementToMove);
            
            // Update the parent of the element to the new directory
            elementToMove.setParent(newParent);
            
            // Add the element to the children of the new parent directory
            newParent.addElement(elementToMove);
            
            System.out.println("Element moved: " + name);
        } else {
            System.out.println("Element not found: " + name);
        }
    }
    /**
     * Searches for a file or directory with the specified name.
     *
     * @param name  the name of the file or directory to search for
     * @return true if the file or directory is found, false otherwise
     */
    public boolean search(String name) {
        return searchRecursive(name, root, "");
    }
     /**
     * Prints the directory tree starting from the root directory.
     */
    public void printDirectoryTree() {
        // Print the entire directory tree starting from the root
        printDirectoryTreeRecursive(root, 0);
    }
    /**
     * Lists the contents of the specified directory.
     *
     * @param dir  the directory whose contents will be listed
     */
    public void listContents(Directory dir) {
        // List all contents of the specified directory
        int flag=0;
        System.out.println("Listing contents of " + dir.getName() + ":");
        for (FileSystemElement element : dir.getChildren()) {
            if (element instanceof Directory) {
                System.out.println("* " + element.getName() + "/");
            } else {
                System.out.println(element.getName());
            }
            flag=1;
        }
        if(flag==0){
            System.out.println("none");
        }
    }
    /**
     * Sorts the contents of the specified directory by creation date.
     *
     * @param dir  the directory whose contents will be sorted
     */
    public void sortDirectoryByDate(Directory dir) {
        // Sort the contents of a directory by creation date
        Collections.sort(dir.getChildren(), new Comparator<FileSystemElement>() {
            @Override
            public int compare(FileSystemElement o1, FileSystemElement o2) {
                // Convert date strings to Date objects for comparison
                Date date1 = o1.getDateCreated();
                Date date2 = o2.getDateCreated();
                
                // Compare the dates
                return date1.compareTo(date2);
            }
        });
    
        // Get the full path of the directory
        String directoryPath = getCurrentPath(dir);
    
        // Print sorted contents with directory names annotated with creation dates
        System.out.println("Sorted contents of " + directoryPath + " by date created:");
        for (FileSystemElement element : dir.getChildren()) {
            if (element instanceof Directory) {
                // Format the date of the directory
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = dateFormat.format(element.getDateCreated());
                System.out.println("* " + element.getName() + "/ (" + formattedDate + ")");
            } else {
                // Format the date of the file
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = dateFormat.format(element.getDateCreated());
                System.out.println(element.getName() + " (" + formattedDate + ")");
            }
        }
    }
    /**
     * Retrieves the current path of the specified directory.
     *
     * @param dir the directory for which to retrieve the current path
     * @return the current path of the directory
     */
    public String getCurrentPath(Directory dir) {
        // If the given directory is null, return an empty string
        if (dir == null) {
            return "";
        }
        // Create a StringBuilder to build the path
        StringBuilder pathBuilder = new StringBuilder();

        // Build the path from the root directory
        buildPathFromRoot(dir, pathBuilder);

        // Return the constructed path
        return pathBuilder.toString();
    }
    /**
     * Retrieves the current directory.
     *
     * @return the current directory
     */
    public Directory getCurrentDirectory() {
        // Get the current directory from the end of the linked list
        return currentPath.getLast();
    }
    /**
     * Changes the current directory to the specified path.
     *
     * @param path the path of the directory to change to
     * @return the new current directory
     */
    public Directory changeDirectory(String path) {
        // Change the current directory to the one specified by path
        String[] pathComponents = path.split("/");
        Directory currentDir = getCurrentDirectory(); // Get the current directory from the linked list

        // Iterate through the path components
        for (String component : pathComponents) {
            // Skip empty components and the root directory
            if (component.isEmpty() || component.equals("root")) {
                continue;
            }

            // Handle special case: navigate back to the root directory
            if (component.equals("home")) {
                currentPath.clear();
                currentPath.add(root);
                currentDir = root;
                continue;
            }

            // Get the child directory corresponding to the current component
            Directory childDir = currentDir.getChildDirectory(component);

            // If the child directory doesn't exist, print an error message and return null
            if (childDir == null) {
                System.out.println("Directory not found: " + component);
                return currentDir; // Return the current directory without changing it
            }

            // Update the current directory to the child directory
            currentPath.add(childDir);
            currentDir = childDir;
        }
        this.currentDirectory=currentDir;
        return currentDir;
    }
    /**
 * Retrieves the root directory of the file system.
 *
 * @return the root directory
 */
    public Directory getRoot(){
        return root;
    }

    /**
     * Recursively searches for a file or directory with the specified name within the current directory and its subdirectories.
     *
     * @param name the name of the file or directory to search for
     * @param currentDirectory the current directory to start the search from
     * @param path the path of the current directory (used for constructing the full path of found elements)
     * @return true if the file or directory is found, false otherwise
     */
    private boolean searchRecursive(String name, Directory currentDirectory, String path) {
        // Construct the full path of the current directory/file
        String currentPath = path.isEmpty() ? currentDirectory.getName() : path + "/" + currentDirectory.getName();
        
        // Check if the current directory/file name matches the query
        if (currentDirectory.getName().equals(name)) {
            System.out.println("Search query: " + name);
            System.out.println("Searching from root...");
            System.out.println("Found: " + currentPath);
            return true;
        }
        
        // Check if any of the files in the current directory match the query
        for (FileSystemElement element : currentDirectory.getChildren()) {
            if (element instanceof File && element.getName().equals(name)) {
                System.out.println("Search query: " + name);
                System.out.println("Searching from root...");
                System.out.println("Found: " + currentPath + "/" + element.getName());
                return true;
            }
        }
    
        // Iterate through the children of the current directory
        for (FileSystemElement element : currentDirectory.getChildren()) {
            if (element instanceof Directory) {
                // If the child is a directory, recursively search its contents
                if (searchRecursive(name, (Directory) element, currentPath)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Recursively prints the directory tree starting from the specified directory.
     *
     * @param directory the directory to start printing from
     * @param level the indentation level for visual representation
     */
    private void printDirectoryTreeRecursive(Directory directory, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  "); // Indentation for visual representation
        }
        System.out.print("* " + directory.getName() + "/");
        if (directory.equals(currentDirectory)) {
            System.out.println(" (Current Directory)");
        } else {
            System.out.println();
        }
        for (FileSystemElement element : directory.getChildren()) {
            if (element instanceof Directory) {
                printDirectoryTreeRecursive((Directory) element, level + 1);
            } else {
                for (int i = 0; i < level + 1; i++) {
                    System.out.print("  "); // Indentation for visual representation
                }
                System.out.println(element.getName());
            }
        }
    }
    
    /**
     * Recursively builds the path from the root directory to the specified directory.
     *
     * @param dir the directory for which to build the path
     * @param pathBuilder the StringBuilder to build the path
     */
    private void buildPathFromRoot(Directory dir, StringBuilder pathBuilder) {
        if (dir == null) {
            return;  // Exit early if the directory is null
        }
        // Recursively build the path from the root
        if (dir.getParent() != null) {
            Directory parentDir = (Directory) dir.getParent();
            buildPathFromRoot(parentDir, pathBuilder);
            pathBuilder.append("/");  // Append the separator before the directory name
        }
        pathBuilder.append(dir.getName());  // Append the current directory's name
    }
    /**
     * Finds a file system element (file or directory) by its name within the specified directory.
     *
     * @param name the name of the element to find
     * @param directory the directory to search within
     * @return the file system element if found, null otherwise
     */
    private FileSystemElement findElementByName(String name, Directory directory) {
        // Search for the element recursively within the directory and its subdirectories
        for (FileSystemElement element : directory.getChildren()) {
            if (element.getName().equals(name)) {
                return element;
            }
            if (element instanceof Directory) {
                FileSystemElement foundElement = findElementByName(name, (Directory) element);
                if (foundElement != null) {
                    return foundElement;
                }
            }
        }
        return null;
    }
}