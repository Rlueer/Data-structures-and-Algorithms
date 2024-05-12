import java.util.Scanner;
public class Main {

    private static FileSystem fs = new FileSystem();

    private static Scanner scanner = new Scanner(System.in);

    private static Directory currentDirectory;
    /**
     * Main method to run the file system management menu.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        currentDirectory = fs.getRoot(); // assume there is a getroot method
        while (true) {
            System.out.println("*******************");
            System.out.println("Current directory:"+ fs.getCurrentPath(currentDirectory));
            System.out.println("===== File System Management Menu =====");
            System.out.println("1. Change directory");
            System.out.println("2. List directory contents");
            System.out.println("3. Create file");
            System.out.println("4. Create directory");
            System.out.println("5. Delete file");
            System.out.println("6. Delete directory");
            System.out.println("7. Move file/directory");
            System.out.println("8. Search file/directory");
            System.out.println("9. Print directory tree");
            System.out.println("10. Sort contents by date");
            System.out.println("11. Exit");
            System.out.println("*******************");
            System.out.print("Please select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    changeDirectory();
                    break;
                case 2:
                    listContents();
                    break;
                case 3:
                    createFile();
                    break;
                case 4:
                    createDirectory();
                    break;
                case 5:
                    deleteFile();
                    break;
                case 6:
                    deleteDirectory();
                   break;
                case 7:
                    moveElement();
                   break;
                case 8:
                    search();
                    break;
                case 9:
                    printDirectoryTree();
                    break;
                case 10:
                    sortDirectoryByDate();
                    break;
                case 11:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option!.Please try again.");
            }
        }
    }
        private static void changeDirectory(){
            System.out.println("Current directory: "+ fs.getCurrentPath(currentDirectory));
            System.out.print("Enter new directory path: ");
            String newPath = scanner.nextLine();
            currentDirectory = fs.changeDirectory(newPath);
            if (currentDirectory == null) {
                System.out.println("Directory not found!");
            } else {
                System.out.println("Directory changed to: " + fs.getCurrentPath(currentDirectory));
            }
        }
        
        private static void listContents(){
            fs.listContents(currentDirectory);
        }

        private static void createFile(){
            System.out.println("Enter file name to create: ");
            String name = scanner.nextLine();
            fs.createFile(name, currentDirectory);
            System.out.println("File created: "+ name);
        }
        private static void createDirectory(){
            System.out.println("Enter directory name to create: ");
            String name = scanner.nextLine();
            fs.createDirectory(name, currentDirectory);
            System.out.println("Directory created: "+ name);
        }
        private static void deleteFile(){
            System.out.println("Enter file name to delete: ");
            String name = scanner.nextLine();
            fs.deleteFile(name, currentDirectory);
            System.out.println("File deleted: "+ name);
        }
        private static void deleteDirectory(){
            System.out.println("Enter directory name to delete: ");
            String name = scanner.nextLine();
            fs.deleteDirectory(name);
            System.out.println("Directory deleted: "+ name);
        }

        private static void moveElement() {
            System.out.print("Enter the name of file/directory to move: ");
            String name = scanner.nextLine();
            System.out.print("Enter the new parent directory path: ");
            String newParentPath = scanner.nextLine();
        
            Directory newParentDirectory = fs.changeDirectory(newParentPath);
            if (newParentDirectory != null) {
                fs.moveElement(name, newParentDirectory);
            } else {
                System.out.println("Directory not found: " + newParentPath);
            }
        }
        
        private static void search(){
            System.out.print("Enter search query: ");
            String query = scanner.nextLine();
            fs.search(query);
        }

        private static void printDirectoryTree(){
            fs.printDirectoryTree();
        }

        private static void sortDirectoryByDate(){
            fs.sortDirectoryByDate(currentDirectory);
        }
        
}

