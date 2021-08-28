package uz.mehrojbek.appbookshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.mehrojbek.appbookshop.service.ClientService;
import uz.mehrojbek.appbookshop.utils.RestConstants;

@RestController
@RequestMapping(RestConstants.USER_CONTROLLER)
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;


}
