import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Date;

class FileSystem {
    private Directory root;
    private LinkedList<Directory> currentPath;
    private Directory currentDirectory;

    public FileSystem() {
        this.root = new Directory("home", null);
        this.currentPath = new LinkedList<>();
        this.currentPath.add(root); // Add root directory to the current path
        currentDirectory=this.root;
    }

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
    
    public void deleteDirectory(String name, Directory parent) {
        Directory directoryToDelete = parent.getChildDirectory(name);
        System.out.println("Directory to delete: " + name);
        if (directoryToDelete != null) {
            directoryToDelete.deleteRecursive();
            parent.removeElement(directoryToDelete);
        } else {
            System.out.println("Directory not found: " + name);
        }
    }

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
    
    public boolean search(String name) {
        return searchRecursive(name, root, "");
    }
    
    public void printDirectoryTree() {
        // Print the entire directory tree starting from the root
        printDirectoryTreeRecursive(root, 0);
    }
    
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

    /*public void sortDirectoryByDate(Directory dir) {
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

        // Print sorted contents with directory names annotated with creation dates
        System.out.println("Sorted contents of " + dir.getName() + " by date created:");
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
    }*/
    
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

    public Directory getCurrentDirectory() {
        // Get the current directory from the end of the linked list
        return currentPath.getLast();
    }
    
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

    public Directory getRoot(){
        return root;
    }

    //helper of search
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
    
    
    
    //helper of printDirectoryTree
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
    

    //helper of getCurrentPath
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