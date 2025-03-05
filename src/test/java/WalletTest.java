import static org.junit.jupiter.api.Assertions.*;

import org.example.Wallet;
import org.junit.jupiter.api.*;

class WalletTest {

    private static Wallet wallet;

    @BeforeAll
    static void classSetup() {
        System.out.println("Initializing WalletTest...");
    }

    @AfterAll
    static void classCleanUp() {
        System.out.println("Cleaning up WalletTest...");
    }

    @BeforeEach
    void methodSetup() {
        wallet = new Wallet();
    }

    @AfterEach
    void methodCleanup() {
        System.out.println("Total Cash after test: " + wallet.getTotalCash());
        wallet = null;
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
        assertNull(wallet.getCard(1));
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
        assertFalse(wallet.withdrawCashNotes(6000));
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
        assertFalse(wallet.withdrawCoins(1500));
        assertEquals(1000, wallet.getTotalCash());
    }

    @Test
    void testWithdrawCashNotesExactAmount() {
        wallet.addCashNotes(5000);
        assertTrue(wallet.withdrawCashNotes(5000));
        assertEquals(0, wallet.getTotalCash());
    }

    @Test
    void testWithdrawCoinsExactAmount() {
        wallet.addCoins(300);
        assertTrue(wallet.withdrawCoins(300));
        assertEquals(0, wallet.getTotalCash());
    }

    @Test
    void testAddNegativeCashNotes() {
        wallet.addCashNotes(-1000);
        assertEquals(0, wallet.getTotalCash());
    }

    @Test
    void testAddNegativeCoins() {
        wallet.addCoins(-500);
        assertEquals(0, wallet.getTotalCash());
    }

    @Test
    void testWithdrawNegativeCashNotes() {
        wallet.addCashNotes(5000);
        assertFalse(wallet.withdrawCashNotes(-2000));
        assertEquals(5000, wallet.getTotalCash());
    }

    @Test
    void testWithdrawNegativeCoins() {
        wallet.addCoins(500);
        assertFalse(wallet.withdrawCoins(-100));
        assertEquals(500, wallet.getTotalCash());
    }

    @Test
    void testAddAndWithdrawMultipleOperations() {
        wallet.addCashNotes(20000);
        wallet.addCoins(1500);
        wallet.withdrawCashNotes(5000);
        wallet.withdrawCoins(500);
        assertEquals(16000, wallet.getTotalCash());
    }

    @Test
    void testGetOwnerInitial() {
        assertNull(wallet.getOwner());
    }

    @Test
    void testGetEmptyCards() {
        assertTrue(wallet.getCards().isEmpty());
    }

    @Test
    void testAddEmptyCard() {
        wallet.addCard("");
        assertEquals(1, wallet.getCards().size());
        assertEquals("", wallet.getCard(0));
    }

    @Test
    void testMultipleCardAddition() {
        wallet.addCard("Visa");
        wallet.addCard("MasterCard");
        wallet.addCard("Amex");
        assertEquals(3, wallet.getCards().size());
        assertEquals("Amex", wallet.getCard(2));
    }

    @Test
    void testWithdrawMoreThanAvailableCash() {
        wallet.addCashNotes(10000);
        wallet.addCoins(1000);
        assertFalse(wallet.withdrawCashNotes(20000));
        assertFalse(wallet.withdrawCoins(5000));
        assertEquals(11000, wallet.getTotalCash());
    }
}
