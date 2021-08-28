package uz.mehrojbek.appbookshop.utils;

import org.springframework.data.domain.Page;
import uz.mehrojbek.appbookshop.entity.Client;
import uz.mehrojbek.appbookshop.payload.ClientDto;
import uz.mehrojbek.appbookshop.payload.CustPage;

public class CommonUtils {

    public static CustPage<ClientDto> custPageBuilder(Page<Client> page){
        return new CustPage<>(
                page.getNumber(),
                page.getNumberOfElements(),
                page.getSize(),
                page.getSort(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getPageable()
        );
    }

}
