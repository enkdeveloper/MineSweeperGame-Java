import java.util.Scanner;

public class MineSweeper {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Mayın Tarlası Oyununa Hoşgeldiniz!");

        System.out.print("Satır sayısını giriniz: ");
        int satirSayisi = input.nextInt();

        System.out.print("Sütun sayısını giriniz: ");
        int sutunSayisi = input.nextInt();

        int elemanSayisi = satirSayisi * sutunSayisi;
        int[][] mayinTarlasi = new int[satirSayisi][sutunSayisi];
        int[][] mayinKonumlari = new int[satirSayisi][sutunSayisi];

        int mayinSayisi = elemanSayisi / 4;
        for (int i = 0; i < mayinSayisi; i++) {
            int randomSatir = (int) (Math.random() * satirSayisi);
            int randomSutun = (int) (Math.random() * sutunSayisi);

            while (mayinKonumlari[randomSatir][randomSutun] == 1) {
                randomSatir = (int) (Math.random() * satirSayisi);
                randomSutun = (int) (Math.random() * sutunSayisi);
            }

            mayinKonumlari[randomSatir][randomSutun] = 1;
        }

        boolean oyunDevam = true;
        while (oyunDevam) {

            oyunTahtasiniGoster(mayinTarlasi);

            System.out.print("Satır Giriniz: ");
            int satir = input.nextInt();

            System.out.print("Sütun Giriniz: ");
            int sutun = input.nextInt();

            if (satir < 0 || satir >= satirSayisi || sutun < 0 || sutun >= sutunSayisi) {
                System.out.println("Geçersiz giriş. Lütfen tekrar deneyin.");
                continue;
            }

            if (mayinKonumlari[satir][sutun] == 1) {
                oyunDevam = false;
                System.out.println("Oyun Bitti, Kaybettiniz!");
            } else {
                int etraftakiMayinSayisi = etraftakiMayinSayisi(mayinKonumlari, satir, sutun);
                mayinTarlasi[satir][sutun] = etraftakiMayinSayisi;

                if (oyunuKazandiMi(mayinTarlasi, mayinKonumlari)) {
                    oyunDevam = false;
                    System.out.println("Oyunu Kazandınız!");
                }
            }
        }

        input.close();
    }

    private static void oyunTahtasiniGoster(int[][] tahta) {
        for (int i = 0; i < tahta.length; i++) {
            for (int j = 0; j < tahta[0].length; j++) {
                if (tahta[i][j] == 0) {
                    System.out.print("- ");
                } else {
                    System.out.print(tahta[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("===========================");
    }

    private static int etraftakiMayinSayisi(int[][] mayinKonumlari, int satir, int sutun) {
        int mayinSayisi = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int yeniSatir = satir + i;
                int yeniSutun = sutun + j;

                if (yeniSatir >= 0 && yeniSatir < mayinKonumlari.length && yeniSutun >= 0 && yeniSutun < mayinKonumlari[0].length) {
                    mayinSayisi += mayinKonumlari[yeniSatir][yeniSutun];
                }
            }
        }

        return mayinSayisi;
    }

    private static boolean oyunuKazandiMi(int[][] tahta, int[][] mayinKonumlari) {
        for (int i = 0; i < tahta.length; i++) {
            for (int j = 0; j < tahta[0].length; j++) {
                if (tahta[i][j] == 0 && mayinKonumlari[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
