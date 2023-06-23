import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

class ParkingSpot {
    String nomorSpot;
    String namaCustomer;
    String platNomor;
    boolean isBooked;

    public ParkingSpot(String nomorSpot) {
        this.nomorSpot = nomorSpot;
        this.namaCustomer = "";
        this.platNomor = "";
        this.isBooked = false;
    }
}

class ParkingSpotList {
    ParkingSpot[] spots;
    int size;
    int capacity;

    public ParkingSpotList(int capacity) {
        spots = new ParkingSpot[capacity];
        size = 0;
        this.capacity = capacity;
    }

    public void addSpot(String nomorSpot, String namaCustomer, String platNomor) {
        if (size >= capacity) {
            System.out.println("Tempat parkir tidak tersedia.");
            return;
        }
        ParkingSpot newSpot = new ParkingSpot(nomorSpot);
        newSpot.namaCustomer = namaCustomer;
        newSpot.platNomor = platNomor;
        spots[size] = newSpot;
        size++;
        System.out.println("Pemesanan tempat parkir telah ditambahkan.");
    }

    public void displayStatus() {
        if (size == 0) {
            System.out.println("Tidak ada tempat parkir yang tersedia.");
        } else {
            System.out.println("Tempat Parkir yang disediakan:");
            System.out.print("Sudah Dipesan: ");
            boolean unBooked = false;
            for (int i = 0; i < capacity; i++) {
                String nomorSpot = String.valueOf(i + 1);
                boolean isBooked = false;
                for (int j = 0; j < size; j++) {
                    if (spots[j].nomorSpot.equals(nomorSpot) && spots[j].isBooked) {
                        isBooked = true;
                        break;
                    }
                }
                if (isBooked) {
                    System.out.print(nomorSpot + " ");
                } else {
                    unBooked = true;
                }
            }
            if (!unBooked) {
                System.out.print("Tidak tersedia");
            }
            System.out.println();

            System.out.print("Belum Dipesan: ");
            boolean booked = false;
            for (int i = 0; i < capacity; i++) {
                String nomorSpot = String.valueOf(i + 1);
                boolean isBooked = false;
                for (int j = 0; j < size; j++) {
                    if (spots[j].nomorSpot.equals(nomorSpot) && spots[j].isBooked) {
                        isBooked = true;
                        break;
                    }
                }
                if (!isBooked) {
                    System.out.print(nomorSpot + " ");
                    booked = true;
                }
            }
            if (!booked) {
                System.out.print("Tidak tersedia");
            }
            System.out.println();
        }
    }

    public void sortSpots() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (spots[j].nomorSpot.compareTo(spots[j + 1].nomorSpot) > 0) {
                    ParkingSpot temp = spots[j];
                    spots[j] = spots[j + 1];
                    spots[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            spots[i].isBooked = true;
        }
    }

    public int searchSpot(String nomorSpot, int index) {
        if (index >= 0 && index < size) {
            if (spots[index].nomorSpot.equals(nomorSpot)) {
                return index;
            } else {
                return searchSpot(nomorSpot, index + 1);
            }
        }
        return -1;
    }
}

public class PesanTempatParkir {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer[] capacity = new Integer[5];
        for (int i = 0; i < capacity.length; i++) {
            capacity[i] = i + 1;
        }

        System.out.println("Nomor tempat parkir yang disediakan :");
        for (int i = 0; i < capacity.length; i++) {
            System.out.print(capacity[i] + " ");
        }
        System.out.println();

        ParkingSpotList spotList = new ParkingSpotList(capacity.length);
        while (true) {
            System.out.println("========== Pemesanan Tempat Parkir Online ==========");
            System.out.println("1. Tambah pemesanan tempat parkir");
            System.out.println("2. Tampilkan status pemesanan tempat parkir");
            System.out.println("3. Cari tempat parkir yang telah dipesan");
            System.out.println("0. Keluar");
            System.out.print("Masukkan pilihan: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Masukkan nomor tempat parkir: ");
                    String nomorSpot = scanner.next();
                    System.out.print("Masukkan nama customer: ");
                    String namaCustomer = scanner.next();
                    System.out.print("Masukkan plat nomor: ");
                    String platNomor = scanner.next();
                    spotList.addSpot(nomorSpot, namaCustomer, platNomor);
                    break;
                case 2:
                    spotList.sortSpots();
                    spotList.displayStatus();
                    break;
                case 3:
                    System.out.print("Masukkan nomor tempat parkir pemesanan yang ingin dicari: ");
                    String searchSpotNumber = scanner.next();
                    int index = spotList.searchSpot(searchSpotNumber, 0);
                    if (index != -1) {
                        System.out.println("Tempat parkir ditemukan pada indeks " + index);
                        System.out.println("Nama Pemesan: " + spotList.spots[index].namaCustomer);
                        System.out.println("Nomor Plat Pemesan: " + spotList.spots[index].platNomor);
                    } else {
                        System.out.println("Tempat parkir tidak ditemukan.");
                    }
                    break;
                case 0:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }

            System.out.println();
        }
    }
}
