
// import java.util.*;

// public class Main {
//     private static RedBlackTree rbt = new RedBlackTree();
//     private static Set<String> customizedPlates = new HashSet<>();
//     private static int revenue = 0;

//     // Generate random plate
//     private static String generatePlate() {
//         Random rand = new Random();
//         StringBuilder plate = new StringBuilder();
//         for (int i = 0; i < 4; i++) {
//             if (rand.nextBoolean()) plate.append((char) ('0' + rand.nextInt(10))); // Number
//             else plate.append((char) ('A' + rand.nextInt(26))); // Letter
//         }
//         return plate.toString();
//     }

//     // Add customized plate
//     private static void addLicence(String plateNum) {
//         if (rbt.insert(plateNum)) {
//             customizedPlates.add(plateNum);
//             revenue += 7; // 4 Galleons + 3 for custom
//             System.out.println(plateNum + " registered successfully.");
//         } else {
//             System.out.println("Failed to register " + plateNum + ": already exists.");
//         }
//     }

//     // Add random plate
//     private static void addLicence() {
//         String plateNum;
//         do {
//             plateNum = generatePlate();
//         } while (rbt.search(plateNum));
//         rbt.insert(plateNum);
//         revenue += 4;
//         System.out.println(plateNum + " created and registered successfully.");
//     }

//     // Drop license
//     private static void dropLicence(String plateNum) {
//         if (rbt.search(plateNum)) {
//             rbt.delete(plateNum);
//             if (customizedPlates.contains(plateNum)) {
//                 revenue -= 7;
//                 customizedPlates.remove(plateNum);
//             } else {
//                 revenue -= 4;
//             }
//             System.out.println(plateNum + " removed successfully.");
//         } else {
//             System.out.println("Failed to remove " + plateNum + ": does not exist.");
//         }
//     }

//     // Lookup licence
//     private static void lookupLicence(String plateNum) {
//         System.out.println(plateNum + (rbt.search(plateNum) ? " exists." : " does not exist."));
//     }

//     // Lookup previous
//     private static void lookupPrev(String plateNum) {
//         System.out.println(plateNum + "'s prev is " + rbt.getPrev(plateNum) + ".");
//     }

//     // Lookup next
//     private static void lookupNext(String plateNum) {
//         System.out.println(plateNum + "'s next is " + rbt.getNext(plateNum) + ".");
//     }

//     // Lookup range
//     private static void lookupRange(String lo, String hi) {
//         List<String> result = new ArrayList<>();
//         rbt.rangeSearch(lo, hi, result);
//         System.out.println("Plate numbers between " + lo + " and " + hi + ": " + result);
//     }

//     // Report revenue
//     private static void revenue() {
//         System.out.println("Current annual revenue is " + revenue + " Galleons.");
//     }

//     // Quit
//     private static void quit() {
//         System.out.println("Exiting system...");
//         System.exit(0);
//     }

//     // Main loop
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         while (true) {
//             System.out.print("\nEnter command: ");
//             String command = scanner.nextLine().trim();

//             if (command.equalsIgnoreCase("add")) {
//                 System.out.print("Enter plate number (or press enter for random): ");
//                 String plateNum = scanner.nextLine().trim();
//                 if (plateNum.isEmpty()) {
//                     addLicence(); // Generate random plate
//                 } else {
//                     addLicence(plateNum); // Add custom plate
//                 }
//             } else if (command.equalsIgnoreCase("drop")) {
//                 System.out.print("Enter plate number to remove: ");
//                 String plateNum = scanner.nextLine().trim();
//                 dropLicence(plateNum);
//             } else if (command.equalsIgnoreCase("lookup")) {
//                 System.out.print("Enter plate number to lookup: ");
//                 String plateNum = scanner.nextLine().trim();
//                 lookupLicence(plateNum);
//             } else if (command.equalsIgnoreCase("prev")) {
//                 System.out.print("Enter plate number to find previous: ");
//                 String plateNum = scanner.nextLine().trim();
//                 lookupPrev(plateNum);
//             } else if (command.equalsIgnoreCase("next")) {
//                 System.out.print("Enter plate number to find next: ");
//                 String plateNum = scanner.nextLine().trim();
//                 lookupNext(plateNum);
//             } else if (command.equalsIgnoreCase("range")) {
//                 System.out.print("Enter lower bound: ");
//                 String lo = scanner.nextLine().trim();
//                 System.out.print("Enter upper bound: ");
//                 String hi = scanner.nextLine().trim();
//                 lookupRange(lo, hi);
//             } else if (command.equalsIgnoreCase("revenue")) {
//                 revenue();
//             } else if (command.equalsIgnoreCase("quit")) {
//                 quit();
//             } else {
//                 System.out.println("Invalid command. Available commands: add, drop, lookup, prev, next, range, revenue, quit.");
//             }
//         }
//     }
// }




import java.io.*;
import java.util.*;

public class Main {
    private static RedBlackTree rbt = new RedBlackTree();
    private static Set<String> customizedPlates = new HashSet<>();
    private static int revenue = 0;
    private static PrintWriter outputWriter;

    // Generate random plate
    private static String generatePlate() {
        Random rand = new Random();
        StringBuilder plate = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            if (rand.nextBoolean())
                plate.append((char) ('0' + rand.nextInt(10))); // Number
            else
                plate.append((char) ('A' + rand.nextInt(26))); // Letter
        }
        return plate.toString();
    }

    // Add customized plate
    private static void addLicence(String plateNum) {
        if (rbt.insert(plateNum)) {
            customizedPlates.add(plateNum);
            revenue += 7; // 4 Galleons + 3 for custom
            outputWriter.println(plateNum + " registered successfully.");
        } else {
            outputWriter.println("Failed to register " + plateNum + ": already exists.");
        }
    }

    // Add random plate
    private static void addLicence() {
        String plateNum;
        do {
            plateNum = generatePlate();
        } while (rbt.search(plateNum));
        rbt.insert(plateNum);
        revenue += 4;
        outputWriter.println(plateNum + " created and registered successfully.");
    }

    // Drop license
    private static void dropLicence(String plateNum) {
        if (rbt.search(plateNum)) {
            rbt.delete(plateNum);
            if (customizedPlates.contains(plateNum)) {
                revenue -= 7;
                customizedPlates.remove(plateNum);
            } else {
                revenue -= 4;
            }
            outputWriter.println(plateNum + " removed successfully.");
        } else {
            outputWriter.println("Failed to remove " + plateNum + ": does not exist.");
        }
    }

    // Lookup licence
    private static void lookupLicence(String plateNum) {
        outputWriter.println(plateNum + (rbt.search(plateNum) ? " exists." : " does not exist."));
    }

    // Lookup previous
    private static void lookupPrev(String plateNum) {
        outputWriter.println(plateNum + "'s prev is " + rbt.getPrev(plateNum) + ".");
    }

    // Lookup next
    private static void lookupNext(String plateNum) {
        outputWriter.println(plateNum + "'s next is " + rbt.getNext(plateNum) + ".");
    }

    // Lookup range
    private static void lookupRange(String lo, String hi) {
        List<String> result = new ArrayList<>();
        rbt.rangeSearch(lo, hi, result);
        outputWriter.println("Plate numbers between " + lo + " and " + hi + ": " + String.join(", ", result));
    }

    // Report revenue
    private static void revenue() {
        outputWriter.println("Current annual revenue is " + revenue + " Galleons.");
    }

    // Quit
    private static void quit() {
        // outputWriter.println("Exiting system...");
    }

    // Main method to read from file and write to output
    // public static void main(String[] args) {
    //     if (args.length != 1) {
    //         System.err.println("Usage: java Main <inputFileName>");
    //         return;
    //     }

    //     String inputFileName = args[0];
    //     String outputFileName = inputFileName + "_output.txt";

    //     try (Scanner scanner = new Scanner(new File(inputFileName));
    //          PrintWriter writer = new PrintWriter(new File(outputFileName))) {

    //         outputWriter = writer;

    //         while (scanner.hasNextLine()) {
    //             String command = scanner.nextLine().trim();

    //             if (command.equalsIgnoreCase("add")) {
    //                 if (scanner.hasNextLine()) {
    //                     String plateNum = scanner.nextLine().trim();
    //                     if (plateNum.isEmpty()) {
    //                         addLicence(); // Generate random plate
    //                     } else {
    //                         addLicence(plateNum); // Add custom plate
    //                     }
    //                 } else {
    //                     addLicence(); // If no plate provided, generate one
    //                 }
    //             } else if (command.equalsIgnoreCase("drop")) {
    //                 if (scanner.hasNextLine()) {
    //                     String plateNum = scanner.nextLine().trim();
    //                     dropLicence(plateNum);
    //                 }
    //             } else if (command.equalsIgnoreCase("lookup")) {
    //                 if (scanner.hasNextLine()) {
    //                     String plateNum = scanner.nextLine().trim();
    //                     lookupLicence(plateNum);
    //                 }
    //             } else if (command.equalsIgnoreCase("prev")) {
    //                 if (scanner.hasNextLine()) {
    //                     String plateNum = scanner.nextLine().trim();
    //                     lookupPrev(plateNum);
    //                 }
    //             } else if (command.equalsIgnoreCase("next")) {
    //                 if (scanner.hasNextLine()) {
    //                     String plateNum = scanner.nextLine().trim();
    //                     lookupNext(plateNum);
    //                 }
    //             } else if (command.equalsIgnoreCase("range")) {
    //                 if (scanner.hasNextLine()) {
    //                     String lo = scanner.nextLine().trim();
    //                     if (scanner.hasNextLine()) {
    //                         String hi = scanner.nextLine().trim();
    //                         lookupRange(lo, hi);
    //                     }
    //                 }
    //             } else if (command.equalsIgnoreCase("revenue")) {
    //                 revenue();
    //             } else if (command.equalsIgnoreCase("quit")) {
    //                 quit();
    //                 break;
    //             } else {
    //                 outputWriter.println("Invalid command: " + command);
    //             }
    //         }
    //     } catch (FileNotFoundException e) {
    //         System.err.println("Error: Input file not found.");
    //     }
    // }



    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <inputFileName>");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = inputFileName + "_output.txt";

        try (Scanner scanner = new Scanner(new File(inputFileName));
            //  PrintWriter writer = new PrintWriter(new File(outputFileName)))
            PrintWriter writer = new PrintWriter(new FileOutputStream(outputFileName, true))) {

            outputWriter = writer;

            while (scanner.hasNextLine()) {
                String commandLine = scanner.nextLine().trim();
                if (commandLine.isEmpty()) continue;

                String[] parts = commandLine.split("[(), ]+");
                String command = parts[0];

                switch (command.toLowerCase()) {
                    case "addlicence":
                        if (parts.length == 1) {
                            addLicence(); // Generate random plate
                        } else {
                            addLicence(parts[1]); // Add custom plate
                        }
                        break;
                    case "droplicence":
                        if (parts.length > 1) dropLicence(parts[1]);
                        break;
                    case "lookuplicence":
                        if (parts.length > 1) lookupLicence(parts[1]);
                        break;
                    case "lookupprev":
                        if (parts.length > 1) lookupPrev(parts[1]);
                        break;
                    case "lookupnext":
                        if (parts.length > 1) lookupNext(parts[1]);
                        break;
                    case "lookuprange":
                        if (parts.length > 2) lookupRange(parts[1], parts[2]);
                        break;
                    case "revenue":
                        revenue();
                        break;
                    case "quit":
                        quit();
                        return;
                    default:
                        outputWriter.println("Invalid command: " + command);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: Input file not found.");
        }
    }
}
