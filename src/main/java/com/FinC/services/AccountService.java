package com.FinC.services;

import com.FinC.dtos.AccountDto;
import com.FinC.models.Account;
import com.FinC.repositories.AccountRepository;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    EmailService emailService;

    public Account findById(UUID id){
        return accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
    }
    public List<Account> findAll(){
        return accountRepository.findAll();
    }
    public Account createAccount(AccountDto accountDto){
        var account = new Account();
        BeanUtils.copyProperties(accountDto,account);
//        emailService.enviarEmailTexto()
        return accountRepository.save(account);
    }
    public byte[] gerarPdf(UUID id) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        return byteArrayOutputStream.toByteArray();

    }
    public Account updateAccount(AccountDto accountDto,UUID id){
        var account = findById(id);
        BeanUtils.copyProperties(accountDto,account);
        return accountRepository.save(account);
    }
    public void deleteAccount(UUID id){
        var account = findById(id);
        accountRepository.delete(account);
    }

}
