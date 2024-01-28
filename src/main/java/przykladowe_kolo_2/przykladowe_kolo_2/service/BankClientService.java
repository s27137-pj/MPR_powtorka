package przykladowe_kolo_2.przykladowe_kolo_2.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import przykladowe_kolo_2.przykladowe_kolo_2.exception.IndexException;
import przykladowe_kolo_2.przykladowe_kolo_2.model.BankClient;
import przykladowe_kolo_2.przykladowe_kolo_2.repository.BankClientRepository;

@Service
@AllArgsConstructor
public class BankClientService {

    private BankClientRepository bankClientRepository;

    public BankClient save(BankClient bankClient){
        if(bankClient.getIndex() < 0){
            throw new IndexException("Nr klienta musi byc liczba dodatnia");
        }

        if(bankClient.getSaldo() <= 0){
            throw new IndexException("Saldo nie moze byc ujemne");
        }

        return bankClientRepository.save(bankClient);
    }

//    public BankClient check(BankClient bankClient){
//        boolean result = bankClientList.contains(bankClient);
//
//        if (result == false){
//            throw new IndexException("nie mamy takiego klienta");
//        }
//
//        return bankClient;
//    }

    public BankClient przelew(BankClient bankClient, int kwotaPrzelewu, int szukanyIndex){



        bankClientRepository.check(szukanyIndex);
        if(kwotaPrzelewu > bankClient.getSaldo()){
            throw new IndexException("Nie masz siana na taki przelew");
        }

        int noweSaldo = bankClient.getSaldo() - kwotaPrzelewu;

        bankClient.setSaldo(noweSaldo);


        return bankClientRepository.save(bankClient);
    }

}
