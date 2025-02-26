import static org.junit.jupiter.api.Assertions.*;

import org.example.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WalletTest {

    private Wallet wallet;

    @BeforeEach
    void setUp() {
        wallet = new Wallet();
    }

    @Test
    void testSetOwner() {
        wallet.setOwner("Reza Luthfi");
        assertEquals("Reza Luthfi", wallet.getOwner());
    }

    @Test
    void testAddCard() {
        wallet.addCard("Visa");
        wallet.addCard("MasterCard");
        assertEquals(2, wallet.getCards().size());
        assertEquals("Visa", wallet.getCard(0));
        assertEquals("MasterCard", wallet.getCard(1));
    }

    @Test
    void testGetCardInvalidIndex() {
        wallet.addCard("Visa");
        assertNull(wallet.getCard(1)); //Mengambil kartu dari indeks yang tidak ada
    }

    @Test
    void testAddCashNotes() {
        wallet.addCashNotes(10000);
        assertEquals(10000, wallet.getTotalCash());
    }

    @Test
    void testAddCoins() {
        wallet.addCoins(500);
        assertEquals(500, wallet.getTotalCash());
    }

    @Test
    void testWithdrawCashNotes() {
        wallet.addCashNotes(10000);
        assertTrue(wallet.withdrawCashNotes(5000));
        assertEquals(5000, wallet.getTotalCash());
    }

    @Test
    void testWithdrawCashNotesInsufficientFunds() {
        wallet.addCashNotes(5000);
        assertFalse(wallet.withdrawCashNotes(6000)); //Jumlah tidak cukup
        assertEquals(5000, wallet.getTotalCash());
    }

    @Test
    void testWithdrawCoins() {
        wallet.addCoins(1000);
        assertTrue(wallet.withdrawCoins(500));
        assertEquals(500, wallet.getTotalCash());
    }

    @Test
    void testWithdrawCoinsInsufficientFunds() {
        wallet.addCoins(1000);
        assertFalse(wallet.withdrawCoins(1500)); //Jumlah tidak cukup
        assertEquals(1000, wallet.getTotalCash());
    }

    @Test
    void testWithdrawCashNotesExactAmount() {
        wallet.addCashNotes(5000);
        assertTrue(wallet.withdrawCashNotes(5000)); //Menarik jumlah tepat
        assertEquals(0, wallet.getTotalCash()); //Pastikan saldo jadi 0
    }

    @Test
    void testWithdrawCoinsExactAmount() {
        wallet.addCoins(300);
        assertTrue(wallet.withdrawCoins(300)); //Menarik jumlah tepat
        assertEquals(0, wallet.getTotalCash()); //Pastikan saldo jadi 0
    }

    @Test
    void testAddNegativeCashNotes() {
        wallet.addCashNotes(-1000);  //Uang negatif, seharusnya tidak boleh
        assertEquals(0, wallet.getTotalCash()); //Pastikan jumlah tetap 0
    }

    @Test
    void testAddNegativeCoins() {
        wallet.addCoins(-500);   //Koin negatif, seharusnya tidak boleh
        assertEquals(0, wallet.getTotalCash()); //Pastikan jumlah tetap 0
    }

    @Test
    void testWithdrawNegativeCashNotes() {
        wallet.addCashNotes(5000);
        assertFalse(wallet.withdrawCashNotes(-2000)); //Penarikan negatif tidak boleh
        assertEquals(5000, wallet.getTotalCash()); //Pastikan saldo tetap tidak berubah
    }

    @Test
    void testWithdrawNegativeCoins() {
        wallet.addCoins(500);
        assertFalse(wallet.withdrawCoins(-100)); //Penarikan negatif tidak boleh
        assertEquals(500, wallet.getTotalCash());  //Pastikan saldo tetap tidak berubah
    }

    @Test
    void testAddAndWithdrawMultipleOperations() {
        wallet.addCashNotes(20000);
        wallet.addCoins(1500);
        wallet.withdrawCashNotes(5000);
        wallet.withdrawCoins(500);

        assertEquals(16000, wallet.getTotalCash()); // Cash yang tersisa setelah beberapa operasi
    }

    @Test
    void testGetOwnerInitial() {
        assertNull(wallet.getOwner()); //Pastikan owner awalnya null
    }

    @Test
    void testGetEmptyCards() {
        assertTrue(wallet.getCards().isEmpty()); //Pastikan daftar kartu kosong di awal
    }

    @Test
    void testAddEmptyCard() {
        wallet.addCard("");  //Tambah kartu kosong
        assertEquals(1, wallet.getCards().size()); //Pastikan tetap tersimpan
        assertEquals("", wallet.getCard(0));  //Kartu kosong ada di indeks pertama
    }

    @Test
    void testMultipleCardAddition() {
        wallet.addCard("Visa");
        wallet.addCard("MasterCard");
        wallet.addCard("Amex");

        assertEquals(3, wallet.getCards().size()); //Pastikan tiga kartu berhasil ditambahkan
        assertEquals("Amex", wallet.getCard(2));  //Cek kartu terakhir yang ditambahkan
    }

    @Test
    void testWithdrawMoreThanAvailableCash() {
        wallet.addCashNotes(10000);
        wallet.addCoins(1000);
        assertFalse(wallet.withdrawCashNotes(20000)); //Penarikan lebih dari cash notes
        assertFalse(wallet.withdrawCoins(5000));  //Penarikan lebih dari coins

        assertEquals(11000, wallet.getTotalCash()); //Total cash tetap tidak berubah
    }
}
