package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE);
	}

	@Test

		// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test

	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);

		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");
	}

	@Test
	void correctlyPrintTicketNotEnough() {
		machine.insertMoney(PRICE - 1);
		assertFalse(machine.printTicket(), "ticket imprimé mais pas asser d'argent");
	}

	@Test
	void correctlyPrintTicketEnough() {
		machine.insertMoney(PRICE);
		assertTrue(machine.printTicket(), "Le ticket n'est pas imprimé alors que la balance est suffisante");
	}

	@Test
	void balanceIsCorrectlyUpdated() {
		machine.insertMoney(PRICE + 1);
		machine.printTicket();
		assertEquals(1, machine.getBalance(), "La balance n'est pas update correctement");
	}

	@Test
	void balanceCorrectlyUpdated() {
		machine.insertMoney(10);
		assertEquals(10, machine.getBalance(), "La balance a changé quand il ne fallait pas");
		machine.insertMoney(PRICE);
		assertEquals(10 + PRICE, machine.getBalance(), "La balance a changé quand il ne fallait pas");
		machine.printTicket();
		assertEquals(10, machine.getBalance(), "Balance change de facon pas normal");
	}

	@Test
	void refundCorrectAmount() {
		machine.insertMoney(30);
		assertEquals(30, machine.refund(), "Refund ne donne pas le bon montant");
	}

	@Test
	void refundPutBackToZero() {
		machine.insertMoney(30);
		machine.refund();
		assertEquals(0, machine.getBalance(), "Balance pas remis a 0 correctement");
	}
	//9 S9 : on ne peut pas insérer un montant négatif
	@Test
	void noInsertNegativeNumber(){
		try{
			machine.insertMoney(-1);
			fail("Cant accept negative numbers");
		}catch(IllegalArgumentException e){

		}
	}
	//S10 on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
	@Test
	void cantCreateNegativePriceMachine(){
		try{
			TicketMachine negMachine = new TicketMachine(-1);
			fail("Cant create negative price machine");
		}catch(IllegalArgumentException e){

		}
	}
}