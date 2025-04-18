import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BankActionTest {
	
	
	/**
	 * Test per il metodo deposita della classe Banca
	 */
	@Test
	public void testDepositaAction() {
		
		double saldo=100;
		double portafoglio=1000;
		
		boolean result=Banca.deposita(portafoglio, saldo);
		
		assertEquals(false,result,"");
	}
	
	/**
	 * Test per il metodo preleva della classe Banca
	 */
	
	@Test
	public void testPrelevaAction() {
		
		double saldo=100;
		double conto=1000;
		
		boolean result=Banca.preleva(conto, saldo);
		
		assertEquals(false,result,"");
	}
	
	/**
	 * Test per il metodo investimento della classe Banca
	 */
		
	@Test
	public void testInvestimentoAction() {
		
		int grandezzaRischio=100;
		double soldiInvestiti=10;
		
		double guadagno = Banca.investimento(grandezzaRischio, soldiInvestiti);
		boolean result = (guadagno==50) || (guadagno==2);
		
		assertEquals(true,result,"");
		
	}

}


