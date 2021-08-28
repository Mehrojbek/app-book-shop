package uz.mehrojbek.appbookshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.mehrojbek.appbookshop.entity.Client;
import uz.mehrojbek.appbookshop.entity.Order;
import uz.mehrojbek.appbookshop.payload.ApiResult;
import uz.mehrojbek.appbookshop.payload.ClientDto;
import uz.mehrojbek.appbookshop.payload.CustPage;
import uz.mehrojbek.appbookshop.payload.OrderDto;
import uz.mehrojbek.appbookshop.repository.ClientRepository;
import uz.mehrojbek.appbookshop.utils.CommonUtils;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public ApiResult<CustPage<ClientDto>> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Client> clientPage = clientRepository.findAll(pageable);
        CustPage<ClientDto> custPage = CommonUtils.custPageBuilder(clientPage);
        custPage.setContent(clientPage.getContent().stream().map(this::clientDtoBuilder).collect(Collectors.toList()));
        return ApiResult.successRespWithData(custPage);
    }

    public ApiResult<?> add(ClientDto clientDto){
        Client client = new Client(
                clientDto.getFullName(),
                clientDto.getPhoneNumber(),
                clientDto.getRegion()
        );
//        Order order = new Order(client,)
    }




    private ClientDto clientDtoBuilder(Client client){
        return new ClientDto(
                client.getId(),
                client.getFullName(),
                client.getPhoneNumber(),
                client.getRegion(),
                !Objects.isNull(client.getOrderList()) ? client.getOrderList().stream().map(this::orderDtoBuilder).collect(Collectors.toList()) : null
        );
    }

    private OrderDto orderDtoBuilder(Order order){
        return new OrderDto(
                order.getId(),
                order.getProductList()
        );
    }

    private CustPage<ClientDto> custPageBuilder(Page<Client> page){
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
