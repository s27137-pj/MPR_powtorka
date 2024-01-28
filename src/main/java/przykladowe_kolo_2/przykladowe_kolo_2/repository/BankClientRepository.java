package przykladowe_kolo_2.przykladowe_kolo_2.repository;

import org.springframework.stereotype.Repository;
import przykladowe_kolo_2.przykladowe_kolo_2.exception.IndexException;
import przykladowe_kolo_2.przykladowe_kolo_2.model.BankClient;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BankClientRepository {

    private List<BankClient> bankClientList = new ArrayList<>();


    public BankClient save(BankClient bankClient){
        bankClient.setIndex(bankClientList.size());

        bankClientList.add(bankClient);

        return bankClient;

    }

    public  void check(int szukanyClientIndex){
//        boolean result = bankClientList.contains(bankClient);

        boolean result = bankClientList.stream()
                .anyMatch(bankClient1 -> bankClient1.getIndex() == szukanyClientIndex);


        if (result == false){
            throw new IndexException("nie mamy takiego klienta");
        }

    }

//    public BankClient przelew(int indexKlienta, int kwotaPrzelewu){
//        boolean nadawca = bankClientList.contains(indexKlienta);
//
//        if(nadawca == false){
//            throw new IndexException("nie ma takiego klienta");
//        }
//
//        przelew().setSaldo();
//
//
//        return bankClient;
//    }
}
