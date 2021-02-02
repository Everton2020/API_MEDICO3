package com.gbc.cadastromedico.controller;

import com.gbc.cadastromedico.dto.response.CepResponseDTO;
import com.gbc.cadastromedico.dto.resquest.CepRequestDTO;
import com.gbc.cadastromedico.model.ApiMessage;
import com.gbc.cadastromedico.model.Cep;
import com.gbc.cadastromedico.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/cep")
public class CepController {

    @Autowired
    private CepRepository repository;

    @GetMapping("/{cep}")
    public ResponseEntity<Object> getByCep(@PathVariable String cep) {
        Cep cepObject = repository.getByCep(cep);
        if (cepObject != null) {
            return new ResponseEntity<>(new CepResponseDTO(cepObject), HttpStatus.OK);
        } else {
            String url = "https://viacep.com.br/ws/"+cep+"/json/";
            RestTemplate restTemplate = new RestTemplate();
            try {
                Cep cepResponse = restTemplate.getForObject(url, Cep.class);
                this.repository.save(cepResponse);
                return new ResponseEntity<>(new CepResponseDTO(cepResponse), HttpStatus.OK);
            } catch (HttpClientErrorException httpClientErrorException) {
                return new ResponseEntity<>(new ApiMessage(httpClientErrorException.getMessage()), httpClientErrorException.getStatusCode());
            }

        }
    }

    @PostMapping
    public ResponseEntity<Object> postCep(@RequestBody CepRequestDTO cepRequestDTO) {
        if (cepRequestDTO.getCep() == "" || cepRequestDTO.getCep() == null) {
            return new ResponseEntity<>(new ApiMessage("Informações Incorretas"), HttpStatus.BAD_REQUEST);
        } else {
            Cep cep = this.repository.save(cepRequestDTO.build());
            return new ResponseEntity<>(cep, HttpStatus.CREATED);
        }

    }
}


