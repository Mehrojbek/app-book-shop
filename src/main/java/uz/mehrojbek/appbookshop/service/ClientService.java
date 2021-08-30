package uz.mehrojbek.appbookshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.mehrojbek.appbookshop.entity.Client;
import uz.mehrojbek.appbookshop.exception.RestException;
import uz.mehrojbek.appbookshop.payload.ApiResult;
import uz.mehrojbek.appbookshop.payload.ClientDto;
import uz.mehrojbek.appbookshop.payload.CustPage;
import uz.mehrojbek.appbookshop.repository.ClientRepository;
import uz.mehrojbek.appbookshop.utils.CommonUtils;

import java.util.UUID;
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

    public ApiResult<ClientDto> getOne(UUID id){
        Client client = clientRepository.findById(id).orElseThrow(() -> new RestException("Client topilmadi", HttpStatus.NOT_FOUND));
        return ApiResult.successRespWithData(clientDtoBuilder(client));
    }

    public ApiResult<?> add(ClientDto clientDto){
        try {
            Client client = new Client(
                    clientDto.getFullName(),
                    clientDto.getPhoneNumber(),
                    clientDto.getRegion(),
                    clientDto.getProductList(),
                    false
            );
            clientRepository.save(client);
            return ApiResult.successResponse("buyurtma saqlandi");
        }catch (Exception e){
            throw new RestException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ApiResult<?> delete(UUID id){
        try {
            clientRepository.deleteById(id);
            return ApiResult.successResponse("client deleted");
        }catch (Exception e){
            throw new RestException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ApiResult<?> edit(UUID id, ClientDto clientDto){
        try {
            Client client = clientRepository.findById(id).orElseThrow(() -> new RestException("client topilmadi", HttpStatus.NOT_FOUND));
            client.setFullName(clientDto.getFullName());
            client.setPhoneNumber(clientDto.getPhoneNumber());
            client.setRegion(clientDto.getRegion());
            client.setProductList(clientDto.getProductList());
            client.setBuy(clientDto.isBuy());
            clientRepository.save(client);
            return ApiResult.successResponse("client tahrirlandi");
        }catch (Exception e){
            throw new RestException("clientni tahrirlashda xatolik : "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    private ClientDto clientDtoBuilder(Client client){
        return new ClientDto(
                client.getId(),
                client.getFullName(),
                client.getPhoneNumber(),
                client.getRegion(),
                client.getProductList(),
                client.isBuy()
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
