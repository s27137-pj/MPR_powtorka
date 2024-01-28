package przykladowe_kolo_2.przykladowe_kolo_2.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import przykladowe_kolo_2.przykladowe_kolo_2.exception.IndexException;
import przykladowe_kolo_2.przykladowe_kolo_2.model.BankClient;
import przykladowe_kolo_2.przykladowe_kolo_2.repository.BankClientRepository;

import static org.junit.jupiter.api.Assertions.*;
//dffretter
class BankClientServiceTest {
    private static BankClientService bankClientService;

    private static BankClientRepository bankClientRepository;

    @BeforeAll
    public static void setup(){
        bankClientRepository = new BankClientRepository();

        bankClientService = new BankClientService(bankClientRepository);

    }

    @Test

    void shoudlSaveNewClient(){
        BankClient bankClient = new BankClient(123, 1000);

        BankClient result = assertDoesNotThrow(() -> bankClientService.save(bankClient));

        assertEquals(result.getIndex(), bankClient.getIndex());
        assertEquals(result.getSaldo(), bankClient.getSaldo());


    }

    @Test

    void shouldMakeOverflow(){
        BankClient bankClient = new BankClient(123, 1000);

        IndexException indexException = assertThrows(IndexException.class, () -> bankClientService.przelew(bankClient, 1100, 123));

        assertEquals(indexException.getMessage(), "Nie masz siana na taki przelew");


    }

    @Test

    void shouldCheckClient(){
//        given
        BankClient bankClient = new BankClient(123, 1000);

        IndexException indexException = assertThrows(IndexException.class, () -> bankClientRepository.check(123, 1000 ));

        assertEquals(indexException.getMessage(), "Nie ma takiego clienta");

    }

}